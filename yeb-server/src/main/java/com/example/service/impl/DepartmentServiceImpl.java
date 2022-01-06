package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.DepartmentMapper;
import com.example.pojo.Department;
import com.example.pojo.RespBean;
import com.example.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private  DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override

    public List<Department> getAllDepartmentWithChildren() {
        return departmentMapper.getAllDepartmentWithChildren(-1);
    }

    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if (1==department.getResult()){
            return RespBean.success("添加成功",department);
        }
        return RespBean.error("添加失败");
    }

    @Override
    public RespBean deleDePartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleDePartment(department);
        if (-1==department.getResult()){
            return RespBean.error("删除失败，该部门下还有员工");
        }
        if (-2==department.getResult()){
           return RespBean.error("删除失败,该部门下还有子部门");
       }
        if (1==department.getResult()){
            return RespBean.error("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
