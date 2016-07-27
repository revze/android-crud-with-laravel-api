package com.customerapps.revze.customerapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Customer> list_cust = new ArrayList<Customer>();
    JSONArray listCust = null;
    String url_read_cust = "http://192.168.43.8:8000";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_CUST = "customers";
    public static final String TAG_ID_CUST = "id";
    public static final String TAG_NAME_CUST = "name";
    public static final String TAG_BIRTH_CUST = "birth_date";
    public static final String TAG_GENDER_CUST = "gender";
    public static final String TAG_PHONE_CUST = "phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.listCust);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CreateActivity.class);
                startActivity(i);
            }
        });

        ReadCustTask m = (ReadCustTask) new ReadCustTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                String idcust = ((TextView) view.findViewById(R.id.id_cust)).getText().toString();
                String namecust = ((TextView) view.findViewById(R.id.name_cust)).getText().toString();
                String birthcust = ((TextView) view.findViewById(R.id.birth_cust)).getText().toString();
                String gendercust = ((TextView) view.findViewById(R.id.gender_cust)).getText().toString();
                String phonecust = ((TextView) view.findViewById(R.id.phone_cust)).getText().toString();

                Intent i = null;
                i = new Intent(MainActivity.this, ShowActivity.class);
                Bundle b = new Bundle();
                b.putString("id_cust", idcust);
                b.putString("name_cust", namecust);
                b.putString("birth_cust", birthcust);
                b.putString("gender_cust", gendercust);
                b.putString("phone_cust", phonecust);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this,"Application created by Revando.",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class ReadCustTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            String returnResult = getCustList();
            return returnResult;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();
            if (result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(MainActivity.this,"Unable to connect to server, please check your internet connection!",Toast.LENGTH_LONG).show();
            }
            if (result.equalsIgnoreCase("no results"))
            {
                Toast.makeText(MainActivity.this,"Data empty",Toast.LENGTH_LONG).show();
            }
            else{
//                list.setAdapter(new CustomerAdapter(ScrollingActivity.this,list_cust));
                list.setAdapter(new CustomerAdapter(MainActivity.this,list_cust));
            }

        }

        public String getCustList()
        {
            Customer tempCust = new Customer();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(url_read_cust,"GET",parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success==1)
                {
                    listCust = json.getJSONArray(TAG_CUST);
                    for (int i = 0; i < listCust.length(); i++)
                    {
                        JSONObject c = listCust.getJSONObject(i);
                        tempCust = new Customer();
                        tempCust.setId(c.getString(TAG_ID_CUST));
                        tempCust.setName(c.getString(TAG_NAME_CUST));
                        tempCust.setBirth_date(c.getString(TAG_BIRTH_CUST));
                        tempCust.setGender(c.getString(TAG_GENDER_CUST));
                        tempCust.setPhone_number(c.getString(TAG_PHONE_CUST));
                        list_cust.add(tempCust);
                    }
                    return "OK";
                }

                else{
                    return "no results";
                }
            }
            catch (Exception e)
            {
                return "Exception Caught";
            }
        }
    }
}
