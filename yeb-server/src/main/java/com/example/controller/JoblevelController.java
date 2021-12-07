package com.example.controller;


import com.example.pojo.Joblevel;
import com.example.pojo.RespBean;
import com.example.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * 职位管理
 * @author mlx
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/system/basic/jobLevel")
public class JoblevelController {
    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation(value = "获取职称的所有信息")
    @GetMapping("/")
    public List<Joblevel> getAllJoblevels(){
        return  joblevelService.list();
    }

    @ApiOperation(value = "添加职称信息")
    @PostMapping("/")
    public RespBean addJoblevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            return RespBean.success("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @ApiOperation("/修改职称信息")
    @PutMapping("/")
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){
            return RespBean.success("修改成功");
        }return RespBean.error("修改失败");
    }


    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{ids}")
    public  RespBean deleteJoblevel(Integer id){
       if( joblevelService.removeById(id)){
           return RespBean.success("删除成功");
       }return RespBean.error("删除失败");
    }

    @ApiOperation(value = "批量删除职称信息")
    @DeleteMapping("/")
    public RespBean deleteJoblevelByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
