package cz.itnetwork.models;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceManager {

    // MySQL CRUD
    private static final String CREATE_INSURANCE_SQL = "INSERT INTO insurances (type_of_insurance, person_id, amount, subject_of_insurance, valid_from, valid_until) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_INSURANCE_BY_ID_SQL = "SELECT * FROM insurances WHERE insurance_id=?";
    private static final String UPDATE_INSURANCE_SQL = "UPDATE insurances SET type_of_insurance=?, amount=?, subject_of_insurance=?, valid_from=?, valid_until=? WHERE insurance_id=?";
    private static final String DELETE_INSURANCE_SQL = "DELETE FROM insurances WHERE insurance_id=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM insurances";
    private static final String SELECT_ALL_INSURANCES_BY_PERSON_ID_SQL = "SELECT * FROM insurances WHERE person_id=?";
    // URL for connection to MySQL
    private final String jdbcURL = "jdbc:mysql://localhost/records_of_insured_persons?user=root&password=";

    // CREATE adds insurance to the database
    public void insertInsurance(Insurance insurance) {
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_INSURANCE_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, insurance.getTypeOfInsurance());
            preparedStatement.setInt(2, insurance.getPersonId());
            preparedStatement.setInt(3, insurance.getAmount());
            preparedStatement.setString(4, insurance.getSubjectOfInsurance());
            preparedStatement.setString(5, insurance.getValidFrom());
            preparedStatement.setString(6, insurance.getValidUntil());
            preparedStatement.executeUpdate();
            System.out.println("Insurance successfully saved.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
    }

    // READ selects insurance from the database by ID
    public Insurance selectInsurance(int insuranceId) {
        Insurance insurance = null;
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSURANCE_BY_ID_SQL)) {
            preparedStatement.setInt(1, insuranceId);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("insurance_id");
                String typeOfInsurance = resultSet.getString("type_of_insurance");
                int personId = resultSet.getInt("person_id");
                int amount = resultSet.getInt("amount");
                String subjectOfInsurance = resultSet.getString("subject_of_insurance");
                String validFrom = resultSet.getString("valid_from");
                String validUntil = resultSet.getString("valid_until");
                insurance = new Insurance(id, typeOfInsurance, personId, amount, subjectOfInsurance, validFrom, validUntil);
            }
            System.out.println("Insurance successfully loaded.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return insurance;
    }

    // UPDATE edits insurance in the database
    public boolean updateInsurance(Insurance insurance) {
        boolean rowUpdated = false;
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSURANCE_SQL)) {
            System.out.println(preparedStatement);
            preparedStatement.setString(1, insurance.getTypeOfInsurance());
            preparedStatement.setInt(2, insurance.getAmount());
            preparedStatement.setString(3, insurance.getSubjectOfInsurance());
            preparedStatement.setString(4, insurance.getValidFrom());
            preparedStatement.setString(5, insurance.getValidUntil());
            preparedStatement.setInt(6, insurance.getInsuranceId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
            System.out.println("Insurance successfully updated.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return rowUpdated;
    }

    // DELETE deletes insurance in the database
    public boolean deleteInsurance(int insuranceId) {
        boolean rowDeleted = false;
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSURANCE_SQL)) {
            preparedStatement.setInt(1, insuranceId);
            System.out.println(preparedStatement);
            rowDeleted = preparedStatement.executeUpdate() > 0;
            System.out.println("Insurance successfully deleted from the database.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return rowDeleted;
    }

    // shows list of all insurances
    public List<Insurance> selectListOfInsurances() {
        List<Insurance> listOfInsurances = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SQL)) {
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int insuranceId = resultSet.getInt("insurance_id");
                String typeOfInsurance = resultSet.getString("type_of_insurance");
                int personId = resultSet.getInt("person_id");
                int amount = resultSet.getInt("amount");
                String subjectOfInsurance = resultSet.getString("subject_of_insurance");
                String validFrom = resultSet.getString("valid_from");
                String validUntil = resultSet.getString("valid_until");
                listOfInsurances.add(new Insurance(insuranceId, typeOfInsurance, personId, amount, subjectOfInsurance, validFrom, validUntil));
            }
            System.out.println("List of insurances successfully loaded.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return listOfInsurances;
    }

    // shows list of all insurances by persons ID
    public List<Insurance> selectAllInsurancesOfPerson(int personId) {
        List<Insurance> listOfInsurancesOfPerson = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_INSURANCES_BY_PERSON_ID_SQL)) {
            preparedStatement.setInt(1, personId);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int insuranceId = resultSet.getInt("insurance_id");
                String typeOfInsurance = resultSet.getString("type_of_insurance");
                int amount = resultSet.getInt("amount");
                String subjectOfInsurance = resultSet.getString("subject_of_insurance");
                String validFrom = resultSet.getString("valid_from");
                String validUntil = resultSet.getString("valid_until");
                listOfInsurancesOfPerson.add(new Insurance(insuranceId, typeOfInsurance, personId, amount, subjectOfInsurance, validFrom, validUntil));
            }
            System.out.println("List of insurances of a person successfully loaded.");
        } catch (SQLException ex) {
            System.out.println("An error occurred while communicating with the database.");
        }
        return listOfInsurancesOfPerson;
    }

}
