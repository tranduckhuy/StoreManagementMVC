package models.dao.concrete;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import models.User;
import models.dao.factory.DaoFactory;
import models.dao.interfaces.UserDao;

/**
 *
 * @author ADMIN
 */
public class MySQLUSerDao implements UserDao {
    
    private final String database = "connectdb";
    
    private final String CHECK_VALID_USER = "SELECT * FROM users WHERE username = ? and password = ?";
    private final String ADD_USER = "INSERT INTO users (username, password) VALUES (?, ?)";
    private final String EDIT_USER = "UPDATE users SET password = ? WHERE username = ?";    
    private final String DELETE_USER = "DELETE FROM users WHERE username = ?";    
    
    @Override
    public boolean checkUser(User user) throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(CHECK_VALID_USER)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    @Override
    public boolean add(User user) throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(ADD_USER)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    @Override
    public boolean edit(User user) throws SQLException {
        try (Connection conn = DaoFactory.getDatabase().getConnection(database);
                PreparedStatement ps = conn.prepareStatement(EDIT_USER)){
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getUsername());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    @Override
    public boolean delete(User user) throws SQLException {
        try (Connection conn = DaoFactory.getDatabase().getConnection(database);
                PreparedStatement ps = conn.prepareStatement(DELETE_USER)){
            ps.setString(1, user.getUsername());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
}
