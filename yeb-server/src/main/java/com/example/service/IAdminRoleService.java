package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.AdminRole;
import com.example.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
public interface IAdminRoleService extends IService<AdminRole> {

    /**
     * create by: mlNothing
     * description:操作员更新角色
     * create time: 2021/12/8 16:58
     * @param adminId 操作员id
     *  @param rids 角色id
     * @return RespBean
     */
    RespBean addAdminRole( Integer adminId, Integer[] rids);

}
