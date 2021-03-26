package com.hgx.shop.order.to;

import com.hgx.shop.order.entity.OrderEntity;
import com.hgx.shop.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hgx
 * @date 2021/3/2 21:14
 * @Description
 */
@Data
public class OrderCreateTo {

    private OrderEntity order;

    private List<OrderItemEntity> orderItems;

    private BigDecimal payPrice;//订单计算的应付价格

    private BigDecimal fare;//运费

}
