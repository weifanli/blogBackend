package com.liweifan.modules.importData.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 入库
 * @author weifanl
 * @date:  2019年11月23日 上午8:42:12
 */
@Table(name="ruku_original")
public class RukuOriginal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private  Integer   id;			//
	private  String   spdm;			//商品代码
	private  String   spmc;			//商品名称
	private  String   spgg;			//商品规格
	private  String   dw;			//单位
	private  Double   dj;			//单价(含税)
	private  Integer   sl;			//数量
	private  Double   je;			//金额(含税)
	private  Date   qcsj;			//期初时间
	private  Date   qmsj;			//期末时间
	private String wjlj;			//文件路径
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpdm() {
		return spdm;
	}
	public void setSpdm(String spdm) {
		this.spdm = spdm;
	}
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	public String getSpgg() {
		return spgg;
	}
	public void setSpgg(String spgg) {
		this.spgg = spgg;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	public Double getDj() {
		return dj;
	}
	public void setDj(Double dj) {
		this.dj = dj;
	}
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public Date getQcsj() {
		return qcsj;
	}
	public void setQcsj(Date qcsj) {
		this.qcsj = qcsj;
	}
	public Date getQmsj() {
		return qmsj;
	}
	public void setQmsj(Date qmsj) {
		this.qmsj = qmsj;
	}
	public String getWjlj() {
		return wjlj;
	}
	public void setWjlj(String wjlj) {
		this.wjlj = wjlj;
	}
	
}
