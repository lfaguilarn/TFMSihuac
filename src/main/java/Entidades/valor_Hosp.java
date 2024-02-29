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
public class valor_Hosp {
    String id;
    String descripcion;
    String costo;
    String habitacion;

    public valor_Hosp(String id, String descripcion, String costo, String habitacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.costo = costo;
        this.habitacion = habitacion;
    }

    public valor_Hosp(){
    }

    public String getId(){
            return id;
    }

    public void setId(String id){
            this.id = id;
    }
    public String getDescripcion(){
            return descripcion;
    }

    public void setDescripcion(String adulto){
            this.descripcion = adulto;
    }
    public String getCosto(){
            return costo;
    }

    public void setCosto(String costo){
            this.costo = costo;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

}
