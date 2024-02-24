/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Luis Fernando
 */
public class metodos {
    public void centrarVentana(JFrame ventana, boolean maximizar)
    {
      ventana.setLocationRelativeTo(null);
      ventana.setResizable(maximizar);
    }

    public void ventanaSinBorde(JFrame ventana)
    {
      ventana.setUndecorated(true);
    }

    public void ventanaMaximizada(JFrame ventana)
    {
      ventana.setExtendedState(ventana.MAXIMIZED_BOTH);
    }

    public void ventanaSinBtnMax(JFrame ventana)
    {
      ventana.setResizable(false);
    }
    
    public void cambiarImgLbl(JLabel lbl, String imagen) 
    {
        ImageIcon newicon = null;
        ImageIcon foto = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/"+imagen));
        Image img = foto.getImage();
        Image newimg = img.getScaledInstance(lbl.getBounds().width, lbl.getBounds().height, Image.SCALE_SMOOTH);
        newicon = new javax.swing.ImageIcon(newimg);
        lbl.setIcon(newicon);
    }
    
}
