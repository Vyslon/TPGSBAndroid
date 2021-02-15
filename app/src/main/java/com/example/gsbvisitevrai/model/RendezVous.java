package com.example.gsbvisitevrai.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Classe métier, implémente Parcelable pour pouvoir afficher un médicament dans l'IHM
 */
public class RendezVous implements Parcelable {
    private int numero;
    private String dateRdv;
    private String heure;
    private Praticien praticien;

    /**
     * Constructeur.
     * @param numero
     * @param dateRdv
     * @param heure
     * @param praticien
     */
    public RendezVous(int numero, String dateRdv, String heure, Praticien praticien) {
        this.numero = numero;
        this.dateRdv = dateRdv;
        this.heure = heure;
        this.praticien = praticien;
    }

    protected RendezVous(Parcel in) {
        numero = in.readInt();
        dateRdv = in.readString();
        heure = in.readString();
        praticien = in.readParcelable(Praticien.class.getClassLoader());
    }

    public static final Creator<RendezVous> CREATOR = new Creator<RendezVous>() {
        @Override
        public RendezVous createFromParcel(Parcel in) {
            return new RendezVous(in);
        }

        @Override
        public RendezVous[] newArray(int size) {
            return new RendezVous[size];
        }
    };

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDate() {
        return dateRdv;
    }

    public void setDate(String date) {
        this.dateRdv = dateRdv;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public Praticien getPraticien() {
        return praticien;
    }

    public void setPraticien(Praticien praticien) {
        this.praticien = praticien;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getNumero());
        dest.writeString(getDate());
        dest.writeString(getHeure());
        dest.writeParcelable(getPraticien(), flags);
    }
}
