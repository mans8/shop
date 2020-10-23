package com.hgx.shop.search.services.impl;

import com.alibaba.fastjson.JSON;
import com.hgx.common.to.es.SkuEsModel;
import com.hgx.shop.search.config.ShopElasticSearchConfig;
import com.hgx.shop.search.constant.EsConstant;
import com.hgx.shop.search.services.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hgx
 * @date 2020/10/23 17:07
 * @Description
 */
@Slf4j
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    /**
     * 商品上架时保存数据到es
     *
     * @param skuEsModels
     */
    @Override
    public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {

        //保存到es
        //1.给es中建立索引，product，建立好映射关系

        //2.给es中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsModel model : skuEsModels) {
            //1.构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(model.getSkuId().toString());
            String s = JSON.toJSONString(model);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }


        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ShopElasticSearchConfig.COMMON_OPTIONS);
        //TODO 如果批量错误
        boolean b = bulk.hasFailures();
        List<String> collect = Arrays.stream(bulk.getItems()).map(item -> {
            return item.getId();
        }).collect(Collectors.toList());
        log.info("商品上架完成：{}，返回数据：", collect, bulk.toString());
        return b;
    }
}
