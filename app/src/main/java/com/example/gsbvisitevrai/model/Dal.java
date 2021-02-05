package com.example.gsbvisitevrai.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Classe Dal.
 * @author : B. CHATAING.
 * created on  26/01/2021.
 * modified on 31/01/2021.
 */
public class Dal {
    private static Dal dal = null;
    //Nom de la base stocké dans le dispositif.
    private static GsbDb createDb = null;

    /**
     * Constructeur privé : création d'une unique instance (singleton).
     */
    private Dal() {
        super();
    }

    /**
     * Crée la db dans le dispositif.
     * @param context : passé par l'activity
     */
    public static Dal getInstance(Context context) {
        if(Dal.dal == null) {
            Dal.dal=new Dal();
            SQLiteDatabase db;
            String dbNom = "GsbDb.sqlite";
            int version = 1;
            createDb = new GsbDb(context, dbNom, null, version);
            db = createDb.getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS medicament");
            createDb.onCreate(db);
        }
        return Dal.dal;
    }


    /**
     * Accès à la db créée.
     * @return instance de GsbDb (création de la db).
     */
    public GsbDb getCreateDb() {
        return createDb;
    }

}
