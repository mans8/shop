package com.hgx.shop.auth.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2020/12/21 19:33
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
