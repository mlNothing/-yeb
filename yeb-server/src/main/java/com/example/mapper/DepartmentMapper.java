package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Department;
import com.example.pojo.RespBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartmentWithChildren(Integer pid);

    void addDepartment(Department department);
}
