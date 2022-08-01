
package Principal;
import Vistas.*;
import Modelo.*;


public class Main {

    public static void main(String[] args) {
        //1. crear instancia de la clse conexion
         Conexion conexion= new Conexion();
         conexion.getConnection();
         DatosModelDB datos= new DatosModelDB();
         datos.getPuestosTrabajo(1);
         datos.getSucursales();
         
        // 2. crear instancia del Jframe Login
        Login login = new Login();
        login.setVisible(true);
    
    }  
    
    
}
