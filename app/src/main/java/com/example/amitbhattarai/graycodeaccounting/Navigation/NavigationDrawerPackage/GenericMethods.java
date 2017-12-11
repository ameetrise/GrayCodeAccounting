package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amitbhattarai on 11/22/17.
 */

public class GenericMethods {
    public int addingNumbers(int num1, int num2) {
        return num1 + num2;
    }

    public void settingStringsToListView(Context context, ListView listview, ArrayList<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1, items);
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
