package com.hgx.shop.product.exception;

import com.hgx.common.exception.BizCodeEnume;
import com.hgx.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * @author hgx
 * @date 2020/10/16 22:50
 * @Description 集中处理全局异常
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.hgx.shop.product.controller")//指定处理的范围
public class ProductExceptionControllerAdvice {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)//指定此方法能处理哪类  异常
    public R handleVaildException(MethodArgumentNotValidException e) {
        log.error("数据校验出问题：{}，异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();
        bindingResult.getFieldErrors().forEach((fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }));
        return R.error(BizCodeEnume.VAILD_EXCEPTION.getCode(), BizCodeEnume.VAILD_EXCEPTION.getMsg()).put("data", errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        return R.error(BizCodeEnume.UNKNOW_EXCEPTION.getCode(),BizCodeEnume.UNKNOW_EXCEPTION.getMsg());
    }
}
