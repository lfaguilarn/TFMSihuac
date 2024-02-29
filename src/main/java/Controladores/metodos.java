/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;
import Entidades.cliente;
import Entidades.cliente_Hab;
import Entidades.consumo;
import Entidades.habitacion;
import Entidades.reservacion;
import Entidades.usuario;
import Entidades.visita;
import com.tfmsihuac.tfmsihuac.Inicio;
//import com.toedter.calendar.JDateChooser;
//import com.toedter.calendar.JTextFieldDateEditor;
//import com.toedter.components.JSpinField;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
/**
 *
 * @author Luis Fernando
 */
public class metodos {
    public String[] cursores = { "defecto", "punto de mira", "texto", "cargando", "inclinado derecha", "inclinado izquierda", "inclinado izquierda", "inclinado derecha", "vertical", "vertical", "horizontal", "horizontal", "mano", "movimiento" };
  String[] tiposMensaje = { "error", "informacion", "peligro", "pregunta" };
  String[] iconostiposMensaje = { "logo_error", "logo_informacion", "logo_peligro", "logo_pregunta" };
  public String defecto = "defecto";
  public String puntoDeMira = "punto de mira";
  public String texto = "texto";
  public String cargando = "cargando";
  public String inclinadoDerecha = "inclinado derecha";
  public String inclinadoIzquierda = "inclinado izquierda";
  public String vertical = "vertical";
  public String horizontal = "horizontal";
  public String mano = "mano";
  public String movimiento = "movimiento";
  public String error = "error";
  public String informacion = "informacion";
  public String peligro = "peligro";
  public String pregunta = "pregunta";
  public String azul = "blue";
public String marrón = "brown";
public String verde = "green";
public String naranja = "orange";
public String rosa = "pink";
public String morado = "purple";
public String rojo = "red";
public String amarillo = "yellow";
public String negro = "black";
public String blanco = "white";
public String gris = "#696969";
public int superTitulo = 16;
public int titulo1 = 15;
public int titulo2 = 14;
public int normal = 13;

  usuarioController usuario = new usuarioController();
//  public AudioClip Cursor = Applet.newAudioClip(metodos.class.getResource("click.wav"));
 // public AudioClip Click = Applet.newAudioClip(metodos.class.getResource("cursor.wav"));
  
  public void cambiarImgBoton(JButton boton, String imagen){
    botonInvisible(boton);
    ImageIcon newicon = null;
    ImageIcon foto = new ImageIcon(getClass().getResource("/Imagenes/" + imagen));
    Image img = foto.getImage();
    Image newimg = img.getScaledInstance(boton.getBounds().width, boton.getBounds().height, 4);
    //Color color = new Color(Color.DARK_GRAY);
    newicon = new ImageIcon(newimg);
    boton.setIcon(newicon);
  }
  
  public void cambiarImgBoton(JButton[] botones, String[] imagenes)
  {
    for (int i = 0; i < botones.length; i++) {
      cambiarImgBoton(botones[i], imagenes[i]);
    }
  }
  
  public void centrarVentana(JFrame ventana, boolean maximizar)
  {
    ventana.setLocationRelativeTo(null);
    ventana.setResizable(maximizar);
  }
  
  public void ventanaSinBorde(JFrame ventana)
  {
    ventana.setUndecorated(true);
  }
  
  public void ventanaMaximizada(JFrame ventana)
  {
    ventana.setExtendedState(ventana.MAXIMIZED_BOTH);
  }
  
  public void ventanaSinBtnMax(JFrame ventana)
  {
    ventana.setResizable(false);
  }
  
  public double anchoVentana(JFrame ventana){
      return ventana.getSize().getWidth();
  }
  public double altoVentana(JFrame ventana){
      return ventana.getSize().getHeight();
  }
  
  public void centrarTitleMax(JFrame frame) {

        Font font = frame.getFont();

        String currentTitle = frame.getTitle().trim();
        FontMetrics fm = frame.getFontMetrics(font);
        int frameWidth = frame.getWidth();
        int titleWidth = fm.stringWidth(currentTitle);
        int spaceWidth = fm.stringWidth(" ");
        int centerPos = (frameWidth / 2) - (titleWidth / 2);
        int spaceCount = centerPos / spaceWidth;
        String pad = "";
        pad = String.format("%" + (spaceCount - 14) + "s", pad);
        frame.setTitle(pad + currentTitle);

    }
  
  public void centrarTitle(JFrame frame, String titulo){
    frame.setSize(600,300);
    frame.setFont(new Font("System", Font.PLAIN, 14));
    Font f = frame.getFont();
    FontMetrics fm = frame.getFontMetrics(f);
    int x = fm.stringWidth(titulo);
    int y = fm.stringWidth(" ");
    int z = frame.getWidth()/2 - (x/2);
    int w = z/y;
    String pad ="";
    //for (int i=0; i!=w; i++) pad +=" "; 
    pad = String.format("%"+w+"s", pad);
    frame.setTitle(pad+titulo);
  }
  public void cambiarMouseLbl(String cursor, JLabel lbl)
  {
    for (int i = 0; i < this.cursores.length; i++) {
      if (this.cursores[i].equalsIgnoreCase(cursor)) {
        lbl.setCursor(new Cursor(i));
      }
    }
  }
  
  public void cambiarMouseLbl(String cursor, JLabel[] lbl)
  {
    for (int i = 0; i < this.cursores.length; i++) {
      if (this.cursores[i].equalsIgnoreCase(cursor)) {
        for (int j = 0; j < lbl.length; j++) {
          lbl[j].setCursor(new Cursor(i));
        }
      }
    }
  }
  
  public void cambiarMouseBtn(String cursor, JButton btn)
  {
    for (int i = 0; i < this.cursores.length; i++) {
      if (this.cursores[i].equalsIgnoreCase(cursor)) {
        btn.setCursor(new Cursor(i));
      }
    }
  }
  
  public void cambiarMouseBtn(String cursor, JButton[] btn)
  {
    for (int i = 0; i < this.cursores.length; i++) {
      if (this.cursores[i].equalsIgnoreCase(cursor)) {
        for (int j = 0; j < btn.length; j++) {
          btn[j].setCursor(new Cursor(i));
        }
      }
    }
  }
  
  public void cambiarMouseCheck(String cursor, JCheckBox check)
  {
    for (int i = 0; i < this.cursores.length; i++) {
      if (this.cursores[i].equalsIgnoreCase(cursor)) {
        check.setCursor(new Cursor(i));
      }
    }
  }
  
  public void cambiarMouseCheck(String cursor, JCheckBox[] check)
  {
    for (int i = 0; i < this.cursores.length; i++) {
      if (this.cursores[i].equalsIgnoreCase(cursor)) {
        for (int j = 0; j < check.length; j++) {
          check[j].setCursor(new Cursor(i));
        }
      }
    }
  }
  
  public int cantImgFondo(String img){
    int result=0;
    int cont=0; 
    String excep ="";
    do {          
        try {
          result++;
          ImageIcon foto = new ImageIcon(getClass().getResource("/Imagenes/" + img+result+".jpg"));
          cont++;
        } catch (Exception e) {
          excep=e.toString();
        }
    
    } while (!excep.equals("java.lang.NullPointerException"));
    return cont;
  }
  
