package com.liweifan.common.base.service;

import java.util.List;

public interface BaseService<T> {
	public T getById(Object id);
	public Integer insert(T data);
	public Integer update(T data);
	public Integer deleteById(Object id);
	public Integer delete(T data);
	public Integer insertOrUpdate(T data);
	public Integer insertOrUpdate(T data,String id);
	
	public Integer updateSelective(T data);
	public Integer insertSelective(T data);
	public Integer insertOrUpdateSelective(T data);
	
	public boolean isExists(Object key);
	public Object getKey(T data);
	public Object setKey(T data,Object key);
	public List<T> getList(Object param);
	
	public boolean preInsert(T data);
	public void afterInsert(T data);
	
	public boolean preUpdate(T data);
	public void afterUpdate(T data);
	
	public boolean preDelete(Object id);
	public void afterDelete(Object id);
	
	public boolean preSelect(T data);
	public void afterSelect(T data);
}
