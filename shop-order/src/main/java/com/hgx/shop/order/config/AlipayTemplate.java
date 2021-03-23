package com.hgx.shop.order.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.hgx.shop.order.vo.PayVo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "alipay")
@Component
@Data
public class AlipayTemplate {

    //在支付宝创建的应用的id
    public static String app_id = "2021000117625330";

    // 商户私钥，您的PKCS8格式RSA2私钥
    private String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWei9i4dVd13hi+lih078kbV6fsgok55OehnEHnEcgMqXi5pR/QkYSPVNL3f2kLzmm59BkLyS5Wu3qiSuFJzQ4fkY3ft1G0QHyEhYt4BXvQXxMa+7hgX2ijFQqRpVHcYsCOhPx76izueDPkyhrEOymBdyhXxKLBxrKoeF5R16qFrCkRUq+LeNcOMrVnM/s3SRS6KinNNzBNWx+J+Wvi8+JEMjwH2srlpWbKTvaq2zCjPQv2NZzr2aWKvznDw3VNPWY114mQkpGarPwnTVCOVyDa6fNg/JJvmCotIXndc1vmiL4iFyLGqOX554Y2AznQOWxqmnA7hMOeQ5riKfwMm/jAgMBAAECggEASqByG8IQRSf1JnEtv6P56aDsCpsLeN34Uegu6i1RAODdcDgWcT527djIFJoVjnV1TBcvPGA9AEfeMjCxCqo0tce6Hw+lwdcp9dmOclXtyuhZMrVclS3ZFuItY4H5yqK+4+3Vsmi+69NFcOx0YRTVqEjD8Kvr0FNHsDK0VadVhVhTYqj3T0QNHWmI6uaX1J0bg0/kzo12PM4k+Zer+PmqjtMPYZSbPFF4R8OfZf8PQnWI/7yJvaoDNFn7MXL2limO1QTbX0c347+o2oMglmV/z/BzB1ra4vKAn/FJ1GILeBSCUCpzAuaY9ZszFnxuKSbmWthfTXaqa1UxnRQ9rH1zIQKBgQDcqFIpWnhnu/ss+ihrvi1Tcjt/iT37y96K9eDCR6VgZtFwO6Sv7C4qkfeWxJgqTecmozHTEVbMX6u4BliifCHbVqxAINpAzD7KiwjO/8FnPCbZsHThbghzGLWwFb9IwUficgbcjeDQoveIAEyXA7K3QH+TgnkC9cOXPAacgYhIuQKBgQCulD7q8hqtKD7z+YvnlVdEnFhYn8wmlX7xOWn0oWamhFv/1oV//mv1MdZM76Z96SpaFim0BQMi3cB5SsaEu5ODHu6DS5i4T5BEvxJp7w5xlrlmehSb5QqKQLpHz0GqI+qsfYVNia59r1MaekeHbNVXTpmMBIjBQN2w4t8TQXX3ewKBgDcbNkGrnWxSrvzhkJyvxwQTADbuoHM0y+QqWbJLUErOArojuxJQSotJHushN9tAz1x/3x6WMvYhYnI364TQuWfcsnHlgnwyRfAxXsbLapKMnCs7fUUbRLLx1dYe6EGUO46a6YnFSQLx4B2v7XC9dLF9Qx8ks3IAzJMFY6VMWCvJAoGBAI2lbp9GLGrOOrXNhbwyqYnbqLrPfN7ZcRPb16JKynTU86hSsCK3lXRuKUhnMZj7dAd/7/pGDtNdROukxgmv//HlSPRyg8kXlSCSPyiAdCGpf2gsKUiLv8+I0Ruuh8K6PZASj/PngBIuDKlxCVRR849RELhQfXz/WmBI02JWPJa9AoGAccJjzbRMSvIcXoeQLDMvIQLNF8UO7Isl3DcPiVdIHZF06O/d+L0ah8Fee/T4AQs+tChwVB1uXd71rkV6hnZ9Ak4FGMzeSyn2m8dBA4uFJWcxMi8//7QpK2gORprT970cV5Bp3WnKoQ3L7Hvi5tehEyyqxMmtKUgqLLSkKn3KHsA=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmvTDK4WFmXpSnSGD5m8pIS4QlyDv7LHhQR97e7P3ceubKqnBzPBAzYK7mTexfWDWRT9f6ByxAjsiRuov55pRxo7pKiaKArMCbJyS7lNZeaa7QGXhkIudp1WKUXho6FvWL8MzME++47Ml6kEPGnv2Jtkkk5eK2Kp2Pl/H7zatvvq7btTQSMTYvr5c9Au63eWhaCKD51lkB99Yp7itPBhd6/HJXtGxVmoIeD+9Swm8ImGp7kA3Cnty9AvILL5Mi4vCaYdL5Tz6n74OuhdrdJuAHud7yO79VftoSsDWOBBlveH99SXej1oy0gRA8W3NRbOCE55LoQeM20MxbOuvzAqGEwIDAQAB";
    // 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
    private String notify_url = "http://ngb7nd04q2.52http.net/payed/notify";

    //页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //同步通知，支付成功，一般跳转到成功页
    private String return_url = "http://member.shop.com/memberOrder.html";

    // 签名方式
    private String sign_type = "RSA2";

    // 字符编码格式
    private String charset = "utf-8";

    private String timeout = "30m";

    // 支付宝网关； https://openapi.alipaydev.com/gateway.do
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public String pay(PayVo vo) throws AlipayApiException {

        //AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        //1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        //2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = vo.getOut_trade_no();
        //付款金额，必填
        String total_amount = vo.getTotal_amount();
        //订单名称，必填
        String subject = vo.getSubject();
        //商品描述，可空
        String body = vo.getBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + timeout + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
        System.out.println("支付宝的响应：" + result);

        return result;

    }
}
