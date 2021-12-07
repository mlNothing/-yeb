package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.pojo.Menu;
import com.example.pojo.MenuRole;
import com.example.pojo.Role;
import com.example.pojo.RespBean;
import com.example.service.IMenuRoleService;
import com.example.service.IMenuService;
import com.example.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mlNothing
 * @date 2021/12/7 10:33
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    public static final String ROLE_ = "ROLE_";

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色信息")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }
    @ApiOperation(value = "添加角色信息")
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith(ROLE_)){
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
    @DeleteMapping("/role/{id}")
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

    @ApiOperation(value = "根据角色获取所有菜单")
    @GetMapping("/menus")
    public  List<Menu> getAllMenus(){
       return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/min/{rid}")
    public List<Integer> getMidById(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid)).stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation(value = "根据角色id修改菜单id")
    @PutMapping("")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
