package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models;

/**
 * Created by amitbhattarai on 11/20/17.
 */

public class InvoiceModel {
    String sn;
    String description;
    String quantity;
    String rate;
    String amt;

    public InvoiceModel(String Sn,String Description,String Quantity,String Rate,String Amount){
        this.sn=Sn;
        this.description=Description;
        this.quantity= Quantity;
        this.rate=Rate;
        this.amount=Amount;
    }


    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String amount;
}
