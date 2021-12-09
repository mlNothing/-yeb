package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Employee;
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
}
