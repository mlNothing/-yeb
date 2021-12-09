package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import com.example.pojo.RespPageBean;

import java.time.LocalDate;

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

}
