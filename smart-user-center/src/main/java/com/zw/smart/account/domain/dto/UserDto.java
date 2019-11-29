package com.zw.smart.account.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.io.Serializable;


/**
 * @author zhangwei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements Serializable {
    private int uid;
    /**
     * 用户名
     * 账号规则必须是 字母开头+数字    6 16
     * a-z + A-Z 0-9 _
     */
    @Pattern(regexp = "^[a-zA-Z]\\w{5,15}$")
    private String username;
    /**
     * 密码
     * \w 表示  A-Z a-z 0-9 _
     * <p>
     * 首字母必须大写  数字+ 字母  必须8位
     */
    @Pattern(regexp = "^[A-Z]\\w{7,15}$")
    private String password;

    /**
     * 手机
     * 验证
     */
    @Pattern(regexp = "^1[3-9]\\d{9}$")
    private String phone;

    /**
     * 性别 1 表示男 0 表示女
     */
    private Boolean sex;

    /**
     * 邮箱
     * 邮箱的地址规则：数字、字母、下划线 + @ + 数字、英文 + . +英文
     */
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")
    private String email;
    /**
     * 备注
     */
    private String mark;

}
