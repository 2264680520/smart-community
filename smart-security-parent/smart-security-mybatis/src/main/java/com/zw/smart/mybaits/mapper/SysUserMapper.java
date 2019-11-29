package com.zw.smart.mybaits.mapper;

import com.zw.smart.mybaits.entity.SysUser;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser selectByUsername(@Param("username") String username);
}