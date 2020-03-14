package com.example.profileassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {
    //Define the variables for send button and text
    Button applyButton;
    EditText email;
    SharedPreferences pref;
    Intent intent;

    /**
     * Uses shared preferences to save info between this activity and the mainActivity.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //Use back_arrow2 to navigate back to home page without edits
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow2);
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });

        //set preferences for email entered by user
        email = (EditText) findViewById(R.id.E_editEmail);
        applyButton = (Button) findViewById(R.id.E_apply);
        pref = getSharedPreferences("emails", MODE_PRIVATE);
        intent = new Intent(EmailActivity.this, MainActivity.class);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String strEmail = email.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("emailInput", strEmail);
                editor.commit();
                startActivity(intent);
            }
        });
    }
}
