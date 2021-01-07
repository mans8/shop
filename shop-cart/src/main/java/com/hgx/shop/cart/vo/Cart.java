package com.hgx.shop.cart.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author hgx
 * @date 2021/1/5 20:02
 * @Description 购物车。需要计算的属性必须重写get方法，保证每次获取的数据都会进行计算
 *
 */
public class Cart {

    List<CartItem> items;
    //商品数量
    private Integer countNum;
    //商品类型数量
    private Integer countType;
    //商品总价
    private BigDecimal totalAmount;
    //减免价格
    private BigDecimal reduce = new BigDecimal("0.00");

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getCountNum() {
        int count = 0;
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                count += item.getCount();
            }
        }
        return count;
    }

    public Integer getCountType() {
        int count = 0;
        if (items != null && items.size() > 0) {
            for (CartItem item : items) {
                count += 1;
            }
        }
        return count;
    }

    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0");
        //1.计算购物项总价
        if (items!= null && items.size()>0){
            for (CartItem item : items){
                BigDecimal totalPrice = item.getTotalPrice();
                amount = amount.add(totalPrice);
            }
        }
        //2.减去优惠总价
        BigDecimal subtract = amount.subtract(getReduce());
        return subtract;
    }

    public BigDecimal getReduce() {
        return reduce;
    }

    public void setReduce(BigDecimal reduce) {
        this.reduce = reduce;
    }
}
