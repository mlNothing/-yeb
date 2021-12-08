package com.example.controller;


import com.example.pojo.Admin;
import com.example.pojo.RespBean;
import com.example.service.IAdminRoleService;
import com.example.service.IAdminService;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/system/admin/")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAdminRoleService adminRoleService;

    @ApiModelProperty(value = "根据关键词查找当前所有用户和所带的角色")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keyWords){
        return adminService.getAllAdmins(keyWords);
    }

    @ApiModelProperty(value = "增加操作员")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.save(admin)){
            return RespBean.success("添加成功");
        }return RespBean.error("添加失败");
    }
    @ApiModelProperty("根据id删除操作员")
    @GetMapping("/{id}")
    public RespBean deleteAdminById(@PathVariable Integer id){
        if (adminService.removeById(id)){
            return RespBean.success("删除成功");
        }return RespBean.error("删除失败");
    }

    @ApiModelProperty("根据id更新操作员所附带的角色")
    @PutMapping("/roles")
    public RespBean addAdminRole( Integer adminId,Integer[] rids){
        return adminRoleService.addAdminRole(adminId,rids);
    }
}
