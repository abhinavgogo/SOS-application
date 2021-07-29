package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sendActivity extends AppCompatActivity {
    private EditText number, message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        number = findViewById(R.id.phoneNumber);
        message = findViewById(R.id.msg);
        send = findViewById(R.id.sendbtn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        sendSMS();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });
    }

    private void sendSMS() {
        String phoneNo = number.getText().toString().trim();
        String SMS = message.getText().toString().trim();

        try {

            SmsManager smsManager1 = SmsManager.getDefault();
            smsManager1.sendTextMessage(phoneNo, null, SMS, null,null);
            Toast.makeText(this, "SMS sent Successfully!", Toast.LENGTH_LONG).show();
        } catch (Exception e)
        {
            Toast.makeText(this,"SMS failed!",Toast.LENGTH_LONG).show();
        }
    }
}