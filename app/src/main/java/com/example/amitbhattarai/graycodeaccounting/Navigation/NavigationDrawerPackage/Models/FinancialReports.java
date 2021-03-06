package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amitbhattarai on 12/1/17.
 */

public class FinancialReports {

    @SerializedName("Code")
    @Expose
    private Long code;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<FinanceData> data = null;

    public FinancialReports(Long code, String message, List<FinanceData> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FinanceData> getData() {
        return data;
    }

    public void setData(List<FinanceData> data) {
        this.data = data;
    }

}