package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

public class Purchase extends AppCompatActivity {

    ListView saleslistview;
    LinearLayout sales_type_spinner;
    ListAdapter salesadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        saleslistview = (ListView) findViewById(R.id.saleslist);
        sales_type_spinner = (LinearLayout) findViewById(R.id.down_spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String> items = new ArrayList<>();
        items.add("8,000.00");
        items.add("12,000.00");
        items.add("15,600.00");
        items.add("18,300.00");
        items.add("13,900.00");
        items.add("11,300.00");

        ArrayList<String> spinitems = new ArrayList<>();
        spinitems.add("Invoices");
        //salesadapter = new SalesAdapter(this, items);
        //saleslistview.setAdapter(salesadapter);
        saleslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(Purchase.this,InvoiceDetailPurchase.class));
            }
        });
        sales_type_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sales_page_menu, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ArrayList<String> branchlist = new ArrayList<>();
        branchlist.add("Branch 1");
        branchlist.add("Branch 2");
        branchlist.add("Branch 3");
        switch (item.getItemId()) {
            case R.id.action_branches:
                showAlert(branchlist);
                break;

            case R.id.action_calendar:
                alertCalendar();
                break;

            case R.id.action_search:
//                Toast.makeText(this, currentView, Toast.LENGTH_SHORT).show();
//                Log.d("checkclickcount", "showSearch: " + String.valueOf(clickToggle));
//                if (clickToggle % 2 != 0) {
//                    showSearch();
//                } else {
//                    search_layout.removeAllViews();
//                    clickToggle = clickToggle + 1;
//                    currentView = "spinner";
//                }
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    public void showAlert() {
        ArrayList<String> itemlist = new ArrayList<>();
        itemlist.add("All invoices");
        itemlist.add("overdue");
        itemlist.add("paid");
        itemlist.add("unpaid");
        itemlist.add("partial");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_list, null);
        ListView alertList = dialogView.findViewById(R.id.alertlist);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemlist);
        alertList.setAdapter(itemsAdapter);

        dialogBuilder.setView(dialogView);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    public void showAlert(ArrayList<String> itemlist) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_list, null);
        ListView alertList = dialogView.findViewById(R.id.alertlist);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemlist);
        alertList.setAdapter(itemsAdapter);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
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
