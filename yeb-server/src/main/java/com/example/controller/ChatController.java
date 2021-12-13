package com.example.controller;

import com.example.pojo.Admin;
import com.example.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mlNothing
 * @date 2021/12/13 17:08
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Resource
    private IAdminService adminService;
    @ApiOperation(value = "获取所有操作员")
    @GetMapping("/admin")
    public List<Admin> getAllEmployee(String keyword){
        return adminService.getAllAdmins(keyword);
    }
}
