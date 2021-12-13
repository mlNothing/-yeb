package com.example.controller;


import com.example.pojo.RespBean;
import com.example.pojo.Salary;
import com.example.service.ISalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;

    /**
     * create by: mlNothing
     * description: 获取
     * create time: 2021/12/12 23:50
     * @param
     * @return list
     */
    @ApiOperation("获取所有的工资账套")
    @GetMapping("/")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    /**
     * create by: mlNothing
     * description: 更新工资账套
     * create time: 2021/12/12 23:50
     * @param salary salary
     * @return
     */
    @ApiOperation("添加工资账套")
    @PostMapping("/")
    public RespBean addSalary(@RequestBody Salary salary){
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)){
            return RespBean.success("添加成功");
        }return RespBean.error("添加失败");
    }

    /**
     * create by: mlNothing
     * description:
     * create time: 2021/12/12 23:53
     * @param id id
     * @return
     */
    @ApiOperation("删除工资账套")
    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable Integer id){
        if (salaryService.removeById(id)){
            return RespBean.success("删除成功");
        }return RespBean.error("删除失败");
    }

    @ApiOperation("更新工资账套")
    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary){
        if (salaryService.updateById(salary)){
            return RespBean.success("更新成功");
        }return RespBean.error("更新失败");
    }

}
