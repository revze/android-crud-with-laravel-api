package com.customerapps.revze.customerapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();
    TextView IdCust, NameCust, BirthCust, GenderCust, PhoneCust;
    Button backBtn, editBtn, deleteBtn;

    String url_delete_cust = "http://192.168.43.8:8000/destroy";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID_CUST = "id";

    String cust_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        IdCust = (TextView) findViewById(R.id.idValue);
        NameCust = (TextView) findViewById(R.id.nameValue);
        BirthCust = (TextView) findViewById(R.id.birthValue);
        GenderCust = (TextView) findViewById(R.id.genderValue);
        PhoneCust = (TextView) findViewById(R.id.phoneValue);
        backBtn = (Button) findViewById(R.id.btn_back);
        editBtn = (Button) findViewById(R.id.btn_edit);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Bundle b = getIntent().getExtras();
        String isi_id_cust = b.getString("id_cust");
        String isi_name_cust = b.getString("name_cust");
        String isi_birth_cust = b.getString("birth_cust");
        String isi_gender_cust = b.getString("gender_cust");
        String isi_phone_cust = b.getString("phone_cust");

        IdCust.setText(isi_id_cust);
        NameCust.setText(isi_name_cust);
        BirthCust.setText(isi_birth_cust);
        GenderCust.setText(isi_gender_cust);
        PhoneCust.setText(isi_phone_cust);

        cust_id = isi_id_cust;

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = null;
                i = new Intent(ShowActivity.this, EditActivity.class);
                Bundle b = new Bundle();
                b.putString("id_cust", IdCust.getText().toString());
                b.putString("name_cust", NameCust.getText().toString());
                b.putString("birth_cust", BirthCust.getText().toString());
                b.putString("gender_cust", GenderCust.getText().toString());
                b.putString("phone_cust", PhoneCust.getText().toString());
                i.putExtras(b);
                startActivity(i);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteCustTask().execute();
            }
        });
    }

    class DeleteCustTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_ID_CUST, cust_id));

            try {
                JSONObject json = jParser.makeHttpRequest(url_delete_cust,"GET",parameter);

                int success = json.getInt(TAG_SUCCESS);
                if (success==1)
                {
                    return "OK";
                }

                else{
                    return "FAIL";
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Exception Caught";
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();

            if (result.equalsIgnoreCase("Exception Caught"))
            {
                Toast.makeText(ShowActivity.this,"Unable to connect to server,please check your internet connection!",Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(ShowActivity.this,"Fail... try again",Toast.LENGTH_LONG).show();
            }

            else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(ShowActivity.this,"Customer successfully deleted.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
