package com.example.gsbvisitevrai.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class GsbDb extends SQLiteOpenHelper {
    String medicament = "CREATE TABLE medicament ("
            + "depotlegal TEXT PRIMARY KEY,"
            + "nomcommercial TEXT,"
            + "composition TEXT,"
            + "effet TEXT,"
            + "contreindication TEXT,"
            + "prix REAL)";

    public GsbDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS medicament");
        db.execSQL(medicament);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
