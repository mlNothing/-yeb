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

    public RespBean updateMenuRole(@RequestBody Integer rid, Integer[] mids);

}
