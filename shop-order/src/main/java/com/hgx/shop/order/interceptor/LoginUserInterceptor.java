package com.hgx.shop.order.interceptor;

import com.hgx.common.constant.AuthServerConstant;
import com.hgx.common.vo.MemberRespVo;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hgx
 * @date 2021/2/10 10:34
 * @Description
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberRespVo> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MemberRespVo attribute = (MemberRespVo) request.getSession().getAttribute(AuthServerConstant.LOGIN_USER);

        String uri = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/order/order/status/**", uri);
        //匹配支付请求（使用了内网穿透）
        boolean match1 = antPathMatcher.match("/payed/notify", uri);
        if (match || match1) {
            return true;
        }

        if (attribute != null) {
            loginUser.set(attribute);
            return true;
        } else {
            //没登录就去登录
            request.getSession().setAttribute("msg", "请先登录");
            response.sendRedirect("http://auth.shop.com/login.html");
            return false;
        }

    }

}
