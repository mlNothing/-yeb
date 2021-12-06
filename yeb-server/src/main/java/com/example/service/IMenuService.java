package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IMenuService extends IService<Menu> {
    /**
     * create by: mlNothing
     * description: 根据用户id获取菜单列表
     * create time: 2021/12/6 16:25
     * @param 
     * @return 
     */
    List<Menu> getMenuByAdminId();
    /**
     * create by: mlNothing
     * description: 根据角色获取菜单列表
     * create time: 2021/12/6 16:24
     * @param 
     * @return 
     */
    List<Menu> getAllMenusWithRole();
}
