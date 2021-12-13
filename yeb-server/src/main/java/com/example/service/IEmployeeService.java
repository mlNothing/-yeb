package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import com.example.pojo.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * create by: mlNothing
     * description: 获取所有员工分页
     * create time: 2021/12/8 20:40
     * @param currentPage 当前页
     * @param size 条数
     * @param employee 实体类
     * @param beginDateScope 时间范围
     * @return RespPageBean
     */
    RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * create by: mlNothing
     * description: 添加的时候自动获取最大工号
     * create time: 2021/12/9 10:37
     * @param
     * @return 
     */
    RespBean getMaxWorkID();

    /**
     * create by: mlNothing
     * description: 添加操作员工
     * create time: 2021/12/9 11:03
     * @param employee 操作员工
     * @return RespBean
     */
    RespBean addEmployee(Employee employee);

    /**
     * create by: mlNothing
     * description: 不带分页的查询所有员工
     * create time: 2021/12/9 15:44
     * @param id id
     * @return LIST
     */
    List<Employee> getAllEmps(Integer id);

    /**
     * create by: mlNothing
     * description: 查询员工的工资账套
     * create time: 2021/12/13 16:22
     * @param 
     * @return 
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer pageSize);
}
