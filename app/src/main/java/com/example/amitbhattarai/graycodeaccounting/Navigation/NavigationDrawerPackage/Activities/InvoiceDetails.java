package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.CustomExpandableListAdapterInvoices;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CashAndBankData;
import com.example.amitbhattarai.graycodeaccounting.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceDetails extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String,  List<CashAndBankData>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details);

        String data = getIntent().getStringExtra("data");
        String fromclass = getIntent().getStringExtra("class");

        List<CashAndBankData> list;
        Gson gson = new Gson();
        Type type = new TypeToken<List<CashAndBankData>>() {
        }.getType();
        list = gson.fromJson(data, type);
        Log.d("thistag", "onCreate: " + new Gson().toJson(list));
        Log.d("thistag", "onCreate: " +data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Accounts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepareListData(list);
        expListView = findViewById(R.id.expandableDetailView);

        listAdapter = new CustomExpandableListAdapterInvoices(this, listDataHeader, listDataChild,fromclass);
        expListView.setAdapter(listAdapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void prepareListData( List<CashAndBankData> list) {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        list.add(new CashAndBankData());
        listDataHeader.add(getIntent().getStringExtra("class"));
        listDataChild.put(listDataHeader.get(0), list);
    }
}
