package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.ExpNavAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.ImageTextAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.GenericMethods;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ContactsModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ImageTextModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
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
        setCompanyDetails();
        prepareListData(pref.getSelectedcompany());
        setExpAdapter();


        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.SEND_SMS},
                1);

        nav_company_logo = nav_header.findViewById(R.id.company_logo);

        setToolbar();
        setNavigationDrawerMenu();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(MainActivity.this, "SMS permission denied.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
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
        } else if (itemString.equals(strings.getContacts())) {
            startActivity(new Intent(MainActivity.this, ContactDetails.class));
        } else if (itemString.equals("Login")) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
                switch (i) {
                    case 0:
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:0123456789"));
                        startActivity(intent);
                        break;
                    case 1:
                        sendEmail(strings.getOurmail(), "", "");
                        break;
                    case 2:
                        SMSAlert();
                        break;
                }
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

    public void setExpAdapter() {
        adapter = new ExpNavAdapter(MainActivity.this, listDataHeader, listDataChild);
        company_list.setAdapter(adapter);
    }

    public void SMSAlert() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.sms_layout, null);
        final EditText message_box = dialogView.findViewById(R.id.sms_body);
        Button send_btn = dialogView.findViewById(R.id.send_sms);
        dialogBuilder.setView(dialogView);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = message_box.getText().toString();
                if (message.equals(null) || message.equals("")) {
                    message_box.setError("Write a message");
                } else {
                    sendSMSMessage("9877787", message);
                    alertDialog.dismiss();
                }
            }
        });


    }

    protected void sendSMSMessage(String phoneNo, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again.",
                    Toast.LENGTH_LONG).show();
            Log.d(TAG, "sendSMSMessage: " + e);
            e.printStackTrace();
        }
    }

    protected void sendEmail(String to, String subject, String body) {
        String[] TO = {to};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        Log.d(TAG, "sendEmaisl: " + to);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, "");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCompanyDetails() {
        tv_nav_company_address.setText(String.valueOf(pref.getCompanyaddress()));
        tv_nav_company_name.setText(pref.getCompanyname());
    }
}
