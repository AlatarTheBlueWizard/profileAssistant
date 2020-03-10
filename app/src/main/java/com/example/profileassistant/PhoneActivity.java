package com.example.profileassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PhoneActivity extends AppCompatActivity {
    EditText phone;
    Button applyButton;
    SharedPreferences pref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        //Use back_arrow3 to navigate back to home page without edits
        ImageButton backArrow = (ImageButton) findViewById(R.id.back_arrow3);
        backArrow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(backIntent);
            }
        });

        //set preferences for phone entered by user
        phone = (EditText) findViewById(R.id.P_phoneEdit);
        applyButton = (Button) findViewById(R.id.P_apply);
        pref = getSharedPreferences("phones", MODE_PRIVATE);
        intent = new Intent(PhoneActivity.this, MainActivity.class);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String strPhone = phone.getText().toString();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("phoneInput", strPhone);
                editor.commit();
                startActivity(intent);
            }
        });
    }
}
