package com.example.kamran.logingreentheme;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class controlpanelshop extends AppCompatActivity {
    public static double lat;
    public static double lng;
    TextView locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlpanelshop);

        locationText = (TextView) findViewById(R.id.location);

        locationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent(controlpanelshop.this, MapsActivity.class);
                startActivity(mapIntent);

                try {
                    String latString = String.valueOf(lat);
                    String lngString = String.valueOf(lng);
                    latString = latString.substring(0, latString.indexOf(".") + 2);
                    lngString = lngString.substring(0, lngString.indexOf(".") + 2);
                    String locationString = latString + ", " + lngString;
                    locationText.setText(locationString);
                } catch (Exception e) {}
            }
        });
    }
}
