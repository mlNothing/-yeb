package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mlx
 * @since 2021-11-16
 */
@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
   /**
    * 根据用户id获取权限列表
        * @param adminId
        * @return
        */
    List<Role> getRoles(Integer adminId);


}
