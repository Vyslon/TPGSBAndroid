package com.example.gsbvisitevrai.view;

import android.content.Intent;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.desai.vatsal.mydynamictoast.MyDynamicToast;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.controller.MedicamentController;
import com.example.gsbvisitevrai.controller.PraticienController;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.example.gsbvisitevrai.model.*;

import java.io.*;
import java.net.URL;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {
/*    ArrayList<Medicament> medicaments;
    ArrayList<Praticien> praticiens;
    ArrayList<RendezVous> rendezVous;
    Bundle bundleRDV = new Bundle();
    Bundle bundleMedocs = new Bundle();
    Bundle bundlePraticiens = new Bundle();*/
    private EditText editText;

    /**
     * Appeler lors de l'ouverture de la page d'accueil
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.idApplication);
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                String value = editText.getText().toString();
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                if (value.compareTo(pref.getString("password", null)) == 0) {
                    Intent intent = new Intent(MainActivity.this, listeRDV.class);
                    startActivity(intent);
                }
                else
                    MyDynamicToast.errorMessage(MainActivity.this, "Code erroné");
            }
            return false;
        }
        );

/*        MedicamentController medicamentController = MedicamentController.getInstance(getBaseContext());
        medicaments = medicamentController.medicaments();
        PraticienController praticienController = PraticienController.getInstance(getBaseContext());
        praticiens = praticienController.praticiens();
        RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
        rendezVous = rendezVousController.rendezVous();
        bundleRDV.putParcelableArrayList("lesRendezVous", rendezVous);
        bundleMedocs.putParcelableArrayList("lesMedocs", medicaments);
        bundlePraticiens.putParcelableArrayList("lesPraticiens", praticiens);
        ecoutecalcul();

        //1 - Configuration Toolbar
        this.configureToolbar();*/
    }

    /**
     * Ajoute à chaque bouton le comportement nécessare à l'ouverture de la page correspondante
     */
/*    private void ecoutecalcul(){
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
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Ajouter le menu à la Toolbard
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mes rendez-vous");
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }
}