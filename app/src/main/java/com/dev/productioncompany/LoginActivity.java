package com.dev.productioncompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dev.productioncompany.model.UserModel;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText etUsername, etPassword;
    String strUsername, strPassword;
    AppCompatButton btnLogin;
    ArrayList<UserModel> list = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        list.clear();
        list.add(new UserModel("aaa", "aaa@123"));
        list.add(new UserModel("bbb", "bbb@123"));
        list.add(new UserModel("ccc", "ccc@123"));
        list.add(new UserModel("ddd", "ddd@123"));
        list.add(new UserModel("eee", "eee@123"));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean flag=false;
                strUsername = etUsername.getText().toString();
                strPassword = etPassword.getText().toString();
                if (strUsername.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                }else if (strPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                }else{
                    for (UserModel users:list){
                        if (users.getUsername().contains(strUsername) && users.getPassword().contains(strPassword)){
                            flag=true;
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("name", strUsername);
                            startActivity(intent);
                            finish();
                            break;
                        }
                    }

                    if (!flag){
                        Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

    }
}