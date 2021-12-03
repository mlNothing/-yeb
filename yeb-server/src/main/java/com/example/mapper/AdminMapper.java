package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Admin;
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
public interface AdminMapper extends BaseMapper<Admin> {

}
