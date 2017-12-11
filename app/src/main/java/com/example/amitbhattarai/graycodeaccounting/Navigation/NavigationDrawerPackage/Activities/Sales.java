package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.SalesAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoicesModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
import com.example.amitbhattarai.graycodeaccounting.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sales extends AppCompatActivity {
    ListView saleslistview;
    LinearLayout sales_type_spinner;
    ListAdapter salesadapter;
    LinearLayout search_layout;
    String TAG = "checktags";
    String source;
    ProjectStrings strings;
    TextView type_spinner;
    int position;
    String companyId;
    String currentDate;
    String branchId;
    GenericMethods gm;
    Pref pref;
    List<InvoicesModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        type_spinner = findViewById(R.id.sales_type_spinner);
        strings = new ProjectStrings();
        pref = new Pref(this);
        gm = new GenericMethods();
        currentDate = gm.getCurrentDate();
        companyId = pref.getCompanyid();
        Log.d(TAG, "onCreate:CID " + companyId);
        branchId = strings.getBranchId();
        search_layout = findViewById(R.id.down_spinner);
        saleslistview = (ListView) findViewById(R.id.saleslist);
        saleslistview.setTextFilterEnabled(true);
        sales_type_spinner = (LinearLayout) findViewById(R.id.down_spinner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        source = getIntent().getStringExtra("source");


        if (source.equals(strings.getOverdue())) {
            type_spinner.setText(strings.getOverdue());
            String data = getIntent().getStringExtra("data");
            Log.d(TAG, "onCreate:ss " + String.valueOf(data));
            Gson gson = new Gson();
            Type type = new TypeToken<List<InvoicesModel>>() {
            }.getType();
            list = gson.fromJson(data, type);
            salesadapter = new SalesAdapter(this, list);
            saleslistview.setAdapter(salesadapter);
        } else if (source.equals(strings.getUnpaid())) {
            type_spinner.setText(strings.getUnpaid());
            String data = getIntent().getStringExtra("data");
            Log.d(TAG, "onCreate:ss " + String.valueOf(data));
            Gson gson = new Gson();
            Type type = new TypeToken<List<InvoicesModel>>() {
            }.getType();
            list = gson.fromJson(data, type);

            salesadapter = new SalesAdapter(this, list);
            saleslistview.setAdapter(salesadapter);
        } else if (source.equals(strings.getAllinvoices())) {
            String data = getIntent().getStringExtra("data");
            type_spinner.setText(source);
            Log.d(TAG, "onCreate:ss " + String.valueOf(data));
            Gson gson = new Gson();
            Type type = new TypeToken<List<InvoicesModel>>() {
            }.getType();
            list = gson.fromJson(data, type);
            salesadapter = new SalesAdapter(this, list);
            saleslistview.setAdapter(salesadapter);
        } else if (source.equals(strings.getSales())) {
            String data = getIntent().getStringExtra("data");
            type_spinner.setText(source);
            Log.d(TAG, "onCreate:ss " + String.valueOf(data));
            Gson gson = new Gson();
            Type type = new TypeToken<List<InvoicesModel>>() {
            }.getType();
            list = gson.fromJson(data, type);
            salesadapter = new SalesAdapter(this, list);
            saleslistview.setAdapter(salesadapter);
        } else if (source.equals(strings.getPaid())) {
            String data = getIntent().getStringExtra("data");
            type_spinner.setText(source);
            Log.d(TAG, "onCreate:ss " + String.valueOf(data));
            Gson gson = new Gson();
            Type type = new TypeToken<List<InvoicesModel>>() {
            }.getType();
            list = gson.fromJson(data, type);
            salesadapter = new SalesAdapter(this, list);
            saleslistview.setAdapter(salesadapter);
        }

        saleslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(Sales.this, InvoiceDetailSales.class));
            }
        });

        sales_type_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> itemlist = new ArrayList<>();
                itemlist.add(strings.getAllinvoices());
                itemlist.add(strings.getOverdue());
                itemlist.add(strings.getPaid());
                itemlist.add(strings.getUnpaid());
                itemlist.add(strings.getPartial());
                showAlert(itemlist, position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.sales_page_menu, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sales_page_menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                type_spinner.setText(strings.getAllinvoices());
                filterList(s);
                return false;
            }
        });
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
                showAlert(branchlist, position);
                break;

            case R.id.action_calendar:
                alertCalendar();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void showAlert(final ArrayList<String> itemlist, int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_list, null);
        final ListView alertList = dialogView.findViewById(R.id.alertlist);
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemlist);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        alertList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                source = itemlist.get(i);
                type_spinner.setText(itemlist.get(i));
                Log.d(TAG, "onItemClickss: " + source.replace(" ", ""));
                if (source.equals(strings.getAllinvoices())) {
                    getSalesType("all", "date", "", currentDate);
                }
                if (source.equals(strings.getOverdue())) {
                    getSalesType("overdue", "upto", "", currentDate);
                }
                if (source.equals(strings.getPaid())) {
                    getSalesType("paid", "upto", "", currentDate);
                }
                if (source.equals(strings.getUnpaid())) {
                    getSalesType("unpaid", "upto", "", currentDate);
                }
                if (source.equals(strings.getPartial())) {
                    getSalesType("partial", "upto", "", currentDate);
                }

                //all invoices type all, date type date, from - , to todays date
                //overdue type overdue, date type upto, from - , to todays date
                //paid type paid, date type upto, from - , to todays date
                //unpaid type unpaid, date type upto, from - , to todays date
                //partial type partial, date type upto, from - , to todays date

                Log.d(TAG, "onItemClickstring: " + itemlist.get(i).replace(" ", ""));
                getSalesType(itemlist.get(i).replace(" ", ""), "", "", currentDate);
                alertDialog.dismiss();
            }
        });
        alertList.setAdapter(itemsAdapter);
        alertList.setSelection(position);
    }

    int datecount = 0;
    String fromdate, todate = currentDate;

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
                    Toast.makeText(Sales.this, "Select to date.", Toast.LENGTH_SHORT).show();
                    pick.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatePicker datePicker = dialogView.findViewById(R.id.datePicker);
                            todate = String.valueOf(datePicker.getYear()) + "-" + String.valueOf(datePicker.getMonth() + 1) + "-"
                                    + String.valueOf(datePicker.getDayOfMonth());
                            Log.d(TAG, "onClickol: " + source);
                            if (source.equals(strings.getAllinvoices())) {
                                getSalesType("all", "range", fromdate, todate);
                            }
                            if (source.equals(strings.getOverdue())) {
                                getSalesType("overdue", "range", fromdate, todate);
                            }
                            if (source.equals(strings.getPaid())) {
                                getSalesType("paid", "range", fromdate, todate);
                            }
                            if (source.equals(strings.getUnpaid())) {
                                getSalesType("unpaid", "range", fromdate, todate);
                            }
                            if (source.equals(strings.getPartial())) {
                                getSalesType("partial", "range", fromdate, todate);
                            }

                            alertDialog.dismiss();
                            Log.d(TAG, "onClickthis: To " + todate);
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

    public void getSalesType(String type, String datetype, String from, String to) {
        Log.d(TAG, "getSalesTypes: Type " + type + "\n" + " datetype " + datetype + "\n" + "fromdate " + from + "\ntodate " + to);
        ApiService api = Retroclient.getApiService();
        Log.d(TAG, "getSalesType: " + type);
        Call<List<InvoicesModel>> call = api.getInvoices(companyId, branchId, type, datetype, from, to);
        call.enqueue(new Callback<List<InvoicesModel>>() {
            @Override
            public void onResponse(Call<List<InvoicesModel>> call, final Response<List<InvoicesModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponsesuccesst: " + new Gson().toJson(response.body()) + "\n");
                    salesadapter = new SalesAdapter(Sales.this, response.body());
                    saleslistview.setAdapter(salesadapter);
                } else Log.d(TAG, "datafetch is failed");
            }

            @Override
            public void onFailure(Call<List<InvoicesModel>> call, Throwable t) {
                Log.d(TAG, "datafetch is " + t);
            }
        });
    }

    public void filterList(String text) {
        Log.d(TAG, "filterListtext: " + text);
        List<InvoicesModel> filteredList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAssociateName().contains(text)) {
                filteredList.add(list.get(i));
            }
            salesadapter = new SalesAdapter(Sales.this, filteredList);
            saleslistview.setAdapter(salesadapter);
        }
    }
}
