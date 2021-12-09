package com.example.controller;


import com.example.pojo.*;
import com.example.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.portable.IDLEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private INationService nationService;

    @Autowired
    private IPoliticsStatusService politicsStatusService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IJoblevelService joblevelService;

    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有员工(分页)")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope
                                    ){
                return employeeService.getEmployee(currentPage,size,employee,beginDateScope);
    }
    @ApiOperation(value="获取全部民族")
    @GetMapping("/nations")
    public List<Nation> getNations(){
        return nationService.list();
    }


    @ApiOperation(value = "获取全部政治面貌")
    @GetMapping("/politicsStatuses")
    public List<PoliticsStatus> getPoliticsStatuses(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取全部部门信息")
    @GetMapping("/deps")
    public List<Department> getDeps(){
       return departmentService.list();
    }

    @ApiOperation(value = "获取全部职称信息")
    @GetMapping("/joblevels")
    public List<Joblevel> getJoblevels(){
        return joblevelService.list();
    }


    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/positions")
    public List<Position> gePositions(){
        return positionService.list();
    }

    @ApiOperation(value = "获取最大工号")
    @GetMapping("/macWorkID")
    public RespBean getMaxWorkID(){
       return employeeService.getMaxWorkID();
    }

    @ApiOperation(value = "添加操作员")
    @PutMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }


}
