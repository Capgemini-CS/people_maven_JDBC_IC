package com.person.map;

import com.person.bean.CarBean;
import com.person.exception.InvalidActionException;
import com.person.serivce.CarDTO;
import org.tinylog.Logger;

public class CarMapper implements Mapper<CarDTO, CarBean> {

    @Override
    public CarDTO beanToDTO(CarBean carBean) throws InvalidActionException {

        if (carBean == null) {
            Logger.error("Given Entity is null.");
            throw new InvalidActionException("Can't map to DTO.");
        }
        return CarDTO.builder()
                .carId(carBean.getCarId())
                .brand(carBean.getBrand())
                .color(carBean.getColor())
                .personId(carBean.getPersonId())
                .transmission(carBean.getTransmission())
                .motorType(carBean.getMotorType())
                .build();
    }

    @Override
    public CarBean DTOToBean(CarDTO carDTO) throws InvalidActionException {

        if (carDTO == null) {
            Logger.error("Given DTO is null.");
            throw new InvalidActionException("Can't map to Entity.");
        }
        return CarBean.builder()
                .carId(carDTO.getCarId())
                .brand(carDTO.getBrand())
                .color(carDTO.getColor())
                .personId(carDTO.getPersonId())
                .transmission(carDTO.getTransmission())
                .motorType(carDTO.getMotorType())
                .build();
    }
}
