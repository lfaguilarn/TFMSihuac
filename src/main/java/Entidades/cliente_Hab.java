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
public class cliente_Hab {
    String id;
    String id_cliente;
    String id_hab;
    String consumo;
    String fechaIng;
    String fechaSal;
    String id_visita;
    String estado;
    String abono;
    String hora_ing;

    public String getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(String fechaIng) {
        this.fechaIng = fechaIng;
    }

    public String getFechaSal() {
        return fechaSal;
    }

    public void setFechaSal(String fechaSal) {
        this.fechaSal = fechaSal;
    }

    public String getAbono() {
        return abono;
    }

    public void setAbono(String abono) {
        this.abono = abono;
    }

    public String getHora_ing() {
        return hora_ing;
    }

    public void setHora_ing(String hora_ing) {
        this.hora_ing = hora_ing;
    }

    public cliente_Hab(String id, String id_cliente, String id_hab, String consumo, String fechaIng, String fechaSal, String id_visita, String estado, String abono, String hora_ing) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_hab = id_hab;
        this.consumo = consumo;
        this.fechaIng = fechaIng;
        this.fechaSal = fechaSal;
        this.id_visita = id_visita;
        this.estado = estado;
        this.abono = abono;
        this.hora_ing = hora_ing;
    }
    
    public cliente_Hab(){
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
    public String getId_hab(){
            return id_hab;
    }

    public void setId_hab(String id_hab){
            this.id_hab = id_hab;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public String getId_visita() {
        return id_visita;
    }

    public void setId_visita(String id_visita) {
        this.id_visita = id_visita;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
