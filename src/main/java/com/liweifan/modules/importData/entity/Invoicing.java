package com.liweifan.modules.importData.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="invoicing")
public class Invoicing implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private  Integer   pid;			//主键
	private  Date   qcsj;			//期初时间
	private  Date   qmsj;			//期末时间
	private  String   spmc;			//商品名称
	private  Integer   qckc;			//期初库存
	private  Double   qcje;			//期初金额
	private  Integer   cgrksl;			//采购入库数量
	private  Double   cgrkje;			//采购入库金额
	private  Double   xsje;			//销售金额
	private  Integer   xssl;			//销售数量
	private  Integer   b2bcksl;			//B2B出库数量
	private  Integer   qmkc;			//期末库存
	private  Double   qmje;			//期末金额
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
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
	public String getSpmc() {
		return spmc;
	}
	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}
	public Integer getQckc() {
		return qckc;
	}
	public void setQckc(Integer qckc) {
		this.qckc = qckc;
	}
	public Double getQcje() {
		return qcje;
	}
	public void setQcje(Double qcje) {
		this.qcje = qcje;
	}
	public Integer getCgrksl() {
		return cgrksl;
	}
	public void setCgrksl(Integer cgrksl) {
		this.cgrksl = cgrksl;
	}
	public Double getCgrkje() {
		return cgrkje;
	}
	public void setCgrkje(Double cgrkje) {
		this.cgrkje = cgrkje;
	}
	public Double getXsje() {
		return xsje;
	}
	public void setXsje(Double xsje) {
		this.xsje = xsje;
	}
	public Integer getXssl() {
		return xssl;
	}
	public void setXssl(Integer xssl) {
		this.xssl = xssl;
	}
	public Integer getB2bcksl() {
		return b2bcksl;
	}
	public void setB2bcksl(Integer b2bcksl) {
		this.b2bcksl = b2bcksl;
	}
	public Integer getQmkc() {
		return qmkc;
	}
	public void setQmkc(Integer qmkc) {
		this.qmkc = qmkc;
	}
	public Double getQmje() {
		return qmje;
	}
	public void setQmje(Double qmje) {
		this.qmje = qmje;
	}

	
	
}
