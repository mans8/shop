package com.hgx.shop.search.services;

import com.hgx.shop.search.vo.SearchParam;
import com.hgx.shop.search.vo.SearchResult;

/**
 * @author hgx
 * @date 2020/11/2 21:42
 * @Description
 */
public interface MallSearchService {
    /**
     *
     * @param param 检索的所有参数
     * @return 返回检索结果
     */
    SearchResult search(SearchParam param);
}
