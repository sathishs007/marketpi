package com.java.aws.kstudy.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
@Document(collection = "stock")
public class StockVO {
	
	@NotNull
	@JsonProperty("stock_price")
	private Double stockPrice;
	@NotBlank
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
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyCode == null) ? 0 : companyCode.hashCode());
		result = prime * result + (int) (createBy ^ (createBy >>> 32));
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((isDelete == null) ? 0 : isDelete.hashCode());
		result = prime * result + ((stockPrice == null) ? 0 : stockPrice.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + (int) (updateBy ^ (updateBy >>> 32));
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockVO other = (StockVO) obj;
		if (companyCode == null) {
			if (other.companyCode != null)
				return false;
		} else if (!companyCode.equals(other.companyCode))
			return false;
		if (createBy != other.createBy)
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (isDelete == null) {
			if (other.isDelete != null)
				return false;
		} else if (!isDelete.equals(other.isDelete))
			return false;
		if (stockPrice == null) {
			if (other.stockPrice != null)
				return false;
		} else if (!stockPrice.equals(other.stockPrice))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (updateBy != other.updateBy)
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "StockVO [stockPrice=" + stockPrice + ", companyCode=" + companyCode + ", userId=" + userId
				+ ", updatedDate=" + updatedDate + ", createdDate=" + createdDate + ", isDelete=" + isDelete
				+ ", createBy=" + createBy + ", updateBy=" + updateBy + ", time=" + time + ", date=" + date + "]";
	}
	
}
