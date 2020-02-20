package com.liweifan.modules.ImportZbms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.modules.ImportZbms.entity.PzBdfl;
import com.liweifan.modules.ImportZbms.entity.PzBdzb;

@Mapper
public interface PzBdzbMapper extends BaseMapper<PzBdzb>{
	
	public List<PzBdzb> selectBdzbList();
	
	public List<Map<String,String>> selectZBK();
	
	public List<PzBdfl> selectBdflList();
	
	public void updateZb(@Param("jsgssm") String jsgssm,@Param("id") String id);
	
}
