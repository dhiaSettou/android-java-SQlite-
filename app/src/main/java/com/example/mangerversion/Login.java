package com.example.mangerversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
        Button login ;
        Button signIn;
        Db_helper db =  new Db_helper(this);
        EditText userName , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("LogIn");
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login=   findViewById(R.id.login);
        signIn =findViewById(R.id.signinbutton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                goHome();
                String username = userName.getText().toString();
                String pass = password.getText().toString();

                if(username.equals("")||pass.equals(""))
                    Toast.makeText(Login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                if (username.equals("admin")|| password.equals("admin")){
                    Intent in  = new Intent(getApplicationContext(), MangerHome.class);
                    startActivity(in);
                }
                else{
                    Boolean checkAccount = db.checkAccount(username, pass);
                    if(checkAccount==true){
                        Toast.makeText(Login.this, "Welcome : "+username, Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);

                    }else{
                        Toast.makeText(Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
            }




}