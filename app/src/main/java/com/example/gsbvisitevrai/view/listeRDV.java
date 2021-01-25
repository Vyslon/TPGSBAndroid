package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;

public class listeRDV extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_r_d_v);
        ecouteRetour();
    }

    private void ecouteRetour() {
        ((ImageButton) findViewById(R.id.btnRetourdeListeRDV)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(listeRDV.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}