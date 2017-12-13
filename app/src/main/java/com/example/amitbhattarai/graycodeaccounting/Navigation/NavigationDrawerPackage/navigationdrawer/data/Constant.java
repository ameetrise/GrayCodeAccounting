package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.data;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.NavMenuModel;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;

/**
 * Created by Amit on 7/7/17.
 */

public class Constant {

    public static ArrayList<NavMenuModel> getMenuNavigasi() {
        final ProjectStrings strings= new ProjectStrings();
        ArrayList<NavMenuModel> menu = new ArrayList<>();
        menu.add(new NavMenuModel(strings.getHome(), R.drawable.home));
        menu.add(new NavMenuModel(strings.getContacts(), R.drawable.contacts));
        menu.add(new NavMenuModel(strings.getSales(), R.drawable.sales));
        menu.add(new NavMenuModel(strings.getPurchase(), R.drawable.purchase));

        menu.add(new NavMenuModel(strings.getReports(), R.drawable.reports,
                new ArrayList<NavMenuModel.SubMenuModel>() {{
                    add(new NavMenuModel.SubMenuModel(strings.getBalanceSheet(), R.drawable.balancesheet));
                    add(new NavMenuModel.SubMenuModel(strings.getTrialBalance(), R.drawable.trialbalance));
                    add(new NavMenuModel.SubMenuModel(strings.getProfitandloss(), R.drawable.profitandloss));
                }}));

        menu.add(new NavMenuModel(strings.getContactus(), R.drawable.contact_us));
        menu.add(new NavMenuModel(strings.getAboutus(), R.drawable.aboutus));
        return menu;
    }
}
