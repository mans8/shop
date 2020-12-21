package com.hgx.shop.member.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2020/12/18 0:12
 * @Description
 */
@Data
public class SocialUser {
    private String access_token;
    private String remind_in;
    private long expires_in;
    private String uid;
    private String isRealName;
}
