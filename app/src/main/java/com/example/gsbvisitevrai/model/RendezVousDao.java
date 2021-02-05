package com.example.gsbvisitevrai.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Classe RendezVousDAO.
 * @author : B. CHATAING.
 * created on  31/01/2021.
 * modified on 01/02/2021.
 */
public class RendezVousDao {
    final private Dal dal;
    private SQLiteDatabase db;
    private Context context;

    public RendezVousDao(Context context) {
        this.context = context;
        this.dal = Dal.getInstance(context);
        // todo create();
    }

    public RendezVousDao(Context context,SQLiteDatabase db){
        this.context = context;
        this.dal = Dal.getInstance(context);
        this.db = db;
    }

    /**
     * C du Crud.
     */
    public void create(String date, String heure, String idPraticien) {
        db = dal.getCreateDb().getWritableDatabase();
/*        if (read().size() == 0) {
            String req = "insert into rendezvous values ("
                    + "1,"
                    + "'2021/02/01',"
                    + "'14:00',"
                    + "1)";
            db.execSQL(req);
            req = "insert into rendezvous values ("
                    + "2,"
                    + "'2021/02/01',"
                    + "'16:00',"
                    + "2)";
            db.execSQL(req);
            req = "insert into rendezvous values ("
                    + "3,"
                    + "'2021/02/02',"
                    + "'10:00',"
                    + "3)";
            db.execSQL(req);
        }*/
        String req = "insert into rendezvous (date, heure, idPraticien) values ("
                + "'" + date + "'"
                + ", "
                + "'" + heure + "'"
                +", "
                + idPraticien
                + ")";
        db.execSQL(req);
    }

    /**
     * R du Crud.
     * @return arraylist contenant les rendez-vous de la table rendez-vous.
     */
    public ArrayList<RendezVous> read() {
        ArrayList<RendezVous> rendezVous = new ArrayList<>();
        RendezVous leRendezVous;
        db = dal.getCreateDb().getReadableDatabase();
        String req = "Select * from rendezvous";
        Cursor cursor = db.rawQuery(req,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            int numero = cursor.getInt(0);
            String dateRdv = cursor.getString(1);
            String heureRdv = cursor.getString(2);
            int praticienId = cursor.getInt(3);
            PraticienDao praticienDao = new PraticienDao(context,db);
            Praticien praticien = praticienDao.findById(context,praticienId);
            leRendezVous = new RendezVous(numero,dateRdv,heureRdv,praticien);
            rendezVous.add(leRendezVous);
            cursor.moveToNext();
        }
        cursor.close();
        return rendezVous;
    }
}
