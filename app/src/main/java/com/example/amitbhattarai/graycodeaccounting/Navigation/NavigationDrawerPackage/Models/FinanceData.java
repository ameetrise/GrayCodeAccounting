package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 12/1/17.
 */

public class FinanceData {
    public FinanceData(String accountChartName, Long groupOrLedger, String code, String codeString, Double amount) {
        this.accountChartName = accountChartName;
        this.groupOrLedger = groupOrLedger;
        this.code = code;
        this.codeString = codeString;
        this.amount = amount;
    }

    @SerializedName("AccountChartName")
    @Expose
    private String accountChartName;
    @SerializedName("GroupOrLedger")
    @Expose
    private Long groupOrLedger;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("CodeString")
    @Expose
    private String codeString;
    @SerializedName("Amount")
    @Expose
    private Double amount;

    public String getAccountChartName() {
        return accountChartName;
    }

    public void setAccountChartName(String accountChartName) {
        this.accountChartName = accountChartName;
    }

    public Long getGroupOrLedger() {
        return groupOrLedger;
    }

    public void setGroupOrLedger(Long groupOrLedger) {
        this.groupOrLedger = groupOrLedger;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeString() {
        return codeString;
    }

    public void setCodeString(String codeString) {
        this.codeString = codeString;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}