package com.dev.productioncompany;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.app.admin.PreferentialNetworkServiceConfig;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dev.productioncompany.model.ProjectModel;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CreateNewProjectActivity extends AppCompatActivity {

    AppCompatImageView ivBack;
    AppCompatEditText etProjectName, etContract, etAccessories;
    AppCompatButton btnCreate;
    AppCompatTextView tvLabel;
    String strProjectName, strContract, strAccessories;
    static ArrayList<ProjectModel> projectList = new ArrayList<>();
    ProjectModel model;
    String position = "";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_project);

        ivBack = findViewById(R.id.ivBack);
        etProjectName = findViewById(R.id.etProjectName);
        etContract = findViewById(R.id.etContract);
        etAccessories = findViewById(R.id.etAccessories);
        btnCreate = findViewById(R.id.btnCreate);
        tvLabel = findViewById(R.id.tvLabel);

        if (getIntent().getExtras()!=null){
            model = (ProjectModel) getIntent().getSerializableExtra("data");
            position = getIntent().getStringExtra("position");
            etProjectName.setText(model.getName());
            etContract.setText(model.getContract());
            etAccessories.setText(model.getList());
            btnCreate.setText("Update Project");
            tvLabel.setText("Update Project");
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strProjectName = etProjectName.getText().toString();
                strContract = etContract.getText().toString();
                strAccessories = etAccessories.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd / HH:mm:ss", Locale.getDefault());
                String currentDateAndTime = sdf.format(new Date());

                if (strProjectName.isEmpty()){
                    Toast.makeText(CreateNewProjectActivity.this, "Please enter project name", Toast.LENGTH_SHORT).show();
                }else if (strContract.isEmpty()){
                    Toast.makeText(CreateNewProjectActivity.this, "Please enter contract", Toast.LENGTH_SHORT).show();
                }else if (strAccessories.isEmpty()){
                    Toast.makeText(CreateNewProjectActivity.this, "Please enter accessories", Toast.LENGTH_SHORT).show();
                }else{
                    if (getIntent().getExtras()!=null){
                        Toast.makeText(CreateNewProjectActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                        projectList.set(Integer.parseInt(position), new ProjectModel(strProjectName, strContract, strAccessories, currentDateAndTime));
                        startActivity(new Intent(CreateNewProjectActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(CreateNewProjectActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                        projectList.add(new ProjectModel(strProjectName, strContract, strAccessories, currentDateAndTime));
                    }
                    etProjectName.setText("");
                    etContract.setText("");
                    etAccessories.setText("");
                }

            }
        });
    }

}