package com.hgx.shop.search.services;

import com.hgx.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * @author hgx
 * @date 2020/10/23 17:04
 * @Description
 */
public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
