package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.notes.Adpaters.RecycleAdapters;
import com.example.notes.RecycleModel.Models;
import com.example.notes.databinding.ActivityRecycleviewBinding;

import java.util.ArrayList;

public class RecycleviewActivity extends AppCompatActivity {
    ActivityRecycleviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRecycleviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Intent intent = getIntent();

        final String id = getIntent().getStringExtra("id");
        final String title = getIntent().getStringExtra("title");
        final String notes = getIntent().getStringExtra("notes");


        DBHelper dbHelper = new DBHelper(this);

        ArrayList<Models> list = dbHelper.selection();

        RecycleAdapters adapters = new RecycleAdapters(RecycleviewActivity.this, list);
        binding.recycle.setAdapter(adapters);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recycle.setLayoutManager(linearLayoutManager);
        adapters.notifyDataSetChanged();

        binding.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecycleviewActivity.this, DetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}