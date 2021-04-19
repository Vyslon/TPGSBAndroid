package com.example.gsbvisitevrai.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.controller.PraticienController;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.Praticien;
import com.example.gsbvisitevrai.model.RendezVous;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class listeRDV extends AppCompatActivity {
    private ArrayList<RendezVous> lesRDV;
    ArrayList<Medicament> medicaments;
    ArrayList<Praticien> praticiens;
    ArrayList<RendezVous> rendezVous;
    Bundle bundleRDV = new Bundle();
    Bundle bundleMedocs = new Bundle();
    Bundle bundlePraticiens = new Bundle();

    /**
     * Appeler lors de l'ouverture de la page, affiche les rendez-vous dans l'agenda
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_r_d_v);
        List<EventDay> events = new ArrayList<>();
        RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
        ArrayList<RendezVous> lesRDV = rendezVousController.rendezVous();
        Calendar calendar;
        String minuteHeure[];
        String jourMoisAnnee[];
        int heure;
        for (RendezVous rdv: lesRDV) {
            calendar = Calendar.getInstance();
            calendar.set(Calendar.SECOND, 0);
            minuteHeure = rdv.getHeure().split(":");
            heure = Integer.parseInt(minuteHeure[0]);
            calendar.set(Calendar.MINUTE, Integer.parseInt(minuteHeure[1]));
            calendar.set(Calendar.HOUR, heure);
            calendar.set(Calendar.AM_PM, heure <= 12 & heure != 0 ? Calendar.AM : Calendar.PM);
            jourMoisAnnee = rdv.getDate().split("/");
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(jourMoisAnnee[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(jourMoisAnnee[1]) - 1);
            calendar.set(Calendar.YEAR, Integer.parseInt(jourMoisAnnee[2]));
            events.add(new EventDay(calendar, R.drawable.msg));
        }
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setEvents(events);
        List<Calendar> selectedDates = calendarView.getSelectedDates();
        // Gère l'affichage des détails pour le jour cliqué
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                System.out.println(")))))))))))))))" + clickedDayCalendar.HOUR + ":" + clickedDayCalendar.MINUTE);
                Intent intent = new Intent(listeRDV.this, DetailJourActivity.class);
                Bundle bundleRDV = new Bundle();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                bundleRDV.putParcelableArrayList("lesRendezVous", rendezVousController.rendezVous(sdf.format(clickedDayCalendar.getTime())));
                intent.putExtras(bundleRDV);
                startActivity(intent);
            }
        });

        ecouteAjouterRDV();
        //1 - Configuration Toolbar
        this.configureToolbar();
        //2 - Configuration Navigation View
        this.configureNavigation();
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Mes rendez-vous");
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureNavigation() {
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_accueil);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                (item) -> {
                    Intent intent;
                    switch (item.getItemId()) {
                        case R.id.action_accueil:
                            intent = new Intent(listeRDV.this, listeRDV.class);
                            startActivity(intent);
                            break;
                        case R.id.action_medicine: {
                            intent = new Intent(listeRDV.this, MedicamentActivity.class);
                            startActivity(intent);
                            break;
                        }
                        case R.id.action_settings: {
                            intent = new Intent(listeRDV.this, ParametresActivity.class);
                            startActivity(intent);
                            break;
                        }
                    }
                    return true;
                }
        );
    }

    private void ecouteAjouterRDV() {
        ((FloatingActionButton) findViewById(R.id.fabAjouter)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(listeRDV.this, priseRDV.class);
                PraticienController praticienController = PraticienController.getInstance(getBaseContext());
                praticiens = praticienController.praticiens();
                RendezVousController rendezVousController = RendezVousController.getInstance(getBaseContext());
                rendezVous = rendezVousController.rendezVous();
                bundleRDV.putParcelableArrayList("lesRendezVous", rendezVous);
                bundlePraticiens.putParcelableArrayList("lesPraticiens", praticiens);
                intent.putExtras(bundleRDV);
                intent.putExtras(bundlePraticiens);
                startActivity(intent);
            }
        });
    }
}