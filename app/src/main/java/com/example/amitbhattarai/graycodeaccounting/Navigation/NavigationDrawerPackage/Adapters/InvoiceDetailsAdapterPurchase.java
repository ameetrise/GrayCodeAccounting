package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoiceModel;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

/**
 * Created by amitbhattarai on 11/17/17.
 */

public class InvoiceDetailsAdapterPurchase extends ArrayAdapter<InvoiceModel> {
    private Context context;
    LinearLayout bg;
    ArrayList<InvoiceModel> bodyArrayList = new ArrayList<>();
    private LayoutInflater mInflater;
    String TAG = "cusan";

    public InvoiceDetailsAdapterPurchase(Context context, ArrayList<InvoiceModel> time) {
        super(context, R.layout.custom_invoice_detail_row_purchase, time);
        this.context = context;
        this.bodyArrayList = time;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "getView: ");
        View view = mInflater.inflate(R.layout.custom_invoice_detail_row_purchase, parent, false);
        TextView id=(TextView)view.findViewById(R.id.id);
        TextView description=(TextView)view.findViewById(R.id.description);
        TextView quantity=(TextView)view.findViewById(R.id.quantity);
        TextView rate=(TextView)view.findViewById(R.id.rate);
        TextView amount=(TextView)view.findViewById(R.id.amount);

        id.setText(bodyArrayList.get(position).getSn());
        description.setText(bodyArrayList.get(position).getDescription());
        quantity.setText(bodyArrayList.get(position).getQuantity());
        rate.setText(bodyArrayList.get(position).getRate());
        amount.setText(bodyArrayList.get(position).getAmount());
        return view;
    }
}
