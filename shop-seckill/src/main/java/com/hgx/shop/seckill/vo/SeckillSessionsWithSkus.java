package com.hgx.shop.seckill.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hgx
 * @date 2021/3/24 21:15
 * @Description
 */
@Data
public class SeckillSessionsWithSkus {

    private Long id;

    private String name;

    private Date startTime;

    private Date endTime;

    private Integer status;

    private Date createTime;

    private List<SeckillSkuVo> relationSkus;
}
