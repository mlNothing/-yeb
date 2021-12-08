package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
   /**
    * create by: mlNothing
    * description:
    * create time: 2021/12/8 17:06
    * @param adminId 操作员id
    * @param rids 角色id
    * @return Integer
    */
   Integer addAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
