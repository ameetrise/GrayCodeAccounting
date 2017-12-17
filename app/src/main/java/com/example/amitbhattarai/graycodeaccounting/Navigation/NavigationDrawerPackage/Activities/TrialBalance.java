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

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.TrialBalanceAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinancialReports;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.NonScrollListView;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
import com.example.amitbhattarai.graycodeaccounting.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrialBalance extends AppCompatActivity {
    ListAdapter trialbalanceadapter;
    NonScrollListView listtrailbalance;
    TextView company_name, date;
    Pref pref;
    ProjectStrings strings;
    GenericMethods gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_balance);
        pref = new Pref(this);
        strings = new ProjectStrings();
        gm = new GenericMethods();
        company_name = findViewById(R.id.tb_company_name);
        date = findViewById(R.id.tb_date);
        company_name.setText(String.valueOf(pref.getCompanyname()));
        date.setText(String.valueOf("Trial Balance as on: " + gm.getCurrentDate()));
        listtrailbalance = findViewById(R.id.trail_balance_list);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
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
        getTrialBalanceData(pref.getCompanyid(), strings.getTB(), strings.getFromdate(), gm.getCurrentDate());
    }

    public void getTrialBalanceData(String cid, String type, String frmdate, String todte) {
        ApiService api = Retroclient.getApiService();
        Call<FinancialReports> call = api.getFinanceReport(cid, type, frmdate, todte);
        call.enqueue(new Callback<FinancialReports>() {
            @Override
            public void onResponse(Call<FinancialReports> call, Response<FinancialReports> response) {
                if (response.isSuccessful()) {
                    Log.d("andoidsa", "onResponse: Success TB");
                    trialbalanceadapter = new TrialBalanceAdapter(TrialBalance.this, response.body().getData());
                    listtrailbalance.setAdapter(trialbalanceadapter);
                } else Log.d("andoidsa", "onResponse: Failed TB");
            }

            @Override
            public void onFailure(Call<FinancialReports> call, Throwable t) {
                Log.d("andoidsa", "onResponse: Failed TB" + t);
            }
        });
    }

    int datecount = 0;
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
                    Toast.makeText(TrialBalance.this, "Select to date.", Toast.LENGTH_SHORT).show();
                    pick.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
                            todate = String.valueOf(datePicker.getYear()) + "-" + String.valueOf(datePicker.getMonth() + 1) + "-"
                                    + String.valueOf(datePicker.getDayOfMonth());
                            Log.d("yocheck", "onClick: " + "From date " + fromdate + " Todate " + todate);
                            getTrialBalanceData(pref.getCompanyid(), strings.getTB(), fromdate, todate);
                            date.setText("Trial Balance from " + fromdate + " to " + todate);
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
