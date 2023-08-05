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
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText etUsername, etPassword;
    String strUsername, strPassword;
    AppCompatButton btnLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        DataPersistencyHelper.context = getApplicationContext();
        List<UserModel> list = DataPersistencyHelper.loadData();

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