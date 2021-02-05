package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.controller.MedicamentController;
import com.example.gsbvisitevrai.controller.PraticienController;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.example.gsbvisitevrai.model.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Medicament> medicaments;
    ArrayList<Praticien> praticiens;
    ArrayList<RendezVous> rendezVous;
    Bundle bundleRDV = new Bundle();
    Bundle bundleMedocs = new Bundle();
    Bundle bundlePraticiens = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MedicamentController medicamentController = MedicamentController.getInstance(getBaseContext());
        medicaments = medicamentController.medicaments();
        PraticienController praticienController = PraticienController.getInstance(getBaseContext());
        praticiens = praticienController.praticiens();
        RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
        rendezVous = rendezVousController.rendezVous();
        bundleRDV.putParcelableArrayList("lesRendezVous", rendezVous);
        bundleMedocs.putParcelableArrayList("lesMedocs", medicaments);
        bundlePraticiens.putParcelableArrayList("lesPraticiens", praticiens);
        ecoutecalcul();
    }
    private void ecoutecalcul(){
        ((Button) findViewById(R.id.btnListRdv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Mes rendez vous",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, listeRDV.class);
                intent.putExtras(bundleRDV);
                intent.putExtras(bundlePraticiens);
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btnCreateRdv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fixer un rendez vous",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, priseRDV.class);
                intent.putExtras(bundleRDV);
                intent.putExtras(bundlePraticiens);
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.btnListMedicament)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "les Medicaments",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MedicamentActivity.class);
                intent.putExtras(bundleMedocs);
                startActivity(intent);
            }
        });
    }
}