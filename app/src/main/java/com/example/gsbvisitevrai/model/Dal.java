package com.example.gsbvisitevrai.model;

import android.content.Context;

public class Dal {
    private String dbNom = "GsbDb.sqlite";
    private String version;
    private GsbDb createDb;

    public Dal(Context context) {
        createDb = new GsbDb(context, dbNom, null, 1);
    }
    
    public GsbDb getCreateDb() {
        return createDb;
    }
}
