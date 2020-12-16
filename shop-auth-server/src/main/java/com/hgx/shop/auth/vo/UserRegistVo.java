package com.hgx.shop.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author hgx
 * @date 2020/12/14 17:46
 * @Description
 */
@Data
public class UserRegistVo {

    @NotEmpty(message = "用户名必须提交")
    @Length(min = 6, max = 18, message = "用户名必须是6-18位字符")
    private String username;

    @NotEmpty(message = "密码必须提交")
    @Length(min = 6, max = 18, message = "密码必须是6-18位字符")
    private String password;

    @NotEmpty(message = "手机号必须提交")
    private String phone;

    @NotEmpty(message = "验证码必须提交")
    private String code;
}
