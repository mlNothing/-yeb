package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@Repository
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * create by: mlNothing
     * description:
     * create time: 2021/12/8 20:50
     * @param page page
     * @param employee employee
     * @param beginDateScope beginDateScope
     * @return IPage
     */
    IPage<Employee> getEmployeeByPage(@Param("page") Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * create by: mlNothing
     * description: 查询所有的操作员工
     * create time: 2021/12/9 15:46
     * @param id id
     * @return list
     */
    List<Employee> getAllEmps(Integer id);
}
