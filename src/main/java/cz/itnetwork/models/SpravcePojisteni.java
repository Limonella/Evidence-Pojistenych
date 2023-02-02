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
    private static final String CREATE_POJISTENI_SQL = "INSERT INTO druh_pojisteni (druh, detail) VALUES (?, ?)";
    private static final String READ_POJISTENI_BY_ID_SQL = "SELECT * FROM druh_pojisteni WHERE pojisteni_id=?";
    private static final String UPDATE_POJISTENI_SQL = "UPDATE druh_pojisteni SET druh=?, detail=? WHERE pojisteni_id=?";
    private static final String DELETE_POJISTENI_SQL = "DELETE FROM druh_pojisteni WHERE pojisteni_id=?";
    private static final String READ_ALL_SQL = "SELECT * FROM druh_pojisteni";

    // CREATE přidá pojištění do seznamu druhy pojištění
    public void insertPojisteni(Pojisteni pojisteni) {
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(CREATE_POJISTENI_SQL);) {
            System.out.println(dotaz);
            dotaz.setString(1, pojisteni.getDruh());
            dotaz.setString(2, pojisteni.getDetail());
            dotaz.executeUpdate();
            System.out.println("Pojištění uloženo.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        }
    }

    // READ vyhledá pojištění podle ID v seznamu druhy pojištění
    public Pojisteni selectPojisteni(int pojisteni_id) {
        Pojisteni pojisteni = null;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(READ_POJISTENI_BY_ID_SQL);) {
            dotaz.setInt(1, pojisteni_id);
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int id = vysledky.getInt("pojisteni_id");
                String druh = vysledky.getString("druh");
                String detail = vysledky.getString("detail");
                pojisteni = new Pojisteni(id, druh, detail);
            }
            System.out.println("Pojištění načteno.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return pojisteni;
    }

    // UPDATE upraví pojištění v seznamu druhy pojištění
    public boolean updatePojisteni(Pojisteni pojisteni) {
        boolean rowUpdated = false;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(UPDATE_POJISTENI_SQL);) {
            System.out.println(dotaz);
            dotaz.setString(1, pojisteni.getDruh());
            dotaz.setString(2, pojisteni.getDetail());
            rowUpdated = dotaz.executeUpdate() > 0;
            System.out.println("Pojištění upraveno.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return rowUpdated;
    }

    // DELETE smaže pojištění v seznamu druhy pojištění
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

    // Vypiš seznam všech pojištěných
    public List<Pojisteni> readSeznamPojisteni() {
        List<Pojisteni> seznamPojisteni = new ArrayList<>();
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
             PreparedStatement dotaz = spojeni.prepareStatement(READ_ALL_SQL);) {
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int pojisteni_id = vysledky.getInt("pojisteni_id");
                String druh = vysledky.getString("druh");
                String detail = vysledky.getString("detail");
                seznamPojisteni.add(new Pojisteni (pojisteni_id, druh, detail));
            }
            System.out.println("Seznam druhy pojištění načten.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return seznamPojisteni;
    }

}
