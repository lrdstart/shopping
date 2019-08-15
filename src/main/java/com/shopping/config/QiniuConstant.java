package com.shopping.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "qiniu")
public interface QiniuConstant {
     /*
     七牛云配置
     bucket是创建的存储空间名
     path对应存储空间的访问域名
        qiniu:
        accessKey: Iq8oxSzMlrDKLA__W7j-QQLZPvXvbuWvByN2rwA5
        secretKey: 2mFXoWdMfW8pGFAFCWk2UOOXu-XwzLI_7hq3hxL_
        bucket: cloudimage
        path: http://psib4kbns.bkt.clouddn.com
    */

    String BUCKET = "cloudimage";
    String PATH = "http://lrd.image.cn.qiniudns.com";
    String ACCESSKEY = "Iq8oxSzMlrDKLA__W7j-QQLZPvXvbuWvByN2rwA5";
    String SECRETKEY = "2mFXoWdMfW8pGFAFCWk2UOOXu-XwzLI_7hq3hxL_";
}
