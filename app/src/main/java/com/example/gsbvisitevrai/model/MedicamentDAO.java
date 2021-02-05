package com.example.gsbvisitevrai.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Classe MedicamentDAO.
 * @author : B. CHATAING.
 * created on  26/01/2021.
 * modified on 28/01/2021.
 */
public class MedicamentDAO {
    final private Dal dal;
    private SQLiteDatabase db;

    public MedicamentDAO(Context context) {
        this.dal = Dal.getInstance(context);
        create();
    }

    /**
     * C du Crud.
     */
    public void create() {
        db = dal.getCreateDb().getWritableDatabase();
        if (read().size() == 0) {
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
            db.execSQL(req);
        }
    }

    /**
     * R du Crud.
     * @return arraylist contenant les médicaments de la table medicament.
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
            medicament = new Medicament(depot,nom,composition,effet,contreindic,prix.doubleValue());
            medicaments.add(medicament);
            cursor.moveToNext();
        }
        cursor.close();
        return medicaments;
    }

}
