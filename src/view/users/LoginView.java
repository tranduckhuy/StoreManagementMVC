
package view.users;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import models.User;
import util.Icon;
import view.btn.MyButton;

/**
 *
 * @author ADMIN
 */
public class LoginView extends JFrame{

    private JTextField userTf;
    private JPasswordField pwdTf;
    
    private MyButton cancelBtn;
    private MyButton loginBtn;
    
    public LoginView() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/icons/user.png")));
        initComponents();
        configuration();
    }
    
    private void initComponents() {
       this.add( createHeaderPanel(), BorderLayout.NORTH);
       this.add(createFormPanel(), BorderLayout.CENTER);
       this.add(createOptionPanel(), BorderLayout.SOUTH);
    }
    
    private void configuration() {
        this.setTitle("Login Window");
        this.setSize(330, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(123, 229, 221));
        headerPanel.setPreferredSize(new Dimension(300, 33));
        
        JLabel loginLabel = new JLabel("LOGIN FORM");
        
        loginLabel.setFont(new Font("Calibri", 0, 20));
        
        headerPanel.add(loginLabel);
        
        return headerPanel;
    }
    
    private JPanel createFormPanel() {
        
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel userLabel = new JLabel("Username");
        userTf = new JTextField();
        userTf.setPreferredSize(new Dimension(200, 27));
        
        JLabel pwdLabel = new JLabel("Password");
        pwdTf = new JPasswordField();
        pwdTf.setPreferredSize(new Dimension(200, 27));
        
        formPanel.add(userLabel);
        formPanel.add(userTf);
        formPanel.add(pwdLabel);
        formPanel.add(pwdTf);
        
        return formPanel;
    }
    
    private JPanel createOptionPanel() {
        JPanel optionPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        optionPanel.setBorder(BorderFactory.createEmptyBorder(5, 50, 10, 50));
        
        cancelBtn = new MyButton("Canel");
        cancelBtn.setPreferredSize(new Dimension(100, 27));
        cancelBtn.setIcon(Icon.getIcon(getClass(), "cancel"));
        
        loginBtn = new MyButton("Login");
        loginBtn.setPreferredSize(new Dimension(100, 27));
        loginBtn.setIcon(Icon.getIcon(getClass(), "login"));
        
        optionPanel.add(cancelBtn);
        optionPanel.add(loginBtn);
        
        return optionPanel;
    }
    
    public void addCancelBtnListener(ActionListener listener) {
        cancelBtn.addActionListener(listener);
    }
    
    public void addLoginBtnListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }
    
    public User getUserData() {
        String user = userTf.getText();
        String pass = String.valueOf(pwdTf.getPassword());
        if(!user.isEmpty() && !pass.isEmpty()) {
            return new User(user, pass);
        }
        return null;
    }
    
    public void clearAccountInfo() {
        userTf.setText("");
        pwdTf.setText("");
    }
}
