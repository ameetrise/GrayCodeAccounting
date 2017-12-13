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

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    String home = "Home";

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(String balanceSheet) {
        this.balanceSheet = balanceSheet;
    }

    public String getTrialBalance() {
        return trialBalance;
    }

    public void setTrialBalance(String trialBalance) {
        this.trialBalance = trialBalance;
    }

    public String getProfitandloss() {
        return profitandloss;
    }

    public void setProfitandloss(String profitandloss) {
        this.profitandloss = profitandloss;
    }

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getContactus() {
        return contactus;
    }

    public void setContactus(String contactus) {
        this.contactus = contactus;
    }

    public String getReports() {
        return reports;
    }

    public void setReports(String reports) {
        this.reports = reports;
    }

    public Pref getPref() {
        return pref;
    }

    public void setPref(Pref pref) {
        this.pref = pref;
    }

    public ArrayList<String> getBranchlist() {
        return branchlist;
    }

    String contacts = "Contacts";
    String balanceSheet = "Balance Sheet";
    String trialBalance = "Trial Balance";
    String profitandloss = "Profit and Loss";
    String aboutus = "About us";
    String contactus = "Contact us";
    String reports = "Reports";
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
