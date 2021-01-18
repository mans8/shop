package com.hgx.shop.cart.services.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hgx.common.utils.R;
import com.hgx.shop.cart.feign.ProductFeignService;
import com.hgx.shop.cart.interceptor.CartInterceptor;
import com.hgx.shop.cart.services.CartService;
import com.hgx.shop.cart.vo.Cart;
import com.hgx.shop.cart.vo.CartItem;
import com.hgx.shop.cart.vo.SkuInfoVo;
import com.hgx.shop.cart.vo.UserInfoTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author hgx
 * @date 2021/1/5 20:46
 * @Description 购物车Service
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    ProductFeignService productFeignService;

    @Autowired
    ThreadPoolExecutor executor;

    private final String CART_PREFIX = "shop:cart:";

    @Override
    public CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {

        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String res = (String) cartOps.get(skuId.toString());
        if (StringUtils.isEmpty(res)) {
            //购物车无此商品
            //2.添加新到购物车
            //1.远程查询当前要添加的商品信息
            CartItem cartItem = new CartItem();
            CompletableFuture<Void> getSkuInfoTask = CompletableFuture.runAsync(() -> {
                R skuInfo = productFeignService.getSkuInfo(skuId);
                SkuInfoVo data = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                });
                cartItem.setCheck(true);
                cartItem.setCount(num);
                cartItem.setImage(data.getSkuDefaultImg());
                cartItem.setTitle(data.getSkuTitle());
                cartItem.setSkuId(skuId);
                cartItem.setPrice(data.getPrice());
            }, executor);

            //2.远程查询sku的组合信息
            CompletableFuture<Void> getSkuSaleAttrValues = CompletableFuture.runAsync(() -> {
                List<String> values = productFeignService.getSkuSaleAttrValues(skuId);
                cartItem.setSkuAttr(values);
            }, executor);

            //等两个查询都完成
            CompletableFuture.allOf(getSkuInfoTask, getSkuSaleAttrValues).get();
            //存放到redis
            String s = JSON.toJSONString(cartItem);
            cartOps.put(skuId.toString(), s);
            return cartItem;
        } else {
            CartItem cartItem = JSON.parseObject(res, CartItem.class);
            cartItem.setCount(cartItem.getCount() + num);

            cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
            return cartItem;
        }
    }

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();

        String str = (String) cartOps.get(skuId.toString());
        CartItem cartItem = JSON.parseObject(str, CartItem.class);
        return cartItem;
    }

    @Override
    public Cart getCart() throws ExecutionException, InterruptedException {

        Cart cart = new Cart();
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        if (userInfoTo.getUserId() != null) {
            //1.登录
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            //2.如果临时购物车的数据还没有合并【合并购物车】
            String tempCartKey = CART_PREFIX + userInfoTo.getUserkey();
            List<CartItem> tempCartItems = getCartItems(tempCartKey);
            if (tempCartItems != null) {
                //临时购物车有数据，需要合并
                for (CartItem item : tempCartItems) {
                    addToCart(item.getSkuId(), item.getCount());
                }
                //清除临时购物车数据
                clearCart(tempCartKey);
            }
            //3.获取登录后的购物车数据【包含合并过来的临时购物车数据，和登录后的购物车数据】
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);

        } else {
            //2.没登录
            String cartKey = CART_PREFIX + userInfoTo.getUserkey();
            //获取临时购物车的所有购物项
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);
        }
        return cart;
    }

    /**
     * 获取到要操作的购物车
     *
     * @return
     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        //1、
        String cartKey = "";
        if (userInfoTo.getUserId() != null) {
            //shop:cart:1
            cartKey = CART_PREFIX + userInfoTo.getUserId();
        } else {
            cartKey = CART_PREFIX + userInfoTo.getUserkey();
        }
        BoundHashOperations<String, Object, Object> operations = redisTemplate.boundHashOps(cartKey);
        return operations;
    }

    private List<CartItem> getCartItems(String cartKey) {

        BoundHashOperations<String, Object, Object> hashOps = redisTemplate.boundHashOps(cartKey);
        List<Object> values = hashOps.values();
        if (values != null && values.size() > 0) {
            List<CartItem> collect = values.stream().map((obj) -> {
                String str = (String) obj;
                CartItem cartItem = JSON.parseObject(str, CartItem.class);
                return cartItem;
            }).collect(Collectors.toList());
            return collect;
        }
        return null;
    }

    @Override
    public void clearCart(String cartkey) {
        redisTemplate.delete(cartkey);
    }

    /**
     * 勾选购物项
     *
     * @param skuId
     * @param check
     */
    @Override
    public void checkItem(Long skuId, Integer check) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCheck(check == 1 ? true : false);
        String s = JSON.toJSONString(cartItem);
        cartOps.put(skuId.toString(), s);
    }
}
