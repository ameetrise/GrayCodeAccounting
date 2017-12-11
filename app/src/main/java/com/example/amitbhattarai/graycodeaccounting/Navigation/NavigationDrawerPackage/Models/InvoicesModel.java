package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 12/3/17.
 */

public class InvoicesModel {

    @SerializedName("SalesId")
    @Expose
    private Long salesId;
    @SerializedName("InvoiceNo")
    @Expose
    private Long invoiceNo;
    @SerializedName("Amount")
    @Expose
    private Long amount;
    @SerializedName("ReceivedAmount")
    @Expose
    private Long receivedAmount;
    @SerializedName("InvoiceDate")
    @Expose
    private String invoiceDate;
    @SerializedName("DueDate")
    @Expose
    private String dueDate;
    @SerializedName("AssociateId")
    @Expose
    private Long associateId;
    @SerializedName("AssociateName")
    @Expose
    private String associateName;

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public Long getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(Long invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Long receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Long associateId) {
        this.associateId = associateId;
    }

    public String getAssociateName() {
        return associateName;
    }

    public void setAssociateName(String associateName) {
        this.associateName = associateName;
    }

}