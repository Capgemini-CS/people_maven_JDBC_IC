package com.person.repository;

import com.person.bean.CarBean;
import com.person.bean.PersonBean;
import com.person.connection.ConnectionManager;
import com.person.connection.MySqlConnectionManager;
import com.person.exception.DataBaseException;
import org.tinylog.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements Repository<PersonBean> {


    private ConnectionManager sqlConnection = new MySqlConnectionManager();

    @Override
    public List<PersonBean> getAll() throws DataBaseException {
        List<PersonBean> persons = new ArrayList<>();
        String personQuery = "SELECT * FROM persons";

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(personQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PersonBean person = PersonBean.builder()
                        .personId(resultSet.getInt("PERSON_ID"))
                        .firstName(resultSet.getString("FIRST_NAME"))
                        .lastName(resultSet.getString("LAST_NAME"))
                        .age(resultSet.getInt("AGE"))
                        .cnp(resultSet.getString("CNP"))
                        .addressId(resultSet.getInt("ADDRESS_ID"))
                        .jobId(resultSet.getInt("JOB_ID"))
                        .build();
                persons.add(person);
            }
        } catch (SQLException e) {
            Logger.error("There is no value in table.");
            throw new DataBaseException("No entity found!");
        }
        if (persons.isEmpty()) {
            Logger.error("There is no value in table.");
            throw new DataBaseException("No entity found!");
        }
        return persons;
    }

    @Override
    public void save(PersonBean person) throws DataBaseException {

        String personQuery = "INSERT INTO persons\nVALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = sqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(personQuery)) {

            preparedStatement.setInt(1, person.getPersonId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setInt(4, person.getAge());
            preparedStatement.setString(5, person.getCnp());
            preparedStatement.setInt(6, person.getAddressId());
            preparedStatement.setInt(7, person.getJobId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.error("Couldn't connect to data base!");
            throw new DataBaseException("Data Base failed.");
        }


    }

    public void saveOnePerson(PersonBean personBean, Connection connection) throws DataBaseException {
        String personQuery = "INSERT INTO persons\nVALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(personQuery);

            preparedStatement.setInt(1, personBean.getPersonId());
            preparedStatement.setString(2, personBean.getFirstName());
            preparedStatement.setString(3, personBean.getLastName());
            preparedStatement.setInt(4, personBean.getAge());
            preparedStatement.setString(5, personBean.getCnp());
            preparedStatement.setInt(6, personBean.getAddressId());
            preparedStatement.setInt(7, personBean.getJobId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.error("Couldn't connect to data base!");
            throw new DataBaseException("Data Base failed.");
        }


    }


    public void executeInsertTransaction(PersonBean personBean, List<CarBean> cars) throws DataBaseException {
        CarRepository carRepository = new CarRepository();

        try (Connection connection = sqlConnection.getConnection()) {
            try {
                connection.setAutoCommit(false);

                saveOnePerson(personBean, connection);

                for (CarBean car : cars) {
                    carRepository.saveOneCar(personBean, car, connection);
                }

                connection.commit();
                connection.close();

            } catch (SQLException e) {
                System.err.print("Transaction is being rolled back");
                try {
                    connection.rollback();
                    connection.close();
                    Logger.error("Couldn't connect to data base!");
                    throw new DataBaseException("Data Base failed.");
                } catch (SQLException f) {
                    Logger.error("Couldn't connect to data base!");
                    throw new DataBaseException("Data Base failed.");
                }
            }
        } catch (SQLException e) {
            Logger.error("Couldn't connect to data base!");
            throw new DataBaseException("Data Base failed.");
        }
    }


}


