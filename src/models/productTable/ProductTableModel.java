
package models.productTable;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import models.Product;

/**
 *
 * @author ADMIN
 */
public class ProductTableModel extends AbstractTableModel{

    private String[] columnsName = {"ID", "Name", "Manufacturer", "MFG", "EXP", "Quantity", "Price/1", "Total($)"};
    private List<Product> productList;

    public ProductTableModel() {
        productList = new ArrayList<>();
    }
    
    public void setData(List<Product> productList) {
        this.productList = productList;
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        return columnsName[column];
    }
    
    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnsName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        switch(columnIndex) {
            case 0 -> {
                return product.getProductID();
            }
            case 1 -> {
                return product.getProductName();
            }
            case 2 -> {
                return product.getProductManufacturer();
            } 
            case 3 -> {
                return product.getManufacturingDate().toString();
            } 
            case 4 -> {
                return product.getExpiryDate().toString();
            } 
            case 5 -> {
                return product.getQuantity();
            } 
            case 6 -> {
                return product.getPrice();
            }
            case 7 -> {
                return product.getTotalPrice();
            }
        }
        return null;
    }
    
}
