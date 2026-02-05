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

    // Constructor vacío
    public Mensaje() {
    }

    public Mensaje(String contenido, int idUsuario) {
        this.contenido = contenido;
        this.idUsuario = idUsuario;
    }

    public Mensaje(int id, String contenido, Timestamp fecha, int idUsuario) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
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
}

