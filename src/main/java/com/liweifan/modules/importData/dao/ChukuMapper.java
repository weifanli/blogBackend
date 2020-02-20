package com.liweifan.modules.importData.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.importData.entity.ChukuOriginal;
@Mapper
public interface ChukuMapper extends BaseMapper<ChukuOriginal>{

	void insertTime(@Param(value="qcsj") String qcsj, @Param(value="qmsj")String qmsj);
	
}
