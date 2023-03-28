package com.dev.productioncompany;

import static com.dev.productioncompany.CreateNewProjectActivity.projectList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dev.productioncompany.adapter.ProjectsAdapter;
import com.dev.productioncompany.model.ProjectModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RetrieveListActivity extends AppCompatActivity {

    AppCompatImageView ivBack;
    RecyclerView rvProjects;
    ProjectsAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_list);

        ivBack = findViewById(R.id.ivBack);
        rvProjects = findViewById(R.id.rvProjects);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        adapter = new ProjectsAdapter(projectList, this);
        rvProjects.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvProjects.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}