package cz.itnetwork.models;

public class Pojisteni {

    private int pojisteni_id;
    private String druh;
    private String detail;

    public Pojisteni(int pojisteni_id, String druh, String detail) {
        this.pojisteni_id = pojisteni_id;
        this.druh = druh;
        this.detail = detail;
    }

    public Pojisteni(String druh, String detail) {
        this.druh = druh;
        this.detail = detail;
    }

    public Pojisteni() {

    }

    public int getPojisteni_id() {
        return pojisteni_id;
    }

    public void setPojisteni_id(int pojisteni_id) {
        this.pojisteni_id = pojisteni_id;
    }

    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "SpravcePojisteni{" +
                "druh='" + druh + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

}
