package com.crm.customers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "customer_interview")
public class CustomerInterview {
    @Id
    @Column(name="customer_interview_id")
    private String customerInterviewId;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="customer_company")
    private String customerCompany;
    @Column(name="called_num")
    private String calledNum;
    @Column(name="label")
    private Integer label;
    @Column(name="type")
    private Integer type;
    @Column(name="result")
    private String result;
    @Column(name="user_id")
    private Integer userId;
    @Column(name="create_time")
    private Date createTime;

    public String getCustomerInterviewId() {
        return customerInterviewId;
    }

    public void setCustomerInterviewId(String customerInterviewId) {
        this.customerInterviewId = customerInterviewId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getCalledNum() {
        return calledNum;
    }

    public void setCalledNum(String calledNum) {
        this.calledNum = calledNum;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CustomerInterview{" +
                "customerInterviewId='" + customerInterviewId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerCompany='" + customerCompany + '\'' +
                ", calledNum='" + calledNum + '\'' +
                ", label=" + label +
                ", type=" + type +
                ", result='" + result + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                '}';
    }
}
