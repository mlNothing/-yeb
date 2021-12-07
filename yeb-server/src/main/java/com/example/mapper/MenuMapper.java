package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * create by: mlNothing
     * description: 根据用户id查菜单
     * create time: 2021/12/3 11:33
     * @param id id
     * @return list
     */
    List<Menu> getMenuByAdminId(Integer id);

    /**
     * create by: mlNothing
     * description: 通过角色获取所有菜单
     * create time: 2021/12/7 10:09
     * @param 
     * @return 
     */
    List<Menu> getAllMenusWithRole();
}
