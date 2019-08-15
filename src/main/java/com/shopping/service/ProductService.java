package com.shopping.service;

import com.shopping.model.Items;

import java.util.List;

/**
 * @author LRD
 * @create 2019-05-26 20:21
 */
public interface ProductService {

    Items findOne(Integer itemId);

    /**
     * 查询所有在架商品列表
     *
     * @return
     */
    List<Items> findUpAll(Integer page, Integer limit, Integer categoryId, String itemName);

    int save(Items productInfo);

    int updateOne(Items items,Integer itemId);

    int deleteItemById(Integer itemId);
}
