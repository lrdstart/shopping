package com.shopping.service.impl;

import com.shopping.mapper.ItemsMapper;
import com.shopping.model.Items;
import com.shopping.model.ItemsExample;
import com.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LRD
 * @create 2019-05-27 15:43
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public Items findOne(Integer itemId) {
        return itemsMapper.findOne(itemId);
    }

    @Override
    public List<Items> findUpAll(Integer page, Integer limit, Integer categoryId, String itemName) {
        List<Items> itemsList = itemsMapper.selectAll(categoryId, itemName);
        return itemsList;
    }


    @Override
    public int save(Items item) {
        int i = 0;
        try {
            i = itemsMapper.insertSelective(item);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return i;
    }

    @Override
    public int updateOne(Items items, Integer itemId) {
        ItemsExample example = new ItemsExample();
        int i = 0;
        try {
            i = itemsMapper.updateByExampleSelective(items, example, itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }

    @Override
    public int deleteItemById(Integer itemId) {
        int i = 0;
        try {
            i = itemsMapper.deleteItemById(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

}
