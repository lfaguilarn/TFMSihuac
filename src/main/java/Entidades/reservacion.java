/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author Luis Fernando
 */
public class reservacion {
    String id;
    String id_hab;
    String id_cliente;
    String fecha_ing;
    String fecha_sal;
    String estado;
    String observaciones;
    String nombres;
    String direccion;
    String hora_ingreso;
    String hora_salida;
    String cant_us;
    String cant_dias;
    String total_pagar;

    public reservacion(String id, String id_hab, String id_cliente, String fecha_ing, String fecha_sal, String estado, String observaciones, String nombres, String direccion, String hora_ingreso, String hora_salida, String cant_us, String cant_dias, String total_pagar) {
        this.id = id;
        this.id_hab = id_hab;
        this.id_cliente = id_cliente;
        this.fecha_ing = fecha_ing;
        this.fecha_sal = fecha_sal;
        this.estado = estado;
        this.observaciones = observaciones;
        this.nombres = nombres;
        this.direccion = direccion;
        this.hora_ingreso = hora_ingreso;
        this.hora_salida = hora_salida;
        this.cant_us = cant_us;
        this.cant_dias = cant_dias;
        this.total_pagar = total_pagar;
    }

    public reservacion(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_hab() {
        return id_hab;
    }

    public void setId_hab(String id_hab) {
        this.id_hab = id_hab;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha_ing() {
        return fecha_ing;
    }

    public void setFecha_ing(String fecha_ing) {
        this.fecha_ing = fecha_ing;
    }

    public String getFecha_sal() {
        return fecha_sal;
    }

    public void setFecha_sal(String fecha_sal) {
        this.fecha_sal = fecha_sal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHora_ingreso() {
        return hora_ingreso;
    }

    public void setHora_ingreso(String hora_ingreso) {
        this.hora_ingreso = hora_ingreso;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getCant_us() {
        return cant_us;
    }

    public void setCant_us(String cant_us) {
        this.cant_us = cant_us;
    }

    public String getCant_dias() {
        return cant_dias;
    }

    public void setCant_dias(String cant_dias) {
        this.cant_dias = cant_dias;
    }

    public String getTotal_pagar() {
        return total_pagar;
    }

    public void setTotal_pagar(String total_pagar) {
        this.total_pagar = total_pagar;
    }
    
    
}
