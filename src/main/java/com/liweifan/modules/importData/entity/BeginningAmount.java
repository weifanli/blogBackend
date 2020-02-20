package com.liweifan.modules.importData.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="beginning_amount")
public class BeginningAmount implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private  Integer   pid;			//
	private  String   productCode;			//商品编号
	private  String   productName;			//商品名称
	private  Double   productDj;			//商品单位
	private  Integer   productCount;			//商品数量
	private  Double   productJe;			//库存总金额
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductDj() {
		return productDj;
	}
	public void setProductDj(Double productDj) {
		this.productDj = productDj;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public Double getProductJe() {
		return productJe;
	}
	public void setProductJe(Double productJe) {
		this.productJe = productJe;
	}
	
	
}
