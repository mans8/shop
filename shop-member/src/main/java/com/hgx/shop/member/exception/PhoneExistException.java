package com.hgx.shop.member.exception;

/**
 * @author hgx
 * @date 2020/12/14 22:43
 * @Description
 */
public class PhoneExistException extends RuntimeException {
    public PhoneExistException(){
        super("手机号已存在");
    }
}
