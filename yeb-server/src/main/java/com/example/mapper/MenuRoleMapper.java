package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.MenuRole;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * create by: mlNothing
     * description: 根据角色id插入菜单id
     * create time: 2021/12/7 15:29
     * @param rid 角色id
     * @param mids 菜单id
     * @return Integer 条数进行判断是否成功
     */
    Integer insertRecord(@Param("rid")Integer rid,@Param("mids")Integer[] mids);
}
