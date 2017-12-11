package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

/**
 * Created by amitbhattarai on 11/17/17.
 */

public class PurchaseAdapter extends ArrayAdapter<String> {
    private Context context;
    LinearLayout bg;
    ArrayList<String> bodyArrayList = new ArrayList<>();
    private LayoutInflater mInflater;
    String TAG = "cusan";

    public PurchaseAdapter(Context context, ArrayList<String> time) {
        super(context, R.layout.custom_layout_for_invoice, time);
        this.context = context;
        this.bodyArrayList = time;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "getView: ");
        View view = mInflater.inflate(R.layout.custom_layout_for_invoice, parent, false);
        bg=(LinearLayout) view.findViewById(R.id.sales_custom_bg);
        TextView title = (TextView) view.findViewById(R.id.tvss);
        title.setText(bodyArrayList.get(position));
        return view;
    }
}
