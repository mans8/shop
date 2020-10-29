package com.hgx.shop.product.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author hgx
 * @date 2020/10/25 21:34
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catelog2Vo {

    private String catelog1Id;//一级父分类id
    private List<Catelog3Vo> catalog3List;//三级子分类
    private String id;
    private String name;


    /**
     * 三级分类
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Catelog3Vo{
        private String catalog2Id;//负分类，2级分类id
        private String id;
        private String name;
    }
}
