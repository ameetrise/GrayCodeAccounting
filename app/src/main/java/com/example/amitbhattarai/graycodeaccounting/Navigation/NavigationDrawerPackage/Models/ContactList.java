package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amitbhattarai on 12/14/17.
 */

public class ContactList {
    @SerializedName("AssociateId")
    @Expose
    private Long associateId;
    @SerializedName("Honorofic")
    @Expose
    private String honorofic;
    @SerializedName("AssociateGroupId")
    @Expose
    private Long associateGroupId;
    @SerializedName("SubLedgerTypeId")
    @Expose
    private Long subLedgerTypeId;
    @SerializedName("SubLedgerId")
    @Expose
    private Long subLedgerId;
    @SerializedName("DisplayName")
    @Expose
    private String displayName;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("CompanyId")
    @Expose
    private Long companyId;
    @SerializedName("CultureId")
    @Expose
    private Long cultureId;
    @SerializedName("VatNo")
    @Expose
    private String vatNo;
    @SerializedName("LegalName")
    @Expose
    private String legalName;
    @SerializedName("Note")
    @Expose
    private Object note;
    @SerializedName("State")
    @Expose
    private Long state;
    @SerializedName("District")
    @Expose
    private Long district;
    @SerializedName("Mun_Gau")
    @Expose
    private Long munGau;
    @SerializedName("Ward")
    @Expose
    private Long ward;
    @SerializedName("Address")
    @Expose
    private String address;
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

    public Long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Long associateId) {
        this.associateId = associateId;
    }

    public String getHonorofic() {
        return honorofic;
    }

    public void setHonorofic(String honorofic) {
        this.honorofic = honorofic;
    }

    public Long getAssociateGroupId() {
        return associateGroupId;
    }

    public void setAssociateGroupId(Long associateGroupId) {
        this.associateGroupId = associateGroupId;
    }

    public Long getSubLedgerTypeId() {
        return subLedgerTypeId;
    }

    public void setSubLedgerTypeId(Long subLedgerTypeId) {
        this.subLedgerTypeId = subLedgerTypeId;
    }

    public Long getSubLedgerId() {
        return subLedgerId;
    }

    public void setSubLedgerId(Long subLedgerId) {
        this.subLedgerId = subLedgerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCultureId() {
        return cultureId;
    }

    public void setCultureId(Long cultureId) {
        this.cultureId = cultureId;
    }

    public String getVatNo() {
        return vatNo;
    }

    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getDistrict() {
        return district;
    }

    public void setDistrict(Long district) {
        this.district = district;
    }

    public Long getMunGau() {
        return munGau;
    }

    public void setMunGau(Long munGau) {
        this.munGau = munGau;
    }

    public Long getWard() {
        return ward;
    }

    public void setWard(Long ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
