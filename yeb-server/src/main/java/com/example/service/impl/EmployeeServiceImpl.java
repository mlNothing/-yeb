package com.example.service.impl;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.EmployeeMapper;
import com.example.mapper.MailLogMapper;
import com.example.pojo.*;
import com.example.service.IEmployeeService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailLogMapper mailLogMapper;

    @Override
    public RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        Page<Employee> page=new Page<>(currentPage,size);
        IPage<Employee> employeeByPage=employeeMapper.getEmployeeByPage(page,employee,beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(),employeeByPage.getRecords());
        return respPageBean;
    }

    @Override
    public RespBean getMaxWorkID() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        return RespBean.success(null, String.format("%08d",Integer.parseInt(maps.get(0).get("max(workID)").toString()+1)));
    }

    @Override
    public RespBean addEmployee(Employee employee) {
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        double contractTerm = Double.parseDouble(decimalFormat.format(days / 365));
        employee.setContractTerm(contractTerm);
        if (1== employeeMapper.insert(employee)) {
            //            获取插入的员工信息
            Employee emp = employeeMapper.getAllEmps(employee.getId()).get(0);
//            数据库记录发送的消息id
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            mailLog.setStatus(0);
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_NAME);
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            mailLog.setCount(0);
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            mailLog.setCreateTime(LocalDateTime.now());
            mailLog.setUpdateTime(LocalDateTime.now());
//          消息落户
            mailLogMapper.insert(mailLog);
//            将员工信息发送到消息队列（mail.welcome） 最后一定要把消息的id放进去
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,MailConstants.MAIL_ROUTING_NAME,emp,new CorrelationData(msgId));
             return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @Override
    public List<Employee> getAllEmps(Integer id) {
        return employeeMapper.getAllEmps(id);
    }

    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer pageSize) {
        Page<Employee> page=new Page<>(currentPage,pageSize);
        IPage<Employee> employeeByPage=employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(),employeeByPage.getRecords());
        return respPageBean;
    }

}
