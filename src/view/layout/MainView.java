
package view.layout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import models.Product;
import models.productTable.ProductTableModel;
import util.Icon;
import view.btn.MyButton;

/**
 *
 * @author ADMIN
 */
public class MainView extends JFrame{

    private JTable productTable;
    
    private MyButton addBtn;
    private MyButton editBtn;
    private MyButton deleteBtn;
    private MyButton deleteAllBtn;
    private MyButton printBtn;
    private MyButton logoutBtn;
    private MyButton cancelBtn;
    
    private MyButton searchBtn;
    
    private JTextField idTf;
    private JTextField nameTf;
    private JTextField manufacturerTf;
    private JTextField mfgTf;
    private JTextField expTf;
    private JTextField quantityTf;
    private JTextField priceTf;
    private JTextField totalPriceTf;
    
    private JTextField searchTf;
    
    public MainView() {
        initComponents();
        configuration();
        this.setVisible(true);
    }
    
    private void initComponents() {
        
        productTable = new JTable();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/icons/shops.png")));
        this.add(createHeaderPanel(), BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(productTable), BorderLayout.CENTER);
        this.add(createWrapperPanel(), BorderLayout.SOUTH);
    }
    
    private void configuration() {
        this.setTitle("Store Management");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private JPanel createHeaderPanel() {
        JPanel wrapperHeaderPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        wrapperHeaderPanel.setLayout(flowLayout);
        
        wrapperHeaderPanel.setPreferredSize(new Dimension(1000, 70));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setPreferredSize(new Dimension(1000, 35));
        headerPanel.setBackground(new Color(140, 249, 187));
        JLabel headerLabel = new JLabel("STORE MANAGEMENT");
        headerLabel.setFont(new Font("Calibri", 0, 26));
        
        headerPanel.add(headerLabel);
        
        wrapperHeaderPanel.add(headerPanel);
        wrapperHeaderPanel.add(createSearchPanel());
        return wrapperHeaderPanel;
    }
    
    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
        flowLayout.setHgap(20);
        searchPanel.setPreferredSize(new Dimension(1000, 33));
        searchPanel.setLayout(flowLayout);
        
        searchBtn = new MyButton("Search");
        searchBtn.setPreferredSize(new Dimension(105, 27));
        searchBtn.setIcon(Icon.getIcon(getClass(), "search"));
        
        searchTf = new JTextField();
        searchTf.setPreferredSize(new Dimension(300, 27));
        
        searchPanel.add(searchBtn);
        searchPanel.add(searchTf);
        return searchPanel;
    }
    
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(0, 15, 0));
        
        addBtn = new MyButton("Add");
        addBtn.setIcon(Icon.getIcon(getClass(), "add"));
        editBtn = new MyButton("Edit");
        editBtn.setIcon(Icon.getIcon(getClass(), "edit"));
        deleteBtn = new MyButton("Delete");
        deleteBtn.setIcon(Icon.getIcon(getClass(), "delete"));
        deleteAllBtn = new MyButton("Delete All");
        deleteAllBtn.setIcon(Icon.getIcon(getClass(), "delete"));
        printBtn = new MyButton("Print");
        printBtn.setIcon(Icon.getIcon(getClass(), "print"));
        cancelBtn = new MyButton("Cancel");
        cancelBtn.setIcon(Icon.getIcon(getClass(), "cancel"));
        logoutBtn = new MyButton("Logout");
        logoutBtn.setIcon(Icon.getIcon(getClass(), "logout"));
        
        controlPanel.add(addBtn);
        controlPanel.add(editBtn);
        controlPanel.add(deleteBtn);
        controlPanel.add(printBtn);
        controlPanel.add(cancelBtn);
        controlPanel.add(logoutBtn);
        
        return controlPanel;
    }
    
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel idlabel = new JLabel("ID");
        JLabel namelabel = new JLabel("Name");
        JLabel manufacturinglabel = new JLabel("Manufacturer");
        JLabel mfglabel = new JLabel("Manufacturing Date");
        JLabel explabel = new JLabel("Expiry Date");
        JLabel quantitylabel = new JLabel("Quantity");
        JLabel pricelabel = new JLabel("Price per 1");
        JLabel totallabel = new JLabel("Total Price");
        
        idTf = new JTextField();
        nameTf = new JTextField();
        manufacturerTf = new JTextField();
        mfgTf = new JTextField();
        expTf = new JTextField();
        quantityTf = new JTextField();
        priceTf = new JTextField();
        totalPriceTf = new JTextField();
        
        inputPanel.add(idlabel);
        inputPanel.add(idTf);
        
        inputPanel.add(namelabel);
        inputPanel.add(nameTf);
        
        inputPanel.add(manufacturinglabel);
        inputPanel.add(manufacturerTf);
        
        inputPanel.add(mfglabel);
        inputPanel.add(mfgTf);
        
        inputPanel.add(explabel);
        inputPanel.add(expTf);
        
        inputPanel.add(quantitylabel);
        inputPanel.add(quantityTf);
        
        inputPanel.add(pricelabel);
        inputPanel.add(priceTf);
        
        inputPanel.add(totallabel);
        inputPanel.add(totalPriceTf);
        totalPriceTf.setEditable(false);
        
        return inputPanel;
    }
    
    private JPanel createWrapperPanel() {
        JPanel wrapPanel = new JPanel();
        wrapPanel.add(createControlPanel(), BorderLayout.NORTH);
        wrapPanel.add(createInputPanel(), BorderLayout.CENTER);
        wrapPanel.setPreferredSize(new Dimension(1000, 110));
        return wrapPanel;
    }
    
    private void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            JOptionPane.showConfirmDialog(this, e);
        }
    }
    
    public void showListProduct(ProductTableModel productTableModel) {
        productTable.setModel(productTableModel);
    }
    
    public Product getProductData() {
        try {
            int id = Integer.parseInt(idTf.getText());
            String name = nameTf.getText();
            String mf = manufacturerTf.getText();
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate mfg = LocalDate.parse(mfgTf.getText(), df);
            LocalDate exp = LocalDate.parse(expTf.getText(), df);
            if(mfg.isAfter(exp)) {
                JOptionPane.showMessageDialog(null,  "The Manufacturing Date must be before the Expiry Date!");
                return null;
            }
            int quantity = Integer.parseInt(quantityTf.getText());
            double price = Double.parseDouble(priceTf.getText());
            if(!name.isEmpty() && !mf.isEmpty() && quantity > 0 && price > 0) {
                return new Product(id, name, mf, mfg, exp, quantity, price);
            }else {
                JOptionPane.showConfirmDialog(null, "Information can not be left blank");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The information is invalid");
        }catch(DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Date format must be 'yyyy-MM-dd(Ex. 2003-01-01)'");
        }
        return null;
    }
    
    public String getSearchFieldInfo() {
        String searchInfo = searchTf.getText().trim();
        searchInfo = searchInfo.replaceAll("\\s+", " ");
        return searchInfo;
    }
    
    public void addAddBtnListener(ActionListener listener) {
        addBtn.addActionListener(listener);
    }
    
    public void addEditBtnListener(ActionListener listener) {
        editBtn.addActionListener(listener);
    }
    
    public void addDeleteAddBtnListener(ActionListener listener) {
        deleteBtn.addActionListener(listener);
    }
    
    public void addDeleteAllBtnListener(ActionListener listener) {
        deleteAllBtn.addActionListener(listener);
    }
    
    public void addPrintBtnListener(ActionListener listener) {
        printBtn.addActionListener(listener);
    }
    
    public void addLogoutBtnListener(ActionListener listener) {
        logoutBtn.addActionListener(listener);
    }
    
    public void addCancelBtnListener(ActionListener listener) {
        cancelBtn.addActionListener(listener);
    }
    
    public void addTableMouseListener(ListSelectionListener listener) {
        productTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addSearchActionListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }
    
    public void fillInputForm() {
        int selectedRow = productTable.getSelectedRow();
        if(selectedRow >= 0) {
            idTf.setText(productTable.getValueAt(selectedRow, 0).toString());
            nameTf.setText(productTable.getValueAt(selectedRow, 1).toString());
            manufacturerTf.setText(productTable.getValueAt(selectedRow, 2).toString());
            mfgTf.setText(productTable.getValueAt(selectedRow, 3).toString());
            expTf.setText(productTable.getValueAt(selectedRow, 4).toString());
            quantityTf.setText(productTable.getValueAt(selectedRow, 5).toString());
            priceTf.setText(productTable.getValueAt(selectedRow, 6).toString());
            totalPriceTf.setText(productTable.getValueAt(selectedRow, 7).toString());
        }
    }
    
    public void unfillInputForm() {
        idTf.setText("");
        nameTf.setText("");
        manufacturerTf.setText("");
        mfgTf.setText("");
        expTf.setText("");
        quantityTf.setText("");
        priceTf.setText("");
        totalPriceTf.setText("");
    }
    
    public JTable getTable() {
        return productTable;
    }
    
    public MyButton getAddBtn() {
        return addBtn;
    }
}
