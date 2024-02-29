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
public class habitacionController {
    Datos datos = new Datos();
  public Connection conectar;
  String hab = "habitacion";
  public List<habitacion> listarHabitacion(String parametro)
  {
    List<habitacion> listHabitacion = new ArrayList();
    this.conectar = this.datos.Conectar(hab+" - listar");
    String sql = "Select * from habitacion where id LIKE '%" + parametro + "%' OR estado LIKE '%" + parametro + "%' OR observaciones LIKE '%" + parametro + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        habitacion hab = new habitacion();
        hab.setId(result.getString(1));
        hab.setCapacidad(result.getString(2));
        hab.setEstado(result.getString(3));
        hab.setObservaciones(result.getString(4));
          listHabitacion.add(hab);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar habitacion " + e.getMessage());
    }
    return listHabitacion;
  }
  
  public habitacion buscarHabitacion(String id, List<habitacion> listHabitacion)
  {
    habitacion hab = new habitacion();
    for (int i = 0; i < listHabitacion.size(); i++)
    {
      if (((habitacion)listHabitacion.get(i)).getId().equals(id)) {
        hab = (habitacion)listHabitacion.get(i);
      }
      if (((habitacion)listHabitacion.get(i)).getEstado().equalsIgnoreCase(id)) {
        hab = (habitacion)listHabitacion.get(i);
      }
    }
    return hab;
  }
  public habitacion buscarHabitacionPorId(String id, List<habitacion> listHabitacion)
  {
    habitacion hab = new habitacion();
    for (int i = 0; i < listHabitacion.size(); i++)
    {
      if (((habitacion)listHabitacion.get(i)).getId().equals(id)) {
        hab = (habitacion)listHabitacion.get(i);
      }
    }
    return hab;
  }
  
  public String estadoHab(String id, List<habitacion> listHabitacion){
    String estado = "";
    for (int i = 0; i < listHabitacion.size(); i++)
    {
      if (((habitacion)listHabitacion.get(i)).getId().equals(id)) {
        estado = listHabitacion.get(i).getEstado();
      }
    }
    return estado;
  }
  public String imgEstadoHab(String id, List<habitacion> listHabitacion, int num){
    String estado = "";
    String img = "";
    for (int i = 0; i < listHabitacion.size(); i++)
    {
      if (((habitacion)listHabitacion.get(i)).getId().equals(id)) {
        estado = listHabitacion.get(i).getEstado();
      }
    }
    switch(estado){
                case "Disponible":{
                    img = "verde"+num+".png";
                    break;
                }
                case "Ocupado":{
                    img = "rojo"+num+".png";
                    break;
                }
                case "Reservado":{
                    img = "amarillo"+num+".png";
                    break;
                }
                case "No Disponible":{
                    img = "gris"+num+".png";
                    break;
                }
            }
    return img;
  }
  
  public boolean ingresarHabitacion(habitacion user)
  {
    this.conectar = this.datos.Conectar(hab+" - ingresar");
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into habitacion values(?,?,?,?)");
      pst.setString(1, user.getId());
      pst.setString(2, user.getCapacidad());
      pst.setString(3, user.getEstado());
      pst.setString(4, user.getObservaciones());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarHabitacion(habitacion user, String id)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(hab+" - act");
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update habitacion set id = ?, capacidad = ?, estado = ?, observaciones = ? where id =?");
      pst.setString(1, user.getId());
      pst.setString(2, user.getCapacidad());
      pst.setString(3, user.getEstado());
      pst.setString(4, user.getObservaciones());
      pst.setString(5, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarCapHabitacion(String idHab, String cap)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(hab+" - act Cap");
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update habitacion set capacidad = ? where id =?");
      pst.setString(1, cap);
      pst.setString(2, idHab);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar capacidad de habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarEstadoHabitacion(String id, String estado)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(hab+" - act Est");
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update habitacion set estado = ? where id =?");
      pst.setString(1, estado);
      pst.setString(2, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado de habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarEstadoHabitacionDisponible(String id, String estado){
      String state = "'No Disponible'";
      boolean op = false;
      this.conectar = this.datos.Conectar(hab+" - act Est Hab Disp");
      try{
          Statement st = this.conectar.createStatement();
        PreparedStatement pst = this.conectar.prepareStatement("Update habitacion set estado = ? where id =? and estado!= "+state);
        pst.setString(1, estado);
        pst.setString(2, id);
        int n = pst.executeUpdate();
        if (n != 0) {
          op = true;
        }
      }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado de habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarObservHabitacion(String id, String obser)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(hab+" - act Obs");
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update habitacion set observaciones = ? where id =?");
      pst.setString(1, obser);
      pst.setString(2, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar las observaciones de habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean eliminarHabitacion(String id)
  {
      this.conectar = this.datos.Conectar(hab+" - elim Hab");
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from habitacion where id=?");
      pst.setString(1, id);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar habitacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaHabitacion(List<habitacion> Lista)
  {
    String[] titulos = { "Habitacion", "Capacidad", "Estado", "Observaciones"};
    String[] registro = new String[titulos.length];
    DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos);
    for (habitacion habitacion : Lista)
    {
      registro[0] = habitacion.getId();
      registro[1] = habitacion.getCapacidad();
      registro[2] = habitacion.getEstado();
      registro[3] = habitacion.getObservaciones();
      modelo.addRow(registro);
    }
    return modelo;
  }
  
  public boolean HabExistente(String id, List<habitacion> listHabitacion)
  {
    boolean result = false;
    for (int i = 0; i < listHabitacion.size(); i++) {
      if (((habitacion)listHabitacion.get(i)).getId().equals(id)) {
        result = true;
      }
    }
    return result;
  }
  
  public String capacidadMax(List<habitacion> habitaciones, List<cliente_Hab> list){
      String result ="";
      int capMax=0;
      int capDisp=0;
      cliente_HabController controlCliHab = new cliente_HabController();
      for (int i = 0; i < habitaciones.size(); i++) {
          if (habitaciones.get(i).getEstado().equals("Disponible")) {
              capDisp=capDisp+Integer.parseInt(habitaciones.get(i).getCapacidad());
          }
          capMax=capMax+Integer.parseInt(habitaciones.get(i).getCapacidad());
      }
      result = "Cap. Máx.: "+capMax+" us. Cap. Disp.: "+capDisp+" us. Huéspuedes: "+controlCliHab.cantTotalHuesp(list)+" us.";
      return result;
  }
  
  public List<habitacion> habDispOcp(List<habitacion> list){
      List<habitacion> result = new ArrayList<>();
      for (int i = 0; i < list.size(); i++) {
          if (list.get(i).getEstado().equals("Disponible")||list.get(i).getEstado().equals("Ocupado")||list.get(i).getEstado().equals("Reservado")) {
              result.add(list.get(i));
          }
      }
      return result;
  }
  public List<habitacion> habActivas(List<habitacion> list){
      List<habitacion> result = new ArrayList<>();
      for (int i = 0; i < list.size(); i++) {
          if (!list.get(i).getEstado().equals("No Disponible")) {
              result.add(list.get(i));
          }
      }
      return result;
  }
}
