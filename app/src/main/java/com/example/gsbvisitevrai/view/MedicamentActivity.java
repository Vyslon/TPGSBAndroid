package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.model.Medicament;

import java.util.ArrayList;

public class MedicamentActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lvMedicaments;
    com.example.gsbvisite.view.MedicamentListAdapter adapter;
    private ArrayList<Medicament> lesmedicaments;

    /**
     * Appeler lors de l'ouverture de la page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);
        creerListe();
        retourAcceuil();
    }

    /**
     * Gère le retour arrière
     */
    private void retourAcceuil() {
        ((ImageButton) findViewById(R.id.btnRetourDeMedic)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * Créer la liste de médicaments
     */
    private void creerListe(){
        Intent intent = getIntent();
        lesmedicaments =  intent.getParcelableArrayListExtra("lesMedocs");
        if (lesmedicaments != null){
            ListView lstHisto = (ListView)this.findViewById(R.id.listMedicaments);
             adapter = new com.example.gsbvisite.view.MedicamentListAdapter(this, lesmedicaments);
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
        Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MedicamentActivity.this, DetailMedicamentActivity.class);
        intent.putExtra("medicament", medicament);
        startActivity(intent);
    }

}