package com.shopping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.mapper.ItemCategoriesMapper;
import com.shopping.mapper.UsersMapper;
import com.shopping.model.*;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.interfaces.PBEKey;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingApplicationTests {

    @Autowired
    private ItemCategoriesMapper itemCategoriesMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Test
    public void test1(){
        ItemCategoriesExample example = new ItemCategoriesExample();
        List<ItemCategories> categories = itemCategoriesMapper.selectByExample(example);

        System.out.println(categories);
    }
    @Test
    public void test2(){
        ItemCategories categories = new ItemCategories();
        categories.setCategoryId(6);
        categories.setCategoryName("路由器");
        itemCategoriesMapper.insertSelective(categories);

    }
    @Test
    public void test3(){
        ItemCategories categories = new ItemCategories();
        categories.setCategoryId(5);
        categories.setCategoryName("智能家电");
        itemCategoriesMapper.updateByPrimaryKey(categories);
    }
    @Test
    public void test4(){
        ItemCategoriesExample example = new ItemCategoriesExample();
        int i = itemCategoriesMapper.countByExample(example);
        System.out.println(i);
    }
    @Test
    public void test5(){
        int page=5;
        int limit=6;
        int categoryId=1;
        Page<Object> objects = PageHelper.startPage(page , limit);

        List<Items> itemsList = productService.findUpAll(page, limit,0,"小米9");
        //设置返回的总记录数
        PageInfo<Items> pageInfo = new PageInfo<>(itemsList);
        System.out.println(pageInfo.getTotal());
        System.out.println(itemsList);
        System.out.println("page:"+objects);
        System.out.println(pageInfo);
    }

    @Test
    public void test6(){
        Items one = productService.findOne(1);
        System.out.println(one);
    }
    @Test
    public void test7(){
        List<Orders> orders = orderService.findAll(1, 5);
        System.out.println(orders);
    }

}
