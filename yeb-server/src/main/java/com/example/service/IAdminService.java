package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Admin;
import com.example.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IAdminService extends IService<Admin> {

    RespBean login(String username, String password, String code,HttpServletRequest request);

    Admin getAdminByUserName(String username);
}
