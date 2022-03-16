package com.person.service;

import com.person.bean.CarBean;
import com.person.exception.DataBaseException;
import com.person.exception.InvalidActionException;
import com.person.map.CarMapper;
import com.person.map.Mapper;
import com.person.repository.CarRepository;
import com.person.repository.Repository;
import org.tinylog.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarService {

    Repository repository = new CarRepository();


    public List<com.person.serivce.CarDTO> getAllCars() throws DataBaseException, InvalidActionException {

        Mapper<com.person.serivce.CarDTO, CarBean> carMapper = new CarMapper();
        List<com.person.serivce.CarDTO> carDTOList = new ArrayList<>();

        try {
            List<CarBean> carBeanList = repository.getAll();
            for (CarBean carBean : carBeanList) {
                carDTOList.add(carMapper.beanToDTO(carBean));
            }
        } catch (SQLException e) {
            Logger.error("No entity found!");
            throw new DataBaseException("Couldn't display from DB.");
        }
        return carDTOList;
    }

    public void saveCar(com.person.serivce.CarDTO carDTO) throws DataBaseException, InvalidActionException {

        Mapper<com.person.serivce.CarDTO, CarBean> carMapper = new CarMapper();

        try {
            repository.save(carMapper.DTOToBean(carDTO));
        } catch (SQLException e) {
            Logger.error("Given entity is null!");
            throw new DataBaseException("Couldn't save entity in DB.");
        }
    }
}
