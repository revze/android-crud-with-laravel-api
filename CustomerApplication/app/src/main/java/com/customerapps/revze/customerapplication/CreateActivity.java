package com.customerapps.revze.customerapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    String url_create_cust = "http://192.168.43.8:8000/store";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_NAME_CUST = "name";
    public static final String TAG_BIRTH_CUST = "birth_date";
    public static final String TAG_GENDER_CUST = "gender";
    public static final String TAG_PHONE_CUST = "phone_number";

    EditText EditTxtname, EditTxtbirth, EditTxtphone;
    RadioGroup genderRadioGroup;
    Button addBtn, backBtn;
    String nameStr, birthStr, genderStr, phoneStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditTxtname = (EditText) findViewById(R.id.input_name);
        EditTxtbirth = (EditText) findViewById(R.id.input_birth);
        genderRadioGroup = (RadioGroup) findViewById(R.id.group_gender);
        EditTxtphone = (EditText) findViewById(R.id.input_phone);
        addBtn = (Button) findViewById(R.id.button_add);
        backBtn = (Button) findViewById(R.id.button_back);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr = EditTxtname.getText().toString();
                birthStr = EditTxtbirth.getText().toString();
                int id = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                genderStr = radioButton.getText().toString();
                phoneStr = EditTxtphone.getText().toString();
                new CreateCustTask().execute();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }

    class CreateCustTask extends AsyncTask<String, String, String> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(CreateActivity.this);
            dialog.setMessage("Please waiting...");
            dialog.setIndeterminate(false);
            dialog.setCancelable(false);
            dialog.show();
        }

        protected String doInBackground(String... args) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair(TAG_NAME_CUST, nameStr));
            params.add(new BasicNameValuePair(TAG_BIRTH_CUST, birthStr));
            params.add(new BasicNameValuePair(TAG_GENDER_CUST, genderStr));
            params.add(new BasicNameValuePair(TAG_PHONE_CUST, phoneStr));

            JSONObject json = jsonParser.makeHttpRequest(url_create_cust, "GET", params);

            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success==1)
                {
                    finish();
                }

                else{
                    return "gagal_database";
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                return "gagal_koneksi_or_exception";
            }
            return "sukses";
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
            if (result.equalsIgnoreCase("gagal_database"))
            {
                dialog.dismiss();
                Toast.makeText(CreateActivity.this, "Terjadi kesalahan! Silakan cek koneksi anda!", Toast.LENGTH_SHORT).show();
            }

            else if (result.equalsIgnoreCase("gagal_koneksi_or_exception"))
            {
                dialog.dismiss();
                Toast.makeText(CreateActivity.this, "Terjadi masalah! Silakan cek koneksi anda!", Toast.LENGTH_SHORT).show();
            }
            else if (result.equalsIgnoreCase("sukses"))
            {
                dialog.dismiss();
                Intent i = null;
                i = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(i);
                Toast.makeText(CreateActivity.this,"Customer data successfully created.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
