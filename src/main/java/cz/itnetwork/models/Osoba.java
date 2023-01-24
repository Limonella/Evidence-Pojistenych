package cz.itnetwork.models;

public class Osoba {

    private int osoba_id;
    private String jmeno;
    private String prijmeni;
    private String email;
    private String telefonniCislo;
    private String uliceACP; // ulice a číslo popisné
    private String mesto;
    private String psc; // PSČ - poštovní směrovací číslo

    // kompletní konstruktor
    public Osoba(int osoba_id, String jmeno, String prijmeni, String email, String telefonniCislo, String uliceACP, String mesto, String psc) {
        this.osoba_id = osoba_id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefonniCislo = telefonniCislo;
        this.uliceACP = uliceACP;
        this.mesto = mesto;
        this.psc = psc;
    }

    // konstruktor bez id
    public Osoba(String jmeno, String prijmeni, String email, String telefonniCislo, String uliceACP, String mesto, String psc) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.email = email;
        this.telefonniCislo = telefonniCislo;
        this.uliceACP = uliceACP;
        this.mesto = mesto;
        this.psc = psc;
    }

    // prázdný konstruktor
    public Osoba() {
    }

    public int getOsoba_id() {
        return osoba_id;
    }

    public void setOsoba_id(int osoba_id) {
        this.osoba_id = osoba_id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonniCislo() {
        return telefonniCislo;
    }

    public void setTelefonniCislo(String telefonniCislo) {
        this.telefonniCislo = telefonniCislo;
    }

    public String getUliceACP() {
        return uliceACP;
    }

    public void setUliceACP(String uliceACP) {
        this.uliceACP = uliceACP;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    @Override
    public String toString() {
        return "\t" + jmeno + "\t" + prijmeni + "\t" + email + "\t" + telefonniCislo + "\t" + uliceACP + "\t" + mesto + "\t" + psc;
    }

}
