package com.zw.smart.mybaits.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
public class SysRole implements GrantedAuthority {
    private Integer roleId;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色说明
     */
    private String description;

    private List<SysUser> users;

    /**
     * 返回角色的名称
     * @return
     */
    @Override
    public String getAuthority() {
        return name;
    }
}