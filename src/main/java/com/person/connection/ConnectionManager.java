package com.person.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {

    Connection getConnection() throws SQLException;
}
