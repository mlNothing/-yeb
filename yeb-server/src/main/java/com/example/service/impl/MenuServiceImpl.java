package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.MenuMapper;
import com.example.pojo.Admin;
import com.example.pojo.Menu;
import com.example.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    private  MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuByAdminId() {
        Admin principal = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuMapper.getMenuByAdminId(principal.getId());
    }
}
