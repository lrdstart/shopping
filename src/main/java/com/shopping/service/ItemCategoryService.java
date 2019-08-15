package com.shopping.service;

import com.shopping.model.ItemCategories;

import java.util.List;

/**
 * @author LRD
 * @create 2019-05-26 20:22
 */
public interface ItemCategoryService {

    ItemCategories findOne(Integer categoryId);

    List<ItemCategories> findAll();

    int save(ItemCategories ItemCategories);

    int updatebyId(Integer categoryId,String categoryName);

    int deleteItemById(Integer categoryId);
}
