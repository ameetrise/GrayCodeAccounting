package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities.MainActivity;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities.Sales;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amitbhattarai on 12/11/17.
 */

public class ExpNavAdapter extends BaseExpandableListAdapter {
    private Context mcontext;
    TextView textparent;
    String parent_text;
    private List<String> parent;
    private HashMap<String, List<String>> bind_and_display;

    public ExpNavAdapter(Context context, List<String> listDataHeader,
                         HashMap<String, List<String>> listChildData) {
        this.mcontext = context;
        this.parent = listDataHeader;
        this.bind_and_display = listChildData;
    }

    @Override
    public int getGroupCount() {
        return this.parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.bind_and_display.get(this.parent.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.bind_and_display.get(this.parent.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        parent_text = (String) getGroup(groupPosition);
        LayoutInflater parInflater = (LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = parInflater.inflate(R.layout.nav_menu_item, null);
        textparent = convertView.findViewById(R.id.nav_menu_item_title);
        ImageView arrow = convertView.findViewById(R.id.nav_menu_item_arrow);

        if (!isExpanded) {
            arrow.setImageResource(R.drawable.downarrow);
        } else if (isExpanded) {
            arrow.setImageResource(R.drawable.uparrow);
        }

        textparent.setText(parent_text);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String child_text = (String) getChild(groupPosition, childPosition);
        LayoutInflater infalInflater = (LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = infalInflater.inflate(R.layout.nav_submenu_item, null);
        LinearLayout bg = convertView.findViewById(R.id.bg);
        TextView textchild = convertView.findViewById(R.id.main_nav_submenu_item_title);
        textchild.setText(child_text);

        bg.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showAlert();
                return false;
            }
        });

        bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void showAlert() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Remove");
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mcontext);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View dialogView = inflater.inflate(R.layout.custom_alert_list, null);
        ListView alertList = dialogView.findViewById(R.id.alertlist);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(mcontext, android.R.layout.simple_list_item_1, list);
        alertList.setAdapter(itemsAdapter);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        alertList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mcontext, "Removed ", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }
}