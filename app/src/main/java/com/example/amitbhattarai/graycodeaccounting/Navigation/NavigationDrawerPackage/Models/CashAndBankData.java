package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 11/23/17.
 */

public class CashAndBankData {


    @SerializedName("AccountChartName")
    @Expose
    private String accountChartName;
    @SerializedName("SubLedgerName")
    @Expose
    private Object subLedgerName;
    @SerializedName("VoucherDetailId")
    @Expose
    private Long voucherDetailId;
    @SerializedName("VoucherId")
    @Expose
    private Long voucherId;
    @SerializedName("AccountChartId")
    @Expose
    private Long accountChartId;
    @SerializedName("SubLedgerId")
    @Expose
    private Long subLedgerId;
    @SerializedName("Amount")
    @Expose
    private double amount;
    @SerializedName("Description")
    @Expose
    private Object description;
    @SerializedName("CurrencyId")
    @Expose
    private Long currencyId;

    public String getAccountChartName() {
        return accountChartName;
    }

    public void setAccountChartName(String accountChartName) {
        this.accountChartName = accountChartName;
    }

    public Object getSubLedgerName() {
        return subLedgerName;
    }

    public void setSubLedgerName(Object subLedgerName) {
        this.subLedgerName = subLedgerName;
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

    public Long getSubLedgerId() {
        return subLedgerId;
    }

    public void setSubLedgerId(Long subLedgerId) {
        this.subLedgerId = subLedgerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

}