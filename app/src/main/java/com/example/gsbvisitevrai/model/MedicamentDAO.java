package com.example.gsbvisitevrai.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Classe MedicamentDAO.
 * @author : B. CHATAING.
 * created on  26/01/2021.
 * modified on .
 */
public class MedicamentDAO {
    private Dal dal;
    private SQLiteDatabase db;

    public MedicamentDAO(Context context) {

        dal = new Dal(context);
    }

    /**
     * C du Crud.
     */
    public void create() {
        db = dal.getCreateDb().getWritableDatabase();
        String req = "insert into medicament values ("
                + "'3MYC7',"
                + "'TRIMYCINE',"
                + "'Triamcinolone (acétonide) + Néomycine + Nystatine',"
                + "'Redoutable',"
                + "'Ce médicament est contre-indiqué en cas de trouble du sommeil',"
                + "16)";
        db.execSQL(req);
        req = "insert into medicament values ("
                + "'ADIMOL9',"
                + "'ADIMOL',"
                + "'Amoxicilline + Acide clavulanique',"
                + "'Puissant',"
                + "'Ce médicament est contre-indiqué en cas de trouble du sommeil',"
                + "27)";
        db.execSQL(req);
        req = "insert into medicament values ("
                + "'AMOPIL7',"
                + "'AMOPIL',"
                + "'Amoxicilline',"
                + "'Ce médicament est plus puissant que les pénicillines ',"
                + "'Ce médicament est contre-indiqué en cas de trouble du sommeil',"
                + "35)";
    }

    /**
     * R du Crud.
     * @return
     */
    public ArrayList<Medicament> read() {
        ArrayList<Medicament> medicaments = new ArrayList<>();
        Medicament medicament;
        db = dal.getCreateDb().getReadableDatabase();
        String req = "Select * from medicament";
        Cursor cursor = db.rawQuery(req,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            String depot = cursor.getString(0);
            String nom = cursor.getString(1);
            String composition = cursor.getString(2);
            String effet = cursor.getString(3);
            String contreindic = cursor.getString(4);
            Float prix = cursor.getFloat(5);
            medicament = new Medicament(depot,nom,composition,prix.doubleValue());
            medicaments.add(medicament);
        }
        return medicaments;
    }

}
