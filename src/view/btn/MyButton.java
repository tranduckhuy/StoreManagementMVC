
package view.btn;

import java.awt.Dimension;
import javax.swing.JButton;

/**
 *
 * @author ADMIN
 */
public class MyButton extends JButton{

    private String btnName;
    
    public MyButton(String btnName) {
        this.btnName = btnName;
        init();
    }
    
    private void init() {
        this.setText(btnName);
        this.setPreferredSize(new Dimension(95, 27));
        this.setFocusable(false);
    }
}
