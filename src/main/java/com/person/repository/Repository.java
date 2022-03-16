package com.person.repository;

import com.person.exception.DataBaseException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T>{

    List<T> getAll() throws SQLException, DataBaseException;

    void save(T data) throws SQLException, DataBaseException;


}
