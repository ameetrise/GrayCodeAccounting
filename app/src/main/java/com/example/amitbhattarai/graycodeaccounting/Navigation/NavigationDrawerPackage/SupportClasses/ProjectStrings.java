package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses;

import java.util.ArrayList;

/**
 * Created by amitbhattarai on 12/4/17.
 */

public class ProjectStrings {

    String overdue = "Overdue";
    String unpaid = "Unpaid";
    String partial = "Partial";
    String allinvoices = "All Invoices";
    String paid = "Paid";
    String sales = "Sales";
    String purchase = "Purchase";
    String branchId = "1";
    String companyId = "1";
    Pref pref;
    public String getCompanyId() {
        String cid= pref.getCompanyid();
        return cid;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    String empty = "[]";

    public void setBranchlist(ArrayList<String> branchlist) {
        this.branchlist = branchlist;
    }

    ArrayList<String> branchlist = new ArrayList<>();

    public String getOverdue() {
        return overdue;
    }

    public void setOverdue(String overdue) {
        this.overdue = overdue;
    }

    public String getUnpaid() {
        return unpaid;
    }

    public void setUnpaid(String unpaid) {
        this.unpaid = unpaid;
    }

    public String getPartial() {
        return partial;
    }

    public void setPartial(String partial) {
        this.partial = partial;
    }

    public String getAllinvoices() {
        return allinvoices;
    }

    public void setAllinvoices(String allinvoices) {
        this.allinvoices = allinvoices;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

}
