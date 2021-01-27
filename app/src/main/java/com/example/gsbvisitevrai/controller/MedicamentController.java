package com.example.gsbvisitevrai.controller;

import android.content.Context;
import com.example.gsbvisitevrai.model.Medicament;
import com.example.gsbvisitevrai.model.MedicamentDAO;

import java.util.ArrayList;

public final class MedicamentController {
    private static MedicamentController medicamentController = null;
    private static ArrayList<Medicament> medicaments;

    private MedicamentController() {
        super();
    }

    public static final MedicamentController getInstance(Context context) {
        if (MedicamentController.medicamentController == null) {
            MedicamentController.medicamentController = new MedicamentController();
            medicaments = new ArrayList<>();
            MedicamentDAO medicamentsDAO = new MedicamentDAO(context);
            medicaments = medicamentsDAO.read();
        }
        return MedicamentController.medicamentController;
    }

    public Medicament getMedicament(int index) {
        return medicaments.get(index);
    }

    public ArrayList<Medicament> medicaments () {
        return medicaments;
    }
}
