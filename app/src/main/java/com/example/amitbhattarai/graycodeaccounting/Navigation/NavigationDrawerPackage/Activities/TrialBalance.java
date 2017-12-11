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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.TrialBalanceAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinancialReports;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrialBalance extends AppCompatActivity {
    ListAdapter trialbalanceadapter;
    ListView listtrailbalance;
    TextView company_name, date;
    Pref pref;
    GenericMethods gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_balance);
        pref = new Pref(this);
        gm = new GenericMethods();
        company_name = findViewById(R.id.tb_company_name);
        date = findViewById(R.id.tb_date);
        company_name.setText(String.valueOf(pref.getCompanyname()));
        date.setText(String.valueOf("Trial Balance as on: " + gm.getCurrentDate()));
        listtrailbalance = findViewById(R.id.trail_balance_list);
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");


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
        getTrialBalanceData();
    }

    public void getTrialBalanceData() {
        ApiService api = Retroclient.getApiService();
        Call<FinancialReports> call = api.getFinanceReport("1", "TB", "2017-12-01", "2017-12-09");
        call.enqueue(new Callback<FinancialReports>() {
            @Override
            public void onResponse(Call<FinancialReports> call, Response<FinancialReports> response) {
                if (response.isSuccessful()) {
                    Log.d("andoidsa", "onResponse: Success TB");
                    trialbalanceadapter = new TrialBalanceAdapter(TrialBalance.this, response.body().getData());
                    listtrailbalance.setAdapter(trialbalanceadapter);
                }else Log.d("andoidsa", "onResponse: Failed TB");
            }

            @Override
            public void onFailure(Call<FinancialReports> call, Throwable t) {
                Log.d("andoidsa", "onResponse: Failed TB"+t);
            }
        });
    }

    public void alertCalendar() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.calendar_layout, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }
}
