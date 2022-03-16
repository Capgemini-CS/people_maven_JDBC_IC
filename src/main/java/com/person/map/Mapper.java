package com.person.map;

import com.person.exception.InvalidActionException;

public interface Mapper<T, N> {

    T beanToDTO(N t) throws InvalidActionException;

    N DTOToBean(T t) throws InvalidActionException;

}
