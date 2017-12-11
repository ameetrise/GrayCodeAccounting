package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 11/23/17.
 */

public class CompanyDetails {

    @SerializedName("Code")
    @Expose
    private Long code;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private CompanyDetailsData companyDetailsData;

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

    public CompanyDetailsData getCompanyDetailsData() {
        return companyDetailsData;
    }

    public void setCompanyDetailsData(CompanyDetailsData data) {
        this.companyDetailsData = data;
    }

}