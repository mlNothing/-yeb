package com.example.controller;


import com.example.pojo.Admin;
import com.example.service.IAdminService;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/system/admin/")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @ApiModelProperty(value = "根据关键词查找当前所有用户")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keyWords){
        return adminService.getAllAdmins(keyWords);
    }
}
