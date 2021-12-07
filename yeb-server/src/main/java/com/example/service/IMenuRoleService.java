package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.MenuRole;
import com.example.pojo.RespBean;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * create by: mlNothing
     * description: 根据角色id更新菜单id
     * create time: 2021/12/7 15:28
     * @param rid 角色id
     * @param mids 菜单id
     * @return  RespBean
     */
    RespBean updateMenuRole(@RequestBody Integer rid, Integer[] mids);

}
