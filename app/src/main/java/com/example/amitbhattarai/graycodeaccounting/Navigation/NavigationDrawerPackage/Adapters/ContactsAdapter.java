package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Models.ContactList;
import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.ProjectStrings;
import com.example.amitbhattarai.graycodeaccounting.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amitbhattarai on 11/17/17.
 */

public class ContactsAdapter extends ArrayAdapter<ContactList> {
    private Context context;
    ProjectStrings strings;
    List<ContactList> bodyArrayList = new ArrayList<>();
    private LayoutInflater mInflater;
    String TAG = "cusan";

    public ContactsAdapter(Context context, List<ContactList> time) {
        super(context, R.layout.contacts_custom_row, time);
        this.context = context;
        strings = new ProjectStrings();
        this.bodyArrayList = time;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        mInflater = LayoutInflater.from(context);
        Log.d(TAG, "getView: ");
        View view = mInflater.inflate(R.layout.contacts_custom_row, parent, false);
        if (position % 2 == 0) {
            view.findViewById(R.id.contacts_bg).setBackgroundResource(R.color.alter_white);
        }
        TextView contact_name = view.findViewById(R.id.contacts_name);
        TextView contact_phone = view.findViewById(R.id.contacts_phone);
        TextView contact_address = view.findViewById(R.id.contacts_address);
        ImageView contact_sms = view.findViewById(R.id.contacts_sms);
        ImageView contact_email = view.findViewById(R.id.contacts_email);

        contact_name.setText(bodyArrayList.get(position).getHonorofic() + ". " + bodyArrayList.get(position).getDisplayName() + " (" + bodyArrayList.get(position).getEmail() + ")");
        contact_address.setText(bodyArrayList.get(position).getAddress());
        contact_phone.setText(bodyArrayList.get(position).getPhone() + ", " + bodyArrayList.get(position).getMobile());

        contact_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        contact_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
    }
}
