package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;

public class priseRDV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise_r_d_v);
        ecouteRetour();
    }

    private void ecouteRetour() {
        ((ImageButton) findViewById(R.id.btnRetourdePriseRDV)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(priseRDV.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}