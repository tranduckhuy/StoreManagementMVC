
package util;

import javax.swing.ImageIcon;

/**
 *
 * @author ADMIN
 */
public class Icon {
    private static final String ICON_PATH = "/view/icons/";
    
    public static ImageIcon getIcon(Class<?> kClass, String icon) {
        return new ImageIcon(kClass.getResource(ICON_PATH + icon + ".png"));
    }
}
