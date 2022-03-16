package com.person.map;

import com.person.bean.PersonBean;
import com.person.exception.InvalidActionException;
import com.person.serivce.PersonDTO;
import lombok.Builder;
import org.tinylog.Logger;

public class PersonMapper implements Mapper<PersonDTO, PersonBean> {

    @Override
    public PersonDTO beanToDTO(PersonBean personBean) throws InvalidActionException {

        if (personBean == null) {
            Logger.error("Given Entity is null.");
            throw new InvalidActionException("Can't map to DTO.");
        }
        return PersonDTO.builder()
                .personId(personBean.getPersonId())
                .firstName(personBean.getFirstName())
                .lastName(personBean.getLastName())
                .age(personBean.getAge())
                .cnp(personBean.getCnp())
                .addressId(personBean.getAddressId())
                .jobId(personBean.getJobId())
                .build();
    }

    @Override
    public PersonBean DTOToBean(PersonDTO personDTO) throws InvalidActionException {

        if (personDTO == null) {
            Logger.error("Given DTO is null.");
            throw new InvalidActionException("Can't map to Entity.");
        }
        return PersonBean.builder()
                .personId(personDTO.getPersonId())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .age(personDTO.getAge())
                .cnp(personDTO.getCnp())
                .addressId(personDTO.getAddressId())
                .jobId(personDTO.getJobId())
                .build();
    }
}
