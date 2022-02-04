package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Department;
import com.example.pojo.RespBean;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * create by: mlNothing
     * description: 获取所有部分信息
     * create time: 2021/12/8 10:48
     * @return  list
     */
     List<Department> getAllDepartmentWithChildren();

    /**
     * create by: mlNothing
     * description: 添加部门
     * create time: 2021/12/8 10:56
     * @param department 部门
     * @return RespBean
     */
     RespBean addDepartment(Department department);
    /**
     * create by: mlNothing
     * description: 根据id删除部门
     * create time: 2021/12/8 14:35
     * @param id id
     * @return RespBean
     */
     RespBean deleDePartment(Integer id);


    List<Department> getAllDepartments();
}
