
package controller.users;

import controller.store.StoreController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.User;
import models.dao.concrete.MySQLUSerDao;
import view.layout.MainView;
import view.users.LoginView;

/**
 *
 * @author ADMIN
 */
public class UserController {
    
    private MySQLUSerDao userDao;
    private LoginView loginView;
    private MainView mainView;

    private static UserController instance = new UserController();
    
    public static UserController getInstance() {
        return instance;
    }
    
    public UserController() {
        this.loginView = new LoginView();
        userDao = new MySQLUSerDao();
        loginView.addCancelBtnListener(new CancelActionListener());
        loginView.addLoginBtnListener(new LoginActionListener());
        loginView.setVisible(true);
    }
    
    public LoginView getLoginView() {
        return loginView;
    }
    
    private class CancelActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.dispose();
        }
    }
    
    private class LoginActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUserData();
            if(user != null) {
                try {
                    boolean success = userDao.checkUser(user);
                    if(success) {
                        mainView = new MainView();
                        loginView.dispose();
                        StoreController.getInstance().setMainView(mainView);
                        StoreController.getInstance().showListProduct();
                    }else {
                        JOptionPane.showMessageDialog(loginView, "Login failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }
    }
}
