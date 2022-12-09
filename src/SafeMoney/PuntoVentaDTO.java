package SafeMoney;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import SafeMoney.Vistas.PuntoVenta;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 *
 * @author SafeMoney
 */

public class PuntoVentaDTO implements ActionListener{
    
    PuntoVenta vistPV;

    public PuntoVentaDTO(PuntoVenta vistPV) {
        this.vistPV = vistPV;
        this.vistPV.getjBtnDNI_C().addActionListener((ActionListener)this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vistPV.getjBtnDNI_C()){
            String dni = vistPV.getjFDNI_C().getText();
            DniConsul(dni);
            limpiar();
        }
    
    }
    
    public void limpiar(){
        vistPV.getjFDNI_C().setText("");
    }
    public void DniConsul(String dni){
        String enlace = "https://api.apis.net.pe/v1/dni?numero="+dni;
            try {
                URL url = new URL(enlace);
                URLConnection request = url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
                if(root.isJsonObject()){
                    JsonObject rootobj = root.getAsJsonObject();
                    String apell_Paterno = rootobj.get("apellidoPaterno").getAsString();
                    String apell_Materno = rootobj.get("apellidoMaterno").getAsString();
                    String nombres = rootobj.get("nombres").getAsString();
                    vistPV.getjTAdatos().setText("");
                    vistPV.getjTAdatos().append(apell_Paterno+"\n"+apell_Materno+"\n"+nombres);
                }
                
            } catch (JsonIOException | JsonSyntaxException | IOException e) {
                System.out.println(e);
            }
    }
}
