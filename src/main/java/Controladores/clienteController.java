/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Coneccion.Datos;
import Entidades.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Luis Fernando
 */
public class clienteController {
  Datos datos = new Datos();
  public Connection conectar;
  String clienteCtrl="cliente";
  public List<cliente> listarClientes(String parametro)
  {
    List<cliente> listCliente = new ArrayList();
    this.conectar = this.datos.Conectar(clienteCtrl);
    String sql = "Select * from cliente where UPPER(nombres) LIKE '%" + parametro + "%' OR UPPER(direccion) LIKE '%" + parametro + "%' OR UPPER(telefono) LIKE '%" + parametro + "%' OR UPPER(cedula) LIKE '%" + parametro + "%' OR UPPER(estado) LIKE '%" + parametro + "%' OR UPPER(observaciones) LIKE '%" + parametro + "%'";
    try
    {
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        cliente client = new cliente();
        client.setCedula(result.getString(1));
        client.setNombres(result.getString(2));
        client.setDireccion(result.getString(3));
        client.setTelefono(result.getString(4));
        client.setEstado(result.getString(5));
        client.setObservaciones(result.getString(6));
        client.setCorreo(result.getString(7));
        if (!client.getNombres().toUpperCase().equals("CONSUMIDOR FINAL")) {
          listCliente.add(client);
        }
      }
      result.close();
      st.close();
      this.datos.Desconectar(this.conectar);
    }
    catch (Exception e)
    {
      System.out.println("Error en listar cliente " + e.getMessage());
    }
    return listCliente;
  }
  
  public cliente buscarCliente(String cedula, List<cliente> listCliente)
  {
    cliente client = new cliente();
    for (int i = 0; i < listCliente.size(); i++)
    {
      if (((cliente)listCliente.get(i)).getCedula().equals(cedula)) {
        client = (cliente)listCliente.get(i);
      }
      if (((cliente)listCliente.get(i)).getNombres().equalsIgnoreCase(cedula)) {
        client = (cliente)listCliente.get(i);
      }
    }
    return client;
  }
  
  public boolean ingresarCliente(cliente user)
  {
    this.conectar = this.datos.Conectar(clienteCtrl);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into cliente values(?,?,?,?,?,?,?)");
      pst.setString(1, user.getCedula());
      pst.setString(2, user.getNombres());
      if (user.getDireccion().equals("")) {
        pst.setString(3, "N/A");
      } else {
        pst.setString(3, user.getDireccion());
      }
      if (user.getTelefono().equals("")) {
        pst.setString(4, "N/A");
      } else {
        pst.setString(4, user.getTelefono());
      }
      if (user.getEstado().equals("")) {
        pst.setString(5, "Off");
      } else {
        pst.setString(5, user.getEstado());
      }
      pst.setString(6, user.getObservaciones());
      pst.setString(7, user.getCorreo());
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar cliente " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarCliente(cliente user, String cedula)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(clienteCtrl);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update cliente set nombres = ?, direccion = ?, telefono = ? , cedula=?, estado=?, observaciones=?, correo=? where cedula =?");
      pst.setString(1, user.getNombres());
      if (user.getDireccion().equals("")) {
        pst.setString(2, "N/A");
      } else {
        pst.setString(2, user.getDireccion());
      }
      if (user.getTelefono().equals("")) {
        pst.setString(3, "N/A");
      } else {
        pst.setString(3, user.getTelefono());
      }
      pst.setString(4, user.getCedula());
      pst.setString(5, user.getEstado());
      pst.setString(6, user.getObservaciones());
      pst.setString(7, user.getCorreo());
      pst.setString(8, cedula);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar cliente " + e.getMessage());
    }
    System.out.println("act cli");
    this.datos.Desconectar(this.conectar);
    return op;
  }
  public boolean actualizarEstadoCliente(String estado, String cedula)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(clienteCtrl);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update cliente set estado=? where cedula =?");
      pst.setString(1, estado);
      pst.setString(2, cedula);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar estado del cliente " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean eliminarCliente(String cedula)
  {
      this.conectar = this.datos.Conectar(clienteCtrl);
    boolean op = false;
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from cliente where cedula=?");
      pst.setString(1, cedula);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar cliente " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaCliente(List<cliente> Lista)
  {
    String[] titulos = { "Cédula", "Nombres", "Dirección", "Teléfono", "Estado", "Correo", "Observaciones" };
    String[] registro = new String[titulos.length];
    DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    for (cliente cliente : Lista)
    {
      registro[0] = cliente.getCedula();
      registro[1] = cliente.getNombres();
      registro[2] = cliente.getDireccion();
      registro[3] = cliente.getTelefono();
      registro[4] = cliente.getEstado();
      registro[5] = cliente.getCorreo();
      registro[6] = cliente.getObservaciones();
      modelo.addRow(registro);
    }
    return modelo;
  }
  
  public boolean cliExistente(String cedula, List<cliente> listCliente)
  {
    boolean result = false;
    for (int i = 0; i < listCliente.size(); i++) {
      if (((cliente)listCliente.get(i)).getCedula().equals(cedula)) {
        result = true;
      }
    }
    return result;
  }
  
  public int cliOcHab(String cedula, List<cliente> listCliente)
  {
    int result = 0;
    for (int i = 0; i < listCliente.size(); i++) {
        if (listCliente.get(i).getCedula().equals(cedula)) {
            if (!listCliente.get(i).getEstado().equals("Off")) {
                result = Integer.parseInt(listCliente.get(i).getEstado().split(" ")[1]);
            }
        }
    }
    return result;
  }
}
/*
                ced = metodo.mensajeDialogoEntradaCombo("Ingrese el número de cédula del cliente", "Información", "pregunta", null, "");
                nombresCliente = controlCliente.buscarCliente(ced, clientes).getNombres();
                direccionCliente = controlCliente.buscarCliente(ced, clientes).getDireccion();
                if (controlCliente.cliExistente(ced, clientes)) {
                    if (metodo.mensajeConfirmacion("¿Desea ocupar la Habitación N° "+numHab+"?\nDatos del cliente:\nNombres: "+nombresCliente+"\nCédula: "+ced+"\nDirección: "+direccionCliente, "Aviso", "pregunta")) {
                        controlHab.actualizarEstadoHabitacion(numHab+"", "Ocupado");
                        controlHab.actualizarObservHabitacion(numHab+"", nombresCliente+" - "+direccionCliente);
                        habitaciones = controlHab.listarHabitacion("");
                        visita vis = new visita("", ced, nombresCliente, direccionCliente, numHab+"", metodo.fechaSistema(), "N/A", "N/A", "N/A", "En proceso");
                        controlVisita.ingresarVisitas(vis);
                        cliente_Hab cliHab = new cliente_Hab("", ced, numHab+"");
                        controlCliHab.ingresarClienteHab(cliHab);
                        verHab();
                        metodo.mensajeDialogo("Habitación N° "+numHab+" ocupada con éxito", "Información", "informacion");
                    }
                }else{
                    metodo.mensajeDialogo("Cliente no existente en la base de datos", "Erorr", "error");
                }
                */