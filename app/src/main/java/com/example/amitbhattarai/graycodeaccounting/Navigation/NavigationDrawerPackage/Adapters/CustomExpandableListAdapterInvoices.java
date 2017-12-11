package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.CashAndBankData;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by amitbhattarai on 11/27/17.
 */

public class CustomExpandableListAdapterInvoices extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    String fromClass;
    private HashMap<String,  List<CashAndBankData>> _listDataChild;

    public CustomExpandableListAdapterInvoices(Context context, List<String> listDataHeader,
                                               HashMap<String, List<CashAndBankData>> listChildData, String fromclass) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.fromClass=fromclass;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String name="null";
        String amount="null";
        final CashAndBankData childText =(CashAndBankData)  getChild(groupPosition, childPosition);
        if(fromClass.equals("Sundry Debtors")||fromClass.equals("Sundry Creditors")){
            name = String.valueOf(childText.getSubLedgerName());
        }else if(fromClass.equals("Cash in Hand")||fromClass.equals("Bank A/C")) {
            name = String.valueOf(childText.getAccountChartName());
        }
        amount= String.valueOf(childText.getAmount());
        if(name=="null"){
            name="-";
        }if(amount=="null"){
            amount="-";
        }
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView textTitle = (TextView) convertView.findViewById(R.id.lblListItemTitle);
        TextView textValue = (TextView) convertView.findViewById(R.id.lblListItemValue);

        textTitle.setText(name);
        textValue.setText("NRs  "+amount);


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        ExpandableListView eLV = (ExpandableListView) parent;
        eLV.expandGroup(groupPosition);

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
