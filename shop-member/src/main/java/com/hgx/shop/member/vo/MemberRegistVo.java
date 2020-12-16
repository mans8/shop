package com.hgx.shop.member.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * @author hgx
 * @date 2020/12/14 22:04
 * @Description
 */
@Data
public class MemberRegistVo {

    private String username;

    private String password;

    private String phone;
}
