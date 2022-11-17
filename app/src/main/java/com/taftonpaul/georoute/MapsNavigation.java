package com.taftonpaul.georoute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsNavigation extends AppCompatActivity {

    private FloatingActionButton navigation_btn;
    private ImageView back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_navigation);

        navigation_btn = findViewById(R.id.launch);
        back_Btn = findViewById(R.id.backBtn);

        navigation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=22.659239,88.435534&mode=d"));
                intent.setPackage("com.google.android.apps.maps");

                if (intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(intent);
                }

            }
        });

        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsNavigation.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}