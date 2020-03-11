package com.example.profileassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class BioActivity extends AppCompatActivity {
    Button applyButton;
    EditText bio;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);

        //Use back_arrow4 to navigate back to home page without edits
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow4);
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });

        //set preferences for email entered by user
        bio = (EditText) findViewById(R.id.B_bioEdit);
        applyButton = (Button) findViewById(R.id.B_apply);
        pref = getSharedPreferences("bios", MODE_PRIVATE);
        intent = new Intent(BioActivity.this, MainActivity.class);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String strBio = bio.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("bioInput", strBio);
                editor.commit();
                startActivity(intent);
            }
        });
    }
}
