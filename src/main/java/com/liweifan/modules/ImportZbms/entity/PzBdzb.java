package com.liweifan.modules.ImportZbms.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name="pz_bdzb")
public class PzBdzb implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private  String   id;			//主键ID
	private  String   zbbm;			//指标编码
	private  String   zbmc;			//指标名称
	private  String   bdfl;			//表单分类
	private  String   zbjc;			//指标简称
	private  String   zbjb;			//指标级别
	private  Integer  zbxh;			//指标序号
	private  String   pid;			//上级指标ID
	private  String   pcode;			//上级指标编码
	private  String   pname;			//上级指标名称
	private  String   zbfl;			//指标分类
	private  String   zbsjgs;			//指标数据格式
	private  String   txfs;			//填写方式
	private  String   jsgs;			//计算公式
	private  String   sfbh;			//是否变化
	private  String   sfgd;			//是否固定
	private  String   datestart;			//生效时间
	private  String   dateend;			//失效时间
	private  String   remark;			//备注
	private  String   zbdw;			//指标单位
	private  String   field1;			//
	private  String   field2;			//
	private  String   field3;			//
	private  String   field4;			//
	private  String   field5;			//
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZbbm() {
		return zbbm;
	}
	public void setZbbm(String zbbm) {
		this.zbbm = zbbm;
	}
	public String getZbmc() {
		return zbmc;
	}
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	public String getBdfl() {
		return bdfl;
	}
	public void setBdfl(String bdfl) {
		this.bdfl = bdfl;
	}
	public String getZbjc() {
		return zbjc;
	}
	public void setZbjc(String zbjc) {
		this.zbjc = zbjc;
	}
	public String getZbjb() {
		return zbjb;
	}
	public void setZbjb(String zbjb) {
		this.zbjb = zbjb;
	}
	public Integer getZbxh() {
		return zbxh;
	}
	public void setZbxh(Integer zbxh) {
		this.zbxh = zbxh;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getZbfl() {
		return zbfl;
	}
	public void setZbfl(String zbfl) {
		this.zbfl = zbfl;
	}
	public String getZbsjgs() {
		return zbsjgs;
	}
	public void setZbsjgs(String zbsjgs) {
		this.zbsjgs = zbsjgs;
	}
	public String getTxfs() {
		return txfs;
	}
	public void setTxfs(String txfs) {
		this.txfs = txfs;
	}
	public String getJsgs() {
		return jsgs;
	}
	public void setJsgs(String jsgs) {
		this.jsgs = jsgs;
	}
	public String getSfbh() {
		return sfbh;
	}
	public void setSfbh(String sfbh) {
		this.sfbh = sfbh;
	}
	public String getSfgd() {
		return sfgd;
	}
	public void setSfgd(String sfgd) {
		this.sfgd = sfgd;
	}
	public String getDatestart() {
		return datestart;
	}
	public void setDatestart(String datestart) {
		this.datestart = datestart;
	}
	public String getDateend() {
		return dateend;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getZbdw() {
		return zbdw;
	}
	public void setZbdw(String zbdw) {
		this.zbdw = zbdw;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	
	
}
