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

public class ProfitAndLoss extends AppCompatActivity {
    ListView listProfitandLoss;
    ListAdapter adapter;
    ListView listTotalProfitandLoss;
    TextView company_name,date;
    Pref pref;
    GenericMethods gm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_and_loss);
        pref= new Pref(this);
        gm = new GenericMethods();
        listProfitandLoss = findViewById(R.id.profitandlosslist);
        listTotalProfitandLoss = findViewById(R.id.total_profit_loss);
        ArrayList<String> items = new ArrayList<>();
        items.add("a");
        items.add("a");
        items.add("a");
        items.add("a");
        items.add("a");
        items.add("a");
        company_name= findViewById(R.id.pl_company_name);
        date = findViewById(R.id.pl_date);
        company_name.setText(String.valueOf(pref.getCompanyname()));
        date.setText(String.valueOf(gm.getCurrentDate()+" to "+gm.getCurrentDate()));

        //listProfitandLoss.setAdapter(adapter);

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
        getProfitAndLossBalanceData();
    }

    public void getProfitAndLossBalanceData() {
        ApiService api = Retroclient.getApiService();
        Call<FinancialReports> call = api.getFinanceReport("1", "PL", "2017-12-01", "2017-12-09");
        call.enqueue(new Callback<FinancialReports>() {
            @Override
            public void onResponse(Call<FinancialReports> call, Response<FinancialReports> response) {
                if (response.isSuccessful()) {
                    Log.d("andoidsa", "onResponse: Success PL");
                    adapter = new TrialBalanceAdapter(ProfitAndLoss.this, response.body().getData());
                    listTotalProfitandLoss.setAdapter(adapter);
                }else Log.d("andoidsa", "onResponse: Failed PL");
            }

            @Override
            public void onFailure(Call<FinancialReports> call, Throwable t) {
                Log.d("andoidsa", "onResponse: Failed PL "+t);
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
