package com.example.controller;

import com.example.pojo.Role;
import com.example.pojo.RespBean;
import com.example.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author mlNothing
 * @date 2021/12/7 10:33
 */
@RestController
@RequestMapping("/system/basic/Role")
public class PermissionController {
    
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取所有角色信息")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }
    @ApiOperation(value = "添加角色信息")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE"+role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功!");
        }
        return RespBean.error("添加失败!");
    }
    @ApiOperation(value = "更新角色信息")
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role) {
        if (roleService.updateById(role)) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败！");
    }
    @ApiOperation(value = "删除角色信息")
    @DeleteMapping("/{id}")
    public RespBean deleteRole(@PathVariable Integer id) {
        if (roleService.removeById(id)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
    @ApiOperation(value = "批量删除角色信息")
    @DeleteMapping("/")
    public RespBean deleteRoleByIds(Integer[] ids) {
        if (roleService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

}
