package com.hgx.common.exception;

/**
 * @author hgx
 * @date 2020/10/16 23:11
 * @Description 异常状态码枚举
 * 1.错误码定义规则为5位数字
 * 2.前两位表示业务场景，最后三位表示错误码。例如：10001。10：通用  001：数据校验出现错误
 * 3.维护错误码后需要维护错误描述，将他们定义为枚举类型
 * 错误码列表：
 * 10：通用
 *      001：数据校验出现错误
 *      002: 段短信验证码频率太高
 * 11：商品
 * 12：订单
 * 13：购物车
 * 14：物流
 */
public enum BizCodeEnume {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "数据校验出现错误"),
    SMS_CODE_EXCEPTION(10002, "验证码获取频率太高，请稍后再试"),
    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),
    USER_EXIST_EXCEPTION(15001, "用户存在异常"),
    PHONE_EXIST_EXCEPTION(15002, "手机号存在异常");

    private int code;
    private String message;

    BizCodeEnume(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
