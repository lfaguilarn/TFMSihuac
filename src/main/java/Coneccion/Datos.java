/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coneccion;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Luis Fernando
 */
public class Datos {
    private static Connection conector ;
    public Connection Conectar (String param){
    String BD="jdbc:postgresql://localhost:5432/huaca";
    String usuario="postgres";
    String contra="21061993";
    try{
        Class.forName("org.postgresql.Driver");
        //conector = DriverManager.getConnection(BD,usuario,contra);
        conector = DriverManager.getConnection(BD,usuario,contra);
        System.out.println("conectar "+ param);
        //JOptionPane.showMessageDialog(null,"Coneccion exitosa");
    } catch(Exception e){
        System.out.println("Error al conectar: "+e);
        //JOptionPane.showMessageDialog(null,"Error al conectar: "+e);
    }
    return conector;
    }
    public void Desconectar (Connection conect){
        try {
            conect.close();
            //System.out.println("conexion cerrada");
        }
        catch (Exception e) {
            System.out.println("Error en cerrar conexion" + e.getMessage());
        }
    }
}
    
