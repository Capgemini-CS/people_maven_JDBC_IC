package com.person.repository;

import com.person.bean.CarBean;
import com.person.bean.PersonBean;
import com.person.connection.MySqlConnectionManager;
import com.person.exception.DataBaseException;
import lombok.Builder;
import org.tinylog.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements Repository<CarBean> {

    private MySqlConnectionManager connectionManager = new MySqlConnectionManager();

    @Override
    public List<CarBean> getAll() throws SQLException, DataBaseException {

        List<CarBean> carBeanList = new ArrayList<>();
        String query = "SELECT *\nFROM car";


        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            while (resultSet.next()) {
                CarBean carBean = CarBean.builder()
                        .carId(resultSet.getInt("CAR_ID"))
                        .brand(resultSet.getString("BRAND"))
                        .color(resultSet.getString("COLOR"))
                        .personId(resultSet.getInt("PERSON_ID"))
                        .transmission(resultSet.getString("TRANSMISSION"))
                        .motorType(resultSet.getString("MOTOR_TYPE"))
                        .build();
                carBeanList.add(carBean);
            }
        } catch (SQLException e) {
            Logger.error("There is no value in table.");
            throw new DataBaseException("No entity found!");
        }

        if (carBeanList.isEmpty()) {
            Logger.error("There is no value in table.");
            throw new DataBaseException("No entity found!");
        }
        return carBeanList;
    }

    @Override
    public void save(CarBean carBean) throws DataBaseException {

        String query = "INSERT INTO car\nVALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, carBean.getCarId());
            preparedStatement.setString(2, carBean.getBrand());
            preparedStatement.setString(3, carBean.getColor());
            preparedStatement.setInt(4, carBean.getPersonId());
            preparedStatement.setString(5, carBean.getTransmission());
            preparedStatement.setString(6, carBean.getMotorType());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.error("Couldn't connect to data base!");
            throw new DataBaseException("Data Base failed.");
        }


    }


    public void saveOneCar(PersonBean personBean, CarBean carBean, Connection connection) throws DataBaseException {
        String carQuery = "INSERT INTO car\nVALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(carQuery);

            preparedStatement.setInt(1, carBean.getCarId());
            preparedStatement.setString(2, carBean.getBrand());
            preparedStatement.setString(3, carBean.getColor());
            preparedStatement.setInt(4, personBean.getPersonId());
            preparedStatement.setString(5, carBean.getTransmission());
            preparedStatement.setString(6, carBean.getMotorType());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            Logger.error("Couldn't connect to data base!");
            throw new DataBaseException("Data Base failed.");
        }
    }
}

