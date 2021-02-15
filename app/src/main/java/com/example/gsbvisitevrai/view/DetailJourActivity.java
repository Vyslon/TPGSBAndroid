package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
        ecouteRetour();
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

    /**
     * Permet le retour arrière
     */
    private void ecouteRetour() {
        ((ImageButton) findViewById(R.id.btnRetourDeDetail)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}