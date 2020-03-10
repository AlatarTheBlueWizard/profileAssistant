package com.example.profileassistant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class EmailActivity extends AppCompatActivity {
    //Define the variables for send button and text
    Button send_button;
    EditText send_text;

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

        send_button = (Button) findViewById(R.id.E_apply);
        send_text = (EditText) findViewById(R.id.E_editEmail);

        //add the onClickListener in send button
        //after clicked, the instruction will run
        send_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                //get the value which the user inputs in EditTExt
                //and convert to a string
                String strEmail = send_text.getText().toString();
                //Create the intent object of this class to MainActivity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //putExtra method puts the value in the key-value pair
                intent.putExtra("message_keyEmail", strEmail);
                startActivity(intent);
            }
        });
    }
}
