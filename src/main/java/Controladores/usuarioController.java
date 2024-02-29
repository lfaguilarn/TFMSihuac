/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Coneccion.Datos;
import Entidades.usuario;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Luis Fernando
 */
public class usuarioController {
  Datos datos = new Datos();
  public Connection conectar;
  String us = "usuario";
  public List<usuario> listarUsuarios(String parametro)
  {
    List<usuario> listUser = new ArrayList();
    this.conectar = this.datos.Conectar(us);
    try
    {
      String sql = "Select * from usuario where UPPER(nickname) LIKE '%" + parametro.toUpperCase() + "%' OR UPPER(nombres) LIKE '%" + parametro.toUpperCase() + "%'";
      Statement st = this.conectar.createStatement();
      ResultSet result = st.executeQuery(sql);
      while (result.next())
      {
        usuario user = new usuario();
        user.setNickname(result.getString(1));
        user.setNombres(result.getString(2));
        user.setCargo(result.getString(3));
        user.setClave(result.getString(4));
        user.setCliente(result.getString(5));
        user.setHab(result.getString(6));
        user.setValorhosp(result.getString(7));
        user.setReservacion(result.getString(8));
        user.setVisita(result.getString(9));
        user.setEstado(result.getString(10));
        user.setUsuario(result.getString(11));
        
        listUser.add(user);
      }
      result.close();
      st.close();
    }
    catch (Exception e)
    {
      System.out.println("Error en listar usuario -" + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return listUser;
  }
  /*
  public ImageIcon consultarFoto(String nickName, JLabel lbl)
  {
    this.conectar = this.datos.Conectar("foto usuario");
    String sql = "select foto from usuario where nickname= '" + nickName + "'";
    


    ImageIcon newicon = null;
    try
    {
      ResultSet rs = this.datos.ejecutarSQLSelect(sql);
      while (rs.next())
      {
        InputStream is = rs.getBinaryStream(1);
        BufferedImage bi = ImageIO.read(is);
        ImageIcon foto = new ImageIcon(bi);
        Image img = foto.getImage();
        Image newimg = img.getScaledInstance(lbl.getBounds().width, lbl.getBounds().height, 4);
        newicon = new ImageIcon(newimg);
      }
    }
    catch (Exception ex)
    {
      System.out.println("Error en consultar img " + ex.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return newicon;
  }
  */
  public usuario usuarioActivo(List<usuario> listUser)
  {
    usuario user = new usuario();
    for (int i = 0; i < listUser.size(); i++) {
      if (((usuario)listUser.get(i)).getEstado().equals("Activo")) {
        user = (usuario)listUser.get(i);
      }
    }
    return user;
  }
  /*
  public usuario usuarioActivoFactRec()
  {
    usuario user = new usuario();
    List<usuario> listUser = listarUsuarios("");
    for (int i = 0; i < listUser.size(); i++) {
      if (((usuario)listUser.get(i)).getEstado().equals("Activo")) {
        user = (usuario)listUser.get(i);
      }
    }
    return user;
  }
  */
  public List<usuario> filtrarUsuarios(String buscar, List<usuario> listUser)
  {
    List<usuario> listUserFiltrada = new ArrayList();
    for (int i = 0; i < listUser.size(); i++)
    {
      if (((usuario)listUser.get(i)).getNickname().equalsIgnoreCase(buscar)) {
        listUserFiltrada.add(listUser.get(i));
      }
      if (((usuario)listUser.get(i)).getNombres().equalsIgnoreCase(buscar)) {
        listUserFiltrada.add(listUser.get(i));
      }
      if (((usuario)listUser.get(i)).getClave().equalsIgnoreCase(buscar)) {
        listUserFiltrada.add(listUser.get(i));
      }
      if (buscar.equals("")) {
        listUserFiltrada.add(listUser.get(i));
      }
    }
    return listUserFiltrada;
  }
  public usuario buscarUsuarioPorNickName(String nickName, List<usuario> listUser)
  {
    usuario us = new usuario();
    for (int i = 0; i < listUser.size(); i++)
    {
      if (((usuario)listUser.get(i)).getNickname().equalsIgnoreCase(nickName)) {
        us=listUser.get(i);
      }
    }
    return us;
  }
  
  public String claveUsuario(String nickName, List<usuario> listUser)
  {
    String clave = "";
    for (int i = 0; i < listUser.size(); i++) {
      if (((usuario)listUser.get(i)).getNickname().equals(nickName)) {
        clave = ((usuario)listUser.get(i)).getClave();
      }
    }
    return clave;
  }
  
  public boolean cambiarEstado(String estado, String nickname)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(us);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update usuario set estado =? where nickname =?");
      pst.setString(1, estado);
      pst.setString(2, nickname);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al cambiar de estado " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  /*
  public boolean cambiarEstadoSonido(String estadoSon, String nickname)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(us);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update usuario set sonido=? where nickname =?");
      pst.setString(1, estadoSon);
      pst.setString(2, nickname);
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al cambiar de estado de sonido" + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  */
  public boolean ingresarUsuario(usuario user)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(us);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Insert into usuario values(?,?,?,?,?,?,?,?,?,?,?)");
      pst.setString(1, user.getNickname());
      pst.setString(2, user.getNombres());
      pst.setString(3, user.getCargo());
      pst.setString(4, user.getClave());
      pst.setString(5, user.getCliente());
      pst.setString(6, user.getHab());
      pst.setString(7, user.getValorhosp());
      pst.setString(8, user.getReservacion());
      pst.setString(9, user.getVisita());
      pst.setString(10, user.getEstado());
      pst.setString(11, user.getUsuario());
      /*
      pst.setString(11, user.getEstado());
      pst.setBinaryStream(12, user.getFoto(), String.valueOf(user.getFoto()).length());
      pst.setString(13, user.getAjustes());
      pst.setString(14, "True");
      */
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al ingresar usuario " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public boolean actualizarUsuario(String nick, usuario user)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(us);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Update usuario set nombres = ?, cargo= ?, clave = ?, cliente = ?, hab = ?, "
              + "\"valorHosp\" = ?, reservacion = ?, visita =?, estado =?, usuario=?, nickname =? where nickname =?");
      pst.setString(1, user.getNombres());
      pst.setString(2, user.getCargo());
      pst.setString(3, user.getClave());
      pst.setString(4, user.getCliente());
      pst.setString(5, user.getHab());
      pst.setString(6, user.getValorhosp());
      pst.setString(7, user.getReservacion());
      pst.setString(8, user.getVisita());
      pst.setString(9, user.getEstado());
      pst.setString(10, user.getUsuario());
      pst.setString(11, user.getNickname());
      
      pst.setString(12, nick);
      /*
      pst.setString(11, user.getAjustes());
      pst.setString(12, user.getSonido());
      pst.setString(13, nick);
*/
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al actualizar usuario " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  /*
  public void actualizarFotoUsuario(String nick, FileInputStream fis, int longitudBytes)
  {
    this.conectar = this.datos.Conectar("act foto user");
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement ps = this.conectar.prepareStatement("Update usuario set foto = ? where nickname = ?");
      ps.setBinaryStream(1, fis, longitudBytes);
      ps.setString(2, nick);
      ps.execute();
      ps.close();
    }
    catch (SQLException|NumberFormatException|HeadlessException x)
    {
      System.out.println(x);
    }
    this.datos.Desconectar(this.conectar);
  }
  */
  public boolean eliminarUsuario(String nick)
  {
    boolean op = false;
    this.conectar = this.datos.Conectar(us);
    try
    {
      Statement st = this.conectar.createStatement();
      PreparedStatement pst = this.conectar.prepareStatement("Delete from usuario where nickname=?");
      pst.setString(1, nick);
      
      int n = pst.executeUpdate();
      if (n != 0) {
        op = true;
      }
    }
    catch (Exception e)
    {
      System.out.println("Error al eliminar usuario " + e.getMessage());
    }
    this.datos.Desconectar(this.conectar);
    return op;
  }
  
