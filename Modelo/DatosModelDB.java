
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatosModelDB {
    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    DatosSucursalPuestoTrabajo datosDB;
    
    public ArrayList<DatosSucursalPuestoTrabajo> getPuestosTrabajo(int idSucursal){
        ArrayList listaPuestostrabajo = new ArrayList();
        String query = "SELECT `idPuestoTrabajo`, `nombrePuestoTrabajo`, `salario`, `FK_idSucursal` FROM puestotrabajo INNER JOIN sucursal ON (sucursal.idSucursal = puestotrabajo.FK_idSucursal) WHERE sucursal.idSucursal= ?;";
        try{
            connection = conexion.getConnection();
            pst = connection.prepareStatement(query);
            pst.setInt(1, idSucursal);
            rs = pst.executeQuery();
            while(rs.next()){
                datosDB= new DatosSucursalPuestoTrabajo();
                int idpuestoTrabajo= rs.getInt("idPuestoTrabajo");
                String nombrePuestoTrabajo= rs.getString("nombrePuestoTRabajo");
                float salario = rs.getFloat("salario");
                datosDB.setIdPuestoTrabajo(idpuestoTrabajo);
                datosDB.setNombrePuestoTrabajo(nombrePuestoTrabajo);
                datosDB.setSalario(salario);
                listaPuestostrabajo.add(datosDB);
                
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    
           System.out.println("lista de puesto de trabajo"+listaPuestostrabajo);
           return listaPuestostrabajo;
    }
 public ArrayList<DatosSucursalPuestoTrabajo> getSucursales(){
     ArrayList listaSucursales = new ArrayList();
        String query = "SELECT idSucursal, nombreSucursal FROM sucursal ;";
        try{
            connection = conexion.getConnection();
            pst = connection.prepareCall(query);
            rs = pst.executeQuery();
            while(rs.next()){
                datosDB = new DatosSucursalPuestoTrabajo();
                int idSucursal = rs.getInt("idSucursal");
                String nombreSucursal = rs.getString("nombreSucursal");
                datosDB.setIdSucursal(idSucursal);
                datosDB.setNombreSucursal(nombreSucursal);
                listaSucursales.add(datosDB);
            }
        }catch(SQLException e){
            System.out.println(e);
            
        }
        System.out.println("Lista de suscursales"+listaSucursales);
        return listaSucursales;
 }    
}
