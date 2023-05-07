package models.dao.interfaces;

import java.sql.SQLException;
import models.User;

/**
 *
 * @author ADMIN
 */
public interface UserDao {
    
    public boolean add(User user) throws SQLException;
    public boolean edit(User user) throws SQLException;
//    public List<User> getListUser() throws SQLException;
    public boolean delete(User user) throws SQLException;
    public boolean checkUser(User user) throws SQLException;
}
