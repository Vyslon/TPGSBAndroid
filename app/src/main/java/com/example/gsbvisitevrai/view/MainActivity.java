package com.example.gsbvisitevrai.view;

import android.content.Intent;

import android.content.SharedPreferences;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.desai.vatsal.mydynamictoast.MyDynamicToast;
import com.example.gsbvisitevrai.R;


public class MainActivity extends AppCompatActivity {

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
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        String value = editText.getText().toString();
                        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                        if (value.compareTo(pref.getString("password", null)) == 0) {
                            Intent intent = new Intent(MainActivity.this, listeRDV.class);
                            startActivity(intent);
                        } else
                            MyDynamicToast.errorMessage(MainActivity.this, "Code erroné");
                    }
                    return false;
                }
        );
    }

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