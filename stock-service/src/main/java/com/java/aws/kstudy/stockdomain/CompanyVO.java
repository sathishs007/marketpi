package com.java.aws.kstudy.stockdomain;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection ="company")
public class CompanyVO {
	//@Id
	
	@NotBlank
	@Indexed(unique=true)
	@JsonProperty("company_code")
	private String companyCode;
	@NotBlank
	@JsonProperty("company_name")
	private String companyName;
	@NotBlank
	@JsonProperty("company_ceo")
	private String companyCeo;
	@NotNull
	@Min(value = 10,message = "companyTurnover greater then 10 cr")
	@JsonProperty("company_turnover")
	private Double companyTurnover;
	@NotBlank
	@JsonProperty("company_website")
	private String companyWebsite;
	@NotBlank
	@JsonProperty("stock_exchange")
	private String stockExchange;
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
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCeo() {
		return companyCeo;
	}
	public void setCompanyCeo(String companyCeo) {
		this.companyCeo = companyCeo;
	}
	public Double getCompanyTurnover() {
		return companyTurnover;
	}
	public void setCompanyTurnover(Double companyTurnover) {
		this.companyTurnover = companyTurnover;
	}
	public String getCompanyWebsite() {
		return companyWebsite;
	}
	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}
	public String getStockExchange() {
		return stockExchange;
	}
	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
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
