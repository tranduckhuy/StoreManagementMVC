
package models.dao.factory;

import java.sql.Connection;
import models.dao.interfaces.ProductDao;
import models.dao.interfaces.UserDao;

/**
 *
 * @author ADMIN
 */
public abstract class DaoFactory {
    
    public abstract Connection getConnection(String database);
    public abstract UserDao getUserDao();
    public abstract ProductDao getProductDao();
    
    public static DaoFactory getDatabase(){
        return new MySQL();
    }
}
