package com.example.profileassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class TakePhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takephoto);

        //Use back_arrow to navigate back to photo page without edits
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow6);
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), PhotoActivity.class);
                startActivity(backIntent);
            }
        });
    }
}
