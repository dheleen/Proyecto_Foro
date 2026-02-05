/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.modelo;

/**
 *
 * @author dhaca
 */
public class Mensaje {
    private int id;
    private String contenido;
    private String fecha;
    private int idUsuario;
    
    //constructor vacío
    public Mensaje() {}

    // Constructor con parámetros
    public Mensaje(int id, String contenido, String fecha, int idUsuario) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }
    //yaaaaaaaaaaaaaaa
    
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

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }


}
