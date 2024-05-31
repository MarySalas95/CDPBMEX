package com.comp.complementos.DB;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author Mary Villanueva
 */
public class ConnectionPool {

    /* AMBIENTE DEMO */
    /*private final String URL = "jdbc:as400://des.vnpidns.com";
    private final String USER = "COSIS001";
    private final String PASSWORD = "zxc14asd";*/
    
    /* AMBIENTE REAL */
    private final String URL = "jdbc:as400://ame.vnpidns.com";
    private final String USER = "bmx_bi";
    private final String PASSWORD = "BMX1BI";

    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;

    private ConnectionPool() {

        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.ibm.as400.access.AS400JDBCDriver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setUrl(URL);

        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxWait(-1);
        
        out.println("Pool de conexiones creado!!");
    }

    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

}
