package com.liweifan.modules.importData.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 出库
 * @author weifanl
 * @date:  2019年11月23日 上午8:42:12
 */
@Table(name="chuku_original")
public class ChukuOriginal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private  Integer   pid;			//
	private  String   spmc;			//商品名称
	private  String   spgg;			//商品规格
	private  String   dw;			//单位
	private  Integer   sl;			//数量
	private  Double   dj;			//单价
	private  Double   je;			//金额
	private  String   bz;			//备注
	private  Date   qcsj;			//期初时间
	private  Date   qmsj;			//期末时间
	
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public Integer getSl() {
		return sl;
	}
	public void setSl(Integer sl) {
		this.sl = sl;
	}
	public Double getDj() {
		return dj;
	}
	public void setDj(Double dj) {
		this.dj = dj;
	}
	public Double getJe() {
		return je;
	}
	public void setJe(Double je) {
		this.je = je;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
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
}
