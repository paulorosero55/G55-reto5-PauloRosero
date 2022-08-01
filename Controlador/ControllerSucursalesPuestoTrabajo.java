
package Controlador;

import Modelo.Conexion;
import Modelo.DatosModelDB;
import Modelo.DatosSucursalPuestoTrabajo;
import Modelo.Sucursal;
import Vistas.AddUserForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;


public class ControllerSucursalesPuestoTrabajo implements ActionListener{
    private final AddUserForm  view;
Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    DatosModelDB model= new DatosModelDB();
    ArrayList <DatosSucursalPuestoTrabajo> list;
  
    
    public ControllerSucursalesPuestoTrabajo(AddUserForm view) {
        this.view = view;
        this.getListaSucursales();
        Sucursal sucursal= (Sucursal)view.cbSucursal.getSelectedItem();
        getListaPuestosTrabajo(sucursal.getIdSucursal());
        events();
    }
    
    
    public final void getListaSucursales(){
        list = model.getSucursales();
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                int idSucursal=list.get(i).getIdSucursal();
                String nombreSucursal= list.get(i).getNombreSucursal();
                view.cbSucursal.addItem(new Sucursal(idSucursal, nombreSucursal));
            }
            
            
        }else{
            JOptionPane.showMessageDialog(null,"No se encontraron sucursales ","sucussales", JOptionPane.WARNING_MESSAGE);
            
                    
            
        }
        
    }
    
    public final void getListaPuestosTrabajo(int idSucursal ){
        list = model.getPuestosTrabajo(idSucursal);
         if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                DefaultComboBoxModel model= (DefaultComboBoxModel)view.cbPuestoTrabajo.getModel();
                Object [] puestoTrabajo = new Object[2];
                puestoTrabajo[0]=list.get(i).getIdPuestoTrabajo();
                puestoTrabajo[1]=list.get(i).getNombrePuestoTrabajo();
                model.addElement(puestoTrabajo[1]);
                view.cbPuestoTrabajo.setModel(model);
                System.out.println(model);
                
                
            }
            }else{
            JOptionPane.showMessageDialog(null,"No se encontraron puestos de trabajo asociados a la sucursal ","sucussales", JOptionPane.WARNING_MESSAGE);
            
                    
            
        }
        }
    public final void events(){
        view.cbSucursal.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
       Object eventos = ae.getSource();
       if(eventos.equals(view.cbSucursal)){
           view.cbPuestoTrabajo.removeAllItems();
           Sucursal sucursal= (Sucursal)view.cbSucursal.getSelectedItem();
           getListaPuestosTrabajo(sucursal.getIdSucursal());
           
       }
       
        
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
