package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {
    RippleBackground rippleBackground;
    ImageView imageView;
    public Button customsms;
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button = (Button)findViewById(R.id.button);
        customsms = (Button)findViewById(R.id.cussms);
        rippleBackground = (RippleBackground)findViewById(R.id.sos);
        imageView = (ImageView)findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!rippleBackground.isRippleAnimationRunning()){;
                    rippleBackground.startRippleAnimation();
                    openSOSActivity();
                }
                else{
                    imageView.setColorFilter(null);

                    rippleBackground.stopRippleAnimation();
                }

            }
        });
        customsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openSendActivity();
                }
        });
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSOSActivity();
            }
        });
        */

    }
    public void openSOSActivity() {
        Intent sosintent = new Intent(MainActivity.this,MapsActivity.class);
        startActivity(sosintent);
    }
    public void openSendActivity(){
        Intent sendintent = new Intent(MainActivity.this,sendActivity.class);
        startActivity(sendintent);
    }
}