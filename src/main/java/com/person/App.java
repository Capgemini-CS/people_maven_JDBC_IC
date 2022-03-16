package com.person;

import com.person.exception.DataBaseException;
import com.person.exception.InvalidActionException;
import com.person.serivce.PersonDTO;
import com.person.service.CarService;
import com.person.service.PersonService;
import com.person.serivce.CarDTO;

import java.util.ArrayList;
import java.util.List;

public class App {


    public static void main(String[] args) throws DataBaseException, InvalidActionException {

        PersonService personService = new PersonService();
        CarService carService = new CarService();
        PersonDTO personDTO = new PersonDTO();
//        personService.savePerson(new com.person.serivce.PersonDTO(4, "Sun", "Roberto", 18, "1990133004477", 2, 2));
//        carService.saveCar(new com.person.serivce.CarDTO(3,"Mercedes","White", 3, "Auto", "Diesel"));






        List<CarDTO> carsDTO = new ArrayList<>();
        carsDTO.add(new CarDTO(5, "Soda", "White", 7, "Auto", "Diesel"));
        carsDTO.add(new CarDTO(6, "Ferrari", "Black", 7, "Auto", "Diesel"));
        carsDTO.add(new CarDTO(7, "Bugatti", "Gray", 7, "Auto", "Diesel"));

        personService.executeInsertTransaction(
                new PersonDTO(7, "Radu", "Radu", 21, "4444", 2, 2),
                carsDTO
                );


//        System.out.println(personService.getAllPersons());
//
//        System.out.println("/////////////////////////////////////////////////");
//
//        System.out.println(carService.getAllCars());
    }
}
