package com.example.controller;

import com.example.pojo.Admin;
import com.example.pojo.RespBean;
import com.example.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author mlNothing
 * @date 2021/12/13 17:23
 */
@RestController
public class AdminInfoController {

    @Autowired
    private IAdminService adminService;
    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdminInfo(@RequestBody Admin admin, Authentication authentication) {
        //修改admin信息
        if (adminService.updateById(admin)) {
            //修改authentication信息
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(admin, authentication.getCredentials(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            return RespBean.success("用户信息更新成功！");
        }
        return RespBean.error("用户信息更新失败！");

    }
    @ApiOperation("更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updatePassword(@RequestBody Map<String,Object> info) {
        String oldPass= (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId= (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }


}