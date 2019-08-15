package com.shopping.controller;

import com.shopping.model.Orders;
import com.shopping.model.Users;
import com.shopping.service.UserService;
import com.shopping.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("login")
    public String login() {
        return "user/login";
    }

    @RequestMapping("forget_password")
    public String forgetPassword() {
        return "user/forget_password";
    }

    /**
     * 找回密码功能
     *
     * @param json
     * @return
     */
    @RequestMapping(value = "getBackPassword", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getBackPassword(@RequestBody HashMap<String, Object> json) {
        String username = (String) json.get("username");
        String email = (String) json.get("email");
        HashMap<String, Object> return_hashmap = new HashMap<String, Object>();
        Users user = userService.findByName(username);
        if (user == null) {
            return_hashmap.put("msg","用户名不存在");
        } else {
            if (user.getEmail().equals(email)) {
                userService.getBackPassword(username, email);
                return_hashmap.put("msg", "密码已发送至您的邮箱,请注意查收");
            } else {
                return_hashmap.put("msg", "您的邮箱不正确");
            }
        }
        return return_hashmap;
    }


    /**
     * 接受登陆参数
     */
    @RequestMapping(value = "loginUser", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getLoginUesr(@RequestBody HashMap<String, Object> json, HttpServletRequest request) {
        String username = (String) json.get("username");
        String password = (String) json.get("password");
        String checkCode = (String) json.get("checkCode");
        String random = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
        HashMap<String, Object> return_hashmap = new HashMap<String, Object>();
        if (random.equalsIgnoreCase(checkCode)) {
            try {
                //查找用户，验证用户密码
                Users user = userService.findOne(username, password);
                if (user != null) {
                    if (user.getStatus() == 0) {
                        return_hashmap.put("msg", "您的账户尚未激活,请激活后再来登陆");
                    } else {
                        //管理员
                        if (user.getGrade() == 1) {
                            request.getSession().setAttribute("manager", user);
                            return_hashmap.put("msg", 1);
                        } else {
                            request.getSession().setAttribute("loginUser", user);
                            return_hashmap.put("msg", 2);
                        }
                    }
                } else {
                    return_hashmap.put("msg", "用户名或密码错误");
                }
            } catch (Exception e) {
                System.out.println(e);
                return_hashmap.put("msg", "登陆失败");
            }
        } else {
            return_hashmap.put("msg", "验证码错误");
        }
        return return_hashmap;
    }

    @RequestMapping("register")
    public String register() {
        return "user/register";
    }

    /**
     * 接受注册参数
     */
    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> getRegUser(@RequestBody HashMap<String, Object> json, HttpServletRequest request) {
        String random = (String) request.getSession().getAttribute("RANDOMVALIDATECODEKEY");
        String username = (String) json.get("username");
        String password = (String) json.get("password");
        String email = (String) json.get("email");
        String checkCode = (String) json.get("checkCode");
        HashMap<String, Object> return_hashmap = new HashMap<String, Object>();
        if (random.equalsIgnoreCase(checkCode)) {
            int i = userService.registerUser(username, password, email);
            System.out.println(i);
            if (i == 1) {
                return_hashmap.put("result", 1);
            }
            if (i == 3) {
                return_hashmap.put("result", "用户名已存在,请换一个再试试");
            }
            if (i == 2) {
                return_hashmap.put("result", "邮箱已经存在,请更换邮箱");
            }
        } else {
            return_hashmap.put("result", "验证码不正确");
        }
        return return_hashmap;
    }

    /**
     * 激活账户
     *
     * @param username
     * @param password
     * @param map
     * @return
     */
    @GetMapping(value = "active")
    public ModelAndView active(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Map<String, Object> map) {
        if (password != null) {
            boolean flag = userService.active(username, password);
            //3.判断标记响应信息
            String msg = null;
            if (flag) {
                //激活成功
                map.put("msg", "激活成功请登录");
                map.put("url", "/user/login");
            } else {
                //激活失败
                map.put("msg", "激活失败，请联系管理员或稍后重试");
                map.put("url", "/user/login");
                return new ModelAndView("manager/common/error", map);
            }
        }

        return new ModelAndView("manager/common/success");
    }

    @RequestMapping("registerok")
    public String registerok() {
        return "user/register_ok";
    }

    @RequestMapping("liebiao")
    public String liebiao() {
        return "user/liebiao";
    }

    @RequestMapping("details")
    public String details() {
        return "user/details";
    }

    @RequestMapping("orderist")
    public String orderlist() {
        return "user/order_list";
    }

    @RequestMapping("selfInfo")
    public String selfinfo() {
        return "user/self_info";
    }

    @RequestMapping("cart")
    public String cart() {
        return "user/cart";
    }


    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        //Users user = (Users) request.getSession().getAttribute("loginUser");
        //System.out.println(user);
        request.getSession().removeAttribute("loginUser");
        return "redirect:/";
    }

    /**
     * 生成验证码
     */
    @RequestMapping("getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            //输出验证码图片方法
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
