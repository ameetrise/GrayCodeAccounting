package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ImageTextModel;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

/**
 * Created by amitbhattarai on 11/17/17.
 */

public class ImageTextAdapter extends ArrayAdapter<ImageTextModel> {
    private Context context;
    ArrayList<ImageTextModel> bodyArrayList = new ArrayList<>();
    private LayoutInflater mInflater;
    String TAG = "cusan";

    public ImageTextAdapter(Context context, ArrayList<ImageTextModel> time) {
        super(context, R.layout.custom_layout_for_invoice, time);
        this.context = context;
        this.bodyArrayList = time;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "getView: ");
        View view = mInflater.inflate(R.layout.imageandtext, parent, false);
        ImageView image = view.findViewById(R.id.submenuicon);
        TextView text= view.findViewById(R.id.main_nav_submenu_item_title);
        image.setImageResource(bodyArrayList.get(position).getImage());
        text.setText(bodyArrayList.get(position).getText());
        return view;
    }
}
