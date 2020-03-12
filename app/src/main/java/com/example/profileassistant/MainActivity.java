package com.example.profileassistant;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView receiverName, receiverEmail, receiverPhone, receiverBio;
    SharedPreferences prf, prfEmail, prfPhone, prfBio;
    private ImageView imgView;

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

        /*//Use M_photoEdit button to open PhotoActivity
        ImageButton editPhotoButton = (ImageButton) findViewById(R.id.M_photoEdit);
        editPhotoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent phIntent = new Intent(arg0.getContext(), PhotoActivity.class);
                startActivity(phIntent);
            }
        });*/

        //Receive preferences from NameActivity and display
        receiverName = (TextView) findViewById(R.id.M_nameField);
        prf = getSharedPreferences("names", MODE_PRIVATE);
        receiverName.setText(prf.getString("messageFirst", "") + " " +
                prf.getString("messageLast", ""));

        //Receive preferences from EmailActivity and display
        receiverEmail = (TextView) findViewById(R.id.M_emailField);
        prfEmail = getSharedPreferences("emails", MODE_PRIVATE);
        receiverEmail.setText(prfEmail.getString("emailInput", ""));

        //Receive preferences from PhoneActivity and display
        receiverPhone = (TextView) findViewById(R.id.M_phoneField);
        prfPhone = getSharedPreferences("phones", MODE_PRIVATE);
        receiverPhone.setText(prfPhone.getString("phoneInput", ""));

        //Receive preferences from BioActivity and display
        receiverBio = (TextView) findViewById(R.id.M_bioField);
        prfBio = getSharedPreferences("bios", MODE_PRIVATE);
        receiverBio.setText(prfBio.getString("bioInput", ""));

        //Initialize image view
        imgView = (ImageView) findViewById(R.id.imageView2);

        //Call selectImage method with edit button
        ImageButton ePhoto = (ImageButton) findViewById(R.id.M_photoEdit);
        ePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(MainActivity.this);
            }
        });

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Photo Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Profile Picture");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                } else if(options[item].equals("Choose from Photo Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                } else if(options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_CANCELED) {
            switch(requestCode) {
                case 0:
                    if(resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imgView.setImageBitmap(selectedImage);
                    }
                    break;
                case 1:
                    if(resultCode == RESULT_OK && requestCode == 1 && null != data) {
                        decodeUri(data.getData());
                    }
                    break;
            }
        }
    }

    public void decodeUri(Uri uri) {
        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            //The new size being scaled to
            final int REQUIRED_SIZE = 600;

            //Find the correct scale value. Should be power of 2
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while(true) {
                if(width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(imageSource, null, o2);
            imgView.setImageBitmap(bitmap);
        } catch(FileNotFoundException e) {

        } catch(IOException e) {

        } finally {
            if(parcelFD != null) {
                try {
                    parcelFD.close();
                } catch(IOException e) {

                }
            }
        }
    }
}
