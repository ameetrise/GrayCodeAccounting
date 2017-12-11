package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.data;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.NavMenuModel;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

/**
 * Created by Amit on 7/7/17.
 */

public class Constant {

    public static ArrayList<NavMenuModel> getMenuNavigasi() {
        ArrayList<NavMenuModel> menu = new ArrayList<>();
        menu.add(new NavMenuModel("Home", R.drawable.home));
        menu.add(new NavMenuModel("Contacts", R.drawable.contacts));
        menu.add(new NavMenuModel("Sales", R.drawable.sales));
        menu.add(new NavMenuModel("Purchase", R.drawable.purchase));

        menu.add(new NavMenuModel("Reports", R.drawable.reports,
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                    add(new NavMenuModel.SubMenuModel("Balance Sheet", R.drawable.balancesheet));
                    add(new NavMenuModel.SubMenuModel("Trial Balance", R.drawable.trialbalance));
                    add(new NavMenuModel.SubMenuModel("Profit and Loss", R.drawable.profitandloss));
                }}));

        menu.add(new NavMenuModel("About us", R.drawable.aboutus));
        return menu;
    }
}
