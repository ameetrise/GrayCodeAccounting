package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinanceData;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by amitbhattarai on 11/21/17.
 */

public class BalanceSheetLiabilitiesListAdapter extends ArrayAdapter<FinanceData> {

    Context context;
    LayoutInflater mInflater;
    List<FinanceData> list;

    public BalanceSheetLiabilitiesListAdapter(Context context, List<FinanceData> arrayList) {
        super(context, R.layout.custom_balance_sheet_liabilities, arrayList);
        this.context = context;
        this.list = arrayList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.custom_balance_sheet_liabilities, parent, false);
        TextView transaction_name = view.findViewById(R.id.bs_transaction_name);
        TextView first_amount = view.findViewById(R.id.bs_first_amount);
        TextView second_amount = view.findViewById(R.id.bs_second_amount);
        transaction_name.setText(String.valueOf(list.get(position).getAccountChartName()));
        if (list.get(position).getGroupOrLedger() == 1) {
            transaction_name.setTypeface(null, Typeface.BOLD);
        } else {
            second_amount.setText(String.valueOf(list.get(position).getAmount()));
        }
        return view;
    }
}
