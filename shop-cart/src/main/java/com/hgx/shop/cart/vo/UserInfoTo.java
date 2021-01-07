package com.hgx.shop.cart.vo;

import lombok.Data;

/**
 * @author hgx
 * @date 2021/1/6 19:55
 * @Description
 */
@Data
public class UserInfoTo {

    private Long userId;
    private String userkey;
    private boolean tempUser = false;

}
