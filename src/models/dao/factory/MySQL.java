
package models.dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.dao.concrete.MySQLProductDao;
import models.dao.concrete.MySQLUSerDao;
import models.dao.interfaces.ProductDao;
import models.dao.interfaces.UserDao;

/**
 *
 * @author ADMIN
 */
public class MySQL extends DaoFactory{
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "duchuy2003";

    @Override
    public Connection getConnection(String database) {
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(URL + database, user, password);
            if(conn != null) {
                return conn;
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    @Override
    public UserDao getUserDao() {
        return new MySQLUSerDao();
    }

    @Override
    public ProductDao getProductDao() {
        return new MySQLProductDao();
    }
    
}
