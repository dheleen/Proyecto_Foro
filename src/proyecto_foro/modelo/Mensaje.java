/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.modelo;

import java.sql.Timestamp;

public class Mensaje {
    private int id;
    private String contenido;
    private Timestamp fecha;
    private int idUsuario;
    private Integer idMensajePadre; 
    private String nombreUsuario; 
    
    
    public Mensaje() {}
    
    public Mensaje(String contenido, int idUsuario) {
        this.contenido = contenido;
        this.idUsuario = idUsuario;
        this.idMensajePadre = null; 
    }
    
    public Mensaje(String contenido, int idUsuario, int idMensajePadre) {
        this.contenido = contenido;
        this.idUsuario = idUsuario;
        this.idMensajePadre = idMensajePadre; 
    }

    // Getters y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Timestamp getFecha() {
        return fecha;
    }
    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public Integer getIdMensajePadre() {
        return idMensajePadre;
    }
    
    public void setIdMensajePadre(Integer idMensajePadre) {
        this.idMensajePadre = idMensajePadre;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
    public boolean esRespuesta() {
        return idMensajePadre != null;
    }
}

