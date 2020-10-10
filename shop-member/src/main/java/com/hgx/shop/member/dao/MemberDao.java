package com.hgx.shop.member.dao;

import com.hgx.shop.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author hgx
 * @email man133@126.com
 * @date 2020-10-10 15:57:07
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
