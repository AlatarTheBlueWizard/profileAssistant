package com.example.profileassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {
    EditText fName, lName;
    Button applyButton;
    SharedPreferences pref;
    Intent intent;

    /**
     * Uses shared preferences to save info between this activity and the mainActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        //Use back_arrow to navigate back to home page without edits
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });

        //set preferences for names entered by user
        fName = (EditText) findViewById(R.id.N_fName);
        lName = (EditText) findViewById(R.id.N_lName);
        applyButton = (Button) findViewById(R.id.N_apply);
        pref = getSharedPreferences("names", MODE_PRIVATE);
        intent = new Intent(NameActivity.this, MainActivity.class);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String strFirst = fName.getText().toString();
                String strLast = lName.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("messageFirst", strFirst);
                editor.putString("messageLast", strLast);
                editor.commit();
                startActivity(intent);
            }
        });
    }
}
