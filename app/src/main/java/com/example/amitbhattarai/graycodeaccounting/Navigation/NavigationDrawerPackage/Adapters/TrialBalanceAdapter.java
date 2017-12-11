package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.FinanceData;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.List;

/**
 * Created by amitbhattarai on 11/17/17.
 */

public class TrialBalanceAdapter extends ArrayAdapter<FinanceData> {
    private Context context;
    List<FinanceData> bodyArrayList;
    private LayoutInflater mInflater;
    String TAG = "cusan";

    public TrialBalanceAdapter(Context context, List<FinanceData> data) {
        super(context, R.layout.custom_trial_balance_layout, data);
        this.context = context;
        this.bodyArrayList = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "getView: ");
        View view = mInflater.inflate(R.layout.custom_trial_balance_layout, parent, false);
        TextView tb_transaction_name = view.findViewById(R.id.tb_transaction_name);
        TextView tb_first_amount = view.findViewById(R.id.tb_first_amount);
        TextView tb_second_amount = view.findViewById(R.id.tb_second_amount);
        tb_transaction_name.setText(String.valueOf(bodyArrayList.get(position).getAccountChartName()));
        if (bodyArrayList.get(position).getGroupOrLedger() == 1) {
            tb_transaction_name.setTypeface(null, Typeface.BOLD);
        } else
            tb_second_amount.setText(String.valueOf(bodyArrayList.get(position).getAmount()));
        return view;
    }
}
