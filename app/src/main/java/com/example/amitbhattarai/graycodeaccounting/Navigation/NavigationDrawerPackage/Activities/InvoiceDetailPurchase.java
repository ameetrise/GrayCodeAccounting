package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.InvoiceDetailsAdapterSales;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoiceModel;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

public class InvoiceDetailPurchase extends AppCompatActivity {
    ListView invoiceList;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_details_purchase);
        invoiceList = (ListView) findViewById(R.id.invoice_detail_list);
        ArrayList<InvoiceModel> model = new ArrayList<>();
        model.add(new InvoiceModel("1", "Eagle copies", "1200", "3,40,000", "6,00,000"));
        model.add(new InvoiceModel("2", "Natraj Pencils", "1200", "3,40,000", "18,00,000"));
        model.add(new InvoiceModel("3", "Max writer pen", "1200", "3,40,000", "6,00,000"));
        model.add(new InvoiceModel("4", "One Plus 5 covers (for new model)", "1200", "3,40,000", "5,30,000"));
        model.add(new InvoiceModel("5", "Erasers", "1200", "3,40,000", "3,30,000"));
        model.add(new InvoiceModel("6", "Eagle copies", "1200", "3,40,000", "6,00,000"));
        model.add(new InvoiceModel("7", "Natraj Pencils", "1200", "3,40,000", "18,00,000"));
        model.add(new InvoiceModel("8", "Max writer pen", "1200", "3,40,000", "6,00,000"));
        model.add(new InvoiceModel("9", "One Plus 5 covers", "1200", "3,40,000", "5,30,000"));
        model.add(new InvoiceModel("10", "Erasers", "1200", "3,40,000", "3,30,000"));
        adapter = new InvoiceDetailsAdapterSales(this, model);
        invoiceList.setAdapter(adapter);
    }
}
