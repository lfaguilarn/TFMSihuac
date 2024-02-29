/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfmsihuac.tfmsihuac;

import Controladores.metodos;
import Controladores.usuarioController;
import Entidades.usuario;
//import com.sun.org.apache.bcel.internal.generic.IFEQ;
import java.util.*;
import javax.swing.JButton;
/**
 *
 * @author Luis Fernando
 */
public class InicioSesion extends javax.swing.JFrame {
     metodos metodo = new metodos();
    usuarioController controlUser = new usuarioController();
    List<usuario> usuarios= controlUser.listarUsuarios("");
    /**
     * Creates new form InicioSesion
     */
    public InicioSesion() {
        initComponents();
        this.setBounds(0, 0, 600, 450);
        this.setTitle("Bienvenido - SiHuac");
        metodo.centrarVentana(this, false);
 //       metodo.ventanaMaximizada(this);
        metodo.ventanaSinBtnMax(this);
        //lblFondo1.setSize(Integer.parseInt(metodo.anchoVentana(this)+"".split(".")[0]), Integer.parseInt(metodo.altoVentana(this)+"".split(".")[0]));
        lblFondo1.setBounds(0, 0, (int)metodo.anchoVentana(this), (int)metodo.altoVentana(this));
        metodo.cambiarImgLbl(lblInicioSesion, "InicioSesion.png");
        metodo.cambiarImgLbl(lblFondo1, "Logueo.jpg");
        //System.out.println(metodo.anchoVentana(this) + " - "+metodo.altoVentana(this));
        metodo.botonInvisible(btnAceptar);
        metodo.botonInvisible(btnCancelar);
        metodo.cambiarImgBoton(btnAceptar, "aceptar1.png");
        metodo.cambiarImgBoton(btnCancelar, "cancelar1.png");
        metodo.cambiarMouseBtn("mano", btnAceptar);
        metodo.cambiarMouseBtn("mano", btnCancelar);
        metodo.BtnDefecto(this, btnAceptar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblInicioSesion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtClave = new javax.swing.JPasswordField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblFondo1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1800, 1000));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Contraseña:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 210, 130, 50);

        lblInicioSesion.setBackground(new java.awt.Color(255, 255, 255));
        lblInicioSesion.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        lblInicioSesion.setForeground(new java.awt.Color(255, 255, 255));
        lblInicioSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblInicioSesion);
        lblInicioSesion.setBounds(-5, 30, 600, 80);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Usuario:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(80, 130, 110, 50);

        txtUsuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(140, 180, 340, 40);

        txtClave.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        getContentPane().add(txtClave);
        txtClave.setBounds(140, 260, 340, 40);

        btnAceptar.setOpaque(false);
        btnAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAceptarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAceptarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAceptarMouseReleased(evt);
            }
        });
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar);
        btnAceptar.setBounds(90, 330, 190, 70);

        btnCancelar.setOpaque(false);
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCancelarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnCancelarMouseReleased(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(340, 330, 190, 70);
        getContentPane().add(lblFondo1);
        lblFondo1.setBounds(0, 0, 210, 80);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseEntered
        metodo.cambiarImgBoton(btnAceptar, "aceptar2.png");
    }//GEN-LAST:event_btnAceptarMouseEntered

    private void btnAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseExited
        metodo.cambiarImgBoton(btnAceptar, "aceptar1.png");
    }//GEN-LAST:event_btnAceptarMouseExited

    private void btnAceptarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMousePressed
        metodo.cambiarImgBoton(btnAceptar, "aceptar3.png");
    }//GEN-LAST:event_btnAceptarMousePressed

    private void btnAceptarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAceptarMouseReleased
        metodo.cambiarImgBoton(btnAceptar, "aceptar2.png");
    }//GEN-LAST:event_btnAceptarMouseReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String usuario = txtUsuario.getText();
        String clave = txtClave.getText();
        boolean us = false;
        boolean cl = false;
        String userName = "";
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuario.equals(usuarios.get(i).getNickname())) {
                us = true;
                if (clave.equals(usuarios.get(i).getClave())&&us) {
                    cl=true;
                    userName = usuarios.get(i).getNombres();
                }
            }
        }
        if (us) {
            if (cl) {
                controlUser.cambiarEstado("Activo", txtUsuario.getText());
                metodo.mensajeDialogo(metodo.personalizarMsj("Bienvenid@ "+userName, metodo.verde, "times new roman", metodo.titulo2, true, false, false), "SiHuac - Sistema Informático de la Huaca", metodo.informacion);
                //Principal p = new Principal();
                //p.setVisible(true);
                //this.dispose();
                
            }else{
                metodo.mensajeDialogo(metodo.personalizarMsj("Clave incorrecta", metodo.rojo, "times new roman", metodo.titulo2, true, false, false), "SiHuac - Sistema Informático de la Huaca", metodo.error);
            }
        }else{
            metodo.mensajeDialogo(metodo.personalizarMsj("Usuario incorrecto", metodo.rojo, "times new roman", metodo.titulo2, true, false, false), "SiHuac - Sistema Informático de la Huaca", metodo.error);

        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseEntered
        metodo.cambiarImgBoton(btnCancelar, "cancelar2.png");
    }//GEN-LAST:event_btnCancelarMouseEntered

    private void btnCancelarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseExited
        metodo.cambiarImgBoton(btnCancelar, "cancelar1.png");
    }//GEN-LAST:event_btnCancelarMouseExited

    private void btnCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMousePressed
        metodo.cambiarImgBoton(btnCancelar, "cancelar3.png");
    }//GEN-LAST:event_btnCancelarMousePressed

    private void btnCancelarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseReleased
        metodo.cambiarImgBoton(btnCancelar, "cancelar2.png");
    }//GEN-LAST:event_btnCancelarMouseReleased

    /**
     * @param args the command line arguments
     */
    
//    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*       try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
*/
        /* Create and display the form */
/*        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblFondo1;
    private javax.swing.JLabel lblInicioSesion;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}