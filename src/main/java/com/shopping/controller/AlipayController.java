package com.shopping.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.shopping.config.AlipayConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import static com.alipay.api.AlipayConstants.*;

/**
 * @author LRD
 * @create 2019-06-19 9:30
 */
@Controller
@RequestMapping("payTest")
public class AlipayController {

    @RequestMapping("dopay")
    public void dopay(HttpServletResponse response){
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //支付完成以后跳转项目某一个页面
        alipayRequest.setReturnUrl("http://domain.com/CallBack/return_url.jsp");
        //在公共参数中设置回跳和通知地址
        alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp");

        JSONObject json = new JSONObject();
        json.put("out_trade_no", UUID.randomUUID().toString());
        json.put("product_code","FAST_INSTANT_TRADE_PAY");
        json.put("total_amount",0.1);
        json.put("subject","购买一个手机");
        //填充业务参数
        alipayRequest.setBizContent(json.toString());
        String form="";
        try {
            //调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        //直接将完整的表单html输出到页面
        try {
            response.getWriter().write(form);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
