package com.example.controller;


import com.example.pojo.Department;
import com.example.pojo.RespBean;
import com.example.service.IDepartmentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("获取所有部门信息")
    @GetMapping("/")
    public List<Department> getAllDepartmentWithChildren(){
        return departmentService.getAllDepartmentWithChildren();
    }


    @ApiOperation("添加部门信息")
    @PostMapping("/")
    public RespBean addDepartment(@RequestBody  Department department){
      return departmentService.addDepartment(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleDePartment(@PathVariable  Integer id){
        return departmentService.deleDePartment(id);
    }

}
