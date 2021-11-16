package com.example.service.impl;

import com.example.pojo.Admin;
import com.example.mapper.AdminMapper;
import com.example.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
