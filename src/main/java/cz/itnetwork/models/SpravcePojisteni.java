package cz.itnetwork.models;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpravcePojisteni {

    // URL pro připojení k databázi MySQL
    private String jdbcURL = "jdbc:mysql://localhost/evidence_pojistenych?user=root&password=";
    // MySQL CRUD příkazy
    private static final String CREATE_POJISTENI_SQL = "INSERT INTO pojisteni (druh_pojisteni, osoba_id, castka, predmet_pojisteni, platnost_od, platnost_do) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String READ_POJISTENI_BY_ID_SQL = "SELECT * FROM pojisteni WHERE pojisteni_id=?";
    private static final String UPDATE_POJISTENI_SQL = "UPDATE pojisteni SET druh_pojisteni=?, castka=?, predmet_pojisteni=?, platnost_od=?, platnost_do=? WHERE pojisteni_id=?";
    private static final String DELETE_POJISTENI_SQL = "DELETE FROM pojisteni WHERE pojisteni_id=?";
    private static final String READ_ALL_SQL = "SELECT * FROM pojisteni";
    private static final String READ_VSECHNA_POJISTENI_OSOBY_SQL = "SELECT * FROM pojisteni WHERE osoba_id=?";;

    // CREATE vytvoří nové pojištění
    public void insertPojisteni(Pojisteni pojisteni) {
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(CREATE_POJISTENI_SQL);) {
            System.out.println(dotaz);
            dotaz.setString(1, pojisteni.getDruh_pojisteni());
            dotaz.setInt(2, pojisteni.getOsoba_id());
            dotaz.setInt(3, pojisteni.getCastka());
            dotaz.setString(4, pojisteni.getPredmet_pojisteni());
            dotaz.setString(5, pojisteni.getPlatnost_od());
            dotaz.setString(6, pojisteni.getPlatnost_do());
            dotaz.executeUpdate();
            System.out.println("Pojištění uloženo.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        }
    }

    // READ vyhledá pojištění podle ID
    public Pojisteni selectPojisteni(int pojisteni_id) {
        Pojisteni pojisteni = null;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(READ_POJISTENI_BY_ID_SQL);) {
            dotaz.setInt(1, pojisteni_id);
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int id = vysledky.getInt("pojisteni_id");
                String druh_pojisteni = vysledky.getString("druh_pojisteni");
                int osoba_id = vysledky.getInt("osoba_id");
                int castka = vysledky.getInt("castka");
                String predmet_pojisteni = vysledky.getString("predmet_pojisteni");
                String platnost_od = vysledky.getString("platnost_od");
                String platnost_do = vysledky.getString("platnost_do");
                pojisteni = new Pojisteni(id, druh_pojisteni, osoba_id, castka, predmet_pojisteni, platnost_od, platnost_do);
            }
            System.out.println("Pojištění načteno.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return pojisteni;
    }

    // UPDATE upraví pojištění
    public boolean updatePojisteni(Pojisteni pojisteni) {
        boolean rowUpdated = false;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(UPDATE_POJISTENI_SQL);) {
            System.out.println(dotaz);
            dotaz.setString(1, pojisteni.getDruh_pojisteni());
            dotaz.setInt(2, pojisteni.getCastka());
            dotaz.setString(3, pojisteni.getPredmet_pojisteni());
            dotaz.setString(4, pojisteni.getPlatnost_od());
            dotaz.setString(5, pojisteni.getPlatnost_do());
            dotaz.setInt(6, pojisteni.getPojisteni_id());
            rowUpdated = dotaz.executeUpdate() > 0;
            System.out.println("Pojištění upraveno.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return rowUpdated;
    }

    // DELETE smaže pojištění
    public boolean deletePojisteni(int pojisteni_id) {
        boolean rowDeleted = false;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(DELETE_POJISTENI_SQL);) {
            dotaz.setInt(1, pojisteni_id);
            System.out.println(dotaz);
            rowDeleted = dotaz.executeUpdate() > 0;
            System.out.println("Pojištění smazáno z databáze.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return rowDeleted;
    }

    // Vypíše seznam všech pojištění
    public List<Pojisteni> readSeznamPojisteni() {
        List<Pojisteni> seznamVsechPojisteni = new ArrayList<>();
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(READ_ALL_SQL);) {
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int pojisteni_id = vysledky.getInt("pojisteni_id");
                String druh_pojisteni = vysledky.getString("druh_pojisteni");
                int osoba_id = vysledky.getInt("osoba_id");
                int castka = vysledky.getInt("castka");
                String predmet_pojisteni = vysledky.getString("predmet_pojisteni");
                String platnost_od = vysledky.getString("platnost_od");
                String platnost_do = vysledky.getString("platnost_do");
                seznamVsechPojisteni.add(new Pojisteni (pojisteni_id, druh_pojisteni, osoba_id, castka, predmet_pojisteni, platnost_od, platnost_do));
            }
            System.out.println("Seznam druhy pojištění načten.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return seznamVsechPojisteni;
    }

    // Vypíše seznam všech pojištění určitě osoby podle jejího ID
    public List<Pojisteni> readVsechnaPojisteniOsoby(int osoba_id) {
        List<Pojisteni> seznamPojisteni = new ArrayList<>();
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(READ_VSECHNA_POJISTENI_OSOBY_SQL);) {
            dotaz.setInt(1, osoba_id);
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int pojisteni_id = vysledky.getInt("pojisteni_id");
                String druh_pojisteni = vysledky.getString("druh_pojisteni");
                int castka = vysledky.getInt("castka");
                String predmet_pojisteni = vysledky.getString("predmet_pojisteni");
                String platnost_od = vysledky.getString("platnost_od");
                String platnost_do = vysledky.getString("platnost_do");
                seznamPojisteni.add(new Pojisteni (pojisteni_id, druh_pojisteni, osoba_id, castka, predmet_pojisteni, platnost_od, platnost_do));
            }
            System.out.println("Seznam druhy pojištění načten.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return seznamPojisteni;
    }

}
