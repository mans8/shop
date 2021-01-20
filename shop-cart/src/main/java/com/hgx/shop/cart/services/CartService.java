package com.hgx.shop.cart.services;

import com.hgx.shop.cart.vo.Cart;
import com.hgx.shop.cart.vo.CartItem;

import java.util.concurrent.ExecutionException;

/**
 * @author hgx
 * @date 2021/1/5 20:45
 * @Description
 */
public interface CartService {

    /**
     * 将商品添加到购物车
     * @param skuId
     * @param num
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    /**
     * 获取购物车中某个购物项
     * @param skuId
     * @return
     */
    CartItem getCartItem(Long skuId);

    /**
     * 获取购物车
     * @return
     */
    Cart getCart() throws ExecutionException, InterruptedException;

    /**
     * 清空购物车数据
     * @param cartkey
     */
    void clearCart(String cartkey);

    /**
     * 勾选购物项
     * @param skuId
     * @param check
     */
    void checkItem(Long skuId, Integer check);

    /**
     * 修改购物项数量
     * @param skuId
     * @param num
     */
    void changeItemCount(Long skuId, Integer num);
}
