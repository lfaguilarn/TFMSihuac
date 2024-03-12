/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfmsihuac.tfmsihuac;

import Controladores.clienteController;
import Controladores.metodos;
import Controladores.usuarioController;
import Entidades.cliente;
import Entidades.usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;
import javax.swing.Timer;

/**
 *
 * @author Luis Fernando
 */
public class Inicio extends javax.swing.JFrame {
    metodos  metodo = new metodos();
    usuarioController controlUsuario = new usuarioController();
    List<usuario> listaUsuario = controlUsuario.listarUsuarios("");
    public static javax.swing.Timer tiempo1;
    int cont = 0;
    
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        if (listaUsuario.size()==0) {
            metodo.mensajeDialogo("No existen usuarios en el sistema\nPor favor ingrese datos para crear el Administrador del sistema", "Error", "error");
            usuario user = new usuario();
            //String [] opciones = {"Aceptar", "Cancelarr"};
            String nombreUsuario="";
            do {                
                nombreUsuario = metodo.mensajeDialogoEntradaCombo("Ingrese los nombres completos del Administrador", "Información", "informacion", null, "Administrador");
            } while (nombreUsuario==null||nombreUsuario.split(" ").length==0||nombreUsuario.equals(""));
            user.setNombres(nombreUsuario);
            String nick="";
            do {                
                nick = metodo.mensajeDialogoEntradaCombo("Ingrese el usuario para el Administrador", "Información", "informacion", null, "admin");
            } while (nick==null||nick.split(" ").length==0||nick.equals(""));
            user.setNickname(nick);
            String clave="";
            do {                
                clave = metodo.mensajeDialogoEntradaCombo("Ingrese la clave del Administrador", "Información", "informacion", null, "1234");
            } while (clave==null||clave.split(" ").length==0||clave.equals(""));
            user.setClave(clave);
            user.setCargo("Administrador");
            user.setCliente("True");
            user.setHab("True");
            user.setValorhosp("True");
            user.setReservacion("True");
            user.setVisita("True");
            user.setUsuario("True");
            user.setEstado("Activo");
            controlUsuario.ingresarUsuario(user);
            metodo.mensajeDialogo("Administrador creado con éxito", "Aviso", "informacion");
            initComponents();
            metodo.centrarVentana(this, false);
        }else{
            //metodo.mensajeDialogo("Bienvenido a SiHuac\nSistema Informatico de la Huaca", "Bienvenido", "informacion");
            initComponents();
            metodo.centrarVentana(this, false);
            System.out.println(".");
            metodo.cambiarImgLbl(lblInicio, "LogoInicio.jpg");
            System.out.println("*");
            tiempo1 = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont++;
                boolean ver = false;
                usuarioController controlUsuario = new usuarioController();
                List<usuario>usuarios = controlUsuario.listarUsuarios("");
                for (int i = 0; i < usuarios.size(); i++) {
                    if (((usuario)usuarios.get(i)).getEstado().equals("Activo")) {
                        ver=true;
                    }
                }
                if (cont>=3&&!ver) {
                    InicioSesion p = new InicioSesion();
                    p.setVisible(!ver);
                    dispose();
                    tiempo1.stop();
                }
                if (cont==1&&ver) {
                    Principal prin = new Principal();
                    prin.setVisible(ver);
                    dispose();
                    System.out.println("Usuario logueado");
                    tiempo1.stop();
                }
            }
        });
            tiempo1.start();
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblInicio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio");
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(700, 300));
        getContentPane().setLayout(null);
        getContentPane().add(lblInicio);
        lblInicio.setBounds(0, 0, 700, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblInicio;
    // End of variables declaration//GEN-END:variables
}
