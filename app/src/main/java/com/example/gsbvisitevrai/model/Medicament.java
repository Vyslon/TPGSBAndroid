package com.example.gsbvisitevrai.model;

public class Medicament {
    private String MDepotLegal;
    private String MNomCommercial;
    private String MComposition;
    private Double MPrixEchant;

    public Medicament(String MDepotLegal, String MNomCommercial, String MComposition, Double MPrixEchant) {
        this.MDepotLegal = MDepotLegal;
        this.MNomCommercial = MNomCommercial;
        this.MComposition = MComposition;
        this.MPrixEchant = MPrixEchant;
    }

    public String getMDepotLegal() {
        return MDepotLegal;
    }

    public void setMDepotLegal(String MDepotLegal) {
        this.MDepotLegal = MDepotLegal;
    }

    public String getMNomCommercial() {
        return MNomCommercial;
    }

    public void setMNomCommercial(String MNomCommercial) {
        this.MNomCommercial = MNomCommercial;
    }

    public String getMComposition() {
        return MComposition;
    }

    public void setMComposition(String MComposition) {
        this.MComposition = MComposition;
    }

    public Double getMPrixEchant() {
        return MPrixEchant;
    }

    public void setMPrixEchant(Double MPrixEchant) {
        this.MPrixEchant = MPrixEchant;
    }

}
