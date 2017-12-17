package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities.InvoiceDetails;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities.MainActivity;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities.Sales;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CashAndBankData;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CompanyDetails;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CompanyDetailsData;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CustomersAndSuppliersData;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoicesModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.LedgerCashAndBank;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.LedgerCustomersAndSuppliers;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
import com.example.amitbhattarai.graycodeaccounting.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    Pref pref;
    ProgressDialog pd;
    String currentDate;
    GenericMethods genericMethods;
    private static final String ARG_PARAM = "arguments_parameters";
    String TAG = "checkthis";
    String paramText;
    String companyId;
    String branchId;
    ProjectStrings strings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramText = getArguments().getString(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome, container, false);
        pd = new ProgressDialog(getContext(), R.style.CustomDialogTheme);
        strings = new ProjectStrings();
        pd.show();
        pref = new Pref(getContext());
        companyId = strings.getCompanyId();
        branchId = strings.getBranchId();
        genericMethods = new GenericMethods();
        Log.d(TAG, "onCreateView: GMMMMMM " + genericMethods.getCurrentDate());
        currentDate = genericMethods.getCurrentDate();
        LinearLayout cashinHandlayout = view.findViewById(R.id.cashinhandlayout);
        LinearLayout bankaclayout = view.findViewById(R.id.bankaclayout);
        LinearLayout customerslayout = view.findViewById(R.id.customerslayout);
        LinearLayout supplierslayout = view.findViewById(R.id.supplierslayout);
        LinearLayout overduelayout = view.findViewById(R.id.overduelayout);
        LinearLayout unpaidlayout = view.findViewById(R.id.unpaidlayout);
        LinearLayout todaysSalesPanel = view.findViewById(R.id.todaysSalesLayout);
        LinearLayout todaysPurchasePanel = view.findViewById(R.id.todaysPurchaseLayout);

        TextView cashinhandTV = view.findViewById(R.id.cashinhandtextview);
        TextView bankAcTV = view.findViewById(R.id.bank_ac_textView);
        TextView customersTv = view.findViewById(R.id.customers_textView);
        TextView suppliersTv = view.findViewById(R.id.suppliers_textView);
        TextView overdueTitle = view.findViewById(R.id.overdueTitle);
        TextView unpaidTitle = view.findViewById(R.id.unpaidTitle);
        TextView overdueValue = view.findViewById(R.id.overdueValue);
        TextView unpaidValue = view.findViewById(R.id.unpaidValue);
        TextView paid = view.findViewById(R.id.paid);
        TextView unpaid = view.findViewById(R.id.unpaid);
        TextView total = view.findViewById(R.id.total);
        TextView salescount = view.findViewById(R.id.todaysSalesCount);
        TextView purchsepaid = view.findViewById(R.id.purchase_paid);
        TextView purchaseunpaid = view.findViewById(R.id.purchase_unpaid);
        TextView purcahsetotal = view.findViewById(R.id.purchase_total);
        TextView purchasecount = view.findViewById(R.id.todaysPurchaseCount);
        TextView accountDate = view.findViewById(R.id.accountDate);

        accountDate.setText("As on " + currentDate);

        Log.d(TAG, "onCreateView: " + pref.getCompanyname());
        getCashAndBank(cashinhandTV, bankAcTV, cashinHandlayout, bankaclayout);
        getCustomerAndSupplier(customersTv, suppliersTv, customerslayout, supplierslayout);
        getOverdueInvoices(overdueTitle, overdueValue, overduelayout);
        getUnpaidInvoices(unpaidTitle, unpaidValue, unpaidlayout);
        getTodaysSales(paid, unpaid, total, salescount, todaysSalesPanel);
        getTodaysPurchase(purchsepaid, purchaseunpaid, purcahsetotal, purchasecount, todaysPurchasePanel);
        if (pref.getIsFIRSTLOGIN().equals(strings.getEmpty())) {
            Log.d(TAG, "assFirst" + pref.getIsFIRSTLOGIN() + " " + strings.getEmpty());
            getCompanyDetails();
        } else Log.d(TAG, "assFirst No");
        return view;
    }

    public void getCompanyDetails() {
        ApiService api = Retroclient.getApiService();
        Log.d(TAG, "getCompanyDetailss: " + companyId);
        Call<CompanyDetails> call = api.getCompanyDetails(companyId);
        call.enqueue(new Callback<CompanyDetails>() {
            @Override
            public void onResponse(Call<CompanyDetails> call, Response<CompanyDetails> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCompanyDetailsData() != null) {
                        Log.d(TAG, "onResponseasd: ");
                        CompanyDetailsData data = response.body().getCompanyDetailsData();
                        pref.saveString(pref.COMPANYID, String.valueOf(data.getCompanyId()));
                        pref.saveString(pref.COMPANYNAME, String.valueOf(data.getName()));
                        pref.saveString(pref.COMPANYLEGALNAME, String.valueOf(data.getLegalName()));
                        pref.saveString(pref.COMPANYPANNO, String.valueOf(data.getPanNo()));
                        pref.saveString(pref.COMPANYVATNO, String.valueOf(data.getVatNo()));
                        pref.saveString(pref.COMPANYLOGO, String.valueOf(data.getLogo()));
                        pref.saveString(pref.COMPANYSTATE, String.valueOf(data.getState()));
                        pref.saveString(pref.COMPANYCOUNTRY, String.valueOf(data.getCountry()));
                        pref.saveString(pref.COMPANYADDRESS, String.valueOf(data.getAddress1()));
                        pref.saveString(pref.COMPANYEMAIL, String.valueOf(data.getEmail()));
                        pref.saveString(pref.COMPANYFACINGEMAIL, String.valueOf(data.getCustomerFacingEmail()));
                        pref.saveString(pref.COMPANYPHONE, String.valueOf(data.getPhone()));
                        pref.saveString(pref.COMPANYWEBSITE, String.valueOf(data.getWebsite()));
                        pref.saveString(pref.COMPANYTYPEID, String.valueOf(data.getCompanyTypeId()));
                        pref.saveString(pref.COMPANYTYPEID, String.valueOf(data.getCompanyTypeId()));
                        pref.saveString(pref.COMPANYCULTUREID, String.valueOf(data.getCultureId()));
                        pref.saveString(pref.isFIRSTLOGIN, "false");

                        if (getContext() instanceof MainActivity) {
                            ((MainActivity) getContext()).setCompanyDetails();
                        }

                    } else Log.d(TAG, "onResponseasd: response null");
                } else {
                    Log.d(TAG, "onResponseasd: Failed");
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CompanyDetails> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.toString());
                pd.dismiss();
            }
        });
    }

    public void getCashAndBank(final TextView cashinhandTV, final TextView bankAcTV, final LinearLayout cashinHandlayout,
                               final LinearLayout bankaclayout) {
        ApiService api = Retroclient.getApiService();
        Call<LedgerCashAndBank> call = api.getgetCashandBank(companyId, branchId);
        call.enqueue(new Callback<LedgerCashAndBank>() {
            @Override
            public void onResponse(Call<LedgerCashAndBank> call, final Response<LedgerCashAndBank> response) {
                if (response.isSuccessful()) {
                    final List<CashAndBankData> cash = new ArrayList<>();
                    final List<CashAndBankData> bank = new ArrayList<>();
                    Log.d(TAG, "onResponse: yestocheckss " + String.valueOf(new Gson().toJson(response.body())));
                    double totalAmount = 0;
                    long totalReceived = 0;
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        totalAmount = totalAmount + response.body().getData().get(i).getAmount();
                    }

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        if (response.body().getData().get(i).getAccountChartType().equals("b")) {
                            bank.add(response.body().getData().get(i));
                        }
                        if (response.body().getData().get(i).getAccountChartType().equals("c")) {
                            cash.add(response.body().getData().get(i));
                        }
                    }

                    cashinhandTV.setText("NRs " + String.valueOf(totalAmount));
                    bankAcTV.setText("NRs " + String.valueOf(totalReceived));
                    Log.d(TAG, "Total Amount " + String.valueOf(totalAmount + " Total received ") + String.valueOf(totalReceived));
                    pd.dismiss();

                    cashinHandlayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG, "onClicks: " + new Gson().toJson(response.body().getData()));
                            startActivity(new Intent(getContext(), InvoiceDetails.class).putExtra("data", new Gson().toJson(cash)).putExtra("class", "Cash in Hand"));
                        }
                    });

                    bankaclayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), InvoiceDetails.class).putExtra("data", new Gson().toJson(bank)).putExtra("class", "Bank A/C"));
                        }
                    });

                } else {
                    Log.d(TAG, "Response Test failed");
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LedgerCashAndBank> call, Throwable t) {
                Log.d(TAG, "Response Test failed" + t.toString());
                pd.dismiss();
            }
        });
    }

    public void getCustomerAndSupplier(final TextView customersTv, final TextView suppliersTv, final LinearLayout customerslayout,
                                       final LinearLayout supplierslayout) {
        ApiService api = Retroclient.getApiService();
        Call<LedgerCustomersAndSuppliers> call = api.getCustomersAndSuppliers(companyId, branchId);
        call.enqueue(new Callback<LedgerCustomersAndSuppliers>() {
            @Override
            public void onResponse(Call<LedgerCustomersAndSuppliers> call, final Response<LedgerCustomersAndSuppliers> response) {
                if (response.isSuccessful()) {

                    Log.d(TAG, "checkcusts YES " + String.valueOf(response.body().getData().size()));
                    double customerTotal = 0;
                    double vendorTotal = 0;
                    final ArrayList<CustomersAndSuppliersData> customersList = new ArrayList<>();
                    final ArrayList<CustomersAndSuppliersData> vendorsList = new ArrayList<>();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        if (response.body().getData().get(i).getAccountChartName().equals("VendorGL")) {
                            vendorsList.add(response.body().getData().get(i));
                            Log.d(TAG, "onResponse: vendorlistt " + new Gson().toJson(vendorsList));
                        } else if (response.body().getData().get(i).getAccountChartName().equals("CustomerGL")) {
                            customersList.add(response.body().getData().get(i));
                        }
                    }
                    for (int i = 0; i < vendorsList.size(); i++) {
                        vendorTotal = vendorTotal + vendorsList.get(i).getAmount();
                    }

                    for (int i = 0; i < customersList.size(); i++) {
                        customerTotal = customerTotal + customersList.get(i).getAmount();
                    }
                    customersTv.setText("NRs " + String.valueOf(customerTotal));
                    suppliersTv.setText("NRs " + String.valueOf(vendorTotal));


                    supplierslayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Log.d(TAG, "onClickcl: clicked");
                            startActivity(new Intent(getContext(), InvoiceDetails.class).putExtra("data", new Gson().toJson(vendorsList)).putExtra("class", "Sundry Creditors"));
                        }
                    });

                    customerslayout.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), InvoiceDetails.class).putExtra("data", new Gson().toJson(customersList)).putExtra("class", "Sundry Debtors"));
                        }
                    });

                } else Log.d(TAG, "checkcusts NO");
            }

            @Override
            public void onFailure(Call<LedgerCustomersAndSuppliers> call, Throwable t) {
                Log.d(TAG, "Response Test failed" + t.toString());
            }
        });
    }

    public void getOverdueInvoices(final TextView overdueTitle, final TextView overdueValue, final LinearLayout overduelayout) {
        ApiService api = Retroclient.getApiService();
        Call<List<InvoicesModel>> call = api.getInvoices(companyId, branchId, "overdue", "upto", "", currentDate);
        call.enqueue(new Callback<List<InvoicesModel>>() {
            @Override
            public void onResponse(Call<List<InvoicesModel>> call, final Response<List<InvoicesModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "datafetch is successful");
                    Log.d(TAG, "datafetch is successful " + new Gson().toJson(response.body()));
                    overdueTitle.setText("Overdue (" + String.valueOf(response.body().size()) + ")");
                    long totalAmount = 0;
                    long totalReceived = 0;
                    for (int i = 0; i < response.body().size(); i++) {
                        totalAmount = totalAmount + response.body().get(i).getAmount();
                        totalReceived = totalReceived + response.body().get(i).getReceivedAmount();
                    }
                    overdueValue.setText("NRs " + String.valueOf(totalAmount - totalReceived));

                    overduelayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), Sales.class).putExtra("data", new Gson().toJson(response.body())).putExtra("source", strings.getOverdue()));

                        }
                    });

                } else Log.d(TAG, "datafetch is failed");
            }

            @Override
            public void onFailure(Call<List<InvoicesModel>> call, Throwable t) {
                Log.d(TAG, "datafetch is " + t);
            }
        });
    }

    public void getUnpaidInvoices(final TextView overdueTitle, final TextView overdueValue, final LinearLayout unpaidlayout) {
        ApiService api = Retroclient.getApiService();
        Call<List<InvoicesModel>> call = api.getInvoices(companyId, branchId, "unpaid", "upto", "", currentDate);
        call.enqueue(new Callback<List<InvoicesModel>>() {
            @Override
            public void onResponse(Call<List<InvoicesModel>> call, final Response<List<InvoicesModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "datafetchup is successful");
                    Log.d(TAG, "datafetchup is successful " + new Gson().toJson(response.body()));

                    long totalAmount = 0;
                    long totalReceived = 0;
                    for (int i = 0; i < response.body().size(); i++) {
                        totalAmount = totalAmount + response.body().get(i).getAmount();
                        totalReceived = totalReceived + response.body().get(i).getReceivedAmount();
                    }
                    overdueTitle.setText("Unpaid (" + String.valueOf(response.body().size()) + ")");
                    overdueValue.setText("NRs " + String.valueOf(totalAmount - totalReceived));

                    unpaidlayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), Sales.class).putExtra("data", new Gson().toJson(response.body())).putExtra("source", strings.getUnpaid()));
                        }
                    });

                } else Log.d(TAG, "datafetch is failed");
            }

            @Override
            public void onFailure(Call<List<InvoicesModel>> call, Throwable t) {
                Log.d(TAG, "datafetch is " + t);
            }
        });
    }

    public void getTodaysSales(final TextView paid, final TextView unpaid, final TextView total, final TextView salescount, final LinearLayout todaysSalesPanel) {
        ApiService api = Retroclient.getApiService();
        Call<List<InvoicesModel>> call = api.getInvoices(companyId, branchId, "sales", "date", "", currentDate);
        call.enqueue(new Callback<List<InvoicesModel>>() {
            @Override
            public void onResponse(Call<List<InvoicesModel>> call, final Response<List<InvoicesModel>> response) {
                if (response.isSuccessful()) {
                    long paid_cash = 0;
                    long totalcash = 0;
                    for (int i = 0; i < response.body().size(); i++) {
                        paid_cash = paid_cash + response.body().get(i).getReceivedAmount();
                        totalcash = totalcash + response.body().get(i).getAmount();
                    }
                    paid.setText(String.valueOf(paid_cash));
                    unpaid.setText(String.valueOf(totalcash - paid_cash));
                    total.setText(String.valueOf(totalcash));
                    salescount.setText("Today's Sales (" + String.valueOf(response.body().size() + ")"));

                    todaysSalesPanel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), Sales.class).putExtra("data", new Gson().toJson(response.body())).putExtra("source", strings.getSales()));
                        }
                    });

                } else Log.d(TAG, "datafetch is failed");
            }

            @Override
            public void onFailure(Call<List<InvoicesModel>> call, Throwable t) {
                Log.d(TAG, "datafetch is " + t);
            }
        });
    }

    public void getTodaysPurchase(final TextView paid, final TextView unpaid, final TextView total, final TextView purchasecount, final LinearLayout todaysPurchasePanel) {
        ApiService api = Retroclient.getApiService();
        Call<List<InvoicesModel>> call = api.getInvoices(companyId, branchId, "all", "date", "", currentDate);
        call.enqueue(new Callback<List<InvoicesModel>>() {
            @Override
            public void onResponse(Call<List<InvoicesModel>> call, final Response<List<InvoicesModel>> response) {
                if (response.isSuccessful()) {
                    long paid_cash = 0;
                    long totalcash = 0;
                    for (int i = 0; i < response.body().size(); i++) {
//                        paid_cash = paid_cash + response.body().get(i).getReceivedAmount();
//                        totalcash = totalcash + response.body().get(i).getAmount();
                    }
                    paid.setText(String.valueOf(paid_cash));
                    unpaid.setText(String.valueOf(totalcash - paid_cash));
                    total.setText(String.valueOf(totalcash));
                    purchasecount.setText("Today's Purchase (" + String.valueOf(response.body().size() + ")"));

                    todaysPurchasePanel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getContext(), Sales.class).putExtra("data", new Gson().toJson(response.body())).putExtra("source", "purchase"));
                        }
                    });

                } else Log.d(TAG, "datafetch is failed");
            }

            @Override
            public void onFailure(Call<List<InvoicesModel>> call, Throwable t) {
                Log.d(TAG, "datafetch is " + t);
            }
        });
    }

}
