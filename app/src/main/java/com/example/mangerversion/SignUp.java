package com.example.mangerversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    Db_helper db =  new Db_helper(this);
    EditText userName , password, confirm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("SignUp");

        setContentView(R.layout.activity_sign_up);
        userName = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirm_password = (EditText) findViewById(R.id.confirm_password);

    }

    public void btn_add(View view) {
        String username = userName.getText().toString();
        String Password = password.getText().toString();
        String Confirm_Password = confirm_password.getText().toString();

        if(username.equals("")||password.equals("")||confirm_password.equals(""))
            Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        else{
            if(Password.equals(Confirm_Password)){
                Boolean checkuser = db.checkUsername(username);
                if(checkuser==false){
                    Boolean insert = db.addUser(username, Password);
                    if(insert==true){
                        Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomePage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SignUp.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }
        } }
;
}