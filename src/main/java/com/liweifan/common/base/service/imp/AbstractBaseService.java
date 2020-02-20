package com.liweifan.common.base.service.imp;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.liweifan.common.base.dao.BaseMapper;
import com.liweifan.common.base.service.BaseService;
import com.liweifan.common.utils.ReflectionUtil;
import com.liweifan.modules.sys.entity.SysRole;

public abstract class AbstractBaseService <T,M extends BaseMapper<T>> implements BaseService<T> {
	@Autowired
	protected M mapper;
	
	@Override
	public T getById(Object id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=false)
	@Override
	public Integer insert(T data) {
		int result=0;
		if(preInsert(data)){
			result = mapper.insert(data);
			afterInsert(data);
		}
		return result;
	}

	@Transactional(readOnly=false)
	@Override
	public Integer update(T data) {
		int result=0;
		if(preUpdate(data)){
			result = mapper.updateByPrimaryKey(data);
			afterUpdate(data);
		}
		return result;
	}

	@Transactional(readOnly=false)
	@Override
	public Integer deleteById(Object id) {
		int result=0;
		if(preDelete(id)){
			result = mapper.deleteByPrimaryKey(id);
			afterDelete(id);
		}
		return result;
	}
	
	@Transactional(readOnly=false)
	@Override
	public Integer delete(T data) {
		int result=0;
		Object key = getKey(data);
		if(preDelete(key)){
			result = deleteById(key);
			afterDelete(key);
		}
		return result;
	}

	@Transactional(readOnly=false)
	@Override
	public Integer insertOrUpdate(T data) {
		Object key=getKey(data);
		if(key!=null&&getById(key)!=null){
			return update(data);
		}else{
			return insert(data);
		}
	}
	@Transactional(readOnly=false)
	@Override
	public Integer insertOrUpdate(T data,String id) {
		Object key=getKey(data);
		if(key!=null && getById(key)!=null){
			return update(data);
		}else{
			setKey(data, id);
			return insert(data);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public Integer updateSelective(T data) {
		int result=0;
		if(preUpdate(data)){
			result = mapper.updateByPrimaryKeySelective(data);
			afterUpdate(data);
		}
		return result;
	}

	@Transactional(readOnly=false)
	@Override
	public Integer insertSelective(T data) {
		int result=0;
		if(preInsert(data)){
			result = mapper.insertSelective(data);
			afterInsert(data);
		}
		return result;
	}

	@Transactional(readOnly=false)
	@Override
	public Integer insertOrUpdateSelective(T data) {
		Object key=getKey(data);
		if(key!=null&&getById(key)!=null){
			return updateSelective(data);
		}else{
			return insertSelective(data);
		}
	}
	
	@Override
	public List<T> getList(Object param) {
		return null;
	}

	@Override
	public boolean preInsert(T data) {
		return false;
	}

	@Override
	public void afterInsert(T data) {
		
	}

	@Override
	public boolean preUpdate(T data) {
		return false;
	}

	@Override
	public void afterUpdate(T data) {
		
	}

	@Override
	public boolean preDelete(Object id) {
		return false;
	}

	@Override
	public void afterDelete(Object id) {
		
	}

	@Override
	public boolean preSelect(T data) {
		return false;
	}

	@Override
	public void afterSelect(T data) {
		
	}
	
	
	@Override
	public boolean isExists(Object key){
		return getById(key)!=null;
	}
	
	@Override
	public Object getKey(T data){
		Object key=null;
		List<Field> idFields = ReflectionUtil.getFieldsByAnnotation(javax.persistence.Id.class, data);
		if(idFields!=null && idFields.size()>0){
			if(idFields.size()==1){
				key=ReflectionUtil.invokeGetter(data, idFields.get(0).getName());
			}else{
				HashMap<String, Object> keyMap = new HashMap<String,Object>();
				for(Field item:idFields){
					keyMap.put(item.getName(), ReflectionUtil.invokeGetter(data, item.getName()));
				}
				key=keyMap;
			}
		}else{
			key=ReflectionUtil.invokeGetter(data, "id");
		}
		return key;
	}
	
	/**
	 *
	 * @param data
	 * @param key
	 * @return
	 */
	@Override
	public Object setKey(T data,Object key){
		List<Field> idFields = ReflectionUtil.getFieldsByAnnotation(javax.persistence.Id.class, data);
		if(idFields!=null&&idFields.size()>0){
			if(idFields.size()==1){
				ReflectionUtil.invokeSetter(data, idFields.get(0).getName(),key);
			}else{
				if(key instanceof Map){
					@SuppressWarnings("unchecked")
					Map<String,Object> keyMap = (Map<String,Object>)key;
					for(Field item:idFields){
						ReflectionUtil.invokeSetter(data, item.getName(),keyMap.get(item.getName()));
					}
				}
			}
		}else{
			ReflectionUtil.invokeSetter(data, "id",key);
		}
		return key;
	}
	public M getMapper(){
		return mapper;
	}
	public void setMapper(M mapper){
		this.mapper=mapper;
	}
}
