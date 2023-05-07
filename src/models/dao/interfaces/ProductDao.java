
package models.dao.interfaces;

import java.sql.SQLException;
import java.util.List;
import models.Product;

/**
 *
 * @author ADMIN
 */
public interface ProductDao {
    
    public List<Product> getListProduct() throws SQLException;
    public boolean add(Product stuff) throws SQLException;
    public boolean edit(Product stuff) throws SQLException;
    public boolean delete(Product stuff) throws SQLException;
    public int deleteAll() throws SQLException;
    public Product findByID(int id) throws SQLException;
    public List<Product> findByName(String name);
    
}
