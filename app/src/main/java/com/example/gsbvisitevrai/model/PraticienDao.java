package com.example.gsbvisitevrai.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Classe PraticienDAO.
 * @author : B. CHATAING.
 * created on  31/01/2021.
 * modified on 01/02/2021.
 */
public class PraticienDao {
    final private Dal dal;
    private SQLiteDatabase db;

    public PraticienDao(Context context) {

        this.dal = Dal.getInstance(context);
        create();
    }

    public PraticienDao(Context context, SQLiteDatabase db){
        this.dal = Dal.getInstance(context);
        this.db = db;
    }

    /**
     * C du Crud.
     */
    public void create() {
        db = dal.getCreateDb().getWritableDatabase();
        if (read().size() == 0) {
            String req = "insert into praticien values ("
                    + "1,"
                    + "'Notinie',"
                    + "'Alain',"
                    + "'114 rue Authie',"
                    + "'85000',"
                    + "'La Roche Sur Yon',"
                    + "'06-14-90-65-02',"
                    + "16)";
            db.execSQL(req);
            req = "insert into praticien values ("
                    + "2,"
                    + "'Gosselin',"
                    + "'Albert',"
                    + "'13 rue Devon',"
                    + "'41000',"
                    + "'Blois',"
                    + "'06-22-64-35-02',"
                    + "10)";
            db.execSQL(req);
            req = "insert into praticien values ("
                    + "3,"
                    + "'Delahaye',"
                    + "'André',"
                    + "'36 quai des Orfèvres',"
                    + "'75000',"
                    + "'Paris',"
                    + "'06-14-90-65-02',"
                    + "20)";
            db.execSQL(req);
        }
    }

    /**
     * R du Crud.
     * @return arraylist contenant les praticiens de la table praticien.
     */
    public ArrayList<Praticien> read() {
        ArrayList<Praticien> Praticiens = new ArrayList<>();
        Praticien Praticien;
        db = dal.getCreateDb().getReadableDatabase();
        String req = "Select * from praticien";
        Cursor cursor = db.rawQuery(req,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int numero = cursor.getInt(0);
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String adresse = cursor.getString(3);
            String codePostal = cursor.getString(4);
            String ville = cursor.getString(5);
            String telephone = cursor.getString(6);
            int coefNotoriete = cursor.getInt(7);
            Praticien = new Praticien(numero,nom,prenom,adresse,codePostal,ville,telephone,coefNotoriete);
            Praticiens.add(Praticien);
            cursor.moveToNext();
        }
        cursor.close();
        return Praticiens;
    }

    /**
     * R du CRUD.
     * Retourne le praticien dont l'id est passé en paramètre.
     * @param context : nécessaire à RendezVousDao.
     * @param id : id du praticien recherché.
     * @return : le praticien trouvé.
     */
    public Praticien findById(Context context, int id) {
        String[] listCol = {"numero", "nom", "prenom", "adresse", "codepostal","ville","telephone","coefnotoriete"};
        String whereCond = "numero=?";
        String[] whereArg = {Integer.toString(id)};

        Cursor result = db.query ("praticien", listCol, whereCond, whereArg, null, null,
                null);

        Praticien praticien = null;
        db = dal.getCreateDb().getReadableDatabase();
        result.moveToFirst();
        if(!result.isAfterLast()) {
            int numero = result.getInt(0);
            String nom = result.getString(1);
            String prenom = result.getString(2);
            String adresse = result.getString(3);
            String codePostal = result.getString(4);
            String ville = result.getString(5);
            String telephone = result.getString(6);
            int coefnotoriete = result.getInt(7);
            praticien = new Praticien(numero,nom,prenom,adresse,codePostal,ville,telephone,coefnotoriete);
        }
        return praticien;
    }
}
