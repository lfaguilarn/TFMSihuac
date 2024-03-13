/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tfmsihuac.tfmsihuac;

import Controladores.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import Controladores.*;
import Entidades.*;
import com.sun.org.apache.bcel.internal.generic.DADD;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Luis Fernando
 */
public class Principal extends javax.swing.JFrame {
    metodos metodo = new metodos();
    int contImgFondo=1;
    Toolkit t;
    Dimension screenSize;
    int anchoVentana;
    int altoVentana;
    int tiempoCambiarFondoSeg = 100;
    Timer tiempoCambiarFondo, tiempoHabRes, tiempoAct;
    int cantImgFondo;
    int X;
    int Y;
    public int tamañoLetra=0;
    double hipVen;
    int contHabRes=0;
    Date fechaRes = new Date();
    usuario user = new usuario();
    usuarioController controlUsuario = new usuarioController();
    List<usuario>usuarios = controlUsuario.listarUsuarios("");
    clienteController controlCliente = new clienteController();
    List<cliente>clientes = controlCliente.listarClientes("");
    reservacionController controlReserv = new reservacionController();
    List<reservacion> reservaciones = controlReserv.listarReservaciones("");
    cliente_HabController controlCliHab = new cliente_HabController();
    List<cliente_Hab> clienteHabitaciones = controlCliHab.listarCliente_Hab("");
    habitacionController controlHab = new habitacionController();
    List<habitacion> habitaciones = controlHab.listarHabitacion("");
    visitaController controlVisitas = new visitaController();
    List<visita> visitas = controlVisitas.listarVisitas("");
    consumoController controlConsumo = new consumoController();
    List<consumo> consumos = controlConsumo.listarConsumo("");
    valor_HospController controlValorHops = new valor_HospController();
    List<valor_Hosp> valoresHosp = controlValorHops.listarValor_Hosp("");
    boolean bandCliente=false;
    boolean bandHab=false;
    boolean bandPrecioHab=false;
    boolean bandReserv=false;
    boolean bandVisitas=false;
    boolean bandUsuarios=false;
    boolean bandSubHab=false;
    boolean upRes=false;
    boolean upUser=false;
    int[] anchosTblCliente = {50,100,100,50,50,100,100};
//    { "Id", "Cliente", "Tipo","Dirección","Habitación", "Fecha Ingreso", "Fecha Salida","Dias", "Consumo","Observaciones" };
    int[] anchosTblVisita = {10,200,50,200,20,150,150,10,50,150};
//    {"Cédula", "Nombres","Tipo","Check-in","Check-out", "Días", "Costo $","$ Extra", "#Visita"};
    int[] anchosTblClienteHab = {40,100,15,70,70,5,15,15,15,10};
//    { "Id","Hab.", "Cliente", "Check-in", "Check-out","Días", "Cant. us.","$ Total" ,"$ Abono"};
    int[] anchosTblReservacion = {3,20,160,100,100,20,120,40,50};
    //String[] titulos = { "NickName", "Nombres", "Cargo", "Clave", "Cliente", "Habitacion", "Precios", "Reservacion", "Visita", "Usuarios"};
    int[] anchosTblUsuario = {50,120,100,70,30,50,40,60,20,30};
    //String[] titulos = {"Habitación","Descripción", "Costo $"};
    int[] anchosTblPrecioHosp = {50,100,100};
    public JButton[] botonesMenu(){
        JButton[] botones={btnCliente, btnHab, btnReserv, btnVisitas, btnPrecioHab, btnUsuarios};
        return botones;
    }
    public String[] imagenesMenu(){
        String[] imagenes={"cliente1.png", "hab1.png", "reserv1.png", "visita1.png", "valorHosp1.png", "user1.png",};
        return imagenes;
    }
    
    public boolean[] banderasMenu(){
        boolean[] banderasMenu={bandCliente, bandHab, bandReserv, bandVisitas, bandPrecioHab, bandUsuarios, bandSubHab};
        return banderasMenu;
    }
    
    public JPanel[] paneles(){
        JPanel[] paneles={panelCliente, panelHab, panelRes, panelVisita, panelPrecioHosp, panelUsuario, subPanelHab};
        return paneles;
    }
    
    public void actMenu(){
        actPaneles();
        for (int i = 0; i < imagenesMenu().length; i++) {
            if(!banderasMenu()[i]){
                metodo.cambiarImgBoton(botonesMenu()[i], imagenesMenu()[i]);
            }
        }
    }
    public void actPaneles(){
        for (int i = 0; i < paneles().length; i++) {
            paneles()[i].setVisible(banderasMenu()[i]);
        }
    }
    public JTextField[] txtCliente(){
        JTextField[] txtCliente={txtCedulaCliente, txtNombreCliente, txtDireccionCliente, txtTelefonoCliente, txtCorreoCliente, txtObsCliente};
        return txtCliente;
    }
    public JTextField[] txtClienteHab(){
        JTextField[] txtCliente={txtCedulaClientHab, txtNombresClientHab, txtDireccionClientHab, txtTelefonoClientHab, txtCorreoClientHab, txtObservClientHab};
        return txtCliente;
    }
    public JTextField[] txtClienteRes(){
        JTextField[] txtCliente={txtCedulaRes, txtNombresRes, txtDireccionRes, txtTelefonoRes, txtCorreoRes, txtObservRes};
        return txtCliente;
    }
    
    public JTextField[] txtUsuarios(){
        JTextField[] txtUsuario={txtNombreUsuario, txtClaveUsuario, txtCargoUsuario};
        return txtUsuario;
    }
    
    public JButton[] botonHab(){
        JButton[] botonHab={btnHab1, btnHab2, btnHab3, btnHab4, btnHab5, btnHab6, btnHab7, btnHab8, btnHab9, btnHab10};
        return botonHab;
    }
    public JLabel[] labelHab(){
        JLabel[] labelHab={lblHab1, lblHab2, lblHab3, lblHab4, lblHab5, lblHab6, lblHab7, lblHab8, lblHab9, lblHab10};
        return labelHab;
    }
    
    public JLabel[][] labelRes(){
        JLabel[][] labelRes={{lblRes00, lblRes01, lblRes02, lblRes03, lblRes04, lblRes05, lblRes06, lblRes07},{lblRes10, lblRes11, lblRes12, lblRes13, lblRes14, lblRes15, lblRes16, lblRes17},{lblRes20, lblRes21, lblRes22, lblRes23, lblRes24, lblRes25, lblRes26, lblRes27},{lblRes30, lblRes31, lblRes32, lblRes33, lblRes34, lblRes35, lblRes36, lblRes37},{lblRes40, lblRes41, lblRes42, lblRes43, lblRes44, lblRes45, lblRes46, lblRes47},{lblRes50, lblRes51, lblRes52, lblRes53, lblRes54, lblRes55, lblRes56, lblRes57},{lblRes60, lblRes61, lblRes62, lblRes63, lblRes64, lblRes65, lblRes66, lblRes67},{lblRes70, lblRes71, lblRes72, lblRes73, lblRes74, lblRes75, lblRes76, lblRes77},{lblRes80, lblRes81, lblRes82, lblRes83, lblRes84, lblRes85, lblRes86, lblRes87},{lblRes90, lblRes91, lblRes92, lblRes93, lblRes94, lblRes95, lblRes96, lblRes97},{lblRes100, lblRes101, lblRes102, lblRes103, lblRes104, lblRes105, lblRes106, lblRes107}};
        return labelRes;
    }
    public JLabel[][] labelResF(){
        JLabel[][] labelResF={{lblResF00, lblResF01, lblResF02, lblResF03, lblResF04, lblResF05, lblResF06, lblResF07},{lblResF10, lblResF11, lblResF12, lblResF13, lblResF14, lblResF15, lblResF16, lblResF17},{lblResF20, lblResF21, lblResF22, lblResF23, lblResF24, lblResF25, lblResF26, lblResF27},{lblResF30, lblResF31, lblResF32, lblResF33, lblResF34, lblResF35, lblResF36, lblResF37},{lblResF40, lblResF41, lblResF42, lblResF43, lblResF44, lblResF45, lblResF46, lblResF47},{lblResF50, lblResF51, lblResF52, lblResF53, lblResF54, lblResF55, lblResF56, lblResF57},{lblResF60, lblResF61, lblResF62, lblResF63, lblResF64, lblResF65, lblResF66, lblResF67},{lblResF70, lblResF71, lblResF72, lblResF73, lblResF74, lblResF75, lblResF76, lblResF77},{lblResF80, lblResF81, lblResF82, lblResF83, lblResF84, lblResF85, lblResF86, lblResF87},{lblResF90, lblResF91, lblResF92, lblResF93, lblResF94, lblResF95, lblResF96, lblResF97},{lblResF100, lblResF101, lblResF102, lblResF103, lblResF104, lblResF105, lblResF106, lblResF107}};
        return labelResF;
    }
    
    public void limpiarTxtCliente(boolean cedula){
        if (cedula) {
            for (int i = 0; i < txtCliente().length; i++) {
                txtCliente()[i].setText("");
            }
        }else{
            for (int i = 1; i < txtCliente().length; i++) {
                txtCliente()[i].setText("");
            }
        }
        metodo.cambiarImgLbl(lblVistoCedulaCliente, "");
    }
    public void limpiarTxtClienteHab(boolean cedula, String numhab){
        if (cedula) {
            for (int i = 0; i < txtClienteHab().length; i++) {
                txtClienteHab()[i].setText("");
            }
        }else{
            for (int i = 1; i < txtClienteHab().length; i++) {
                txtClienteHab()[i].setText("");
            }
        }
        metodo.cambiarImgLbl(lblVistoClienteHab, "");
        txtAbonoClientHab.setText("0.00");
        cmbTipoCliHab.setSelectedIndex(0);
        String tipo = cmbTipoCliHab.getSelectedItem().toString();
        double costo = Double.parseDouble(controlValorHops.buscarValorHosp(tipo, valoresHosp, numhab).getCosto());
        double cantDias = metodo.cantDias(metodo.DateAString(datChosFechaIngHab.getDate()), metodo.DateAString(datChosFechaSalHab.getDate()));
        lblCostoIndCli.setText("$"+metodo.redondearCerDer(costo*cantDias, 2));
        //lblVistoClienteHab.setText("");
    }
    public void limpiarTxtClienteRes(boolean cedula, String numhab){
        if (cedula) {
            for (int i = 0; i < txtClienteRes().length; i++) {
                txtClienteRes()[i].setText("");
            }
        }else{
            for (int i = 1; i < txtClienteRes().length; i++) {
                txtClienteRes()[i].setText("");
            }
        }
        metodo.cambiarImgLbl(lblVistoCedulaRes, "");
        cmbHabRes.setSelectedIndex(0);
        spinCantAdultRes.setValue(1);
        spinCantNiñoRes.setValue(0);
        spinCantDiasRes.setValue(1);
        txtAbonoRes.setText("0.00");
        Date fecha = new Date();
        txtHoraRes.setText(metodo.de24A12Horas(fecha.getHours()+":"+fecha.getMinutes()));
        choosFechIngRes.setDate(fecha);
        fecha.setTime(fecha.getTime()+(24*60*60*1000));
        choosFechSalRes.setDate(fecha);
        String cost = controlReserv.calcCostRes(Integer.parseInt(spinCantAdultRes.getValue().toString()), Integer.parseInt(spinCantNiñoRes.getValue().toString()), Integer.parseInt(spinCantDiasRes.getValue().toString()), valoresHosp, numhab);
        lblTotalRes.setText(cost);
    }
    
    public void limpiarTxtUsuario(){
        txtNombreUsuario.setText("");
        txtClaveUsuario.setText("");
        txtCargoUsuario.setText("");
        txtNickNameUsuario.setText("");
        chBoxCliente.setSelected(false);
        chBoxHab.setSelected(false);
        chBoxReservacion.setSelected(false);
        chBoxValorHosp.setSelected(false);
        chBoxVisitas.setSelected(false);
        chBoxValorHosp.setSelected(false);
        metodo.enfocarTxt(txtNombreUsuario);
    }
    
    public void verCampRes(boolean ver){
        lblCedulaRes.setVisible(ver);
        txtCedulaRes.setVisible(ver);
        lblNombresRes.setVisible(ver);
        txtNombresRes.setVisible(ver);
        lblDireccionRes.setVisible(ver);
        txtDireccionRes.setVisible(ver);
        lblTotalRes.setVisible(ver);
        lblCorreoRes.setVisible(ver);
        txtCorreoRes.setVisible(ver);
        lblTelefonoRes.setVisible(ver);
        txtTelefonoRes.setVisible(ver);
        lblObservRes.setVisible(ver);
        txtObservRes.setVisible(ver);
        lblCheckInRes.setVisible(ver);
        choosFechIngRes.setVisible(ver);
        lblHabRes.setVisible(ver);
        cmbHabRes.setVisible(ver);
        lblCantAdulRes.setVisible(ver);
        spinCantAdultRes.setVisible(ver);
        lblCantNiñosRes.setVisible(ver);
        spinCantNiñoRes.setVisible(ver);
        lblCatDiasRes.setVisible(ver);
        spinCantDiasRes.setVisible(ver);
        lblCheckOutRes.setVisible(ver);
        choosFechSalRes.setVisible(ver);
        lblAbonoRes.setVisible(ver);
        txtAbonoRes.setVisible(ver);
        lblHoraRes.setVisible(ver);
        txtHoraRes.setVisible(ver);
        //btnHoraRes.setVisible(ver);
        btnGuardarRes.setVisible(ver);
    }
    
    public void actBtnHab(){
        Date hora = new Date();
        String estadoHab = "";
        for (int i = 0; i < botonHab().length; i++) {
            metodo.actEstHab((i+1)+"", reservaciones, clienteHabitaciones, clientes, hora, habitaciones);
            
        }
        habitaciones = controlHab.listarHabitacion("");
        clientes = controlCliente.listarClientes("");
        clienteHabitaciones = controlCliHab.listarCliente_Hab("");
        for (int i = 0; i < labelHab().length; i++) {
            estadoHab = controlHab.estadoHab((i+1)+"", habitaciones);
            //System.out.println((i+1)+" - "+estadoHab);
            if (!estadoHab.equals("No Disponible")) {
                estadoHab = controlHab.estadoHab((i+1)+"", habitaciones)+"♦"+metodo.obtenerEstado((i+1), hora, reservaciones, clienteHabitaciones, visitas, clientes, consumos);
                //System.out.println((i+1)+" - "+estadoHab+" - "+estadoHab.split("♦").length);
                if (estadoHab.split("♦").length>3) {
                    botonHab()[i].setToolTipText(estadoHab.split("♦")[4]);
                }else{
                    botonHab()[i].setToolTipText(estadoHab.split("♦")[2]);
                }
                
            }else{
                botonHab()[i].setToolTipText("No disponible - "+controlHab.buscarHabitacionPorId((i+1)+"", habitaciones).getObservaciones());
            }
            botonHab()[i].setToolTipText(botonHab()[i].getToolTipText().toString()+" - Cap. Max: "+controlHab.buscarHabitacionPorId((i+1)+"", habitaciones).getCapacidad()+" us.");
           // System.out.println("*"+estadoHab);
            metodo.cambiarImgLbl(labelHab()[i], controlHab.imgEstadoHab((i+1)+"", habitaciones, 1));   
        }
    }
    
