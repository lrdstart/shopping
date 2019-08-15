package com.shopping.mapper;

import com.shopping.model.Items;
import com.shopping.model.ItemsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ItemsMapper {


    int deleteItemById(@Param("itemId") Integer itemId);

    Items findOne(@Param("itemId") Integer itemId);

    List<Items> selectAll(@Param("categoryId") Integer categoryId,@Param("itemName") String itemName);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    int countByExample(ItemsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    int deleteByExample(ItemsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    int insert(Items record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    int insertSelective(Items record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    List<Items> selectByExample(ItemsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    int updateByExampleSelective(@Param("record") Items record, @Param("example") ItemsExample example,@Param("itemId") Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table items
     *
     * @mbggenerated Sat May 25 16:39:52 CST 2019
     */
    int updateByExample(@Param("record") Items record, @Param("example") ItemsExample example);
}