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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    JSONParser jParser = new JSONParser();

    String url_update_cust = "http://192.168.43.8:8000/update";

    public static final String TAG_SUCCESS = "success";
    public static final String TAG_ID_CUST = "id";
    public static final String TAG_NAME_CUST = "name";
    public static final String TAG_BIRTH_CUST = "birth_date";
    public static final String TAG_GENDER_CUST = "gender";
    public static final String TAG_PHONE_CUST = "phone_number";

    TextView txtId;
    RadioGroup radioGender;
    RadioButton radioFemale, radioMale;
    EditText editName, editBirth, editPhone;
    Button updateBtn, backBtn;

    String idStr, nameStr, birthStr, genderStr, phoneStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        txtId = (TextView) findViewById(R.id.input_id);
        editName = (EditText) findViewById(R.id.input_name);
        radioGender = (RadioGroup) findViewById(R.id.group_gender);
        editBirth = (EditText) findViewById(R.id.input_birth);
        editPhone = (EditText) findViewById(R.id.input_phone);
        radioFemale = (RadioButton) findViewById(R.id.radio_female);
        radioMale = (RadioButton) findViewById(R.id.radio_male);
        updateBtn = (Button) findViewById(R.id.btn_update);
        backBtn = (Button) findViewById(R.id.btn_back);

        Bundle b = getIntent().getExtras();
        String isi_id_cust = b.getString("id_cust");
        String isi_name_cust = b.getString("name_cust");
        String isi_birth_cust = b.getString("birth_cust");
        String isi_gender_cust = b.getString("gender_cust");
        String isi_phone_cust = b.getString("phone_cust");

        txtId.setText(isi_id_cust);
        editName.setText(isi_name_cust);
        editBirth.setText(isi_birth_cust);
        editPhone.setText(isi_phone_cust);
        radioMale.setChecked(true);
//        if (isi_gender_cust=="Male")
//        {
//            radioMale.setChecked(true);
//        }
//
//        else if(isi_gender_cust=="Female")
//        {
//            radioFemale.setChecked(true);
//        }
//        int id = radioGender.getCheckedRadioButtonId();
//        radioGender.check(id);
//        RadioButton radioButton = (RadioButton) findViewById(id);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idStr = txtId.getText().toString();
                nameStr = editName.getText().toString();
                birthStr = editBirth.getText().toString();
                int id = radioGender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                genderStr = radioButton.getText().toString();
                phoneStr = editPhone.getText().toString();
                new UpdateCustTask().execute();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idcust = txtId.getText().toString();
                String namecust = editName.getText().toString();
                String birthcust = editBirth.getText().toString();
                int id = radioGender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(id);
                String gendercust = radioButton.getText().toString();
                String phonecust = editPhone.getText().toString();

                Intent i = null;
                i = new Intent(EditActivity.this, ShowActivity.class);
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

    class UpdateCustTask extends AsyncTask<String, Void, String>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(EditActivity.this);
            pDialog.setMessage("Please waiting...");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText)
        {
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            parameter.add(new BasicNameValuePair(TAG_ID_CUST, idStr));
            parameter.add(new BasicNameValuePair(TAG_NAME_CUST, nameStr));
            parameter.add(new BasicNameValuePair(TAG_BIRTH_CUST, birthStr));
            parameter.add(new BasicNameValuePair(TAG_GENDER_CUST, genderStr));
            parameter.add(new BasicNameValuePair(TAG_PHONE_CUST, phoneStr));

            try {
                JSONObject json = jParser.makeHttpRequest(url_update_cust,"GET",parameter);

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
                Toast.makeText(EditActivity.this, "Unable to connect to server, please check your internet connection!", Toast.LENGTH_LONG).show();
            }

            if (result.equalsIgnoreCase("FAIL"))
            {
                Toast.makeText(EditActivity.this, "Fail... Try Again",Toast.LENGTH_LONG).show();
            }

            else{
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                Toast.makeText(EditActivity.this,"Customer successfully updated.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
