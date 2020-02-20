package com.liweifan.modules.ImportZbms.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="pa_bdfl")
public class PzBdfl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private  String   code;			//表单编号
	private  String   name;			//表单名称
	private  String   bdywmc;			//表单英文名称
	private  String   bdzbfl;			//表单指标分类
	private  String   pcode;			//上级表单编码
	private  String   pname;			//上级表单名称
	private  String   sfpz;			//是否可配置
	private  String   tablename;			//
	private  String   sjq;			//
	private  String   dwzd;			//
	private  String   STARTDATE;			//生效时间
	private  String   ENDDATE;			//失效时间
	private  String   FIELD1;			//备注
	private  String   FIELD2;			//
	private  String   FIELD3;			//
	private  String   FIELD4;			//
	private  String   FIELD5;			//
	
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
	public String getBdywmc() {
		return bdywmc;
	}
	public void setBdywmc(String bdywmc) {
		this.bdywmc = bdywmc;
	}
	public String getBdzbfl() {
		return bdzbfl;
	}
	public void setBdzbfl(String bdzbfl) {
		this.bdzbfl = bdzbfl;
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
	public String getSfpz() {
		return sfpz;
	}
	public void setSfpz(String sfpz) {
		this.sfpz = sfpz;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getSjq() {
		return sjq;
	}
	public void setSjq(String sjq) {
		this.sjq = sjq;
	}
	public String getDwzd() {
		return dwzd;
	}
	public void setDwzd(String dwzd) {
		this.dwzd = dwzd;
	}
	public String getSTARTDATE() {
		return STARTDATE;
	}
	public void setSTARTDATE(String sTARTDATE) {
		STARTDATE = sTARTDATE;
	}
	public String getENDDATE() {
		return ENDDATE;
	}
	public void setENDDATE(String eNDDATE) {
		ENDDATE = eNDDATE;
	}
	public String getFIELD1() {
		return FIELD1;
	}
	public void setFIELD1(String fIELD1) {
		FIELD1 = fIELD1;
	}
	public String getFIELD2() {
		return FIELD2;
	}
	public void setFIELD2(String fIELD2) {
		FIELD2 = fIELD2;
	}
	public String getFIELD3() {
		return FIELD3;
	}
	public void setFIELD3(String fIELD3) {
		FIELD3 = fIELD3;
	}
	public String getFIELD4() {
		return FIELD4;
	}
	public void setFIELD4(String fIELD4) {
		FIELD4 = fIELD4;
	}
	public String getFIELD5() {
		return FIELD5;
	}
	public void setFIELD5(String fIELD5) {
		FIELD5 = fIELD5;
	}
	
}
