package com.example.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.example.pojo.*;
import com.example.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    @ApiOperation(value = "更新操作员")
    @PostMapping("/")
    public  RespBean updateEmployee(@RequestBody Employee employee){
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功");
        }
        return  RespBean.error("更新失败");
    }
    @ApiOperation(value = "删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleEmployeeById(@PathVariable  Integer id){
        if (employeeService.removeById(id)){
            return RespBean.success("删除成功");
        }return RespBean.error("删除失败");
    }

    @ApiOperation(value = "导出数据")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public RespBean exportEmployee(HttpServletResponse response){
//        如果不传参就是查询所有的员工
        List<Employee> employees=  employeeService.getAllEmps(null);
//        1:文件名 2:表头 3:03版的 .xsl结尾 07的可以查看03的 03看不了07
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, employees);
        ServletOutputStream outputStream=null;
        try {
//            流形式
            response.setHeader("content-type","application/octet-stream");
//            防止中文乱码
            response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode("员工表.xls","UTF-8"));
           outputStream = response.getOutputStream();
            workbook.write(outputStream);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
