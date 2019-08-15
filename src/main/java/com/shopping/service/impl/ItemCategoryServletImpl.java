package com.shopping.service.impl;

import com.shopping.mapper.ItemCategoriesMapper;
import com.shopping.model.ItemCategories;
import com.shopping.model.ItemCategoriesExample;
import com.shopping.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LRD
 * @create 2019-05-28 20:08
 */
@Service
@Transactional
public class ItemCategoryServletImpl implements ItemCategoryService {
    @Autowired
    private ItemCategoriesMapper itemCategoriesMapper;

    @Override
    public ItemCategories findOne(Integer categoryId) {
        return itemCategoriesMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<ItemCategories> findAll() {
        ItemCategoriesExample example = new ItemCategoriesExample();
        List<ItemCategories> categories = itemCategoriesMapper.selectByExample(example);
        return categories;
    }

    @Override
    public int save(ItemCategories category) {
        int i = 0;
        try {
            i = itemCategoriesMapper.insert(category);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updatebyId(Integer categoryId, String categoryName) {
        ItemCategories category = new ItemCategories();
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryName);
        int i = 0;
        try {
            i = itemCategoriesMapper.updateByPrimaryKey(category);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }

    @Override
    public int deleteItemById(Integer categoryId) {
        int i =0;
        try {
            i = itemCategoriesMapper.deleteByPrimaryKey(categoryId);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }
}
