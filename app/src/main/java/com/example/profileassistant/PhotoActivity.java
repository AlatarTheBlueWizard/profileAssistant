package com.example.profileassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        //Use back_arrow to navigate back to home page without edits
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow5);
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });

        //Use ph_takePhoto button to open TakePhotoActivity
        Button takeButton = (Button) findViewById(R.id.ph_takePhoto);
        takeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1) {
                Intent tIntent= new Intent(v1.getContext(), TakePhotoActivity.class);
                startActivity(tIntent);
            }
        });

        //Use ph_uploadPhoto button to open UploadActivity
        Button uploadButton = (Button) findViewById(R.id.ph_uploadPhoto);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                Intent uIntent = new Intent(v2.getContext(), UploadActivity.class);
                startActivity(uIntent);
            }
        });
    }
}
