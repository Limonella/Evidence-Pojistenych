package cz.itnetwork.models;

public class Osoba {

    private int osoba_id;
    private String jmeno;
    private String prijmeni;
    private int vek;
    private String telefonniCislo;

    public Osoba(String jmeno, String prijmeni, int vek, String telefonniCislo) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek =  vek;
        this.telefonniCislo = telefonniCislo;
    }

    public Osoba(int osoba_id, String jmeno, String prijmeni, int vek, String telefonniCislo) {
        this.osoba_id = osoba_id;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek =  vek;
        this.telefonniCislo = telefonniCislo;
    }

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

    public int getVek() {
        return vek;
    }

    public void setVek(int vek) {
        this.vek = vek;
    }

    public String getTelefonniCislo() {
        return telefonniCislo;
    }

    public void setTelefonniCislo(String telefonniCislo) {
        this.telefonniCislo = telefonniCislo;
    }

    @Override
    public String toString() {
        return "\t" + jmeno + "\t" + prijmeni + "\t" + vek + "\t" + telefonniCislo;
    }

}
