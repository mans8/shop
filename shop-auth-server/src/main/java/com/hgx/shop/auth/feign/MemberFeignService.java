package com.hgx.shop.auth.feign;

import com.hgx.common.utils.R;
import com.hgx.shop.auth.vo.SocialUser;
import com.hgx.shop.auth.vo.UserLoginVo;
import com.hgx.shop.auth.vo.UserRegistVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hgx
 * @date 2020/12/16 22:53
 * @Description
 */
@FeignClient("shop-member")
public interface MemberFeignService {

    @PostMapping("/member/member/regist")
    R regist(@RequestBody UserRegistVo vo);

    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVo vo);

    @PostMapping("/member/member/oauth2/login")
    R oauthlogin(@RequestBody SocialUser socialUser) throws Exception;

}
