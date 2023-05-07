
package controller.store;

import controller.users.UserController;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.Product;
import models.dao.concrete.MySQLProductDao;
import models.productTable.ProductTableModel;
import view.layout.MainView;

/**
 *
 * @author ADMIN
 */
public class StoreController {
    
    private MainView mainView;
    private MySQLProductDao productDao;
    private ProductTableModel productTableModel;

    private static StoreController instance = new StoreController();
    
    public static StoreController getInstance() {
        return instance;
    }
    
    public void setMainView(MainView mainView) {
        this.mainView = mainView;
        addEventListener();
    }
    
    private void addEventListener() {
        mainView.addAddBtnListener(new AddActionListener());
        mainView.addEditBtnListener(new EditActionListener());
        mainView.addDeleteAddBtnListener(new DeleteActionListener());
        mainView.addTableMouseListener(new AddTableSelectionListener());
        mainView.addPrintBtnListener(new PrintActionListener());
        mainView.addCancelBtnListener(new CancelActionListener());
        mainView.addLogoutBtnListener(new LogoutActionListener());
        mainView.addSearchActionListener(new SearchActionListener());
    }
    
    public StoreController() {
        this.productDao = new MySQLProductDao();
        this.productTableModel = new ProductTableModel();
    }
    
    public void showListProduct() {
        List<Product> products;
        try {
            productTableModel.setData(productDao.getListProduct());
            mainView.showListProduct(productTableModel);
            mainView.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(mainView, ex);
        }
    }
    
    private class AddActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = mainView.getProductData();
            if(product != null) {
                try {
                    boolean success = productDao.add(product);
                    if(success) {
                        productTableModel.setData(productDao.getListProduct());
                        mainView.unfillInputForm();
                        JOptionPane.showMessageDialog(mainView, "Added successfully!");
                    }else {
                        JOptionPane.showMessageDialog(mainView, "Added failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog(mainView, ex);
                }
            }
        }
        
    }
    
    private class EditActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = mainView.getProductData();
            if(product != null) {
                try {
                    boolean success = productDao.edit(product);
                    if(success) {
                        productTableModel.setData(productDao.getListProduct());
                        mainView.getAddBtn().setEnabled(true);
                        mainView.unfillInputForm();
                        JOptionPane.showMessageDialog(mainView, "Edited successfully!");
                    }else {
                        JOptionPane.showMessageDialog(mainView, "Edited failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog(mainView, ex);
                }
            }
        }
    }
    
    private class DeleteActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = mainView.getProductData();
            if(product != null) {
                try {
                    boolean success = productDao.delete(product);
                    if(success) {
                        productTableModel.setData(productDao.getListProduct());
                        JOptionPane.showMessageDialog(mainView, "Deleted successfully!");
                    }else {
                        JOptionPane.showMessageDialog(mainView, "Deleted failed!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showConfirmDialog(mainView, ex);
                }
            }
        }
    }
    
    private class AddTableSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            mainView.getAddBtn().setEnabled(false);
            mainView.fillInputForm();
        }
    }
    
    private class PrintActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MessageFormat header = new MessageFormat("Store Management");
            MessageFormat footer = new MessageFormat("Page{0, number, integer}");
            
            try {
                boolean succes = mainView.getTable().print(JTable.PrintMode.FIT_WIDTH, header, footer);
                if(succes) {
                    JOptionPane.showMessageDialog(mainView, "    Printed successfully!");
                }
            } catch (HeadlessException | PrinterException ex) {
                JOptionPane.showConfirmDialog(mainView, ex);
            }
        }
        
    }
    
    private class CancelActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainView.unfillInputForm();
            mainView.getTable().clearSelection();
            mainView.getAddBtn().setEnabled(true);
        }
    }
    
    private class LogoutActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(mainView, "Do you want to logout?", "Confirm", JOptionPane.YES_NO_OPTION, 1);
            if(choice == 0) {
                mainView.dispose();
                UserController.getInstance().getLoginView().clearAccountInfo();
                UserController.getInstance().getLoginView().setVisible(true);
            }
        }
        
    }
    
    private class SearchActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchInfo = mainView.getSearchFieldInfo();
            try {
                int id = Integer.parseInt(searchInfo);
                List<Product> searchProduct = new ArrayList<>();
                Product product = productDao.findByID(id);
                if(product != null) {
                    searchProduct.add(product);
                }
                productTableModel.setData(searchProduct);
            } catch (NumberFormatException ex) {
                List<Product> searchProducts = productDao.findByName(searchInfo);
                if(searchProducts != null) {
                    productTableModel.setData(searchProducts);
                }
            } catch(SQLException ex) {
                JOptionPane.showConfirmDialog(mainView, ex);
            }
        }
    }
}
