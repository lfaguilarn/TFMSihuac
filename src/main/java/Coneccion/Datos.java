/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Coneccion;
import java.net.URL;
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
    String classname = "org.postgresql.Driver";
    URL url = null;
    try{        
        url = Class.forName("org.postgresql.Driver").getResource("/" + classname.replace('.', '/') + ".class");
        System.out.println("La ubicación de " + classname + " es: " + url);
        Class.forName("jar:file:/C:/Users/Luis%20Fernando/.m2/repository/org/postgresql/postgresql/42.6.1/postgresql-42.6.1.jar!/org/postgresql/Driver.class");
        //conector = DriverManager.getConnection(BD,usuario,contra);
        conector = DriverManager.getConnection(BD,usuario,contra);
        System.out.println("conectar "+ param);
        //JOptionPane.showMessageDialog(null,"Coneccion exitosa");
    } catch(Exception e){
        System.out.println("La ubicación de " + classname + " es: " + url);
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
    
