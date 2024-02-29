/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Coneccion.Datos;
import Entidades.*;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
public class cliente_HabController {
  Datos datos = new Datos();
  public Connection conectar;
  String cliHab = "cliente Hab";
  public List<cliente_Hab> listarCliente_Hab(String parametro)
  {
    List<cliente_Hab> listCliente_Hab = new ArrayList();
    this.conectar = this.datos.Conectar(cliHab);
    String sql = "Select * from \"cliente_Hab\" where id LIKE '%" + parametro + "%' OR id_cliente LIKE '%" + parametro + "%' OR id_hab LIKE '%" + parametro + "%' OR id_visita LIKE '%" + parametro + "%' OR consumo LIKE '%" + parametro + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        cliente_Hab clientHab = new cliente_Hab();
        clientHab.setId(result.getString(1));
        clientHab.setId_cliente(result.getString(2));
        clientHab.setId_hab(result.getString(3));
        clientHab.setConsumo(result.getString(4));
        clientHab.setFechaIng(result.getString(5));
        clientHab.setId_visita(result.getString(6));
        clientHab.setFechaSal(result.getString(7));
        clientHab.setEstado(result.getString(8));
        clientHab.setAbono(result.getString(9));
        clientHab.setHora_ing(result.getString(10));
        listCliente_Hab.add(clientHab);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar \"cliente_Hab\" " + e.getMessage());
    }
    return listCliente_Hab;
  }
  public List<cliente_Hab> listarCliente_HabPorIdHab(String idHab, String estado)
  {
    List<cliente_Hab> listCliente_Hab = new ArrayList();
    this.conectar = this.datos.Conectar(cliHab);
    String sql = "Select * from \"cliente_Hab\" where id_hab = '" + idHab + "' and estado LIKE '%" + estado + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        cliente_Hab clientHab = new cliente_Hab();
        clientHab.setId(result.getString(1));
        clientHab.setId_cliente(result.getString(2));
        clientHab.setId_hab(result.getString(3));
        clientHab.setConsumo(result.getString(4));
        clientHab.setFechaIng(result.getString(5));
        clientHab.setId_visita(result.getString(6));
        clientHab.setFechaSal(result.getString(7));
        clientHab.setEstado(result.getString(8));
        clientHab.setAbono(result.getString(9));
        clientHab.setHora_ing(result.getString(10));
        listCliente_Hab.add(clientHab);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar \"cliente_Hab\" -" + e.getMessage());
    }
    return listCliente_Hab;
  }
  
  public cliente_Hab buscarClienteHab(String id, List<cliente_Hab> listClienteHab)
  {
    cliente_Hab clientHab = new cliente_Hab();
    for (int i = 0; i < listClienteHab.size(); i++)
    {
      if (((cliente_Hab)listClienteHab.get(i)).getId().equals(id)) {
        clientHab = (cliente_Hab)listClienteHab.get(i);
      }
      if (((cliente_Hab)listClienteHab.get(i)).getId_cliente().equalsIgnoreCase(id)) {
        clientHab = (cliente_Hab)listClienteHab.get(i);
      }
      if (((cliente_Hab)listClienteHab.get(i)).getId_hab().equalsIgnoreCase(id)) {
        clientHab = (cliente_Hab)listClienteHab.get(i);
      }
      if (((cliente_Hab)listClienteHab.get(i)).getConsumo().equalsIgnoreCase(id)) {
        clientHab = (cliente_Hab)listClienteHab.get(i);
      }
    }
    return clientHab;
  }
  public cliente_Hab buscarClienteHabPorIdVis(String id, List<cliente_Hab> listClienteHab)
  {
    cliente_Hab clientHab = new cliente_Hab();
    for (int i = 0; i < listClienteHab.size(); i++)
    {
      if (((cliente_Hab)listClienteHab.get(i)).getId_visita().equals(id)) {
        clientHab = (cliente_Hab)listClienteHab.get(i);
      }
    }
    return clientHab;
  }
  public List<cliente_Hab> buscarClienteNumHab(String idHab, List<cliente_Hab> listClienteHab)
  {
      Date fecha = new Date();
      metodos m = new metodos();
      List<cliente_Hab> clientHab = new ArrayList<>();
      for (int i = 0; i < listClienteHab.size(); i++){
          Date fechaIng = m.StringADate(listClienteHab.get(i).getFechaIng(), "/");
          Date fechaSal = m.StringADate(listClienteHab.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(listClienteHab.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listClienteHab.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
      if (((cliente_Hab)listClienteHab.get(i)).getId_hab().equalsIgnoreCase(idHab)&&fecha.compareTo(fechaIng)>0&&fecha.compareTo(fechaSal)<0) {
        clientHab.add((cliente_Hab)listClienteHab.get(i));
      }
    }
    return clientHab;
  }
  public List<cliente_Hab> buscarClienteHabActiva(List<cliente_Hab> listClienteHab){
      Date fecha = new Date();
      metodos metodo = new metodos();
      int h=0,min=0;
    List<cliente_Hab> clientHab = new ArrayList<>();
    for (int i = 0; i < listClienteHab.size(); i++){
        Date fechaSal = metodo.StringADate(listClienteHab.get(i).getFechaSal(), "/");
        fechaSal.setHours(Integer.parseInt(listClienteHab.get(i).getEstado().split(":")[0]));
        fechaSal.setMinutes(Integer.parseInt(listClienteHab.get(i).getEstado().split(":")[1]));
        fechaSal.setSeconds(0);
      if (metodo.compararEntreFechas(fecha, metodo.StringADate(listClienteHab.get(i).getFechaIng(), "/"), fechaSal )) {
        clientHab.add((cliente_Hab)listClienteHab.get(i));
      }
    }
    return clientHab;
  }
  
  public int idClienteHab(){
      int result = 0;
      String sql = "";
      if (listarCliente_Hab("").size()==0) {
          result = 1;
      }else{
        this.conectar = this.datos.Conectar(cliHab);
        sql = "Select MAX(id::integer) from \"cliente_Hab\"";
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
          System.out.println("Error en buscar id \"cliente_Hab\" +" + e.getMessage());
        } 
      }
      return result;
  }
  
  public boolean ingresarClienteHab(cliente_Hab user)
  {
    this.conectar = this.datos.Conectar(cliHab);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into \"cliente_Hab\" values(?,?,?,?,?,?,?,?,?,?)");
      
      pst.setString(1, (idClienteHab())+"");
      pst.setString(2, user.getId_cliente());
      pst.setString(3, user.getId_hab());
      pst.setString(4, user.getConsumo());
      pst.setString(5, user.getFechaIng());
      pst.setString(6, user.getId_visita());
      pst.setString(7, user.getFechaSal());
      pst.setString(8, user.getEstado());
      pst.setString(9, user.getAbono());
      pst.setString(10, user.getHora_ing());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarClienteHab(cliente_Hab user, String idCliHab)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(cliHab);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"cliente_Hab\" set id = ?, id_cliente = ?, id_hab = ?, consumo=?, \"fechaIng\"=?, id_visita=?, \"fechaSal\"=?, estado=?,abono=?,hora_ing=? where id =?");
      pst.setString(1, user.getId());
      pst.setString(2, user.getId_cliente());
      pst.setString(3, user.getId_hab());
      pst.setString(4, user.getConsumo());
      pst.setString(5, user.getFechaIng());
      pst.setString(6, user.getId_visita());
      pst.setString(7, user.getFechaSal());
      pst.setString(8, user.getEstado());
      pst.setString(9, user.getAbono());
      pst.setString(10, user.getHora_ing());
      pst.setString(11, idCliHab);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarFechaIngClienteHab(String id, String fechaIng)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(cliHab);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"cliente_Hab\" set fechaIng=? where id =?");
      pst.setString(1, fechaIng);
      pst.setString(2, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarFechaSalClienteHab(String idHab, String fechaSal, String idVisita, List<visita> listV, List<valor_Hosp> listVH)
  {
    visitaController controlVisita = new visitaController();
      String tipo = controlVisita.buscarVisitasPorId(idVisita, listV).getTipo();
    valor_HospController controlValor = new valor_HospController();
    String valor = (Double.parseDouble(controlValor.buscarValorHosp(tipo, listVH, idHab).getCosto())*Double.parseDouble(controlVisita.cantDias(controlVisita.buscarVisitas(idHab, listV).getFecha_ing(), fechaSal)+""))+"";
    boolean op = false;
    this.conectar = this.datos.Conectar(cliHab);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"cliente_Hab\" set \"fechaSal\"=?, consumo=? where id_hab =? and id_visita=?");
      pst.setString(1, fechaSal);
      pst.setString(2, valor);
      pst.setString(3, idHab);
      pst.setString(4, idVisita);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarEstadoClienteHab(String idHab, String estado, String idVisita)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(cliHab);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"cliente_Hab\" set estado =? where id_hab =? and id_visita=?");
      pst.setString(1, estado);
      pst.setString(2, idHab);
      pst.setString(3, idVisita);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarHabClienteHab(String cedula, String id_hab, String fechaIng, String fechaSal)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(cliHab);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update \"cliente_Hab\" set id_hab=? where id_cliente =? and \"fechaIng\"=? and \"fechaSal\"=?");
      pst.setString(1, id_hab);
      pst.setString(2, cedula);
      pst.setString(3, fechaIng);
      pst.setString(4, fechaSal);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar id_hab cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean eliminarClienteHab(String id)
  {
      this.conectar = this.datos.Conectar(cliHab);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from \"cliente_Hab\" where id=?");
      pst.setString(1, id);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean eliminarClienteHabIdVis(String id_Visita)
  {
      this.conectar = this.datos.Conectar(cliHab);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from \"cliente_Hab\" where id_visita=?");
      pst.setString(1, id_Visita);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar cliente_Hab " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaClienteHab(List<cliente_Hab> Lista)
  {
    String[] titulos = { "Id", "Cliente", "Habitación"};
    String[] registro = new String[titulos.length];
    DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    for (cliente_Hab cliente_Hab : Lista)
    {
      registro[0] = cliente_Hab.getId();
      registro[1] = cliente_Hab.getId_cliente();
      registro[2] = cliente_Hab.getId_hab();
      modelo.addRow(registro);
    }
    return modelo;
  }
  public DefaultTableModel MostrarTablaClienteHab2(List<cliente_Hab> Lista, List<cliente> clis, List<visita> visitas, List<consumo> listConsumo){
      clienteController controlCli = new clienteController();
      visitaController controlVis = new visitaController();
      consumoController controlCons = new consumoController();
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      Date hora = new Date();
      int h=0,min=0;
      String[] titulos = {"Cédula", "Nombres","Tipo","Check-in","Check-out", "Días", "Costo $","$ Extra","$ Abono", "#Visita"};
      String[] registro = new String[titulos.length];
      DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
          public boolean isCellEditable(int row, int column) {
              return false;
          }
      };
      for (cliente_Hab cliente_Hab : Lista){
          Date fechaSal = m.StringADate(cliente_Hab.getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(cliente_Hab.getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(cliente_Hab.getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          if (!cliente_Hab.getFechaIng().equals(cliente_Hab.getFechaSal())&&m.compararEntreFechas(fecha, m.StringADate(cliente_Hab.getFechaIng(), "/"), fechaSal)) {
              registro[0] = cliente_Hab.getId_cliente();
              registro[1] = controlCli.buscarCliente(cliente_Hab.getId_cliente(), clis).getNombres();
              registro[2] = controlVis.buscarVisitasPorId(cliente_Hab.getId_visita(), visitas).getTipo();
              registro[3] = cliente_Hab.getFechaIng()+" - "+m.de24A12Horas(cliente_Hab.getHora_ing()).toLowerCase();;
              registro[4] = cliente_Hab.getFechaSal()+" - "+m.de24A12Horas(cliente_Hab.getEstado()).toLowerCase();
              registro[5] = controlVis.buscarVisitasPorId(cliente_Hab.getId_visita(), visitas).getCant_dias();
              registro[6] = m.redondearCerDer(Double.parseDouble(cliente_Hab.getConsumo()),2);
              registro[7] = m.redondearCerDer(Double.parseDouble(controlCons.buscarConsumoTotalPorIdVis(cliente_Hab.getId_visita(), listConsumo)),2);
              registro[8] = m.redondearCerDer(Double.parseDouble(cliente_Hab.getAbono()), 2);
              registro[9] = cliente_Hab.getId_visita();
              modelo.addRow(registro);
          }
      }
      return modelo;
  }
  
  public boolean cliHabExistente(String id, List<cliente_Hab> listClienteHab)
  {
    boolean result = false;
    for (int i = 0; i < listClienteHab.size(); i++) {
      if (((cliente_Hab)listClienteHab.get(i)).getId().equals(id)) {
        result = true;
      }
    }
    return result;
  }
  
  public String obtenerCostoTotalHab(String numHab, List<cliente_Hab> listCH, List<consumo> listConsumo, Date fecha){
      String result = "";
      double cost = 0;
      //Date fecha = new Date();
      metodos m = new metodos();
      //fecha = m.StringADate(m.DateAString(fecha), "/");
      consumoController ctrlConsumo = new consumoController();
      int h=0,min=0;
      for (int i = 0; i < listCH.size(); i++) {
          Date fechaSal = m.StringADate(listCH.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(listCH.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listCH.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          Date fechaIng = m.StringADate(listCH.get(i).getFechaIng(), "/");
          if (listCH.get(i).getId_hab().equals(numHab)) {
              if (fecha.compareTo(fechaIng)>=0&&fecha.compareTo(fechaSal)<0) {
                  cost = cost + Double.parseDouble(listCH.get(i).getConsumo())+Double.parseDouble(ctrlConsumo.obtenerCostoPorIdVisita(listCH.get(i).getId_visita(), listConsumo))-Double.parseDouble(listCH.get(i).getAbono());
              }
          }
          /*
          if(!list.get(i).getFechaIng().equals(list.get(i).getFechaSal())&&m.compararEntreFechas(fecha, m.StringADate(list.get(i).getFechaIng(), "/"), fechaSal)&&list.get(i).getId_hab().equals(numHab)){
              cost = cost+Double.parseDouble(list.get(i).getConsumo()+"");
          }
            */
      }
      //cost = cost + Double.parseDouble(ctrlConsumo.obtenerCostoTotalHab(numHab, list, listConsumo));
      result = m.redondearCerDer(cost, 2)+"";
      return result;
  }
  
  public String obtenerHabCliente(String cedula, List<cliente_Hab> list){
      String result = "";
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=0,min=0;
      for (int i = 0; i < list.size(); i++) {
          Date fechaSal = m.StringADate(list.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(list.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(list.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          if(m.compararEntreFechas(fecha, m.StringADate(list.get(i).getFechaIng(), "/"), fechaSal)&&list.get(i).getId_cliente().equals(cedula)){
              result = list.get(i).getId_hab();
          }
      }
      return result;
  }
  public int cantHuespHab(String numHab, List<cliente_Hab> list, Date fecha){
      int result = 0;
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=0,min=0;
      for (int i = 0; i < list.size(); i++) {
          Date fechaSal = m.StringADate(list.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(list.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(list.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          if(m.compararEntreFechas(fecha, m.StringADate(list.get(i).getFechaIng(), "/"), fechaSal)&&list.get(i).getId_hab().equals(numHab)){
              result = result + 1;
          }
      }
      return result;
  }
  public String cantHuespHab2(String numHab, List<cliente_Hab> listCH, Date fecha, List<visita> listV, List<cliente> listCli, List<consumo> listConsumo){
      int resultAdult = 0;
      int resultNino = 0;
      String adult = "";
      String nino="";
      metodos m = new metodos();
      //fecha = m.StringADate(m.DateAString(fecha), "/");
      String abono = "0.00";
      double abon = 0.00;
      String huespedes ="";
      String fechaIng ="";
      String fechaSalida ="";
      String horaIng="0:00";
      String horaSal="0:00";
      int cont = 0;
      clienteController ctrlCli = new clienteController();
      for (int i = 0; i < listCH.size(); i++) {
          Date fechaIni = m.StringADate(listCH.get(i).getFechaIng(), "/");
          Date fechaSal = m.StringADate(listCH.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(listCH.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listCH.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          if(fecha.compareTo(fechaIni)>=0&&fecha.compareTo(fechaSal)<0&&listCH.get(i).getId_hab().equals(numHab)){
          //if(m.compararEntreFechas(fecha, m.StringADate(listCH.get(i).getFechaIng(), "/"), fechaSal)&&listCH.get(i).getId_hab().equals(numHab)){
              for (int j = 0; j < listV.size(); j++) {
                  if(listV.get(j).getId().equals(listCH.get(i).getId_visita())){
                      if(listV.get(j).getTipo().equals("Adulto")){
                          resultAdult=resultAdult+1;
                      }else{
                          resultNino=resultNino+1;
                      }
                      abon = abon+ Double.parseDouble(listCH.get(i).getAbono());
                      //System.out.println(fecha+" - "+listV.get(j).getId_cliente()+controlVis.buscarVisitasPorIdCliente(listV.get(j).getId_cliente(), listV, fecha, Integer.parseInt(list.get(i).getEstado().split(":")[0]),Integer.parseInt(list.get(i).getEstado().split(":")[1])).getNombre());
                      huespedes = huespedes + ctrlCli.buscarCliente(listCH.get(i).getId_cliente(), listCli).getNombres()+" - ";
                      
                          fechaIng = listCH.get(i).getFechaIng();
                          horaIng = listCH.get(i).getHora_ing();
                          
                      
                          fechaSalida = listCH.get(i).getFechaSal();
                          horaSal = listCH.get(i).getEstado();
                      
                  }
              }
          }
                  cont=0;
      }
      String cadenaresultAdult = resultAdult+" ";
      String cadenaresultNino = " y "+resultNino+" ";
      if (resultAdult==1) {
          adult = "Adulto";
      }
      if (resultAdult>1) {
          adult = "Adultos";
      }
      if (resultAdult==0) {
          cadenaresultAdult="";
          adult = "";
      }
      if (resultNino==1) {
          nino = "Niño";
      }
      if (resultNino>1) {
          nino = "Niños";
      }
      if (resultNino==0) {
          cadenaresultNino="";
          nino = "";
      }
      //res = r.getNombres()+" - "+"Check-in: "+r.getFecha_ing()+"("+de24A12Horas(r.getHora_ingreso())+") - Check-out: "+r.getFecha_sal()+"("+de24A12Horas(r.getHora_salida())+") - "+r.getCant_us()+" - "+r.getCant_dias()+" día(s) - Total a pagar: $"+r.getTotal_pagar()+" - Abono: $"+r.getEstado();
                    
      return huespedes+cadenaresultAdult+adult+cadenaresultNino+nino+" - "+"Check-in: "+fechaIng+"("+m.de24A12Horas(horaIng)+") - Check-out: "+fechaSalida+"("+m.de24A12Horas(horaSal)+") - Total a pagar: $"+obtenerCostoTotalHab(numHab, listCH, listConsumo,fecha)+ " - Abono: $"+m.redondearCerDer(abon, 2);
  }
  public String cantHuespHab3(String numHab, List<cliente_Hab> listCliHab, Date fecha, List<visita> listV, List<cliente> listCli, List<consumo> listConsumo, List<valor_Hosp> listValorHosp){
      int resultAdult = 0;
      int resultNino = 0;
      String adult = "";
      String nino="";
      metodos m = new metodos();
      //fecha = m.StringADate(m.DateAString(fecha), "/");
      String huespedesAdult ="";
      String huespedesNiños ="";
      clienteController ctrlCli = new clienteController();
      valor_HospController valHos = new valor_HospController();
      consumoController ctrlCons = new consumoController();
      double costAdul = Double.parseDouble(valHos.buscarValorHosp("Adulto", listValorHosp, numHab).getCosto());
      double costNiño = Double.parseDouble(valHos.buscarValorHosp("Niño", listValorHosp, numHab).getCosto());
      double costHosp = 0;
      double costCons = 0;
      for (int i = 0; i < listCliHab.size(); i++) {
          Date fechaSal = m.StringADate(listCliHab.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(listCliHab.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listCliHab.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          int cantDias = m.cantDias(listCliHab.get(i).getFechaIng(), m.DateAString(fecha));
          double costoDias = 0;
          if(m.compararEntreFechas(fecha, m.StringADate(listCliHab.get(i).getFechaIng(), "/"), fechaSal)&&listCliHab.get(i).getId_hab().equals(numHab)){
              for (int j = 0; j < listV.size(); j++) {
                  if(listV.get(j).getId().equals(listCliHab.get(i).getId_visita())){
                      if(listV.get(j).getTipo().equals("Adulto")){
                          resultAdult=resultAdult+1;
                          huespedesAdult = huespedesAdult + m.personalizarMsj(ctrlCli.buscarCliente(listCliHab.get(i).getId_cliente(), listCli).getNombres()+" - "+cantDias+" dia(s) - $"+m.redondearCerDer(cantDias*costAdul, 2), m.azul, "times new roman", m.normal, false, false, false) +"\n"+ctrlCons.obtenerConsumoPorIdVisita(listCliHab.get(i).getId_visita(), listConsumo)+m.personalizarMsj("----------------------------", "", "times new roman", m.titulo1, true, false, false)+"\n";
                          costHosp = costHosp + (cantDias*costAdul);
                          costCons = costCons + Double.parseDouble(ctrlCons.buscarConsumoTotalPorIdVis(listCliHab.get(i).getId_visita(), listConsumo));
                      }else{
                          resultNino=resultNino+1;
                          huespedesNiños = huespedesNiños + m.personalizarMsj(ctrlCli.buscarCliente(listCliHab.get(i).getId_cliente(), listCli).getNombres()+" - "+cantDias+" dia(s) - $"+m.redondearCerDer(cantDias*costNiño, 2), m.azul, "times new roman", m.normal, false, false, false)+"\n"+ctrlCons.obtenerConsumoPorIdVisita(listCliHab.get(i).getId_visita(), listConsumo)+m.personalizarMsj("----------------------------", "", "times new roman", m.titulo1, true, false, false)+"\n";
                          costHosp = costHosp + (cantDias*costNiño);
                          costCons = costCons + Double.parseDouble(ctrlCons.buscarConsumoTotalPorIdVis(listCliHab.get(i).getId_visita(), listConsumo));
                      }
                      
                      //System.out.println(fecha+" - "+listV.get(j).getId_cliente()+controlVis.buscarVisitasPorIdCliente(listV.get(j).getId_cliente(), listV, fecha, Integer.parseInt(list.get(i).getEstado().split(":")[0]),Integer.parseInt(list.get(i).getEstado().split(":")[1])).getNombre());
                      
                  }
              }
          }
      }
      String cadenaresultAdult = resultAdult+" ";
      String cadenaresultNino = " "+resultNino+" ";
      if (resultAdult==1) {
          adult = "Adulto";
      }
      if (resultAdult>1) {
          adult = "Adultos";
      }
      if (resultAdult==0) {
          cadenaresultAdult="";
          adult = "";
      }
      if (resultNino==1) {
          nino = "Niño";
      }
      if (resultNino>1) {
          nino = "Niños";
      }
      if (resultNino==0) {
          cadenaresultNino="";
          nino = "";
      }
      return m.personalizarMsj(cadenaresultAdult+adult, "black", "times new roman", m.titulo2, true, false, false)+"\n"+
              huespedesAdult+m.personalizarMsj(cadenaresultNino+nino, "black", "times new roman", m.titulo2, true, false, false)+"\n"+
              huespedesNiños+m.personalizarMsj("Total a pagar: $"+m.redondearCerDer(costHosp+costCons,2), m.verde, "times new roman", m.superTitulo, true, false, false);
  }
  public int cantTotalHuesp(List<cliente_Hab> list){
      int result = 0;
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=0,min=0;
      for (int i = 0; i < list.size(); i++) {
          Date fechaSal = m.StringADate(list.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(list.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(list.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          if(!list.get(i).getFechaIng().equals(list.get(i).getFechaSal())&&m.compararEntreFechas(fecha, m.StringADate(list.get(i).getFechaIng(), "/"), fechaSal)){
              result = result + 1;
          }
      }
      return result;
  }
  
  public boolean comprobarHuespHab(String numHab, List<cliente_Hab> list){
      boolean result = false;
      Date fecha = new Date();
      metodos m = new metodos();
      fecha = m.StringADate(m.DateAString(fecha), "/");
      int h=0,min=0;
      for (int i = 0; i < list.size(); i++) {
          Date fechaSal = m.StringADate(list.get(i).getFechaSal(), "/");
          fechaSal.setHours(Integer.parseInt(list.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(list.get(i).getEstado().split(":")[1]));
          fechaSal.setSeconds(0);
          if (list.get(i).getId_hab().equals(numHab)&&m.compararEntreFechas(fecha, m.StringADate(list.get(i).getFechaIng(), "/"), fechaSal)) {
              result = true;
          }
      }
      return result;
  }
  
}
