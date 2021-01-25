package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestionClic((Button)findViewById(R.id.btnListRdv), listeRDV.class);
        gestionClic((Button)findViewById(R.id.btnCreateRdv), priseRDV.class);
        gestionClic((Button)findViewById(R.id.btnListMedicament), MedicamentActivity.class);
    }

    private void gestionClic(Button btn, final Class classe) {
        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "clic sur Liste rendez-vous", Toast.LENGTH_SHORT).show();
                Log.d("message", "clic sur Liste rendez-vous***********");
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });
    }





}