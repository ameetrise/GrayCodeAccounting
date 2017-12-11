package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Amit on 7/7/17.
 */

public class SubTitleViewHolder extends ChildViewHolder {
    private TextView subTitleTextView;
    private ImageView images;

    public SubTitleViewHolder(View itemView) {
        super(itemView);
        subTitleTextView = (TextView) itemView.findViewById(R.id.main_nav_submenu_item_title);
        images = (ImageView) itemView.findViewById(R.id.submenuicon);
    }

    public void setSubTitletName(String name,int image) {
        images.setImageResource(image);
        subTitleTextView.setText(name);
    }
}