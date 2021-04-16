package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.desai.vatsal.mydynamictoast.MyDynamicToast;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class ParametresActivity extends AppCompatActivity {
    /**
     * Appeler lors de l'ouverture de la page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        ecouteSauvegarder();

        configureToolbar();
        configureNavigation();
    }

    /**
     * Sauvegarde le nouveau mot de passe
     */
    private void ecouteSauvegarder() {
        ((Button) findViewById(R.id.buttonSauvMDP)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if else pareil
                EditText passwordText = (EditText) findViewById(R.id.editTextNumberPassword);
                EditText secondPasswordText = (EditText) findViewById(R.id.editTextNumberPassword2);
                if (passwordText.getText().toString().compareTo(secondPasswordText.getText().toString()) == 0)
                {
                    MyDynamicToast.successMessage(ParametresActivity.this, "Mot de passe modifié");
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putString("password", passwordText.getText().toString());
                    editor.commit();

                    passwordText.setText("");
                    secondPasswordText.setText("");
                }
                else
                {
                    MyDynamicToast.errorMessage(ParametresActivity.this, "Les mots de passe saisis ne sont pas identiques");
                }
            }
        });
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Paramètres");
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureNavigation() {
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_settings);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                (item) -> {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_accueil:
                            intent = new Intent(ParametresActivity.this, listeRDV.class);
                            startActivity(intent);
                            break;
                        case R.id.action_medicine: {
                            intent = new Intent(ParametresActivity.this, MedicamentActivity.class);
                            startActivity(intent);
                            break;
                        }
                        case R.id.action_settings: {
                            intent = new Intent(ParametresActivity.this, ParametresActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                    return true;
                }
        );
    }
}
