package com.java.aws.kstudy.kafka.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection = "stock")
public class StockVO {
	
	@JsonProperty("stock_price")
	private Double stockPrice;
	@JsonProperty("company_code")
	private String companyCode;
	@JsonProperty("user_id")
	private long userId;
	@JsonProperty("created_date")
	private Date updatedDate;
	@JsonProperty("updated_date")
	private Date createdDate;
	@JsonProperty("is_delete")
	private Boolean isDelete;
	@JsonProperty("create_by")
	private long createBy;
	@JsonProperty("update_by")
	private long updateBy;
	@JsonProperty("time")
	private String time;
	@JsonProperty("date")
	private String date;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getStockPrice() {
		return stockPrice;
	}
	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	public long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}
	public long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(long updateBy) {
		this.updateBy = updateBy;
	}
	
}
