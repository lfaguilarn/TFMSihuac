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
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fernando
 */
public class consumoController {
  Datos datos = new Datos();
  public Connection conectar;
  String cons = "consumo";
  public List<consumo> listarConsumo(String parametro)
  {
    List<consumo> listConsumo = new ArrayList();
    this.conectar = this.datos.Conectar(cons);
    String sql = "Select * from consumo where id LIKE '%" + parametro + "%' OR \"idHab\" LIKE '%" + parametro + "%' OR descripcion LIKE '%" + parametro + "%' OR \"idVisita\" LIKE '%" + parametro + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        consumo consum = new consumo();
        consum.setId(result.getString(1));
        consum.setIdHab(result.getString(2));
        consum.setDescripcion(result.getString(3));
        consum.setPrecio(result.getString(4));
        consum.setIdVisita(result.getString(5));
        listConsumo.add(consum);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar consumo " + e.getMessage());
    }
    return listConsumo;
  }
  public List<consumo> listarConsumoPorIdHab(String idHab)
  {
    List<consumo> listConsumo = new ArrayList();
    this.conectar = this.datos.Conectar(cons);
    String sql = "Select * from consumo where \"idHab\" = '" + idHab + "'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        consumo consum = new consumo();
        consum.setId(result.getString(1));
        consum.setIdHab(result.getString(2));
        consum.setDescripcion(result.getString(3));
        consum.setPrecio(result.getString(4));
        consum.setIdVisita(result.getString(5));
        listConsumo.add(consum);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar consumo -" + e.getMessage());
    }
    return listConsumo;
  }
  
  public consumo buscarConsumo(String id, List<consumo> listConsumo)
  {
    consumo consum = new consumo();
    for (int i = 0; i < listConsumo.size(); i++)
    {
      if (((consumo)listConsumo.get(i)).getId().equals(id)) {
        consum = (consumo)listConsumo.get(i);
      }
    }
    return consum;
  }
  public List<consumo> buscarConsumoPorIdVis(String idVisita, List<consumo> listConsumo)
  {
    List<consumo> consum = new ArrayList<>();
    for (int i = 0; i < listConsumo.size(); i++)
    {
      if (((consumo)listConsumo.get(i)).getIdVisita().equals(idVisita)) {
        consum.add(listConsumo.get(i));
      }
    }
    return consum;
  }
  public String buscarConsumoTotalPorIdVis(String id, List<consumo> listConsumo)
  {
    double cost = 0;
    for (int i = 0; i < listConsumo.size(); i++)
    {
      if (((consumo)listConsumo.get(i)).getIdVisita().equals(id)) {
          cost = cost + Double.parseDouble(listConsumo.get(i).getPrecio());
      }
    }
    return cost+"";
  }
  public String buscarConsumosPorIdVis(String idVis, List<consumo> listConsumo)
  {
    String cost = "";
    for (int i = 0; i < listConsumo.size(); i++)
    {
      if (((consumo)listConsumo.get(i)).getIdVisita().equals(idVis)) {
          if (i<(listConsumo.size()-1)) {
              cost = cost + listConsumo.get(i).getDescripcion()+", ";
          }else{
              cost = cost + listConsumo.get(i).getDescripcion();
          }
      }
    }
    if (listConsumo.size()==0) {
          cost="N/A";
      }
    return cost+"";
  }
  
  public int idConsumo(){
      int result = 0;
      String sql = "";
      if (listarConsumo("").size()==0) {
          result = 1;
      }else{
        this.conectar = this.datos.Conectar(cons);
        sql = "Select MAX(id::integer) from consumo";
        try
        {
          Statement st = this.conectar.createStatement();
          ResultSet resultSql = st.executeQuery(sql);
          while (resultSql.next())
          {
            result = Integer.parseInt(resultSql.getString(1))+1;
          }
          resultSql.close();
          st.close();
          this.datos.Desconectar(this.conectar);
        }
        catch (Exception e)
        {
          System.out.println("Error en buscar id consumo +" + e.getMessage());
        } 
      }
      return result;
  }
  
  public boolean ingresarConsumo(consumo user)
  {
    this.conectar = this.datos.Conectar(cons);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into consumo values(?,?,?,?,?)");
      
      pst.setString(1, (idConsumo())+"");
      pst.setString(2, user.getIdHab());
      pst.setString(3, user.getDescripcion());
      pst.setString(4, user.getPrecio());
      pst.setString(5, user.getIdVisita());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar consumo " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarConsumo(consumo user, String id)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(cons);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update consumo \"idHab\" = ?, descripcion = ?, precio=?, \"idVisita\"=? where id =?");
      pst.setString(1, user.getIdHab());
      pst.setString(2, user.getDescripcion());
      pst.setString(3, user.getPrecio());
      pst.setString(4, user.getIdVisita());
      pst.setString(5, user.getId());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar consumo " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarPrecioConsumo(String id, String precio)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(cons);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update consumo set precio=? where id =?");
      pst.setString(1, precio);
      pst.setString(2, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado consumo " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean eliminarConsumo(String idConsumo)
  {
      this.conectar = this.datos.Conectar(cons);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from consumo where id=?");
      pst.setString(1, idConsumo);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar consumo " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean eliminarConsumoIdVis(String id_Visita)
  {
      this.conectar = this.datos.Conectar(cons);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from consumo where \"idVisita\"=?");
      pst.setString(1, id_Visita);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar consumo " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaConsumo(List<consumo> Lista, String consumo, List<cliente> clis, List<visita> visitas){
      clienteController controlCli = new clienteController();
      visitaController controlVis = new visitaController();
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      Date hora = new Date();
      int h=0,min=0;
      String[] titulos = {"Id", "Descripcion","$ Costo","Id Visita"};
      String[] registro = new String[titulos.length];
      DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
          public boolean isCellEditable(int row, int column) {
              return false;
          }
      };
      for (consumo consum : Lista){
              registro[0] = consum.getId();
              registro[1] = consum.getDescripcion();
              registro[2] = consum.getPrecio();
              registro[3] = consum.getIdVisita();
              modelo.addRow(registro);
          
      }
      return modelo;
  }
  
  public String obtenerCostoTotalHab(String idHab, List<cliente_Hab> listCliHab, List<consumo> list){
      String result = "";
      double cost = 0;
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=fecha.getHours(),min=fecha.getMinutes();
      for (int i = 0; i < list.size(); i++) {
          for (int j = 0; j < listCliHab.size(); j++) {
              if (list.get(i).getIdHab().equals(idHab)&&list.get(i).getIdVisita().equals(listCliHab.get(j).getId_visita())) {
                  cost = cost+ Double.parseDouble(list.get(i).getPrecio());
              }
          }
      }
      result = cost+"";
      return result;
  }
  public String obtenerCostoPorIdVisita(String idVisita, List<consumo> listConsumo){
      String result = "";
      double cost = 0;
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=fecha.getHours(),min=fecha.getMinutes();
      for (int i = 0; i < listConsumo.size(); i++) {
          if (listConsumo.get(i).getIdVisita().equals(idVisita)) {
              cost = cost + Double.parseDouble(listConsumo.get(i).getPrecio());
          }
      }
      result = cost+"";
      return result;
  }
  public String obtenerConsumoPorIdVisita(String idVisita, List<consumo> listConsumo){
      double cost = 0;
      Date fecha = new Date();
      metodos m = new metodos();
      String result = m.personalizarMsj("» Consumos extras «", "", "times new roman", m.normal-1, true, false, false)+"\n";
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=fecha.getHours(),min=fecha.getMinutes();
      for (int i = 0; i < listConsumo.size(); i++) {
          if (listConsumo.get(i).getIdVisita().equals(idVisita)) {
              result = result + m.personalizarMsj("♦ "+ listConsumo.get(i).getDescripcion() +" : $"+ listConsumo.get(i).getPrecio(), m.azul, "times new roman", m.normal, false, false, false) +"\n";
              cost = cost + Double.parseDouble(listConsumo.get(i).getPrecio());
          }
      }
      //result = result+m.personalizarMsj("Consumo extra Total: $"+m.redondearCerDer(cost,2), "", "times new roman", m.titulo2, true, false, false)+"\n";
      if (cost==0) {
          result="";
      }
      return result;
  }
   
}
