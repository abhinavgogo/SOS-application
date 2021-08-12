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
    ImageView imageView,numberView;
    public Button customSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customSMS = findViewById(R.id.cussms);
        rippleBackground = findViewById(R.id.sos);
        imageView = findViewById(R.id.image);
        numberView = findViewById(R.id.numberView);
        imageView.setOnClickListener(v -> {
            if(!rippleBackground.isRippleAnimationRunning()){;
                rippleBackground.startRippleAnimation();
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));

            }
            else{
                imageView.setColorFilter(null);

                rippleBackground.stopRippleAnimation();
            }

        });
        customSMS.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),sendActivity.class)));
        numberView.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),number_activity.class)));
    }
}