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
public class consumo {
    String id;
    String idHab;
    String descripcion;
    String precio;
    String idVisita;

    public consumo(String id, String idHab, String descripcion, String precio, String idVisita) {
        this.id = id;
        this.idHab = idHab;
        this.descripcion = descripcion;
        this.precio = precio;
        this.idVisita = idVisita;
    }

    public consumo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHab() {
        return idHab;
    }

    public void setIdHab(String idHab) {
        this.idHab = idHab;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(String idVisita) {
        this.idVisita = idVisita;
    }
    
}