    public void txtBtnHab(){
        for (int i = 0; i < botonHab().length; i++) {
            botonHab()[i].setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*29/10));
            botonHab()[i].setBorder(null);
            botonHab()[i].setContentAreaFilled(false);
            if(i+1==10){
                botonHab()[i].setText((i+1)+"");
                labelHab()[i].setToolTipText((i+1)+"");
            }else{
                botonHab()[i].setText("0"+(i+1));
                labelHab()[i].setToolTipText("0"+(i+1)+"");
            }
        }
    }
    
    public void acHab(int numHab){
        String estado = controlHab.estadoHab(numHab+"", habitaciones);
        String[] opDis={"Ocupar", "Deshabilitar", "Cambiar capacidad máx"};
        String[] opOc={"Ver huéspedes","Realizar check-out a todos los huéspedes", "Cambiar capacidad máx"};
        String[] opNoDis={"Habilitar", "Cambiar capacidad máx"};
        String[] opRes={"Aplicar Reserva", "Eliminar Reserva","Cambiar capacidad máx"};
        String opcion="";
        switch(estado){
            case "Disponible":{
                opcion = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Habitación "+numHab+" - "+estado,metodo.verde,"times new roman",metodo.superTitulo,true, false, false)+"\n"+metodo.personalizarMsj("¿Qué desea hacer?","","times new roman",metodo.titulo1,true, false, false), "Habitación "+numHab, metodo.pregunta, opDis, opDis[0]);
                acHab2(numHab, opcion);
                break;
            }
            case "Ocupado":{
                opcion = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Habitación "+numHab+" - "+estado,metodo.rojo,"times new roman",metodo.superTitulo,true, false, false)+"\n"+metodo.personalizarMsj("¿Qué desea hacer?","","times new roman",metodo.titulo1,true, false, false), "Habitación "+numHab, metodo.pregunta, opOc, opDis[0]);
                acHab2(numHab, opcion);
                break;
            }
            case "No Disponible":{
                opcion = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Habitación "+numHab+" - "+estado,metodo.gris,"times new roman",metodo.superTitulo,true, false, false)+"\n"+metodo.personalizarMsj("¿Qué desea hacer?","","times new roman",metodo.titulo1,true, false, false), "Habitación "+numHab, metodo.pregunta, opNoDis, opDis[0]);
                acHab2(numHab, opcion);
                break;
            }
            case "Reservado":{
                opcion = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Habitación "+numHab+" - "+estado,metodo.naranja,"times new roman",metodo.superTitulo,true, false, false)+"\n"+metodo.personalizarMsj("¿Qué desea hacer?","","times new roman",metodo.titulo1,true, false, false), "Habitación "+numHab, metodo.pregunta, opRes, opDis[0]);
                acHab2(numHab, opcion);
                break;
            }
            
        }
    }
    
    public void acHab2(int numHab, String opcion){
        String estado = controlHab.estadoHab(numHab+"", habitaciones);
        switch(opcion){
            case "Ver huéspedes":{
                metodo.cambiarImgLbl(lblFondoPanelHab, "subFondoPanel.png");
                lblTituloPanelHab.setText("Habitación N° "+numHab+" - "+estado);
                bandCliente=false;
                bandHab=true;
                bandPrecioHab=false;
                bandReserv=false;
                bandVisitas=false;
                bandUsuarios=true;
                bandSubHab=true;
                actPaneles();
                List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                String tipo = cmbTipoCliHab.getSelectedItem().toString();
                double costo = Double.parseDouble(controlValorHops.buscarValorHosp(tipo, valoresHosp, numHab+"").getCosto());
                lblCostoIndCli.setText("$"+metodo.redondearCerDer(costo, 2));
                lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                metodo.enfocarTxt(txtCedulaClientHab);
                break;
            }
            case "Ocupar":{
                metodo.cambiarImgLbl(lblFondoPanelHab, "subFondoPanel.png");
                lblTituloPanelHab.setText("Habitación N° "+numHab+" - "+estado);
                bandCliente=false;
                bandHab=true;
                bandPrecioHab=false;
                bandReserv=false;
                bandVisitas=false;
                bandUsuarios=false;
                bandSubHab=true;
                actPaneles();
                List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                String tipo = cmbTipoCliHab.getSelectedItem().toString();
                double costo = Double.parseDouble(controlValorHops.buscarValorHosp(tipo, valoresHosp, numHab+"").getCosto());
                lblCostoIndCli.setText("$"+metodo.redondearCerDer(costo, 2));
                metodo.enfocarTxt(txtCedulaClientHab);
                break;
            }
            case "Deshabilitar":{
                if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de deshabilitar la habitación N° "+numHab+"?", "", "times new roman", metodo.titulo1, false, false, false), "Aviso", metodo.peligro)) {
                    String razon ="";
                    razon = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Escriba una o varias razones para deshabilitar la habitación N° "+numHab, metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Habitación N° "+ numHab, metodo.pregunta, null, "");
                    if (razon!=null) {
                        metodo.mensajeDialogo(metodo.personalizarMsj("Habitación N° "+numHab+" deshabilitada con éxito", metodo.verde, "times new roman", metodo.titulo1, false, false, false), "Información", metodo.informacion);
                        controlHab.actualizarEstadoHabitacion(numHab+"", "No Disponible");
                        controlHab.actualizarObservHabitacion(numHab+"", razon);
                        habitaciones = controlHab.listarHabitacion("");
                        actBtnHab();
                        contHabRes = 0;
                        tiempoHabRes.start();
                    }
                }
                break;
            }
            case "Habilitar":{
                if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de habilitar esta habitación?", "", "times new roman", metodo.titulo1, false, false, false), "Habitación "+numHab, metodo.pregunta)) {
                    controlHab.actualizarEstadoHabitacion(numHab+"", "Disponible");
                    controlHab.actualizarObservHabitacion(numHab+"", "");
                    habitaciones = controlHab.listarHabitacion("");
                    actBtnHab();
                    contHabRes = 0;
                    tiempoHabRes.start();
                    metodo.mensajeDialogo(metodo.personalizarMsj("Habitación N° "+numHab+" habilitada con éxito", metodo.verde, "times new roman", metodo.titulo1, false, false, false), "Información", metodo.informacion);
                }
                break;
            }
            case "Cambiar capacidad máx":{
                if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Capacidad máxima actual: ","", "times new roman", metodo.titulo1, false, false, false)+"\n"+metodo.personalizarMsj(controlHab.buscarHabitacionPorId(numHab+"", habitaciones).getCapacidad()+" huéspedes", metodo.azul, "times new roman", metodo.superTitulo, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Desea actualizar esta capacidad?","", "times new roman", metodo.titulo1, false, false, false), "Habitación "+numHab, metodo.pregunta)) {
                    String newCapacidad = "";
                    String[] opCapMax = new String[30];
                    for (int i = 0; i < opCapMax.length; i++) {
                        opCapMax[i]=(i+1)+" huéspedes";
                    }
                    newCapacidad = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Elija la nueva capacidad máxima","","times new roman",metodo.titulo1,false,false,false), "Habitación "+numHab, metodo.pregunta, opCapMax, opCapMax[0]);
                    if (!newCapacidad.equals("null")) {
                        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Esta seguro de cambiar la capacidad máxima de "+controlHab.buscarHabitacionPorId(numHab+"", habitaciones).getCapacidad()+" huéspedes a "+newCapacidad+"?","", "times new roman", metodo.titulo1, false, false, false), "Habitación "+numHab, metodo.pregunta)) {
                            controlHab.actualizarCapHabitacion(numHab+"", newCapacidad.split(" ")[0]);
                            habitaciones=controlHab.listarHabitacion("");
                            lblCantHuesp1.setText(metodo.convertToMultiline(controlHab.capacidadMax(habitaciones, clienteHabitaciones)));
                            metodo.mensajeDialogo(metodo.personalizarMsj("Capacidad máxima actualizada con éxito!","", "times new roman", metodo.titulo1, false, false, false), "Información", metodo.pregunta);
                            botonHab()[numHab-1].setToolTipText(botonHab()[numHab-1].getToolTipText().split("Cap. Max:")[0]+" Cap. Max: "+controlHab.buscarHabitacionPorId(numHab+"", habitaciones).getCapacidad()+" us.");
                        }
                    }
                    
                }
                break;
            }
            case "Realizar check-out a todos los huéspedes":{
                int contCheckOut = 0;
                if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Huéspedes registrados en la Habitación N°"+numHab, "", "times new roman", metodo.titulo1, true, false, false)+":\n\n"+controlCliHab.cantHuespHab3(numHab+"", clienteHabitaciones, new Date(), visitas, clientes, consumos, valoresHosp)+"\n"+metodo.personalizarMsj("¿Desea realizar el check-out de todos los huéspedes?", "", "times new roman", metodo.titulo2, true, false, false), "Check-out", metodo.pregunta)) {
                    List<cliente_Hab> listCliHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                    for (int i = 0; i < listCliHab.size(); i++) {
                        if(controlCliHab.actualizarFechaSalClienteHab(numHab+"", metodo.DateAString(new Date()), listCliHab.get(i).getId_visita(), visitas, valoresHosp)&&
                        controlCliHab.actualizarEstadoClienteHab(numHab+"", new Date().getHours()+":"+new Date().getMinutes(), listCliHab.get(i).getId_visita())&&
                        controlVisitas.actualizarFechaSal(metodo.DateAString(new Date()), numHab+"", listCliHab.get(i).getId_visita(), visitas, valoresHosp)){
                            contCheckOut++;
                            //metodo.mensajeDialogo(metodo.personalizarMsj("Se realizó el check-out al huésped con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                        }
                    }
                    clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                    visitas = controlVisitas.listarVisitas("");
                    List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                    metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                    lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                    actBtnHab();
                    contHabRes = 0;
                    tiempoHabRes.start();
                    metodo.mensajeDialogo(metodo.personalizarMsj("Se realizó el check-out a "+contCheckOut+" huésped(es) con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                }
                break;
            }
            case "Aplicar Reserva":{
                reservacion r = controlReserv.buscarResFech(numHab+"", new Date(), reservaciones);
                cliente cl = controlCliente.buscarCliente(r.getId_cliente(), clientes);
                int idVisita = visitas.size()+1;
                System.out.println(r.getFecha_sal()+" fecha");
                double cantDias = metodo.cantDias(
                        metodo.DateAString(new Date()),
                        r.getFecha_sal());
                double precioUnit = Double.parseDouble(controlValorHops.buscarValorHosp("Adulto", valoresHosp, numHab+"").getCosto());
                double totalConsumo = cantDias*precioUnit;
                int cantAdult = Integer.parseInt(r.getCant_us().split(" ")[0]);
                int cantNiñ = Integer.parseInt(r.getCant_us().split(" ")[3]);
                controlHab.actualizarEstadoHabitacion(numHab+"", "Ocupado");
                visita vis = new visita(idVisita+"", r.getId_cliente(), r.getNombres(), r.getDireccion(), numHab+"", metodo.DateAString(new Date()), r.getFecha_sal(), cantDias+"", totalConsumo+"", r.getObservaciones(),"Adulto");
                cliente_Hab cliHab = new cliente_Hab("", r.getId_cliente(), numHab+"", totalConsumo+"",metodo.DateAString(new Date()),idVisita+"",r.getFecha_sal(),r.getHora_salida(),txtAbonoClientHab.getText(),metodo.deDateAHora24(new Date()));
                if (controlCliHab.ingresarClienteHab(cliHab)&&controlVisitas.ingresarVisitas(vis)) {
                    visitas = controlVisitas.listarVisitas("");
                    clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                    metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHabitaciones, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                    actBtnHab();
                    if (cantAdult-1==0) {
                        if (cantNiñ==0) {
                            metodo.mensajeDialogo(metodo.personalizarMsj("Reserva aplicada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                        }else{
                            metodo.mensajeDialogo(metodo.personalizarMsj("Reserva aplicada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Aún falta ingresar "+cantNiñ+" niño(s)", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Información", metodo.informacion);                            
                        }
                    }else{
                        if (cantNiñ==0) {
                            metodo.mensajeDialogo(metodo.personalizarMsj("Reserva aplicada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Aún falta ingresar "+(cantAdult-1)+" adulto(s)", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Información", metodo.informacion);
                        }else{
                            metodo.mensajeDialogo(metodo.personalizarMsj("Reserva aplicada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Aún falta ingresar "+(cantAdult-1)+" adulto(s) y "+cantNiñ+" niño(s)", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Información", metodo.informacion);                            
                        }
                    }
                }
                acHab2(numHab, "Ocupar");
                contHabRes = 0;
                tiempoHabRes.start();
                break;
            }
            case "Eliminar Reserva":{
                reservacion r = controlReserv.buscarResFech(numHab+"", new Date(), reservaciones);
                if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Desea eliminar la reservación en la Habitación N°"+numHab+" realizada por", metodo.rojo, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj(r.getNombres()+" por "+r.getCant_dias()+" día(s)?", metodo.negro, "times new roman", metodo.titulo1, true, false, false), "Eliminar Reservación", metodo.pregunta)) {
                    if (controlReserv.eliminarReservaciones(r.getId())) {
                        metodo.mensajeDialogo(metodo.personalizarMsj("Reserva eliminada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                        reservaciones = controlReserv.listarReservaciones("");
                        contHabRes=0;
                        tiempoHabRes.start();
                        actBtnHab();
                    }
                }
                break;
            }
        }
        
    }
    private void actValorHosp(String numHab) {
        String precioHospAdult = controlValorHops.buscarValorHosp("Adulto", valoresHosp, numHab+"").getCosto();
        String precioHospNiño = controlValorHops.buscarValorHosp("Niño", valoresHosp, numHab+"").getCosto();
        //lblDetalleHabSelecPrecioHosp.setText("$ "+metodo.redondearCerDer(Double.parseDouble(precioHospAdult), 2));
        //txtPrecioHospAdult.setText(metodo.redondearCerDer(Double.parseDouble(precioHospAdult), 2));
        //lblPrecioHospHabPrecioHosp.setText("$ "+metodo.redondearCerDer(Double.parseDouble(precioHospNiño), 2));
        //txtPrecioHospNiño.setText(metodo.redondearCerDer(Double.parseDouble(precioHospNiño), 2));
    }

        
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        user = controlUsuario.usuarioActivo(usuarios);
        t = Toolkit.getDefaultToolkit();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        anchoVentana = screenSize.width; 
        altoVentana = screenSize.height-metodo.tamaños(anchoVentana, 260);
        //anchoVentana = 1600; altoVentana = 900-metodo.tamaños(anchoVentana, 260);;
        //lblFondo2.setBounds(0,0,anchoVentana-5,altoVentana-30);
        //metodo.cambiarImgLbl(lblFondo2, "fondo2.png");
        hipVen = Math.sqrt((anchoVentana*anchoVentana)+(altoVentana*altoVentana));
        tamañoLetra = (int)metodo.redondear(hipVen*0.0166473654702487,0);
        System.out.println(anchoVentana +" - "+ altoVentana);
        this.setBounds(0, 0, anchoVentana, altoVentana);
        metodo.centrarVentana(this, false);
        lblFondoPrincipal.setBounds(0, 0, anchoVentana, altoVentana-35);
        metodo.cambiarImgLbl(lblFondoPrincipal, "Fondo1.jpg");
        cantImgFondo=0;
        boolean cantImgF=true;
        while(cantImgF){
            if(metodo.comprobarArchivo("Fondo"+(cantImgFondo+1)+".jpg")){
                cantImgFondo=cantImgFondo+1;
            }else{
                cantImgF=false;
            }
        }
        tiempoCambiarFondo = new Timer(tiempoCambiarFondoSeg*1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contImgFondo++;
                if (contImgFondo<=cantImgFondo) {
                    metodo.cambiarImgLbl(lblFondoPrincipal, "Fondo"+contImgFondo+".jpg");
                }else{
                    metodo.cambiarImgLbl(lblFondoPrincipal, "Fondo1.jpg");
                    contImgFondo=1;
                }
                actBtnHab();
            }
        });
        tiempoHabRes = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                try {
                    //System.out.println("fila: "+i+"-"+labelRes().length);
                    metodo.mostrarHabRes2(contHabRes, labelRes(), labelResF(), lblFondoPanelRes, metodo.DateAString(fechaRes), reservaciones, clienteHabitaciones, visitas, habitaciones, clientes, consumos, tamañoLetra);
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                    if (contHabRes==(labelRes().length-1)) {
                        tiempoHabRes.stop();
                    }else{
                        contHabRes=contHabRes+1;
                        tiempoHabRes.start();
                    }
                
            }
        });
        /*
        tiempoAct = new Timer(30000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tiempoHabRes.start();
                actBtnHab();
            }
        });
        */
        lblAvatar.setBounds(0, 0, metodo.tamaños(anchoVentana, 1380), metodo.tamaños(altoVentana, 2072));
        System.out.println(tamañoLetra);
        metodo.cambiarImgLbl(lblAvatar, "userAvatar.png");
        lblFondo2.setBounds(metodo.tamaños(anchoVentana, 16), metodo.tamaños(altoVentana, 2072), metodo.tamaños(anchoVentana, 1354), metodo.tamaños(altoVentana, 1380));
        metodo.cambiarImgLbl(lblFondo2, "franja.png");
        lblNameUser.setBounds(metodo.tamaños(anchoVentana, 16), metodo.tamaños(altoVentana, 2072), metodo.tamaños(anchoVentana, 1354), metodo.tamaños(altoVentana, 1380));
        lblNameUser.setFont(new Font("Times New Roman", 3, tamañoLetra));
        //lblNameUser.setText(metodo.convertToMultiline(user.getNombres()+"\n"+user.getCargo()));
        //lblNameUser.setText(metodo.convertToMultiline(user.getNombres()));
        lblNameUser.setText("<html> <p style=\"font-family: times new roman; font-size: "+(tamañoLetra*0.9)+"px; text-align: center;\">"+user.getNombres()+"\n"+user.getCargo()+"</p></html>");
        lblFondoPanelPrincipal.setBounds(metodo.tamaños(anchoVentana, 1458), metodo.tamaños(altoVentana, 1075), metodo.tamaños(anchoVentana, 8490), metodo.tamaños(altoVentana, 8593));
        metodo.cambiarImgLbl(lblFondoPanelPrincipal, "FondoPanel.png");
        //btnCliente.setBounds(metodo.tamaños(anchoVentana, 1469), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 1490), metodo.tamaños(altoVentana, 894));
        /*
        for (int i = 0; i < botonesMenu().length; i++) {
            botonesMenu()[i].setBounds(0, 0, metodo.tamaños(anchoVentana, 1406), metodo.tamaños(altoVentana, 894));
        }
        */
        metodo.botonesNoVisibles(botonesMenu());
        metodo.mostrarMenu(metodo.tamaños(anchoVentana, 1469), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 1406), botonesMenu(), user, metodo.tamaños(anchoVentana, 1406), metodo.tamaños(altoVentana, 894));
        metodo.cambiarImgBoton(botonesMenu(), imagenesMenu());
        metodo.cambiarMouseBtn(metodo.mano, botonesMenu());
        panelCliente.setBounds(lblFondoPanelPrincipal.getBounds().x, lblFondoPanelPrincipal.getBounds().y, lblFondoPanelPrincipal.getBounds().width, lblFondoPanelPrincipal.getBounds().height-1);
        panelHab.setBounds(lblFondoPanelPrincipal.getBounds().x, lblFondoPanelPrincipal.getBounds().y, lblFondoPanelPrincipal.getBounds().width, lblFondoPanelPrincipal.getBounds().height-1);
        lblTituloPanelCliente.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 8281), metodo.tamaños(altoVentana, 392));
        lblTituloPanelCliente.setText("Clientes");
        lblTituloPanelCliente.setFont(new Font("Times New Roman", 3, tamañoLetra));
        metodo.panelInvisible(paneles());
        actPaneles();
        lblSubFondoPanelCliente.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 8281), metodo.tamaños(altoVentana, 2412));
        metodo.cambiarImgLbl(lblSubFondoPanelCliente, "subFondoPanel.png");
        lblSubFondoTablaPanelCliente.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 201)+metodo.tamaños(altoVentana, 2412), metodo.tamaños(anchoVentana, 8281), metodo.tamaños(altoVentana, 5950));
        metodo.cambiarImgLbl(lblSubFondoTablaPanelCliente, "subFondoPanel.png");
        lblCedulaCliente.setBounds(metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 854), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblCedulaCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblNombresCliente.setBounds(metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 1407), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblNombresCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblDireccionCliente.setBounds(metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblDireccionCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblObservaciones.setBounds(metodo.tamaños(anchoVentana, 3021), metodo.tamaños(altoVentana, 854), metodo.tamaños(anchoVentana, 1042), metodo.tamaños(altoVentana, 402));
        lblObservaciones.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblCorreoCliente.setBounds(metodo.tamaños(anchoVentana, 3906), metodo.tamaños(altoVentana, 1407), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblCorreoCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblTelefonoCliente.setBounds(metodo.tamaños(anchoVentana, 4271), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblTelefonoCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblVistoCedulaCliente.setBounds(metodo.tamaños(anchoVentana, 2735), metodo.tamaños(altoVentana, 854), metodo.tamaños(anchoVentana, 208), metodo.tamaños(altoVentana, 402));
        lblVistoCedulaCliente.setFont(new Font("Arial", 0, tamañoLetra));
        txtCedulaCliente.setBounds(metodo.tamaños(anchoVentana, 1146), metodo.tamaños(altoVentana, 854), metodo.tamaños(anchoVentana, 1563), metodo.tamaños(altoVentana, 450));
        txtCedulaCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        txtNombreCliente.setBounds(metodo.tamaños(anchoVentana, 1146), metodo.tamaños(altoVentana, 1407), metodo.tamaños(anchoVentana, 2656), metodo.tamaños(altoVentana, 450));
        txtNombreCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        //.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        txtDireccionCliente.setBounds(metodo.tamaños(anchoVentana, 1146), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 2969), metodo.tamaños(altoVentana, 450));
        txtDireccionCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        txtObsCliente.setBounds(metodo.tamaños(anchoVentana, 4219), metodo.tamaños(altoVentana, 854), metodo.tamaños(anchoVentana, 2708), metodo.tamaños(altoVentana, 450));
        txtObsCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        txtCorreoCliente.setBounds(metodo.tamaños(anchoVentana, 4583), metodo.tamaños(altoVentana, 1407), metodo.tamaños(anchoVentana, 2344), metodo.tamaños(altoVentana, 450));
        txtCorreoCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        txtTelefonoCliente.setBounds(metodo.tamaños(anchoVentana, 4844), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 2083), metodo.tamaños(altoVentana, 450));
        txtTelefonoCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        metodo.siguiente(txtCliente());
        btnGuardarCliente.setBounds(metodo.tamaños(anchoVentana, 7031), metodo.tamaños(altoVentana, 1106), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 1005));
        metodo.cambiarImgBoton(btnGuardarCliente, "aggCliente1.png");
        btnLimpiarCliente.setBounds(metodo.tamaños(anchoVentana, 7734), metodo.tamaños(altoVentana, 1106), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 1005));
        metodo.cambiarImgBoton(btnLimpiarCliente, "limpiar1.png");
        jScrollPane1.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 3618), metodo.tamaños(anchoVentana, 7995), metodo.tamaños(altoVentana, 4633));
        tablaCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
        spnTablaCliente.setBounds(metodo.tamaños(anchoVentana, 7917), metodo.tamaños(altoVentana, 2915), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 402));
        spnTablaCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        spnTablaCliente.setMinimum(tamañoLetra/4);
        spnTablaCliente.setMaximum(tamañoLetra);
        spnTablaCliente.setValue(tamañoLetra/2);
        lblTamanoLetra.setBounds(metodo.tamaños(anchoVentana, 6797), metodo.tamaños(altoVentana, 2915), metodo.tamaños(anchoVentana, 1094), metodo.tamaños(altoVentana, 402));
        lblTamanoLetra.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblBuscarCliente.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 2915), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 402));
        lblBuscarCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtBuscarCliente.setBounds(metodo.tamaños(anchoVentana, 885), metodo.tamaños(altoVentana, 2915), metodo.tamaños(anchoVentana, 1875), metodo.tamaños(altoVentana, 450));
        txtBuscarCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        btnModificarCliente.setBounds(metodo.tamaños(anchoVentana, 2917), metodo.tamaños(altoVentana, 2714), metodo.tamaños(anchoVentana, 365), metodo.tamaños(altoVentana, 704));
        btnEliminarCliente.setBounds(metodo.tamaños(anchoVentana, 3438), metodo.tamaños(altoVentana, 2714), metodo.tamaños(anchoVentana, 365), metodo.tamaños(altoVentana, 704));
        metodo.cambiarImgBoton(btnModificarCliente, "upCliente1.png");
        metodo.cambiarImgBoton(btnEliminarCliente, "delCliente1.png");
        btnEliminarCliente.setVisible(false);
        btnModificarCliente.setVisible(false);
        metodo.distrBtnHab(botonHab(), panelHab, 25,100, 2);
        metodo.distrLblHab(labelHab(), panelHab, 25,100, 2);
        txtBtnHab();
        lblFondoPanelHab.setBounds(metodo.tamaños(anchoVentana, 2135), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 6313), metodo.tamaños(altoVentana, 8291));
        subPanelHab.setBounds(metodo.tamaños(anchoVentana, 2135), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 6313), metodo.tamaños(altoVentana, 8291));
        lblTituloPanelHab.setBounds(metodo.tamaños(anchoVentana, 391), metodo.tamaños(altoVentana, 302), metodo.tamaños(anchoVentana, 5568), metodo.tamaños(altoVentana, 603));
        lblTituloPanelHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/2));
        lblTituloPanelHab.setText("Habitación N° 1 - Disponible");
        lblCedulaClienteHab.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblCedulaClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtCedulaClientHab.setBounds(metodo.tamaños(anchoVentana, 788), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 1068), metodo.tamaños(altoVentana, 450));
        txtCedulaClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        lblVistoClienteHab.setBounds(metodo.tamaños(anchoVentana, 1880), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 208), metodo.tamaños(altoVentana, 402));
        lblVistoClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra));
        lblObservClienteHab.setBounds(metodo.tamaños(anchoVentana, 2923), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 938), metodo.tamaños(altoVentana, 402));
        lblObservClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtObservClientHab.setBounds(metodo.tamaños(anchoVentana, 3913), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 1536), metodo.tamaños(altoVentana, 450));
        txtObservClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        cmbTipoCliHab.setBounds(metodo.tamaños(anchoVentana, 2094), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 725), metodo.tamaños(altoVentana, 402));
        /*for (int i = 0; i < valoresHosp.size(); i++) {
            cmbTipoCliHab.addItem(valoresHosp.get(i).getDescripcion());
        }*/
        // Crear un conjunto para almacenar descripciones únicas
        Set<String> descripcionesUnicas = new HashSet<>();

        for (int i = 0; i < valoresHosp.size(); i++) {
            String descripcion = valoresHosp.get(i).getDescripcion();
            descripcionesUnicas.add(descripcion);
        }

        // Limpiar el JComboBox antes de agregar los elementos únicos
        cmbTipoCliHab.removeAllItems();

        // Agregar las descripciones únicas al JComboBox
        for (String descripcion : descripcionesUnicas) {
            cmbTipoCliHab.addItem(descripcion);
        }
        cmbTipoCliHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblNombresClienteHab.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 1508), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblNombresClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtNombresClientHab.setBounds(metodo.tamaños(anchoVentana, 788), metodo.tamaños(altoVentana, 1508), metodo.tamaños(anchoVentana, 1828), metodo.tamaños(altoVentana, 450));
        txtNombresClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        lblCorreoClienteHab.setBounds(metodo.tamaños(anchoVentana, 2665), metodo.tamaños(altoVentana, 1508), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblCorreoClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtCorreoClientHab.setBounds(metodo.tamaños(anchoVentana, 3190), metodo.tamaños(altoVentana, 1508), metodo.tamaños(anchoVentana, 2259), metodo.tamaños(altoVentana, 450));
        txtCorreoClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        lblDireccionClienteHab.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblDireccionClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtDireccionClientHab.setBounds(metodo.tamaños(anchoVentana, 788), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 1828), metodo.tamaños(altoVentana, 450));
        txtDireccionClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        lblTelefonoClienteHab.setBounds(metodo.tamaños(anchoVentana, 2665), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblTelefonoClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtTelefonoClientHab.setBounds(metodo.tamaños(anchoVentana, 3190), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 1253), metodo.tamaños(altoVentana, 450));
        txtTelefonoClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        lblAbonoClienteHab.setBounds(metodo.tamaños(anchoVentana, 4150), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblAbonoClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        txtAbonoClientHab.setBounds(metodo.tamaños(anchoVentana, 4950), metodo.tamaños(altoVentana, 2010), metodo.tamaños(anchoVentana, 500), metodo.tamaños(altoVentana, 450));
        txtAbonoClientHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        lblFechaIngClienteHab.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 2513), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblFechaIngClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        datChosFechaIngHab.setBounds(metodo.tamaños(anchoVentana, 788), metodo.tamaños(altoVentana, 2513), metodo.tamaños(anchoVentana, 1042), metodo.tamaños(altoVentana, 402));
        datChosFechaIngHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        metodo.establecerFechMin(datChosFechaIngHab, new Date(),0);
        metodo.establecerFechMax(datChosFechaIngHab, new Date(),0);
        metodo.establecerFecha(datChosFechaIngHab, new Date(),0);
        metodo.deshabilitarEscritDateChoos(datChosFechaIngHab);
        lblFechaSalidaClienteHab.setBounds(metodo.tamaños(anchoVentana, 1881), metodo.tamaños(altoVentana, 2513), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
        lblFechaSalidaClienteHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        datChosFechaSalHab.setBounds(metodo.tamaños(anchoVentana, 2617), metodo.tamaños(altoVentana, 2513), metodo.tamaños(anchoVentana, 1094), metodo.tamaños(altoVentana, 402));
        datChosFechaSalHab.setFont(new Font("Times New Roman", 0, tamañoLetra*3/4));
        metodo.establecerFechMin(datChosFechaSalHab, new Date(),1);
        metodo.establecerFecha(datChosFechaSalHab, new Date(), 1);
        metodo.deshabilitarEscritDateChoos(datChosFechaSalHab);
        jScrollPane2.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 3415), metodo.tamaños(anchoVentana, 6068), metodo.tamaños(altoVentana, 4724));
        tablaHab.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
        lblCostoTotalHab.setBounds(metodo.tamaños(anchoVentana, 3713), metodo.tamaños(altoVentana, 2440), metodo.tamaños(anchoVentana, 1736), metodo.tamaños(altoVentana, 252));
        lblCostoTotalHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblCostoHab.setBounds(metodo.tamaños(anchoVentana, 3713), metodo.tamaños(altoVentana, 2740), metodo.tamaños(anchoVentana, 1736), metodo.tamaños(altoVentana, 652));
        lblCostoHab.setFont(new Font("Times New Roman", 3, tamañoLetra*5/2));
        lblCostoHab.setForeground(Color.GREEN);
        lblCostoIndHabCli.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 2983), metodo.tamaños(anchoVentana, 1881), metodo.tamaños(altoVentana, 402));
        lblCostoIndHabCli.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
        lblCostoIndCli.setBounds(metodo.tamaños(anchoVentana, 2000), metodo.tamaños(altoVentana, 2850), metodo.tamaños(anchoVentana, 1881), metodo.tamaños(altoVentana, 602));
        lblCostoIndCli.setFont(new Font("Times New Roman", 3, tamañoLetra*3/2));
        spnTablaClienteHab.setBounds(metodo.tamaños(anchoVentana, 5895), metodo.tamaños(altoVentana, 3100), metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 302));
        spnTablaClienteHab.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
        spnTablaClienteHab.setMinimum(tamañoLetra/4);
        spnTablaClienteHab.setMaximum(tamañoLetra);
        spnTablaClienteHab.setValue(tamañoLetra/2);
        btnGuardarClienteHab.setBounds(metodo.tamaños(anchoVentana, 5550), metodo.tamaños(altoVentana, 1005), metodo.tamaños(anchoVentana, 465), metodo.tamaños(altoVentana, 1004));
        metodo.cambiarImgBoton(btnGuardarClienteHab,  "aggCliente1.png");
        btnLimpiarClienteHab.setBounds(metodo.tamaños(anchoVentana, 5550), metodo.tamaños(altoVentana, 2109), metodo.tamaños(anchoVentana, 465), metodo.tamaños(altoVentana, 904));
        metodo.cambiarImgBoton(btnLimpiarClienteHab, "limpiar1.png");
        lblCantHuesp.setBounds(metodo.tamaños(anchoVentana, 26), metodo.tamaños(altoVentana, 3920), metodo.tamaños(anchoVentana, 1380), metodo.tamaños(altoVentana, 1307));
        metodo.cambiarImgLbl(lblCantHuesp, "franja.png");
        lblCantHuesp1.setBounds(metodo.tamaños(anchoVentana, 26), metodo.tamaños(altoVentana, 3920), metodo.tamaños(anchoVentana, 1380), metodo.tamaños(altoVentana, 1307));
        lblCantHuesp1.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        metodo.actCapMax(lblCantHuesp1, habitaciones, clienteHabitaciones);
        setIconImage(metodo.cambiarIconoVentana("Imagenes/logo huaca.png"));
        panelRes.setBounds(lblFondoPanelPrincipal.getBounds().x, lblFondoPanelPrincipal.getBounds().y, lblFondoPanelPrincipal.getBounds().width, lblFondoPanelPrincipal.getBounds().height-1);
        lblFondoPanelRes.setBounds(metodo.tamaños(anchoVentana, 2135), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 6313), metodo.tamaños(altoVentana, 8291));
        metodo.cambiarImgLbl(lblFondoPanelRes, "subFondoPanel.png");
        metodo.posLblRes(labelRes(), labelResF(), lblFondoPanelRes, 10, 10, anchoVentana);
        btnRegresarDia.setBounds(metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 3786), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 583));
        metodo.cambiarImgBoton(btnRegresarDia, "atras.png");
        btnRegresarSemana.setBounds(metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 4466), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 583));
        metodo.cambiarImgBoton(btnRegresarSemana, "Datras.png");
        btnAvanzarSemana.setBounds(metodo.tamaños(anchoVentana, 8125), metodo.tamaños(altoVentana, 4466), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 583));
        metodo.cambiarImgBoton(btnAvanzarSemana, "Dadelante.png");
        btnAvanzarDia.setBounds(metodo.tamaños(anchoVentana, 8125), metodo.tamaños(altoVentana, 3786), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 583));
        metodo.cambiarImgBoton(btnAvanzarDia, "adelante.png");
        lblCedulaRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 1398), metodo.tamaños(anchoVentana, 625), metodo.tamaños(altoVentana, 388));
        lblCedulaRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        lblFondoPanelRes1.setBounds(metodo.tamaños(anchoVentana, 52), metodo.tamaños(altoVentana, 194), metodo.tamaños(anchoVentana, 2083), metodo.tamaños(altoVentana, 8291));
        metodo.cambiarImgLbl(lblFondoPanelRes1, "subFondoPanel.png");
        txtCedulaRes.setBounds(metodo.tamaños(anchoVentana, 701), metodo.tamaños(altoVentana, 1398), metodo.tamaños(anchoVentana, 1109), metodo.tamaños(altoVentana, 450));
        txtCedulaRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        lblVistoCedulaRes.setBounds(metodo.tamaños(anchoVentana, 1840), metodo.tamaños(altoVentana, 1398), metodo.tamaños(anchoVentana, 208), metodo.tamaños(altoVentana, 388));
        lblVistoCedulaRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra));
        btnAgregarReservacion.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 388), metodo.tamaños(anchoVentana, 469), metodo.tamaños(altoVentana, 874));
        metodo.cambiarImgBoton(btnAgregarReservacion, "aggCliente1.png");
        lblAgregarReservacion.setBounds(metodo.tamaños(anchoVentana, 607), metodo.tamaños(altoVentana, 680), metodo.tamaños(anchoVentana, 1466), metodo.tamaños(altoVentana, 388));
        lblAgregarReservacion.setFont(new Font("Times New Roman", 3, tamañoLetra*85/100));
        lblNombresRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 1745), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 388));
        lblNombresRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        txtNombresRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 2083), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 450));
        txtNombresRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        lblDireccionRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, (2421)), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 388));
        lblDireccionRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        txtDireccionRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 3107-350), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 450));
        txtDireccionRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        lblTelefonoRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 3592-350), metodo.tamaños(anchoVentana, 625), metodo.tamaños(altoVentana, 388));
        lblTelefonoRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        txtTelefonoRes.setBounds(metodo.tamaños(anchoVentana, 729), metodo.tamaños(altoVentana, 3592-350), metodo.tamaños(anchoVentana, 1354), metodo.tamaños(altoVentana, 450));
        txtTelefonoRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        lblCorreoRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 4029-350), metodo.tamaños(anchoVentana, 625), metodo.tamaños(altoVentana, 388));
        lblCorreoRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        txtCorreoRes.setBounds(metodo.tamaños(anchoVentana, 729), metodo.tamaños(altoVentana, 4029-350), metodo.tamaños(anchoVentana, 1354), metodo.tamaños(altoVentana, 450));
        txtCorreoRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        lblObservRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 4466-450), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 388));
        lblObservRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        txtObservRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 4854-500), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 450));
        txtObservRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        lblCheckInRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 5243-450), metodo.tamaños(anchoVentana, 729), metodo.tamaños(altoVentana, 388));
        lblCheckInRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
       // lblCheckOutRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 5631-200), metodo.tamaños(anchoVentana, 729), metodo.tamaños(altoVentana, 388));
        //lblCheckOutRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        choosFechIngRes.setBounds(metodo.tamaños(anchoVentana, 800), metodo.tamaños(altoVentana, 5243-450), metodo.tamaños(anchoVentana, 1263), metodo.tamaños(altoVentana, 388));
        choosFechIngRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        choosFechIngRes.setDate(new Date());
        metodo.deshabilitarEscritDateChoos(choosFechIngRes);
        metodo.establecerFechMin(choosFechIngRes, new Date(), 0);
        //choosFechSalRes.setBounds(metodo.tamaños(anchoVentana, 800), metodo.tamaños(altoVentana, 5631-200), metodo.tamaños(anchoVentana, 1263), metodo.tamaños(altoVentana, 388));
        choosFechSalRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        //lblCantAdulRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 5825), metodo.tamaños(anchoVentana, 625), metodo.tamaños(altoVentana, 388));
        lblCantAdulRes.setBounds(metodo.tamaños(anchoVentana, 734), metodo.tamaños(altoVentana, 5187), metodo.tamaños(anchoVentana, 625), metodo.tamaños(altoVentana, 388));
        lblCantAdulRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        //spinCantAdultRes.setBounds(metodo.tamaños(anchoVentana, 677), metodo.tamaños(altoVentana, 5874), metodo.tamaños(anchoVentana, 339), metodo.tamaños(altoVentana, 291));
        spinCantAdultRes.setBounds(metodo.tamaños(anchoVentana, 906), metodo.tamaños(altoVentana, 5525), metodo.tamaños(anchoVentana, 325), metodo.tamaños(altoVentana, 388));
        spinCantAdultRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        spinCantAdultRes.setValue(1);
        lblCantNiñosRes.setBounds(metodo.tamaños(anchoVentana, 1181), metodo.tamaños(altoVentana, 5187), metodo.tamaños(anchoVentana, 625), metodo.tamaños(altoVentana, 388));
        lblCantNiñosRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        lblCatDiasRes.setBounds(metodo.tamaños(anchoVentana, 1667), metodo.tamaños(altoVentana, 5187), metodo.tamaños(anchoVentana, 417), metodo.tamaños(altoVentana, 388));
        lblCatDiasRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        spinCantNiñoRes.setBounds(metodo.tamaños(anchoVentana, 1342), metodo.tamaños(altoVentana, 5525), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 388));
        spinCantNiñoRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        spinCantNiñoRes.setValue(0);
        spinCantDiasRes.setBounds(metodo.tamaños(anchoVentana, 1719), metodo.tamaños(altoVentana, 5525), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 388));
        spinCantDiasRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        spinCantDiasRes.setValue(1);
        lblCheckOutRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 6014), metodo.tamaños(anchoVentana, 729), metodo.tamaños(altoVentana, 388));
        lblCheckOutRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        choosFechSalRes.setBounds(metodo.tamaños(anchoVentana, 800), metodo.tamaños(altoVentana, 6014), metodo.tamaños(anchoVentana, 1263), metodo.tamaños(altoVentana, 388));
        metodo.establecerFechMin(choosFechSalRes, choosFechIngRes.getDate(), Integer.parseInt(spinCantDiasRes.getValue().toString()));
        Date fecha = choosFechIngRes.getDate();
        fecha.setTime(fecha.getTime()+Integer.parseInt(spinCantDiasRes.getValue().toString())*24*60*60*1000);
        choosFechSalRes.setDate(fecha);
        metodo.deshabilitarEscritDateChoos(choosFechSalRes);
        //metodo.deshabilitarTodasFechasDateChoos(choosFechSalRes);
        lblHoraRes.setBounds(metodo.tamaños(anchoVentana, 802), metodo.tamaños(altoVentana, 6399), metodo.tamaños(anchoVentana, 911), metodo.tamaños(altoVentana, 388));
        lblHoraRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
        txtHoraRes.setBounds(metodo.tamaños(anchoVentana, 882), metodo.tamaños(altoVentana, 6749), metodo.tamaños(anchoVentana, 729), metodo.tamaños(altoVentana, 388));
        txtHoraRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
        txtHoraRes.setText(metodo.de24A12Horas(new Date().getHours()+":"+new Date().getMinutes()));
        txtHoraRes.setEditable(false);
        //btnHoraRes.setBounds(metodo.tamaños(anchoVentana, 1700), metodo.tamaños(altoVentana, 6500), metodo.tamaños(anchoVentana, 391), metodo.tamaños(altoVentana, 728));
        //metodo.cambiarImgBoton(btnHoraRes, "reloj1.png");
        lblTotalRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 7287), metodo.tamaños(anchoVentana, 1432), metodo.tamaños(altoVentana, 975));
        lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*2));
        lblTotalRes.setText(controlReserv.calcCostRes(Integer.parseInt(spinCantAdultRes.getValue().toString()), Integer.parseInt(spinCantNiñoRes.getValue().toString()), Integer.parseInt(spinCantDiasRes.getValue().toString()), valoresHosp, "1"));
        btnGuardarRes.setBounds(metodo.tamaños(anchoVentana, 1563), metodo.tamaños(altoVentana, 7287), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 971));
        metodo.cambiarImgBoton(btnGuardarRes, "guardar1.png");
        metodo.siguiente(txtClienteRes());
        
        metodo.txtEditable(txtClienteRes(), false);
        txtCedulaRes.setEditable(true);
        metodo.deshabilitarEscritJspinn(spinCantDiasRes);
        metodo.deshabilitarEscritJspinn(spinCantAdultRes);
        metodo.deshabilitarEscritJspinn(spinCantNiñoRes);
        /*timePicker1.addEventTimePicker(new EventTimePicker() {
            @Override
            public void timeSelected(String string) {
                txtHoraRes.setText(string);
            }
        });*/
       lblFondoMenuCuadricula.setBounds(metodo.tamaños(anchoVentana, 2135), metodo.tamaños(altoVentana, 0), metodo.tamaños(anchoVentana, 2264), metodo.tamaños(altoVentana, 291));
       metodo.cambiarImgLbl(lblFondoMenuCuadricula, "subFondoPanel.png");
       CuadriculaRes.setBounds(metodo.tamaños(anchoVentana, 2160), metodo.tamaños(altoVentana, 0), metodo.tamaños(anchoVentana, 1142), metodo.tamaños(altoVentana, 291));
       CuadriculaRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
       TablaRes.setBounds(metodo.tamaños(anchoVentana, 3381), metodo.tamaños(altoVentana, 0), metodo.tamaños(anchoVentana, 1042), metodo.tamaños(altoVentana, 291));
       TablaRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
       jScrollPane3.setBounds(metodo.tamaños(anchoVentana, 2252), metodo.tamaños(altoVentana, 437), metodo.tamaños(anchoVentana, 6092), metodo.tamaños(altoVentana, 7961));
       TablaReservacion.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       jScrollPane3.setVisible(false);
       lblHabRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 5187), metodo.tamaños(anchoVentana, 675), metodo.tamaños(altoVentana, 388));
       lblHabRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
       cmbHabRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 5525), metodo.tamaños(anchoVentana, 655), metodo.tamaños(altoVentana, 388));
       cmbHabRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       lblAbonoRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 6399), metodo.tamaños(anchoVentana, 629), metodo.tamaños(altoVentana, 388));
       lblAbonoRes.setFont(new Font("Times New Roman", Font.BOLD, tamañoLetra*3/4));
       txtAbonoRes.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 6749), metodo.tamaños(anchoVentana, 629), metodo.tamaños(altoVentana, 388));
       txtAbonoRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       spinTamTblRes.setBounds(metodo.tamaños(anchoVentana, 7944), metodo.tamaños(altoVentana, 100), metodo.tamaños(anchoVentana, 400), metodo.tamaños(altoVentana, 300));
       spinTamTblRes.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       spinTamTblRes.setValue(tamañoLetra/2);
       tiempoHabRes.start();
       actBtnHab();
       //tiempoAct.start();
       tiempoCambiarFondo.start();
       
       panelVisita.setBounds(lblFondoPanelPrincipal.getBounds().x, lblFondoPanelPrincipal.getBounds().y, lblFondoPanelPrincipal.getBounds().width, lblFondoPanelPrincipal.getBounds().height-1);
       lblSubFondoPanelVisita.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 8281), metodo.tamaños(altoVentana, 8433));
       metodo.cambiarImgLbl(lblSubFondoPanelVisita, "subFondoPanel.png");
       lblBuscarVisita.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 418), metodo.tamaños(anchoVentana, 781), metodo.tamaños(altoVentana, 402));
       lblBuscarVisita.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       txtBuscarVisita.setBounds(metodo.tamaños(anchoVentana, 946), metodo.tamaños(altoVentana, 418), metodo.tamaños(anchoVentana, 2363), metodo.tamaños(altoVentana, 450));
       txtBuscarVisita.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       jScrollPane4.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 888), metodo.tamaños(anchoVentana, 7995), metodo.tamaños(altoVentana, 7633));
       tablaVisita.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       spinTablaVisita.setBounds(metodo.tamaños(anchoVentana, 7917), metodo.tamaños(altoVentana, 418), metodo.tamaños(anchoVentana, 313), metodo.tamaños(altoVentana, 402));
       spinTablaVisita.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       spinTablaVisita.setModel(new javax.swing.SpinnerNumberModel(tamañoLetra/2, tamañoLetra/4, tamañoLetra*2, 1));
       panelPrecioHosp.setBounds(lblFondoPanelPrincipal.getBounds().x, lblFondoPanelPrincipal.getBounds().y, lblFondoPanelPrincipal.getBounds().width, lblFondoPanelPrincipal.getBounds().height-1);
       lblSubFondoPanelPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 8281), metodo.tamaños(altoVentana, 8433));
       metodo.cambiarImgLbl(lblSubFondoPanelPrecioHosp, "subFondoPanel.png");
       //lblTituloPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 885), metodo.tamaños(altoVentana, 1456), metodo.tamaños(anchoVentana, 2083), metodo.tamaños(altoVentana, 971));
       lblTituloPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 3029), metodo.tamaños(altoVentana, 388), metodo.tamaños(anchoVentana, 2431), metodo.tamaños(altoVentana, 583));
       lblTituloPrecioHosp.setFont(new Font("Times New Roman", 3, tamañoLetra*6/4));
       //lblDetalleHabSelecPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 2621), metodo.tamaños(anchoVentana, 2865), metodo.tamaños(altoVentana, 2913));
       //lblDetalleHabSelecPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 1165), metodo.tamaños(anchoVentana, 4375), metodo.tamaños(altoVentana, 1165));
       lblDetalleHabSelecPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 3802), metodo.tamaños(altoVentana, 2330), metodo.tamaños(anchoVentana, 4375), metodo.tamaños(altoVentana, 2233));
       lblDetalleHabSelecPrecioHosp.setFont(new Font("Times New Roman", 2, tamañoLetra));
       //lblHabSeleccionadaPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 5469), metodo.tamaños(altoVentana, 1456), metodo.tamaños(anchoVentana, 2083), metodo.tamaños(altoVentana, 971));
       //lblHabSeleccionadaPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 3281), metodo.tamaños(altoVentana, 1068), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 388));
       lblHabSeleccionadaPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 5000), metodo.tamaños(altoVentana, 1784), metodo.tamaños(anchoVentana, 1979), metodo.tamaños(altoVentana, 388));
       lblHabSeleccionadaPrecioHosp.setFont(new Font("Times New Roman", 3, tamañoLetra));
       //lblPrecioHospHabPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 5052), metodo.tamaños(altoVentana, 2621), metodo.tamaños(anchoVentana, 2865), metodo.tamaños(altoVentana, 2913));
       lblPrecioHospHabPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 1115), metodo.tamaños(anchoVentana, 2804), metodo.tamaños(altoVentana, 538));
       lblPrecioHospHabPrecioHosp.setFont(new Font("Times New Roman", 3, tamañoLetra*9/10));
       //jScrollPane6.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 3689), metodo.tamaños(anchoVentana, 7917), metodo.tamaños(altoVentana, 4466));
       jScrollPane6.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 1650), metodo.tamaños(anchoVentana, 3359), metodo.tamaños(altoVentana, 6408));
       tablaPrecioHosp.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       //spnTablaPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 7760), metodo.tamaños(altoVentana, 3204), metodo.tamaños(anchoVentana, 417), metodo.tamaños(altoVentana, 383));
       spnTablaPrecioHosp.setBounds(metodo.tamaños(anchoVentana, 3181), metodo.tamaños(altoVentana, 1200), metodo.tamaños(anchoVentana, 417), metodo.tamaños(altoVentana, 383));
       spnTablaPrecioHosp.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       spnTablaPrecioHosp.setMinimum(tamañoLetra/4);
       spnTablaPrecioHosp.setMaximum(tamañoLetra);
       spnTablaPrecioHosp.setValue(tamañoLetra/2);
       /*
       txtPrecioHospAdult.setBounds(metodo.tamaños(anchoVentana, 1121), metodo.tamaños(altoVentana, 3221), metodo.tamaños(anchoVentana, 2265), metodo.tamaños(altoVentana, 1713));
       txtPrecioHospAdult.setFont(new Font("Times New Roman", 3, tamañoLetra*9/2));
       txtPrecioHospAdult.setVisible(false);
       txtPrecioHospNiño.setBounds(metodo.tamaños(anchoVentana, 5652), metodo.tamaños(altoVentana, 3221), metodo.tamaños(anchoVentana, 2265), metodo.tamaños(altoVentana, 1713));
       txtPrecioHospNiño.setFont(new Font("Times New Roman", 3, tamañoLetra*9/2));
       txtPrecioHospNiño.setVisible(false);
       */
       //btnGuardarPrecioHab.setBounds(metodo.tamaños(anchoVentana, 3648), metodo.tamaños(altoVentana, 3107), metodo.tamaños(anchoVentana, 1094), metodo.tamaños(altoVentana, 2039));
       //btnGuardarPrecioHab.setBounds(metodo.tamaños(anchoVentana, 7135), metodo.tamaños(altoVentana, 1748), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 971));
       btnGuardarPrecioHab.setBounds(metodo.tamaños(anchoVentana, 5469), metodo.tamaños(altoVentana, 5340), metodo.tamaños(anchoVentana, 1042), metodo.tamaños(altoVentana, 1942));
       metodo.cambiarImgBoton(btnGuardarPrecioHab, "editar1.png");
       btnGuardarPrecioHab.setVisible(false);
       panelUsuario.setBounds(lblFondoPanelPrincipal.getBounds().x, lblFondoPanelPrincipal.getBounds().y, lblFondoPanelPrincipal.getBounds().width, lblFondoPanelPrincipal.getBounds().height-1);
       lblFondoArribaUsuario.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 201), metodo.tamaños(anchoVentana, 8292), metodo.tamaños(altoVentana, 2777));
       metodo.cambiarImgLbl(lblFondoArribaUsuario, "subFondoPanel.png");
       lblEncabezadoUsuarios.setBounds(metodo.tamaños(anchoVentana, 3229), metodo.tamaños(altoVentana, 291), metodo.tamaños(anchoVentana, 2031), metodo.tamaños(altoVentana, 583));
       lblEncabezadoUsuarios.setFont(new Font("Times New Roman", 3, tamañoLetra*3/2));
       lblNombresUsuario.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 1068), metodo.tamaños(anchoVentana, 651), metodo.tamaños(altoVentana, 388));
       lblNombresUsuario.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       lblClaveUsuario.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 1505), metodo.tamaños(anchoVentana, 651), metodo.tamaños(altoVentana, 388));
       lblClaveUsuario.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       lblCargoUsuario.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 1942), metodo.tamaños(anchoVentana, 651), metodo.tamaños(altoVentana, 388));
       lblCargoUsuario.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       lblNickNameUsuario.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 2379), metodo.tamaños(anchoVentana, 701), metodo.tamaños(altoVentana, 388));
       lblNickNameUsuario.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       txtNombreUsuario.setBounds(metodo.tamaños(anchoVentana, 990), metodo.tamaños(altoVentana, 1068), metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 450));
       txtNombreUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       txtClaveUsuario.setBounds(metodo.tamaños(anchoVentana, 990), metodo.tamaños(altoVentana, 1505), metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 450));
       txtClaveUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       txtCargoUsuario.setBounds(metodo.tamaños(anchoVentana, 990), metodo.tamaños(altoVentana, 1942), metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 450));
       txtCargoUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       txtNickNameUsuario.setBounds(metodo.tamaños(anchoVentana, 990), metodo.tamaños(altoVentana, 2379), metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 450));
       txtNickNameUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       lblPermisosUsuario.setBounds(metodo.tamaños(anchoVentana, 3281), metodo.tamaños(altoVentana, 918), metodo.tamaños(anchoVentana, 3594), metodo.tamaños(altoVentana, 1852));
       //lblPermisosUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permisos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, tamañoLetra*3/4), new java.awt.Color(255, 255, 255))); // NOI18N
       lblPermisosUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true), "Permisos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, tamañoLetra*3/4), new java.awt.Color(255, 255, 255))); // NOI18N
       lblFondoAbajoUsuario.setBounds(metodo.tamaños(anchoVentana, 104), metodo.tamaños(altoVentana, 2913), metodo.tamaños(anchoVentana, 8292), metodo.tamaños(altoVentana, 5476));
       metodo.cambiarImgLbl(lblFondoAbajoUsuario, "subFondoPanel.png");
       lblBuscarUsuario.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 3107), metodo.tamaños(anchoVentana, 651), metodo.tamaños(altoVentana, 450));
       lblBuscarUsuario.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       txtBuscarUsuario.setBounds(metodo.tamaños(anchoVentana, 990), metodo.tamaños(altoVentana, 3107), metodo.tamaños(anchoVentana, 2188), metodo.tamaños(altoVentana, 450));
       txtBuscarUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra*3/4));
       jScrollPane5.setBounds(metodo.tamaños(anchoVentana, 260), metodo.tamaños(altoVentana, 3592), metodo.tamaños(anchoVentana, 7901), metodo.tamaños(altoVentana, 4408));
       tblUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       spinTblUsuario.setBounds(metodo.tamaños(anchoVentana, 7813), metodo.tamaños(altoVentana, 3107), metodo.tamaños(anchoVentana, 365), metodo.tamaños(altoVentana, 388));
       spinTblUsuario.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
       //spinTblUsuario.setValue(tamañoLetra/2);
       spinTblUsuario.setModel(new SpinnerNumberModel(tamañoLetra/2, tamañoLetra/4, tamañoLetra*2, 1));
       chBoxCliente.setBounds(metodo.tamaños(anchoVentana, 3698), metodo.tamaños(altoVentana, 1262), metodo.tamaños(anchoVentana, 1250), metodo.tamaños(altoVentana, 388));
       chBoxCliente.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       chBoxHab.setBounds(metodo.tamaños(anchoVentana, 3698), metodo.tamaños(altoVentana, 1748), metodo.tamaños(anchoVentana, 1250), metodo.tamaños(altoVentana, 388));
       chBoxHab.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       chBoxReservacion.setBounds(metodo.tamaños(anchoVentana, 3698), metodo.tamaños(altoVentana, 2233), metodo.tamaños(anchoVentana, 1250), metodo.tamaños(altoVentana, 388));
       chBoxReservacion.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       chBoxVisitas.setBounds(metodo.tamaños(anchoVentana, 5313), metodo.tamaños(altoVentana, 1262), metodo.tamaños(anchoVentana, 1250), metodo.tamaños(altoVentana, 388));
       chBoxVisitas.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       chBoxValorHosp.setBounds(metodo.tamaños(anchoVentana, 5313), metodo.tamaños(altoVentana, 1748), metodo.tamaños(anchoVentana, 1250), metodo.tamaños(altoVentana, 388));
       chBoxValorHosp.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       chBoxUsuario.setBounds(metodo.tamaños(anchoVentana, 5313), metodo.tamaños(altoVentana, 2233), metodo.tamaños(anchoVentana, 1250), metodo.tamaños(altoVentana, 388));
       chBoxUsuario.setFont(new Font("Times New Roman", 3, tamañoLetra*3/4));
       btnGuardarUsuario.setBounds(metodo.tamaños(anchoVentana, 6979), metodo.tamaños(altoVentana, 1408), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 971));
       metodo.cambiarImgBoton(btnGuardarUsuario, "guardar1.png");
       btnLimpiarUsuario.setBounds(metodo.tamaños(anchoVentana, 7604), metodo.tamaños(altoVentana, 1408), metodo.tamaños(anchoVentana, 521), metodo.tamaños(altoVentana, 971));
       metodo.cambiarImgBoton(btnLimpiarUsuario, "limpiar1.png");
       metodo.deshabilitarEscritJspinn(spinTblUsuario);
       txtNickNameUsuario.setEditable(false);
       //lblCargando.setBounds((anchoVentana/2)-(metodo.tamaños(anchoVentana, 1380)/2), (altoVentana/2)-(metodo.tamaños(altoVentana, 3333)/2), metodo.tamaños(anchoVentana, 1380), metodo.tamaños(altoVentana, 1667));
       //metodo.cambiarImgGifLbl(lblCargando, "cargando.gif");
        //timePicker1.setVisible(false);
        //metodo.cambiarImgBoton(btnHoraRes, "relojPequeño2.gif");