  public DefaultTableModel MostrarTablaUsuario(List<usuario> Lista, usuario user)
  {
    String[] titulos = { "NickName", "Nombres", "Cargo", "Clave", "Clientes", "Habitaciones", "Valor Hosp.", "Reservaciones", "Visitas", "Usuarios"};
    
    String[] registro = new String[titulos.length];DefaultTableModel modelo = new DefaultTableModel((Object[][])null, titulos){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    for (usuario usuario : Lista){
        if (!usuario.getNickname().equalsIgnoreCase(user.getNickname())) {
            registro[0] = usuario.getNickname();
            registro[1] = usuario.getNombres();
            registro[2] = usuario.getCargo();
            registro[3] = usuario.getClave();
            registro[4] = permitidoBloqueado(usuario.getCliente());
            registro[5] = permitidoBloqueado(usuario.getHab());
            registro[6] = permitidoBloqueado(usuario.getValorhosp());
            registro[7] = permitidoBloqueado(usuario.getReservacion());
            registro[8] = permitidoBloqueado(usuario.getVisita());
            registro[9] = permitidoBloqueado(usuario.getUsuario());
            modelo.addRow(registro);
        }
    }
    return modelo;
  }
  
  public String permitidoBloqueado(String var)
  {
    String result = "";
    if (var.equalsIgnoreCase("True")) {
      result = "√";
    } else {
      result = "X";
    }
    return result;
  }
  public boolean permitidoBloqueadoInv(String var)
  {
    boolean result = false;
    if (var.equalsIgnoreCase("√")) {
      result = true;
    } else {
      result = false;
    }
    return result;
  }
  
  public boolean existeUsuario(List<usuario> listUser, String nickName)
  {
    boolean existe = false;
    for (int i = 0; i < listUser.size(); i++) {
      if (((usuario)listUser.get(i)).getNickname().equalsIgnoreCase(nickName)) {
        existe = true;
      }
    }
    return existe;
  }

  public String getNickName(List<usuario> listUser, String nick, int cont){
      String count = "";
      if (cont>0) {
          count = cont+"";
      }else{
          count = "";
      }
      while (existeUsuario(listUser, nick)) {          
          cont = cont+1;
          nick = nick+cont;
      }
      /*
      for (int i = 0; i < listUser.size(); i++) {
          if ((nick+count).equals(listUser.get(i).getNickname())) {
              cont = cont +1;
              getNickName(listUser, nick+count, cont);
              System.out.println(nick+count+", "+ cont);
          }else{
              count=cont+"";
              nick = nick+count;
          }
      }
        */
      return nick;
  }
}
