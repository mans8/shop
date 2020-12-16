package com.hgx.shop.auth.feign;

import com.hgx.common.utils.R;
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

}
