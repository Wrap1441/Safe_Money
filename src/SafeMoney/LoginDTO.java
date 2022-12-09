package SafeMoney;

import SafeMoney.DAO.LoginDAO;
import SafeMoney.Vistas.Login;
import SafeMoney.Modelos.login;
import SafeMoney.Vistas.PuntoVenta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

/**
 *
 * @author SAFEMONEY
 */

public class LoginDTO implements ActionListener{
    Login vistaL;
    LoginDAO login = new LoginDAO();
    login lg;

    public LoginDTO(Login vista) {
        this.vistaL = vista;
        this.vistaL.getBtnIniciar().addActionListener((ActionListener) this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistaL.getBtnIniciar()){
            String correo = vistaL.getTxtCorreo().getText();
            String pass = String.valueOf(vistaL.getTxtPass().getPassword());
            if (!"".equals(correo) || !"".equals(pass)) {
                lg = login.log(correo, pass);
                if (lg.getCorreo() != null && lg.getPass() != null) {
                    PuntoVenta sis = new PuntoVenta();
                    sis.setLocationRelativeTo(null);
                    sis.setVisible(true);
                    PuntoVentaDTO sisdto = new PuntoVentaDTO(sis);
                    vistaL.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "EMAIL O CONTRASEÑA ES INCORRECTO :C","¡INICIO DE SESION ERROR!",JOptionPane.ERROR_MESSAGE);
                    vistaL.getTxtCorreo().setBorder(new LineBorder(Color.RED));
                    vistaL.getTxtPass().setBorder(new LineBorder(Color.RED));
                }
            }else{}
        }
    }
}
