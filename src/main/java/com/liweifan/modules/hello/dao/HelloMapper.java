package com.liweifan.modules.hello.dao;

import org.apache.ibatis.annotations.Mapper;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.hello.entity.Company;
@Mapper
public interface HelloMapper extends BaseMapper<Company>{
	public Company getCompany();
}
