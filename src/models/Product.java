
package models;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class Product {
    
    private int productID;
    private String productName;
    private String productManufacturer;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private int quantity;
    private double price;

    public Product() {
    }

    public Product(int productID, String productName, String productManufacturer, LocalDate manufacturingDate, LocalDate expiryDate, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.productManufacturer = productManufacturer;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(LocalDate manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return quantity*price;
    }

}
