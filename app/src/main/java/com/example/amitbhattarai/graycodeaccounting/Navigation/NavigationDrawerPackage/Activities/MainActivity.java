package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.ExpNavAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.ImageTextAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ImageTextModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.fragment.HomeFragment;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.data.Constant;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.NavMenuAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.NavMenuModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.SubTitle;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.navigationdrawer.TitleMenu;
import com.example.amitbhattarai.graycodeaccounting.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavMenuAdapter.MenuItemClickListener {

    TextView tv_nav_company_name, tv_nav_company_address;
    ImageView nav_company_logo;
    Toolbar toolbar;
    Pref pref;
    String TAG = "mainactivitylog";
    DrawerLayout drawer;
    ArrayList<NavMenuModel> menu;
    String companyId;
    String branchId;
    ExpandableListView company_list;
    String currentDate;
    ExpNavAdapter adapter;
    ProjectStrings strings;
    List<String> listDataHeader;
    List<String> listChild = new ArrayList<>();
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        drawer = findViewById(R.id.main_layout);
        branchId = "1";
        strings = new ProjectStrings();
        companyId = new Pref(this).getCompanyid();
        currentDate = new GenericMethods().getCurrentDate();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        View nav_header = findViewById(R.id.main_nav_header);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        pref = new Pref(this);
        tv_nav_company_name = nav_header.findViewById(R.id.nav_company_name);
        tv_nav_company_address = nav_header.findViewById(R.id.nav_company_address);
        company_list = findViewById(R.id.nav_company_list);

        prepareListData(pref.getSelectedcompany());
        setExpAdapter();

        nav_company_logo = nav_header.findViewById(R.id.company_logo);
        tv_nav_company_address.setText(String.valueOf(pref.getCompanyaddress()));
        Log.d(TAG, "onCreateas: " + pref.getCompanyList());
        tv_nav_company_name.setText(pref.getCompanyname());
        setToolbar();
        setNavigationDrawerMenu();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.navicon);
        setSupportActionBar(toolbar);
    }

    private void setNavigationDrawerMenu() {
        NavMenuAdapter adapter = new NavMenuAdapter(this, getMenuList(), this);
        RecyclerView navMenuDrawer = findViewById(R.id.main_nav_menu_recyclerview);
        navMenuDrawer.setAdapter(adapter);
        navMenuDrawer.setLayoutManager(new LinearLayoutManager(this));
        navMenuDrawer.setAdapter(adapter);
        adapter.selectedItemParent = menu.get(0).menuTitle;
        onMenuItemClick(adapter.selectedItemParent);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }


    private List<TitleMenu> getMenuList() {
        List<TitleMenu> list = new ArrayList<>();
        menu = Constant.getMenuNavigasi();
        for (int i = 0; i < menu.size(); i++) {
            ArrayList<SubTitle> subMenu = new ArrayList<>();
            if (menu.get(i).subMenu.size() > 0) {
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    subMenu.add(new SubTitle(menu.get(i).subMenu.get(j).subMenuTitle, menu.get(i).subMenu.get(j).images));
                }
            }
            list.add(new TitleMenu(menu.get(i).menuTitle, subMenu, menu.get(i).menuIconDrawable));
        }
        return list;
    }

    @Override
    public void onMenuItemClick(String itemString) {
        if (itemString.equals(strings.getSales())) {

        } else if (itemString.equals(strings.getPurchase())) {
            startActivity(new Intent(this, Purchase.class));
        } else if (itemString.equals(strings.getContacts())) {
            startActivity(new Intent(this, LoginActivity.class));

        } else if (itemString.equals(strings.getTrialBalance())) {
            startActivity(new Intent(this, TrialBalance.class));
        } else if (itemString.equals(strings.getHome())) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_content, new HomeFragment())
                    .commit();
        } else if (itemString.equals(strings.getBalanceSheet())) {
            startActivity(new Intent(this, BalanceSheet.class));
        } else if (itemString.equals(strings.getProfitandloss())) {
            startActivity(new Intent(this, ProfitAndLoss.class));
        } else if (itemString.equals(strings.getAboutus())) {
            Intent httpIntent = new Intent(Intent.ACTION_VIEW);
            httpIntent.setData(Uri.parse("http://www.graycode.com.np"));
            startActivity(httpIntent);
        } else if (itemString.equals(strings.getContactus())) {
            ArrayList<ImageTextModel> branchlist = new ArrayList<>();
            branchlist.add(new ImageTextModel("Phone", R.drawable.phone));
            branchlist.add(new ImageTextModel("Email", R.drawable.email));
            branchlist.add(new ImageTextModel("SMS", R.drawable.sms));
            showContactUsAlert(branchlist);
        }
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.main_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void showContactUsAlert(ArrayList<ImageTextModel> itemlist) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_alert_list, null);
        ListView alertList = dialogView.findViewById(R.id.alertlist);

        ListAdapter itemsAdapter = new ImageTextAdapter(MainActivity.this, itemlist);
        alertList.setAdapter(itemsAdapter);

        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        alertList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, String.valueOf(i + 1) + " selected", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }
        if (id == R.id.action_branches) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void prepareListData(String header) {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        ArrayList<String> arrayList;
        String data = pref.getCompanyList();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        arrayList = gson.fromJson(data, type);
        listChild.addAll(arrayList);
        listDataHeader.add(header);
        listDataChild.put(listDataHeader.get(0), listChild);
    }
    public void setExpAdapter(){
        adapter = new ExpNavAdapter(MainActivity.this, listDataHeader, listDataChild);
        company_list.setAdapter(adapter);
    }
}
