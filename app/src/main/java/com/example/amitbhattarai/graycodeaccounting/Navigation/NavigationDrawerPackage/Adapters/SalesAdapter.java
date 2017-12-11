package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities.Sales;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoicesModel;
import com.example.amitbhattarai.graycodeaccounting.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by amitbhattarai on 11/17/17.
 */

public class SalesAdapter extends ArrayAdapter<InvoicesModel> {
    private Context context;
    List<InvoicesModel> bodyArrayList = new ArrayList<>();
    List<InvoicesModel> orig = new ArrayList<>();
    private LayoutInflater mInflater;
    String TAG = "cusan";


    public SalesAdapter(Sales context, List<InvoicesModel> list) {
        super(context, R.layout.custom_layout_for_invoice, list);
        this.context = context;
        this.bodyArrayList = list;
        Log.d(TAG, "SalesAdapter: asdfasf " + new Gson().toJson(list));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "getView: ");
        View view = mInflater.inflate(R.layout.custom_layout_for_invoice, parent, false);
        TextView invoiceNumber = view.findViewById(R.id.invoiceNumber);
        TextView invoiceDate = view.findViewById(R.id.invoiceDate);
        TextView invoiceDueDate = view.findViewById(R.id.invoiceDueDate);
        TextView invoiceName = view.findViewById(R.id.invoiceName);
        TextView invoiceAmount = view.findViewById(R.id.invoiceAmount);
        TextView invoiceDueAmount = view.findViewById(R.id.invoiceDueAmount);
        TextView invoiceOverDueDays = view.findViewById(R.id.invoiceOverduedays);
        ImageView invoicesms = view.findViewById(R.id.invoiceSMS);
        ImageView invoiceemail = view.findViewById(R.id.invoiceEmail);
        LinearLayout overdueview = view.findViewById(R.id.overdueview);
        InvoicesModel list = bodyArrayList.get(position);
        invoiceNumber.setText(String.valueOf("Invoice No: " + list.getInvoiceNo()));
        invoiceName.setText(String.valueOf("Name: " + list.getAssociateName()));
        invoiceAmount.setText(String.valueOf("NRs " + list.getAmount()));
        invoiceDate.setText("Date: " + String.valueOf(getnStrings(list.getInvoiceDate(), 10)));
        invoiceDueDate.setText("Due Date: " + String.valueOf(getnStrings(list.getDueDate(), 10)));
        int days = daysBetweenDates(String.valueOf(list.getInvoiceDate()), String.valueOf(list.getDueDate()));
        if (days < 0) {
            overdueview.setVisibility(View.VISIBLE);
            invoiceOverDueDays.setText("Overdue day(s): " + String.valueOf(Math.abs(days)));  //maths.abs(-6) ==6
        } else {
            overdueview.setVisibility(View.GONE);
        }
        return view;
    }

    public String getnStrings(String string, int charcount) {
        String upToNCharacters = string.substring(0, Math.min(string.length(), charcount));
        return upToNCharacters;
    }

    public int daysBetweenDates(String sfromDate, String stoDate) {
        Date fromDate = null;
        Date toDate = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fromDate = df.parse(sfromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            toDate = df.parse(stoDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) ((fromDate.getTime() - toDate.getTime()) / (60 * 60 * 24 * 1000));
    }
//    public void updateAdapter(List<InvoicesModel> newlist) {
//        bodyArrayList.clear();
//        bodyArrayList.addAll(newlist);
//        this.notifyDataSetChanged();
//    }
}
