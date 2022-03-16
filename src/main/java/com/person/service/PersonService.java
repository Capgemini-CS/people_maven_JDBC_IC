package com.person.service;

import com.person.bean.CarBean;
import com.person.bean.PersonBean;
import com.person.exception.DataBaseException;
import com.person.exception.InvalidActionException;
import com.person.map.CarMapper;
import com.person.map.Mapper;
import com.person.map.PersonMapper;
import com.person.repository.PersonRepository;
import com.person.repository.Repository;
import org.tinylog.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.person.serivce.CarDTO;
import com.person.serivce.PersonDTO;

public class PersonService {

    Repository personRepository = new PersonRepository();
    PersonRepository repository = new PersonRepository();

    public List<com.person.serivce.PersonDTO> getAllPersons() throws DataBaseException, InvalidActionException {

        Mapper<com.person.serivce.PersonDTO, PersonBean> personMapper = new PersonMapper();

        List<com.person.serivce.PersonDTO> personDTOS = new ArrayList<>();
        try {
            List<PersonBean> personBeans = personRepository.getAll();
            for (int i = 0; i < personBeans.size(); i++) {
                personDTOS.add(personMapper.beanToDTO(personBeans.get(i)));
            }
        } catch (SQLException e) {
            Logger.error("No entity found!");
            throw new DataBaseException("Couldn't display from DB.");
        }


        return personDTOS;
    }

    public void savePerson(PersonDTO personDTO) throws DataBaseException, InvalidActionException {
        PersonMapper personMapper = new PersonMapper();
        try {
            personRepository.save(personMapper.DTOToBean(personDTO));
        } catch (SQLException e) {
            Logger.error("Given entity is null!");
            throw new DataBaseException("Couldn't save entity in DB.");
        }

    }

    public void executeInsertTransaction(PersonDTO personDTO, List<CarDTO> carsDTO) throws DataBaseException, InvalidActionException {

        PersonMapper personMapper = new PersonMapper();
        CarMapper carMapper = new CarMapper();

        List<CarBean> carsBean = new ArrayList<>();

        for(int i = 0; i<carsDTO.size();i++){

            carsBean.add(carMapper.DTOToBean(carsDTO.get(i)));
        }
        try {
            repository.executeInsertTransaction(personMapper.DTOToBean(personDTO), carsBean);
        } catch (InvalidActionException | DataBaseException e) {
            Logger.error("Given entity is null!");
            throw new DataBaseException("Couldn't save entity in DB.");
        }
    }
}
