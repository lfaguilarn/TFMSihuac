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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis Fernando
 */
public class visitaController {
    Datos datos = new Datos();
  public Connection conectar;
  String vis = "visita";
  public List<visita> listarVisitas(String parametro)
  {
    List<visita> listVisitas = new ArrayList();
    this.conectar = this.datos.Conectar(vis);
    String sql = "Select * from visita where UPPER(id) LIKE '%" + parametro + "%' OR UPPER(id_cliente) LIKE '%" + parametro + "%' OR UPPER(nombre) LIKE '%" + parametro + "%' OR UPPER(direccion) LIKE '%" + parametro + "%' OR UPPER(id_hab) LIKE '%" + parametro + "%' OR UPPER(fecha_ing) LIKE '%" + parametro + "%' OR UPPER(fecha_sal) LIKE '%" + parametro + "%' OR UPPER(consumo) LIKE '%" + parametro + "%' OR UPPER(observaciones) LIKE '%" + parametro + "%' order by id ASC";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        visita reserv = new visita();
        reserv.setId(result.getString(1));
        reserv.setId_cliente(result.getString(2));
        reserv.setNombre(result.getString(3));
        reserv.setDireccion(result.getString(4));
        reserv.setId_hab(result.getString(5));
        reserv.setFecha_ing(result.getString(6));
        reserv.setFecha_sal(result.getString(7));
        reserv.setCant_dias(result.getString(8));
        reserv.setConsumo(result.getString(9));
        reserv.setObservaciones(result.getString(10));
        reserv.setTipo(result.getString(11));
        listVisitas.add(reserv);
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar visita " + e.getMessage());
    }
    return listVisitas;
  }
  
  public visita buscarVisitas(String id, List<visita> listVisitas)
  {
    visita reserv = new visita();
    for (int i = 0; i < listVisitas.size(); i++)
    {
      if (((visita)listVisitas.get(i)).getId().equals(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getId_cliente().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getId_hab().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getFecha_ing().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getFecha_sal().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getConsumo().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getCant_dias().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getObservaciones().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
      if (((visita)listVisitas.get(i)).getTipo().equalsIgnoreCase(id)) {
        reserv = (visita)listVisitas.get(i);
      }
    }
    return reserv;
  }
  public visita buscarVisitasPorId(String idVis, List<visita> listVisitas)
  {
    visita reserv = new visita();
    for (int i = 0; i < listVisitas.size(); i++)
    {
      if (((visita)listVisitas.get(i)).getId().equals(idVis)) {
        reserv = (visita)listVisitas.get(i);
      }
    }
    return reserv;
  }
  public visita buscarVisitasPorIdClient(String idCliente, List<visita> listVisitas, Date Fecha, int h, int min)
  {
    visita reserv = new visita();
    metodos m= new metodos();
    for (int i = 0; i < listVisitas.size(); i++){
      if (((visita)listVisitas.get(i)).getId_cliente().equals(idCliente)&&m.compararEntreFechas(Fecha, m.StringADate(listVisitas.get(i).getFecha_ing(), "/"), m.StringADate(listVisitas.get(i).getFecha_sal(), "/"))) {
        reserv = (visita)listVisitas.get(i);
      }
    }
    return reserv;
  }
  public List<visita> buscarVisitasPorIdCliente(String idCliente, List<visita> listVisitas)
  {
    List<visita> vis = new ArrayList();
    for (int i = 0; i < listVisitas.size(); i++){
      if (((visita)listVisitas.get(i)).getId_cliente().equals(idCliente)) {
        vis.add((visita)listVisitas.get(i));
      }
    }
    return vis;
  }
  public List<visita> buscarVisitasPorIdHab(String idHab, List<visita> listVisitas)
  {
    List<visita> vis = new ArrayList();
    for (int i = 0; i < listVisitas.size(); i++){
      if (((visita)listVisitas.get(i)).getId_hab().equals(idHab)) {
        vis.add((visita)listVisitas.get(i));
      }
    }
    return vis;
  }
  public List<visita> buscarVisitasPorFecha(Date fecha, List<visita> listVisitas)
  {
    List<visita> vis = new ArrayList();
    metodos m = new metodos();
    for (int i = 0; i < listVisitas.size(); i++){
      if (fecha.compareTo(m.StringADate(listVisitas.get(i).getFecha_ing(), "/"))>=0&&fecha.compareTo(m.StringADate(listVisitas.get(i).getFecha_sal(), "/"))<=0) {
        vis.add((visita)listVisitas.get(i));
      }
    }
    return vis;
  }
  
  public int idVisitas(){
      int result = 0;
      String sql = "";
      if (listarVisitas("").size()==0) {
          result = 1;
      }else{
        this.conectar = this.datos.Conectar(vis);
        sql = "Select MAX(id::integer) from visita";
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
          System.out.println("Error en listar visitas " + e.getMessage());
        } 
      }
      return result;
  }
  
  public boolean ingresarVisitas(visita user)
  {
    this.conectar = this.datos.Conectar(vis);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into visita values(?,?,?,?,?,?,?,?,?,?,?)");
      pst.setString(1, (idVisitas())+"");
      pst.setString(2, user.getId_cliente());
      pst.setString(3, user.getNombre());
      pst.setString(4, user.getDireccion());
      pst.setString(5, user.getId_hab());
      pst.setString(6, user.getFecha_ing());
      pst.setString(7, user.getFecha_sal());
      pst.setString(8, user.getCant_dias());
      pst.setString(9, user.getConsumo());
      pst.setString(10, user.getObservaciones());
      pst.setString(11, user.getTipo());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar visita " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarVisitas(visita user, String id)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(vis);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update visita set id = ?, id_cliente = ?, nombre=?,direccion=?, id_hab = ?, fecha_ing =?, "
              + "fecha_sal =?, cant_dias=?, consumo=?, observaciones=?, tipo=? where id =?");
      pst.setString(1, user.getId());
      pst.setString(2, user.getId_cliente());
      pst.setString(3, user.getNombre());
      pst.setString(4, user.getDireccion());
      pst.setString(5, user.getId_hab());
      pst.setString(6, user.getFecha_ing());
      pst.setString(7, user.getFecha_sal());
      pst.setString(8, user.getCant_dias());
      pst.setString(9, user.getConsumo());
      pst.setString(10, user.getObservaciones());
      pst.setString(11, user.getTipo());
      pst.setString(12, id);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar visita " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarHabVisitas(String id_hab, String idVisitas)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(vis);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update visita set id_hab = ? where id =?");
      pst.setString(1, id_hab);
      pst.setString(2, idVisitas);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar id_hab en visita " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  /*
  public int cantDias(String fechaIng, String fechaSal) throws ParseException{
      int result=0;
      String fechIni ="";
      String fechFin ="";
      String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
      for (int i = 0; i < meses.length; i++) {
          if (fechaSal.split("/")[1].equals(meses[i])) {
              fechFin = fechaSal.split("/")[0]+"/"+meses[i]+"/"+fechaSal.split("/")[2];
          }
      }
      for (int i = 0; i < meses.length; i++) {
          if (fechaIng.split("/")[1].equals(meses[i])) {
              fechIni = fechaIng.split("/")[0]+"/"+meses[i]+"/"+fechaIng.split("/")[2];
          }
      }
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
      Date firstDate = sdf.parse(fechIni);
      Date secondDate = sdf.parse(fechFin);

        long diff = secondDate.getTime() - firstDate.getTime();

        TimeUnit time = TimeUnit.DAYS; 
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println("The difference in days is : "+diffrence);
        result = Integer.parseInt(diffrence+"");
      return result;
  }
  */
  
  public int cantDias(String fechaIni, String fechaFin){
      int result = 0;
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
          result++;
          fechaIng.setTime(fechaIng.getTime()+(1000*60*60*24));
      }
      
      return result;
  }
  
  public String consumo(JComboBox ch, String numhab){
      valor_HospController vH = new valor_HospController();
      valor_Hosp vAdulto = vH.buscarValorHosp(ch.getSelectedItem().toString(), vH.listarValor_Hosp(""), numhab);
      String result = vAdulto.getCosto();
      return result;
  }
  
  public boolean actualizarFechaSal(String fechaSal, String idHab, String idVisita, List<visita> listV, List<valor_Hosp> listVH)
  {
    String tipo = buscarVisitasPorId(idVisita, listV).getTipo();
    valor_HospController controlValor = new valor_HospController();
    String valor = (Double.parseDouble(controlValor.buscarValorHosp(tipo, listVH, idHab).getCosto())*Double.parseDouble(cantDias(buscarVisitas(idHab, listV).getFecha_ing(), fechaSal)+""))+"";
    boolean op = false;
    this.conectar = this.datos.Conectar(vis);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update visita set fecha_sal =?, cant_dias=?, consumo=? where id_hab =? and id=?");
      pst.setString(1, fechaSal);
      pst.setString(2, cantDias(buscarVisitasPorId(idVisita, listV).getFecha_ing(), fechaSal)+"");
      pst.setString(3, valor);
      pst.setString(4, idHab);
      pst.setString(5, idVisita);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar visita " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean eliminarVisitas(String idVisita)
  {
      this.conectar = this.datos.Conectar(vis);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from visita where id=?");
      pst.setString(1, idVisita);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar visita " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaVisitas(List<visita> Lista, List<consumo> listConsumo, List<cliente_Hab> listCH)
  {
      metodos m = new metodos();
      consumoController cons = new consumoController();
      cliente_HabController cliHab = new cliente_HabController();
    String[] titulos = { "Id", "Cliente", "Tipo","Dirección","Hab.", "Check-in", "Check-out","Día(s)", "Consumo","Observaciones" };
    String[] registro = new String[titulos.length];
    DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
          public boolean isCellEditable(int row, int column) {
              return false;
          }
      };
    for (visita visita : Lista)
    {
      registro[0] = visita.getId();
      registro[1] = visita.getNombre();
      registro[2] = visita.getTipo();
      registro[3] = visita.getDireccion();
      registro[4] = visita.getId_hab();
      registro[5] = visita.getFecha_ing()+" - "+m.de24A12Horas(cliHab.buscarClienteHabPorIdVis(visita.getId(), listCH).getHora_ing());;
      registro[6] = visita.getFecha_sal()+" - "+m.de24A12Horas(cliHab.buscarClienteHabPorIdVis(visita.getId(), listCH).getEstado());
      registro[7] = m.deDoubleAInt(visita.getCant_dias(), true)+"";
      registro[8] = "$"+m.redondearCerDer((Double.parseDouble(visita.getConsumo())+Double.parseDouble(cons.buscarConsumoTotalPorIdVis(visita.getId(), listConsumo))), 2);
      registro[9] = visita.getObservaciones();
      modelo.addRow(registro);
    }
    return modelo;
  }
  
  public boolean visitaExistente(String id, List<visita> listVisitas)
  {
    boolean result = false;
    for (int i = 0; i < listVisitas.size(); i++) {
      if (((visita)listVisitas.get(i)).getId().equals(id)) {
        result = true;
      }
    }
    return result;
  }
}
