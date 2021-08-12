package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contactActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    private static final int CONTACT_PICKER_REQUEST = 202;

    private EditText namec,phnoc;
    private Button conaddbtn,choose;
    SharedPreferences sharedPreferences;


    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NUM = "phno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        namec = findViewById(R.id.cname);
        phnoc = findViewById(R.id.cphno);
        conaddbtn = findViewById(R.id.conadd);
        sharedPreferences = getSharedPreferences(KEY_NUM,MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NUM,null);

//        if(phnoc!=null){
//            Intent conintent = new Intent(this,MapsActivity.class);
//            startActivity(conintent);
//
//        }
    }
    public void addContact(View view){
        try {
            Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE},
                    "DISPLAY_NAME = '" + namec.getText().toString() + "'", null, null);

            cursor.moveToFirst();
            phnoc.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            phnoc.setText("NA");
        }
    }
    public void addpref(View view){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NUM,phnoc.getText().toString());
        editor.apply();
        Intent conintent = new Intent(contactActivity.this,MapsActivity.class);
        startActivity(conintent);
        Toast.makeText(this,"Contact Added Successfully",Toast.LENGTH_SHORT).show();
    }

}