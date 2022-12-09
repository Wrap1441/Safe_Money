package SafeMoney;

import SafeMoney.Vistas.Login;

/**
 *
 * @author SafeMoney
 */
public class SafeMoney {
    public static Login lg;
    public static LoginDTO controlador;
    
    public static void main(String[] args) {
        lg = new Login();
        lg.setLocationRelativeTo(null);
        lg.setVisible(true);
        controlador = new LoginDTO(lg);
    }
    
}
