package cz.itnetwork.models;


import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpravceOsob {

    // URL pro připojení k databázi MySQL
    private String jdbcURL = "jdbc:mysql://localhost/evidence_pojistenych?user=root&password=";
    // MySQL CRUD příkazy
    private static final String CREATE_USER_SQL = "INSERT INTO osoby (jmeno, prijmeni, email, telefonni_cislo, ulice_a_cp, mesto, psc) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_USER_BY_ID_SQL = "SELECT * FROM osoby WHERE osoba_id=?";
    private static final String UPDATE_USER_SQL = "UPDATE osoby SET jmeno=?, prijmeni=?, email=?, telefonni_cislo=?, ulice_a_cp=?, mesto=?, psc=? WHERE osoba_id=?";
    private static final String DELETE_USER_SQL = "DELETE FROM osoby WHERE osoba_id=?"; // v MySQL je nastaveno automatické mazání pojištění mazané osoby pomocí ON DELETE CASCADE;
    private static final String READ_ALL_SQL = "SELECT * FROM osoby";

    // CREATE přidá osobu do seznamu pojištených
    public void insertOsobu(Osoba osoba) {
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
            PreparedStatement dotaz = spojeni.prepareStatement(CREATE_USER_SQL);) {
            System.out.println(dotaz);
            dotaz.setString(1, osoba.getJmeno());
            dotaz.setString(2, osoba.getPrijmeni());
            dotaz.setString(3, osoba.getEmail());
            dotaz.setString(4, osoba.getTelefonniCislo());
            dotaz.setString(5, osoba.getUliceACP());
            dotaz.setString(6, osoba.getMesto());
            dotaz.setString(7, osoba.getPsc());
            dotaz.executeUpdate();
            System.out.println("Osoba uložena");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        }
    }

    // READ vyhledá osobu podle ID v seznamu pojištěných
    public Osoba selectOsobu(int osoba_id) {
        Osoba osoba = null;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
            PreparedStatement dotaz = spojeni.prepareStatement(READ_USER_BY_ID_SQL);) {
            dotaz.setInt(1, osoba_id);
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int id = vysledky.getInt("osoba_id");
                String jmeno = vysledky.getString("jmeno");
                String prijmeni = vysledky.getString("prijmeni");
                String email = vysledky.getString("email");
                String telCislo = vysledky.getString("telefonni_cislo");
                String uliceACP = vysledky.getString("ulice_a_cp");
                String mesto = vysledky.getString("mesto");
                String psc = vysledky.getString("psc");
                osoba = new Osoba(id, jmeno, prijmeni, email, telCislo, uliceACP, mesto, psc);
            }
            System.out.println("Pojištěnec načten.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return osoba;
    }

    // UPDATE upraví osobu v seznamu pojištěných
    public boolean updateOsobu(Osoba osoba) {
        boolean rowUpdated = false;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
            PreparedStatement dotaz = spojeni.prepareStatement(UPDATE_USER_SQL);) {
            System.out.println(dotaz);
            dotaz.setString(1, osoba.getJmeno());
            dotaz.setString(2, osoba.getPrijmeni());
            dotaz.setString(3, osoba.getEmail());
            dotaz.setString(4, osoba.getTelefonniCislo());
            dotaz.setString(5, osoba.getUliceACP());
            dotaz.setString(6, osoba.getMesto());
            dotaz.setString(7, osoba.getPsc());
            dotaz.setInt(8, osoba.getOsoba_id());
            rowUpdated = dotaz.executeUpdate() > 0;
            System.out.println("Pojištěnec upraven.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return rowUpdated;
    }

    // DELETE smaže osobu v seznamu pojištěných
    public boolean deleteOsobu(int osoba_id) {
        boolean rowDeleted = false;
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
            PreparedStatement dotaz = spojeni.prepareStatement(DELETE_USER_SQL);) {
            dotaz.setInt(1, osoba_id);
            System.out.println(dotaz);
            rowDeleted = dotaz.executeUpdate() > 0;
            System.out.println("Pojištěnec smazán z databáze.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return rowDeleted;
    }

    // Vypíše seznam všech pojištěných
    public List<Osoba> readSeznamOsob() {
        List<Osoba> seznamOsob = new ArrayList<>();
        try (Connection spojeni = DriverManager.getConnection(jdbcURL);
            PreparedStatement dotaz = spojeni.prepareStatement(READ_ALL_SQL);) {
            System.out.println(dotaz);
            ResultSet vysledky = dotaz.executeQuery();
            while (vysledky.next()) {
                int osoba_id = vysledky.getInt("osoba_id");
                String jmeno = vysledky.getString("jmeno");
                String prijmeni = vysledky.getString("prijmeni");
                String email = vysledky.getString("email");
                String telCislo = vysledky.getString("telefonni_cislo");
                String uliceACP = vysledky.getString("ulice_a_cp");
                String mesto = vysledky.getString("mesto");
                String psc = vysledky.getString("psc");
                seznamOsob.add(new Osoba (osoba_id, jmeno, prijmeni, email, telCislo, uliceACP, mesto, psc));
            }
            System.out.println("Seznam pojištěnců načten.");
        } catch (SQLException ex) {
            System.out.println("Chyba při komunikaci s databází.");
        } return seznamOsob;
    }

}
