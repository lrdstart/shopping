package com.shopping.config;

/**
 * @author LRD
 * @create 2019-06-19 9:42
 */
public class AlipayConfig {
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016092900626875";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCEjMTjjZzkPPAl75sGAFJvyfqj3JvGvwMG2p+3cYDYzZDjcJ3PDfgCPOI+Ckmi/V+23fomqdgF2vkQgG6Tvoj068sVFzXSpMcFdxlffw5Gf9Qq1Yq3Cb4BIK+4IJYnuLsS+w8dTnk1REJBpzV+rcQZTgVzbWc96N1e7ug8wUfnoAoMLULHJP4Al5PcNdZpSYYVpev6amCXTsebrpkchu9jRlA2d235glBnLHzoMAwDCnI8PIKc4AIg1zC9QuMnNIlBUI+E6u3BUyg69RrwTz3HbqnL1dFirsjjZT6uT+2DJmeJ1f9zXrfBaH4CxzbBgvPt6sBb6/3Jea+i06RvsC57AgMBAAECggEAfpNKhR47Pzzt+twHRsDR+AyBuy601yKailYFhRDvgJk1DewB1rK5LbHkPmhmjy2z1BW/hmJUdqr3gomSjqW5dWEhyl8it6dAonhsE2RqRmpVOh43W7kC52LGSmdb3Eys4GA8u7ivSG2Jc9Zv4CuhgqSd0e8cUuNnWft8bGAKASXT8Vj2iQ53TR2hjGP4X8u8NqdqRD8u3p3HKj5xTpNElMOwdl0Ape0Df6uG9GMPRFEsw++MdI9QGY+VjP5wsw8fMu7+26w4SRhp38RwzyZ8ifTnYbbacFiaoQdsT7PBLKPEe60LJ23uv9KmJRBPdQrm78vJltYLtHNrOHZfko4YkQKBgQC7uYDeGSgC34AIgZs4oIEF27zmgYtp2dDicICCfJH/N5iNGO19n2XAKUeJ3IL4igvZXMzh8hbciio9KBfOQS0JqGcBmZa2rt7DpMApNaoYVDi6HEt0VwepxKNzdSS40AleXqqdsQuDwP2VWWi/rvu8/1Li0VNSx71axNlqerR7OQKBgQC0whreIv6rPgAS9MZ9F+xH8lOquWsT25ErvCa/ZcIHyQu/AhwTCUVkzR4FxLu5zkwjO1EtAo3MVTlFEH6ui+HQp+EAeQ32wTq/rtW3sqevB8c+fwNblI06docx79yHRCL1XUfl3bo0/Qucay6tTsn5BLZJDimsHQC9SqYtZz8TUwKBgQCB6wQQsYBLXLqTKH9wVUR19XlqXcVKYIB79jg6FlREOQnOvK2//wzATNFBy+aoc4F30Q3KYW0I/GX3JUQ0bk7pHC8NoGn5zFRHOkHzQzyQ0djglKxzu7eF+pzUTwI3FWMmeIXD1L+PU4UsU1B3OHboehu29f+gjrOxmgKN+pXloQKBgCxz09GvP3vzamc/BRU8beIfqQ3CPaALjj74LXtCVh4+BgJ6TVctH4CzpN98S0JdhoHfk44DI8YNa2EdtlnMksMSlhXcr19wk7cSEwLzD6POwOQQrk22Uaj//y17tHsbeZFn2EeiaKmhVVqpTThXuPnhe8apkfv+wfDLS+S8kFl7AoGAWoadjPBgOTfarWOUeJf5oorOJqVLK/5j7Qw/D+9Wu53WLLvFRtkpMIwNhwj50OpWxJhafBPPym9/6A2grrHnXZS0C7WFYrXv0cTvZrknwPv7q+TegKmB5Z1hZoVinU2EqYFeU+sg5EII3tXYrZF8Zi9MvjJleCRW6BaWjzw8Lxg=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhIzE442c5DzwJe+bBgBSb8n6o9ybxr8DBtqft3GA2M2Q43Cdzw34AjziPgpJov1ftt36JqnYBdr5EIBuk76I9OvLFRc10qTHBXcZX38ORn/UKtWKtwm+ASCvuCCWJ7i7EvsPHU55NURCQac1fq3EGU4Fc21nPejdXu7oPMFH56AKDC1CxyT+AJeT3DXWaUmGFaXr+mpgl07Hm66ZHIbvY0ZQNndt+YJQZyx86DAMAwpyPDyCnOACINcwvULjJzSJQVCPhOrtwVMoOvUa8E89x26py9XRYq7I42U+rk/tgyZnidX/c163wWh+Asc2wYLz7erAW+v9yXmvotOkb7AuewIDAQAB";

    // 签名方式
    public static String SIGN_TYPE = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    public static String FORMAT = "json";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

}
