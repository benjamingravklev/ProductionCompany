package com.dev.productioncompany;

import static com.dev.productioncompany.CreateNewProjectActivity.projectList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.productioncompany.adapter.ProjectsAdapter;
import com.dev.productioncompany.model.ProjectModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class RetrieveListActivity extends AppCompatActivity {

    private static final String TAG = "RetrieveListActivity";
    AppCompatImageView ivBack;
    RecyclerView rvProjects;
    ProjectsAdapter adapter;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        db.collection("projects")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            projectList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String id = document.getId(); // get the document ID
                                String name = document.getString("name");
                                String contract = document.getString("contract");
                                String accessories = document.getString("accessories");
                                String datetime = document.getString("datetime");
                                projectList.add(new ProjectModel(id, name, contract, accessories, datetime));
                            }
                            adapter = new ProjectsAdapter(projectList, RetrieveListActivity.this);
                            rvProjects.setLayoutManager(new LinearLayoutManager(RetrieveListActivity.this, LinearLayoutManager.VERTICAL, false));
                            rvProjects.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}