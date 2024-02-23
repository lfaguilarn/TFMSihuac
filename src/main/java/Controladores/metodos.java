/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.swing.JFrame;

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
    
    
}
