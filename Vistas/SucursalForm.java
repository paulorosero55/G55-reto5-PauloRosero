package Vistas;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SucursalForm extends javax.swing.JDialog {

    Connection connection;
    Conexion conexion = new Conexion();
    Statement st;
    ResultSet rs;

    public SucursalForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);

    }

    public void recibirDatosDireccion(String departamento, String zona, String tipoCalle, String numero1, String numero2, String numero3) {
        String sucursal = txtSucursal.getText();
        if (!sucursal.isEmpty()) {
            String query = "INSERT INTO `direccion`(`zona`, `tipoCalle`, `numero1`, `numero2`,`numero3`, `nombreDepartamento`) VALUES('" + zona + "'  ,'" + tipoCalle + "'  ,'" + numero1 + "','" + numero2 + "','" + numero3 + "','" + departamento + "');";
            try {
                connection = conexion.getConnection();
                st = connection.createStatement();
                st.execute(query);
                //String queryIdDireccion = "SELECT idDireccion FROM `direccion` WHERE nombreDepartamento = '" + departamento + "' AND zona = '" + zona + "' AND tipoCalle = '" + "' AND numero1 = '" + numero1 + "'AND numero2 ='" + numero2 + "' AND numero3 = '" + numero3 + "';";
               
                String queryIdDireccion = "SELECT idDireccion FROM `direccion`WHERE nombreDepartamento='" + departamento + "'AND zona ='" + zona + "'AND tipoCalle = '" + tipoCalle + "'AND numero1 ='" + numero1 + "'AND numero2 ='" + numero2 + "'AND numero3 ='" + numero3 + "';";
                try {
                    rs = st.executeQuery(queryIdDireccion);
                    while (rs.next()) {
                        int idDireccion = rs.getInt("idDireccion");
                        //String queryInsertSucursal = "INSERT INTO sucursal (`nombreSucursal`, `FK_nit`,`FK_idDireccion`) VALUES ('" +sucursal+ "',999999991,'" +idDireccion+ "')";
                        String queryInsertSucursal = "INSERT INTO `sucursal`(`nombreSucursal`, `FK_nit`,`FK_idDireccion`) VALUES ('" + sucursal + "',999999991," + idDireccion + ")";

                        System.out.println(queryInsertSucursal);
                        try {
                            st.executeUpdate(queryInsertSucursal);
                            JOptionPane.showMessageDialog(this, "La sucursal ha sido creada");
                        } catch (SQLException e) {
                            System.out.println(e);

                        }
                    }
                    }catch (SQLException e){
                    System.out.println(e);
                    
                }
                }catch (SQLException e){
                    System.out.println(e);
                    JOptionPane.showMessageDialog(this,"no fue posible crear la sucursal");
            }
        }
    }
       
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Sucursal");

        txtSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSucursalActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/company (1).png"))); // NOI18N
        btnGuardar.setText(" Crear");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnGuardar)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnGuardar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String sucursal = txtSucursal.getText();
        if (sucursal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la sucursal es requerido", "Sucursal", JOptionPane.ERROR_MESSAGE);

        } else {
            this.dispose();
        }

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSucursalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SucursalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SucursalForm dialog = new SucursalForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtSucursal;
    // End of variables declaration//GEN-END:variables
}
