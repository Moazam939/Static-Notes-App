package com.example.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.notes.Adpaters.RecycleAdapters;
import com.example.notes.RecycleModel.Models;
import com.example.notes.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    ArrayList<Models> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = new Intent(DetailActivity.this, RecycleviewActivity.class);

        binding.savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(DetailActivity.this);
                boolean inserted = dbHelper.insertion(
                        binding.edttitle.getText().toString(),
                        binding.edtnotes.getText().toString());
                if(inserted)
                    Toast.makeText(DetailActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Models models = new Models();
                addItem(models);
                Intent intent2 = new Intent(DetailActivity.this, OptionActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
    public void addItem(Models newItem) {
        list.add(newItem);
    }

}