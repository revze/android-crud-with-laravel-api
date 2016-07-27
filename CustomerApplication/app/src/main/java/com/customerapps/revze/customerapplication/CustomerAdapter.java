package com.customerapps.revze.customerapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by revze on 27/07/16.
 */
public class CustomerAdapter extends BaseAdapter{

    private Activity activity;
    private ArrayList<Customer> data_cust = new ArrayList<Customer>();
    private  static LayoutInflater inflater = null;

    public CustomerAdapter(Activity a, ArrayList<Customer> d)
    {
        activity = a;
        data_cust = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount()
    {
        return data_cust.size();
    }

    public Object getItem(int position)
    {
        return data_cust.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (convertView==null)
            vi = inflater.inflate(R.layout.list_item_cust,null);
        TextView id_cust = (TextView) vi.findViewById(R.id.id_cust);
        TextView name_cust = (TextView) vi.findViewById(R.id.name_cust);
        TextView phone_cust = (TextView) vi.findViewById(R.id.phone_cust);
        TextView gender_cust = (TextView) vi.findViewById(R.id.gender_cust);
        TextView birth_cust = (TextView) vi.findViewById(R.id.birth_cust);

        Customer list_cust = data_cust.get(position);
        id_cust.setText(list_cust.getId());
        name_cust.setText(list_cust.getName());
        phone_cust.setText(list_cust.getPhone_number());
        gender_cust.setText(list_cust.getGender());
        birth_cust.setText(list_cust.getBirth_date());

        return vi;
    }

}
