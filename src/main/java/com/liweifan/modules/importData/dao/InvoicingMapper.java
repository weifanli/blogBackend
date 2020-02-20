package com.liweifan.modules.importData.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.importData.entity.Invoicing;
@Mapper
public interface InvoicingMapper extends BaseMapper<Invoicing>{
	
	public List<Invoicing> selectL(@Param(value="start") Integer start,@Param(value="end") Integer end);
}
