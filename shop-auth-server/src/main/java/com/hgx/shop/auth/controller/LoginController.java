package com.hgx.shop.auth.controller;

import com.alibaba.fastjson.TypeReference;
import com.hgx.common.constant.AuthServerConstant;
import com.hgx.common.exception.BizCodeEnume;
import com.hgx.common.utils.R;
import com.hgx.shop.auth.feign.MemberFeignService;
import com.hgx.shop.auth.feign.ThirdPartFeignService;
import com.hgx.shop.auth.vo.UserLoginVo;
import com.hgx.shop.auth.vo.UserRegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hgx
 * @date 2020/12/14 21:39
 * @Description
 */

@Controller
public class LoginController {

    @Autowired
    ThirdPartFeignService thirdPartFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    MemberFeignService memberFeignService;

    /**
     * 发送一个请求直接跳转到一个页面
     * SpringMVC viewcontroller 将请求和页面映射过来.
     */
    @ResponseBody
    @GetMapping("/sms/sendcode")
    public R sendCode(@RequestParam("phone") String phone) {

        //TODO 1、接口防刷

        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 60000) {
                //60秒内不能再发
                return R.error(BizCodeEnume.SMS_CODE_EXCEPTION.getCode(), BizCodeEnume.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        //2、验证码的再次校验。redis
        // key = sms:code:手机号
        // value = 6位验证码_验证码创建时间的毫秒数

        String code = UUID.randomUUID().toString().substring(0, 5);
        String redisKey = AuthServerConstant.SMS_CACHE_PREFIX + phone;
        String redisValue = code + "_" + System.currentTimeMillis();

        //redis缓存验证码，反之60秒内继续发送验证码
        redisTemplate.opsForValue().set(redisKey, redisValue, 10, TimeUnit.MINUTES);
        thirdPartFeignService.sendCode(phone, code);
        return R.ok();
    }

    /**
     * RedirectAttributes redirectAttributes：模拟重定向数据
     *
     * @param vo
     * @param result
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/regist")
    public String regist(@Valid UserRegistVo vo, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,
                    FieldError::getDefaultMessage));
            //model.addAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("errors", errors);
            //校验出错，转发到注册页
            return "redirect:http://auth.shop.com/reg.html";
        }

        //真正注册
        //1.校验验证码
        String code = vo.getCode();
        String s = redisTemplate.opsForValue().get(AuthServerConstant.SMS_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(s)) {
            if (code.equals(s.split("_")[0])) {
                //删除验证码，令牌机制
                redisTemplate.delete(AuthServerConstant.SMS_CACHE_PREFIX + vo.getPhone());
                //验证码通过 //真正注册。调用远程服务进行注册
                R r = memberFeignService.regist(vo);
                if (r.getCode() == 0) {
                    //成功

                    return "redirect:/login.html";
                } else {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("msg", r.getData("msg", new TypeReference<String>() {
                    }));
                    redirectAttributes.addFlashAttribute("errors", errors);
                    return "redirect:http://auth.shop.com/reg.html";
                }
            } else {
                Map<String, String> errors = new HashMap<>();
                errors.put("code", "验证码错误");
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.shop.com/reg.html";
            }
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("code", "验证码错误");
            redirectAttributes.addFlashAttribute("errors", errors);
            //校验出错，转发到注册页
            return "redirect:http://auth.shop.com/reg.html";
        }
    }

    @PostMapping("/login")
    public String login(UserLoginVo vo, RedirectAttributes redirectAttributes) {
        //远程登录
        R r = memberFeignService.login(vo);
        if (r.getCode() == 0) {
            //成功
            return "redirect:http://shop.com";
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("msg", r.getData("msg", new TypeReference<String>() {}));
            redirectAttributes.addFlashAttribute("errors", "");
            return "redirect:http://auth.shop.com/login.html";
        }
    }

}
