package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 11/23/17.
 */

public class CustomersAndSuppliersData {

    @SerializedName("AccountChartName")
    @Expose
    private String accountChartName;

    public String getSubLedgerName() {
        return SubLedgerName;
    }

    public void setSubLedgerName(String subLedgerName) {
        SubLedgerName = subLedgerName;
    }

    @SerializedName("SubLedgerName")
    @Expose
    private String SubLedgerName;
    @SerializedName("VoucherDetailId")
    @Expose
    private Long voucherDetailId;
    @SerializedName("VoucherId")
    @Expose
    private Long voucherId;
    @SerializedName("AccountChartId")
    @Expose
    private Long accountChartId;
    @SerializedName("AccountChartType")
    @Expose
    private String accountChartType;
    @SerializedName("SubLedgerId")
    @Expose
    private Long subLedgerId;
    @SerializedName("Amount")
    @Expose
    private Double amount;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("CurrencyId")
    @Expose
    private Long currencyId;
    @SerializedName("CultureId")
    @Expose
    private Long cultureId;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("Name")
    @Expose
    private Object name;
    @SerializedName("AccountNumber")
    @Expose
    private Object accountNumber;
    @SerializedName("DrCr")
    @Expose
    private Long drCr;

    public String getAccountChartName() {
        return accountChartName;
    }

    public void setAccountChartName(String accountChartName) {
        this.accountChartName = accountChartName;
    }

    public Long getVoucherDetailId() {
        return voucherDetailId;
    }

    public void setVoucherDetailId(Long voucherDetailId) {
        this.voucherDetailId = voucherDetailId;
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public Long getAccountChartId() {
        return accountChartId;
    }

    public void setAccountChartId(Long accountChartId) {
        this.accountChartId = accountChartId;
    }

    public String getAccountChartType() {
        return accountChartType;
    }

    public void setAccountChartType(String accountChartType) {
        this.accountChartType = accountChartType;
    }

    public Long getSubLedgerId() {
        return subLedgerId;
    }

    public void setSubLedgerId(Long subLedgerId) {
        this.subLedgerId = subLedgerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Object accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getDrCr() {
        return drCr;
    }

    public void setDrCr(Long drCr) {
        this.drCr = drCr;
    }

}
