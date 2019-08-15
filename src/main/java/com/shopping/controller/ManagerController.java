package com.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.model.ItemCategories;
import com.shopping.model.Items;
import com.shopping.model.Orders;
import com.shopping.model.OrdersDetails;
import com.shopping.service.ItemCategoryService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;
import com.shopping.service.UserService;
import com.shopping.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.util.*;

@Controller
@RequestMapping("manager")
public class ManagerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ItemCategoryService itemCategoryService;
    @Autowired
    private UserService userServie;

    /**
     * 订单列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/orderList")
    public ModelAndView orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "limit", defaultValue = "5") Integer limit,
                                  Map<String, Object> map) {
        PageHelper.startPage(page, limit);
        List<Orders> ordersList = orderService.findAll(page, limit);
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        map.put("pageInfo", pageInfo);
        map.put("ordersList", ordersList);
        map.put("currentPage", page);
        return new ModelAndView("manager/order/list", map);
    }

    @RequestMapping("/orderDetails")
    public ModelAndView orderDetails(@RequestParam(value = "orderId") String orderId,
                                     Map<String, Object> map) {

        try {
            Orders order = orderService.findOne(orderId);
            List<OrdersDetails> list = orderService.findOrderDetailsByOrderId(orderId);

            order.setOrdersDetailsList(orderService.findOrderDetailsByOrderId(orderId));
            map.put("details", order.getOrdersDetailsList());
            map.put("order", order);
        } catch (Exception e) {
            //e.printStackTrace();
            map.put("msg", "查询订单详情出现异常");
            map.put("url", "/manager/orderList");
            return new ModelAndView("manager/common/error", map);
        }
        return new ModelAndView("manager/order/detail", map);
    }


    @RequestMapping("/cancelOrder")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            Orders order = orderService.findOne(orderId);
            if (order != null) {
                int isCancel = orderService.cancel(order);
                if (isCancel == 1) {
                    map.put("msg", "取消成功");
                    map.put("url", "/manager/orderList");
                } else {
                    map.put("msg", "取消失败");
                    map.put("url", "/manager/orderList");
                    return new ModelAndView("manager/common/error", map);
                }
            } else {
                map.put("msg", "订单不存在");
                map.put("url", "/manager/orderList");
                return new ModelAndView("manager/common/error", map);
            }
        } catch (Exception e) {
            map.put("msg", "出现了致命的异常");
            map.put("url", "/manager/orderList");
            return new ModelAndView("manager/common/error", map);
        }
        return new ModelAndView("manager/common/success");
    }

    @RequestMapping("/sendOrder")
    public ModelAndView sendOrder(@RequestParam("orderId") String orderId,
                                  @RequestParam("trackingNumber") String trackingNumber,
                                  Map<String, Object> map) {

        Orders order = orderService.findOne(orderId);
        if (order != null) {
            int result = orderService.send(order, trackingNumber);
            if (result == 1) {
                map.put("msg", "发货成功");
                map.put("url", "/manager/orderList");
                return new ModelAndView("manager/common/success");
            } else {
                map.put("msg", "发货失败");
                map.put("url", "/manager/orderList");
                return new ModelAndView("manager/common/error");
            }
        } else {
            map.put("msg", "订单不存在");
            map.put("url", "/manager/orderList");
            return new ModelAndView("manager/common/error");
        }
    }

    @RequestMapping("/finishOrder")
    public ModelAndView finishOrder(@RequestParam("orderId") String orderId,
                                    Map<String, Object> map) {

        Orders order = orderService.findOne(orderId);
        if (order != null) {
            int result = orderService.finish(order);
            if (result == 1) {
                map.put("msg", "订单已完成");
                map.put("url", "/manager/orderList");
                return new ModelAndView("manager/common/success");
            } else {
                map.put("msg", "订单完成失败");
                map.put("url", "/manager/orderList");
                return new ModelAndView("manager/common/error");
            }
        } else {
            map.put("msg", "订单不存在");
            map.put("url", "/manager/orderList");
            return new ModelAndView("manager/common/error");
        }
    }

    /**
     * 分页查询商品，可以根据类别，商品名称等查询
     * 已完善，不要再改
     *
     * @param page
     * @param categoryId
     * @param itemName
     * @param map
     * @return
     */
    @GetMapping("/itemsList")
    public ModelAndView itemsList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId,
                                  @RequestParam(value = "itemName", defaultValue = "") String itemName,
                                  Map<String, Object> map) {
        PageHelper.startPage(page, 6);
        List<Items> itemsList = productService.findUpAll(page, 6, categoryId, itemName);
        PageInfo<Items> pageInfo = new PageInfo<>(itemsList);
        List<ItemCategories> categoriesList = itemCategoryService.findAll();
        map.put("pageInfo", pageInfo);
        map.put("itemsList", itemsList);
        map.put("categoriesList", categoriesList);
        map.put("currentPage", page);
        map.put("categoryId", categoryId);
        map.put("itemName", itemName);
        return new ModelAndView("manager/product/list", map);
    }

    @RequestMapping(value = "/addItems", method = RequestMethod.POST)
    public ModelAndView addItems(@RequestParam(value = "itemEdition") String itemEdition,
                                 @RequestParam(value = "itemPrice") @NumberFormat Double itemPrice,
                                 @RequestParam(value = "itemAmount") Integer itemAmount,
                                 @RequestParam(value = "itemType") Integer itemType,
                                 @RequestParam(value = "itemDescription") String itemDescription,
                                 @RequestParam(value = "itemName") String itemName,
                                 @RequestParam(value = "itemImgUri") MultipartFile itemImgUri,
                                 Map<String, Object> map) {
        Items item = new Items();
        item.setItemName(itemName);
        item.setItemPrice(itemPrice);
        item.setItemAmount(itemAmount);
        item.setItemDescription(itemDescription);
        item.setItemEdition(itemEdition);
        try {
            item.setItemImgUri(upload(itemImgUri));
        } catch (Exception e) {
            //e.printStackTrace();
            item.setItemImgUri("");
        }
        item.setCategoryId(itemType);
        int count = productService.save(item);
        if (count == 1) {
            map.put("msg", "添加成功");
            map.put("url", "/manager/itemsList");
            return new ModelAndView("manager/common/success", map);
        } else {
            map.put("msg", "添加失败");
            map.put("url", "/manager/itemsList");
            return new ModelAndView("manager/common/error", map);
        }
    }


    /**
     * 携带当前商品id跳转到更新界面
     *
     * @param itemId
     * @param map
     * @return
     */
    @GetMapping("/update")
    public ModelAndView update(@RequestParam(value = "itemId", defaultValue = "0") Integer itemId,
                               Map<String, Object> map) {
        Items item = productService.findOne(itemId);
        List<ItemCategories> categoriesList = itemCategoryService.findAll();
        map.put("categoriesList", categoriesList);
        map.put("item", item);
        return new ModelAndView("manager/product/update", map);
    }

    /**
     * 更新商品信息
     *
     * @param itemId
     * @param itemImgUri
     * @param itemName
     * @param typeCode
     * @param itemAmount
     * @param itemEdition
     * @param itemDescription
     * @param itemPrice
     * @param image
     * @param map
     * @return
     */
    @RequestMapping(value = "/updateItems")
    public ModelAndView updateItems(@RequestParam(value = "itemId") Integer itemId,
                                    @RequestParam(value = "itemImgUri") String itemImgUri,
                                    @RequestParam(value = "itemName") String itemName,
                                    @RequestParam(value = "typeCode") Integer typeCode,
                                    @RequestParam(value = "itemAmount") Integer itemAmount,
                                    @RequestParam(value = "itemEdition") String itemEdition,
                                    @RequestParam(value = "itemDescription") String itemDescription,
                                    @RequestParam(value = "itemPrice") @NumberFormat Double itemPrice,
                                    @RequestParam(value = "image") MultipartFile image,
                                    Map<String, Object> map) throws Exception {
        List<ItemCategories> categoriesList = itemCategoryService.findAll();
        map.put("categoriesList", categoriesList);
        Items itemExample = new Items();
        itemExample.setItemName(itemName);
        itemExample.setItemEdition(itemEdition);
        itemExample.setItemPrice(itemPrice);
        itemExample.setItemAmount(itemAmount);
        itemExample.setItemDescription(itemDescription);
        itemExample.setCategoryId(typeCode);
        System.out.println(image);
        if (!image.isEmpty()) {
            String imageUrl = upload(image);
            itemExample.setItemImgUri(imageUrl);
        } else {
            itemExample.setItemImgUri(itemImgUri);
        }

        System.out.println(itemExample);

        int count = productService.updateOne(itemExample, itemId);
        if (count == 1) {
            map.put("msg", "修改成功");
        } else {
            map.put("msg", "修改失败");
            map.put("url", "/manager/update?itemId=" + itemId);
            return new ModelAndView("manager/common/error", map);
        }
        //修改之后的信息，显示在界面上
        Items item = productService.findOne(itemId);
        map.put("item", item);
        return new ModelAndView("manager/product/update", map);
    }

    /**
     *      * 上传文件到七牛云
     *      * @throws Exception 
     *      
     */
    public String upload(@RequestParam("image") MultipartFile image) throws Exception {

        FileInputStream inputStream = (FileInputStream) image.getInputStream();
        String path = QiniuUtil.uploadImg(inputStream, UUID.randomUUID().toString().substring(0, 8));
        return path;
    }

    /**
     * 商品详情页
     * 无需修改
     *
     * @param itemId
     * @param map
     * @return
     */
    @GetMapping("/itemDetails")
    public ModelAndView itemDetails(@RequestParam(value = "itemId", defaultValue = "0") Integer itemId,
                                    Map<String, Object> map) {
        Items item = productService.findOne(itemId);
        ItemCategories categories = itemCategoryService.findOne(item.getCategoryId());
        map.put("cateName", categories.getCategoryName());
        map.put("items", item);
        return new ModelAndView("manager/product/details", map);
    }

    /**
     * 根据商品id删除单个商品
     * 已完善，无需再改
     *
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/deleteItem")
    @ResponseBody
    public Map<String, Object> deleteItem(@RequestParam(value = "itemId", defaultValue = "0") Integer itemId) {

        return deleteById(productService.deleteItemById(itemId));
    }


    //类目增删改查已经完善

    @GetMapping("/categoryList")
    public ModelAndView categoryList(Map<String, Object> map) {

        List<ItemCategories> categoriesList = itemCategoryService.findAll();
        map.put("categoriesList", categoriesList);
        return new ModelAndView("manager/category/list", map);
    }

    @GetMapping("/categoryUpdate")
    public ModelAndView categoryUpdate(@RequestParam(value = "categoryId") Integer categoryId,
                                       Map<String, Object> map) {
        ItemCategories category = itemCategoryService.findOne(categoryId);
        map.put("category", category);
        return new ModelAndView("manager/category/update", map);
    }

    @PostMapping("/updateCategory")
    public ModelAndView updateCategory(@RequestParam(value = "categoryId") Integer categoryId,
                                       @RequestParam(value = "categoryName") String categoryName,
                                       Map<String, Object> map) {

        int result = itemCategoryService.updatebyId(categoryId, categoryName);
        if (result == 1) {
            map.put("msg", "修改成功");
            map.put("url", "/manager/categoryList");
            return new ModelAndView("manager/common/success", map);
        } else {
            map.put("msg", "修改失败");
            map.put("url", "/manager/categoryList");
            return new ModelAndView("manager/common/error", map);

        }
    }

    @RequestMapping(value = "/addCategory")
    public String addCategory() {
        return "manager/category/index";
    }

    @PostMapping("/categorySave")
    public ModelAndView categorySave(@RequestParam(value = "categoryId") Integer categoryId,
                                     @RequestParam(value = "categoryName") String categoryName,
                                     Map<String, Object> map) {

        ItemCategories category = new ItemCategories();
        category.setCategoryId(categoryId);
        category.setCategoryName(categoryName);
        int result = itemCategoryService.save(category);
        if (result == 1) {
            map.put("msg", "增加成功");
            map.put("url", "/manager/categoryList");
            return new ModelAndView("manager/common/success", map);
        } else {
            map.put("msg", "增加失败");
            map.put("url", "/manager/categoryList");
            return new ModelAndView("manager/common/error", map);
        }
    }

    @RequestMapping(value = "/deleteItemCategory")
    @ResponseBody
    public Map<String, Object> deleteItemCategory(@RequestParam(value = "categoryId") Integer categoryId) {

        return deleteById(itemCategoryService.deleteItemById(categoryId));
    }

    private Map<String, Object> deleteById(int i) {
        HashMap<String, Object> return_hashmap = new HashMap<String, Object>();

        int count = i;
        if (count == 1) {
            return_hashmap.put("msg", "删除成功");
        } else {
            return_hashmap.put("msg", "删除失败");
        }
        return return_hashmap;
    }

    /**
     * 登出操作
     *
     * @param request
     * @return
     */

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        //Users user = (Users) request.getSession().getAttribute("manager");
        //System.out.println(user);
        request.getSession().removeAttribute("manager");
        return "redirect:/";
    }


    @RequestMapping("/changePassword")
    public String changePassword() {
        return "manager/edit/index1";
    }

    /**
     * 修改密码，要求输入原密码和新密码以及再次确认输入
     *
     * @param username
     * @param password
     * @param newPassword
     * @param map
     * @return
     */
    @RequestMapping("/editPassword")
    public ModelAndView editPassword(@RequestParam(value = "username") String username,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "newPassword") String newPassword,
                                     Map<String, Object> map) {
        int result = userServie.changePassword(username, password, newPassword);
        if (result == 1) {
            map.put("msg", "修改成功,请重新登陆");
            map.put("url", "/user/login");
            return new ModelAndView("manager/common/success", map);
        } else {
            map.put("msg", "修改失败");
            map.put("url", "/manager/changePassword");
            return new ModelAndView("manager/common/error", map);
        }

    }
}
