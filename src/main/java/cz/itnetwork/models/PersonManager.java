package cz.itnetwork.models;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonManager {

    // MySQL CRUD
    private static final String INSERT_USER_SQL = "INSERT INTO persons (name, surname, email, phone_number, street_name_and_building_number, city, zip_code) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM persons WHERE person_id=?";
    private static final String UPDATE_USER_SQL = "UPDATE persons SET name=?, surname=?, email=?, phone_number=?, street_name_and_building_number=?, city=?, zip_code=? WHERE person_id=?";
    private static final String DELETE_USER_SQL = "DELETE FROM persons WHERE person_id=?"; // deletes person and all of his insurances (ON DELETE CASCADE - MySQL)
    private static final String SELECT_ALL_SQL = "SELECT * FROM persons";
    // URL for connection to MySQL
    private final String jdbcURL = "jdbc:mysql://localhost/records_of_insured_persons?user=root&password=";

    // CREATE adds person to the database
    public void insertPerson(Person person) {
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getPhoneNumber());
            preparedStatement.setString(5, person.getStreetNameABN());
            preparedStatement.setString(6, person.getCity());
            preparedStatement.setString(7, person.getZipCode());
            preparedStatement.executeUpdate();
            System.out.println("Person successfully saved.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
    }

    // READ selects person from the database by ID
    public Person selectPerson(int personId) {
        Person person = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, personId);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String streetNameABN = resultSet.getString("street_name_and_building_number");
                String city = resultSet.getString("city");
                String zipCode = resultSet.getString("zip_code");
                person = new Person(id, name, surname, email, phoneNumber, streetNameABN, city, zipCode);
            }
            System.out.println("Personal details successfully loaded.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return person;
    }

    // UPDATE edits person from the database
    public boolean updatePerson(Person person) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getPhoneNumber());
            preparedStatement.setString(5, person.getStreetNameABN());
            preparedStatement.setString(6, person.getCity());
            preparedStatement.setString(7, person.getZipCode());
            preparedStatement.setInt(8, person.getPersonId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
            System.out.println("Insured person successfully updated.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return rowUpdated;
    }

    // DELETE deletes person from the database
    public boolean deletePerson(int personId) {
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, personId);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            System.out.println("Insured person successfully deleted from the database.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return rowDeleted;
    }

    // shows list of all insured persons
    public List<Person> selectListOfPersons() {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String streetNameABN = resultSet.getString("street_name_and_building_number");
                String city = resultSet.getString("city");
                String zipCode = resultSet.getString("zip_code");
                listOfPersons.add(new Person(personId, name, surname, email, phoneNumber, streetNameABN, city, zipCode));
            }
            System.out.println("List of insured persons successfully loaded.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return listOfPersons;
    }

}
