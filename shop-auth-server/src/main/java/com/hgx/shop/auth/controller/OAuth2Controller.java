package com.hgx.shop.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.hgx.common.utils.HttpUtils;
import com.hgx.common.utils.R;
import com.hgx.shop.auth.feign.MemberFeignService;
import com.hgx.common.vo.MemberRespVo;
import com.hgx.shop.auth.vo.SocialUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.hgx.common.constant.AuthServerConstant.LOGIN_USER;

/**
 * @author hgx
 * @date 2020/12/17 23:54
 * @Description 处理社交登录请求
 */
@Slf4j
@Controller
public class OAuth2Controller {

    @Autowired
    MemberFeignService memberFeignService;

    /**
     * 社交登录成功回调
     *
     * @param code
     * @return
     * @throws Exception 参考资料：https://open.weibo.com/wiki/%E6%8E%88%E6%9D%83%E6%9C%BA%E5%88%B6
     */
    @GetMapping("/oauth2.0/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse,
                        HttpServletRequest request) throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("client_id", "2426792128");
        map.put("client_secret", "ae533bafc7b0e541b61287d93d03f6ba");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://auth.shop.com/oauth2.0/weibo/success");
        map.put("code", code);
        //1.根据code换取accessToken
        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", new HashMap<>(), map,
                new HashMap<>());

        //2.处理
        if (response.getStatusLine().getStatusCode() == 200) {
            //获取到accessToken
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //1.当前用户如果是第一次进网站，自动注册进来（为当前社交用户生成一个会员账户信息，以后这个社交账号就对应指定的会员）
            //登录或者注册社交用户
            R oauthLogin = memberFeignService.oauthlogin(socialUser);
            if (oauthLogin.getCode() == 0) {
                MemberRespVo data = oauthLogin.getData("data", new TypeReference<MemberRespVo>() {
                });
                log.info("登录成功：用户：{}", data.toString());

                //1、第一次使用session，命令浏览器保存卡号，JSESSIONID这个cookie
                //以后浏览器访问哪个网站就会带上这个网站的cookie
                //TODO 1、默认发的令牌。当前域（解决子域session共享问题）
                //TODO 2、使用JSON的序列化方式来序列化对象到Redis中
                session.setAttribute(LOGIN_USER, data);
                //servletResponse.addHeader();

                //2.登录成功就跳回首页
                return "redirect:http://shop.com";
            } else {
                return "redirect:http://auth.shop.com/login.html";
            }
        } else {
            return "redirect:http://auth.shop.com/login.html";
        }
    }

}
