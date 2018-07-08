package com.example.pranayarora.webservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Signup extends AppCompatActivity {

    Button login,signup;
    EditText mobile_number,email_id,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        login = (Button)findViewById(R.id.button3);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        mobile_number=(EditText)findViewById(R.id.editText3);
        password=(EditText)findViewById(R.id.editText4);
        email_id=(EditText)findViewById(R.id.editText6);

        signup = (Button)findViewById( R.id.button );

    }
}
