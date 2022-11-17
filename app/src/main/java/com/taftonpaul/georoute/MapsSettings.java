package com.taftonpaul.georoute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MapsSettings extends AppCompatActivity {

    Spinner spinner;
    ImageView back_Btn;
    Switch kilometres, miles;
    Button save_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_settings);

        spinner = findViewById(R.id.spinner);
        back_Btn = findViewById(R.id.backBtn);
        kilometres = findViewById(R.id.kmswitch);
        miles = findViewById(R.id.milesswitch);
        save_Btn = findViewById(R.id.saveBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MapsSettings.this, R.array.landmarks
                , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MapsSettings.this, MainActivity.class);
               startActivity(intent);
            }
        });

        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapsSettings.this, "Settings Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

}