package com.example.controller;


import com.example.pojo.Menu;
import com.example.service.IMenuRoleService;
import com.example.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/config")
public class MenuController {
    @Autowired
    private IMenuService menuService;
    @GetMapping("/menu")
    @ApiOperation(value = "通过用户id获取菜单列表")
    private List<Menu> getMenuByAdminId(){
        return menuService.getMenuByAdminId();
    }

}
