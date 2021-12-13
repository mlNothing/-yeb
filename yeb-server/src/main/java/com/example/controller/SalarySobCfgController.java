package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.pojo.Employee;
import com.example.pojo.RespBean;
import com.example.pojo.RespPageBean;
import com.example.service.IEmployeeService;
import com.example.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author mlNothing
 * @date 2021/12/13 16:12
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeService employeeService;

    /**
     * create by: mlNothing
     * description: 查询所有员工账套
     * create time: 2021/12/13 16:22
     * @param
     * @return
     */
    @ApiOperation("查询所有员工账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "10") Integer pageSize){

        return employeeService.getEmployeeWithSalary(currentPage,pageSize);

    }

    /**
     * create by: mlNothing
     * description: 更新员工账套
     * create time: 2021/12/13 16:22
     * @param
     * @return
     */
    @ApiOperation("更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid){
        if(employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid
        ))){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

}
