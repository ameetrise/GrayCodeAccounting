package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.BalanceSheetLiabilitiesListAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinanceData;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinancialReports;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.NonScrollListView;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BalanceSheet extends AppCompatActivity {
    NonScrollListView list_liabilities;
    NonScrollListView list_assets;
    ListAdapter adapter_liabilities;
    TextView company_name, date, liabilities_total;
    Pref pref;
    GenericMethods gm;
    ProjectStrings strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_sheet);
        pref = new Pref(this);
        strings= new ProjectStrings();
        gm = new GenericMethods();
        list_liabilities = findViewById(R.id.balance_sheet_liabilities_list);
        list_assets = findViewById(R.id.balance_sheet_assets_list);
        company_name = findViewById(R.id.balance_sheet_company_name);
        liabilities_total = findViewById(R.id.bs_liabilities_total);
        date = findViewById(R.id.balance_sheet_date);
        company_name.setText(pref.getCompanyname());
        date.setText("Balance Sheet as on: " + (String.valueOf(gm.getCurrentDate())));
        list_assets.setAdapter(adapter_liabilities);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_calendar:
                                    alertCalendar();
                                    break;
                                case R.id.action_back:
                                    finish();
                            }
                            return false;
                        }
                    });
        }
        List<FinanceData> list= new ArrayList<>();

        getBalanceSheetData(pref.getCompanyid(),strings.getBS(),strings.getFromdate(),gm.getCurrentDate());
    }

    public void getBalanceSheetData(String companyId,String type,String fromDate,String toDate) {
        ApiService api = Retroclient.getApiService();
        Call<FinancialReports> call = api.getFinanceReport(companyId, type, fromDate,toDate);
        call.enqueue(new Callback<FinancialReports>() {
            @Override
            public void onResponse(Call<FinancialReports> call, Response<FinancialReports> response) {
                if (response.isSuccessful()) {
                    double totaoAmount = 0;
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        totaoAmount = totaoAmount + response.body().getData().get(i).getAmount();
                    }

                    liabilities_total.setText(String.valueOf(totaoAmount));
                    adapter_liabilities = new BalanceSheetLiabilitiesListAdapter(BalanceSheet.this, response.body().getData());
                    list_liabilities.setAdapter(adapter_liabilities);
                } else Log.d("andoidsa", "onResponse: Failed BS");
            }

            @Override
            public void onFailure(Call<FinancialReports> call, Throwable t) {
                Log.d("andoidsa", "onResponse: Failed BS" + t);
            }
        });
    }
    int datecount=0;
    String fromdate;
    String todate;
    public void alertCalendar() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.calendar_layout, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        Button cancel = dialogView.findViewById(R.id.btnCanel);
        final Button pick = dialogView.findViewById(R.id.pickBtn);
        if (datecount == 0) {
            Toast.makeText(this, "Select from date.", Toast.LENGTH_SHORT).show();

            pick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
                    fromdate = String.valueOf(datePicker.getYear()) + "-" + String.valueOf(datePicker.getMonth() + 1) + "-" + String.valueOf(datePicker.getDayOfMonth());
                    Toast.makeText(BalanceSheet.this, "Select to date.", Toast.LENGTH_SHORT).show();
                    pick.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
                            todate = String.valueOf(datePicker.getYear()) + "-" + String.valueOf(datePicker.getMonth() + 1) + "-"
                                    + String.valueOf(datePicker.getDayOfMonth());
                            Log.d("yocheck", "onClick: "+"From date "+fromdate+" Todate "+todate);
                            date.setText("Balane Sheet from "+fromdate+" to "+todate);
                            getBalanceSheetData(pref.getCompanyid(),strings.getBS(),fromdate,todate);
                            alertDialog.dismiss();
                        }
                    });
                }
            });
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}
