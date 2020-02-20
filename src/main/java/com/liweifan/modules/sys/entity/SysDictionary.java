package com.liweifan.modules.sys.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_dictionary")
public class SysDictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private  String   id;			//主键
	private  String   pid;			//上级id
	private  Integer   nodeType;	//节点类型：1.非末端节点 2、末端节点
	private  String   code;			//字典代码
	private  String   name;			//字典名字
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getNodeType() {
		return nodeType;
	}
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