//System.out.println(metodo.tamaños(anchoVentana, 2135)+","+metodo.tamaños(altoVentana, 201)+","+metodo.tamaños(anchoVentana, 6313)+","+metodo.tamaños(altoVentana, 8291));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnCliente = new javax.swing.JButton();
        btnHab = new javax.swing.JButton();
        btnPrecioHab = new javax.swing.JButton();
        btnReserv = new javax.swing.JButton();
        btnVisitas = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        lblCargando = new javax.swing.JLabel();
        lblNameUser = new javax.swing.JLabel();
        lblFondo2 = new javax.swing.JLabel();
        lblAvatar = new javax.swing.JLabel();
        panelUsuario = new javax.swing.JPanel();
        btnGuardarUsuario = new javax.swing.JButton();
        btnLimpiarUsuario = new javax.swing.JButton();
        lblEncabezadoUsuarios = new javax.swing.JLabel();
        lblNombresUsuario = new javax.swing.JLabel();
        lblClaveUsuario = new javax.swing.JLabel();
        lblCargoUsuario = new javax.swing.JLabel();
        lblNickNameUsuario = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        txtCargoUsuario = new javax.swing.JTextField();
        txtClaveUsuario = new javax.swing.JTextField();
        txtNickNameUsuario = new javax.swing.JTextField();
        lblBuscarUsuario = new javax.swing.JLabel();
        txtBuscarUsuario = new javax.swing.JTextField();
        spinTblUsuario = new javax.swing.JSpinner();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        chBoxCliente = new javax.swing.JCheckBox();
        chBoxHab = new javax.swing.JCheckBox();
        chBoxReservacion = new javax.swing.JCheckBox();
        chBoxVisitas = new javax.swing.JCheckBox();
        chBoxValorHosp = new javax.swing.JCheckBox();
        chBoxUsuario = new javax.swing.JCheckBox();
        lblPermisosUsuario = new javax.swing.JLabel();
        lblFondoArribaUsuario = new javax.swing.JLabel();
        lblFondoAbajoUsuario = new javax.swing.JLabel();
        panelPrecioHosp = new javax.swing.JPanel();
        spnTablaPrecioHosp = new com.toedter.components.JSpinField();
        lblTituloPrecioHosp = new javax.swing.JLabel();
        lblHabSeleccionadaPrecioHosp = new javax.swing.JLabel();
        btnGuardarPrecioHab = new javax.swing.JButton();
        lblDetalleHabSelecPrecioHosp = new javax.swing.JLabel();
        lblPrecioHospHabPrecioHosp = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaPrecioHosp = new javax.swing.JTable();
        lblSubFondoPanelPrecioHosp = new javax.swing.JLabel();
        panelVisita = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaVisita = new javax.swing.JTable();
        txtBuscarVisita = new javax.swing.JTextField();
        lblBuscarVisita = new javax.swing.JLabel();
        spinTablaVisita = new javax.swing.JSpinner();
        lblSubFondoPanelVisita = new javax.swing.JLabel();
        panelHab = new javax.swing.JPanel();
        subPanelHab = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaHab = new javax.swing.JTable();
        btnGuardarClienteHab = new javax.swing.JButton();
        btnLimpiarClienteHab = new javax.swing.JButton();
        cmbTipoCliHab = new javax.swing.JComboBox<>();
        txtCedulaClientHab = new javax.swing.JTextField();
        lblTituloPanelHab = new javax.swing.JLabel();
        lblCedulaClienteHab = new javax.swing.JLabel();
        lblNombresClienteHab = new javax.swing.JLabel();
        lblDireccionClienteHab = new javax.swing.JLabel();
        lblObservClienteHab = new javax.swing.JLabel();
        lblCorreoClienteHab = new javax.swing.JLabel();
        lblTelefonoClienteHab = new javax.swing.JLabel();
        lblVistoClienteHab = new javax.swing.JLabel();
        lblFechaIngClienteHab = new javax.swing.JLabel();
        lblFechaSalidaClienteHab = new javax.swing.JLabel();
        lblCostoTotalHab = new javax.swing.JLabel();
        lblCostoHab = new javax.swing.JLabel();
        lblCostoIndHabCli = new javax.swing.JLabel();
        lblCostoIndCli = new javax.swing.JLabel();
        lblAbonoClienteHab = new javax.swing.JLabel();
        txtAbonoClientHab = new javax.swing.JTextField();
        txtObservClientHab = new javax.swing.JTextField();
        txtNombresClientHab = new javax.swing.JTextField();
        txtDireccionClientHab = new javax.swing.JTextField();
        txtCorreoClientHab = new javax.swing.JTextField();
        txtTelefonoClientHab = new javax.swing.JTextField();
        datChosFechaIngHab = new com.toedter.calendar.JDateChooser();
        datChosFechaSalHab = new com.toedter.calendar.JDateChooser();
        spnTablaClienteHab = new com.toedter.components.JSpinField();
        btnHab1 = new javax.swing.JButton();
        btnHab2 = new javax.swing.JButton();
        btnHab3 = new javax.swing.JButton();
        btnHab4 = new javax.swing.JButton();
        btnHab5 = new javax.swing.JButton();
        btnHab6 = new javax.swing.JButton();
        btnHab7 = new javax.swing.JButton();
        btnHab8 = new javax.swing.JButton();
        btnHab9 = new javax.swing.JButton();
        btnHab10 = new javax.swing.JButton();
        lblHab1 = new javax.swing.JLabel();
        lblHab2 = new javax.swing.JLabel();
        lblHab3 = new javax.swing.JLabel();
        lblHab4 = new javax.swing.JLabel();
        lblHab5 = new javax.swing.JLabel();
        lblHab6 = new javax.swing.JLabel();
        lblHab7 = new javax.swing.JLabel();
        lblHab8 = new javax.swing.JLabel();
        lblHab9 = new javax.swing.JLabel();
        lblHab10 = new javax.swing.JLabel();
        lblFondoPanelHab = new javax.swing.JLabel();
        panelRes = new javax.swing.JPanel();
        btnRegresarDia = new javax.swing.JButton();
        btnRegresarSemana = new javax.swing.JButton();
        btnAvanzarDia = new javax.swing.JButton();
        btnAvanzarSemana = new javax.swing.JButton();
        lblResF11 = new javax.swing.JLabel();
        lblResF12 = new javax.swing.JLabel();
        lblResF13 = new javax.swing.JLabel();
        lblResF14 = new javax.swing.JLabel();
        lblResF15 = new javax.swing.JLabel();
        lblResF16 = new javax.swing.JLabel();
        lblResF17 = new javax.swing.JLabel();
        lblResF21 = new javax.swing.JLabel();
        lblResF22 = new javax.swing.JLabel();
        lblResF23 = new javax.swing.JLabel();
        lblResF24 = new javax.swing.JLabel();
        lblResF25 = new javax.swing.JLabel();
        lblResF26 = new javax.swing.JLabel();
        lblResF27 = new javax.swing.JLabel();
        lblResF31 = new javax.swing.JLabel();
        lblResF32 = new javax.swing.JLabel();
        lblResF33 = new javax.swing.JLabel();
        lblResF34 = new javax.swing.JLabel();
        lblResF35 = new javax.swing.JLabel();
        lblResF36 = new javax.swing.JLabel();
        lblResF37 = new javax.swing.JLabel();
        lblResF41 = new javax.swing.JLabel();
        lblResF42 = new javax.swing.JLabel();
        lblResF43 = new javax.swing.JLabel();
        lblResF44 = new javax.swing.JLabel();
        lblResF45 = new javax.swing.JLabel();
        lblResF46 = new javax.swing.JLabel();
        lblResF47 = new javax.swing.JLabel();
        lblResF51 = new javax.swing.JLabel();
        lblResF52 = new javax.swing.JLabel();
        lblResF53 = new javax.swing.JLabel();
        lblResF54 = new javax.swing.JLabel();
        lblResF55 = new javax.swing.JLabel();
        lblResF56 = new javax.swing.JLabel();
        lblResF57 = new javax.swing.JLabel();
        lblResF61 = new javax.swing.JLabel();
        lblResF62 = new javax.swing.JLabel();
        lblResF63 = new javax.swing.JLabel();
        lblResF64 = new javax.swing.JLabel();
        lblResF65 = new javax.swing.JLabel();
        lblResF66 = new javax.swing.JLabel();
        lblResF67 = new javax.swing.JLabel();
        lblResF71 = new javax.swing.JLabel();
        lblResF72 = new javax.swing.JLabel();
        lblResF73 = new javax.swing.JLabel();
        lblResF74 = new javax.swing.JLabel();
        lblResF75 = new javax.swing.JLabel();
        lblResF76 = new javax.swing.JLabel();
        lblResF77 = new javax.swing.JLabel();
        lblResF81 = new javax.swing.JLabel();
        lblResF82 = new javax.swing.JLabel();
        lblResF83 = new javax.swing.JLabel();
        lblResF84 = new javax.swing.JLabel();
        lblResF85 = new javax.swing.JLabel();
        lblResF86 = new javax.swing.JLabel();
        lblResF87 = new javax.swing.JLabel();
        lblResF91 = new javax.swing.JLabel();
        lblResF92 = new javax.swing.JLabel();
        lblResF93 = new javax.swing.JLabel();
        lblResF94 = new javax.swing.JLabel();
        lblResF95 = new javax.swing.JLabel();
        lblResF96 = new javax.swing.JLabel();
        lblResF97 = new javax.swing.JLabel();
        lblResF101 = new javax.swing.JLabel();
        lblResF102 = new javax.swing.JLabel();
        lblResF103 = new javax.swing.JLabel();
        lblResF104 = new javax.swing.JLabel();
        lblResF105 = new javax.swing.JLabel();
        lblResF106 = new javax.swing.JLabel();
        lblResF107 = new javax.swing.JLabel();
        lblRes00 = new javax.swing.JLabel();
        lblRes01 = new javax.swing.JLabel();
        lblRes02 = new javax.swing.JLabel();
        lblRes03 = new javax.swing.JLabel();
        lblRes04 = new javax.swing.JLabel();
        lblRes05 = new javax.swing.JLabel();
        lblRes06 = new javax.swing.JLabel();
        lblRes07 = new javax.swing.JLabel();
        lblRes10 = new javax.swing.JLabel();
        lblRes11 = new javax.swing.JLabel();
        lblRes12 = new javax.swing.JLabel();
        lblRes13 = new javax.swing.JLabel();
        lblRes14 = new javax.swing.JLabel();
        lblRes15 = new javax.swing.JLabel();
        lblRes16 = new javax.swing.JLabel();
        lblRes17 = new javax.swing.JLabel();
        lblRes20 = new javax.swing.JLabel();
        lblRes21 = new javax.swing.JLabel();
        lblRes22 = new javax.swing.JLabel();
        lblRes23 = new javax.swing.JLabel();
        lblRes24 = new javax.swing.JLabel();
        lblRes25 = new javax.swing.JLabel();
        lblRes26 = new javax.swing.JLabel();
        lblRes27 = new javax.swing.JLabel();
        lblRes30 = new javax.swing.JLabel();
        lblRes31 = new javax.swing.JLabel();
        lblRes32 = new javax.swing.JLabel();
        lblRes33 = new javax.swing.JLabel();
        lblRes34 = new javax.swing.JLabel();
        lblRes35 = new javax.swing.JLabel();
        lblRes36 = new javax.swing.JLabel();
        lblRes37 = new javax.swing.JLabel();
        lblRes40 = new javax.swing.JLabel();
        lblRes41 = new javax.swing.JLabel();
        lblRes42 = new javax.swing.JLabel();
        lblRes43 = new javax.swing.JLabel();
        lblRes44 = new javax.swing.JLabel();
        lblRes45 = new javax.swing.JLabel();
        lblRes46 = new javax.swing.JLabel();
        lblRes47 = new javax.swing.JLabel();
        lblRes50 = new javax.swing.JLabel();
        lblRes51 = new javax.swing.JLabel();
        lblRes52 = new javax.swing.JLabel();
        lblRes53 = new javax.swing.JLabel();
        lblRes54 = new javax.swing.JLabel();
        lblRes55 = new javax.swing.JLabel();
        lblRes56 = new javax.swing.JLabel();
        lblRes57 = new javax.swing.JLabel();
        lblRes60 = new javax.swing.JLabel();
        lblRes61 = new javax.swing.JLabel();
        lblRes62 = new javax.swing.JLabel();
        lblRes63 = new javax.swing.JLabel();
        lblRes64 = new javax.swing.JLabel();
        lblRes65 = new javax.swing.JLabel();
        lblRes66 = new javax.swing.JLabel();
        lblRes67 = new javax.swing.JLabel();
        lblRes70 = new javax.swing.JLabel();
        lblRes71 = new javax.swing.JLabel();
        lblRes72 = new javax.swing.JLabel();
        lblRes73 = new javax.swing.JLabel();
        lblRes74 = new javax.swing.JLabel();
        lblRes75 = new javax.swing.JLabel();
        lblRes76 = new javax.swing.JLabel();
        lblRes77 = new javax.swing.JLabel();
        lblRes80 = new javax.swing.JLabel();
        lblRes81 = new javax.swing.JLabel();
        lblRes82 = new javax.swing.JLabel();
        lblRes83 = new javax.swing.JLabel();
        lblRes84 = new javax.swing.JLabel();
        lblRes85 = new javax.swing.JLabel();
        lblRes86 = new javax.swing.JLabel();
        lblRes87 = new javax.swing.JLabel();
        lblRes90 = new javax.swing.JLabel();
        lblRes91 = new javax.swing.JLabel();
        lblRes92 = new javax.swing.JLabel();
        lblRes93 = new javax.swing.JLabel();
        lblRes94 = new javax.swing.JLabel();
        lblRes95 = new javax.swing.JLabel();
        lblRes96 = new javax.swing.JLabel();
        lblRes97 = new javax.swing.JLabel();
        lblRes100 = new javax.swing.JLabel();
        lblRes101 = new javax.swing.JLabel();
        lblRes102 = new javax.swing.JLabel();
        lblRes103 = new javax.swing.JLabel();
        lblRes104 = new javax.swing.JLabel();
        lblRes105 = new javax.swing.JLabel();
        lblRes106 = new javax.swing.JLabel();
        lblRes107 = new javax.swing.JLabel();
        lblResF00 = new javax.swing.JLabel();
        lblResF01 = new javax.swing.JLabel();
        lblResF02 = new javax.swing.JLabel();
        lblResF03 = new javax.swing.JLabel();
        lblResF04 = new javax.swing.JLabel();
        lblResF05 = new javax.swing.JLabel();
        lblResF06 = new javax.swing.JLabel();
        lblResF07 = new javax.swing.JLabel();
        lblResF10 = new javax.swing.JLabel();
        lblResF20 = new javax.swing.JLabel();
        lblResF30 = new javax.swing.JLabel();
        lblResF40 = new javax.swing.JLabel();
        lblResF50 = new javax.swing.JLabel();
        lblResF60 = new javax.swing.JLabel();
        lblResF70 = new javax.swing.JLabel();
        lblResF80 = new javax.swing.JLabel();
        lblResF90 = new javax.swing.JLabel();
        lblResF100 = new javax.swing.JLabel();
        lblAgregarReservacion = new javax.swing.JLabel();
        btnAgregarReservacion = new javax.swing.JButton();
        txtCedulaRes = new javax.swing.JTextField();
        lblVistoCedulaRes = new javax.swing.JLabel();
        lblCedulaRes = new javax.swing.JLabel();
        lblNombresRes = new javax.swing.JLabel();
        lblDireccionRes = new javax.swing.JLabel();
        lblTelefonoRes = new javax.swing.JLabel();
        lblCorreoRes = new javax.swing.JLabel();
        lblObservRes = new javax.swing.JLabel();
        lblCheckInRes = new javax.swing.JLabel();
        lblCheckOutRes = new javax.swing.JLabel();
        lblCatDiasRes = new javax.swing.JLabel();
        lblHoraRes = new javax.swing.JLabel();
        lblCantAdulRes = new javax.swing.JLabel();
        lblCantNiñosRes = new javax.swing.JLabel();
        lblHabRes = new javax.swing.JLabel();
        lblAbonoRes = new javax.swing.JLabel();
        cmbHabRes = new javax.swing.JComboBox<>();
        lblTotalRes = new javax.swing.JLabel();
        CuadriculaRes = new javax.swing.JRadioButton();
        TablaRes = new javax.swing.JRadioButton();
        lblFondoMenuCuadricula = new javax.swing.JLabel();
        txtNombresRes = new javax.swing.JTextField();
        txtDireccionRes = new javax.swing.JTextField();
        txtTelefonoRes = new javax.swing.JTextField();
        txtCorreoRes = new javax.swing.JTextField();
        txtObservRes = new javax.swing.JTextField();
        txtHoraRes = new javax.swing.JTextField();
        txtAbonoRes = new javax.swing.JTextField();
        choosFechIngRes = new com.toedter.calendar.JDateChooser();
        choosFechSalRes = new com.toedter.calendar.JDateChooser();
        btnGuardarRes = new javax.swing.JButton();
        spinCantDiasRes = new javax.swing.JSpinner();
        spinCantNiñoRes = new javax.swing.JSpinner();
        spinCantAdultRes = new javax.swing.JSpinner();
        spinTamTblRes = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaReservacion = new javax.swing.JTable();
        lblFondoPanelRes1 = new javax.swing.JLabel();
        lblFondoPanelRes = new javax.swing.JLabel();
        panelCliente = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        spnTablaCliente = new com.toedter.components.JSpinField();
        btnGuardarCliente = new javax.swing.JButton();
        btnLimpiarCliente = new javax.swing.JButton();
        btnModificarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        txtCedulaCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        txtObsCliente = new javax.swing.JTextField();
        txtCorreoCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtBuscarCliente = new javax.swing.JTextField();
        lblCedulaCliente = new javax.swing.JLabel();
        lblVistoCedulaCliente = new javax.swing.JLabel();
        lblTituloPanelCliente = new javax.swing.JLabel();
        lblNombresCliente = new javax.swing.JLabel();
        lblDireccionCliente = new javax.swing.JLabel();
        lblTelefonoCliente = new javax.swing.JLabel();
        lblCorreoCliente = new javax.swing.JLabel();
        lblObservaciones = new javax.swing.JLabel();
        lblTamanoLetra = new javax.swing.JLabel();
        lblBuscarCliente = new javax.swing.JLabel();
        lblSubFondoPanelCliente = new javax.swing.JLabel();
        lblSubFondoTablaPanelCliente = new javax.swing.JLabel();
        lblCantHuesp1 = new javax.swing.JLabel();
        lblCantHuesp = new javax.swing.JLabel();
        lblFondoPanelPrincipal = new javax.swing.JLabel();
        lblFondoPrincipal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Informático de Hosteria La Huaca");
        setResizable(false);
        getContentPane().setLayout(null);

        btnCliente.setToolTipText("Clientes");
        btnCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnClienteMouseReleased(evt);
            }
        });
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });
        getContentPane().add(btnCliente);
        btnCliente.setBounds(190, 10, 33, 9);

        btnHab.setToolTipText("Habitaciones");
        btnHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHabMouseReleased(evt);
            }
        });
        btnHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabActionPerformed(evt);
            }
        });
        getContentPane().add(btnHab);
        btnHab.setBounds(190, 10, 33, 9);

        btnPrecioHab.setToolTipText("Precio de Habitaciones");
        btnPrecioHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrecioHabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrecioHabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnPrecioHabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnPrecioHabMouseReleased(evt);
            }
        });
        btnPrecioHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrecioHabActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrecioHab);
        btnPrecioHab.setBounds(190, 10, 33, 9);

        btnReserv.setToolTipText("Reservaciones");
        btnReserv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReservMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReservMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReservMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnReservMouseReleased(evt);
            }
        });
        btnReserv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservActionPerformed(evt);
            }
        });
        getContentPane().add(btnReserv);
        btnReserv.setBounds(190, 10, 33, 9);

        btnVisitas.setToolTipText("Visitas");
        btnVisitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVisitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVisitasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVisitasMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnVisitasMouseReleased(evt);
            }
        });
        btnVisitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisitasActionPerformed(evt);
            }
        });
        getContentPane().add(btnVisitas);
        btnVisitas.setBounds(190, 10, 33, 9);

        btnUsuarios.setToolTipText("Usuarios");
        btnUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUsuariosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnUsuariosMouseReleased(evt);
            }
        });
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });
        getContentPane().add(btnUsuarios);
        btnUsuarios.setBounds(190, 10, 33, 9);
        getContentPane().add(lblCargando);
        lblCargando.setBounds(900, 260, 0, 0);

        lblNameUser.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        lblNameUser.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblNameUser);
        lblNameUser.setBounds(10, 30, 200, 200);

        lblFondo2.setFont(new java.awt.Font("Times New Roman", 3, 30)); // NOI18N
        lblFondo2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblFondo2);
        lblFondo2.setBounds(10, 30, 200, 200);
        getContentPane().add(lblAvatar);
        lblAvatar.setBounds(10, 30, 200, 200);

        panelUsuario.setLayout(null);

        btnGuardarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarUsuario.setEnabled(false);
        btnGuardarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarUsuarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarUsuarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGuardarUsuarioMouseReleased(evt);
            }
        });
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });
        panelUsuario.add(btnGuardarUsuario);
        btnGuardarUsuario.setBounds(670, 370, 33, 9);

        btnLimpiarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarUsuarioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLimpiarUsuarioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLimpiarUsuarioMouseReleased(evt);
            }
        });
        btnLimpiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarUsuarioActionPerformed(evt);
            }
        });
        panelUsuario.add(btnLimpiarUsuario);
        btnLimpiarUsuario.setBounds(670, 370, 33, 9);

        lblEncabezadoUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        lblEncabezadoUsuarios.setText("Usuarios");
        panelUsuario.add(lblEncabezadoUsuarios);
        lblEncabezadoUsuarios.setBounds(210, 80, 41, 14);

        lblNombresUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombresUsuario.setText("Nombres:");
        panelUsuario.add(lblNombresUsuario);
        lblNombresUsuario.setBounds(210, 80, 46, 14);

        lblClaveUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblClaveUsuario.setText("Clave:");
        panelUsuario.add(lblClaveUsuario);
        lblClaveUsuario.setBounds(210, 80, 31, 14);

        lblCargoUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblCargoUsuario.setText("Cargo:");
        panelUsuario.add(lblCargoUsuario);
        lblCargoUsuario.setBounds(210, 80, 33, 14);

        lblNickNameUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNickNameUsuario.setText("NickName:");
        panelUsuario.add(lblNickNameUsuario);
        lblNickNameUsuario.setBounds(210, 80, 50, 14);

        txtNombreUsuario.setToolTipText("Ingrese los 2 nombres y 2 apellidos del usuario");
        txtNombreUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreUsuarioKeyReleased(evt);
            }
        });
        panelUsuario.add(txtNombreUsuario);
        txtNombreUsuario.setBounds(890, 250, 6, 20);
        panelUsuario.add(txtCargoUsuario);
        txtCargoUsuario.setBounds(890, 250, 6, 20);

        txtClaveUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClaveUsuarioKeyReleased(evt);
            }
        });
        panelUsuario.add(txtClaveUsuario);
        txtClaveUsuario.setBounds(890, 250, 6, 20);
        panelUsuario.add(txtNickNameUsuario);
        txtNickNameUsuario.setBounds(890, 250, 6, 20);

        lblBuscarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscarUsuario.setText("Buscar:");
        panelUsuario.add(lblBuscarUsuario);
        lblBuscarUsuario.setBounds(210, 80, 36, 14);
        panelUsuario.add(txtBuscarUsuario);
        txtBuscarUsuario.setBounds(890, 250, 6, 20);

        spinTblUsuario.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinTblUsuarioStateChanged(evt);
            }
        });
        panelUsuario.add(spinTblUsuario);
        spinTblUsuario.setBounds(650, 330, 29, 20);

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblUsuario);

        panelUsuario.add(jScrollPane5);
        jScrollPane5.setBounds(780, 90, 452, 402);

        chBoxCliente.setForeground(new java.awt.Color(255, 255, 255));
        chBoxCliente.setText("Clientes");
        chBoxCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuario.add(chBoxCliente);
        chBoxCliente.setBounds(340, 140, 63, 23);

        chBoxHab.setForeground(new java.awt.Color(255, 255, 255));
        chBoxHab.setText("Habitaciones");
        chBoxHab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuario.add(chBoxHab);
        chBoxHab.setBounds(340, 140, 87, 23);

        chBoxReservacion.setForeground(new java.awt.Color(255, 255, 255));
        chBoxReservacion.setText("Reservación");
        chBoxReservacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuario.add(chBoxReservacion);
        chBoxReservacion.setBounds(340, 140, 85, 23);

        chBoxVisitas.setForeground(new java.awt.Color(255, 255, 255));
        chBoxVisitas.setText("Visitas");
        chBoxVisitas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuario.add(chBoxVisitas);
        chBoxVisitas.setBounds(340, 140, 55, 23);

        chBoxValorHosp.setForeground(new java.awt.Color(255, 255, 255));
        chBoxValorHosp.setText("Valor Hospedaje");
        chBoxValorHosp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuario.add(chBoxValorHosp);
        chBoxValorHosp.setBounds(340, 140, 103, 23);

        chBoxUsuario.setForeground(new java.awt.Color(255, 255, 255));
        chBoxUsuario.setText("Usuarios");
        chBoxUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelUsuario.add(chBoxUsuario);
        chBoxUsuario.setBounds(340, 140, 67, 23);

        lblPermisosUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblPermisosUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true), "Permisos", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 24), new java.awt.Color(255, 255, 255))); // NOI18N
        panelUsuario.add(lblPermisosUsuario);
        lblPermisosUsuario.setBounds(310, 90, 400, 160);

        lblFondoArribaUsuario.setForeground(new java.awt.Color(255, 255, 255));
        panelUsuario.add(lblFondoArribaUsuario);
        lblFondoArribaUsuario.setBounds(210, 80, 0, 0);

        lblFondoAbajoUsuario.setForeground(new java.awt.Color(255, 255, 255));
        panelUsuario.add(lblFondoAbajoUsuario);
        lblFondoAbajoUsuario.setBounds(210, 80, 0, 0);

        getContentPane().add(panelUsuario);
        panelUsuario.setBounds(10, 0, 1160, 500);

        panelPrecioHosp.setLayout(null);

        spnTablaPrecioHosp.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spnTablaPrecioHospPropertyChange(evt);
            }
        });
        panelPrecioHosp.add(spnTablaPrecioHosp);
        spnTablaPrecioHosp.setBounds(680, 170, 29, 20);

        lblTituloPrecioHosp.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPrecioHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloPrecioHosp.setText("Precio de Hospedaje");
        lblTituloPrecioHosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTituloPrecioHospMouseClicked(evt);
            }
        });
        panelPrecioHosp.add(lblTituloPrecioHosp);
        lblTituloPrecioHosp.setBounds(90, 130, 98, 14);

        lblHabSeleccionadaPrecioHosp.setForeground(new java.awt.Color(255, 255, 255));
        lblHabSeleccionadaPrecioHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHabSeleccionadaPrecioHosp.setText("Habitación Seleccionada");
        panelPrecioHosp.add(lblHabSeleccionadaPrecioHosp);
        lblHabSeleccionadaPrecioHosp.setBounds(90, 130, 115, 14);

        btnGuardarPrecioHab.setToolTipText("Actualizar valor de hospedaje de Habitación");
        btnGuardarPrecioHab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarPrecioHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarPrecioHabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarPrecioHabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarPrecioHabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGuardarPrecioHabMouseReleased(evt);
            }
        });
        btnGuardarPrecioHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPrecioHabActionPerformed(evt);
            }
        });
        panelPrecioHosp.add(btnGuardarPrecioHab);
        btnGuardarPrecioHab.setBounds(200, 80, 33, 9);

        lblDetalleHabSelecPrecioHosp.setForeground(new java.awt.Color(255, 255, 255));
        lblDetalleHabSelecPrecioHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDetalleHabSelecPrecioHosp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        lblDetalleHabSelecPrecioHosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDetalleHabSelecPrecioHospMouseClicked(evt);
            }
        });
        panelPrecioHosp.add(lblDetalleHabSelecPrecioHosp);
        lblDetalleHabSelecPrecioHosp.setBounds(90, 130, 6, 6);

        lblPrecioHospHabPrecioHosp.setForeground(new java.awt.Color(255, 255, 255));
        lblPrecioHospHabPrecioHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrecioHospHabPrecioHosp.setText("Costos de Hospedaje por Habitación");
        lblPrecioHospHabPrecioHosp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        lblPrecioHospHabPrecioHosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrecioHospHabPrecioHospMouseClicked(evt);
            }
        });
        panelPrecioHosp.add(lblPrecioHospHabPrecioHosp);
        lblPrecioHospHabPrecioHosp.setBounds(90, 130, 180, 20);

        jScrollPane6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jScrollPane6MouseReleased(evt);
            }
        });

        tablaPrecioHosp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaPrecioHosp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPrecioHospMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPrecioHospMouseReleased(evt);
            }
        });
        jScrollPane6.setViewportView(tablaPrecioHosp);

        panelPrecioHosp.add(jScrollPane6);
        jScrollPane6.setBounds(150, 110, 452, 402);

        lblSubFondoPanelPrecioHosp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelPrecioHosp.add(lblSubFondoPanelPrecioHosp);
        lblSubFondoPanelPrecioHosp.setBounds(270, 150, 0, 0);

        getContentPane().add(panelPrecioHosp);
        panelPrecioHosp.setBounds(0, 20, 1100, 510);

        panelVisita.setLayout(null);

        tablaVisita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaVisita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVisitaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaVisita);

        panelVisita.add(jScrollPane4);
        jScrollPane4.setBounds(150, 110, 452, 402);

        txtBuscarVisita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarVisitaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarVisitaKeyReleased(evt);
            }
        });
        panelVisita.add(txtBuscarVisita);
        txtBuscarVisita.setBounds(170, 120, 6, 20);

        lblBuscarVisita.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscarVisita.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBuscarVisita.setText("Buscar:");
        panelVisita.add(lblBuscarVisita);
        lblBuscarVisita.setBounds(90, 130, 36, 14);

        spinTablaVisita.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        spinTablaVisita.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinTablaVisitaStateChanged(evt);
            }
        });
        panelVisita.add(spinTablaVisita);
        spinTablaVisita.setBounds(750, 160, 39, 20);
        panelVisita.add(lblSubFondoPanelVisita);
        lblSubFondoPanelVisita.setBounds(270, 150, 0, 0);

        getContentPane().add(panelVisita);
        panelVisita.setBounds(110, 0, 890, 530);

        panelHab.setToolTipText("Habitaciones");
        panelHab.setLayout(null);

        subPanelHab.setLayout(null);

        tablaHab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHabMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaHab);

        subPanelHab.add(jScrollPane2);
        jScrollPane2.setBounds(370, 50, 452, 402);

        btnGuardarClienteHab.setToolTipText("Guardar");
        btnGuardarClienteHab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarClienteHab.setEnabled(false);
        btnGuardarClienteHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarClienteHabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarClienteHabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarClienteHabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGuardarClienteHabMouseReleased(evt);
            }
        });
        btnGuardarClienteHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteHabActionPerformed(evt);
            }
        });
        subPanelHab.add(btnGuardarClienteHab);
        btnGuardarClienteHab.setBounds(490, 70, 33, 9);

        btnLimpiarClienteHab.setToolTipText("Limpiar cajas de texto");
        btnLimpiarClienteHab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarClienteHab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteHabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteHabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteHabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteHabMouseReleased(evt);
            }
        });
        btnLimpiarClienteHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarClienteHabActionPerformed(evt);
            }
        });
        subPanelHab.add(btnLimpiarClienteHab);
        btnLimpiarClienteHab.setBounds(490, 70, 33, 9);

        cmbTipoCliHab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoCliHabItemStateChanged(evt);
            }
        });
        subPanelHab.add(cmbTipoCliHab);
        cmbTipoCliHab.setBounds(490, 210, 28, 20);

        txtCedulaClientHab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaClientHabKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaClientHabKeyReleased(evt);
            }
        });
        subPanelHab.add(txtCedulaClientHab);
        txtCedulaClientHab.setBounds(150, 100, 6, 20);

        lblTituloPanelHab.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPanelHab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subPanelHab.add(lblTituloPanelHab);
        lblTituloPanelHab.setBounds(220, 160, 0, 0);

        lblCedulaClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaClienteHab.setText("Cédula*:");
        subPanelHab.add(lblCedulaClienteHab);
        lblCedulaClienteHab.setBounds(220, 160, 43, 14);

        lblNombresClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblNombresClienteHab.setText("Nombres*:");
        subPanelHab.add(lblNombresClienteHab);
        lblNombresClienteHab.setBounds(220, 160, 52, 14);

        lblDireccionClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccionClienteHab.setText("Dirección:");
        subPanelHab.add(lblDireccionClienteHab);
        lblDireccionClienteHab.setBounds(220, 160, 47, 14);

        lblObservClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblObservClienteHab.setText("Observaciones:");
        subPanelHab.add(lblObservClienteHab);
        lblObservClienteHab.setBounds(220, 160, 75, 14);

        lblCorreoClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreoClienteHab.setText("Correo:");
        subPanelHab.add(lblCorreoClienteHab);
        lblCorreoClienteHab.setBounds(220, 160, 37, 14);

        lblTelefonoClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefonoClienteHab.setText("Celular:");
        subPanelHab.add(lblTelefonoClienteHab);
        lblTelefonoClienteHab.setBounds(220, 160, 37, 14);

        lblVistoClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        subPanelHab.add(lblVistoClienteHab);
        lblVistoClienteHab.setBounds(220, 160, 0, 0);

        lblFechaIngClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaIngClienteHab.setText("Check-in:");
        subPanelHab.add(lblFechaIngClienteHab);
        lblFechaIngClienteHab.setBounds(220, 160, 45, 14);

        lblFechaSalidaClienteHab.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaSalidaClienteHab.setText("Check-out:");
        subPanelHab.add(lblFechaSalidaClienteHab);
        lblFechaSalidaClienteHab.setBounds(220, 160, 53, 14);

        lblCostoTotalHab.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoTotalHab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCostoTotalHab.setText("Costo Total de Hospedaje");
        subPanelHab.add(lblCostoTotalHab);
        lblCostoTotalHab.setBounds(220, 160, 124, 14);

        lblCostoHab.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoHab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCostoHab.setText("$0.00");
        lblCostoHab.setToolTipText("Costo Total de Hospedaje");
        subPanelHab.add(lblCostoHab);
        lblCostoHab.setBounds(220, 160, 28, 14);

        lblCostoIndHabCli.setForeground(new java.awt.Color(255, 255, 255));
        lblCostoIndHabCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCostoIndHabCli.setText("Costo Individual de Hospedaje:");
        subPanelHab.add(lblCostoIndHabCli);
        lblCostoIndHabCli.setBounds(220, 160, 150, 14);

        lblCostoIndCli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCostoIndCli.setText("$0.00");
        lblCostoIndCli.setToolTipText("Costo Individual de hospedaje");
        subPanelHab.add(lblCostoIndCli);
        lblCostoIndCli.setBounds(220, 160, 28, 14);

        lblAbonoClienteHab.setForeground(new java.awt.Color(0, 255, 0));
        lblAbonoClienteHab.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAbonoClienteHab.setText("Abono:");
        subPanelHab.add(lblAbonoClienteHab);
        lblAbonoClienteHab.setBounds(220, 160, 35, 14);

        txtAbonoClientHab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbonoClientHab.setText("0.00");
        txtAbonoClientHab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoClientHabKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoClientHabKeyReleased(evt);
            }
        });
        subPanelHab.add(txtAbonoClientHab);
        txtAbonoClientHab.setBounds(150, 100, 28, 20);
        subPanelHab.add(txtObservClientHab);
        txtObservClientHab.setBounds(150, 100, 6, 20);

        txtNombresClientHab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresClientHabKeyReleased(evt);
            }
        });
        subPanelHab.add(txtNombresClientHab);
        txtNombresClientHab.setBounds(150, 100, 6, 20);
        subPanelHab.add(txtDireccionClientHab);
        txtDireccionClientHab.setBounds(150, 100, 6, 20);
        subPanelHab.add(txtCorreoClientHab);
        txtCorreoClientHab.setBounds(150, 100, 6, 20);
        subPanelHab.add(txtTelefonoClientHab);
        txtTelefonoClientHab.setBounds(150, 100, 6, 20);

        datChosFechaIngHab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datChosFechaIngHabPropertyChange(evt);
            }
        });
        subPanelHab.add(datChosFechaIngHab);
        datChosFechaIngHab.setBounds(280, 230, 87, 20);

        datChosFechaSalHab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datChosFechaSalHabPropertyChange(evt);
            }
        });
        subPanelHab.add(datChosFechaSalHab);
        datChosFechaSalHab.setBounds(400, 250, 87, 20);

        spnTablaClienteHab.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spnTablaClienteHabPropertyChange(evt);
            }
        });
        subPanelHab.add(spnTablaClienteHab);
        spnTablaClienteHab.setBounds(300, 310, 29, 20);

        panelHab.add(subPanelHab);
        subPanelHab.setBounds(-181, -30, 920, 570);

        btnHab1.setForeground(new java.awt.Color(255, 255, 255));
        btnHab1.setToolTipText("Habitación 1");
        btnHab1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab1MouseReleased(evt);
            }
        });
        btnHab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab1ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab1);
        btnHab1.setBounds(70, 100, 33, 9);

        btnHab2.setForeground(new java.awt.Color(255, 255, 255));
        btnHab2.setToolTipText("Habitación 2");
        btnHab2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab2MouseReleased(evt);
            }
        });
        btnHab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab2ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab2);
        btnHab2.setBounds(70, 100, 33, 9);

        btnHab3.setForeground(new java.awt.Color(255, 255, 255));
        btnHab3.setToolTipText("Habitación 3");
        btnHab3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab3MouseReleased(evt);
            }
        });
        btnHab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab3ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab3);
        btnHab3.setBounds(70, 100, 33, 9);

        btnHab4.setForeground(new java.awt.Color(255, 255, 255));
        btnHab4.setToolTipText("Habitación 4");
        btnHab4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab4MouseReleased(evt);
            }
        });
        btnHab4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab4ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab4);
        btnHab4.setBounds(70, 100, 33, 9);

        btnHab5.setForeground(new java.awt.Color(255, 255, 255));
        btnHab5.setToolTipText("Habitación 5");
        btnHab5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab5MouseReleased(evt);
            }
        });
        btnHab5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab5ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab5);
        btnHab5.setBounds(70, 100, 33, 9);

        btnHab6.setForeground(new java.awt.Color(255, 255, 255));
        btnHab6.setToolTipText("Habitación 6");
        btnHab6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab6MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab6MouseReleased(evt);
            }
        });
        btnHab6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab6ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab6);
        btnHab6.setBounds(70, 100, 33, 9);

        btnHab7.setForeground(new java.awt.Color(255, 255, 255));
        btnHab7.setToolTipText("Habitación 7");
        btnHab7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab7MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab7MouseReleased(evt);
            }
        });
        btnHab7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab7ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab7);
        btnHab7.setBounds(70, 100, 33, 9);

        btnHab8.setForeground(new java.awt.Color(255, 255, 255));
        btnHab8.setToolTipText("Habitación 8");
        btnHab8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab8MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab8MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab8MouseReleased(evt);
            }
        });
        btnHab8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab8ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab8);
        btnHab8.setBounds(70, 100, 33, 9);

        btnHab9.setForeground(new java.awt.Color(255, 255, 255));
        btnHab9.setToolTipText("Habitación 9");
        btnHab9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab9MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab9MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab9MouseReleased(evt);
            }
        });
        btnHab9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab9ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab9);
        btnHab9.setBounds(70, 100, 33, 9);

        btnHab10.setForeground(new java.awt.Color(255, 255, 255));
        btnHab10.setToolTipText("Habitación 10");
        btnHab10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHab10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHab10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHab10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHab10MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnHab10MouseReleased(evt);
            }
        });
        btnHab10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHab10ActionPerformed(evt);
            }
        });
        panelHab.add(btnHab10);
        btnHab10.setBounds(70, 100, 33, 9);
        panelHab.add(lblHab1);
        lblHab1.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab2);
        lblHab2.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab3);
        lblHab3.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab4);
        lblHab4.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab5);
        lblHab5.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab6);
        lblHab6.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab7);
        lblHab7.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab8);
        lblHab8.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab9);
        lblHab9.setBounds(220, 160, 0, 0);
        panelHab.add(lblHab10);
        lblHab10.setBounds(220, 160, 0, 0);
        panelHab.add(lblFondoPanelHab);
        lblFondoPanelHab.setBounds(220, 160, 0, 0);

        getContentPane().add(panelHab);
        panelHab.setBounds(270, 40, 1060, 590);

        panelRes.setToolTipText("Reservaciones");
        panelRes.setLayout(null);

        btnRegresarDia.setToolTipText("Regresar un día");
        btnRegresarDia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresarDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarDiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarDiaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegresarDiaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRegresarDiaMouseReleased(evt);
            }
        });
        btnRegresarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarDiaActionPerformed(evt);
            }
        });
        panelRes.add(btnRegresarDia);
        btnRegresarDia.setBounds(240, 120, 33, 9);

        btnRegresarSemana.setToolTipText("Regresar una semana");
        btnRegresarSemana.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegresarSemana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegresarSemanaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegresarSemanaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegresarSemanaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnRegresarSemanaMouseReleased(evt);
            }
        });
        btnRegresarSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarSemanaActionPerformed(evt);
            }
        });
        panelRes.add(btnRegresarSemana);
        btnRegresarSemana.setBounds(240, 120, 33, 9);

        btnAvanzarDia.setToolTipText("Avanzar un día");
        btnAvanzarDia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzarDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvanzarDiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvanzarDiaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAvanzarDiaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAvanzarDiaMouseReleased(evt);
            }
        });
        btnAvanzarDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarDiaActionPerformed(evt);
            }
        });
        panelRes.add(btnAvanzarDia);
        btnAvanzarDia.setBounds(240, 120, 33, 9);

        btnAvanzarSemana.setToolTipText("Avanzar una semana");
        btnAvanzarSemana.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAvanzarSemana.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAvanzarSemanaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAvanzarSemanaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAvanzarSemanaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAvanzarSemanaMouseReleased(evt);
            }
        });
        btnAvanzarSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvanzarSemanaActionPerformed(evt);
            }
        });
        panelRes.add(btnAvanzarSemana);
        btnAvanzarSemana.setBounds(240, 120, 33, 9);
        panelRes.add(lblResF11);
        lblResF11.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF12);
        lblResF12.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF13);
        lblResF13.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF14);
        lblResF14.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF15);
        lblResF15.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF16);
        lblResF16.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF17);
        lblResF17.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF21);
        lblResF21.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF22);
        lblResF22.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF23);
        lblResF23.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF24);
        lblResF24.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF25);
        lblResF25.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF26);
        lblResF26.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF27);
        lblResF27.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF31);
        lblResF31.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF32);
        lblResF32.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF33);
        lblResF33.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF34);
        lblResF34.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF35);
        lblResF35.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF36);
        lblResF36.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF37);
        lblResF37.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF41);
        lblResF41.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF42);
        lblResF42.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF43);
        lblResF43.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF44);
        lblResF44.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF45);
        lblResF45.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF46);
        lblResF46.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF47);
        lblResF47.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF51);
        lblResF51.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF52);
        lblResF52.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF53);
        lblResF53.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF54);
        lblResF54.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF55);
        lblResF55.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF56);
        lblResF56.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF57);
        lblResF57.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF61);
        lblResF61.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF62);
        lblResF62.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF63);
        lblResF63.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF64);
        lblResF64.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF65);
        lblResF65.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF66);
        lblResF66.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF67);
        lblResF67.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF71);
        lblResF71.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF72);
        lblResF72.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF73);
        lblResF73.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF74);
        lblResF74.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF75);
        lblResF75.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF76);
        lblResF76.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF77);
        lblResF77.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF81);
        lblResF81.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF82);
        lblResF82.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF83);
        lblResF83.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF84);
        lblResF84.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF85);
        lblResF85.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF86);
        lblResF86.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF87);
        lblResF87.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF91);
        lblResF91.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF92);
        lblResF92.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF93);
        lblResF93.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF94);
        lblResF94.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF95);
        lblResF95.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF96);
        lblResF96.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF97);
        lblResF97.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF101);
        lblResF101.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF102);
        lblResF102.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF103);
        lblResF103.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF104);
        lblResF104.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF105);
        lblResF105.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF106);
        lblResF106.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF107);
        lblResF107.setBounds(500, 140, 0, 0);

        lblRes00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRes00MouseClicked(evt);
            }
        });
        panelRes.add(lblRes00);
        lblRes00.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes01);
        lblRes01.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes02);
        lblRes02.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes03);
        lblRes03.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes04);
        lblRes04.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes05);
        lblRes05.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes06);
        lblRes06.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes07);
        lblRes07.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes10);
        lblRes10.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes11);
        lblRes11.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes12);
        lblRes12.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes13);
        lblRes13.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes14);
        lblRes14.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes15);
        lblRes15.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes16);
        lblRes16.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes17);
        lblRes17.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes20);
        lblRes20.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes21);
        lblRes21.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes22);
        lblRes22.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes23);
        lblRes23.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes24);
        lblRes24.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes25);
        lblRes25.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes26);
        lblRes26.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes27);
        lblRes27.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes30);
        lblRes30.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes31);
        lblRes31.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes32);
        lblRes32.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes33);
        lblRes33.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes34);
        lblRes34.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes35);
        lblRes35.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes36);
        lblRes36.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes37);
        lblRes37.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes40);
        lblRes40.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes41);
        lblRes41.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes42);
        lblRes42.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes43);
        lblRes43.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes44);
        lblRes44.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes45);
        lblRes45.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes46);
        lblRes46.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes47);
        lblRes47.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes50);
        lblRes50.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes51);
        lblRes51.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes52);
        lblRes52.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes53);
        lblRes53.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes54);
        lblRes54.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes55);
        lblRes55.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes56);
        lblRes56.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes57);
        lblRes57.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes60);
        lblRes60.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes61);
        lblRes61.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes62);
        lblRes62.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes63);
        lblRes63.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes64);
        lblRes64.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes65);
        lblRes65.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes66);
        lblRes66.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes67);
        lblRes67.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes70);
        lblRes70.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes71);
        lblRes71.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes72);
        lblRes72.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes73);
        lblRes73.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes74);
        lblRes74.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes75);
        lblRes75.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes76);
        lblRes76.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes77);
        lblRes77.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes80);
        lblRes80.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes81);
        lblRes81.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes82);
        lblRes82.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes83);
        lblRes83.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes84);
        lblRes84.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes85);
        lblRes85.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes86);
        lblRes86.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes87);
        lblRes87.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes90);
        lblRes90.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes91);
        lblRes91.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes92);
        lblRes92.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes93);
        lblRes93.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes94);
        lblRes94.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes95);
        lblRes95.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes96);
        lblRes96.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes97);
        lblRes97.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes100);
        lblRes100.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes101);
        lblRes101.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes102);
        lblRes102.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes103);
        lblRes103.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes104);
        lblRes104.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes105);
        lblRes105.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes106);
        lblRes106.setBounds(500, 140, 0, 0);
        panelRes.add(lblRes107);
        lblRes107.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF00);
        lblResF00.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF01);
        lblResF01.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF02);
        lblResF02.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF03);
        lblResF03.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF04);
        lblResF04.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF05);
        lblResF05.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF06);
        lblResF06.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF07);
        lblResF07.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF10);
        lblResF10.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF20);
        lblResF20.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF30);
        lblResF30.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF40);
        lblResF40.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF50);
        lblResF50.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF60);
        lblResF60.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF70);
        lblResF70.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF80);
        lblResF80.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF90);
        lblResF90.setBounds(500, 140, 0, 0);
        panelRes.add(lblResF100);
        lblResF100.setBounds(500, 140, 0, 0);

        lblAgregarReservacion.setFont(new java.awt.Font("Tahoma", 3, 13)); // NOI18N
        lblAgregarReservacion.setForeground(new java.awt.Color(255, 255, 255));
        lblAgregarReservacion.setText("Agregar Reservación");
        lblAgregarReservacion.setToolTipText("Agregar Reservación");
        panelRes.add(lblAgregarReservacion);
        lblAgregarReservacion.setBounds(280, 260, 138, 16);

        btnAgregarReservacion.setToolTipText("Agregar Reservación");
        btnAgregarReservacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarReservacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAgregarReservacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAgregarReservacionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAgregarReservacionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAgregarReservacionMouseReleased(evt);
            }
        });
        btnAgregarReservacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarReservacionActionPerformed(evt);
            }
        });
        panelRes.add(btnAgregarReservacion);
        btnAgregarReservacion.setBounds(460, 150, 33, 9);

        txtCedulaRes.setToolTipText("");
        txtCedulaRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaResKeyReleased(evt);
            }
        });
        panelRes.add(txtCedulaRes);
        txtCedulaRes.setBounds(130, 200, 6, 20);

        lblVistoCedulaRes.setForeground(new java.awt.Color(255, 255, 255));
        lblVistoCedulaRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVistoCedulaRes.setToolTipText("");
        panelRes.add(lblVistoCedulaRes);
        lblVistoCedulaRes.setBounds(280, 260, 0, 0);

        lblCedulaRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCedulaRes.setText("Cédula:*");
        lblCedulaRes.setToolTipText("");
        panelRes.add(lblCedulaRes);
        lblCedulaRes.setBounds(280, 260, 43, 14);

        lblNombresRes.setForeground(new java.awt.Color(255, 255, 255));
        lblNombresRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNombresRes.setText("Nombres:*");
        lblNombresRes.setToolTipText("");
        panelRes.add(lblNombresRes);
        lblNombresRes.setBounds(280, 260, 52, 14);

        lblDireccionRes.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccionRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDireccionRes.setText("Dirección:");
        lblDireccionRes.setToolTipText("");
        panelRes.add(lblDireccionRes);
        lblDireccionRes.setBounds(280, 260, 47, 14);

        lblTelefonoRes.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefonoRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTelefonoRes.setText("Teléfono:");
        lblTelefonoRes.setToolTipText("");
        panelRes.add(lblTelefonoRes);
        lblTelefonoRes.setBounds(280, 260, 46, 14);

        lblCorreoRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreoRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCorreoRes.setText("Correo:");
        lblCorreoRes.setToolTipText("");
        lblCorreoRes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        panelRes.add(lblCorreoRes);
        lblCorreoRes.setBounds(280, 260, 37, 14);

        lblObservRes.setForeground(new java.awt.Color(255, 255, 255));
        lblObservRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblObservRes.setText("Observaciones:");
        lblObservRes.setToolTipText("");
        panelRes.add(lblObservRes);
        lblObservRes.setBounds(280, 260, 75, 14);

        lblCheckInRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCheckInRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCheckInRes.setText("Check-in:");
        lblCheckInRes.setToolTipText("");
        panelRes.add(lblCheckInRes);
        lblCheckInRes.setBounds(280, 260, 45, 14);

        lblCheckOutRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCheckOutRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCheckOutRes.setText("Check-out:");
        lblCheckOutRes.setToolTipText("");
        panelRes.add(lblCheckOutRes);
        lblCheckOutRes.setBounds(280, 260, 53, 14);

        lblCatDiasRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCatDiasRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCatDiasRes.setText("Días");
        lblCatDiasRes.setToolTipText("");
        panelRes.add(lblCatDiasRes);
        lblCatDiasRes.setBounds(280, 260, 20, 14);

        lblHoraRes.setForeground(new java.awt.Color(255, 255, 255));
        lblHoraRes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoraRes.setText("Hora check-in");
        lblHoraRes.setToolTipText("");
        panelRes.add(lblHoraRes);
        lblHoraRes.setBounds(280, 260, 65, 14);

        lblCantAdulRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCantAdulRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantAdulRes.setText("Adultos");
        lblCantAdulRes.setToolTipText("");
        panelRes.add(lblCantAdulRes);
        lblCantAdulRes.setBounds(280, 260, 36, 14);

        lblCantNiñosRes.setForeground(new java.awt.Color(255, 255, 255));
        lblCantNiñosRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantNiñosRes.setText("Niños");
        lblCantNiñosRes.setToolTipText("");
        panelRes.add(lblCantNiñosRes);
        lblCantNiñosRes.setBounds(280, 260, 26, 14);

        lblHabRes.setForeground(new java.awt.Color(255, 255, 255));
        lblHabRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHabRes.setText("Habitación");
        lblHabRes.setToolTipText("");
        panelRes.add(lblHabRes);
        lblHabRes.setBounds(280, 260, 50, 14);

        lblAbonoRes.setForeground(new java.awt.Color(0, 255, 0));
        lblAbonoRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAbonoRes.setText("Abono: $");
        lblAbonoRes.setToolTipText("");
        panelRes.add(lblAbonoRes);
        lblAbonoRes.setBounds(280, 260, 44, 14);

        panelRes.add(cmbHabRes);
        cmbHabRes.setBounds(550, 290, 28, 20);

        lblTotalRes.setForeground(new java.awt.Color(0, 255, 0));
        lblTotalRes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalRes.setToolTipText("Total a pagar");
        lblTotalRes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        panelRes.add(lblTotalRes);
        lblTotalRes.setBounds(280, 260, 4, 4);

        buttonGroup1.add(CuadriculaRes);
        CuadriculaRes.setForeground(new java.awt.Color(255, 255, 255));
        CuadriculaRes.setSelected(true);
        CuadriculaRes.setText("Ver Cuadrícula");
        CuadriculaRes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CuadriculaRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CuadriculaResActionPerformed(evt);
            }
        });
        panelRes.add(CuadriculaRes);
        CuadriculaRes.setBounds(280, 350, 95, 23);

        buttonGroup1.add(TablaRes);
        TablaRes.setForeground(new java.awt.Color(255, 255, 255));
        TablaRes.setText("Ver en Tabla");
        TablaRes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TablaRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TablaResActionPerformed(evt);
            }
        });
        panelRes.add(TablaRes);
        TablaRes.setBounds(280, 350, 85, 23);

        lblFondoMenuCuadricula.setForeground(new java.awt.Color(0, 255, 0));
        lblFondoMenuCuadricula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFondoMenuCuadricula.setToolTipText("");
        panelRes.add(lblFondoMenuCuadricula);
        lblFondoMenuCuadricula.setBounds(280, 260, 0, 0);

        txtNombresRes.setToolTipText("");
        txtNombresRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombresResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombresResKeyReleased(evt);
            }
        });
        panelRes.add(txtNombresRes);
        txtNombresRes.setBounds(130, 200, 6, 20);

        txtDireccionRes.setToolTipText("");
        txtDireccionRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionResKeyReleased(evt);
            }
        });
        panelRes.add(txtDireccionRes);
        txtDireccionRes.setBounds(130, 200, 6, 20);

        txtTelefonoRes.setToolTipText("");
        txtTelefonoRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoResKeyReleased(evt);
            }
        });
        panelRes.add(txtTelefonoRes);
        txtTelefonoRes.setBounds(130, 200, 6, 20);

        txtCorreoRes.setToolTipText("");
        txtCorreoRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCorreoResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoResKeyReleased(evt);
            }
        });
        panelRes.add(txtCorreoRes);
        txtCorreoRes.setBounds(130, 200, 6, 20);

        txtObservRes.setToolTipText("");
        txtObservRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtObservResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservResKeyReleased(evt);
            }
        });
        panelRes.add(txtObservRes);
        txtObservRes.setBounds(130, 200, 6, 20);

        txtHoraRes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtHoraRes.setToolTipText("Ingrese la hora de ingreso de la reservacion en formato hh:mm - a.m./p.m.");
        panelRes.add(txtHoraRes);
        txtHoraRes.setBounds(130, 200, 6, 20);

        txtAbonoRes.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAbonoRes.setText("0.00");
        txtAbonoRes.setToolTipText("");
        txtAbonoRes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAbonoResKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAbonoResKeyReleased(evt);
            }
        });
        panelRes.add(txtAbonoRes);
        txtAbonoRes.setBounds(130, 200, 28, 20);

        choosFechIngRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                choosFechIngResPropertyChange(evt);
            }
        });
        panelRes.add(choosFechIngRes);
        choosFechIngRes.setBounds(230, 200, 87, 20);

        choosFechSalRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                choosFechSalResPropertyChange(evt);
            }
        });
        panelRes.add(choosFechSalRes);
        choosFechSalRes.setBounds(230, 200, 87, 20);

        btnGuardarRes.setToolTipText("Guardar");
        btnGuardarRes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarRes.setEnabled(false);
        btnGuardarRes.setSelected(true);
        btnGuardarRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarResMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarResMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarResMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGuardarResMouseReleased(evt);
            }
        });
        btnGuardarRes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarResActionPerformed(evt);
            }
        });
        panelRes.add(btnGuardarRes);
        btnGuardarRes.setBounds(190, 310, 33, 9);

        spinCantDiasRes.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinCantDiasRes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinCantDiasResStateChanged(evt);
            }
        });
        spinCantDiasRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinCantDiasResPropertyChange(evt);
            }
        });
        panelRes.add(spinCantDiasRes);
        spinCantDiasRes.setBounds(30, 270, 31, 20);

        spinCantNiñoRes.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spinCantNiñoRes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinCantNiñoResStateChanged(evt);
            }
        });
        spinCantNiñoRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinCantNiñoResPropertyChange(evt);
            }
        });
        panelRes.add(spinCantNiñoRes);
        spinCantNiñoRes.setBounds(30, 270, 31, 20);

        spinCantAdultRes.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinCantAdultRes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinCantAdultResStateChanged(evt);
            }
        });
        spinCantAdultRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinCantAdultResPropertyChange(evt);
            }
        });
        panelRes.add(spinCantAdultRes);
        spinCantAdultRes.setBounds(30, 270, 31, 20);

        spinTamTblRes.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinTamTblRes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinTamTblResStateChanged(evt);
            }
        });
        spinTamTblRes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spinTamTblResPropertyChange(evt);
            }
        });
        panelRes.add(spinTamTblRes);
        spinTamTblRes.setBounds(30, 270, 31, 20);

        TablaReservacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaReservacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaReservacionMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TablaReservacion);

        panelRes.add(jScrollPane3);
        jScrollPane3.setBounds(0, 220, 452, 402);
        panelRes.add(lblFondoPanelRes1);
        lblFondoPanelRes1.setBounds(220, 160, 0, 0);
        panelRes.add(lblFondoPanelRes);
        lblFondoPanelRes.setBounds(220, 160, 0, 0);

        getContentPane().add(panelRes);
        panelRes.setBounds(430, 30, 780, 560);

        panelCliente.setLayout(null);

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaClienteMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaCliente);

        panelCliente.add(jScrollPane1);
        jScrollPane1.setBounds(150, 110, 452, 402);

        spnTablaCliente.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spnTablaClientePropertyChange(evt);
            }
        });
        panelCliente.add(spnTablaCliente);
        spnTablaCliente.setBounds(680, 170, 29, 20);

        btnGuardarCliente.setToolTipText("Guardar");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.setEnabled(false);
        btnGuardarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGuardarClienteMouseReleased(evt);
            }
        });
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });
        panelCliente.add(btnGuardarCliente);
        btnGuardarCliente.setBounds(490, 70, 33, 9);

        btnLimpiarCliente.setToolTipText("Limpiar cajas de texto");
        btnLimpiarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLimpiarClienteMouseReleased(evt);
            }
        });
        btnLimpiarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarClienteActionPerformed(evt);
            }
        });
        panelCliente.add(btnLimpiarCliente);
        btnLimpiarCliente.setBounds(490, 70, 33, 9);

        btnModificarCliente.setToolTipText("Actualizar datos del cliente");
        btnModificarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModificarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnModificarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnModificarClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnModificarClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnModificarClienteMouseReleased(evt);
            }
        });
        btnModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarClienteActionPerformed(evt);
            }
        });
        panelCliente.add(btnModificarCliente);
        btnModificarCliente.setBounds(490, 70, 33, 9);

        btnEliminarCliente.setToolTipText("Eliminar Cliente");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEliminarClienteMouseReleased(evt);
            }
        });
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });
        panelCliente.add(btnEliminarCliente);
        btnEliminarCliente.setBounds(490, 70, 33, 9);

        txtCedulaCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtCedulaCliente);
        txtCedulaCliente.setBounds(170, 120, 6, 20);

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtNombreCliente);
        txtNombreCliente.setBounds(170, 120, 6, 20);

        txtDireccionCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtDireccionCliente);
        txtDireccionCliente.setBounds(170, 120, 6, 20);

        txtObsCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObsClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtObsCliente);
        txtObsCliente.setBounds(170, 120, 6, 20);

        txtCorreoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtCorreoCliente);
        txtCorreoCliente.setBounds(170, 120, 6, 20);

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtTelefonoCliente);
        txtTelefonoCliente.setBounds(170, 120, 6, 20);

        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });
        panelCliente.add(txtBuscarCliente);
        txtBuscarCliente.setBounds(170, 120, 6, 20);

        lblCedulaCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCedulaCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCedulaCliente.setText("Cédula:*");
        panelCliente.add(lblCedulaCliente);
        lblCedulaCliente.setBounds(90, 130, 43, 14);

        lblVistoCedulaCliente.setForeground(new java.awt.Color(255, 255, 255));
        panelCliente.add(lblVistoCedulaCliente);
        lblVistoCedulaCliente.setBounds(90, 130, 0, 0);

        lblTituloPanelCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloPanelCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelCliente.add(lblTituloPanelCliente);
        lblTituloPanelCliente.setBounds(50, 50, 0, 0);

        lblNombresCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblNombresCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombresCliente.setText("Nombres:*");
        panelCliente.add(lblNombresCliente);
        lblNombresCliente.setBounds(90, 130, 52, 14);

        lblDireccionCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblDireccionCliente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDireccionCliente.setText("Dirección:");
        panelCliente.add(lblDireccionCliente);
        lblDireccionCliente.setBounds(90, 130, 47, 14);

        lblTelefonoCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblTelefonoCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTelefonoCliente.setText("Celular:");
        panelCliente.add(lblTelefonoCliente);
        lblTelefonoCliente.setBounds(90, 130, 37, 14);

        lblCorreoCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblCorreoCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCorreoCliente.setText("Correo:");
        panelCliente.add(lblCorreoCliente);
        lblCorreoCliente.setBounds(90, 130, 37, 14);

        lblObservaciones.setForeground(new java.awt.Color(255, 255, 255));
        lblObservaciones.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblObservaciones.setText("Observaciones:");
        panelCliente.add(lblObservaciones);
        lblObservaciones.setBounds(90, 130, 75, 14);

        lblTamanoLetra.setForeground(new java.awt.Color(255, 255, 255));
        lblTamanoLetra.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTamanoLetra.setText("Tamaño de letra:");
        panelCliente.add(lblTamanoLetra);
        lblTamanoLetra.setBounds(90, 130, 82, 14);

        lblBuscarCliente.setForeground(new java.awt.Color(255, 255, 255));
        lblBuscarCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBuscarCliente.setText("Buscar:");
        panelCliente.add(lblBuscarCliente);
        lblBuscarCliente.setBounds(90, 130, 36, 14);
        panelCliente.add(lblSubFondoPanelCliente);
        lblSubFondoPanelCliente.setBounds(270, 150, 0, 0);
        panelCliente.add(lblSubFondoTablaPanelCliente);
        lblSubFondoTablaPanelCliente.setBounds(270, 150, 0, 0);

        getContentPane().add(panelCliente);
        panelCliente.setBounds(270, 40, 1030, 590);

        lblCantHuesp1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblCantHuesp1);
        lblCantHuesp1.setBounds(20, 360, 0, 0);

        lblCantHuesp.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(lblCantHuesp);
        lblCantHuesp.setBounds(20, 360, 0, 0);
        getContentPane().add(lblFondoPanelPrincipal);
        lblFondoPanelPrincipal.setBounds(290, 160, 0, 0);

        lblFondoPrincipal.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                lblFondoPrincipalMouseDragged(evt);
            }
        });
        lblFondoPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblFondoPrincipalMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblFondoPrincipalMouseReleased(evt);
            }
        });
        getContentPane().add(lblFondoPrincipal);
        lblFondoPrincipal.setBounds(0, 0, 0, 0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseEntered
        if(!bandCliente)metodo.cambiarImgBoton(btnCliente, "cliente2.png");
    }//GEN-LAST:event_btnClienteMouseEntered

    private void btnClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseExited
        if(!bandCliente)metodo.cambiarImgBoton(btnCliente, "cliente1.png");
    }//GEN-LAST:event_btnClienteMouseExited

    private void btnClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMousePressed
        if(!bandCliente)metodo.cambiarImgBoton(btnCliente, "cliente3.png");
    }//GEN-LAST:event_btnClienteMousePressed

    private void btnClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClienteMouseReleased
        if(!bandCliente)metodo.cambiarImgBoton(btnCliente, "cliente2.png");
    }//GEN-LAST:event_btnClienteMouseReleased

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        bandCliente=true;
        bandHab=false;
        bandPrecioHab=false;
        bandReserv=false;
        bandVisitas=false;
        bandUsuarios=false;
        bandSubHab=false;
        actMenu();
        metodo.enfocarTxt(txtCedulaCliente);
        //{ "Cédula", "Nombres", "Dirección", "Telefono", "Estado", "Correo", "Observaciones" };

        tablaCliente.setFont(new Font("Times New Roman", Font.PLAIN, tamañoLetra/2));
        metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
        metodo.tamañoFuenteTablaSpn(spnTablaCliente, tablaCliente);
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnHabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabMouseEntered
        if(!bandHab)metodo.cambiarImgBoton(btnHab, "hab3.png");
    }//GEN-LAST:event_btnHabMouseEntered

    private void btnHabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabMouseExited
        if(!bandHab)metodo.cambiarImgBoton(btnHab, "hab1.png");
    }//GEN-LAST:event_btnHabMouseExited

    private void btnHabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabMousePressed
        if(!bandHab)metodo.cambiarImgBoton(btnHab, "hab2.png");
    }//GEN-LAST:event_btnHabMousePressed

    private void btnHabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHabMouseReleased
        if(!bandHab)metodo.cambiarImgBoton(btnHab, "hab3.png");
    }//GEN-LAST:event_btnHabMouseReleased

    private void btnHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabActionPerformed
        bandCliente=false;
        bandHab=true;
        bandPrecioHab=false;
        bandReserv=false;
        bandVisitas=false;
        bandUsuarios=false;
        bandSubHab=false;
        actMenu();
        metodo.siguiente(txtClienteHab());
    }//GEN-LAST:event_btnHabActionPerformed

    private void btnPrecioHabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrecioHabMouseEntered
        if(!bandPrecioHab)metodo.cambiarImgBoton(btnPrecioHab, "valorHosp2.png");
    }//GEN-LAST:event_btnPrecioHabMouseEntered

    private void btnPrecioHabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrecioHabMouseExited
        if(!bandPrecioHab)metodo.cambiarImgBoton(btnPrecioHab, "valorHosp1.png");
    }//GEN-LAST:event_btnPrecioHabMouseExited

    private void btnPrecioHabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrecioHabMousePressed
        if(!bandPrecioHab)metodo.cambiarImgBoton(btnPrecioHab, "valorHosp3.png");
    }//GEN-LAST:event_btnPrecioHabMousePressed

    private void btnPrecioHabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrecioHabMouseReleased
        if(!bandPrecioHab)metodo.cambiarImgBoton(btnPrecioHab, "valorHosp2.png");
    }//GEN-LAST:event_btnPrecioHabMouseReleased

    private void btnPrecioHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrecioHabActionPerformed
        bandCliente=false;
        bandHab=false;
        bandPrecioHab=true;
        bandReserv=false;
        bandVisitas=false;
        bandUsuarios=false;
        bandSubHab=false;
        actMenu();
        btnGuardarPrecioHab.setVisible(false);
        lblHabSeleccionadaPrecioHosp.setVisible(false);
        lblDetalleHabSelecPrecioHosp.setVisible(false);
        metodo.fondoTablaInvisible(tablaPrecioHosp, jScrollPane6, controlValorHops.MostrarTablaValorHosp(valoresHosp), anchosTblPrecioHosp, tamañoLetra);
        metodo.tamañoFuenteTablaSpn(spnTablaPrecioHosp, tablaPrecioHosp);
        /*for (int i = 0; i < habitaciones.size(); i++) {
            actValorHosp(habitaciones.get(i).getId());
        }*/

    }//GEN-LAST:event_btnPrecioHabActionPerformed

    private void btnReservMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservMouseEntered
        if(!bandReserv)metodo.cambiarImgBoton(btnReserv, "reserv2.png");
    }//GEN-LAST:event_btnReservMouseEntered

    private void btnReservMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservMouseExited
        if(!bandReserv)metodo.cambiarImgBoton(btnReserv, "reserv1.png");
    }//GEN-LAST:event_btnReservMouseExited

    private void btnReservMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservMousePressed
        if(!bandReserv)metodo.cambiarImgBoton(btnReserv, "reserv3.png");
    }//GEN-LAST:event_btnReservMousePressed

    private void btnReservMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReservMouseReleased
        if(!bandReserv)metodo.cambiarImgBoton(btnReserv, "reserv2.png");
    }//GEN-LAST:event_btnReservMouseReleased

    private void btnReservActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservActionPerformed
        bandCliente=false;
        bandHab=false;
        bandPrecioHab=false;
        bandReserv=true;
        bandVisitas=false;
        bandUsuarios=false;
        bandSubHab=false;
        actMenu();
        verCampRes(false);
        spinTamTblRes.setVisible(jScrollPane3.isVisible());
        int [] hab = new int[habitaciones.size()];
        for (int i = 0; i < habitaciones.size(); i++) {
            hab[i]=Integer.parseInt(habitaciones.get(i).getId());
        }
        Arrays.sort(hab);
        cmbHabRes.removeAllItems();
        for (int i = 0; i < habitaciones.size(); i++) {
            cmbHabRes.addItem("Hab. "+hab[i]);
        }
    }//GEN-LAST:event_btnReservActionPerformed

    private void btnVisitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVisitasMouseEntered
        if(!bandVisitas)metodo.cambiarImgBoton(btnVisitas, "visita2.png");
    }//GEN-LAST:event_btnVisitasMouseEntered

    private void btnVisitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVisitasMouseExited
        if(!bandVisitas)metodo.cambiarImgBoton(btnVisitas, "visita1.png");
    }//GEN-LAST:event_btnVisitasMouseExited

    private void btnVisitasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVisitasMousePressed
        if(!bandVisitas)metodo.cambiarImgBoton(btnVisitas, "visita3.png");
    }//GEN-LAST:event_btnVisitasMousePressed

    private void btnVisitasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVisitasMouseReleased
        if(!bandVisitas)metodo.cambiarImgBoton(btnVisitas, "visita2.png");
    }//GEN-LAST:event_btnVisitasMouseReleased

    private void btnVisitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisitasActionPerformed
        bandCliente=false;
        bandHab=false;
        bandPrecioHab=false;
        bandReserv=false;
        bandVisitas=true;
        bandUsuarios=false;
        bandSubHab=false;
        actMenu();
        metodo.fondoTablaInvisible(tablaVisita, jScrollPane4, controlVisitas.MostrarTablaVisitas(visitas,consumos, clienteHabitaciones), anchosTblVisita, tamañoLetra);
        metodo.enfocarTxt(txtBuscarVisita);
        metodo.tamañoFuenteTablaSpn(spinTablaVisita, tablaVisita);
    }//GEN-LAST:event_btnVisitasActionPerformed

    private void btnUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseEntered
        if(!bandUsuarios)metodo.cambiarImgBoton(btnUsuarios, "user2.png");
    }//GEN-LAST:event_btnUsuariosMouseEntered

    private void btnUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseExited
        if(!bandUsuarios)metodo.cambiarImgBoton(btnUsuarios, "user1.png");
    }//GEN-LAST:event_btnUsuariosMouseExited

    private void btnUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMousePressed
        if(!bandUsuarios)metodo.cambiarImgBoton(btnUsuarios, "user3.png");
    }//GEN-LAST:event_btnUsuariosMousePressed

    private void btnUsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosMouseReleased
        if(!bandUsuarios)metodo.cambiarImgBoton(btnUsuarios, "user2.png");
    }//GEN-LAST:event_btnUsuariosMouseReleased

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        bandCliente=false;
        bandHab=false;
        bandPrecioHab=false;
        bandReserv=false;
        bandVisitas=false;
        bandUsuarios=true;
        bandSubHab=false;
        actMenu();
        metodo.fondoTablaInvisible(tblUsuario, jScrollPane5, controlUsuario.MostrarTablaUsuario(usuarios, user),anchosTblUsuario, tamañoLetra);
        metodo.enfocarTxt(txtNombreUsuario);
        metodo.siguiente(txtUsuarios());
        metodo.tamañoFuenteTablaSpn(spinTblUsuario, tblUsuario);
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnGuardarUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioMouseEntered
        metodo.cambiarImgBoton(btnGuardarUsuario, "guardar2.png");
    }//GEN-LAST:event_btnGuardarUsuarioMouseEntered

    private void btnGuardarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioMouseExited
        metodo.cambiarImgBoton(btnGuardarUsuario, "guardar1.png");
    }//GEN-LAST:event_btnGuardarUsuarioMouseExited

    private void btnGuardarUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioMousePressed
        metodo.cambiarImgBoton(btnGuardarUsuario, "guardar3.png");
    }//GEN-LAST:event_btnGuardarUsuarioMousePressed

    private void btnGuardarUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioMouseReleased
        metodo.cambiarImgBoton(btnGuardarUsuario, "guardar2.png");
    }//GEN-LAST:event_btnGuardarUsuarioMouseReleased
    String [] datosUser = new String[anchosTblUsuario.length];
    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed
        if (upUser) {
            usuario us = controlUsuario.buscarUsuarioPorNickName(txtNickNameUsuario.getText(), usuarios);
            usuario newUser = new usuario(txtNickNameUsuario.getText(), txtNombreUsuario.getText(), txtCargoUsuario.getText(), txtClaveUsuario.getText(), chBoxCliente.isSelected()+"", chBoxHab.isSelected()+"",chBoxValorHosp.isSelected()+"", chBoxReservacion.isSelected()+"", chBoxVisitas.isSelected()+"",us.getEstado(), chBoxUsuario.isSelected()+"");
            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de actualizar los datos de este usuario?", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
                if (controlUsuario.actualizarUsuario(datosUser[0], newUser)) {
                    metodo.mensajeDialogo(metodo.personalizarMsj("Usuario actualizado con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Actualización", metodo.informacion);
                    usuarios = controlUsuario.listarUsuarios("");
                    metodo.fondoTablaInvisible(tblUsuario, jScrollPane5, controlUsuario.MostrarTablaUsuario(usuarios, user), anchosTblUsuario, tamañoLetra);
                    limpiarTxtUsuario();
                    upUser=false;
                }
            }
        }else{
            usuario newUser = new usuario(txtNickNameUsuario.getText(), txtNombreUsuario.getText(), txtCargoUsuario.getText(), txtClaveUsuario.getText(), chBoxCliente.isSelected()+"", chBoxHab.isSelected()+"",chBoxValorHosp.isSelected()+"", chBoxReservacion.isSelected()+"", chBoxVisitas.isSelected()+"","Inactivo", chBoxUsuario.isSelected()+"");
            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de ingresar este usuario?", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
                if (controlUsuario.ingresarUsuario(newUser)) {
                    metodo.mensajeDialogo(metodo.personalizarMsj("Usuario ingresado con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Actualización", metodo.informacion);
                    usuarios = controlUsuario.listarUsuarios("");
                    metodo.fondoTablaInvisible(tblUsuario, jScrollPane5, controlUsuario.MostrarTablaUsuario(usuarios, user), anchosTblUsuario, tamañoLetra);
                    limpiarTxtUsuario();
                    upUser=false;
                }
            }
        }
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void btnLimpiarUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarUsuarioMouseEntered
        metodo.cambiarImgBoton(btnLimpiarUsuario, "limpiar2.png");
    }//GEN-LAST:event_btnLimpiarUsuarioMouseEntered

    private void btnLimpiarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarUsuarioMouseExited
        metodo.cambiarImgBoton(btnLimpiarUsuario, "limpiar1.png");
    }//GEN-LAST:event_btnLimpiarUsuarioMouseExited

    private void btnLimpiarUsuarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarUsuarioMousePressed
        metodo.cambiarImgBoton(btnLimpiarUsuario, "limpiar3.png");
    }//GEN-LAST:event_btnLimpiarUsuarioMousePressed

    private void btnLimpiarUsuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarUsuarioMouseReleased
        metodo.cambiarImgBoton(btnLimpiarUsuario, "limpiar2.png");
    }//GEN-LAST:event_btnLimpiarUsuarioMouseReleased

    private void btnLimpiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarUsuarioActionPerformed
        limpiarTxtUsuario();
    }//GEN-LAST:event_btnLimpiarUsuarioActionPerformed

    private void txtNombreUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreUsuarioKeyReleased
        if (txtNombreUsuario.getText().split(" ").length>3){
            String nickName = (txtNombreUsuario.getText().split(" ")[0].split("")[0]+txtNombreUsuario.getText().split(" ")[1].split("")[0]+txtNombreUsuario.getText().split(" ")[2]+txtNombreUsuario.getText().split(" ")[3].split("")[0]).toLowerCase();
            txtNickNameUsuario.setText(controlUsuario.getNickName(usuarios, nickName, 0));
            btnGuardarUsuario.setEnabled(true);
            /*if (txtNombreUsuario.getText().split(" ")[0].split("").length>=3&&txtNombreUsuario.getText().split(" ")[1].split("").length>=3) {
                String nombre1=txtNombreUsuario.getText().split(" ")[0];
                String nombre2=txtNombreUsuario.getText().split(" ")[1];
                txtNickNameUsuario.setText(nombre1.split("")[0]+nombre1.split("")[1]+nombre1.split("")[2]+nombre2.split("")[0]+nombre2.split("")[1]+nombre2.split("")[2]);
            }
            */
        }else{
            txtNickNameUsuario.setText("");
            btnGuardarUsuario.setEnabled(false);
        }
        if (txtNombreUsuario.getText().split(" ").length>3&&!txtClaveUsuario.getText().equals("")) {
            btnGuardarUsuario.setEnabled(true);
        }else{
            btnGuardarUsuario.setEnabled(false);
        }
    }//GEN-LAST:event_txtNombreUsuarioKeyReleased

    private void txtClaveUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveUsuarioKeyReleased
        if (txtNombreUsuario.getText().split(" ").length>3&&!txtClaveUsuario.getText().equals("")) {
            btnGuardarUsuario.setEnabled(true);
        }else{
            btnGuardarUsuario.setEnabled(false);
        }
    }//GEN-LAST:event_txtClaveUsuarioKeyReleased

    private void spinTblUsuarioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinTblUsuarioStateChanged
        metodo.tamañoFuenteTablaSpn(spinTblUsuario, tblUsuario);
    }//GEN-LAST:event_spinTblUsuarioStateChanged

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        if (evt.getClickCount()==2) {
            datosUser = metodo.deTblAArray(tblUsuario);
            String [] opciones = {"Actualizar", "Eliminar", "Cancelar"};
            int op = metodo.mensajeOpcionMultiple(metodo.personalizarMsj("Elija una opción", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Información", opciones);
            switch(op){
                case 0:{
                    txtNombreUsuario.setText(datosUser[1]);
                    txtClaveUsuario.setText(datosUser[3]);
                    txtCargoUsuario.setText(datosUser[2]);
                    txtNickNameUsuario.setText(datosUser[0]);
                    chBoxCliente.setSelected(controlUsuario.permitidoBloqueadoInv(datosUser[4]));
                    chBoxHab.setSelected(controlUsuario.permitidoBloqueadoInv(datosUser[5]));
                    chBoxValorHosp.setSelected(controlUsuario.permitidoBloqueadoInv(datosUser[6]));
                    chBoxReservacion.setSelected(controlUsuario.permitidoBloqueadoInv(datosUser[7]));
                    chBoxVisitas.setSelected(controlUsuario.permitidoBloqueadoInv(datosUser[8]));
                    chBoxUsuario.setSelected(controlUsuario.permitidoBloqueadoInv(datosUser[9]));
                    metodo.enfocarTxt(txtNombreUsuario);
                    upUser = true;
                    break;
                }
                case 1:{
                    if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de eliminar este usuario?", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
                        if (controlUsuario.eliminarUsuario(datosUser[0])) {
                            metodo.mensajeDialogo(metodo.personalizarMsj("Usuario eliminado con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Eliminar", metodo.informacion);
                            usuarios = controlUsuario.listarUsuarios("");
                            metodo.fondoTablaInvisible(tblUsuario, jScrollPane5, controlUsuario.MostrarTablaUsuario(usuarios, user), anchosTblUsuario, tamañoLetra);
                            limpiarTxtUsuario();
                        }
                    }
                    break;
                }
            }
        }
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void spnTablaPrecioHospPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnTablaPrecioHospPropertyChange
        if (bandPrecioHab) {
            metodo.tamañoFuenteTablaSpn(spnTablaPrecioHosp, tablaPrecioHosp);
        }
    }//GEN-LAST:event_spnTablaPrecioHospPropertyChange

    private void lblTituloPrecioHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTituloPrecioHospMouseClicked

    }//GEN-LAST:event_lblTituloPrecioHospMouseClicked

    private void btnGuardarPrecioHabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarPrecioHabMouseEntered
        metodo.cambiarImgBoton(btnGuardarPrecioHab, "editar2.png");
    }//GEN-LAST:event_btnGuardarPrecioHabMouseEntered

    private void btnGuardarPrecioHabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarPrecioHabMouseExited
        metodo.cambiarImgBoton(btnGuardarPrecioHab, "editar1.png");
    }//GEN-LAST:event_btnGuardarPrecioHabMouseExited

    private void btnGuardarPrecioHabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarPrecioHabMousePressed
        metodo.cambiarImgBoton(btnGuardarPrecioHab, "editar3.png");
    }//GEN-LAST:event_btnGuardarPrecioHabMousePressed

    private void btnGuardarPrecioHabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarPrecioHabMouseReleased
        metodo.cambiarImgBoton(btnGuardarPrecioHab, "editar2.png");
    }//GEN-LAST:event_btnGuardarPrecioHabMouseReleased
    
    String precioAdult="";
    int contActPrecioHosp = 0;
    private void btnGuardarPrecioHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPrecioHabActionPerformed
        boolean clav = false;
        if (tablaPrecioHosp.getSelectedRow()>0&&contActPrecioHosp<4) {
            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de actualizar este valor de hospedaje?", metodo.rojo, "times new roman", tamañoLetra*3/4, true, false, false).split("</html>")[0]+
                metodo.personalizarMsj("Habitación N° "+metodo.deTblAString(tablaPrecioHosp, 0), metodo.negro, "times new roman", tamañoLetra/2, true, false, false).split("<html>")[1].split("</html>")[0]+
                metodo.personalizarMsj("Tipo de huésped: "+metodo.deTblAString(tablaPrecioHosp, 1), metodo.azul, "times new roman", tamañoLetra/2, true, false, false).split("<html>")[1].split("</html>")[0]+
                metodo.personalizarMsj("Valor individual de hospedaje: $"+metodo.deTblAString(tablaPrecioHosp, 2), metodo.verde, "times new roman", tamañoLetra*6/10, true, false, false).split("<html>")[1], "Aviso", metodo.pregunta)) {
            String psw = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese su contraseña", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta, null, "");
            if (psw!=null) {
                if (psw.equals(user.getClave())) {
                    String newprecio = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese el nuevo costo de hospedaje", metodo.azul, "times new roman", tamañoLetra*65/100, true, false, false).split("</html>")[0]+
                        metodo.personalizarMsj("Utiliza el punto (.) como separador de decimal. Ejemplo: 47.50", metodo.verde, "times new roman", tamañoLetra/2, false, false, false).split("<html>")[1], "Información", metodo.informacion, null, metodo.deTblAString(tablaPrecioHosp, 2));
                    if (newprecio!=null&&!newprecio.isEmpty()&&metodo.isDouble(newprecio)) {
                        newprecio = metodo.redondearCerDer(Double.parseDouble(newprecio), 2);
                        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de actualizar el precio de Hospedaje?", metodo.azul, "times new roman", metodo.titulo1, true, false, false).split("</html>")[0]+
                            metodo.personalizarMsj("De $"+metodo.deTblAString(tablaPrecioHosp, 2)+" a $"+newprecio, metodo.verde, "times new roman", tamañoLetra/2, true, false, false).split("<html>")[1], "Aviso", metodo.pregunta)) {
                        if(controlValorHops.actualizarValorHospPorDescripcion(metodo.deTblAString(tablaPrecioHosp, 1), newprecio, metodo.deTblAString(tablaPrecioHosp, 0))){
                            metodo.mensajeDialogo(metodo.personalizarMsj("Valor de hospedaje actualizado correctamente", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                            valoresHosp = controlValorHops.listarValor_Hosp("");
                            actValorHosp(metodo.deTblAString(tablaPrecioHosp, 0));
                            contActPrecioHosp=0;
                            btnGuardarPrecioHab.setVisible(false);
                            lblHabSeleccionadaPrecioHosp.setVisible(false);
                            lblDetalleHabSelecPrecioHosp.setVisible(false);
                            metodo.fondoTablaInvisible(tablaPrecioHosp, jScrollPane6, controlValorHops.MostrarTablaValorHosp(valoresHosp), anchosTblPrecioHosp, tamañoLetra);
                            metodo.tamañoFuenteTablaSpn(spnTablaPrecioHosp, tablaPrecioHosp);
                        }
                    }
                }else{
                    metodo.mensajeDialogo(metodo.personalizarMsj("Precio de hospedaje no válido", metodo.rojo, "times new roman", tamañoLetra*3/4, true, false, false), "Error", metodo.error);
                }
            }else{
                contActPrecioHosp = contActPrecioHosp + 1;
                metodo.mensajeDialogo(metodo.personalizarMsj("Contrasena incorrecta", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Tiene "+(4-contActPrecioHosp)+" intento(s) más", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
            }
        }
        }
        }
        /*
        if (metodo.isDouble(txtPrecioHospAdult.getText())) {
            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de actualizar el precio de Hospedaje?", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
                if(controlValorHops.actualizarValorHospPorDescripcion("Adulto", txtPrecioHospAdult.getText())&&controlValorHops.actualizarValorHospPorDescripcion("Niño", txtPrecioHospNiño.getText())){
                    metodo.mensajeDialogo(metodo.personalizarMsj("Valor de hospedaje actualizado correctamente", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                    valoresHosp = controlValorHops.listarValor_Hosp("");
                    actValorHosp();
                    txtPrecioHospAdult.setVisible(false);
                    txtPrecioHospNiño.setVisible(false);
                    contActPrecioHosp=0;
                    btnGuardarPrecioHab.setVisible(false);
                }
            }
        }
        */
    }//GEN-LAST:event_btnGuardarPrecioHabActionPerformed

    private void lblDetalleHabSelecPrecioHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDetalleHabSelecPrecioHospMouseClicked
        /*
        if (evt.getClickCount()==2&&contActPrecioHosp<3) {
            String [] opciones = {"Actualizar precio", "Cancelar"};
            int op = metodo.mensajeOpcionMultiple(metodo.personalizarMsj("Elija una opción", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Actualización de precios", opciones);
            switch (op){
                case 0:{
                    String psw = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese la contraseña del administrador", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta, null, "");
                    if (psw.equals(user.getClave())) {
                        txtPrecioHospAdult.setVisible(true);
                        metodo.enfocarTxt(txtPrecioHospAdult);
                        btnGuardarPrecioHab.setVisible(true);
                        contActPrecioHosp=0;
                    }else{
                        contActPrecioHosp++;
                        metodo.mensajeDialogo(metodo.personalizarMsj("Contrasena incorrecta", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Tiene "+(3-contActPrecioHosp)+" intento(s) más", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                    }
                    break;
                }
                case 1:{
                }
            }
        }*/
    }//GEN-LAST:event_lblDetalleHabSelecPrecioHospMouseClicked

    private void lblPrecioHospHabPrecioHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrecioHospHabPrecioHospMouseClicked
        /*
        if (evt.getClickCount()==2&&contActPrecioHosp<3) {
            String [] opciones = {"Actualizar precio", "Cancelar"};
            int op = metodo.mensajeOpcionMultiple(metodo.personalizarMsj("Elija una opción", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Actualización de precios", opciones);
            switch (op){
                case 0:{
                    String psw = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese la contraseña del administrador", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta, null, "");
                    if (psw.equals(user.getClave())) {
                        txtPrecioHospNiño.setVisible(true);
                        metodo.enfocarTxt(txtPrecioHospNiño);
                        btnGuardarPrecioHab.setVisible(true);
                        contActPrecioHosp=0;
                    }else{
                        contActPrecioHosp++;
                        metodo.mensajeDialogo(metodo.personalizarMsj("Contrasena incorrecta", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Tiene "+(3-contActPrecioHosp)+" intento(s) más", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                    }
                }
            }
        }
        */
    }//GEN-LAST:event_lblPrecioHospHabPrecioHospMouseClicked

    private void tablaPrecioHospMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPrecioHospMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaPrecioHospMouseClicked

    private void tablaPrecioHospMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPrecioHospMouseReleased
        if (tablaPrecioHosp.getSelectedRow()>=0) {
            btnGuardarPrecioHab.setVisible(true);
            lblHabSeleccionadaPrecioHosp.setVisible(true);
            lblDetalleHabSelecPrecioHosp.setVisible(true);
            String numHab = metodo.deTblAString(tablaPrecioHosp, 0);
            String tipoHuesp = metodo.deTblAString(tablaPrecioHosp, 1);
            String costo = metodo.deTblAString(tablaPrecioHosp, 2);
            String detalle = metodo.personalizarMsj("Habitación N°"+numHab,metodo.blanco,"times new roman",tamañoLetra,true,true,false).split("</html>")[0]+
            metodo.personalizarMsj("Tipo de Huésped: "+tipoHuesp,metodo.blanco,"times new roman",tamañoLetra,false,true,false).split("<html>")[1].split("</html>")[0]+
            metodo.personalizarMsj("Valor Individual de Hospedaje: $"+costo,metodo.blanco,"times new roman",tamañoLetra,false,true,false).split("<html>")[1];
            //System.out.println(detalle);
            lblDetalleHabSelecPrecioHosp.setText(detalle);
        }else{
            btnGuardarPrecioHab.setVisible(false);
            lblHabSeleccionadaPrecioHosp.setVisible(false);
            lblDetalleHabSelecPrecioHosp.setVisible(false);
            lblDetalleHabSelecPrecioHosp.setText("");
        }
    }//GEN-LAST:event_tablaPrecioHospMouseReleased

    private void jScrollPane6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane6MouseReleased

    private void tablaVisitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVisitaMouseClicked
        if (evt.getClickCount()==2) {
            String [] datos = metodo.deTblAArray(tablaVisita);
            String [] opciones ={"Ver todas las visitas de este cliente", "Ver todas las visitas del "+datos[5].split(" - ")[0], "Ver todas las visitas de la habitación N°"+datos[4]};
            visita vis = controlVisitas.buscarVisitasPorId(datos[0], visitas);
            List<visita> vis2 = new ArrayList();
            String op = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Elija una opción", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Visitas", metodo.pregunta, opciones, opciones[0]);
            if (op.equals(opciones[1])) {
                op = "Ver todas las visitas en fecha";
            }
            if (op.equals(opciones[2])) {
                op = "Ver todas las visitas en habitacion";
            }
            switch(op){
                case "Ver todas las visitas de este cliente":{
                    vis2 = controlVisitas.buscarVisitasPorIdCliente(vis.getId_cliente(), visitas);
                    String encabezado = "Hab.♦Nombres♦Check-in♦Check-out♦$Hospedaje♦$Extra♦Descripción";
                    String mensaje = "Dato1♦Dato2♦Dato3♦Dato4•Dato5♦Dato6♦Dato7♦Dato8";
                    mensaje = "";
                    for (int i = 0; i < vis2.size(); i++) {
                        cliente_Hab CH = controlCliHab.buscarClienteHabPorIdVis(vis2.get(i).getId(), clienteHabitaciones);
                        mensaje = mensaje+CH.getId_hab()+"♦"+vis2.get(i).getNombre()+"♦"+CH.getFechaIng()+" - "+metodo.de24A12Horas(CH.getHora_ing())+"♦"+CH.getFechaSal()+" - "+metodo.de24A12Horas(CH.getEstado())+"♦$"+metodo.redondearCerDer(Double.parseDouble(CH.getConsumo()), 2)+"♦$"+metodo.redondearCerDer(Double.parseDouble(controlConsumo.buscarConsumoTotalPorIdVis(vis2.get(i).getId(), consumos)), 2)+"♦"+controlConsumo.buscarConsumosPorIdVis(vis2.get(i).getId(), controlConsumo.buscarConsumoPorIdVis(vis2.get(i).getId(), consumos))+"•";
                    }
                    metodo.mensajeDialogo(metodo.personalizarMsjTabla(encabezado, mensaje, "#4472C4", metodo.blanco , metodo.negro, metodo.negro, "#CFD5EA", "#E9EBF5","times new roman",6,5), "Registro de Visitas", metodo.informacion);
                    break;
                }
                case "Ver todas las visitas en fecha" :{
                    vis2 = controlVisitas.buscarVisitasPorFecha(metodo.StringADate(datos[5].split(" - ")[0], "/"), visitas);
                    String encabezado = "Hab.♦Id. Cliente♦Nombres♦Dirección♦Check-in♦Check-out♦$Hospedaje♦$Extra♦Descripción";
                    String mensaje = "";
                    for (int i = 0; i < vis2.size(); i++) {
                        cliente_Hab CH = controlCliHab.buscarClienteHabPorIdVis(vis2.get(i).getId(), clienteHabitaciones);
                        mensaje = mensaje+CH.getId_hab()+"♦"+CH.getId_cliente()+"♦"+vis2.get(i).getNombre()+"♦"+vis2.get(i).getDireccion()+"♦"+CH.getFechaIng()+" - "+metodo.de24A12Horas(CH.getHora_ing())+"♦"+CH.getFechaSal()+" - "+metodo.de24A12Horas(CH.getEstado())+"♦$"+metodo.redondearCerDer(Double.parseDouble(CH.getConsumo()), 2)+"♦$"+metodo.redondearCerDer(Double.parseDouble(controlConsumo.buscarConsumoTotalPorIdVis(vis2.get(i).getId(), consumos)), 2)+"♦"+controlConsumo.buscarConsumosPorIdVis(vis2.get(i).getId(), controlConsumo.buscarConsumoPorIdVis(vis2.get(i).getId(), consumos))+"•";
                    }
                    metodo.mensajeDialogo(metodo.personalizarMsjTabla(encabezado, mensaje, "#4472C4", metodo.blanco , metodo.negro, metodo.negro, "#CFD5EA", "#E9EBF5","times new roman",6,5), "Registro de Visitas", metodo.informacion);
                    break;
                }
                case "Ver todas las visitas en habitacion" :{
                    vis2 = controlVisitas.buscarVisitasPorIdHab(datos[4], visitas);
                    String encabezado = "Hab.♦Id. Cliente♦Nombres♦Dirección♦Check-in♦Check-out♦$Hospedaje♦$Extra♦Descripción";
                    String mensaje = "";
                    for (int i = 0; i < vis2.size(); i++) {
                        cliente_Hab CH = controlCliHab.buscarClienteHabPorIdVis(vis2.get(i).getId(), clienteHabitaciones);
                        mensaje = mensaje+CH.getId_hab()+"♦"+CH.getId_cliente()+"♦"+vis2.get(i).getNombre()+"♦"+vis2.get(i).getDireccion()+"♦"+CH.getFechaIng()+" - "+metodo.de24A12Horas(CH.getHora_ing())+"♦"+CH.getFechaSal()+" - "+metodo.de24A12Horas(CH.getEstado())+"♦$"+metodo.redondearCerDer(Double.parseDouble(CH.getConsumo()), 2)+"♦$"+metodo.redondearCerDer(Double.parseDouble(controlConsumo.buscarConsumoTotalPorIdVis(vis2.get(i).getId(), consumos)), 2)+"♦"+controlConsumo.buscarConsumosPorIdVis(vis2.get(i).getId(), controlConsumo.buscarConsumoPorIdVis(vis2.get(i).getId(), consumos))+"•";
                    }
                    metodo.mensajeDialogo(metodo.personalizarMsjTabla(encabezado, mensaje, "#4472C4", metodo.blanco , metodo.negro, metodo.negro, "#CFD5EA", "#E9EBF5","times new roman",6,5), "Registro de Visitas", metodo.informacion);
                    break;
                }
            }
        }
    }//GEN-LAST:event_tablaVisitaMouseClicked

    private void txtBuscarVisitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVisitaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarVisitaKeyPressed

    private void txtBuscarVisitaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarVisitaKeyReleased
        metodo.fondoTablaInvisible(tablaVisita, jScrollPane4, controlVisitas.MostrarTablaVisitas(controlVisitas.listarVisitas(txtBuscarVisita.getText().toUpperCase()), consumos, clienteHabitaciones), anchosTblVisita, tamañoLetra);
        metodo.tamañoFuenteTablaSpn(spinTablaVisita, tablaVisita);
    }//GEN-LAST:event_txtBuscarVisitaKeyReleased

    private void spinTablaVisitaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinTablaVisitaStateChanged
        if (bandVisitas) {
            metodo.tamañoFuenteTablaSpn(spinTablaVisita, tablaVisita);
        }
    }//GEN-LAST:event_spinTablaVisitaStateChanged

    private void tablaHabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHabMouseClicked
        String cedula = metodo.deTblAString(tablaHab, 0);
        String nombres = metodo.deTblAString(tablaHab, 1);
        String consExt = metodo.deTblAString(tablaHab, 7);
        String idVisita = metodo.deTblAString(tablaHab, 9);
        String fechaIng = metodo.deTblAString(tablaHab, 3);
        String tipoCli = metodo.deTblAString(tablaHab, 2);
        String fechaSal = metodo.deTblAString(tablaHab, 4).split(" - ")[0];
        String horaSal = metodo.de12A24Horas(metodo.deTblAString(tablaHab, 4).split(" - ")[1]);
        String numHab = lblTituloPanelHab.getText().split(" ")[2];
        List<consumo> consumosIdVis = controlConsumo.buscarConsumoPorIdVis(idVisita, consumos);
        int cantOp=consumosIdVis.size();
        String [] opciones = {"Ver más datos del cliente", "Cambiar de habitación","Realizar check-out", "Eliminar huésped", "Actualizar fecha de salida", "Actualizar hora de salida","Agregar consumo","Ver consumo","Eliminar consumo"};
        String [] opciones2 = {"Ver más datos del cliente", "Cambiar de habitación","Realizar check-out", "Eliminar huésped", "Actualizar fecha de salida", "Actualizar hora de salida","Agregar consumo"};
        if (evt.getClickCount()==2) {
            String op = "";
            if (cantOp>0) {
                op = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Cliente seleccionado:", metodo.negro, "times new roman", metodo.titulo2, true, false, false)+" \n"+metodo.personalizarMsj(metodo.deTblAString(tablaHab, 1), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Qué desea realizar?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Aviso", metodo.pregunta, opciones, opciones[0]);
            }else{
                op = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Cliente seleccionado:", metodo.negro, "times new roman", metodo.titulo2, true, false, false)+" \n"+metodo.personalizarMsj(metodo.deTblAString(tablaHab, 1), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Qué desea realizar?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Aviso", metodo.pregunta, opciones2, opciones2[0]);
            }
            switch(op){
                case "Ver más datos del cliente":{
                    cliente cli = controlCliente.buscarCliente(cedula, clientes);
                    metodo.mensajeDialogo(metodo.personalizarMsj("Cédula: "+cedula, metodo.negro, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("Nombres: "+cli.getNombres(), metodo.azul, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("Dirección: "+cli.getDireccion(), metodo.negro, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("Celular: "+cli.getTelefono(), metodo.azul, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("Correo: "+cli.getCorreo(), metodo.negro, "times new roman", metodo.titulo2, false, false, false)+"\n\n"+metodo.personalizarMsj(cli.getObservaciones(), metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Datos del cliente", metodo.informacion);
                    break;
                }
                case "Cambiar de habitación":{
                    List<habitacion> hab = controlHab.habActivas(habitaciones);
                    int [] habDispo = new int[hab.size()-1];
                    int cont = 0;
                    String [] habDis = new String[habDispo.length];
                    for (int i = 0; i < hab.size(); i++) {
                        //System.out.println(hab.get(i).getId()+" - "+numHab);
                        if (!hab.get(i).getId().equals(numHab)) {
                            habDispo[cont]=Integer.parseInt(hab.get(i).getId());
                            cont++;
                        }
                        //habDispo[i]=Integer.parseInt(hab.get(i).getId());
                    }
                    Arrays.sort(habDispo);
                    for (int i = 0; i < habDispo.length; i++) {
                        habDis[i] = "Habitación N° "+habDispo[i];
                    }
                    String newHab = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Elija una habitación", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Cambio de habitación", metodo.pregunta, habDis, habDis[0]);
                    if (!newHab.equals("null")) {
                        newHab = newHab.split(" ")[2];
                        if (controlReserv.validarFechasReserv(newHab, reservaciones, fechaIng, fechaSal)) {
                            metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Una o varias de las fechas estan reservadas en la Habitación N° "+newHab, metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                        }else{
                            if(metodo.mensajeConfirmacion(metodo.personalizarMsj("Habitación Actual: "+numHab, metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj("¿Esta seguro de cambiar el huésped a la "+newHab+"?", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Cambio de Habitación", metodo.pregunta)){

                                if(controlCliente.actualizarEstadoCliente("Hab "+newHab, cedula)&&controlCliHab.actualizarHabClienteHab(cedula, newHab, fechaIng, fechaSal)&&controlVisitas.actualizarHabVisitas(newHab, idVisita)){
                                    visitas = controlVisitas.listarVisitas("");
                                    habitaciones = controlHab.listarHabitacion("");
                                    clientes = controlCliente.listarClientes("");
                                    clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                                    actBtnHab();
                                    contHabRes = 0;
                                    tiempoHabRes.start();
                                    String estado = controlHab.estadoHab(numHab+"", habitaciones);
                                    lblTituloPanelHab.setText("Habitación N° "+numHab+" - "+estado);
                                    List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                                    metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                                    lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                                    metodo.enfocarTxt(txtCedulaClientHab);
                                    metodo.mensajeDialogo(metodo.personalizarMsj("Cambio de habitación realizado con éxito", metodo.verde, "times new roman", metodo.titulo2, false, false, false), "Información", metodo.informacion);

                                }else{
                                    metodo.mensajeDialogo(metodo.personalizarMsj("Error al realizar el cambio de habitación", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                                }
                            }

                        }
                    }
                    break;
                }
                case "Actualizar fecha de salida":{
                    if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Fecha salida actual: "+fechaSal, metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Desea cambiar la fecha de salida?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Cambio de fecha de salida", metodo.pregunta)) {
                        boolean diaAct = false;
                        if (!metodo.DateAString(new Date()).equals(fechaIng)) {
                            diaAct=true;
                        }
                        String[] fechas = metodo.generarFecha(new Date(), 30, diaAct);
                        String newFechaSal = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Fecha salida actual: "+fechaSal, metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Elija la nueva fecha de salida", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Cambio de fecha", metodo.pregunta, fechas, fechas[0]);
                        if (!newFechaSal.equals("null")) {
                            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Fecha salida actual: "+fechaSal, metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj("Nueva fecha de salida: "+newFechaSal, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Desea realizar este cambio?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Cambio de fecha de salida", metodo.pregunta)) {
                                controlCliHab.actualizarFechaSalClienteHab(numHab, newFechaSal, idVisita, visitas, valoresHosp);
                                controlVisitas.actualizarFechaSal(newFechaSal, numHab, idVisita, visitas, valoresHosp);
                                visitas = controlVisitas.listarVisitas("");
                                clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                                List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                                metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                                lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                                metodo.enfocarTxt(txtCedulaClientHab);
                                metodo.mensajeDialogo(metodo.personalizarMsj("Cambio de check-out realizado con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                                contHabRes = 0;
                                tiempoHabRes.start();
                            }
                        }
                    }
                    break;
                }
                case "Actualizar hora de salida":{
                    if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Hora salida actual: "+metodo.deTblAString(tablaHab, 4).split(" - ")[1], metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Desea cambiar la hora de salida?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Cambio de hora de salida", metodo.pregunta)) {
                        String[] horas = metodo.generarHora(metodo.StringADate(fechaSal, "/"), horaSal);
                        String newHoraSal = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Hora de salida actual: "+metodo.deTblAString(tablaHab, 4).split(" - ")[1], metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Elija la nueva hora de salida", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Cambio de hora de salida", metodo.pregunta, horas, horas[0]);
                        if (!newHoraSal.equals("null")) {
                            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Hora de salida actual: "+metodo.de24A12Horas(horaSal), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj("Nueva hora de salida: "+newHoraSal, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Desea realizar este cambio?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Cambio de hora de salida", metodo.pregunta)) {
                                controlCliHab.actualizarEstadoClienteHab(numHab, metodo.de12A24Horas(newHoraSal.toLowerCase()), idVisita);
                                clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                                List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                                metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                                lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                                metodo.enfocarTxt(txtCedulaClientHab);
                                metodo.mensajeDialogo(metodo.personalizarMsj("Cambio de check-out realizado con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                            }
                        }
                    }
                    break;
                }
                case "Agregar consumo":{
                    String descripcion = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Escriba la descripción del consumo extra", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Consumo extra", metodo.pregunta, null, "");
                    //System.out.println("descripcion: "+descripcion+" +");
                    if (descripcion!=null&&!descripcion.isEmpty()) {
                        //System.out.println("descripcion: "+descripcion+" -");
                        String precio = "";
                        do{
                            precio = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Escriba el precio de "+descripcion, metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Precio del consumo extra", metodo.pregunta, null, "");
                            if (precio==null||precio.isEmpty()) {
                                precio="-1.0";
                            }
                            //System.out.println(precio+" - "+metodo.isDouble(precio)+"");
                        }
                        while(!metodo.isDouble(precio));
                        if (!precio.equals("-1.0")) {
                            precio = metodo.redondearCerDer(Double.parseDouble(precio), 2);
                            //System.out.println("precio: "+precio);
                        }
                        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Descripción: "+descripcion, metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj("Precio: "+precio, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de ingresar este consumo extra?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Consumo extra", metodo.pregunta)) {
                            if (controlConsumo.ingresarConsumo(new consumo("", numHab, descripcion, precio, idVisita))) {
                                consumos = controlConsumo.listarConsumo("");
                                List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                                metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                                lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                                metodo.enfocarTxt(txtCedulaClientHab);
                                metodo.mensajeDialogo(metodo.personalizarMsj("Consumo extra ingresado con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                            }
                        }
                    }
                    break;
                }
                case "Ver consumo":{
                    if (consumosIdVis.size()>0) {
                        String cons = metodo.personalizarMsj("Huésped: "+nombres, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj("Consumos extras:", metodo.negro, "times new roman", metodo.titulo2, true, false, false)+"\n";
                        for (int i = 0; i < consumosIdVis.size(); i++) {
                            cons = cons+metodo.personalizarMsj("♦"+consumosIdVis.get(i).getDescripcion()+": $"+consumosIdVis.get(i).getPrecio(), metodo.azul, "times new roman", metodo.titulo2, false, false, false)+"\n";
                        }
                        cons=cons+"\n\n"+metodo.personalizarMsj("Consumo extra total: $"+consExt, metodo.verde, "times new roman", metodo.titulo1, true, false, false);
                        metodo.mensajeDialogo(cons, "Consumos extras", metodo.informacion);
                    }else{
                        metodo.mensajeDialogo(metodo.personalizarMsj("El huésped "+nombres+", no ha realizado consumos extras", metodo.rojo, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                    }
                    break;
                }
                case "Eliminar consumo":{
                    String [] cons = new String[consumosIdVis.size()];
                    for (int i = 0; i < cons.length; i++) {
                        cons[i]=consumosIdVis.get(i).getDescripcion()+": $"+consumosIdVis.get(i).getPrecio();
                    }
                    String con = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Huésped: "+nombres, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Cuál es el consumo que desea eliminar?", metodo.negro, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Consumos extras:", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Consumos extras", metodo.pregunta, cons, cons[0]);
                    if (!con.equals("null")) {
                        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguo de eliminar este consumo?", metodo.rojo, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj(con, metodo.azul, "times new roman", metodo.titulo2, true, false, false),"Aviso",metodo.pregunta)) {
                            for (int i = 0; i < cons.length; i++) {
                                if (con.equals(consumosIdVis.get(i).getDescripcion()+": $"+consumosIdVis.get(i).getPrecio())) {
                                    controlConsumo.eliminarConsumo(consumosIdVis.get(i).getId());
                                    consumos = controlConsumo.listarConsumo("");
                                    List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                                    metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                                    lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                                    metodo.enfocarTxt(txtCedulaClientHab);
                                    metodo.mensajeDialogo(metodo.personalizarMsj("Consumo extra eliminado con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                                }
                            }
                        }
                    }
                    break;
                }
                case "Realizar check-out":{
                    double contDias = controlVisitas.cantDias(fechaIng, metodo.DateAString(new Date()));
                    double precioDia = Double.parseDouble(controlValorHops.buscarValorHosp(tipoCli, valoresHosp, numHab).getCosto());
                    double precioTot = contDias*precioDia;
                    double costCons=0;
                    String cons = "";
                    for (int i = 0; i < consumosIdVis.size(); i++) {
                        if(i==0){
                            cons = "\n\n"+metodo.personalizarMsj("Consumos extras:", metodo.negro, "times new roman", metodo.titulo2, true, false, false)+"\n";
                        }
                        cons = cons+metodo.personalizarMsj(consumosIdVis.get(i).getDescripcion()+": $"+consumosIdVis.get(i).getPrecio(), metodo.azul, "times new roman", metodo.titulo2, false, false, false)+"\n";
                        costCons = costCons + Double.parseDouble(consumosIdVis.get(i).getPrecio());
                    }
                    cons = cons +metodo.personalizarMsj("Total consumos extras: "+metodo.redondearCerDer(costCons, 2), metodo.azul, "times new roman", metodo.titulo1, true, false, false)+"\n";
                    String mensaje = metodo.personalizarMsj("Huésped: "+nombres, metodo.verde, "times new roman", metodo.superTitulo, true, false, false)+"\n\n"+metodo.personalizarMsj("Consumo Hospedaje:", metodo.negro, "times new roman", metodo.titulo2, true, false, false)+"\n"+metodo.personalizarMsj("Cant. dias: "+((int)contDias)+" dia(s)", metodo.azul, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("Costo por dia: $"+metodo.redondearCerDer(precioDia,2), metodo.azul, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("Costo total por hospedaje: $"+metodo.redondearCerDer(precioTot,2), metodo.azul, "times new roman", metodo.titulo1, true, false, false)+cons;
                    if (metodo.mensajeConfirmacion(mensaje+"\n"+metodo.personalizarMsj("Total a pagar: $"+metodo.redondearCerDer(precioTot+costCons,2), metodo.verde, "times new roman", metodo.superTitulo, true, false, false)+"\n"+metodo.personalizarMsj("¿Está seguro de realizar el check-out de este huésped?", metodo.negro, "times new roman", metodo.titulo1, true, false, false), "Check-out", metodo.pregunta)) {
                        if(controlCliHab.actualizarFechaSalClienteHab(numHab, metodo.DateAString(new Date()), idVisita, visitas, valoresHosp)&&
                            controlCliHab.actualizarEstadoClienteHab(numHab, new Date().getHours()+":"+new Date().getMinutes(), idVisita)&&
                            controlVisitas.actualizarFechaSal(metodo.DateAString(new Date()), numHab, idVisita, visitas, valoresHosp)){
                            metodo.mensajeDialogo(metodo.personalizarMsj("Se realizó el check-out al huésped con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                            clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                            visitas = controlVisitas.listarVisitas("");
                            List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                            metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                            lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                            actBtnHab();
                            contHabRes = 0;
                            tiempoHabRes.start();
                        }
                    }
                    break;
                }
                case "Eliminar huésped":{
                    if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de eliminar a "+nombres+" de la Habitación N° "+numHab+"?", metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("(Si elimina el huésped de la habitación no quedará ningún", metodo.rojo, "times new roman", metodo.titulo2, false, false, false)+"\n"+metodo.personalizarMsj("registro del hospedaje o consumo realizado por este huésped)", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Aviso", metodo.peligro)) {
                        controlCliHab.eliminarClienteHabIdVis(idVisita);
                        controlVisitas.eliminarVisitas(idVisita);
                        clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                        visitas = controlVisitas.listarVisitas("");
                        List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                        metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                        lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                        actBtnHab();
                        contHabRes = 0;
                        tiempoHabRes.start();
                        metodo.mensajeDialogo(metodo.personalizarMsj("Se eliminó al huésped con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                    }
                    break;
                }
            }
        }

    }//GEN-LAST:event_tablaHabMouseClicked

    private void btnGuardarClienteHabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteHabMouseEntered
        metodo.cambiarImgBoton(btnGuardarClienteHab, "aggCliente2.png");
    }//GEN-LAST:event_btnGuardarClienteHabMouseEntered

    private void btnGuardarClienteHabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteHabMouseExited
        metodo.cambiarImgBoton(btnGuardarClienteHab, "aggCliente1.png");
    }//GEN-LAST:event_btnGuardarClienteHabMouseExited

    private void btnGuardarClienteHabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteHabMousePressed
        metodo.cambiarImgBoton(btnGuardarClienteHab, "aggCliente3.png");
    }//GEN-LAST:event_btnGuardarClienteHabMousePressed

    private void btnGuardarClienteHabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteHabMouseReleased
        metodo.cambiarImgBoton(btnGuardarClienteHab, "aggCliente2.png");
    }//GEN-LAST:event_btnGuardarClienteHabMouseReleased
    
    public void ingresarCliHab(int numHab){
        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de ingresar este cliente?", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Aviso", metodo.pregunta)) {
            int cantDias = controlVisitas.cantDias(metodo.DateAString(datChosFechaIngHab.getDate()), metodo.DateAString(datChosFechaSalHab.getDate()));
            double consumo = (double)cantDias* Double.parseDouble(controlValorHops.buscarValorHosp(cmbTipoCliHab.getSelectedItem()+"", valoresHosp, numHab+"").getCosto());
            String idVisita = controlVisitas.idVisitas()+"";
            cliente_Hab cliHab = new cliente_Hab("", txtCedulaClientHab.getText(), numHab+"", consumo+"", metodo.DateAString(datChosFechaIngHab.getDate()), metodo.DateAString(datChosFechaSalHab.getDate()),idVisita, "14:00",txtAbonoClientHab.getText(),metodo.deDateAHora24(new Date()));
            visita vis = new visita(idVisita, txtCedulaClientHab.getText(),txtNombresClientHab.getText(),txtDireccionClientHab.getText(),numHab+"",metodo.DateAString(datChosFechaIngHab.getDate()),metodo.DateAString(datChosFechaSalHab.getDate()),cantDias+"",consumo+"",txtObservClientHab.getText(),cmbTipoCliHab.getSelectedItem()+"");
            if (controlCliHab.ingresarClienteHab(cliHab)) {
                if (controlVisitas.ingresarVisitas(vis)) {
                    controlCliente.actualizarEstadoCliente("Hab "+numHab, txtCedulaClientHab.getText());
                    clientes = controlCliente.listarClientes("");
                    clienteHabitaciones = controlCliHab.listarCliente_Hab("");
                    visitas = controlVisitas.listarVisitas("");
                    if (!controlHab.buscarHabitacion(numHab+"", habitaciones).getEstado().equals("Ocupado")) {
                        controlHab.actualizarEstadoHabitacion(numHab+"", "Ocupado");
                        habitaciones = controlHab.listarHabitacion("");
                        actBtnHab();
                        contHabRes = 0;
                        tiempoHabRes.start();
                    }
                    metodo.mensajeDialogo(metodo.personalizarMsj("Cliente ingresado con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                    List<cliente_Hab> clienteHab = controlCliHab.buscarClienteNumHab(numHab+"", clienteHabitaciones);
                    metodo.fondoTablaInvisible(tablaHab, jScrollPane2, controlCliHab.MostrarTablaClienteHab2(clienteHab, clientes, visitas, consumos), anchosTblClienteHab, tamañoLetra);
                    lblCostoHab.setText("$"+metodo.redondearCerDer(Double.parseDouble(controlCliHab.obtenerCostoTotalHab(numHab+"", clienteHab, consumos, new Date())), 2));
                    metodo.actCapMax(lblCantHuesp1, habitaciones, clienteHabitaciones);
                    limpiarTxtClienteHab(true, numHab+"");
                    metodo.enfocarTxt(txtCedulaClientHab);
                }else{
                    metodo.mensajeDialogo(metodo.personalizarMsj("Error al ingresar la visita", metodo.rojo, "times new roman", metodo.titulo2, true, false, false), "Error", metodo.error);
                }
            }else{
                metodo.mensajeDialogo(metodo.personalizarMsj("Error al ingresar el cliente a la Habitación "+numHab, metodo.rojo, "times new roman", metodo.titulo2, true, false, false), "Error", metodo.error);
            }
        }
    }
    
    private void btnGuardarClienteHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteHabActionPerformed
        String numHab = lblTituloPanelHab.getText().split(" ")[2];
        if (controlCliente.cliExistente(txtCedulaClientHab.getText(), clientes)) {
            ingresarCliHab(Integer.parseInt(numHab));
        }else{
            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("Cliente no existente en la base de datos, ¿Desea ingresarlo?", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Aviso", metodo.pregunta)) {
                cliente cli = new cliente(txtCedulaClientHab.getText(), txtNombresClientHab.getText(), txtDireccionClientHab.getText(), txtTelefonoClientHab.getText(), "Hab "+numHab, txtObservClientHab.getText(), txtCorreoClientHab.getText());
                if (controlCliente.ingresarCliente(cli)) {
                    metodo.mensajeDialogo(metodo.personalizarMsj("Cliente engresado con éxito!", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                    ingresarCliHab(Integer.parseInt(numHab));
                    clientes = controlCliente.listarClientes("");
                }
            }else{
                limpiarTxtClienteHab(false, numHab);
                btnGuardarClienteHab.setEnabled(false);
                metodo.enfocarTxt(txtCedulaClientHab);
            }
        }
    }//GEN-LAST:event_btnGuardarClienteHabActionPerformed

    private void btnLimpiarClienteHabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteHabMouseEntered
        metodo.cambiarImgBoton(btnLimpiarClienteHab, "limpiar2.png");
    }//GEN-LAST:event_btnLimpiarClienteHabMouseEntered

    private void btnLimpiarClienteHabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteHabMouseExited
        metodo.cambiarImgBoton(btnLimpiarClienteHab, "limpiar1.png");
    }//GEN-LAST:event_btnLimpiarClienteHabMouseExited

    private void btnLimpiarClienteHabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteHabMousePressed
        metodo.cambiarImgBoton(btnLimpiarClienteHab, "limpiar3.png");
    }//GEN-LAST:event_btnLimpiarClienteHabMousePressed

    private void btnLimpiarClienteHabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteHabMouseReleased
        metodo.cambiarImgBoton(btnLimpiarClienteHab, "limpiar2.png");
    }//GEN-LAST:event_btnLimpiarClienteHabMouseReleased

    private void btnLimpiarClienteHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClienteHabActionPerformed
        String numHab = lblTituloPanelHab.getText().split(" ")[2];
        limpiarTxtClienteHab(true, numHab);
    }//GEN-LAST:event_btnLimpiarClienteHabActionPerformed

    private void cmbTipoCliHabItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoCliHabItemStateChanged
        String numHab = lblTituloPanelHab.getText().split(" ")[2];
        if (bandSubHab) {
            String tipo = cmbTipoCliHab.getSelectedItem().toString();
            double costo = Double.parseDouble(controlValorHops.buscarValorHosp(tipo, valoresHosp, numHab).getCosto());
            double cantDias = metodo.cantDias(metodo.DateAString(datChosFechaIngHab.getDate()), metodo.DateAString(datChosFechaSalHab.getDate()));
            lblCostoIndCli.setText("$"+metodo.redondearCerDer(costo*cantDias, 2));
        }
    }//GEN-LAST:event_cmbTipoCliHabItemStateChanged

    private void txtCedulaClientHabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClientHabKeyPressed
        metodo.SNumeros(txtCedulaClientHab, "numero");
    }//GEN-LAST:event_txtCedulaClientHabKeyPressed

    private void txtCedulaClientHabKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClientHabKeyReleased
        String numHab = lblTituloPanelHab.getText().split(" ")[2];
        /*
        if (metodo.validadorDeCedula(txtCedulaClientHab.getText())) {
            lblVistoClienteHab.setText("√");
            lblVistoClienteHab.setForeground(Color.green);
            lblVistoClienteHab.setToolTipText("Número de cédula válido");
        }else{
            if (txtCedulaClientHab.getText().equals("")) {
                lblVistoClienteHab.setText("");
                lblVistoClienteHab.setForeground(Color.red);
            }else{
                lblVistoClienteHab.setText("х");
                lblVistoClienteHab.setForeground(Color.red);
                lblVistoClienteHab.setToolTipText("Número de cédula no válido");
            }
        }*/
        try{
            metodo.vistoEquis(metodo.validadorDeCedula(txtCedulaClientHab.getText()), lblVistoClienteHab);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        if (metodo.validadorDeCedula(txtCedulaClientHab.getText())) {
            //lblVistoCedulaRes.setText("√");
            //lblVistoCedulaRes.setForeground(Color.green);
            lblVistoClienteHab.setToolTipText("Número de cédula válido");
        }else{
            if (txtCedulaClientHab.getText().equals("")) {
                metodo.cambiarImgLbl(lblVistoClienteHab, "");
                //lblVistoCedulaRes.setText("");
                //lblVistoCedulaRes.setForeground(Color.red);
            }else{
                //lblVistoCedulaRes.setText("х");
                //lblVistoCedulaRes.setForeground(Color.red);
                lblVistoClienteHab.setToolTipText("Número de cédula no válido");
            }
        }
        if (!txtCedulaClientHab.getText().equals("")&&!txtNombresClientHab.getText().equals("")) {
            btnGuardarClienteHab.setEnabled(true);
        }else{
            btnGuardarClienteHab.setEnabled(false);
        }
        if (controlCliente.cliExistente(txtCedulaClientHab.getText(), clientes)) {
            cliente cli = controlCliente.buscarCliente(txtCedulaClientHab.getText(), clientes);
            txtNombresClientHab.setText(cli.getNombres());
            txtDireccionClientHab.setText(cli.getDireccion());
            txtObservClientHab.setText(cli.getObservaciones());
            txtCorreoClientHab.setText(cli.getCorreo());
            txtTelefonoClientHab.setText(cli.getTelefono());
            metodo.txtEditable(txtClienteHab(), false);
            txtCedulaClientHab.setEditable(true);
            btnGuardarClienteHab.setEnabled(true);
        }else{
            metodo.txtEditable(txtClienteHab(), true);
            for (int i = 1; i < txtClienteHab().length; i++) {
                txtClienteHab()[i].setText("");
            }
        }
        int idHab = 0;
        idHab=controlCliente.cliOcHab(txtCedulaClientHab.getText(), clientes);
        if (idHab>0) {
            limpiarTxtClienteHab(true, idHab+"");
            metodo.txtEditable(txtClienteHab(), true);
            btnGuardarClienteHab.setEnabled(false);
            lblVistoClienteHab.setText("");
            if (idHab==Integer.parseInt(numHab)) {
                metodo.mensajeDialogo(metodo.personalizarMsj("El cliente ya se encuentra en esta habitación", metodo.rojo, "times new roman", metodo.titulo1, false, false, false), "Error", metodo.error);
            }else{
                metodo.mensajeDialogo(metodo.personalizarMsj("El cliente se encuentra en habitación "+idHab, metodo.rojo, "times new roman", metodo.titulo1, false, false, false), "Error", metodo.error);
            }
        }
        if (txtCedulaClientHab.getText().equals("")) {
            lblVistoClienteHab.setToolTipText("Ingrese número de cédula");
        }
    }//GEN-LAST:event_txtCedulaClientHabKeyReleased
    
    String abonoHab = "0.00";
    private void txtAbonoClientHabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoClientHabKeyPressed
        metodo.SNumeros(txtAbonoClientHab, "decimal");
        abonoHab = txtAbonoClientHab.getText();
    }//GEN-LAST:event_txtAbonoClientHabKeyPressed

    private void txtAbonoClientHabKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoClientHabKeyReleased
        if (!metodo.isDouble(txtAbonoClientHab.getText())&&!txtAbonoClientHab.getText().equals("")) {
            txtAbonoClientHab.setText(abonoHab);
        }
    }//GEN-LAST:event_txtAbonoClientHabKeyReleased

    private void txtNombresClientHabKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresClientHabKeyReleased
        if (!txtCedulaClientHab.getText().equals("")&&!txtNombresClientHab.getText().equals("")) {
            btnGuardarClienteHab.setEnabled(true);
        }else{
            btnGuardarClienteHab.setEnabled(false);
        }
    }//GEN-LAST:event_txtNombresClientHabKeyReleased

    private void datChosFechaIngHabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datChosFechaIngHabPropertyChange

        if (bandSubHab) {
            String numHab = lblTituloPanelHab.getText().split(" ")[2];
            int cantDias = datChosFechaIngHab.getDate().compareTo(datChosFechaSalHab.getDate());
            if (cantDias==1) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser inferior a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                Date fecha = datChosFechaIngHab.getDate();
                fecha.setTime(fecha.getTime()+(24*60*60*1000));
                datChosFechaSalHab.setDate(fecha);
            }
            if (cantDias==0) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser igual a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                Date fecha = datChosFechaIngHab.getDate();
                fecha.setTime(fecha.getTime()+(24*60*60*1000));
                datChosFechaSalHab.setDate(fecha);
            }
            if (cantDias<0) {
                String tipo = cmbTipoCliHab.getSelectedItem().toString();
                double costo = Double.parseDouble(controlValorHops.buscarValorHosp(tipo, valoresHosp, numHab).getCosto());
                cantDias = metodo.cantDias(metodo.DateAString(datChosFechaIngHab.getDate()), metodo.DateAString(datChosFechaSalHab.getDate()));
                lblCostoIndCli.setText("$"+metodo.redondearCerDer(costo*cantDias, 2));
            }
        }
    }//GEN-LAST:event_datChosFechaIngHabPropertyChange

    private void datChosFechaSalHabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datChosFechaSalHabPropertyChange

        if (bandSubHab) {
            String numHab = lblTituloPanelHab.getText().split(" ")[2];
            int cantDias = datChosFechaIngHab.getDate().compareTo(datChosFechaSalHab.getDate());
            if (cantDias==1) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser inferior a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                Date fecha = datChosFechaIngHab.getDate();
                fecha.setTime(fecha.getTime()+(24*60*60*1000));
                datChosFechaSalHab.setDate(fecha);
            }
            if (cantDias==0) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser igual a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                Date fecha = datChosFechaIngHab.getDate();
                fecha.setTime(fecha.getTime()+(24*60*60*1000));
                datChosFechaSalHab.setDate(fecha);
            }
            if (cantDias<0) {
                String tipo = cmbTipoCliHab.getSelectedItem().toString();
                double costo = Double.parseDouble(controlValorHops.buscarValorHosp(tipo, valoresHosp, numHab).getCosto());
                cantDias = metodo.cantDias(metodo.DateAString(datChosFechaIngHab.getDate()), metodo.DateAString(datChosFechaSalHab.getDate()));
                lblCostoIndCli.setText("$"+metodo.redondearCerDer(costo*cantDias, 2));
            }
        }
    }//GEN-LAST:event_datChosFechaSalHabPropertyChange

    private void spnTablaClienteHabPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnTablaClienteHabPropertyChange
        if (bandSubHab) {
            metodo.tamañoFuenteTablaSpn(spnTablaClienteHab, tablaHab);
        }
    }//GEN-LAST:event_spnTablaClienteHabPropertyChange

    private void btnHab1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab1MouseEntered
        metodo.cambiarImgLbl(lblHab1, controlHab.imgEstadoHab(1+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab1MouseEntered

    private void btnHab1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab1MouseExited
        metodo.cambiarImgLbl(lblHab1, controlHab.imgEstadoHab(1+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab1MouseExited

    private void btnHab1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab1MousePressed
        metodo.cambiarImgLbl(lblHab1, controlHab.imgEstadoHab(1+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab1MousePressed

    private void btnHab1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab1MouseReleased
        metodo.cambiarImgLbl(lblHab1, controlHab.imgEstadoHab(1+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab1MouseReleased

    private void btnHab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab1ActionPerformed
        acHab(1);
    }//GEN-LAST:event_btnHab1ActionPerformed

    private void btnHab2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab2MouseEntered
        metodo.cambiarImgLbl(lblHab2, controlHab.imgEstadoHab(2+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab2MouseEntered

    private void btnHab2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab2MouseExited
        metodo.cambiarImgLbl(lblHab2, controlHab.imgEstadoHab(2+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab2MouseExited

    private void btnHab2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab2MousePressed
        metodo.cambiarImgLbl(lblHab2, controlHab.imgEstadoHab(2+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab2MousePressed

    private void btnHab2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab2MouseReleased
        metodo.cambiarImgLbl(lblHab2, controlHab.imgEstadoHab(2+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab2MouseReleased

    private void btnHab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab2ActionPerformed
        acHab(2);
    }//GEN-LAST:event_btnHab2ActionPerformed

    private void btnHab3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab3MouseEntered
        metodo.cambiarImgLbl(lblHab3, controlHab.imgEstadoHab(3+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab3MouseEntered

    private void btnHab3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab3MouseExited
        metodo.cambiarImgLbl(lblHab3, controlHab.imgEstadoHab(3+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab3MouseExited

    private void btnHab3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab3MousePressed
        metodo.cambiarImgLbl(lblHab3, controlHab.imgEstadoHab(3+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab3MousePressed

    private void btnHab3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab3MouseReleased
        metodo.cambiarImgLbl(lblHab3, controlHab.imgEstadoHab(3+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab3MouseReleased

    private void btnHab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab3ActionPerformed
        acHab(3);
    }//GEN-LAST:event_btnHab3ActionPerformed

    private void btnHab4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab4MouseEntered
        metodo.cambiarImgLbl(lblHab4, controlHab.imgEstadoHab(4+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab4MouseEntered

    private void btnHab4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab4MouseExited
        metodo.cambiarImgLbl(lblHab4, controlHab.imgEstadoHab(4+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab4MouseExited

    private void btnHab4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab4MousePressed
        metodo.cambiarImgLbl(lblHab4, controlHab.imgEstadoHab(4+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab4MousePressed

    private void btnHab4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab4MouseReleased
        metodo.cambiarImgLbl(lblHab4, controlHab.imgEstadoHab(4+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab4MouseReleased

    private void btnHab4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab4ActionPerformed
        acHab(4);
    }//GEN-LAST:event_btnHab4ActionPerformed

    private void btnHab5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab5MouseEntered
        metodo.cambiarImgLbl(lblHab5, controlHab.imgEstadoHab(5+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab5MouseEntered

    private void btnHab5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab5MouseExited
        metodo.cambiarImgLbl(lblHab5, controlHab.imgEstadoHab(5+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab5MouseExited

    private void btnHab5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab5MousePressed
        metodo.cambiarImgLbl(lblHab5, controlHab.imgEstadoHab(5+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab5MousePressed

    private void btnHab5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab5MouseReleased
        metodo.cambiarImgLbl(lblHab5, controlHab.imgEstadoHab(5+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab5MouseReleased

    private void btnHab5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab5ActionPerformed
        acHab(5);
    }//GEN-LAST:event_btnHab5ActionPerformed

    private void btnHab6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab6MouseEntered
        metodo.cambiarImgLbl(lblHab6, controlHab.imgEstadoHab(6+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab6MouseEntered

    private void btnHab6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab6MouseExited
        metodo.cambiarImgLbl(lblHab6, controlHab.imgEstadoHab(6+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab6MouseExited

    private void btnHab6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab6MousePressed
        metodo.cambiarImgLbl(lblHab6, controlHab.imgEstadoHab(6+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab6MousePressed

    private void btnHab6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab6MouseReleased
        metodo.cambiarImgLbl(lblHab6, controlHab.imgEstadoHab(6+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab6MouseReleased

    private void btnHab6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab6ActionPerformed
        acHab(6);
    }//GEN-LAST:event_btnHab6ActionPerformed

    private void btnHab7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab7MouseEntered
        metodo.cambiarImgLbl(lblHab7, controlHab.imgEstadoHab(7+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab7MouseEntered

    private void btnHab7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab7MouseExited
        metodo.cambiarImgLbl(lblHab7, controlHab.imgEstadoHab(7+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab7MouseExited

    private void btnHab7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab7MousePressed
        metodo.cambiarImgLbl(lblHab7, controlHab.imgEstadoHab(7+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab7MousePressed

    private void btnHab7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab7MouseReleased
        metodo.cambiarImgLbl(lblHab7, controlHab.imgEstadoHab(7+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab7MouseReleased

    private void btnHab7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab7ActionPerformed
        acHab(7);
    }//GEN-LAST:event_btnHab7ActionPerformed

    private void btnHab8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab8MouseEntered
        metodo.cambiarImgLbl(lblHab8, controlHab.imgEstadoHab(8+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab8MouseEntered

    private void btnHab8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab8MouseExited
        metodo.cambiarImgLbl(lblHab8, controlHab.imgEstadoHab(8+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab8MouseExited

    private void btnHab8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab8MousePressed
        metodo.cambiarImgLbl(lblHab8, controlHab.imgEstadoHab(8+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab8MousePressed

    private void btnHab8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab8MouseReleased
        metodo.cambiarImgLbl(lblHab8, controlHab.imgEstadoHab(8+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab8MouseReleased

    private void btnHab8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab8ActionPerformed
        acHab(8);
    }//GEN-LAST:event_btnHab8ActionPerformed

    private void btnHab9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab9MouseEntered
        metodo.cambiarImgLbl(lblHab9, controlHab.imgEstadoHab(9+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab9MouseEntered

    private void btnHab9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab9MouseExited
        metodo.cambiarImgLbl(lblHab9, controlHab.imgEstadoHab(9+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab9MouseExited

    private void btnHab9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab9MousePressed
        metodo.cambiarImgLbl(lblHab9, controlHab.imgEstadoHab(9+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab9MousePressed

    private void btnHab9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab9MouseReleased
        metodo.cambiarImgLbl(lblHab9, controlHab.imgEstadoHab(9+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab9MouseReleased

    private void btnHab9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab9ActionPerformed
        acHab(9);
    }//GEN-LAST:event_btnHab9ActionPerformed

    private void btnHab10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab10MouseEntered
        metodo.cambiarImgLbl(lblHab10, controlHab.imgEstadoHab(10+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab10MouseEntered

    private void btnHab10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab10MouseExited
        metodo.cambiarImgLbl(lblHab10, controlHab.imgEstadoHab(10+"", habitaciones, 1));
    }//GEN-LAST:event_btnHab10MouseExited

    private void btnHab10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab10MousePressed
        metodo.cambiarImgLbl(lblHab10, controlHab.imgEstadoHab(10+"", habitaciones, 3));
    }//GEN-LAST:event_btnHab10MousePressed

    private void btnHab10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHab10MouseReleased
        metodo.cambiarImgLbl(lblHab10, controlHab.imgEstadoHab(10+"", habitaciones, 2));
    }//GEN-LAST:event_btnHab10MouseReleased

    private void btnHab10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHab10ActionPerformed
        acHab(10);
    }//GEN-LAST:event_btnHab10ActionPerformed

    private void btnRegresarDiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarDiaMouseEntered
        metodo.cambiarImgBoton(btnRegresarDia, "atras2.png");
    }//GEN-LAST:event_btnRegresarDiaMouseEntered

    private void btnRegresarDiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarDiaMouseExited
        metodo.cambiarImgBoton(btnRegresarDia, "atras.png");
    }//GEN-LAST:event_btnRegresarDiaMouseExited

    private void btnRegresarDiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarDiaMousePressed
        metodo.cambiarImgBoton(btnRegresarDia, "atras3.png");
    }//GEN-LAST:event_btnRegresarDiaMousePressed

    private void btnRegresarDiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarDiaMouseReleased
        metodo.cambiarImgBoton(btnRegresarDia, "atras2.png");
    }//GEN-LAST:event_btnRegresarDiaMouseReleased

    private void btnRegresarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarDiaActionPerformed
        fechaRes.setTime(fechaRes.getTime()-(1000*24*60*60));
        contHabRes = 0;
        tiempoHabRes.start();
    }//GEN-LAST:event_btnRegresarDiaActionPerformed

    private void btnRegresarSemanaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarSemanaMouseEntered
        metodo.cambiarImgBoton(btnRegresarSemana, "Datras2.png");
    }//GEN-LAST:event_btnRegresarSemanaMouseEntered

    private void btnRegresarSemanaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarSemanaMouseExited
        metodo.cambiarImgBoton(btnRegresarSemana, "Datras.png");
    }//GEN-LAST:event_btnRegresarSemanaMouseExited

    private void btnRegresarSemanaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarSemanaMousePressed
        metodo.cambiarImgBoton(btnRegresarSemana, "Datras3.png");
    }//GEN-LAST:event_btnRegresarSemanaMousePressed

    private void btnRegresarSemanaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegresarSemanaMouseReleased
        metodo.cambiarImgBoton(btnRegresarSemana, "Datras2.png");
    }//GEN-LAST:event_btnRegresarSemanaMouseReleased

    private void btnRegresarSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarSemanaActionPerformed
        fechaRes.setTime(fechaRes.getTime()-(7*1000*24*60*60));
        contHabRes = 0;
        tiempoHabRes.start();
    }//GEN-LAST:event_btnRegresarSemanaActionPerformed

    private void btnAvanzarDiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarDiaMouseEntered
        metodo.cambiarImgBoton(btnAvanzarDia, "adelante2.png");
    }//GEN-LAST:event_btnAvanzarDiaMouseEntered

    private void btnAvanzarDiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarDiaMouseExited
        metodo.cambiarImgBoton(btnAvanzarDia, "adelante.png");
    }//GEN-LAST:event_btnAvanzarDiaMouseExited

    private void btnAvanzarDiaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarDiaMousePressed
        metodo.cambiarImgBoton(btnAvanzarDia, "adelante3.png");
    }//GEN-LAST:event_btnAvanzarDiaMousePressed

    private void btnAvanzarDiaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarDiaMouseReleased
        metodo.cambiarImgBoton(btnAvanzarDia, "adelante2.png");
    }//GEN-LAST:event_btnAvanzarDiaMouseReleased

    private void btnAvanzarDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarDiaActionPerformed
        fechaRes.setTime(fechaRes.getTime()+(1000*24*60*60));
        contHabRes = 0;
        tiempoHabRes.start();
    }//GEN-LAST:event_btnAvanzarDiaActionPerformed

    private void btnAvanzarSemanaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarSemanaMouseEntered
        metodo.cambiarImgBoton(btnAvanzarSemana, "Dadelante2.png");
    }//GEN-LAST:event_btnAvanzarSemanaMouseEntered

    private void btnAvanzarSemanaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarSemanaMouseExited
        metodo.cambiarImgBoton(btnAvanzarSemana, "Dadelante.png");
    }//GEN-LAST:event_btnAvanzarSemanaMouseExited

    private void btnAvanzarSemanaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarSemanaMousePressed
        metodo.cambiarImgBoton(btnAvanzarSemana, "Dadelante3.png");
    }//GEN-LAST:event_btnAvanzarSemanaMousePressed

    private void btnAvanzarSemanaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAvanzarSemanaMouseReleased
        metodo.cambiarImgBoton(btnAvanzarSemana, "Dadelante2.png");
    }//GEN-LAST:event_btnAvanzarSemanaMouseReleased

    private void btnAvanzarSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvanzarSemanaActionPerformed
        fechaRes.setTime(fechaRes.getTime()+(7*1000*24*60*60));
        contHabRes = 0;
        tiempoHabRes.start();
    }//GEN-LAST:event_btnAvanzarSemanaActionPerformed

    private void lblRes00MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRes00MouseClicked
        fechaRes = new Date();
        contHabRes=0;
        tiempoHabRes.start();
    }//GEN-LAST:event_lblRes00MouseClicked

    private void btnAgregarReservacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarReservacionMouseEntered
        metodo.cambiarImgBoton(btnAgregarReservacion, "aggCliente2.png");
    }//GEN-LAST:event_btnAgregarReservacionMouseEntered

    private void btnAgregarReservacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarReservacionMouseExited
        metodo.cambiarImgBoton(btnAgregarReservacion, "aggCliente1.png");
    }//GEN-LAST:event_btnAgregarReservacionMouseExited

    private void btnAgregarReservacionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarReservacionMousePressed
        metodo.cambiarImgBoton(btnAgregarReservacion, "aggCliente3.png");
    }//GEN-LAST:event_btnAgregarReservacionMousePressed
    String idRes;
    public void actualizarRes(){
        String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
        String abono="0.00";
        if (!txtAbonoRes.getText().equals("")) {
            abono=txtAbonoRes.getText();
        }
        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de actualizar esta reservación?", metodo.rojo, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
            reservacion r = new reservacion(idRes, idHab, txtCedulaRes.getText(), metodo.DateAString(choosFechIngRes.getDate()),metodo.DateAString(choosFechSalRes.getDate()),abono,txtObservRes.getText(),txtNombresRes.getText(),txtDireccionRes.getText(),metodo.de12A24Horas(txtHoraRes.getText()),"12:00",spinCantAdultRes.getValue().toString()+" Adulto(s) y "+spinCantNiñoRes.getValue().toString()+" Niño(s)",spinCantDiasRes.getValue().toString(),lblTotalRes.getText().split(" ")[1]);
            if (controlReserv.actualizarReservaciones(r, idRes)) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Reservación actualizada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                contHabRes=0;
                reservaciones = controlReserv.listarReservaciones("");
                tiempoHabRes.start();
                metodo.fondoTablaInvisible(TablaReservacion, jScrollPane3, controlReserv.MostrarTablaReservaciones(reservaciones), anchosTblReservacion, tamañoLetra);
                limpiarTxtClienteRes(true, idHab);
                verCampRes(false);
                upRes=false;
            }
        }
    }
    
    private void btnAgregarReservacionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarReservacionMouseReleased
        metodo.cambiarImgBoton(btnAgregarReservacion, "aggCliente2.png");
    }//GEN-LAST:event_btnAgregarReservacionMouseReleased

    private void btnAgregarReservacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarReservacionActionPerformed
        String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
        if (upRes) {
            if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Desea guardar los cambios realizados en la reservación?", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
                actualizarRes();
            }
        }else{
            upRes=false;
            verCampRes(true);
            limpiarTxtClienteRes(true, idHab);
            metodo.enfocarTxt(txtCedulaRes);
        }
    }//GEN-LAST:event_btnAgregarReservacionActionPerformed

    private void txtCedulaResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaResKeyPressed
        metodo.SNumeros(txtCedulaRes, "numero");
    }//GEN-LAST:event_txtCedulaResKeyPressed

    private void txtCedulaResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaResKeyReleased
        try{
            metodo.vistoEquis(metodo.validadorDeCedula(txtCedulaRes.getText()), lblVistoCedulaRes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        if (metodo.validadorDeCedula(txtCedulaRes.getText())) {
            //lblVistoCedulaRes.setText("√");
            //lblVistoCedulaRes.setForeground(Color.green);
            lblVistoCedulaRes.setToolTipText("Número de cédula válido");
        }else{
            if (txtCedulaRes.getText().equals("")) {
                metodo.cambiarImgLbl(lblVistoCedulaRes, "");
                //lblVistoCedulaRes.setText("");
                //lblVistoCedulaRes.setForeground(Color.red);
            }else{
                //lblVistoCedulaRes.setText("х");
                //lblVistoCedulaRes.setForeground(Color.red);
                lblVistoCedulaRes.setToolTipText("Número de cédula no válido");
            }
        }
        if (controlCliente.cliExistente(txtCedulaRes.getText(), clientes)) {
            cliente cli = controlCliente.buscarCliente(txtCedulaRes.getText(), clientes);
            txtNombresRes.setText(cli.getNombres());
            txtDireccionRes.setText(cli.getDireccion());
            txtObservRes.setText(cli.getObservaciones());
            txtCorreoRes.setText(cli.getCorreo());
            txtTelefonoRes.setText(cli.getTelefono());
            metodo.txtEditable(txtClienteRes(), false);
            txtCedulaRes.setEditable(true);
            btnGuardarRes.setEnabled(true);
        }else{
            metodo.txtEditable(txtClienteRes(), true);
        }
        if (txtNombresRes.getText().equals("")||txtCedulaRes.getText().equals("")) {
            btnGuardarRes.setEnabled(false);
        }else{
            btnGuardarRes.setEnabled(true);
        }
    }//GEN-LAST:event_txtCedulaResKeyReleased

    private void CuadriculaResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CuadriculaResActionPerformed
        if (!lblRes00.isVisible()) {
            jScrollPane3.setVisible(false);
            spinTamTblRes.setVisible(false);
            for (int i = 0; i < labelRes().length; i++) {
                for (int j = 0; j < labelRes()[i].length; j++) {
                    labelRes()[i][j].setVisible(true);
                    labelResF()[i][j].setVisible(true);
                }
            }
            btnAvanzarDia.setVisible(true);
            btnAvanzarSemana.setVisible(true);
            btnRegresarSemana.setVisible(true);
            btnRegresarDia.setVisible(true);
            //metodo.fondoTablaInvisible(TablaReservacion, jScrollPane3, controlReserv.MostrarTablaReservaciones(reservaciones), anchosTblReservacion);
        }
    }//GEN-LAST:event_CuadriculaResActionPerformed

    private void TablaResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TablaResActionPerformed
        if (!jScrollPane3.isVisible()) {
            //System.out.println("entro");
            jScrollPane3.setVisible(true);
            spinTamTblRes.setVisible(true);
            for (int i = 0; i < labelRes().length; i++) {
                for (int j = 0; j < labelRes()[i].length; j++) {
                    labelRes()[i][j].setVisible(false);
                    labelResF()[i][j].setVisible(false);
                }
            }
            btnAvanzarDia.setVisible(false);
            btnAvanzarSemana.setVisible(false);
            btnRegresarSemana.setVisible(false);
            btnRegresarDia.setVisible(false);
            metodo.fondoTablaInvisible(TablaReservacion, jScrollPane3, controlReserv.MostrarTablaReservaciones(reservaciones), anchosTblReservacion, tamañoLetra);
            metodo.tamañoFuenteTablaSpn(spinTamTblRes, TablaReservacion);
        }

    }//GEN-LAST:event_TablaResActionPerformed

    private void timePicker1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_timePicker1PropertyChange

    }//GEN-LAST:event_timePicker1PropertyChange

    private void txtNombresResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresResKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresResKeyPressed

    private void txtNombresResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombresResKeyReleased
        if (txtNombresRes.getText().equals("")||txtCedulaRes.getText().equals("")) {
            btnGuardarRes.setEnabled(false);
        }else{
            btnGuardarRes.setEnabled(true);
        }
    }//GEN-LAST:event_txtNombresResKeyReleased

    private void txtDireccionResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionResKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionResKeyPressed

    private void txtDireccionResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionResKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionResKeyReleased

    private void txtTelefonoResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoResKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoResKeyPressed

    private void txtTelefonoResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoResKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoResKeyReleased

    private void txtCorreoResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoResKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoResKeyPressed

    private void txtCorreoResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoResKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoResKeyReleased

    private void txtObservResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservResKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservResKeyPressed

    private void txtObservResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservResKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservResKeyReleased
    String abono="";
    private void txtAbonoResKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoResKeyPressed
        metodo.SNumeros(txtAbonoRes, "decimal");
        abono = txtAbonoRes.getText();
    }//GEN-LAST:event_txtAbonoResKeyPressed

    private void txtAbonoResKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbonoResKeyReleased
        if (!metodo.isDouble(txtAbonoRes.getText())&&!txtAbonoRes.getText().equals("")) {
            txtAbonoRes.setText(abono);
        }
    }//GEN-LAST:event_txtAbonoResKeyReleased

    private void choosFechIngResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_choosFechIngResPropertyChange

        if (bandReserv) {
            String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
            int cantDias = choosFechIngRes.getDate().compareTo(choosFechSalRes.getDate());
            //System.out.println("cant: "+cantDias);
            if (cantDias==1) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser inferior a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                //choosFechIngRes.setDate(new Date());
                Date fecha = choosFechIngRes.getDate();
                spinCantDiasRes.setValue(1);
                fecha.setTime(fecha.getTime()+Integer.parseInt(spinCantDiasRes.getValue().toString())*24*60*60*1000);
                choosFechSalRes.setDate(fecha);
            }
            if (cantDias==0) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser igual a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                Date fecha = choosFechIngRes.getDate();
                spinCantDiasRes.setValue(1);
                fecha.setTime(fecha.getTime()+Integer.parseInt(spinCantDiasRes.getValue().toString())*24*60*60*1000);
                choosFechSalRes.setDate(fecha);
            }
            if (cantDias<0) {
                double tam = tamañoLetra*2;
                int tamaño = (int)tam;
                cantDias = metodo.cantDias(metodo.DateAString(choosFechIngRes.getDate()), metodo.DateAString(choosFechSalRes.getDate()));
                spinCantDiasRes.setValue(cantDias);
                String cost = controlReserv.calcCostRes(Integer.parseInt(spinCantAdultRes.getValue().toString()), Integer.parseInt(spinCantNiñoRes.getValue().toString()), Integer.parseInt(spinCantDiasRes.getValue().toString()), valoresHosp, idHab);
                lblTotalRes.setText(cost);
                int cantDigitos = cost.length();
                if (cantDigitos>8) {
                    //System.out.println("entro "+ cantDigitos);
                    double cantPerd = cantDigitos-8;
                    //System.out.println("cantPerd "+cantPerd);
                    tam = tam*cantPerd/10;
                    //System.out.println("tam0: "+tam);
                    tamaño = (tamañoLetra*2) - (int)tam;
                    //System.out.println("entro2 "+tamaño);
                    lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, tamaño));
                    //System.out.println("entro3");
                }else{
                    lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, (int)tamaño));
                }
                //System.out.println("cost: "+cost+" - digitos: "+cantDigitos);
                //System.out.println("tam: "+tamaño);
                //metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser igual a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
            }

        }
    }//GEN-LAST:event_choosFechIngResPropertyChange

    private void choosFechSalResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_choosFechSalResPropertyChange

        if (bandReserv) {
            String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
            int cantDias = choosFechIngRes.getDate().compareTo(choosFechSalRes.getDate());
            //System.out.println("cant: "+cantDias);
            if (cantDias==1) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser inferior a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                //choosFechIngRes.setDate(new Date());
                Date fecha = choosFechIngRes.getDate();
                spinCantDiasRes.setValue(1);
                fecha.setTime(fecha.getTime()+Integer.parseInt(spinCantDiasRes.getValue().toString())*24*60*60*1000);
                choosFechSalRes.setDate(fecha);
            }
            if (cantDias==0) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser igual a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                Date fecha = choosFechIngRes.getDate();
                spinCantDiasRes.setValue(1);
                fecha.setTime(fecha.getTime()+Integer.parseInt(spinCantDiasRes.getValue().toString())*24*60*60*1000);
                choosFechSalRes.setDate(fecha);
            }
            if (cantDias<0) {
                double tam = tamañoLetra*2;
                int tamaño = (int)tam;
                cantDias = metodo.cantDias(metodo.DateAString(choosFechIngRes.getDate()), metodo.DateAString(choosFechSalRes.getDate()));
                spinCantDiasRes.setValue(cantDias);
                String cost = controlReserv.calcCostRes(Integer.parseInt(spinCantAdultRes.getValue().toString()), Integer.parseInt(spinCantNiñoRes.getValue().toString()), Integer.parseInt(spinCantDiasRes.getValue().toString()), valoresHosp, idHab);
                lblTotalRes.setText(cost);
                int cantDigitos = cost.length();
                if (cantDigitos>8) {
                    //System.out.println("entro "+ cantDigitos);
                    double cantPerd = cantDigitos-8;
                    //System.out.println("cantPerd "+cantPerd);
                    tam = tam*cantPerd/10;
                    //System.out.println("tam0: "+tam);
                    tamaño = (tamañoLetra*2) - (int)tam;
                    //System.out.println("entro2 "+tamaño);
                    lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, tamaño));
                    //System.out.println("entro3");
                }else{
                    lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, (int)tamaño));
                }
                //System.out.println("cost: "+cost+" - digitos: "+cantDigitos);
                //System.out.println("tam: "+tamaño);
                //metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("La fecha de salida no puede ser igual a la fecha de ingreso", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
            }

        }
    }//GEN-LAST:event_choosFechSalResPropertyChange

    private void btnGuardarResMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarResMouseEntered
        metodo.cambiarImgBoton(btnGuardarRes, "guardar2.png");
    }//GEN-LAST:event_btnGuardarResMouseEntered

    private void btnGuardarResMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarResMouseExited
        metodo.cambiarImgBoton(btnGuardarRes, "guardar1.png");
    }//GEN-LAST:event_btnGuardarResMouseExited

    private void btnGuardarResMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarResMousePressed
        metodo.cambiarImgBoton(btnGuardarRes, "guardar3.png");
    }//GEN-LAST:event_btnGuardarResMousePressed

    private void btnGuardarResMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarResMouseReleased
        metodo.cambiarImgBoton(btnGuardarRes, "guardar2.png");
    }//GEN-LAST:event_btnGuardarResMouseReleased

    private void btnGuardarResActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarResActionPerformed
        String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
        String abono="0.00";
        List<reservacion> listRes = reservaciones;
        if (upRes) {
            for (int i = 0; i < listRes.size(); i++) {
                if (listRes.get(i).getId().equals(idRes)) {
                    listRes.remove(i);
                }
            }
            if (controlReserv.validarFechasReserv(idHab, listRes, metodo.DateAString(choosFechIngRes.getDate()), metodo.DateAString(choosFechSalRes.getDate()))) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Una o varias de las fechas ya están reservadas", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
            }else{
                actualizarRes();
            }
        }else{
            if (!txtAbonoRes.getText().equals("")) {
                abono=txtAbonoRes.getText();
            }
            if (txtCedulaRes.getText().equals("")||txtNombresRes.getText().equals("")) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("No es posible reservar sin identificación o nombres del cliente", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
            }else{
                if (controlReserv.validarFechasReserv(idHab, reservaciones, metodo.DateAString(choosFechIngRes.getDate()), metodo.DateAString(choosFechSalRes.getDate()))) {
                    metodo.mensajeDialogo(metodo.personalizarMsj("Error", metodo.rojo, "times new roman", metodo.titulo1, true, false, false)+"\n"+metodo.personalizarMsj("Una o varias de las fechas ya están reservadas", metodo.negro, "times new roman", metodo.titulo2, false, false, false), "Error", metodo.error);
                }else{
                    if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de realizar esta reservación?", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Reservación", metodo.pregunta)) {
                        reservacion r = new reservacion("", idHab, txtCedulaRes.getText(), metodo.DateAString(choosFechIngRes.getDate()),metodo.DateAString(choosFechSalRes.getDate()),abono,txtObservRes.getText(),txtNombresRes.getText(),txtDireccionRes.getText(),metodo.de12A24Horas(txtHoraRes.getText()),"12:00",spinCantAdultRes.getValue().toString()+" Adulto(s) y "+spinCantNiñoRes.getValue().toString()+" Niño(s)",spinCantDiasRes.getValue().toString(),lblTotalRes.getText().split(" ")[1]);
                        if (controlReserv.ingresarReservaciones(r)) {
                            metodo.mensajeDialogo(metodo.personalizarMsj("Reservación ingresada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                            contHabRes=0;
                            reservaciones = controlReserv.listarReservaciones("");
                            tiempoHabRes.start();
                            limpiarTxtClienteRes(true, idHab);
                            verCampRes(false);
                        }
                    }
                }
            }
        }
        actBtnHab();
    }//GEN-LAST:event_btnGuardarResActionPerformed

    private void spinCantDiasResStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinCantDiasResStateChanged
        if (bandReserv) {
            Date fechaIng = choosFechIngRes.getDate();
            for (int i = 0; i < Integer.parseInt(spinCantDiasRes.getValue().toString()); i++) {
                fechaIng.setTime(fechaIng.getTime()+(24*60*60*1000));
            }
            //fechaIng.setTime(fechaIng.getTime()+(spinCantDiasRes.getValue()*24*60*60*1000));
            choosFechSalRes.setDate(fechaIng);
        }
    }//GEN-LAST:event_spinCantDiasResStateChanged

    private void spinCantDiasResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinCantDiasResPropertyChange
        /*
        if (bandReserv) {
            Date fechaIng = choosFechIngRes.getDate();
            for (int i = 0; i < jSpinner1.getValue(); i++) {
                fechaIng.setTime(fechaIng.getTime()+(24*60*60*1000));
            }
            //fechaIng.setTime(fechaIng.getTime()+(spinCantDiasRes.getValue()*24*60*60*1000));
            choosFechSalRes.setDate(fechaIng);
        }*/
        //System.out.println("-"+jSpinner1.getValue().toString());
    }//GEN-LAST:event_spinCantDiasResPropertyChange

    private void spinCantNiñoResStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinCantNiñoResStateChanged
        String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
        if (bandReserv) {
            int cantDias = choosFechIngRes.getDate().compareTo(choosFechSalRes.getDate());
            double tam = tamañoLetra*2;
            int tamaño = (int)tam;
            cantDias = metodo.cantDias(metodo.DateAString(choosFechIngRes.getDate()), metodo.DateAString(choosFechSalRes.getDate()));
            spinCantDiasRes.setValue(cantDias);
            String cost = controlReserv.calcCostRes(Integer.parseInt(spinCantAdultRes.getValue().toString()), Integer.parseInt(spinCantNiñoRes.getValue().toString()), Integer.parseInt(spinCantDiasRes.getValue().toString()), valoresHosp, idHab);
            lblTotalRes.setText(cost);
            int cantDigitos = cost.length();
            if (cantDigitos>8) {
                double cantPerd = cantDigitos-8;
                tam = tam*cantPerd/10;
                tamaño = (tamañoLetra*2) - (int)tam;
                lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, tamaño));
            }else{
                lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, (int)tamaño));
            }
            //System.out.println("tam: "+tamaño);
        }
    }//GEN-LAST:event_spinCantNiñoResStateChanged

    private void spinCantNiñoResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinCantNiñoResPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_spinCantNiñoResPropertyChange

    private void spinCantAdultResStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinCantAdultResStateChanged
        String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
        if (bandReserv) {
            int cantDias = choosFechIngRes.getDate().compareTo(choosFechSalRes.getDate());
            double tam = tamañoLetra*2;
            int tamaño = (int)tam;
            cantDias = metodo.cantDias(metodo.DateAString(choosFechIngRes.getDate()), metodo.DateAString(choosFechSalRes.getDate()));
            spinCantDiasRes.setValue(cantDias);
            String cost = controlReserv.calcCostRes(Integer.parseInt(spinCantAdultRes.getValue().toString()), Integer.parseInt(spinCantNiñoRes.getValue().toString()), Integer.parseInt(spinCantDiasRes.getValue().toString()), valoresHosp, idHab);
            lblTotalRes.setText(cost);
            int cantDigitos = cost.length();
            if (cantDigitos>8) {
                double cantPerd = cantDigitos-8;
                tam = tam*cantPerd/10;
                tamaño = (tamañoLetra*2) - (int)tam;
                lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, tamaño));
            }else{
                lblTotalRes.setFont(new Font("Times New Roman", Font.BOLD, (int)tamaño));
            }
            //System.out.println("tam: "+tamaño);
        }
    }//GEN-LAST:event_spinCantAdultResStateChanged

    private void spinCantAdultResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinCantAdultResPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_spinCantAdultResPropertyChange

    private void spinTamTblResStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinTamTblResStateChanged
        if (bandReserv) {
            metodo.tamañoFuenteTablaSpn(spinTamTblRes, TablaReservacion);
        }
    }//GEN-LAST:event_spinTamTblResStateChanged

    private void spinTamTblResPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spinTamTblResPropertyChange

    }//GEN-LAST:event_spinTamTblResPropertyChange

    private void TablaReservacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaReservacionMouseClicked
        String idHab = cmbHabRes.getSelectedItem().toString().split(" ")[1];
        if (evt.getClickCount()==2) {
            idRes = metodo.deTblAString(TablaReservacion, 0);
            int cantAd = Integer.parseInt(metodo.deTblAString(TablaReservacion, 6).split(" ")[0]);
            int cantNi = Integer.parseInt(metodo.deTblAString(TablaReservacion, 6).split(" ")[3]);
            reservacion r = controlReserv.buscarReservacionesPorId(idRes, reservaciones);
            cliente cli = controlCliente.buscarCliente(r.getId_cliente(), clientes);
            String[] opciones = {"Modificar", "Eliminar", "Cancelar"};
            int op = metodo.mensajeOpcionMultiple(metodo.personalizarMsj("¿Qué desea realizar con la reservación seleccionada?", metodo.azul, "times new roman", metodo.titulo1, true, false, false), "Reservación", opciones);
            //System.out.println(" - "+op);
            op=op+1;
            switch (op){
                case 1:{
                    limpiarTxtClienteRes(true, idHab);
                    verCampRes(true);
                    txtCedulaRes.setText(r.getId_cliente());
                    txtNombresRes.setText(r.getNombres());
                    txtDireccionRes.setText(r.getDireccion());
                    txtTelefonoRes.setText(cli.getTelefono());
                    txtCorreoRes.setText(cli.getCorreo());
                    txtObservRes.setText(cli.getObservaciones());
                    cmbHabRes.setSelectedItem("Hab. "+r.getId_hab());
                    spinCantAdultRes.setValue(cantAd);
                    spinCantNiñoRes.setValue(cantNi);
                    spinCantDiasRes.setValue(Integer.parseInt(r.getCant_dias()));
                    choosFechSalRes.setDate(metodo.StringADate(r.getFecha_sal(),"/"));
                    choosFechIngRes.setDate(metodo.StringADate(r.getFecha_ing(),"/"));
                    txtAbonoRes.setText(r.getEstado());
                    txtHoraRes.setText(metodo.de24A12Horas(r.getHora_ingreso()).toUpperCase());
                    lblTotalRes.setText("$ "+r.getTotal_pagar());
                    btnGuardarRes.setEnabled(true);
                    upRes=true;
                    break;
                }
                case 2:{
                    if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de eliminar esta reservación?", metodo.rojo, "times new roman", metodo.titulo1, true, false, false), "Aviso", metodo.pregunta)) {
                        if (controlReserv.eliminarReservaciones(idRes)) {
                            metodo.mensajeDialogo(metodo.personalizarMsj("Reservación eliminada con éxito", metodo.verde, "times new roman", metodo.titulo1, true, false, false), "Información", metodo.informacion);
                            contHabRes=0;
                            reservaciones = controlReserv.listarReservaciones("");
                            tiempoHabRes.start();
                            metodo.fondoTablaInvisible(TablaReservacion, jScrollPane3, controlReserv.MostrarTablaReservaciones(reservaciones), anchosTblReservacion, tamañoLetra);
                            actBtnHab();
                        }
                    }
                    break;
                }
            }
        }
    }//GEN-LAST:event_TablaReservacionMouseClicked

    private void tablaClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseReleased
        if (tablaCliente.getSelectedRow()>=0) {
            btnEliminarCliente.setVisible(true);
            btnModificarCliente.setVisible(true);
        }else{
            btnEliminarCliente.setVisible(false);
            btnModificarCliente.setVisible(false);
        }
    }//GEN-LAST:event_tablaClienteMouseReleased

    private void spnTablaClientePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spnTablaClientePropertyChange
        if (bandCliente) {
            metodo.tamañoFuenteTablaSpn(spnTablaCliente, tablaCliente);
        }
    }//GEN-LAST:event_spnTablaClientePropertyChange

    private void btnGuardarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteMouseEntered
        metodo.cambiarImgBoton(btnGuardarCliente, "aggCliente2.png");
    }//GEN-LAST:event_btnGuardarClienteMouseEntered

    private void btnGuardarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteMouseExited
        metodo.cambiarImgBoton(btnGuardarCliente, "aggCliente1.png");
    }//GEN-LAST:event_btnGuardarClienteMouseExited

    private void btnGuardarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteMousePressed
        metodo.cambiarImgBoton(btnGuardarCliente, "aggCliente3.png");
    }//GEN-LAST:event_btnGuardarClienteMousePressed

    private void btnGuardarClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarClienteMouseReleased
        metodo.cambiarImgBoton(btnGuardarCliente, "aggCliente2.png");
    }//GEN-LAST:event_btnGuardarClienteMouseReleased

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de ingresar este cliente a la base de datos?", metodo.azul, "times new roman", metodo.titulo2, false, false, false), "Aviso", metodo.pregunta)) {
            cliente cli = new cliente(txtCedulaCliente.getText(), txtNombreCliente.getText(), txtDireccionCliente.getText(), txtTelefonoCliente.getText(), "Off", txtObsCliente.getText(), txtCorreoCliente.getText());
            if (controlCliente.ingresarCliente(cli)) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Cliente ingresado con éxito!", metodo.verde, "times new roman", metodo.titulo2, false, false, false), "Información", metodo.informacion);
                clientes = controlCliente.listarClientes("");
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                limpiarTxtCliente(true);
                metodo.enfocarTxt(txtCedulaCliente);
            }
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnLimpiarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteMouseEntered
        metodo.cambiarImgBoton(btnLimpiarCliente, "limpiar2.png");
    }//GEN-LAST:event_btnLimpiarClienteMouseEntered

    private void btnLimpiarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteMouseExited
        metodo.cambiarImgBoton(btnLimpiarCliente, "limpiar1.png");
    }//GEN-LAST:event_btnLimpiarClienteMouseExited

    private void btnLimpiarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteMousePressed
        metodo.cambiarImgBoton(btnLimpiarCliente, "limpiar3.png");
    }//GEN-LAST:event_btnLimpiarClienteMousePressed

    private void btnLimpiarClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarClienteMouseReleased
        metodo.cambiarImgBoton(btnLimpiarCliente, "limpiar2.png");
    }//GEN-LAST:event_btnLimpiarClienteMouseReleased

    private void btnLimpiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarClienteActionPerformed
        limpiarTxtCliente(true);
        metodo.enfocarTxt(txtCedulaCliente);
    }//GEN-LAST:event_btnLimpiarClienteActionPerformed

    private void btnModificarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarClienteMouseEntered
        metodo.cambiarImgBoton(btnModificarCliente, "upCliente2.png");
    }//GEN-LAST:event_btnModificarClienteMouseEntered

    private void btnModificarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarClienteMouseExited
        metodo.cambiarImgBoton(btnModificarCliente, "upCliente1.png");
    }//GEN-LAST:event_btnModificarClienteMouseExited

    private void btnModificarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarClienteMousePressed
        metodo.cambiarImgBoton(btnModificarCliente, "upCliente3.png");
    }//GEN-LAST:event_btnModificarClienteMousePressed

    private void btnModificarClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarClienteMouseReleased
        metodo.cambiarImgBoton(btnModificarCliente, "upCliente1.png");
    }//GEN-LAST:event_btnModificarClienteMouseReleased

    private void btnModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarClienteActionPerformed
        String [] opciones ={"Cédula","Nombres", "Dirección", "Teléfono", "Correo", "Observaciones"};
        String respuesta = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Elija el campo que desea modificar", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, opciones, opciones[0]);
        String cedula = tablaCliente.getValueAt(tablaCliente.getSelectedRow(), 0).toString();
        cliente newCli = controlCliente.buscarCliente(cedula, clientes);
        switch(respuesta){
            case "Cédula":{
                String newCedula = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese la nueva cédula", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, null, cedula);
                if(controlCliente.cliExistente(newCedula, clientes)){
                    metodo.mensajeDialogo(metodo.personalizarMsj("El número de cédula pertenece a otro cliente", metodo.rojo, "times new roman", metodo.titulo2, true, false, false), "Error", metodo.error);
                }else{
                    if (newCedula!=null&&metodo.mensajeConfirmacion(metodo.personalizarMsj("Cédula anterior: "+cedula, metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Nueva cédula: "+newCedula, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de actualizar esta información?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta)) {
                        newCli.setCedula(newCedula);
                        controlCliente.actualizarCliente(newCli, cedula);
                        clientes = controlCliente.listarClientes("");
                        metodo.mensajeDialogo(metodo.personalizarMsj("Información actualizada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                    }
                }
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                btnEliminarCliente.setVisible(false);
                btnModificarCliente.setVisible(false);
                break;
            }
            case "Nombres":{
                String newNombres = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese los nombres del cliente", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, null, newCli.getNombres());
                if (newNombres!=null&&metodo.mensajeConfirmacion(metodo.personalizarMsj("Nombres anteriores: "+newCli.getNombres(), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Nuevos nombres: "+newNombres, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de actualizar esta información?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta)) {
                    newCli.setNombres(newNombres);
                    controlCliente.actualizarCliente(newCli, cedula);
                    clientes = controlCliente.listarClientes("");
                    metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                    metodo.mensajeDialogo(metodo.personalizarMsj("Información actualizada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                }
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                btnEliminarCliente.setVisible(false);
                btnModificarCliente.setVisible(false);
                break;
            }
            case "Dirección":{
                String newDireccion = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese la nueva dirección del cliente", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, null, newCli.getDireccion());
                if (newDireccion!=null&&metodo.mensajeConfirmacion(metodo.personalizarMsj("Dirección anterior: "+newCli.getDireccion(), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Nueva dirección: "+newDireccion, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de actualizar esta información?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta)) {
                    newCli.setDireccion(newDireccion);
                    controlCliente.actualizarCliente(newCli, cedula);
                    clientes = controlCliente.listarClientes("");
                    metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                    metodo.mensajeDialogo(metodo.personalizarMsj("Información actualizada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                }
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                btnEliminarCliente.setVisible(false);
                btnModificarCliente.setVisible(false);
                break;
            }
            case "Teléfono":{
                String newTelefono = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese el teléfono del cliente", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, null, newCli.getTelefono());
                if (newTelefono!=null&&metodo.mensajeConfirmacion(metodo.personalizarMsj("Teléfono anterior: "+newCli.getTelefono(), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Nuevo teléfono: "+newTelefono, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de actualizar esta información?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta)) {
                    newCli.setTelefono(newTelefono);
                    controlCliente.actualizarCliente(newCli, cedula);
                    clientes = controlCliente.listarClientes("");
                    metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                    metodo.mensajeDialogo(metodo.personalizarMsj("Información actualizada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                }
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                btnEliminarCliente.setVisible(false);
                btnModificarCliente.setVisible(false);
                break;
            }
            case "Correo":{
                String newCorreo = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese la nueva dirección de correo del cliente", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, null, newCli.getCorreo());
                if (newCorreo!=null&&metodo.mensajeConfirmacion(metodo.personalizarMsj("Dirección de correo anterior: "+newCli.getCorreo(), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Nueva dirección de correo: "+newCorreo, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de actualizar esta información?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta)) {
                    newCli.setCorreo(newCorreo);
                    controlCliente.actualizarCliente(newCli, cedula);
                    clientes = controlCliente.listarClientes("");
                    metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                    metodo.mensajeDialogo(metodo.personalizarMsj("Información actualizada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                }
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                btnEliminarCliente.setVisible(false);
                btnModificarCliente.setVisible(false);
                break;
            }
            case "Observaciones":{
                String newObservaciones = metodo.mensajeDialogoEntradaCombo(metodo.personalizarMsj("Ingrese la observación del cliente", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta, null, newCli.getObservaciones());
                if (newObservaciones!=null&&metodo.mensajeConfirmacion(metodo.personalizarMsj("Observación anterior: "+newCli.getObservaciones(), metodo.azul, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("Nueva observación: "+newObservaciones, metodo.verde, "times new roman", metodo.titulo2, true, false, false)+"\n\n"+metodo.personalizarMsj("¿Está seguro de actualizar esta información?", metodo.negro, "times new roman", metodo.titulo2, true, false, false), "Actualización de datos", metodo.pregunta)) {
                    newCli.setObservaciones(newObservaciones);
                    controlCliente.actualizarCliente(newCli, cedula);
                    clientes = controlCliente.listarClientes("");
                    metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                    metodo.mensajeDialogo(metodo.personalizarMsj("Información actualizada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                }
                metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
                btnEliminarCliente.setVisible(false);
                btnModificarCliente.setVisible(false);
                break;
            }
        }

    }//GEN-LAST:event_btnModificarClienteActionPerformed

    private void btnEliminarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMouseEntered
        metodo.cambiarImgBoton(btnEliminarCliente, "delCliente2.png");
    }//GEN-LAST:event_btnEliminarClienteMouseEntered

    private void btnEliminarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMouseExited
        metodo.cambiarImgBoton(btnEliminarCliente, "delCliente1.png");
    }//GEN-LAST:event_btnEliminarClienteMouseExited

    private void btnEliminarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMousePressed
        metodo.cambiarImgBoton(btnEliminarCliente, "delCliente3.png");
    }//GEN-LAST:event_btnEliminarClienteMousePressed

    private void btnEliminarClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarClienteMouseReleased
        metodo.cambiarImgBoton(btnEliminarCliente, "delCliente2.png");
    }//GEN-LAST:event_btnEliminarClienteMouseReleased

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        String cedula = metodo.deTblAString(tablaCliente, 0);
        if (metodo.mensajeConfirmacion(metodo.personalizarMsj("¿Está seguro de eliminar este cliente?", metodo.azul, "times new roman", metodo.titulo2, true, false, false), "Aviso", metodo.pregunta)) {
            if (controlCliente.eliminarCliente(cedula)) {
                metodo.mensajeDialogo(metodo.personalizarMsj("Información eliminada con éxito", metodo.verde, "times new roman", metodo.titulo2, true, false, false), "Información", metodo.informacion);
                clientes = controlCliente.listarClientes("");
            }
        }
        metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(clientes), anchosTblCliente, tamañoLetra);
        btnEliminarCliente.setVisible(false);
        btnModificarCliente.setVisible(false);
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void txtCedulaClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClienteKeyPressed
        metodo.SNumeros(txtCedulaCliente, "numero");
    }//GEN-LAST:event_txtCedulaClienteKeyPressed

    private void txtCedulaClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaClienteKeyReleased
        /*
        if (metodo.validadorDeCedula(txtCedulaCliente.getText())) {
            lblVistoCedulaCliente.setText("√");
            lblVistoCedulaCliente.setForeground(Color.green);
            lblVistoCedulaCliente.setToolTipText("Número de cédula válido");
        }else{
            if (txtCedulaCliente.getText().equals("")) {
                lblVistoCedulaCliente.setText("");
                lblVistoCedulaCliente.setForeground(Color.red);
            }else{
                lblVistoCedulaCliente.setText("х");
                lblVistoCedulaCliente.setForeground(Color.red);
                lblVistoCedulaCliente.setToolTipText("Número de cédula no válido");
            }
        }
        */
        try{
            metodo.vistoEquis(metodo.validadorDeCedula(txtCedulaCliente.getText()), lblVistoCedulaCliente);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
            
        if (metodo.validadorDeCedula(txtCedulaCliente.getText())) {
            //lblVistoCedulaRes.setText("√");
            //lblVistoCedulaRes.setForeground(Color.green);
            lblVistoCedulaCliente.setToolTipText("Número de cédula válido");
        }else{
            if (txtCedulaCliente.getText().equals("")) {
                metodo.cambiarImgLbl(lblVistoCedulaCliente, "");
                //lblVistoCedulaRes.setText("");
                //lblVistoCedulaRes.setForeground(Color.red);
            }else{
                //lblVistoCedulaRes.setText("х");
                //lblVistoCedulaRes.setForeground(Color.red);
                lblVistoCedulaCliente.setToolTipText("Número de cédula no válido");
            }
        }

        if (controlCliente.cliExistente(txtCedulaCliente.getText(), clientes)) {
            metodo.mensajeDialogo(metodo.personalizarMsj("Cliente ya registrado en la base de datos", metodo.rojo, "times new roman", metodo.titulo2, false, false, false), "Aviso", metodo.informacion);
            limpiarTxtCliente(true);
            lblVistoCedulaCliente.setText("");
        }
        if (!txtCedulaCliente.getText().equals("")&&!txtNombreCliente.getText().equals("")) {
            btnGuardarCliente.setEnabled(true);
        }else{
            btnGuardarCliente.setEnabled(false);
        }
    }//GEN-LAST:event_txtCedulaClienteKeyReleased

    private void txtNombreClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyReleased
        if (!txtCedulaCliente.getText().equals("")&&!txtNombreCliente.getText().equals("")) {
            btnGuardarCliente.setEnabled(true);
        }else{
            btnGuardarCliente.setEnabled(false);
        }
    }//GEN-LAST:event_txtNombreClienteKeyReleased

    private void txtDireccionClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionClienteKeyReleased

    private void txtObsClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObsClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObsClienteKeyReleased

    private void txtCorreoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoClienteKeyReleased

    private void txtTelefonoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoClienteKeyReleased

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        List<cliente> busClientes = controlCliente.listarClientes(txtBuscarCliente.getText().toUpperCase());
        metodo.fondoTablaInvisible(tablaCliente, jScrollPane1, controlCliente.MostrarTablaCliente(busClientes), anchosTblCliente, tamañoLetra);
        metodo.tamañoFuenteTablaSpn(spnTablaCliente, tablaCliente);
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void lblFondoPrincipalMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFondoPrincipalMouseDragged
        final Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - X, point.y - (Y + 30));
    }//GEN-LAST:event_lblFondoPrincipalMouseDragged

    private void lblFondoPrincipalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFondoPrincipalMousePressed
        X = evt.getX();
        Y=evt.getY();
        metodo.cambiarMouseLbl(this.metodo.movimiento, this.lblFondoPrincipal);
    }//GEN-LAST:event_lblFondoPrincipalMousePressed

    private void lblFondoPrincipalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFondoPrincipalMouseReleased
        metodo.cambiarMouseLbl(this.metodo.defecto, this.lblFondoPrincipal);
    }//GEN-LAST:event_lblFondoPrincipalMouseReleased

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CuadriculaRes;
    private javax.swing.JRadioButton TablaRes;
    private javax.swing.JTable TablaReservacion;
    private javax.swing.JButton btnAgregarReservacion;
    private javax.swing.JButton btnAvanzarDia;
    private javax.swing.JButton btnAvanzarSemana;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarClienteHab;
    private javax.swing.JButton btnGuardarPrecioHab;
    private javax.swing.JButton btnGuardarRes;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnHab;
    private javax.swing.JButton btnHab1;
    private javax.swing.JButton btnHab10;
    private javax.swing.JButton btnHab2;
    private javax.swing.JButton btnHab3;
    private javax.swing.JButton btnHab4;
    private javax.swing.JButton btnHab5;
    private javax.swing.JButton btnHab6;
    private javax.swing.JButton btnHab7;
    private javax.swing.JButton btnHab8;
    private javax.swing.JButton btnHab9;
    private javax.swing.JButton btnLimpiarCliente;
    private javax.swing.JButton btnLimpiarClienteHab;
    private javax.swing.JButton btnLimpiarUsuario;
    private javax.swing.JButton btnModificarCliente;
    private javax.swing.JButton btnPrecioHab;
    private javax.swing.JButton btnRegresarDia;
    private javax.swing.JButton btnRegresarSemana;
    private javax.swing.JButton btnReserv;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JButton btnVisitas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chBoxCliente;
    private javax.swing.JCheckBox chBoxHab;
    private javax.swing.JCheckBox chBoxReservacion;
    private javax.swing.JCheckBox chBoxUsuario;
    private javax.swing.JCheckBox chBoxValorHosp;
    private javax.swing.JCheckBox chBoxVisitas;
    private com.toedter.calendar.JDateChooser choosFechIngRes;
    private com.toedter.calendar.JDateChooser choosFechSalRes;
    private javax.swing.JComboBox<String> cmbHabRes;
    private javax.swing.JComboBox<String> cmbTipoCliHab;
    private com.toedter.calendar.JDateChooser datChosFechaIngHab;
    private com.toedter.calendar.JDateChooser datChosFechaSalHab;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAbonoClienteHab;
    private javax.swing.JLabel lblAbonoRes;
    private javax.swing.JLabel lblAgregarReservacion;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblBuscarCliente;
    private javax.swing.JLabel lblBuscarUsuario;
    private javax.swing.JLabel lblBuscarVisita;
    private javax.swing.JLabel lblCantAdulRes;
    private javax.swing.JLabel lblCantHuesp;
    private javax.swing.JLabel lblCantHuesp1;
    private javax.swing.JLabel lblCantNiñosRes;
    private javax.swing.JLabel lblCargando;
    private javax.swing.JLabel lblCargoUsuario;
    private javax.swing.JLabel lblCatDiasRes;
    private javax.swing.JLabel lblCedulaCliente;
    private javax.swing.JLabel lblCedulaClienteHab;
    private javax.swing.JLabel lblCedulaRes;
    private javax.swing.JLabel lblCheckInRes;
    private javax.swing.JLabel lblCheckOutRes;
    private javax.swing.JLabel lblClaveUsuario;
    private javax.swing.JLabel lblCorreoCliente;
    private javax.swing.JLabel lblCorreoClienteHab;
    private javax.swing.JLabel lblCorreoRes;
    private javax.swing.JLabel lblCostoHab;
    private javax.swing.JLabel lblCostoIndCli;
    private javax.swing.JLabel lblCostoIndHabCli;
    private javax.swing.JLabel lblCostoTotalHab;
    private javax.swing.JLabel lblDetalleHabSelecPrecioHosp;
    private javax.swing.JLabel lblDireccionCliente;
    private javax.swing.JLabel lblDireccionClienteHab;
    private javax.swing.JLabel lblDireccionRes;
    private javax.swing.JLabel lblEncabezadoUsuarios;
    private javax.swing.JLabel lblFechaIngClienteHab;
    private javax.swing.JLabel lblFechaSalidaClienteHab;
    private javax.swing.JLabel lblFondo2;
    private javax.swing.JLabel lblFondoAbajoUsuario;
    private javax.swing.JLabel lblFondoArribaUsuario;
    private javax.swing.JLabel lblFondoMenuCuadricula;
    private javax.swing.JLabel lblFondoPanelHab;
    private javax.swing.JLabel lblFondoPanelPrincipal;
    private javax.swing.JLabel lblFondoPanelRes;
    private javax.swing.JLabel lblFondoPanelRes1;
    private javax.swing.JLabel lblFondoPrincipal;
    private javax.swing.JLabel lblHab1;
    private javax.swing.JLabel lblHab10;
    private javax.swing.JLabel lblHab2;
    private javax.swing.JLabel lblHab3;
    private javax.swing.JLabel lblHab4;
    private javax.swing.JLabel lblHab5;
    private javax.swing.JLabel lblHab6;
    private javax.swing.JLabel lblHab7;
    private javax.swing.JLabel lblHab8;
    private javax.swing.JLabel lblHab9;
    private javax.swing.JLabel lblHabRes;
    private javax.swing.JLabel lblHabSeleccionadaPrecioHosp;
    private javax.swing.JLabel lblHoraRes;
    private javax.swing.JLabel lblNameUser;
    private javax.swing.JLabel lblNickNameUsuario;
    private javax.swing.JLabel lblNombresCliente;
    private javax.swing.JLabel lblNombresClienteHab;
    private javax.swing.JLabel lblNombresRes;
    private javax.swing.JLabel lblNombresUsuario;
    private javax.swing.JLabel lblObservClienteHab;
    private javax.swing.JLabel lblObservRes;
    private javax.swing.JLabel lblObservaciones;
    private javax.swing.JLabel lblPermisosUsuario;
    private javax.swing.JLabel lblPrecioHospHabPrecioHosp;
    private javax.swing.JLabel lblRes00;
    private javax.swing.JLabel lblRes01;
    private javax.swing.JLabel lblRes02;
    private javax.swing.JLabel lblRes03;
    private javax.swing.JLabel lblRes04;
    private javax.swing.JLabel lblRes05;
    private javax.swing.JLabel lblRes06;
    private javax.swing.JLabel lblRes07;
    private javax.swing.JLabel lblRes10;
    private javax.swing.JLabel lblRes100;
    private javax.swing.JLabel lblRes101;
    private javax.swing.JLabel lblRes102;
    private javax.swing.JLabel lblRes103;
    private javax.swing.JLabel lblRes104;
    private javax.swing.JLabel lblRes105;
    private javax.swing.JLabel lblRes106;
    private javax.swing.JLabel lblRes107;
    private javax.swing.JLabel lblRes11;
    private javax.swing.JLabel lblRes12;
    private javax.swing.JLabel lblRes13;
    private javax.swing.JLabel lblRes14;
    private javax.swing.JLabel lblRes15;
    private javax.swing.JLabel lblRes16;
    private javax.swing.JLabel lblRes17;
    private javax.swing.JLabel lblRes20;
    private javax.swing.JLabel lblRes21;
    private javax.swing.JLabel lblRes22;
    private javax.swing.JLabel lblRes23;
    private javax.swing.JLabel lblRes24;
    private javax.swing.JLabel lblRes25;
    private javax.swing.JLabel lblRes26;
    private javax.swing.JLabel lblRes27;
    private javax.swing.JLabel lblRes30;
    private javax.swing.JLabel lblRes31;
    private javax.swing.JLabel lblRes32;
    private javax.swing.JLabel lblRes33;
    private javax.swing.JLabel lblRes34;
    private javax.swing.JLabel lblRes35;
    private javax.swing.JLabel lblRes36;
    private javax.swing.JLabel lblRes37;
    private javax.swing.JLabel lblRes40;
    private javax.swing.JLabel lblRes41;
    private javax.swing.JLabel lblRes42;
    private javax.swing.JLabel lblRes43;
    private javax.swing.JLabel lblRes44;
    private javax.swing.JLabel lblRes45;
    private javax.swing.JLabel lblRes46;
    private javax.swing.JLabel lblRes47;
    private javax.swing.JLabel lblRes50;
    private javax.swing.JLabel lblRes51;
    private javax.swing.JLabel lblRes52;
    private javax.swing.JLabel lblRes53;
    private javax.swing.JLabel lblRes54;
    private javax.swing.JLabel lblRes55;
    private javax.swing.JLabel lblRes56;
    private javax.swing.JLabel lblRes57;
    private javax.swing.JLabel lblRes60;
    private javax.swing.JLabel lblRes61;
    private javax.swing.JLabel lblRes62;
    private javax.swing.JLabel lblRes63;
    private javax.swing.JLabel lblRes64;
    private javax.swing.JLabel lblRes65;
    private javax.swing.JLabel lblRes66;
    private javax.swing.JLabel lblRes67;
    private javax.swing.JLabel lblRes70;
    private javax.swing.JLabel lblRes71;
    private javax.swing.JLabel lblRes72;
    private javax.swing.JLabel lblRes73;
    private javax.swing.JLabel lblRes74;
    private javax.swing.JLabel lblRes75;
    private javax.swing.JLabel lblRes76;
    private javax.swing.JLabel lblRes77;
    private javax.swing.JLabel lblRes80;
    private javax.swing.JLabel lblRes81;
    private javax.swing.JLabel lblRes82;
    private javax.swing.JLabel lblRes83;
    private javax.swing.JLabel lblRes84;
    private javax.swing.JLabel lblRes85;
    private javax.swing.JLabel lblRes86;
    private javax.swing.JLabel lblRes87;
    private javax.swing.JLabel lblRes90;
    private javax.swing.JLabel lblRes91;
    private javax.swing.JLabel lblRes92;
    private javax.swing.JLabel lblRes93;
    private javax.swing.JLabel lblRes94;
    private javax.swing.JLabel lblRes95;
    private javax.swing.JLabel lblRes96;
    private javax.swing.JLabel lblRes97;
    private javax.swing.JLabel lblResF00;
    private javax.swing.JLabel lblResF01;
    private javax.swing.JLabel lblResF02;
    private javax.swing.JLabel lblResF03;
    private javax.swing.JLabel lblResF04;
    private javax.swing.JLabel lblResF05;
    private javax.swing.JLabel lblResF06;
    private javax.swing.JLabel lblResF07;
    private javax.swing.JLabel lblResF10;
    private javax.swing.JLabel lblResF100;
    private javax.swing.JLabel lblResF101;
    private javax.swing.JLabel lblResF102;
    private javax.swing.JLabel lblResF103;
    private javax.swing.JLabel lblResF104;
    private javax.swing.JLabel lblResF105;
    private javax.swing.JLabel lblResF106;
    private javax.swing.JLabel lblResF107;
    private javax.swing.JLabel lblResF11;
    private javax.swing.JLabel lblResF12;
    private javax.swing.JLabel lblResF13;
    private javax.swing.JLabel lblResF14;
    private javax.swing.JLabel lblResF15;
    private javax.swing.JLabel lblResF16;
    private javax.swing.JLabel lblResF17;
    private javax.swing.JLabel lblResF20;
    private javax.swing.JLabel lblResF21;
    private javax.swing.JLabel lblResF22;
    private javax.swing.JLabel lblResF23;
    private javax.swing.JLabel lblResF24;
    private javax.swing.JLabel lblResF25;
    private javax.swing.JLabel lblResF26;
    private javax.swing.JLabel lblResF27;
    private javax.swing.JLabel lblResF30;
    private javax.swing.JLabel lblResF31;
    private javax.swing.JLabel lblResF32;
    private javax.swing.JLabel lblResF33;
    private javax.swing.JLabel lblResF34;
    private javax.swing.JLabel lblResF35;
    private javax.swing.JLabel lblResF36;
    private javax.swing.JLabel lblResF37;
    private javax.swing.JLabel lblResF40;
    private javax.swing.JLabel lblResF41;
    private javax.swing.JLabel lblResF42;
    private javax.swing.JLabel lblResF43;
    private javax.swing.JLabel lblResF44;
    private javax.swing.JLabel lblResF45;
    private javax.swing.JLabel lblResF46;
    private javax.swing.JLabel lblResF47;
    private javax.swing.JLabel lblResF50;
    private javax.swing.JLabel lblResF51;
    private javax.swing.JLabel lblResF52;
    private javax.swing.JLabel lblResF53;
    private javax.swing.JLabel lblResF54;
    private javax.swing.JLabel lblResF55;
    private javax.swing.JLabel lblResF56;
    private javax.swing.JLabel lblResF57;
    private javax.swing.JLabel lblResF60;
    private javax.swing.JLabel lblResF61;
    private javax.swing.JLabel lblResF62;
    private javax.swing.JLabel lblResF63;
    private javax.swing.JLabel lblResF64;
    private javax.swing.JLabel lblResF65;
    private javax.swing.JLabel lblResF66;
    private javax.swing.JLabel lblResF67;
    private javax.swing.JLabel lblResF70;
    private javax.swing.JLabel lblResF71;
    private javax.swing.JLabel lblResF72;
    private javax.swing.JLabel lblResF73;
    private javax.swing.JLabel lblResF74;
    private javax.swing.JLabel lblResF75;
    private javax.swing.JLabel lblResF76;
    private javax.swing.JLabel lblResF77;
    private javax.swing.JLabel lblResF80;
    private javax.swing.JLabel lblResF81;
    private javax.swing.JLabel lblResF82;
    private javax.swing.JLabel lblResF83;
    private javax.swing.JLabel lblResF84;
    private javax.swing.JLabel lblResF85;
    private javax.swing.JLabel lblResF86;
    private javax.swing.JLabel lblResF87;
    private javax.swing.JLabel lblResF90;
    private javax.swing.JLabel lblResF91;
    private javax.swing.JLabel lblResF92;
    private javax.swing.JLabel lblResF93;
    private javax.swing.JLabel lblResF94;
    private javax.swing.JLabel lblResF95;
    private javax.swing.JLabel lblResF96;
    private javax.swing.JLabel lblResF97;
    private javax.swing.JLabel lblSubFondoPanelCliente;
    private javax.swing.JLabel lblSubFondoPanelPrecioHosp;
    private javax.swing.JLabel lblSubFondoPanelVisita;
    private javax.swing.JLabel lblSubFondoTablaPanelCliente;
    private javax.swing.JLabel lblTamanoLetra;
    private javax.swing.JLabel lblTelefonoCliente;
    private javax.swing.JLabel lblTelefonoClienteHab;
    private javax.swing.JLabel lblTelefonoRes;
    private javax.swing.JLabel lblTituloPanelCliente;
    private javax.swing.JLabel lblTituloPanelHab;
    private javax.swing.JLabel lblTituloPrecioHosp;
    private javax.swing.JLabel lblTotalRes;
    private javax.swing.JLabel lblVistoCedulaCliente;
    private javax.swing.JLabel lblVistoCedulaRes;
    private javax.swing.JLabel lblVistoClienteHab;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelHab;
    private javax.swing.JPanel panelPrecioHosp;
    private javax.swing.JPanel panelRes;
    private javax.swing.JPanel panelUsuario;
    private javax.swing.JPanel panelVisita;
    private javax.swing.JSpinner spinCantAdultRes;
    private javax.swing.JSpinner spinCantDiasRes;
    private javax.swing.JSpinner spinCantNiñoRes;
    private javax.swing.JSpinner spinTablaVisita;
    private javax.swing.JSpinner spinTamTblRes;
    private javax.swing.JSpinner spinTblUsuario;
    private com.toedter.components.JSpinField spnTablaCliente;
    private com.toedter.components.JSpinField spnTablaClienteHab;
    private com.toedter.components.JSpinField spnTablaPrecioHosp;
    private javax.swing.JPanel subPanelHab;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTable tablaHab;
    private javax.swing.JTable tablaPrecioHosp;
    private javax.swing.JTable tablaVisita;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtAbonoClientHab;
    private javax.swing.JTextField txtAbonoRes;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarUsuario;
    private javax.swing.JTextField txtBuscarVisita;
    private javax.swing.JTextField txtCargoUsuario;
    private javax.swing.JTextField txtCedulaClientHab;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCedulaRes;
    private javax.swing.JTextField txtClaveUsuario;
    private javax.swing.JTextField txtCorreoClientHab;
    private javax.swing.JTextField txtCorreoCliente;
    private javax.swing.JTextField txtCorreoRes;
    private javax.swing.JTextField txtDireccionClientHab;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtDireccionRes;
    private javax.swing.JTextField txtHoraRes;
    private javax.swing.JTextField txtNickNameUsuario;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JTextField txtNombresClientHab;
    private javax.swing.JTextField txtNombresRes;
    private javax.swing.JTextField txtObsCliente;
    private javax.swing.JTextField txtObservClientHab;
    private javax.swing.JTextField txtObservRes;
    private javax.swing.JTextField txtTelefonoClientHab;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoRes;
    // End of variables declaration//GEN-END:variables
}
