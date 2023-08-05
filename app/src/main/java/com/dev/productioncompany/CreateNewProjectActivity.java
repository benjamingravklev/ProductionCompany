package com.dev.productioncompany;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.dev.productioncompany.model.ProjectModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateNewProjectActivity extends AppCompatActivity {

    private static final String TAG = "CreateNewProject";
    AppCompatImageView ivBack;
    AppCompatEditText etProjectName, etContract, etAccessories;
    AppCompatButton btnCreate;
    AppCompatTextView tvLabel;
    String strProjectName, strContract, strAccessories;
    static ArrayList<ProjectModel> projectList = new ArrayList<>();
    ProjectModel model;
    String position = "";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                } else if (strContract.isEmpty()){
                    Toast.makeText(CreateNewProjectActivity.this, "Please enter contract", Toast.LENGTH_SHORT).show();
                } else if (strAccessories.isEmpty()){
                    Toast.makeText(CreateNewProjectActivity.this, "Please enter accessories", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, Object> newProject = new HashMap<>();
                    newProject.put("name", strProjectName);
                    newProject.put("contract", strContract);
                    newProject.put("accessories", strAccessories);
                    newProject.put("datetime", currentDateAndTime);

                    if (getIntent().getExtras() != null){
                        // This is an existing project, update it
                        db.collection("projects").document(model.getId()) // use the stored document ID
                                .set(newProject)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(CreateNewProjectActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(CreateNewProjectActivity.this, MainActivity.class));
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @SuppressLint("LongLogTag")
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error updating document", e);
                                    }
                                });
                    } else {
                        // This is a new project, add it
                        db.collection("projects")
                                .add(newProject)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        String docId = documentReference.getId();
                                        ProjectModel newProjectModel = new ProjectModel(docId, strProjectName, strContract, strAccessories, currentDateAndTime);
                                        projectList.add(newProjectModel);
                                        Toast.makeText(CreateNewProjectActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                                        etProjectName.setText("");
                                        etContract.setText("");
                                        etAccessories.setText("");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @SuppressLint("LongLogTag")
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w(TAG, "Error adding document", e);
                                    }
                                });
                    }
                }
            }
        });
    }

}