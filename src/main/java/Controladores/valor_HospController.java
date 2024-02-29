/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Coneccion.Datos;
import Entidades.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fernando
 */
public class valor_HospController {
    Datos datos = new Datos();
  public Connection conectar;
  String valHos = "valor Hosp";
  public List<valor_Hosp> listarValor_Hosp(String parametro)
  {
    List<valor_Hosp> listValor_Hosp = new ArrayList();
    this.conectar = this.datos.Conectar(valHos);
    String sql = "Select * from \"valor_Hosp\" where id LIKE '%" + parametro + "%' OR descripcion LIKE '%" + parametro + "%' OR costo LIKE '%" + parametro + "%' OR habitacion LIKE '%" + parametro + "%' ORDER BY habitacion::numeric ASC";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        valor_Hosp clientHab = new valor_Hosp();
        clientHab.setId(result.getString(1));
        clientHab.setDescripcion(result.getString(2));
        clientHab.setCosto(result.getString(3));
        clientHab.setHabitacion(result.getString(4));
          listValor_Hosp.add(clientHab);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar valor_Hosp " + e.getMessage());
    }
    return listValor_Hosp;
  }
  
  public valor_Hosp buscarValorHosp(String descripcion, List<valor_Hosp> listValorHosp, String hab)
  {
    valor_Hosp clientHab = new valor_Hosp();
    for (int i = 0; i < listValorHosp.size(); i++){
        if (listValorHosp.get(i).getDescripcion().equals(descripcion)&&
                listValorHosp.get(i).getHabitacion().equals(hab)) {
            clientHab = listValorHosp.get(i);
        }
    }
    return clientHab;
  }
  
  public String[] obtenerDescripcion(List<valor_Hosp> list, String hab){
      String [] result = new String[list.size()];
      for (int i = 0; i < list.size(); i++) {
          if (list.get(i).getHabitacion().equals(hab)) {
            result[i] = list.get(i).getDescripcion();
          }
      }
      return result;
  }
  
  
  public boolean ingresarValorHosp(valor_Hosp user)
  {
    this.conectar = this.datos.Conectar(valHos);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into \"valor_Hosp\" values(?,?,?,?)");
      pst.setString(1, user.getId());
      pst.setString(2, user.getDescripcion());
      pst.setString(3, user.getCosto());
      pst.setString(4, user.getHabitacion());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar valor_Hosp " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarValorHosp(valor_Hosp valorHosp, String id)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(valHos);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"valor_Hosp\" set id = ?, descripcion = ?, costo = ?, habitacion=? where id =?");
      pst.setString(1, valorHosp.getId());
      pst.setString(2, valorHosp.getDescripcion());
      pst.setString(3, valorHosp.getCosto());
      pst.setString(4, valorHosp.getHabitacion());
      pst.setString(5, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar valor_Hosp " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarValorHospPorDescripcion(String descripcion, String valor, String hab)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(valHos);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"valor_Hosp\" set costo = ? where descripcion =? and habitacion = ?");
      pst.setString(1, valor);
      pst.setString(2, descripcion);
      pst.setString(3, hab);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar valor_Hosp por descripcion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean eliminarValorHosp(String id)
  {
      this.conectar = this.datos.Conectar(valHos);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from \"valor_Hosp\" where id=?");
      pst.setString(1, id);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar valor_Hosp " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaValorHosp(List<valor_Hosp> Lista)
  {
    String[] titulos = {"Habitación","Tipo de Huésped", "Costo $"};
    String[] registro = new String[titulos.length];
    DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    for (valor_Hosp valor_Hosp : Lista)
    {
      registro[0] = valor_Hosp.getHabitacion();
      registro[1] = valor_Hosp.getDescripcion();
      registro[2] = valor_Hosp.getCosto();
      modelo.addRow(registro);
    }
    return modelo;
  }
  
  public boolean valorHospExistente(String descripcion, List<valor_Hosp> listValorHosp)
  {
    boolean result = false;
    for (int i = 0; i < listValorHosp.size(); i++) {
      if (((valor_Hosp)listValorHosp.get(i)).getDescripcion().equals(descripcion)) {
        result = true;
      }
    }
    return result;
  }
}
