package com.hgx.shop.member.exception;

/**
 * @author hgx
 * @date 2020/12/14 22:45
 * @Description
 */
public class UsernameExistException extends RuntimeException {

    public UsernameExistException() {
        super("用户名已存在");
    }
}
