package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.model.Medicament;

public class DetailMedicamentActivity extends AppCompatActivity {
    private TextView txtNom;
    private TextView txtEffet;
    private TextView txtPrix;
    private TextView txtDepotLegal;
    private TextView txtComposition;
    private TextView txtContreIndication;

    /**
     * Appeler lors de l'ouverture de la page, rempli les textview
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_medicament);
        txtNom = (TextView)this.findViewById(R.id.txtDetNomCommercial);
        txtEffet = (TextView)this.findViewById(R.id.txtDetEffet);
        txtPrix = (TextView)this.findViewById(R.id.txtDetPrix);
        txtDepotLegal = (TextView)this.findViewById(R.id.txtDetDepotLegal);
        txtComposition = (TextView)this.findViewById(R.id.txtDetComposition);
        txtContreIndication = (TextView)this.findViewById(R.id.txtDetContreIndication);
        Medicament medicament = getIntent().getParcelableExtra("medicament");
        txtNom.setText(medicament.getMNomCommercial());
        txtEffet.setText(medicament.getMEffet());
        txtDepotLegal.setText(medicament.getMDepotLegal());
        txtComposition.setText(medicament.getMComposition());
        txtContreIndication.setText(medicament.getMContreIndication());
        Double prix = medicament.getMPrixEchant();
        txtPrix.setText(prix.toString());
        gestionClic();
    }

    /**
     * Gère le retour arrière
     */
    private void gestionClic() {
        ((Button)this.findViewById(R.id.btnRetourdeDetailMedic)).setOnClickListener((v) -> {
            onBackPressed();
        });
    }
}