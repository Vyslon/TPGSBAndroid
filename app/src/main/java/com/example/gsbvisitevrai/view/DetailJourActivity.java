package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.RendezVous;

import java.util.ArrayList;

public class DetailJourActivity extends AppCompatActivity {
    private ArrayList<RendezVous> lesRDV;
    private com.example.gsbvisitevrai.view.RendezVousListAdapter adapter;

    /**
     * S'exécute lors de l'ouverture de la page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jour);
        creerListe();

        this.configureToolbar();
    }

    /**
     * Créer la liste des rendez-vous
     */
    private void creerListe(){
        Intent intent = getIntent();
        lesRDV =  intent.getParcelableArrayListExtra("lesRendezVous");
        if (lesRDV != null){
            ListView lstHisto = (ListView)this.findViewById(R.id.listRendezVous);
            adapter = new com.example.gsbvisitevrai.view.RendezVousListAdapter(this, lesRDV);
            lstHisto.setAdapter(adapter);
        }
    }

    private void configureToolbar() {
        //Get the toolbar (serialise)
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        toolbar.setTitle("Mes rendez-vous du jour");
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();
        // Enable the Up button
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}