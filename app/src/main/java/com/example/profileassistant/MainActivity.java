package com.example.profileassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView receiverName;
    TextView receiverEmail;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Use M_nameEdit button to open NameActivity
        Button editNameButton = (Button) findViewById(R.id.M_nameEdit);
        editNameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent nIntent = new Intent(v.getContext(), NameActivity.class);
                startActivity(nIntent);
            }
        });

        //Use M_emailEdit button to open EmailActivity
        Button editEmailButton = (Button) findViewById(R.id.M_emailEdit);
        editEmailButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v2) {
                Intent eIntent = new Intent(v2.getContext(), EmailActivity.class);
                startActivity(eIntent);
            }
        });

        //Use M_phoneEdit button to open PhoneActivity
        Button editPhoneButton = (Button) findViewById(R.id.M_phoneEdit);
        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v3) {
                Intent pIntent = new Intent(v3.getContext(), PhoneActivity.class);
                startActivity(pIntent);
            }
        });

        //Use M_bioEdit button to open BioActivity
        Button editBioButton = (Button) findViewById(R.id.M_bioEdit);
        editBioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v4) {
                Intent bIntent = new Intent(v4.getContext(), BioActivity.class);
                startActivity(bIntent);
            }
        });

        //Receive preferences from NameActivity and display
        receiverName = (TextView) findViewById(R.id.M_nameField);
        prf = getSharedPreferences("names", MODE_PRIVATE);
        receiverName.setText(prf.getString("messageFirst", "") + " " +
                prf.getString("messageLast", ""));


    }
}
