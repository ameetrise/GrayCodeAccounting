package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by ameet on 1/31/2017.
 */



public class Pref {
    ProjectStrings strings;
    SharedPreferences pref;
    public static final String SELECTEDCOMPANY = "selected_company";
    public static final String COMPANYID = "company_id";
    public static final String isFIRSTLOGIN = "isFirstLogin";
    public static final String COMPANYNAME = "company_name";
    public static final String COMPANYLEGALNAME = "company_legal_name";
    public static final String COMPANYPANNO = "company_pan_no";
    public static final String COMPANYREGISTRATIONNUMBER = "company_reg_number";
    public static final String COMPANYVATNO = "company_vat_no";
    public static final String COMPANYLOGO = "company_logo";
    public static final String COMPANYCOUNTRY = "company_country";
    public static final String COMPANYSTATE = "company_state";
    public static final String COMPANYADDRESS = "company_state";
    public static final String COMPANYEMAIL = "company_email";
    public static final String COMPANYFACINGEMAIL = "company_facing_email";
    public static final String COMPANYPHONE = "company_phone";
    public static final String COMPANYWEBSITE = "company_website";
    public static final String COMPANYTYPEID = "company_type_id";
    public static final String COMPANYCULTUREID = "company_culture_id";
    public static final String COMPANIES= "";

    public Pref(Context context) {
        if (context == null) {
            throw new NullPointerException("Context is null, pass the context");
        }
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean saveString(String key, String value) {
        return pref.edit().putString(key, value).commit();
    }

    public String getSavedValue(String key) {
        strings= new ProjectStrings();
        return pref.getString(key, strings.getEmpty());
    }

    public String getIsFIRSTLOGIN() {
        String isfirstLogin = getSavedValue(isFIRSTLOGIN);
        return isfirstLogin;
    }

    public String getCompanyList() {
        String companylist = getSavedValue(COMPANIES);
        return companylist;
    }

    public String getSelectedcompany() {
        String sc = getSavedValue(SELECTEDCOMPANY);
        return sc;
    }


    public String getCompanyid() {
        String companyId = getSavedValue(COMPANYID);
        return companyId;
    }

    public String getCompanyname() {
        String companyName = (getSavedValue(COMPANYNAME));
        return companyName;
    }

    public String getCompanypanno() {
        String companyPanNo = getSavedValue(COMPANYPANNO);
        return companyPanNo;
    }

    public String getCompanyregistrationnumber() {
        String regnumber = (getSavedValue(COMPANYREGISTRATIONNUMBER));
        return regnumber;
    }

    public String getCompanyvatno() {
        String vat = (getSavedValue(COMPANYVATNO));
        return vat;
    }

    public String getCompanylogo() {
        String logo = (getSavedValue(COMPANYLOGO));
        return logo;
    }

    public String getCompanycountry() {
        String country = (getSavedValue(COMPANYCOUNTRY));
        return country;
    }

    public String getCompanystate() {
        String state = (getSavedValue(COMPANYSTATE));
        return state;
    }

    public String getCompanyaddress() {
        String address = (getSavedValue(COMPANYADDRESS));
        return address;
    }

    public String getCompanyemail() {
        String email = (getSavedValue(COMPANYEMAIL));
        return email;
    }

    public String getCompanyfacingemail() {
        String facingemail = (getSavedValue(COMPANYFACINGEMAIL));
        return facingemail;
    }

    public String getCompanyphone() {
        String phone = (getSavedValue(COMPANYPHONE));
        return phone;
    }

    public String getCompanywebsite() {
        String website = (getSavedValue(COMPANYWEBSITE));
        return website;
    }

    public String getCompanytypeid() {
        String typeid = (getSavedValue(COMPANYTYPEID));
        return typeid;
    }

    public String getCompanycultureid() {
        String cultureid = (getSavedValue(COMPANYCULTUREID));
        return cultureid;
    }
}


    /*Pref pref= new Pref(c);
    pref.saveString(pref.LOGIN_STATUS,"hello");

    Pref p= new Pref(this);
    Boolean isLoggedin= p.isLoggedIn();
    Log.d("pradeep", String.valueOf(isLoggedin));*/