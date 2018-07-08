package com.example.pranayarora.webservices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button login,signup;
    EditText mobile_number,password;
    private ProgressDialog pDialog;
    int flag = 0;
    JSONParser jsonParser = new JSONParser();
    private static String url = "http://192.168.43.129/WebApplication/login.php";
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.login);
        mobile_number = (EditText) findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOnline(MainActivity.this))
                {
                    Toast.makeText(MainActivity.this,"No network connection",Toast.LENGTH_LONG).show();
                    return;
                }
                String number = mobile_number.getText().toString();
                String pass = password.getText().toString();
                new LoginAccess().execute(number,pass);
            }
        });

    }

    private boolean isOnline(Context ctx) {

        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }


    public class LoginAccess extends AsyncTask<String,String,String>
    {

        @Override
        public void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog( MainActivity.this);
            pDialog.setMessage("Login");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String mobile_number = arg0[0];
            String password = arg0[1];
            params.add(new BasicNameValuePair("mobile_number", mobile_number));
            params.add(new BasicNameValuePair("password", password));
            JSONObject json = jsonParser.makeHttpRequest(url,"POST", params);
            Log.d("Create Response", json.toString());

            try {
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1)
                {
                    flag=0;
                    Intent i = new Intent(getApplicationContext(),Welcome.class);
                    i.putExtra("mobile_number",mobile_number);
                    i.putExtra("password",password);
                    startActivity(i);
                    finish();
                }
                else
                {
                    // failed to login
                    flag=1;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute( s );
        }
    }
}


