package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface AdminMapper extends BaseMapper<Admin> {
   /**
    * create by: mlNothing
    * description: 获取其他用户
    * create time: 2021/12/8 16:02
    * @param id id
    * @param keyWords  keyWords
    * @return List<Admin>
    */
   List<Admin> getAllAdmins(@Param("id") Integer id,@Param("keyWords") String keyWords);
}
