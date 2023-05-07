
import controller.users.UserController;
import java.awt.EventQueue;

/**
 *
 * @author ADMIN
 */
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                UserController.getInstance();
            }
        });
    }
}
