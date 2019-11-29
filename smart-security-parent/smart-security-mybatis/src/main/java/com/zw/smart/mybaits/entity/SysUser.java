package com.zw.smart.mybaits.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SysUser implements UserDetails {
    private Integer uid;
    private String username;
    private String password;
    /**
     * 一个用户可以拥有多个角色(权限)
     */
    private List<SysRole> roles = new ArrayList<>();

    /**
     * 返回的角色集合
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}