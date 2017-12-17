package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.ContactsAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.ImageTextAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters.SalesAdapter;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ContactList;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ContactsModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.InvoicesModel;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ApiService;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Retroclient;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactDetails extends AppCompatActivity {
    ListView contactlist;
    String TAG = "contactlist";
    ListAdapter itemsAdapter;
    List<ContactList> allList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contacts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contactlist= findViewById(R.id.contact_listview);
        getContacts();
    }

    public void getContacts() {
        ApiService api = Retroclient.getApiService();
        Call<ContactsModel> call = api.getContactList("1", "100");
        call.enqueue(new Callback<ContactsModel>() {
            @Override
            public void onResponse(Call<ContactsModel> call, Response<ContactsModel> response) {
                if (response.isSuccessful()) {
                    allList=response.body().getData();
                    itemsAdapter = new ContactsAdapter(ContactDetails.this, response.body().getData());
                    contactlist.setAdapter(itemsAdapter);
                } else {
                    Log.d(TAG, "onResponse: Failed");
                }
            }

            @Override
            public void onFailure(Call<ContactsModel> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.toString());
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contacts_page_menu, menu);
        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
               filterList(s.toLowerCase());
                return false;
            }
        });
        return true;
    }

    public void filterList(String text) {
        Log.d(TAG, "filterListtext: " + text);
        List<ContactList> filteredList = new ArrayList<>();
        for (int i = 0; i < allList.size(); i++) {
            if (allList.get(i).getDisplayName().toLowerCase().contains(text)||allList.get(i).getEmail().toLowerCase().contains(text)||
                    allList.get(i).getMobile().contains(text)||allList.get(i).getPhone().contains(text)||allList.get(i).getAddress().toLowerCase().contains(text)) {
                filteredList.add(allList.get(i));
            }
            itemsAdapter = new ContactsAdapter(ContactDetails.this, filteredList);
            contactlist.setAdapter(itemsAdapter);
        }
    }

}
