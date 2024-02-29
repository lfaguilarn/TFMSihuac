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
public class usuario {
    String nickname;
String nombres;
String cargo;
String clave;
String cliente;
String hab;
String valorHosp;
String reservacion;
String visita;
String estado;
String user;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

public usuario(String nickname, String nombres, String cargo, String clave, String cliente, String hab, String valorHosp, String reservacion, String visita, String estado, String usuario) {
this.nickname = nickname;
this.nombres = nombres;
this.cargo = cargo;
this.clave = clave;
this.cliente = cliente;
this.hab = hab;
this.reservacion = reservacion;
this.visita = visita;
this.valorHosp = valorHosp;
this.estado = estado;
this.user = usuario;
}

public usuario(){
}

public String getNickname(){
	return nickname;
}

public void setNickname(String nickname){
	this.nickname = nickname;
}
public String getNombres(){
	return nombres;
}

public void setNombres(String nombres){
	this.nombres = nombres;
}
public String getCargo(){
	return cargo;
}

public void setCargo(String cargo){
	this.cargo = cargo;
}
public String getClave(){
	return clave;
}

public void setClave(String clave){
	this.clave = clave;
}
public String getCliente(){
	return cliente;
}

public void setCliente(String cliente){
	this.cliente = cliente;
}
public String getHab(){
	return hab;
}

public void setHab(String hab){
	this.hab = hab;
}
public String getValorhosp(){
	return valorHosp;
}

public void setValorhosp(String valorHosp){
	this.valorHosp = valorHosp;
}
public String getReservacion(){
	return reservacion;
}

public void setReservacion(String reservacion){
	this.reservacion = reservacion;
}
public String getVisita(){
	return visita;
}

public void setVisita(String visita){
	this.visita = visita;
}

    public String getUsuario() {
        return user;
    }

    public void setUsuario(String usuario) {
        this.user = usuario;
    }

}
