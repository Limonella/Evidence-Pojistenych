package cz.itnetwork.models;

import java.text.DateFormat;

public class Pojisteni {

    private int pojisteni_id;
    private String druh_pojisteni;
    private int osoba_id;
    private int castka;
    private String predmet_pojisteni;
    private String platnost_od;
    private String platnost_do;

    // kompletní konstruktor
    public Pojisteni(int pojisteni_id, String druh_pojisteni, int osoba_id, int castka, String predmet_pojisteni, String platnost_od, String platnost_do) {
        this.pojisteni_id = pojisteni_id;
        this.druh_pojisteni = druh_pojisteni;
        this.osoba_id = osoba_id;
        this.castka = castka;
        this.predmet_pojisteni = predmet_pojisteni;
        this.platnost_od = platnost_od;
        this.platnost_do = platnost_do;
    }

    // konstruktor bez pojisteni_id
    public Pojisteni(String druh_pojisteni, int osoba_id, int castka, String predmet_pojisteni, String platnost_od, String platnost_do) {
        this.druh_pojisteni = druh_pojisteni;
        this.osoba_id = osoba_id;
        this.castka = castka;
        this.predmet_pojisteni = predmet_pojisteni;
        this.platnost_od = platnost_od;
        this.platnost_do = platnost_do;
    }

    // prázdný konstruktor
    public Pojisteni() {

    }

    public int getPojisteni_id() {
        return pojisteni_id;
    }

    public void setPojisteni_id(int pojisteni_id) {
        this.pojisteni_id = pojisteni_id;
    }

    public String getDruh_pojisteni() {
        return druh_pojisteni;
    }

    public void setDruh_pojisteni(String druh_pojisteni) {
        this.druh_pojisteni = druh_pojisteni;
    }

    public int getOsoba_id() {
        return osoba_id;
    }

    public void setOsoba_id(int osoba_id) {
        this.osoba_id = osoba_id;
    }

    public int getCastka() {
        return castka;
    }

    public void setCastka(int castka) {
        this.castka = castka;
    }

    public String getPredmet_pojisteni() {
        return predmet_pojisteni;
    }

    public void setPredmet_pojisteni(String predmet_pojisteni) {
        this.predmet_pojisteni = predmet_pojisteni;
    }

    public String getPlatnost_od() {
        return platnost_od;
    }

    public void setPlatnost_od(String platnost_od) {
        this.platnost_od = platnost_od;
    }

    public String getPlatnost_do() {
        return platnost_do;
    }

    public void setPlatnost_do(String platnost_do) {
        this.platnost_do = platnost_do;
    }

    @Override
    public String toString() {
        return "Pojisteni{" +
                "pojisteni_id=" + pojisteni_id +
                ", druh_pojisteni='" + druh_pojisteni + '\'' +
                ", osoba_id=" + osoba_id +
                ", castka=" + castka +
                ", predmet_pojisteni='" + predmet_pojisteni + '\'' +
                ", platnost_od=" + platnost_od +
                ", platnost_do=" + platnost_do +
                '}';
    }
}
