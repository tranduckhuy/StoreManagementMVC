package models.dao.concrete;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Product;
import models.dao.factory.DaoFactory;
import models.dao.interfaces.ProductDao;

/**
 *
 * @author ADMIN
 */
public class MySQLProductDao implements ProductDao {

    private final String database = "things";

    private final String GET_LIST_PRODUCT = "SELECT * FROM product";
    private final String ADD_STUFF = "INSERT INTO product (name, manufacturer, mfg, exp, quantity, price) VALUES (?, ?, ?, ?, ?, ?)";
    private final String EDIT_STUFF = "UPDATE product SET name = ?, manufacturer = ?, mfg = ?, exp = ?, quantity = ?, price = ? WHERE id = ?";
    private final String DELETE_STUFF = "DELETE FROM product WHERE id = ?";
    private final String DELETE_ALL_STUFF = "DELETE FROM product";
    private final String FIND_BY_ID = "SELECT * FROM product WHERE id = ?";
    private final String FIND_BY_NAME = "SELECT * FROM product WHERE name LIKE ?";
    private final String CHECK_UNIQUE_IDPRODUCT = "SELECT * FROM product WHERE id = ?";
    private final String CHECK_EXIST_PRODUCT = "SELECT * FROM product WHERE name = ? and manufacturer = ? and mfg = ? and exp = ?";

    @Override
    public List<Product> getListProduct() throws SQLException {
        List<Product> products = new ArrayList<>();
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                Statement st = conn.createStatement();  
                ResultSet rs = st.executeQuery(GET_LIST_PRODUCT)) {
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate mfg = LocalDate.parse(rs.getString("mfg"), df);
                LocalDate exp = LocalDate.parse(rs.getString("exp"), df);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                products.add(new Product(idProduct, name, manufacturer, 
                        mfg, exp, quantity, price));
            }
            return products;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    private boolean checkExistedProductId(Product product) throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(CHECK_UNIQUE_IDPRODUCT)) {
            ps.setString(1, product.getProductID() + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    private boolean checkExistedProduct(Product product) {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(CHECK_EXIST_PRODUCT)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductManufacturer());
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            ps.setString(3, product.getManufacturingDate().format(df));
            ps.setString(4, product.getExpiryDate().format(df));
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
    public boolean add(Product product) throws SQLException {
        if (checkExistedProductId(product)) {
            if(checkExistedProduct(product)) {
                edit(product);
                return true;
            }else {
                JOptionPane.showMessageDialog(null, "The ID already existed");
                return false;
            }
        }else {
            try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                    PreparedStatement ps = conn.prepareStatement(ADD_STUFF)) {
                ps.setString(1, product.getProductName());
                ps.setString(2, product.getProductManufacturer());

                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                ps.setString(3, product.getManufacturingDate().format(df));
                ps.setString(4, product.getExpiryDate().format(df));
                ps.setString(5, product.getQuantity() + "");
                ps.setString(6, product.getPrice() + "");
                ps.executeUpdate();
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return false;
    }

    @Override
    public boolean edit(Product product) throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(EDIT_STUFF)) {
            ps.setString(1, product.getProductName());
            ps.setString(2, product.getProductManufacturer());
            ps.setString(3, product.getManufacturingDate().toString());
            ps.setString(4, product.getExpiryDate().toString());
            ps.setString(5, product.getQuantity() + "");
            ps.setString(6, product.getPrice() + "");
            ps.setString(7, product.getProductID() + "");
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    @Override
    public boolean delete(Product product) throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(DELETE_STUFF)) {
            ps.setString(1, product.getProductID() + "");
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    @Override
    public int deleteAll() throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(DELETE_ALL_STUFF)) {
            int rowsEffect = ps.executeUpdate();
            return rowsEffect;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }

    @Override
    public Product findByID(int id) throws SQLException {
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(FIND_BY_ID)) {
            ps.setString(1, id + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idProduct = rs.getInt("id");
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate mfg = LocalDate.parse(rs.getString("mfg"), df);
                LocalDate exp = LocalDate.parse(rs.getString("exp"), df);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                return new Product(idProduct, name, 
                        manufacturer, mfg, exp, quantity, price);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String fname){
        List<Product> products = new ArrayList<>();
        try ( Connection conn = DaoFactory.getDatabase().getConnection(database);  
                PreparedStatement ps = conn.prepareStatement(FIND_BY_NAME)) {
            ps.setString(1, "%" + fname + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String name = rs.getString("name");
                String manufacturer = rs.getString("manufacturer");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate mfg = LocalDate.parse(rs.getString("mfg"), df);
                LocalDate exp = LocalDate.parse(rs.getString("exp"), df);
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                products.add(new Product(idProduct, name, 
                        manufacturer, mfg, exp, quantity, price));
            }
            return products;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

}
