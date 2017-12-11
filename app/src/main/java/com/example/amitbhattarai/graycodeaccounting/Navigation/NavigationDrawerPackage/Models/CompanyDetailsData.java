package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 11/23/17.
 */


public class CompanyDetailsData {

    @SerializedName("CompanyId")
    @Expose
    private Long companyId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("LegalName")
    @Expose
    private String legalName;
    @SerializedName("PanNo")
    @Expose
    private String panNo;
    @SerializedName("RegistrationNumber")
    @Expose
    private String registrationNumber;
    @SerializedName("VatNo")
    @Expose
    private String vatNo;
    @SerializedName("Logo")
    @Expose
    private String logo;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("ZipCode")
    @Expose
    private String zipCode;
    @SerializedName("Address1")
    @Expose
    private String address1;
    @SerializedName("Address2")
    @Expose
    private String address2;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("CustomerFacingEmail")
    @Expose
    private String customerFacingEmail;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("CompanyTypeId")
    @Expose
    private Long companyTypeId;
    @SerializedName("CultureId")
    @Expose
    private Long cultureId;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("IsApproved")
    @Expose
    private Boolean isApproved;
    @SerializedName("ApprovedBy")
    @Expose
    private Long approvedBy;
    @SerializedName("IsDeleted")
    @Expose
    private Boolean isDeleted;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("AddedBy")
    @Expose
    private Long addedBy;
    @SerializedName("UpdatedBy")
    @Expose
    private Long updatedBy;
    @SerializedName("UpdatedOn")
    @Expose
    private String updatedOn;
    @SerializedName("RowTotal")
    @Expose
    private Long rowTotal;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerFacingEmail() {
        return customerFacingEmail;
    }

    public void setCustomerFacingEmail(String customerFacingEmail) {
        this.customerFacingEmail = customerFacingEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Long getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Long companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Long getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Long approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Long getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Long addedBy) {
        this.addedBy = addedBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getRowTotal() {
        return rowTotal;
    }

    public void setRowTotal(Long rowTotal) {
        this.rowTotal = rowTotal;
    }

}
