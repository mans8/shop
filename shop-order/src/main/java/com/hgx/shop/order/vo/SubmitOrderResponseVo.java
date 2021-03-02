package com.hgx.shop.order.vo;

import com.hgx.shop.order.entity.OrderEntity;
import lombok.Data;

/**
 * @author hgx
 * @date 2021/3/2 20:49
 * @Description
 */
@Data
public class SubmitOrderResponseVo {

    private OrderEntity orderEntity;
    //错误状态码 0成功
    private Integer code;

}
