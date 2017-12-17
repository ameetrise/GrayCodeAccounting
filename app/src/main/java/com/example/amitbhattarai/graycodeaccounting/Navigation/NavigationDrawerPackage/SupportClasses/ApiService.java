package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CompanyDetails;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ContactsModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinancialReports;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoicesModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.LedgerCashAndBank;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.LedgerCustomersAndSuppliers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ameet Bhattarai on 7/21/2017.
 */

public interface ApiService {
    @GET("5a16706131000056008d324d")
    Call<String> getJson();

    @GET("OrganizationSetting/GetCompanyInformation/{companyid}")
    Call<CompanyDetails> getCompanyDetails(@Path("companyid") String companyId);

    @GET("Dashboard/GetLedgerBalanceCustomerAndSupplier/{companyId}/{branchId}")
    Call<LedgerCustomersAndSuppliers> getCustomersAndSuppliers(@Path("companyId") String companyId,@Path("branchId") String branchid);

    @GET("Dashboard/GetLedgerBalanceCashAndBank/{companyId}/{branchId}")
    Call<LedgerCashAndBank> getgetCashandBank(@Path("companyId") String companyId,@Path("branchId") String branchid);

    @GET("Associate/list/{pageNo}/{pageSize}")
    Call<ContactsModel> getContactList(@Path("pageNo") String pageNo, @Path("pageSize") String pageSize);

    @FormUrlEncoded
    @POST("Sales/GetInvoicesByCriteria")
    Call<List<InvoicesModel>> getInvoices(
            @Field("CompanyId") String company_id,
            @Field("BranchId") String branch_id,
            @Field("SalesType") String sales_type,
            @Field("DateType") String data_type,
            @Field("FromDate") String from_date,
            @Field("ToDate") String to_date
    );

    @GET("FinancialStatement/GetFinancialStatement/{companyid}/{reportType}/{fromDate}/{toDate}")
    Call<FinancialReports> getFinanceReport(@Path("companyid") String companyId,@Path("reportType") String reportType,@Path("fromDate") String fromDate,@Path("toDate")String toDate);
}