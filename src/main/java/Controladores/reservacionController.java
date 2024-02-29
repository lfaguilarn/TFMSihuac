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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fernando
 */
public class reservacionController {
  Datos datos = new Datos();
  public Connection conectar;
  String reserv = "reservacion";
  public List<reservacion> listarReservaciones(String parametro)
  {
    List<reservacion> listReservaciones = new ArrayList();
    this.conectar = this.datos.Conectar(reserv);
    String sql = "Select * from reservacion where UPPER(id) LIKE '%" + parametro + "%' OR UPPER(id_hab) LIKE '%" + parametro + "%' OR UPPER(id_cliente) LIKE '%" + parametro + "%' OR UPPER(estado) LIKE '%" + parametro + "%' OR UPPER(nombres) LIKE '%" + parametro + "%' OR UPPER(direccion) LIKE '%" + parametro + "%' OR UPPER(hora_ingreso) LIKE '%" + parametro + "%' OR UPPER(fecha_ing) LIKE '%" + parametro + "%' OR UPPER(fecha_sal) LIKE '%" + parametro + "%' OR UPPER(observaciones) LIKE '%" + parametro + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        reservacion reserv = new reservacion();
        reserv.setId(result.getString(1));
        reserv.setId_hab(result.getString(2));
        reserv.setId_cliente(result.getString(3));
        reserv.setFecha_ing(result.getString(4));
        reserv.setFecha_sal(result.getString(5));
        reserv.setEstado(result.getString(6));
        reserv.setObservaciones(result.getString(7));
        reserv.setNombres(result.getString(8));
        reserv.setDireccion(result.getString(9));
        reserv.setHora_ingreso(result.getString(10));
        reserv.setHora_salida(result.getString(11));
        reserv.setCant_us(result.getString(12));
        reserv.setCant_dias(result.getString(13));
        reserv.setTotal_pagar(result.getString(14));
        listReservaciones.add(reserv);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar reservacion " + e.getMessage());
    }
    return listReservaciones;
  }
  public List<reservacion> listarReservacionesPorIdHab(String idHab, String estado)
  {
    List<reservacion> listReservaciones = new ArrayList();
    this.conectar = this.datos.Conectar(reserv);
    String sql = "Select * from reservacion where UPPER(id_hab) = '" + idHab + "' and (estado) LIKE '%" + estado + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        reservacion reserv = new reservacion();
        reserv.setId(result.getString(1));
        reserv.setId_hab(result.getString(2));
        reserv.setId_cliente(result.getString(3));
        reserv.setFecha_ing(result.getString(4));
        reserv.setFecha_sal(result.getString(5));
        reserv.setEstado(result.getString(6));
        reserv.setObservaciones(result.getString(7));
        reserv.setNombres(result.getString(8));
        reserv.setDireccion(result.getString(9));
        reserv.setHora_ingreso(result.getString(10));
        reserv.setHora_salida(result.getString(11));
        reserv.setCant_us(result.getString(12));
        reserv.setCant_dias(result.getString(13));
        reserv.setTotal_pagar(result.getString(14));
        listReservaciones.add(reserv);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar reservacion " + e.getMessage());
    }
    return listReservaciones;
  }
  
  public reservacion buscarReservaciones(String id, List<reservacion> listReservaciones)
  {
    reservacion reserv = new reservacion();
    for (int i = 0; i < listReservaciones.size(); i++)
    {
      if (((reservacion)listReservaciones.get(i)).getId().equals(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
      if (((reservacion)listReservaciones.get(i)).getId_cliente().equalsIgnoreCase(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
      if (((reservacion)listReservaciones.get(i)).getId_hab().equalsIgnoreCase(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
      if (((reservacion)listReservaciones.get(i)).getFecha_ing().equalsIgnoreCase(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
      if (((reservacion)listReservaciones.get(i)).getFecha_sal().equalsIgnoreCase(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
      if (((reservacion)listReservaciones.get(i)).getEstado().equalsIgnoreCase(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
      if (((reservacion)listReservaciones.get(i)).getObservaciones().equalsIgnoreCase(id)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
    }
    return reserv;
  }
  public reservacion buscarReservacionesPorId(String idRes, List<reservacion> listReservaciones)
  {
    reservacion reserv = new reservacion();
    for (int i = 0; i < listReservaciones.size(); i++)
    {
      if (((reservacion)listReservaciones.get(i)).getId().equals(idRes)) {
        reserv = (reservacion)listReservaciones.get(i);
      }
    }
    return reserv;
  }
  public List<reservacion> buscarReservacionesPorIdHab(String id, List<reservacion> listReservaciones)
  {
    List<reservacion> reserv = new ArrayList<>();
    for (int i = 0; i < listReservaciones.size(); i++)
    {
      if (((reservacion)listReservaciones.get(i)).getId_hab().equals(id)) {
        reserv.add((reservacion)listReservaciones.get(i));
      }
    }
    return reserv;
  }
  public List<reservacion> buscarReservacionesActivas(List<reservacion> listReservaciones)
  {
    List<reservacion> reserv = new ArrayList<>();
    for (int i = 0; i < listReservaciones.size(); i++)
    {
      if (((reservacion)listReservaciones.get(i)).getEstado().equals("Activo")) {
        reserv.add((reservacion)listReservaciones.get(i));
      }
    }
    return reserv;
  }
  
  public boolean ingresarReservaciones(reservacion user)
  {
    this.conectar = this.datos.Conectar(reserv);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into reservacion values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
      pst.setString(1, idRes()+"");
      pst.setString(2, user.getId_hab());
      pst.setString(3, user.getId_cliente());
      pst.setString(4, user.getFecha_ing());
      pst.setString(5, user.getFecha_sal());
      pst.setString(6, user.getEstado());
      pst.setString(7, user.getObservaciones());
      pst.setString(8, user.getNombres());
      pst.setString(9, user.getDireccion());
      pst.setString(10, user.getHora_ingreso());
      pst.setString(11, user.getHora_salida());
      pst.setString(12, user.getCant_us());
      pst.setString(13, user.getCant_dias());
      pst.setString(14, user.getTotal_pagar());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar reservacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public int idRes(){
      int result = 0;
      String sql = "";
      if (listarReservaciones("").size()==0) {
          result = 1;
      }else{
        this.conectar = this.datos.Conectar(reserv);
        sql = "Select MAX(id::integer) from reservacion";
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
          System.out.println("Error en listar reservaciones para id" + e.getMessage());
        } 
      }
      return result;
  }
  
  public boolean actualizarReservaciones(reservacion user, String idRes)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(reserv);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update reservacion set id = ?, id_hab = ?, id_cliente = ? , fecha_ing =?, fecha_sal =?, estado=?, observaciones=?, nombres=?, direccion=?, hora_ingreso=?, hora_salida=?, cant_us=?, cant_dias=?, total_pagar=? where id =?");
      pst.setString(1, user.getId());
      pst.setString(2, user.getId_hab());
      pst.setString(3, user.getId_cliente());
      pst.setString(4, user.getFecha_ing());
      pst.setString(5, user.getFecha_sal());
      pst.setString(6, user.getEstado());
      pst.setString(7, user.getObservaciones());
      pst.setString(8, user.getNombres());
      pst.setString(9, user.getDireccion());
      pst.setString(10, user.getHora_ingreso());
      pst.setString(11, user.getHora_salida());
      pst.setString(12, user.getCant_us());
      pst.setString(13, user.getCant_dias());
      pst.setString(14, user.getTotal_pagar());
      pst.setString(15, idRes);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar reservacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarCampoReservaciones(String campo, String valor, String idRes)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(reserv);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update reservacion set "+campo+" = ? where id =?");
      pst.setString(1, valor);
      pst.setString(2, idRes);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar reservacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean eliminarReservaciones(String id)
  {
      this.conectar = this.datos.Conectar(reserv);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from reservacion where id=?");
      pst.setString(1, id);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar reservacion " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaReservaciones(List<reservacion> Lista)
  {
      metodos m = new metodos();
    String[] titulos = { "Id","Hab.", "Cliente", "Check-in", "Check-out","Días", "Cant. us.","$ Total" ,"$ Abono"};
    String[] registro = new String[titulos.length];DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    for (reservacion reservacion : Lista)
    {
      registro[0] = reservacion.getId();
      registro[1] = reservacion.getId_hab();
      registro[2] = reservacion.getNombres();
      registro[3] = reservacion.getFecha_ing()+" - "+m.de24A12Horas(reservacion.getHora_ingreso()).toLowerCase();
      registro[4] = reservacion.getFecha_sal()+" - "+m.de24A12Horas(reservacion.getHora_salida()).toLowerCase();
      registro[5] = reservacion.getCant_dias();
      registro[6] = reservacion.getCant_us();
      registro[7] = reservacion.getTotal_pagar();
      registro[8] = reservacion.getEstado();
      modelo.addRow(registro);
    }    
    return modelo;
  }
  
  public boolean resExistente(String id, List<reservacion> listReservaciones)
  {
    boolean result = false;
    for (int i = 0; i < listReservaciones.size(); i++) {
      if (((reservacion)listReservaciones.get(i)).getId().equals(id)) {
        result = true;
      }
    }
    return result;
  }
  public List<reservacion> resHabExistente(String idHab, List<reservacion> listReservaciones)
  {
    List<reservacion> reserv=new ArrayList<reservacion>();
    for (int i = 0; i < listReservaciones.size(); i++) {
      if (((reservacion)listReservaciones.get(i)).getId_hab().equals(idHab)
              //&&listReservaciones.get(i).getEstado().equals("Activo")
              ) {
        reserv.add(listReservaciones.get(i));
      }
    }
    return reserv;
  }
  
  public List<String> fechaReserv(String idHab, List<reservacion> listReservaciones){
      List<String> result = new ArrayList<String>();
      for (int i = 0; i < listReservaciones.size(); i++) {
          List<String> fechas = entreFechas(resHabExistente(idHab, listReservaciones).get(i).getFecha_ing(), resHabExistente(idHab, listReservaciones).get(i).getFecha_sal());
          for (int j = 0; j < fechas.size(); j++) {
              result.add(fechas.get(j));
              //System.out.println(fechas.get(i)+" * ");
          }
      }
      return result;
  }
  
  public List<String> entreFechas(String fechaIni, String fechaFin){
      List<String> result = new ArrayList<String>();
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      Date fechaIng = null;
      Date fechaSal = null;
      try {
          fechaIng = formato.parse(fechaIni);
          fechaSal = formato.parse(fechaFin);
      } catch (Exception e) {
          System.out.println(e.getMessage().toString());
      }
      while (fechaIng.compareTo(fechaSal)!=0) {
          //System.out.println("entro");
          result.add(fechaIng.getDate()+"/"+(fechaIng.getMonth()+1)+"/"+(fechaIng.getYear()+1900));
          //System.out.println(fechaIng.getDate()+"/"+(fechaIng.getMonth()+1)+"/"+(fechaIng.getYear()+1900)+" .*. ");
          fechaIng.setTime(fechaIng.getTime()+(1000*60*60*24));
      }
      return result;
  }
  
  public boolean validarFechasReserv(String idHab, List<reservacion> listReservaciones, String fechaIni, String FechaFin){
      boolean result = false;
      List<String> fechasRes = fechaReserv(idHab, resHabExistente(idHab, listReservaciones));
      //System.out.println(resHabExistente(id, listReservaciones).get(0).getFecha_ing()+" - "+resHabExistente(id, listReservaciones).get(0).getFecha_sal());
      List<String> fechasHab = entreFechas(fechaIni, FechaFin);
      /*for (int i = 0; i < fechasRes.size(); i++) {
          System.out.println(fechasRes.get(i)+" - ");
      }*/
      for (int i = 0; i < fechasRes.size(); i++) {
          for (int j = 0; j < fechasHab.size(); j++) {
              if (fechasRes.get(i).equals(fechasHab.get(j))) {
                  result = true;
              }
          }
      }
      return result;
  }
  
  public void eliminarResFech(String numHab, String fecha, List<reservacion> listR){
      for (int i = 0; i < listR.size(); i++) {
          if (listR.get(i).getId_hab().equals(numHab)
                  //&&listR.get(i).getEstado().equals("Activo")
                  ) {
              List<String> fechas = entreFechas(listR.get(i).getFecha_ing(), listR.get(i).getFecha_sal());
              for (int j = 0; j < fechas.size(); j++) {
                  if (fecha.equals(fechas.get(i))) {
                      eliminarReservaciones(listR.get(i).getId());
                  }
              }
          }
      }
  }
  public reservacion buscarResFech(String numHab, Date fecha, List<reservacion> listR){
      reservacion r = new reservacion();
      metodos m = new metodos();
      for (int i = 0; i < listR.size(); i++) {
          Date fechaIng = m.StringADate(listR.get(i).getFecha_ing(), "/");
          fechaIng.setHours(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[0]));
          fechaIng.setMinutes(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[1]));
          fechaIng.setSeconds(0);
          Date fechaSal = m.StringADate(listR.get(i).getFecha_sal(), "/");
          fechaSal.setHours(Integer.parseInt(listR.get(i).getHora_salida().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listR.get(i).getHora_salida().split(":")[1]));
          fechaSal.setSeconds(0);
          if (listR.get(i).getId_hab().equals(numHab)
                  //&&listR.get(i).getEstado().equals("Activo")
                  &&m.compararEntreFechas(fecha, fechaIng, fechaSal)) {
              r = listR.get(i);
          }
      }
      return r;
  }
  
  public String cantHuespRes(Date fecha, String numHab, List<reservacion> listR){
      String result = "";
      metodos m = new metodos();
      for (int i = 0; i < listR.size(); i++) {
          Date fechaIng = m.StringADate(listR.get(i).getFecha_ing(), "/");
          fechaIng.setHours(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[0]));
          fechaIng.setMinutes(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[1]));
          fechaIng.setSeconds(0);
          Date fechaSal = m.StringADate(listR.get(i).getFecha_sal(), "/");
          fechaSal.setHours(Integer.parseInt(listR.get(i).getHora_salida().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listR.get(i).getHora_salida().split(":")[1]));
          fechaSal.setSeconds(0);
          if (listR.get(i).getId_hab().equals(numHab)&&m.compararEntreFechas(fecha, fechaIng, fechaSal)) {
              result = listR.get(i).getObservaciones();
          }
      }
      return result;
  }
  public String costoRes(Date fecha, String numHab, List<reservacion> listR, List<valor_Hosp> listV){
      String result = "";
      double costo = 0;
      int cantAdul = 0;
      int cantNiño = 0;
      metodos m = new metodos();
      valor_HospController controlVH = new valor_HospController();
      double costAdult = Double.parseDouble(controlVH.buscarValorHosp("Adulto", listV, numHab).getCosto());
      double costNino = Double.parseDouble(controlVH.buscarValorHosp("Niño", listV, numHab).getCosto());
      for (int i = 0; i < listR.size(); i++) {
          Date fechaIng = m.StringADate(listR.get(i).getFecha_ing(), "/");
          fechaIng.setHours(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[0]));
          fechaIng.setMinutes(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[1]));
          fechaIng.setSeconds(0);
          Date fechaSal = m.StringADate(listR.get(i).getFecha_sal(), "/");
          fechaSal.setHours(Integer.parseInt(listR.get(i).getHora_salida().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listR.get(i).getHora_salida().split(":")[1]));
          fechaSal.setSeconds(0);
          if (listR.get(i).getId_hab().equals(numHab)&&m.compararEntreFechas(fecha, fechaIng, fechaSal)) {
              cantAdul = Integer.parseInt(listR.get(i).getObservaciones().split(" ")[0]);
              cantNiño = Integer.parseInt(listR.get(i).getObservaciones().split(" ")[3]);
          }
      }
      costo = (cantAdul*costAdult)+(cantNiño*costNino);
      return costo+"";
  }
  
  public String calcCostRes(double cantAdult, double cantNiños, double cantDias, List<valor_Hosp> listValorHosp, String numhab){
      String result = "";
      valor_HospController ctrlValorHosp = new valor_HospController();
      metodos m = new metodos();
      double valorNiño = Double.parseDouble(ctrlValorHosp.buscarValorHosp("Niño", listValorHosp, numhab).getCosto());
      double valorAdulto = Double.parseDouble(ctrlValorHosp.buscarValorHosp("Adulto", listValorHosp, numhab).getCosto());
      double total = (cantDias*valorAdulto*cantAdult)+(cantDias*valorNiño*cantNiños);
      result = "$ "+m.redondearCerDer(total, 2);
      return result;
  }
  
}
