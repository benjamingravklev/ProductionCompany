package com.dev.productioncompany;

import static com.dev.productioncompany.CreateNewProjectActivity.projectList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.productioncompany.adapter.ProjectsAdapter;

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