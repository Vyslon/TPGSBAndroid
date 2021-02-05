package com.example.gsbvisitevrai.view;

import android.content.Intent;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.gsbvisitevrai.R;
import com.example.gsbvisitevrai.controller.RendezVousController;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.RendezVous;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class listeRDV extends AppCompatActivity {
    private ArrayList<RendezVous> lesRDV;

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
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                System.out.println(")))))))))))))))" + clickedDayCalendar.HOUR + ":" + clickedDayCalendar.MINUTE);
                Intent intent = new Intent(listeRDV.this, DetailJourActivity.class);
                Bundle bundleRDV = new Bundle();
                bundleRDV.putParcelableArrayList("lesRendezVous", lesRDV);
                intent.putExtras(bundleRDV);
                startActivity(intent);
            }
        });
        ecouteRetour();
    }



    private void ecouteRetour() {
        ((ImageButton) findViewById(R.id.btnRetourdeListeRDV)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}