  public void mensajeDialogo(String mensaje, String titulo, String tipoMsj)
  {
      
      Icon icono = new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Image image = new ImageIcon(getClass().getResource("/Imagenes/logo_"+tipoMsj+".png")).getImage();
            g.drawImage(image, x, y, c); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getIconWidth() {
            return 50;
        }

        @Override
        public int getIconHeight() {
            return 50;
        }
    };
    for (int i = 0; i < this.tiposMensaje.length; i++) {
      if (tipoMsj.equals(this.tiposMensaje[i])) {
        JOptionPane.showMessageDialog(null, mensaje, titulo, i, icono);
      }
    }
  }
  
  public String mensajeDialogoEntradaCombo(String mensaje, String titulo, String tipoMsj, String[] opciones, String txtDefecto)
  {
    String result = null;
    Icon icono = new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Image image = new ImageIcon(getClass().getResource("/Imagenes/logo_"+tipoMsj+".png")).getImage();
            g.drawImage(image, x, y, c); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getIconWidth() {
            return 50;
        }

        @Override
        public int getIconHeight() {
            return 50;
        }
    };
    for (int i = 0; i < this.tiposMensaje.length; i++) {
      if (tipoMsj.equals(this.tiposMensaje[i])) {
        if (opciones == null)
        {
          if (txtDefecto.equals("")) {
            try{result = (String) JOptionPane.showInputDialog(null, mensaje, titulo, i,icono,null,null);}catch(Exception e){result=null;}
          } else {
            try{result = (String) JOptionPane.showInputDialog(null, mensaje, titulo, i, icono, null, txtDefecto);}catch(Exception e){result=null;}
          }
        }
        else {
          try{result = JOptionPane.showInputDialog(null, mensaje, titulo, i, icono, opciones, txtDefecto) + "";}catch(Exception e){result=null;}
        }
      }
    }
    return result;
  }
  
  public boolean mensajeConfirmacion(String mensaje, String titulo, String tipoMsj)
  {
    boolean result = false;
    int resp = 0;
    Icon icono = new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Image image = new ImageIcon(getClass().getResource("/Imagenes/logo_"+tipoMsj+".png")).getImage();
            g.drawImage(image, x, y, c); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getIconWidth() {
            return 50;
        }

        @Override
        public int getIconHeight() {
            return 50;
        }
    };
    for (int i = 0; i < this.tiposMensaje.length; i++) {
      if (tipoMsj.equals(this.tiposMensaje[i])) {
        resp = JOptionPane.showConfirmDialog(null, mensaje, titulo, 0, i, icono);
      }
    }
    if (resp == 0) {
      result = true;
    }
    return result;
  }
  
  public int mensajeOpcionMultiple(String mensaje, String titulo, String[] opciones)
  {
      Icon icono = new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Image image = new ImageIcon(getClass().getResource("/Imagenes/logo_pregunta.png")).getImage();
            g.drawImage(image, x, y, c); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getIconWidth() {
            return 50;
        }

        @Override
        public int getIconHeight() {
            return 50;
        }
    };
    int result = 0;
    result = JOptionPane.showOptionDialog(null, mensaje, titulo, -1, 3, icono, opciones, opciones[0]);
    return result;
  }
  
  public String convertToMultiline(String orig)
  {
    return "<html>" + orig.replaceAll("\n\t", "<br>");
  }
  
  public void mostrarMenu(int x, int y, int espaciado, JButton[] botones, usuario us, int ancho, int alto)
  {
    /*
    int x = 302;
    int y = 25;
    int espaciado = 250;
      */
    int cont = 0;
    boolean cliente = Boolean.parseBoolean(us.getCliente());
    boolean hab = Boolean.parseBoolean(us.getHab());
    boolean reservacion = Boolean.parseBoolean(us.getReservacion());
    boolean visita = Boolean.parseBoolean(us.getVisita());
    boolean valorHab = Boolean.parseBoolean(us.getValorhosp());
    boolean use = Boolean.parseBoolean(us.getUsuario());
    for (int i = 0; i < botones.length; i++)
    {
      if ((cliente) && (botones[i].getToolTipText().equals("Clientes")))
      {
        botones[i].setBounds(x + cont * espaciado, y, ancho, alto);
        botones[i].setVisible(true);cont++;
      }
      if ((hab) && (botones[i].getToolTipText().equals("Habitaciones")))
      {
        botones[i].setBounds(x + cont * espaciado, y-2, ancho, alto);
        botones[i].setVisible(true);cont++;
      }
      if ((reservacion) && (botones[i].getToolTipText().equals("Reservaciones")))
      {
        botones[i].setBounds(x + cont * espaciado, y-2, ancho, alto);
        botones[i].setVisible(true);cont++;
      }
      if ((visita) && (botones[i].getToolTipText().equals("Visitas")))
      {
        botones[i].setBounds(x + cont * espaciado, y, ancho, alto);
        botones[i].setVisible(true);cont++;
      }
      if ((valorHab) && (botones[i].getToolTipText().equals("Precio de Habitaciones")))
      {
        botones[i].setBounds(x + cont * espaciado, y-2, ancho, alto);
        botones[i].setVisible(true);cont++;
      }
      if ((use) && (botones[i].getToolTipText().equals("Usuarios")))
      {
        botones[i].setBounds(x + cont * espaciado, y-2, ancho, alto);
        botones[i].setVisible(true);cont++;
      }
    }
  }
  
  public void botonesNoVisibles(JButton[] botones)
  {
    for (int i = 0; i < botones.length; i++) {
      botones[i].setVisible(false);
    }
  }
  
  public void botonInvisible(JButton boton){
      boton.setBorder(null);
      boton.setContentAreaFilled(false);
  }
  
  public void panelInvisible(JPanel[] paneles)
  {
    for (int i = 0; i < paneles.length; i++) {
      paneles[i].setOpaque(false);
    }
  }
  
  public void panelOculto(JPanel[] paneles)
  {
    for (int i = 0; i < paneles.length; i++) {
      paneles[i].setVisible(false);
    }
  }
  
  public void cambiarImgLbl(JLabel lbl, String imagen) 
  {
     
    ImageIcon newicon = null;
    ImageIcon foto = new javax.swing.ImageIcon(getClass().getResource("/Imagenes/"+imagen));
    Image img = foto.getImage();
    Image newimg = img.getScaledInstance(lbl.getBounds().width, lbl.getBounds().height, Image.SCALE_SMOOTH);
    newicon = new javax.swing.ImageIcon(newimg);
    lbl.setIcon(newicon);
  }
  public void cambiarImgGifLbl(JLabel lbl, String imagen)
  {
    ImageIcon newicon = null;
    ImageIcon foto = new ImageIcon(getClass().getResource("/Imagenes/" + imagen));
    //Image img = foto.getImage();
    //Image newimg = img.getScaledInstance(lbl.getBounds().width, lbl.getBounds().height, Image.SCALE_SMOOTH);
    newicon = new ImageIcon(foto.getImage().getScaledInstance(lbl.getBounds().width, lbl.getBounds().height, Image.SCALE_DEFAULT));
    lbl.setIcon(newicon);
  }
 
  public static boolean isFileExists(File file) {
        return file.exists() && !file.isDirectory();
  }
  
  public boolean comprobarArchivo(String ruta){
      boolean result = true;
      try{
          ImageIcon foto = new ImageIcon(getClass().getResource("/Imagenes/" + ruta));
      }catch(Exception e){
          result = false;
      }
      return result;
  }
  
  public boolean comprobarClave(String contraseña)
  {
    boolean result = false;
    boolean mayus = false;
    boolean minus = false;
    boolean num = false;
    char caracte = '.';
    for (int i = 0; i < contraseña.length(); i++)
    {
      caracte = contraseña.charAt(i);
      if (Character.isUpperCase(caracte)) {
        mayus = true;
      }
      if (Character.isLowerCase(caracte)) {
        minus = true;
      }
      if (Character.isDigit(caracte)) {
        num = true;
      }
    }
    if ((num) && (mayus) && (minus)) {
      result = true;
    }
    return result;
  }
  
  public void cerrarSesion(JRootPane rootPane, String nickName)
  {
    Object[] opciones = { "Aceptar", "Cancelar" };
    int eleccion = JOptionPane.showOptionDialog(rootPane, "¿"+nickName + " desea cerrar la aplicación?", "Mensaje de Confirmación", 0, 3, null, opciones, "Aceptar");
    if (eleccion == 0)
    {
      this.usuario.cambiarEstado("Inactivo", nickName);
      System.exit(0);
    }
  }
  
  public void fondoTablaInvisible(JTable tabla, JScrollPane scrollPane, DefaultTableModel model, int[] anchos, int tamañoletra)
  {
    tabla.setModel(model);
    for (int i = 0; i < tabla.getColumnCount(); i++) {        
        tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
    }
    DefaultTableCellRenderer render = (DefaultTableCellRenderer)tabla.getTableHeader().getDefaultRenderer();
    render.setHorizontalAlignment(0);
    render.setFont(render.getFont().deriveFont(1));
    tabla.getTableHeader().setFont(new Font("Times New Roman", 1, (tamañoletra*6/10)));
    tabla.getTableHeader().setDefaultRenderer(render);
    tabla.setRowHeight(25);
    tabla.setOpaque(false);
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    tabla.setShowGrid(false);
    TableRowSorter sorter = new TableRowSorter(model);
    tabla.setRowSorter(sorter);
      centrarTexto(tabla);
    
  }
  
  public void centrarTexto(JTable tabla){
      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.CENTER);
      for (int i = 0; i < tabla.getColumnCount(); i++) {
          tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
      }
    
  }
  
  public void limpiarTexto(JTextField[] txt)
  {
    for (int i = 0; i < txt.length; i++) {
      txt[i].setText("");
    }
  }
  
  public void SNumeros(JTextField txt, final String tipoNumero)
  {
    txt.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e)
      {
        char c = e.getKeyChar();
        if ((tipoNumero.equalsIgnoreCase("numero")) && (
          (c < '0') || (c > '9'))) {
          e.consume();
        }
        if ((tipoNumero.equalsIgnoreCase("decimal")) && 
          ((c < '0') || (c > '9')) && (c != '.')) {
          e.consume();
        }
      }
    });
  }
  /*
  public void SNumeros(JSpinField txt, final String tipoNumero)
  {
    txt.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e)
      {
        char c = e.getKeyChar();
        if ((tipoNumero.equalsIgnoreCase("numero")) && (
          (c < '0') || (c > '9'))) {
          e.consume();
        }
        if ((tipoNumero.equalsIgnoreCase("decimal")) && 
          ((c < '0') || (c > '9')) && (c != '.')) {
          e.consume();
        }
      }
    });
  }
  */
  public void SNumeros(JSpinner txt, final String tipoNumero)
  {
    txt.addKeyListener(new KeyAdapter()
    {
      public void keyTyped(KeyEvent e)
      {
        char c = e.getKeyChar();
        if ((tipoNumero.equalsIgnoreCase("numero")) && (
          (c < '0') || (c > '9'))) {
          e.consume();
        }
        if ((tipoNumero.equalsIgnoreCase("decimal")) && 
          ((c < '0') || (c > '9')) && (c != '.')) {
          e.consume();
        }
      }
    });
  }
  
  public boolean isDouble(String cadena)
  {
    boolean resultado;
    if(cadena==null){
        resultado = false;
    }else{
        try
        {
          Double.parseDouble(cadena);
          resultado = true;
        }
        catch (NumberFormatException exception)
        {
        //  boolean resultado;
          resultado = false;
        }
    }
        return resultado;
  }
  
  public boolean isNumeric(String cadena)
  {
    boolean resultado;
    if(cadena==null){
        resultado = false;
    }else{
        try
        {
          Integer.parseInt(cadena);
          resultado = true;
        }
        catch (NumberFormatException exception)
        {
        //  boolean resultado;
          resultado = false;
        }
    }
    return resultado;
  }
  
  public String convertirADouble(String cadena)
  {
    String result = "";
    if(cadena==null){
        result = "";
    }else{
        if (!cadena.equals("")&& isDouble(cadena)) {
          result = String.valueOf(Double.parseDouble(cadena));
        }
    }
    return result;
  }
  
  public String fechaSistema()
  {
    Date fecha = new Date();
    String[] diaSemana = { "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
    String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
    return diaSemana[fecha.getDay()]+" ♦ "+fecha.getDate() + "/" + meses[fecha.getMonth()] + "/" + (fecha.getYear() + 1900);
  }
  public String fechaSistema2(String fechaIni, int cantdias)
  {
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      Date fechaIng = null;
      try {
          fechaIng = formato.parse(fechaIni);
      } catch (Exception e) {
          System.out.println(e.getMessage().toString());
      }
    fechaIng.setTime(fechaIng.getTime()+(cantdias*(1000*60*60*24)));
    String[] diaSemana = { "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
    String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" };
    return diaSemana[fechaIng.getDay()]+" "+fechaIng.getDate() + "-" + meses[fechaIng.getMonth()] + "-" + (fechaIng.getYear() + 1900);
  }
  public String fechaSistema3(String fechaIni,int cantdias)
  {
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaIng = null;
    try {
        fechaIng = formato.parse(fechaIni);
    } catch (Exception e) {
        System.out.println(e.getMessage().toString());
    }
    fechaIng.setTime(fechaIng.getTime()+(cantdias*(1000*60*60*24)));
    return fechaIng.getDate() + "/" + (fechaIng.getMonth()+1) + "/" + (fechaIng.getYear() + 1900);
  }
  
  public String fechaJCalendar(Date fecha){
    //String result = "";
    String[] diaSemana = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
    String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };
    return fecha.getDate() + "/" + meses[fecha.getMonth()] + "/" + (fecha.getYear() + 1900);
    
  }
  public String DateAString(Date fecha){
      if (fecha!=null) {
          return fecha.getDate() + "/" + (fecha.getMonth()+1) + "/" + (fecha.getYear() + 1900);
      }else{
          return "";
      }
  }
  public Date StringADate(String fecha, String separador){
    //Date result = new Date();
    String dia = fecha.split(separador)[0];
    String mes = fecha.split(separador)[1];
    String anio = fecha.split(separador)[2];
    String fechaIni = dia+"/"+mes+"/"+anio;
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    Date fechaIng = null;
    try {
        fechaIng = formato.parse(fechaIni);
    } catch (Exception e) {
        System.out.println(e.getMessage().toString());
    }
    return fechaIng;
  }
 
  public String hora(){
        Calendar h=Calendar.getInstance();
        java.util.Date hora=h.getTime();
        DateFormat f= new SimpleDateFormat("hh:mm:ss aa");
        return f.format(hora);
    }
  
  public void cambiarTxtLbl(JLabel lbl, String txt)
  {
    lbl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)), txt, 0, 0, new Font("Calibri", 1, 16)));
  }
  /*
  public String obtenerFechaCalendar(JDateChooser calendario)
  {
    return calendario.getDate().getDate() + "/" + (calendario.getDate().getMonth() + 1) + "/" + (calendario.getDate().getYear() + 1900);
  }
  
  public void sonar(AudioClip sonido, usuario usuario)
  {
    if (Boolean.parseBoolean(usuario.getSonido()))
    {
      sonido.stop();
      sonido.play();
    }
  }
  
  public void llenarCombos(JComboBox[] combos)
  {
    for (int i = 0; i < combos.length; i++) {
      AutoCompleteDecorator.decorate(combos[i]);
    }
  }
  */
  public double redondear(double numero, int cantDec)
  {
    double num = 0.0D;
    BigDecimal bd = new BigDecimal(numero);
    bd = bd.setScale(cantDec, RoundingMode.HALF_UP);
    num = bd.doubleValue();
    return num;
  }
  public String redondearCerDer(double numero, int cantDec)
  {
    double num = 0.0D;
    String result="";
    BigDecimal bd = new BigDecimal(numero);
    bd = bd.setScale(cantDec, RoundingMode.HALF_UP);
    num = bd.doubleValue();
    String cantDecDer = cortarCadenaPorPuntos(num+"")[1].length()+"";
    int difCantDec = cantDec - Integer.parseInt(cantDecDer);
    result = num+"";
      for (int i = 0; i < difCantDec; i++) {
          result = result + "0";
      }
    return result;
  }
  
  public String[] cortarCadenaPorPuntos(String cadena) {
    return cadena.split("\\.");
  }
  
  public void spinerNoEditable(JSpinner spn)
  {
    JFormattedTextField tf = ((JSpinner.DefaultEditor)spn.getEditor()).getTextField();
    tf.setEditable(false);
    tf.setBackground(Color.white);
  }
  
  public void checkBoxToolTipText(JCheckBox[] checkBox)
  {
    for (int i = 0; i < checkBox.length; i++) {
      if (checkBox[i].isSelected()) {
        checkBox[i].setToolTipText("Bloquear la gestión de " + checkBox[i].getText());
      } else {
        checkBox[i].setToolTipText("Permitir gestionar " + checkBox[i].getText());
      }
    }
  }
  
  public boolean checkBoxSelect(JCheckBox[] checkBox)
  {
    boolean select = false;
    for (int i = 0; i < checkBox.length; i++) {
      if (checkBox[i].isSelected()) {
        select = true;
      }
    }
    return select;
  }
  
  public void checkBoxToolTipText(JCheckBox checkBox)
  {
    if (checkBox.isSelected()) {
      checkBox.setToolTipText("Bloquear la gestión de " + checkBox.getText());
    } else {
      checkBox.setToolTipText("Permitir gestionar " + checkBox.getText());
    }
  }
  
  public void checkBoxDiselect(JCheckBox[] checkBox)
  {
    for (int i = 0; i < checkBox.length; i++) {
      checkBox[i].setSelected(false);
    }
  }
  
  public void siguiente(JTextField[] txt)
  {
    for (int i = 1; i < txt.length; i++) {
      txt[(i - 1)].setNextFocusableComponent(txt[i]);
    }
  }
  
  
  public void deTblATxtSelected(JTable tbl, JTextField txt, int columna)
  {
    if (tbl.getSelectedRow() >= 0) {
      txt.setText(tbl.getValueAt(tbl.getSelectedRow(), columna).toString());
    }
  }
  public String deTblAString(JTable tbl, int columna)
  {
      String txt="";
    if (tbl.getSelectedRow() >= 0) {
      txt = tbl.getValueAt(tbl.getSelectedRow(), columna).toString();
    }
    return txt;
  }
  
  public String[] deTblAArray(JTable tbl){
      String [] datosTabla = new String[tbl.getColumnCount()];
      for (int i = 0; i < datosTabla.length; i++) {
          datosTabla[i]=tbl.getValueAt(tbl.getSelectedRow(),i).toString();
      }
      return datosTabla;
  }
  
  public void textPaneTransparente(JTextPane txt, JScrollPane pane, int tamaño)
  {
    pane.setBorder(BorderFactory.createEmptyBorder());
    pane.getViewport().setOpaque(false);
    pane.setOpaque(false);
    pane.setBackground(new Color(0, 0, 0, 0));
    txt.setBorder(BorderFactory.createEmptyBorder());
    txt.setOpaque(false);
    txt.setBackground(new Color(0, 0, 0, 0));
    txt.setFont(new Font("Times New Roman", 0, tamaño + 1));
    txt.setEditable(false);
  }
  
  public void validarText(JTextField txt, JLabel lbl, JButton btn, String dat)
  {
    if (txt.getText().equals(""))
    {
      lbl.setForeground(Color.red);
      btn.setEnabled(false);
      txt.setToolTipText("Datos no válidos");
      lbl.setToolTipText("Datos no válidos");
      btn.setToolTipText("Datos no válidos");
    }
    else
    {
      lbl.setForeground(Color.black);
      btn.setEnabled(true);
      txt.setToolTipText(null);
      lbl.setToolTipText(null);
      btn.setToolTipText(null);
    }
    if (txt.getText().equals(dat)) {
      btn.setEnabled(false);
    } else {
      btn.setEnabled(true);
    }
  }
  
  public boolean validadorDeCedula(String cedula) {
    boolean cedulaCorrecta = false;

    try {

    if (cedula.length() == 10) // ConstantesApp.LongitudCedula
    {
    int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
    if (tercerDigito < 6) {
    // Coeficientes de validación cédula
    // El decimo digito se lo considera dígito verificador
     int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
     int verificador = Integer.parseInt(cedula.substring(9,10));
     int suma = 0;
     int digito = 0;
    for (int i = 0; i < (cedula.length() - 1); i++) {
     digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
     suma += ((digito % 10) + (digito / 10));
    }

    if ((suma % 10 == 0) && (suma % 10 == verificador)) {
     cedulaCorrecta = true;
    }
    else if ((10 - (suma % 10)) == verificador) {
     cedulaCorrecta = true;
    } else {
     cedulaCorrecta = false;
    }
    } else {
    cedulaCorrecta = false;
    }
    } else {
    cedulaCorrecta = false;
    }
    } catch (NumberFormatException nfe) {
    cedulaCorrecta = false;
    } catch (Exception err) {
    //System.out.println("Una excepcion ocurrio en el proceso de validadcion");
    cedulaCorrecta = false;
    }

    if (!cedulaCorrecta) {
    //System.out.println("La Cédula ingresada es Incorrecta");
    }
    return cedulaCorrecta;
    }
    
  public String obtenerDatosSumadosJTable(JTable tabla, int colum){
      String result = "";
      double cost = 0;
      if (tabla.getColumnCount()<=colum) {
          result = "";
      }else{
        for (int i = 0; i < tabla.getRowCount(); i++) {
            cost = cost + Double.parseDouble(tabla.getValueAt(i, colum)+"");
        }
        result = cost+"";
      }
      
      return result;
  }
  
  public void BtnDefecto(JFrame frame, JButton btn){
      frame.getRootPane().setDefaultButton(btn);
  }
  
  public void posLblRes(JLabel[][] labels,JLabel[][] labelsF, JLabel lblfondo, int separacionFilas, int separacionColumnas, int anchoV){
      int contx=labels.length;
      int conty=labels[0].length;
      int posx=lblfondo.getBounds().x+tamaños(anchoV, 391);
      int posy=lblfondo.getBounds().y;
      //int ancholbl = (lblfondo.getBounds().width-(separacionColumnas*3*2)-((conty-1)*separacionColumnas))/conty;
      int ancholbl = (lblfondo.getBounds().width-((tamaños(anchoV, 391))*2))/(conty);
      int altolbl = 10+(lblfondo.getBounds().height-((contx+1)*separacionFilas))/contx;
      
      for (int i = 0; i < contx; i++) {
          for (int j = 0; j < conty; j++) {
              labelsF[i][j].setBounds((posx+(ancholbl*j)+separacionColumnas), (posy+(altolbl*i)+separacionFilas), ancholbl+2, altolbl);
              if(i==10&&j==0){
                  labels[i][j].setBounds((posx+(ancholbl*j)+separacionColumnas), (posy+(altolbl*i)+separacionFilas), ancholbl, altolbl);
              }else{
                  if (j==0||i==0) {
                      labels[i][j].setBounds((posx+(ancholbl*j)+separacionColumnas)+5, (posy+(altolbl*i)+separacionFilas), ancholbl+2, altolbl);
                  }else{
                      labels[i][j].setBounds((posx+(ancholbl*j)+separacionColumnas), (posy+(altolbl*i)+separacionFilas), ancholbl+2, altolbl);
                  }
                  
              }
          }
      }
      /*
      for (int i = 0; i < contx; i++) {
          for (int j = 0; j < conty; j++) {
              labelsF[i][j].setBounds((posx+(ancholbl*j)+separacionColumnas), (posy+(altolbl*i)+separacionFilas), ancholbl, altolbl);              
          }
      }*/
  }
  
  public void mostrarHabRes(JLabel[][] labels,JLabel[][] labelsF, JLabel lblfondo, String fecha, List<reservacion> listR) throws IOException{
      int contx=labels.length;
      int conty=labels[0].length;
      for (int i = 0; i < contx; i++) {
          System.out.println("ii: "+i);
          for (int j = 0; j < conty; j++) {
              System.out.println("jj: "+j);
              if (i==0&&j>0) {
                  cambiarImgLbl(labelsF[i][j], "blanco1.png");
                  labels[i][j].setText(convertToMultiline(fechaSistema2(fecha,(j-1))));
                  labels[i][j].setToolTipText(fechaSistema2(fecha,(j-1)));
              }
              if (j==0&&i>0) {
                  cambiarImgLbl(labelsF[i][j], "blanco1.png");
                  labels[i][j].setText("Hab. N° "+i);
                  labels[i][j].setToolTipText("Habitación N° "+i);
                  labels[i][j].setFont(new Font("Times New Roman", Font.BOLD, 28));
                  if(i==10){
                      labels[i][j].setFont(new Font("Times New Roman", Font.BOLD, 18));
                  }
              }
              if (i>0&&j>0) {
                  cambiarImgLbl(labelsF[i][j], "verdeRes.png");
                  /*String estadoHab = obtenerEstado(i, fechaSistema3(fecha,(j-1)), listR);
                  cambiarImgLbl(labelsF[i][j], estadoHab.split("♦")[0]);
                  labelsF[i][j].setToolTipText("Hab. N° "+i+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+estadoHab.split("♦")[1]);*/
              }
          }
      }
      //System.out.println("Filas: "+contx+"\nColumnas: "+conty+"\nancho lbl: "+ancholbl+"\nalto lbl: "+altolbl);
  }
  public boolean mostrarHabRes2(int fila, JLabel[][] labels,JLabel[][] labelsF, JLabel lblfondo, String fecha, List<reservacion> listR, List<cliente_Hab> listCH, List<visita> listV, List<habitacion> listHabitacion, List<cliente> listCli, List<consumo> listConsumo, int tamañoLetra) throws IOException{
      boolean result=false;
      //Date fech = new Date();
      int contx=labels.length;
      int conty=labels[0].length;
      habitacionController controlHab = new habitacionController();
        for (int j = 0; j < conty; j++) {
            if (fila==0&&j>0) {
                cambiarImgLbl(labelsF[fila][j], "blanco1.png");
                labels[fila][j].setText(convertToMultiline(fechaSistema2(fecha,(j-1))));
                labels[fila][j].setToolTipText(fechaSistema2(fecha,(j-1)));
                labels[fila][j].setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*63/100));
            }
            if (j==0&&fila>0) {
                cambiarImgLbl(labelsF[fila][j], "blanco1.png");
                labels[fila][j].setText("Hab. N° "+fila);
                labels[fila][j].setToolTipText("Habitación N° "+fila);
                labels[fila][j].setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*77/100));
            }
            if (j==0&&fila==0) {
                //cambiarImgLbl(labelsF[fila][j], "blanco1.png");
                cambiarImgLbl(labels[fila][j], "house.png");
                //labels[fila][j].setText(convertToMultiline(" Ir a fecha actual"));
                labels[fila][j].setToolTipText("Regresar a Fecha Actual");
                labels[fila][j].setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*60/100));
                cambiarMouseLbl("mano",labels[fila][j]);
            }
            if (fila>0&&j>0) {
                /*if (j==1&&fecha.equals(DateAString(fech))) {
                    String stateHab = controlHab.estadoHab(fila+"", listHabitacion);
                    if(stateHab.equals("Ocupado")){
                        cambiarImgLbl(labelsF[fila][j], "rojoRes.png");
                        labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+"Ocupado");
                    }else{
                        if(stateHab.equals("No Disponible")){
                            cambiarImgLbl(labelsF[fila][j], "blanco1.png");
                            labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+"No Disponible");
                        }else{
                            String estadoHab = obtenerEstado(fila, fechaSistema3(fecha,(j-1)), listR);
                            cambiarImgLbl(labelsF[fila][j], estadoHab.split("♦")[0]);
                            labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+estadoHab.split("♦")[1]);
                        }
                    }
                }else{*/
                    String estadoHab = obtenerEstado(fila, StringADate(fechaSistema3(fecha,(j-1)),"/"), listR, listCH, listV, listCli,listConsumo);
                    //System.out.println(fila + " - "+fechaSistema3(fecha,(j-1))+" - "+estadoHab);
                   
                    if(estadoHab.split("♦").length>2){
                        String fechaIngreso = estadoHab.split("\\(")[0].split(" ")[estadoHab.split("\\(")[0].split(" ").length-1];
                        String fechaSalida = estadoHab.split("\\(")[1].split(" ")[estadoHab.split("\\(")[1].split(" ").length-1];
                        String horaIngreso = de12A24Horas(estadoHab.split("\\(")[1].split("\\)")[0]);
                        String horaSalida = de12A24Horas(estadoHab.split("\\(")[2].split("\\)")[0]);
                        int horaIn = Integer.parseInt(horaIngreso.split(":")[0])*60+Integer.parseInt(horaIngreso.split(":")[1]);
                        int horaSal = Integer.parseInt(horaSalida.split(":")[0])*60+Integer.parseInt(horaSalida.split(":")[1]);
                        int diaEnMin = 24*60;
                        if (fechaSistema3(fecha,(j-1)).equals(fechaIngreso)) {
                            labelsF[fila][j].setBounds((int)labelsF[fila][j].getBounds().getX(),(int)labelsF[fila][j].getBounds().getY(), (int)(labelsF[fila][0].getBounds().getWidth())*horaIn/diaEnMin, (int)labelsF[fila][j].getBounds().getHeight());
                        }
                        if (fechaSistema3(fecha,(j-1)).equals(fechaSalida)) {
                            labelsF[fila][j].setBounds((int)labelsF[fila][j].getBounds().getX(),(int)labelsF[fila][j].getBounds().getY(), (int)(labelsF[fila][0].getBounds().getWidth())*horaSal/diaEnMin, (int)labelsF[fila][j].getBounds().getHeight());
                        }
                        //System.out.println(fila+" - "+fechaIngreso+" - "+horaIngreso+" - "+fechaSalida+" - "+horaSalida);
                        cambiarImgLbl(labelsF[fila][j], estadoHab.split("♦")[0]);
                        cambiarImgLbl(labels[fila][j], estadoHab.split("♦")[2]);
                        labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+estadoHab.split("♦")[1]);
                        //labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+labelsF[fila][j-1].getToolTipText());
                        labels[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+estadoHab.split("♦")[3]);
                        //System.out.println("finnn");
                    }else{
                        labelsF[fila][j].setBounds((int)labelsF[fila][j].getBounds().getX(),(int)labelsF[fila][j].getBounds().getY(), (int)(labels[fila][j].getBounds().getWidth()), (int)labelsF[fila][j].getBounds().getHeight());
                        cambiarImgLbl(labelsF[fila][j], estadoHab.split("♦")[0]);
                        cambiarImgLbl(labels[fila][j], estadoHab.split("♦")[0]);
                        labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+estadoHab.split("♦")[1]);
                    }
                    String stateHab = controlHab.estadoHab(fila+"", listHabitacion);
                    if(stateHab.equals("No Disponible")){
                        cambiarImgLbl(labelsF[fila][j], "blanco1.png");
                        labelsF[fila][j].setToolTipText("Hab. N° "+fila+" - "+fechaSistema2(fecha,(j-1)).split(" ")[0]+" - "+"No Disponible");
                    }
                //}
            }
            
            if(j==(conty-1)){
                result = true;
            }
      }
      return result;//System.out.println("Filas: "+contx+"\nColumnas: "+conty+"\nancho lbl: "+ancholbl+"\nalto lbl: "+altolbl);
  }
  
  public String obtenerEstado(int idHab, Date fecha, List<reservacion> listR, List<cliente_Hab> listCH, List<visita> listV, List<cliente> listCli, List<consumo> listConsumo){
      String result = "verdeRes.png♦Disponible";
      Date fechaini;
      Date fechafin;
      habitacionController controlHab = new habitacionController();
      int ocupado = 0;
      int reservado = 0;
      int disponible = 1;
      String cant="";
      String res = "";
      reservacion r = new reservacion();
      cliente_Hab cH = new cliente_Hab();
      if (listR.size()>0) {
          for (int i = 0; i < listR.size(); i++) {
                if (listR.get(i).getId_hab().equals(idHab+"")
                        //&&listR.get(i).getEstado().equals("Activo")
                        ) {
                    fechaini = StringADate(listR.get(i).getFecha_ing(),"/");
                    fechaini.setHours(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[0]));
                    fechaini.setMinutes(Integer.parseInt(listR.get(i).getHora_ingreso().split(":")[1]));
                    fechaini.setSeconds(0);
                    fechafin = StringADate(listR.get(i).getFecha_sal(),"/");
                    fechafin.setHours(Integer.parseInt(listR.get(i).getHora_salida().split(":")[0]));
                    fechafin.setMinutes(Integer.parseInt(listR.get(i).getHora_salida().split(":")[1]));
                    fechafin.setSeconds(0);
                    //System.out.println(idHab+" - fecha: "+DateAString(fecha)+" - fechaini: "+DateAString(fechaini)+" - fechafin: "+DateAString(fechafin));
                    if (fecha.compareTo(fechaini)>=0&&fecha.compareTo(fechafin)<0) {
                        reservado = 1;
                        
                    }/*
                    List<String> fechas = entreFechas(fechaini, fechafin);
                    if (compararFechas(fecha, fechafin)<=0) {
                        for (int j = 0; j < fechas.size(); j++) {
                            if (fecha.equals(fechas.get(j))) {
                                reservado = 1;
                                r = listR.get(i);
                                //result="amarilloRes.png♦"+listR.get(i).getNombres()+" - "+listR.get(i).getObservaciones()+" - "+listR.get(i).getDireccion()+" - Check-in: "+listR.get(i).getHora_ingreso()+" - Check-out: "+listR.get(i).getHora_salida();
                            }
                        }
                    }*/
                    if (fecha.getYear()==fechafin.getYear()&&fecha.getMonth()==fechafin.getMonth()&&fecha.getDate()==fechafin.getDate()) {
                        reservado = 2;
                        //r = listR.get(i);
                    }
                    if (//fecha.compareTo(fechaini)<0&&
                            (fecha.getYear()==fechaini.getYear()&&fecha.getMonth()==fechaini.getMonth()&&fecha.getDate()==fechaini.getDate())) {
                        reservado = 3;
                        //r = listR.get(i);
                    }
                    r = listR.get(i);
                    res = r.getNombres()+" - "+"Check-in: "+r.getFecha_ing()+"("+de24A12Horas(r.getHora_ingreso())+") - Check-out: "+r.getFecha_sal()+"("+de24A12Horas(r.getHora_salida())+") - "+r.getCant_us()+" - "+r.getCant_dias()+" día(s) - Total a pagar: $"+r.getTotal_pagar()+" - Abono: $"+r.getEstado();
                    
                    //System.out.println(fecha+" - R - "+fechafin);
                    /*
                    if(compararFechas(fecha, fechafin)==0){
                        for (int j = 0; j < fechas.size(); j++) {
                            reservado = 2;
                            //if (fecha.equals(fechas.get(j))) {
                                //result="amarilloRes.png♦"+listR.get(i).getNombres()+" - "+listR.get(i).getObservaciones()+" - "+listR.get(i).getDireccion()+" - Check-in: "+listR.get(i).getHora_ingreso()+" - Check-out: "+listR.get(i).getHora_salida()+"♦verdeRes.png♦Disponible";
                            //}
                        }
                    }*/
                }
          }
      }
      if (listCH.size()>0) {
          for (int i = 0; i < listCH.size(); i++) {
                if (listCH.get(i).getId_hab().equals(idHab+"")) {
                    /*
                    fechaini = listCH.get(i).getFechaIng();
                    fechafin = listCH.get(i).getFechaSal();
                    List<String> fechas = entreFechas(fechaini, fechafin);*/
                    fechaini = StringADate(listCH.get(i).getFechaIng(),"/");
                    fechafin = StringADate(listCH.get(i).getFechaSal(),"/");
                    fechafin.setHours(Integer.parseInt(listCH.get(i).getEstado().split(":")[0]));
                    fechafin.setMinutes(Integer.parseInt(listCH.get(i).getEstado().split(":")[1]));
                    fechafin.setSeconds(0);
                    cliente_HabController ctrlCliHab = new cliente_HabController();
                    cant = ctrlCliHab.cantHuespHab2(idHab+"", listCH, fecha,listV, listCli,listConsumo);
                    //System.out.println(idHab+" - fecha: "+DateAString(fecha)+" - fechaini: "+DateAString(fechaini)+" - fechafin: "+DateAString(fechafin));
                    if (fecha.compareTo(fechaini)>=0&&fecha.compareTo(fechafin)<0) {
                        ocupado = 1;
                    }
                    if (fecha.getYear()==fechafin.getYear()&&fecha.getMonth()==fechafin.getMonth()&&fecha.getDate()==fechafin.getDate()) {
                        ocupado = 2;
                        //cant = ctrlCliHab.cantHuespHab2(idHab+"", listCH, fecha,listV, listCli, listConsumo);
                    }
                    if (//fecha.compareTo(fechaini)<0&&
                            (fecha.getYear()==fechaini.getYear()&&fecha.getMonth()==fechaini.getMonth()&&fecha.getDate()==fechaini.getDate())) {
                        ocupado = 3;
                        //r = listR.get(i);
                    }
                    
                    /*
                    if (compararFechas(fecha, fechafin)<0) {
                        for (int j = 0; j < fechas.size(); j++) {
                            if (fecha.equals(fechas.get(j))) {
                                ocupado = 1;
                                //result="rojoRes.png♦"+"Ocupado - "+ cant;
                            }
                        }
                    }
                    //System.out.println(fecha+" - "+fechafin+" - comparacion - "+compararFechas(fecha, fechafin));
                    if (compararFechas(fecha, fechafin)==0) {
                        for (int j = 0; j < fechas.size(); j++) {
                            ocupado = 2;
                            //if (fecha.equals(fechas.get(j))) {
                            cant = ctrlCliHab.cantHuespHab2(idHab+"", listCH, StringADate(fechaSistema3(fecha, -1),"/"),listV, listCli, listConsumo);
                                //result="rojoRes.png♦"+"Ocupado - "+ cant+"♦verdeRes.png♦Disponible";
                            //}
                        }
                    }
                    */
              }
          }
      }
      //System.out.println(//DateAString(fecha)+" - "+idHab+" - reservado == "+reservado+"&&ocupado == "+ocupado);
      if(reservado == 1&&ocupado ==0){
          result="amarilloRes.png♦"+res;
      }
      if(reservado == 2&&ocupado ==0){
          result="amarilloRes.png♦"+res+"♦verdeRes.png♦Disponible";
      }
      if (reservado == 3&&ocupado ==0) {
          result="verdeRes.png♦Disponible"+"♦amarilloRes.png♦"+res;
      }
      if(ocupado==1&&reservado==0){
          result="rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==2&&reservado==0){
          result="rojoRes.png♦"+"Ocupado - "+ cant+"♦verdeRes.png♦Disponible";
      }
      if(ocupado==3&&reservado==0){
          result="verdeRes.png♦Disponible"+"♦rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==1&&reservado==1){
          result="rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==2&&reservado==1){
          result="rojoRes.png♦"+"Ocupado - "+ cant+"♦amarilloRes.png♦"+res;
      }
      if(ocupado==3&&reservado==1){
          result="amarilloRes.png♦"+res+"♦rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==1&&reservado==2){
          result="rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==2&&reservado==2){
          result="rojoRes.png♦"+"Ocupado - "+ cant+"♦verdeRes.png♦Disponible";
      }
      if(ocupado==3&&reservado==2){
          result="amarilloRes.png♦"+res+"♦rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==1&&reservado==3){
          result="rojoRes.png♦"+"Ocupado - "+ cant;
      }
      if(ocupado==2&&reservado==3){
          result="rojoRes.png♦"+"Ocupado - "+ cant+"♦amarilloRes.png♦"+res;
      }
      if(ocupado==3&&reservado==3){
          result="verdeRes.png♦Disponible♦rojoRes.png♦"+"Ocupado - "+ cant;
      }
      
      /*
      if (reservado==2&&ocupado==1) {
          result="amarilloRes.png♦"+r.getNombres()+" - "+"Check-in: "+r.getFecha_ing()+"("+r.getHora_ingreso()+") - Check-out: "+r.getFecha_sal()+"("+r.getHora_salida()+") - "+r.getCant_us()+" - "+r.getCant_dias()+" día(s) - Total a pagar: $"+r.getTotal_pagar()+" - Abono: $"+r.getEstado()+"♦rojoRes.png♦"+"Ocupado - "+ cant;
      }
      
      if (disponible==1&&reservado==3) {
          result="verdeRes.png♦Disponible"+"♦amarilloRes.png♦"+r.getNombres()+" - "+"Check-in: "+r.getFecha_ing()+"("+r.getHora_ingreso()+") - Check-out: "+r.getFecha_sal()+"("+r.getHora_salida()+") - "+r.getCant_us()+" - "+r.getCant_dias()+" día(s) - Total a pagar: $"+r.getTotal_pagar()+" - Abono: $"+r.getEstado();
      }
      */
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
      //result.add(fechaIng.getDate()+"/"+(fechaIng.getMonth()+1)+"/"+(fechaIng.getYear()+1900));
      while (fechaIng.compareTo(fechaSal)!=0) {
          //System.out.println("entro");
          result.add(fechaIng.getDate()+"/"+(fechaIng.getMonth()+1)+"/"+(fechaIng.getYear()+1900));
          //System.out.println(fechaIng.getDate()+"/"+(fechaIng.getMonth()+1)+"/"+(fechaIng.getYear()+1900)+" .*. ");
          fechaIng.setTime(fechaIng.getTime()+(1000*60*60*24));
      }
      //result.add(fechaSal.getDate()+"/"+(fechaSal.getMonth()+1)+"/"+(fechaSal.getYear()+1900));
      return result;
  }
  
  public int compararFechas(String fecha1, String fecha2){
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      Date fechaIng = null;
      Date fechaSal = null;
      try {
          fechaIng = formato.parse(fecha1);
          fechaSal = formato.parse(fecha2);
      } catch (Exception e) {
          System.out.println(e.getMessage().toString());
      }
      return fechaIng.compareTo(fechaSal);
  }
  public boolean compararEntreFechas(Date fecha, Date fechaIng, Date fechaSal){
      boolean result = false;
      /*
      Date horaFin = new Date();
      horaFin.setHours(h);
      horaFin.setMinutes(min);
      horaFin.setSeconds(0);
      Date horaActual = new Date();
      //System.out.println("*"+horaFin + " - "+ horaActual);
      Date fechaNew = StringADate(DateAString(fecha), "/");
      int hour = horaActual.compareTo(horaFin);
      
      boolean hora = false;
      if (hour<=0){
          hora = true;
      }
      */
      if((fecha.compareTo(fechaIng)>=0&&fecha.compareTo(fechaSal)<0)){
          result = true;
      }
      /*
      if (hora&&fechaNew.compareTo(fechaSal)==0) {
          result = true;
      }
      */
      return result;
  }
  
  public void enfocarTxt(JTextField txt){
      txt.requestFocus();
  }
  
  public void actEstHab(String idHab, List<reservacion> listReser, List<cliente_Hab> listClientHab, List<cliente> clientes, Date hora, List<habitacion> listH){
    int reser = 0;
    int ocup = 0;
    int noDisp = 0;
      //System.out.println(hora +" - " + horaFin + " - "+hour);
    //System.out.println(Integer.parseInt(hora.split("-")[0])+" - "+hora.split("-")[1]);
    habitacionController controlHab = new habitacionController();
    cliente_HabController ctrlCH = new cliente_HabController();
    clienteController controlCliente = new clienteController();
    reservacion r = new reservacion();
    cliente_Hab cH = new cliente_Hab();
      if (controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("No Disponible")) {
          noDisp=1;
      }
    //Date fecha = new Date();
    for (int i = 0; i < listReser.size(); i++) {
        if (listReser.get(i).getId_hab().equals(idHab)) {
            Date fechaSal = StringADate(listReser.get(i).getFecha_sal(), "/");
            Date fechaIng = StringADate(listReser.get(i).getFecha_ing(), "/");
            fechaIng.setHours(Integer.parseInt(listReser.get(i).getHora_ingreso().split(":")[0]));
            fechaIng.setMinutes(Integer.parseInt(listReser.get(i).getHora_ingreso().split(":")[1]));
            fechaSal.setHours(Integer.parseInt(listReser.get(i).getHora_salida().split(":")[0]));
            fechaSal.setMinutes(Integer.parseInt(listReser.get(i).getHora_salida().split(":")[1]));
            
            //System.out.println(idHab+" - "+(DateAString(hora)+" - "+(DateAString(fechaIng)+" - "+DateAString(fechaSal))));
            //System.out.println(idHab+ " / "+ hora.compareTo(fechaIng)+"\n"+idHab+" / "+hora.compareTo(fechaSal));
            if(hora.compareTo(fechaIng)>=0&&hora.compareTo(fechaSal)<0){
                r=listReser.get(i);
                reser=1;
                //System.out.println(idHab+" - "+reser);
            }/*
                //System.out.println(idHab + " entro");
                if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Reservado")) {
                    controlHab.actualizarEstadoHabitacionDisponible(listReser.get(i).getId_hab(), "Reservado");
                }
                if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(listReser.get(i).getObservaciones())) {
                    controlHab.actualizarObservHabitacion(listReser.get(i).getId_hab(), listReser.get(i).getObservaciones());
                }
            }else{
                if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Disponible")) {
                    controlHab.actualizarEstadoHabitacionDisponible(listReser.get(i).getId_hab(), "Disponible");
                System.out.println("entro 1");
                }
                if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes")) {
                    controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes");
                }
            }
            */
          }
      }
      //System.out.println(listClient.size()+" -> tamaño");
      for (int i = 0; i < listClientHab.size(); i++) {
          Date fechaSal = StringADate(listClientHab.get(i).getFechaSal(), "/");
          Date fechaIng = StringADate(listClientHab.get(i).getFechaIng(), "/");
          fechaSal.setHours(Integer.parseInt(listClientHab.get(i).getEstado().split(":")[0]));
          fechaSal.setMinutes(Integer.parseInt(listClientHab.get(i).getEstado().split(":")[1]));
          //System.out.println(fecha+" - "+StringADate(listClient.get(i).getFechaIng(), "/")+" - "+StringADate(fechaSistema3(listClient.get(i).getFechaSal(), cantDias), "/"));
          //System.out.println(fecha+" - "+fechaIng+" - "+fechaSal);
          if (compararEntreFechas(hora, fechaIng, fechaSal)&&listClientHab.get(i).getId_hab().equals(idHab)) {
              cH=listClientHab.get(i);
              ocup=1;
          }/*
              //System.out.println("Habitacion:" + idHab + "oc");
            if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Ocupado")) {
                controlHab.actualizarEstadoHabitacionDisponible(listClientHab.get(i).getId_hab(), "Ocupado");
            }
            if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes")) {
                controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes");
            }
              //controlHab.actualizarEstadoHabitacionDisponible(idHab, "Ocupado");
              //controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes");
          }
          if (!compararEntreFechas(fecha, fechaIng, fechaSal)&&listClientHab.get(i).getId_hab().equals(idHab)
                  //&&listClientHab.get(i).getFechaIng().equals(listClientHab.get(i).getFechaSal())
                  ) {
              //System.out.println("Habitacion:" + idHab +" - d");
            if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Disponible")) {
                controlHab.actualizarEstadoHabitacionDisponible(listClientHab.get(i).getId_hab(), "Disponible");
                System.out.println("entro 2");
            }
            if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes")) {
                controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes");
            }
              //controlHab.actualizarEstadoHabitacionDisponible(idHab, "Disponible");
              //controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes");
              for (int  j= 0; j < clientes.size(); j++) {
                        if (clientes.get(j).getEstado().equals("Hab "+idHab)) {
                            if (!controlCliente.buscarCliente(clientes.get(j).getCedula(), clientes).getEstado().equals("Off")) {
                                controlCliente.actualizarEstadoCliente("Off", clientes.get(j).getCedula());
                            }
                            //controlCliente.actualizarEstadoCliente("Off", clientes.get(j).getCedula());
                        }
                    }
          }/*
          if (!compararEntreFechas(fecha, StringADate(listClientHab.get(i).getFechaIng(), "/"), StringADate(fechaSistema3(listClientHab.get(i).getFechaSal(), 0), "/"),h,min)&&listClientHab.get(i).getId_hab().equals(idHab)) {
              System.out.println("Habitacion:" + idHab +" - di");
              controlHab.actualizarEstadoHabitacionDisponible(idHab, "Disponible");
              controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, fecha)+" huéspedes");
              for (int  j= 0; j < clientes.size(); j++) {
                        //System.out.println("estado - "+clientes.get(j).getEstado()+" - "+idHab);
                        if (clientes.get(j).getEstado().equals("Hab "+idHab)) {
                            controlCliente.actualizarEstadoCliente("Off", clientes.get(j).getCedula());
                        }
                    }
          }*/
      }
      //System.out.println(idHab+" - ocup "+ocup+ "&&reser "+reser+" &&noDisp "+noDisp);
      if(ocup==0&&reser==0&&noDisp==0){
         // System.out.println("Entro "+idHab+" - "+controlHab.buscarHabitacionPorId(idHab, listH).getEstado());
        if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Disponible")) {
            controlHab.actualizarEstadoHabitacionDisponible(idHab, "Disponible");
        }
        if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(ctrlCH.cantHuespHab(idHab, listClientHab, hora)+" huéspedes")) {
            controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, hora)+" huéspedes");
        }
      }
      if (reser==1&&ocup==0&&noDisp==0) {
        if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Reservado")) {
            controlHab.actualizarEstadoHabitacionDisponible(r.getId_hab(), "Reservado");
        }
        if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(r.getObservaciones())) {
            controlHab.actualizarObservHabitacion(r.getId_hab(), r.getObservaciones());
        }
      }
      if (ocup==1&&reser==0&&noDisp==0) {
          if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Ocupado")) {
              controlHab.actualizarEstadoHabitacionDisponible(idHab, "Ocupado");
          }
          if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(ctrlCH.cantHuespHab(idHab, listClientHab, hora)+" huéspedes")) {
              controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, hora)+" huéspedes");
          }
      }
      if(ocup==1&&reser==1&&noDisp==0){
          if (!controlHab.buscarHabitacionPorId(idHab, listH).getEstado().equals("Ocupado")) {
              controlHab.actualizarEstadoHabitacionDisponible(idHab, "Ocupado");
          }
          if (!controlHab.buscarHabitacionPorId(idHab, listH).getObservaciones().equals(ctrlCH.cantHuespHab(idHab, listClientHab, hora)+" huéspedes")) {
              controlHab.actualizarObservHabitacion(idHab, ctrlCH.cantHuespHab(idHab, listClientHab, hora)+" huéspedes");
          }
      }
          
          for (int  j= 0; j < clientes.size(); j++) {
              if (clientes.get(j).getEstado().equals("Hab "+idHab)) {
                  if (!controlCliente.buscarCliente(clientes.get(j).getCedula(), clientes).getEstado().equals("Off")&&
                          !validarClientHab(clientes.get(j).getCedula(), listClientHab, hora)) {
                      controlCliente.actualizarEstadoCliente("Off", clientes.get(j).getCedula());
                  }
              }
          }
      
  }
  
  public boolean validarClientHab(String cedula, List<cliente_Hab> listCh, Date fechaAct){
      boolean result = false;
      Date fechaIni;
      Date fechaFin;
      
      for (int i = 0; i < listCh.size(); i++) {
          if (listCh.get(i).getId_cliente().equals(cedula)) {
              fechaIni = StringADate(listCh.get(i).getFechaIng(), "/");
              if (fechaAct.compareTo(fechaIni)>0) {
                  fechaFin = StringADate(listCh.get(i).getFechaSal(), "/");
                  fechaFin.setHours(Integer.parseInt(listCh.get(i).getEstado().split(":")[0]));
                  fechaFin.setMinutes(Integer.parseInt(listCh.get(i).getEstado().split(":")[1]));
                  if (fechaAct.compareTo(fechaFin)<0) {
                      result=true;
                  }
              }
          }
      }
      return result;
  }
  
  public boolean validarFechaHabOcRes(String numHab, List<cliente_Hab> listCh, Date fechaIni, Date fechaFin){
      boolean result= false;
      int cont=0;
      Date fechaSal= new Date();
      for (int i = 0; i < listCh.size(); i++) {
          if(listCh.get(i).getId_hab().equals(numHab)){
              fechaSal = StringADate(listCh.get(i).getFechaSal(), "/");
              fechaSal.setHours(Integer.parseInt(listCh.get(i).getEstado().split(":")[0]));
              fechaSal.setMinutes(Integer.parseInt(listCh.get(i).getEstado().split(":")[1]));
              fechaSal.setSeconds(0);
              while (fechaIni.compareTo(fechaFin)<0){
                  fechaIni.setTime(fechaIni.getTime()+(cont*(1000*60*60*24)));
                  if (fechaIni.compareTo(StringADate(listCh.get(i).getFechaIng(), "/"))>=0&&fechaIni.compareTo(fechaSal)<0) {
                      result=true;
                      fechaIni = fechaFin;
                  }else{
                      cont++;
                  }
              }
          }
          /*
          List<String> fechas = entreFechas(DateAString(fechaIni), DateAString(fechaFin));
          for (int j = 0; j < fechas.size(); j++) {
              System.out.println("fecha "+j+": "+fechas.get(j)+"|"+listCh.get(i).getFechaIng()+"|"+listCh.get(i).getFechaSal());
                  if (listCh.get(i).getId_hab().equals(numHab)&&compararEntreFechas(StringADate(fechas.get(j), "/"), StringADate(listCh.get(i).getFechaIng(),"/"), StringADate(listCh.get(i).getFechaSal(),"/"))) {
                      result=true;
              }
          }*/
      }
      return result;
  }
  
  public String de12A24Horas(String hora12){
      String result = "";
      int h = Integer.parseInt(hora12.split(":")[0]);
      int min = Integer.parseInt(hora12.split(":")[1].split(" ")[0]);
      String form = hora12.split(":")[1].split(" ")[1].toLowerCase();
      if (form.equalsIgnoreCase("pm")&&h<12) {
          h=h+12;
      }
      String minutos = "0";
      if(min==0){
          minutos = "00";
      }else{
          minutos = min+"";
      }
      result = h+":"+minutos;
      
      return result;
  }
  public String de24A12Horas(String hora24){
      String result = "";
      int h = Integer.parseInt(hora24.split(":")[0]);
      int min = Integer.parseInt(hora24.split(":")[1]);
      String minutos = "0";
      if(min==0){
          minutos = "00";
      }else{
          if (min<10) {
              minutos = "0"+min+"";
          }else{
              minutos = min+"";
          }
      }
      if (h<12) {
          result = h+":"+minutos+" AM";
      }
      if (h==12) {
          result = h+":"+minutos+" PM";
      }
      if (h>12) {
          h=h-12;
          result = h+":"+minutos+" PM";
      }
      return result;
  }
  
  public int tamaños(int original, int porcentaje){
      int result = 0;
      double porcent = Double.parseDouble(porcentaje+"");
      double orig = Double.parseDouble(original+"");
      result = (int)redondear((orig*porcent)/10000, 0);
      return result;
  }
  
  public void distrBtnHab(JButton[] botones, JPanel panel, int porcentX, int porcentY, int cantBtnX){
      int ancho = panel.getBounds().width*porcentX/100;
      int alto = panel.getBounds().height*porcentY/100;
      int anchoBtn = ancho/(cantBtnX+1);
      int altoBtn = anchoBtn;
      int espacioX = anchoBtn/(cantBtnX+1);
      int espacioY = altoBtn/((botones.length/cantBtnX));
      int contY=-1;
      int contX=0;
      for (int i = 0; i < botones.length; i++) {
          if (i%cantBtnX==0) {
              contY=contY+1;
              contX=0;
          }
          botones[i].setBounds(espacioX*(contX+1)+(anchoBtn*contX),espacioY*(contY+1)+(altoBtn*contY),anchoBtn,altoBtn);
          contX++;
          /*
          for (int j = 0; j < cantBtnX; j++) {
              botones[i].setBounds(espacioX*(i+1)+(anchoBtn*i),espacioY*(j+1)+(altoBtn*j),anchoBtn,altoBtn);
              System.out.println(botones[i].getToolTipText()+": "+espacioX*(i+1)+(anchoBtn*i)+","+espacioY*(j+1)+(altoBtn*j)+","+anchoBtn+","+altoBtn);
          }
            */
      }
  }
  public void distrLblHab(JLabel[] labels, JPanel panel, int porcentX, int porcentY, int cantBtnX){
      int ancho = panel.getBounds().width*porcentX/100;
      int alto = panel.getBounds().height*porcentY/100;
      int anchoBtn = ancho/(cantBtnX+1);
      int altoBtn = anchoBtn;
      int espacioX = anchoBtn/(cantBtnX+1);
      int espacioY = altoBtn/((labels.length/cantBtnX));
      int contY=-1;
      int contX=0;
      for (int i = 0; i < labels.length; i++) {
          if (i%cantBtnX==0) {
              contY=contY+1;
              contX=0;
          }
          labels[i].setBounds(espacioX*(contX+1)+(anchoBtn*contX),espacioY*(contY+1)+(altoBtn*contY),anchoBtn,altoBtn);
          contX++;
      }
  }
  /*
  public void establecerFechMin(JDateChooser chooser, Date date, int cantDias){
      date.setTime(date.getTime()+(cantDias*(1000*60*60*24)));
      chooser.setMinSelectableDate(date);
  }
  public void establecerFechMax(JDateChooser chooser, Date date, int cantDias){
      date.setTime(date.getTime()+(cantDias*(1000*60*60*24)));
      chooser.setMaxSelectableDate(date);
  }
  public void establecerFecha(JDateChooser chooser, Date date, int cantDias){
      date.setTime(date.getTime()+(cantDias*(1000*60*60*24)));
      chooser.setDate(date);
  }
  public void deshabilitarEscritDateChoos(JDateChooser chooser){
      JTextFieldDateEditor editor = (JTextFieldDateEditor) chooser.getDateEditor();
      editor.setEditable(false);
  }
  public void deshabilitarEscritJspinn(JSpinner spinner){
      //JTextField
      ((DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
  }
 
  public void deshabilitarTodasFechasDateChoos(JDateChooser chooser){
      chooser.setSelectableDateRange(null, null);
  }

  public void tamañoFuenteTablaSpn(JSpinField spn, JTable tabla){
      tabla.setRowHeight(spn.getValue()+8);
      tabla.setFont(new Font("Times New Roman", Font.PLAIN, spn.getValue()));
  }
  */
  public void tamañoFuenteTablaSpn(JSpinner spn, JTable tabla){
      tabla.setRowHeight((int)spn.getValue()+8);
      tabla.setFont(new Font("Times New Roman", Font.PLAIN, (int)spn.getValue()));
  }
  
  public void txtEditable(JTextField[] txt, boolean enabled){
      for (int i = 0; i < txt.length; i++) {
          txt[i].setEditable(enabled);
      }
  }
  
  public void actCapMax(JLabel lbl, List<habitacion> habitaciones, List<cliente_Hab> clienteHabitaciones){
      habitacionController controlHab = new habitacionController();
      lbl.setText(convertToMultiline(controlHab.capacidadMax(habitaciones, clienteHabitaciones)));
  }
  
  public String[] generarFecha(Date fecha, int cantDias, boolean diaAct){
      String[] fechas = new String[cantDias];
      for (int i = 0; i < fechas.length; i++) {
          if(diaAct)fechas[i]= DateAString(fecha);
          fecha.setTime(fecha.getTime()+((1000*60*60*24)));
          if(!diaAct)fechas[i]= DateAString(fecha);
      }
      return fechas;
  }
  
  public String[] generarHora(Date fechaSal, String horaSal){
      Date fecha = new Date();
      Date newFecha = new Date();
      newFecha.setHours(0);
      newFecha.setMinutes(30);
      int hour = Integer.parseInt(horaSal.split(":")[0]);
      int min = Integer.parseInt(horaSal.split(":")[1]);
      fecha.setHours(hour);
      fecha.setMinutes(min);
      if (fecha.compareTo(fechaSal)<0) {
          hour=0;
      }else{
          hour = new Date().getHours();
      }
      int cant = 0;
      if(new Date().getMinutes()>30&&hour>0){
          cant = (24-hour-1)*2;
      }else{
          cant = ((24-hour-1)*2)+1;
      }
              
      String[] fechas = new String[cant];
      if (hour>0) {
              if (new Date().getMinutes()>=30) {
                  newFecha.setHours(new Date().getHours()+1);
                  newFecha.setMinutes(0);
              }else{
                  newFecha.setHours(new Date().getHours());
                  newFecha.setMinutes(30);
              }
          }
      for (int i = 0; i < fechas.length; i++) {
          fechas[i]=de24A12Horas(newFecha.getHours()+":"+newFecha.getMinutes());
          newFecha.setTime(newFecha.getTime()+(30*60*1000));
      }
      return fechas;
  }
  
  public Image cambiarIconoVentana(String rutaImg){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(rutaImg));
        return retValue;
    }
  
  public int cantDias(String fechaIni, String fechaFin){
      int result = 0;
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      Date fechaIng = StringADate(fechaIni, "/");
      Date fechaSal = StringADate(fechaFin, "/");
      /*
      try {
          fechaIng = formato.parse(fechaIni);
          fechaSal = formato.parse(fechaFin);
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
        */
      while (fechaIng.compareTo(fechaSal)!=0) {
          result++;
          fechaIng.setTime(fechaIng.getTime()+(1000*60*60*24));
      }
      
      return result;
  }
  
  public String personalizarMsj(String mensaje, String color, String tipoLetra, int tamaño, boolean negrita, boolean cursiva, boolean subrayada){
      String result = "";
      String bold1 = "<strong>";
      String bold2 = "</strong>";
      String cursiva1 = "<em>";
      String cursiva2 = "</em>";
      String subrayada1 = "<u>";
      String subrayada2 = "</u>";
      String fuente1 = "<FONT FACE=\""+tipoLetra+"\">";
      String fuente2 = "</FONT>";
      String colorfuente = "black";
      if (!negrita) {
          bold1="";
          bold2="";
      }
      if (!cursiva) {
          cursiva1="";
          cursiva2="";
      }
      if (!subrayada) {
          subrayada1="";
          subrayada2="";
      }
      if (tipoLetra.equals("")) {
          fuente1="";
          fuente2="";
      }
      if (!color.equals("")) {
          colorfuente = color;
      }
      String inicio = "<html><p style=\" color: "+colorfuente+"; font: "+tamaño+"px\">";
      String fin = "</p></html>";
      result = inicio+bold1+cursiva1+subrayada1+fuente1+mensaje+fuente2+subrayada2+cursiva2+bold2+fin;
      return result;
  }
  public String personalizarMsjTabla(String encabezado, String mensaje, String colorFondoEncabezado, String colorLetraEncabezado, String colorLetraFila1,String colorLetraFila2, String colorFondoFila1, String colorFondoFila2, String tipoLetra, int tamañoLetraEncab, int tamañoLetraFila){
      String result = "";
      String tablaIni = "<TABLE BORDER=1>";
      String tablaFin = "</TABLE>";
      String encabezadoInicio = "<TR>";
      String encabezadoFin = "</TR>";
      String celdaFin = "</font></TD>";
      String celdas = "";
      String enc = "";
      for (int i = 0; i < encabezado.split("♦").length; i++) {
          enc = enc+"<TH BGCOLOR=\""+colorFondoEncabezado+"\" colspan=\"1\" align=\"center\"  height=\"48\"><font size="+tamañoLetraEncab+" face= \""+tipoLetra+"\" color=\""+colorLetraEncabezado+"\"><b>"+encabezado.split("♦")[i]+"</b></font></TH>";
      }
      for (int i = 0; i < mensaje.split("•").length; i++) {
          celdas = celdas+"<TR>";
          for (int j = 0; j < mensaje.split("•")[i].split("♦").length; j++) {
              if (i%2==0) {
                  celdas = celdas+"<TD BGCOLOR=\""+colorFondoFila1+"\" colspan=\"1\" align=\"center\"  height=\"4\"><font size="+tamañoLetraFila+" face= \""+tipoLetra+"\" color=\""+colorLetraFila1+"\">"+mensaje.split("•")[i].split("♦")[j]+celdaFin;
              }else{
                  celdas = celdas+"<TD BGCOLOR=\""+colorFondoFila2+"\" colspan=\"1\" align=\"center\"  height=\"4\"><font size="+tamañoLetraFila+" face= \""+tipoLetra+"\" color=\""+colorLetraFila2+"\">"+mensaje.split("•")[i].split("♦")[j]+celdaFin;
              }
          }
          celdas = celdas+"</TR>";
      }
      String inicio = "<html>";
      String fin = "</html>";
      result = inicio+tablaIni+encabezadoInicio+enc+encabezadoFin+celdas+tablaFin+fin;
      return result;
  }
  
  public void vistoEquis (boolean visto, JLabel lbl) throws IOException{
      if (visto) {
          cambiarImgLbl(lbl, "visto.png");
      }else{
          cambiarImgLbl(lbl, "equis.png");
      }
  }
  
  public String deDoubleAInt(String num, boolean redondear){
      String result="";
      String numero = "";
      String decimal ="";
      if (num.split("\\.").length>1) {
          numero = num.split("\\.")[0];
          decimal = num.split("\\.")[1];
          if (redondear) {
              if (Integer.parseInt(decimal)>=5) {
                  numero = (Integer.parseInt(numero)+1)+"";
              }
          }
      }else{
          numero = num+"";
      }
      result = (numero);
      return result;
  }
  
  public String deDateAHora24(Date fecha){
      String hora="";
      hora=fecha.getHours()+":"+fecha.getMinutes();
      return hora;
  }
}
