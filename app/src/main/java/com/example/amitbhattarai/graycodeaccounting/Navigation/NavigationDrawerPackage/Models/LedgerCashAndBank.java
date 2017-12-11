package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amitbhattarai on 11/23/17.
 */


public class LedgerCashAndBank {

    @SerializedName("Code")
    @Expose
    private Long code;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<CashAndBankData> data = null;

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

    public List<CashAndBankData> getData() {
        return data;
    }

    public void setData(List<CashAndBankData> data) {
        this.data = data;
    }

}
