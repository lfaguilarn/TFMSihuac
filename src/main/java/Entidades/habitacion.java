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
public class habitacion {
    String id;
String capacidad;
String estado;
String observaciones;

public habitacion(String id, String capacidad, String estado, String observaciones) {
this.id = id;
this.capacidad = capacidad;
this.estado = estado;
this.observaciones = observaciones;
}

public habitacion(){
}

public String getId(){
	return id;
}

public void setId(String id){
	this.id = id;
}
public String getCapacidad(){
	return capacidad;
}

public void setCapacidad(String capacidad){
	this.capacidad = capacidad;
}
public String getEstado(){
	return estado;
}

public void setEstado(String estado){
	this.estado = estado;
}
public String getObservaciones(){
	return observaciones;
}

public void setObservaciones(String observaciones){
	this.observaciones = observaciones;
}

}
