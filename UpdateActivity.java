package com.example.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notes.RecycleModel.Models;
import com.example.notes.databinding.ActivityUpdateBinding;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        final String id = getIntent().getStringExtra("id");
        final String title = getIntent().getStringExtra("title");
        final String notes = getIntent().getStringExtra("notes");


//        binding.noteid.setText(id);
        binding.uptitle.setText(title);
        binding.upnotes.setText(notes);

        DBHelper dbHelper = new DBHelper(this);
        Cursor cursor = dbHelper.getOrders(id);
        binding.noteid.setText(cursor.getString(0));

        binding.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper1 = new DBHelper(UpdateActivity.this);
                boolean updation = dbHelper1.updation(binding.noteid.getText().toString(),
                        binding.uptitle.getText().toString(),
                        binding.upnotes.getText().toString());
                if (updation) {
                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(UpdateActivity.this, "Error", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(UpdateActivity.this, OptionActivity.class);
                startActivity(intent1);
                finish();

            }
        });
    }
}