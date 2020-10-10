package com.hgx.shop.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hgx.common.utils.PageUtils;
import com.hgx.shop.member.entity.MemberEntity;

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
}

