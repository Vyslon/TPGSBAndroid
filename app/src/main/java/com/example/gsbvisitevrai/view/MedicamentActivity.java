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
import com.example.gsbvisitevrai.controller.MedicamentController;
import com.example.gsbvisitevrai.controller.PraticienController;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.Praticien;
import com.example.gsbvisitevrai.model.RendezVous;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MedicamentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvMedicaments;
    com.example.gsbvisite.view.MedicamentListAdapter adapter;
    ArrayList<Medicament> medicaments;
    ArrayList<Praticien> praticiens;
    ArrayList<RendezVous> rendezVous;
    Bundle bundleRDV = new Bundle();
    Bundle bundleMedocs = new Bundle();
    Bundle bundlePraticiens = new Bundle();

    /**
     * Appeler lors de l'ouverture de la page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);
        creerListe();

        configureToolbar();
        configureNavigation();
    }

    /**
     * Créer la liste de médicaments
     */
    private void creerListe(){
        Intent intent = getIntent();
        MedicamentController medicamentController = MedicamentController.getInstance(getBaseContext());
        medicaments = medicamentController.medicaments();
        if (medicaments != null){
            ListView lstHisto = (ListView)this.findViewById(R.id.listMedicaments);
             adapter = new com.example.gsbvisite.view.MedicamentListAdapter(this, medicaments);
            lstHisto.setAdapter(adapter);
            lstHisto.setOnItemClickListener(this);
        }
    }

    /**
     * Permet la sélection d'un médicament pour afficher ses détails
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Medoc", "Position : " + String.valueOf(position));
        Medicament medicament = (Medicament)adapter.getItem(position);
        String value = medicament.getMNomCommercial();
        Intent intent = new Intent(MedicamentActivity.this, DetailMedicamentActivity.class);
        intent.putExtra("medicament", medicament);
        startActivity(intent);
    }

    private void configureNavigation() {
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_medicine);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                (item) -> {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_accueil:
                            intent = new Intent(MedicamentActivity.this, listeRDV.class);
                            startActivity(intent);
                            break;
                        case R.id.action_medicine: {
                            intent = new Intent(MedicamentActivity.this, MedicamentActivity.class);
                            startActivity(intent);
                            break;
                        }
                        case R.id.action_settings: {
                            intent = new Intent(MedicamentActivity.this, ParametresActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                    return true;
                }
        );
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Liste des médicaments");
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

}