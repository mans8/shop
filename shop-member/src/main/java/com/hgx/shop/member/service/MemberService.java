package com.hgx.shop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.member.entity.MemberEntity;
import com.hgx.shop.member.exception.PhoneExistException;
import com.hgx.shop.member.exception.UsernameExistException;
import com.hgx.shop.member.vo.MemberLoginVo;
import com.hgx.shop.member.vo.MemberRegistVo;
import com.hgx.shop.member.vo.SocialUser;

import java.util.Map;

/**
 * 会员
 *
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 15:57:07
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void regist(MemberRegistVo vo);

    void checkPhoneUnique(String email) throws PhoneExistException;

    void checkUserNameUnique(String username) throws UsernameExistException;

    MemberEntity login(MemberLoginVo vo);

    MemberEntity login(SocialUser socialUser) throws Exception;
}

