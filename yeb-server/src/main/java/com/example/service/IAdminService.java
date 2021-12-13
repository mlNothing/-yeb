package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Admin;
import com.example.pojo.RespBean;
import com.example.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IAdminService extends IService<Admin> {

    /**
     * create by: mlNothing
     * description: 登录方法
     * create time: 2021/12/7 10:06
     * @param username 姓名
     * @param password 密码
     * @param  code  验证码
     * @param  request request
     * @return resbean
     */
    RespBean login(String username, String password, String code,HttpServletRequest request);


    /**
     * create by: mlNothing
     * description: 获取用户信息
     * create time: 2021/12/7 10:07
     * @param username username
     * @return admin
     */
    Admin getAdminByUserName(String username);

    /**
     * create by: mlNothing
     * description: 获取所有的角色
     * create time: 2021/12/7 10:07
     * @param adminId
     * @return Liset
     */
    List<Role> getRoles(Integer adminId);

    /**
     * create by: mlNothing
     * description: 根据关键词获取当前用户查询到的所有用户
     * create time: 2021/12/8 15:44
     * @param keyWords 关键词
     * @return RespBean
     */
    List<Admin> getAllAdmins(String keyWords);

    /**
     * create by: mlNothing
     * description: 更新用户密码
     * create time: 2021/12/13 17:33
     * @param 
     * @return 
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
