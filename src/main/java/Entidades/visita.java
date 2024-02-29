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
public class visita {
    String id;
String id_cliente;
String nombre;
String direccion;
String id_hab;
String fecha_ing;
String fecha_sal;
String cant_dias;
String consumo;
String observaciones;
String tipo;

public visita(String id, String id_cliente, String nombre, String direccion, String id_hab, String fecha_ing, String fecha_sal, String cant_dias, String consumo, String observaciones, String tipo) {
this.id = id;
this.id_cliente = id_cliente;
this.nombre = nombre;
this.direccion = direccion;
this.id_hab = id_hab;
this.fecha_ing = fecha_ing;
this.fecha_sal = fecha_sal;
this.cant_dias = cant_dias;
this.consumo = consumo;
this.observaciones = observaciones;
this.tipo = tipo;
}

public visita(){
}

public String getId(){
	return id;
}

public void setId(String id){
	this.id = id;
}
public String getId_cliente(){
	return id_cliente;
}

public void setId_cliente(String id_cliente){
	this.id_cliente = id_cliente;
}
public String getNombre(){
	return nombre;
}

public void setNombre(String nombre){
	this.nombre = nombre;
}
public String getDireccion(){
	return direccion;
}

public void setDireccion(String direccion){
	this.direccion = direccion;
}
public String getId_hab(){
	return id_hab;
}

public void setId_hab(String id_hab){
	this.id_hab = id_hab;
}
public String getFecha_ing(){
	return fecha_ing;
}

public void setFecha_ing(String fecha_ing){
	this.fecha_ing = fecha_ing;
}
public String getFecha_sal(){
	return fecha_sal;
}

public void setFecha_sal(String fecha_sal){
	this.fecha_sal = fecha_sal;
}
public String getCant_dias(){
	return cant_dias;
}

public void setCant_dias(String cant_dias){
	this.cant_dias = cant_dias;
}
public String getConsumo(){
	return consumo;
}

public void setConsumo(String consumo){
	this.consumo = consumo;
}
public String getObservaciones(){
	return observaciones;
}

public void setObservaciones(String observaciones){
	this.observaciones = observaciones;
}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}
