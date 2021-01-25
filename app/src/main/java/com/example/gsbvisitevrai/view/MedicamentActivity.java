package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gsbvisitevrai.R;

public class MedicamentActivity extends AppCompatActivity {
    ListView lvMedicaments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicament);
        lvMedicaments = (ListView)findViewById(R.id.listMedicaments);
        String[] listeStrings = {"Aspirine", "Doliprane", "Ibuprofène"};

        lvMedicaments.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));
        ecouteRetour();
    }

    private void ecouteRetour() {
        ((ImageButton)findViewById(R.id.btnRetourdeMedic)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MedicamentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        lvMedicaments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=(String)lvMedicaments.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}