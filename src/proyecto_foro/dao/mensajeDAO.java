/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.dao;

import proyecto_foro.conexion.conexionBD;
import proyecto_foro.modelo.Mensaje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mensajeDAO {
    
  
    public boolean publicar(Mensaje mensaje) {
        String sql;
        if (mensaje.esRespuesta()) {
            sql = "INSERT INTO mensaje(contenido, id_usuario, id_mensaje_padre) VALUES (?, ?, ?)";
        } else {
            sql = "INSERT INTO mensaje(contenido, id_usuario) VALUES (?, ?)";
        }

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mensaje.getContenido());
            ps.setInt(2, mensaje.getIdUsuario());
            
            if (mensaje.esRespuesta()) {
                ps.setInt(3, mensaje.getIdMensajePadre());
            }
            
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error publicando mensaje: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
   
    public List<Mensaje> listarMensajesPrincipales() {
        List<Mensaje> lista = new ArrayList<>();
        String sql = """
            SELECT m.*, u.nombre as nombre_usuario 
            FROM mensaje m 
            JOIN usuario u ON m.id_usuario = u.id 
            WHERE m.id_mensaje_padre IS NULL 
            ORDER BY m.fecha DESC
            """;

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mensaje m = crearMensajeDesdeResultSet(rs);
                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error listando mensajes: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
  
    public List<Mensaje> listarRespuestas(int idMensajePadre) {
        List<Mensaje> lista = new ArrayList<>();
        String sql = """
            SELECT m.*, u.nombre as nombre_usuario 
            FROM mensaje m 
            JOIN usuario u ON m.id_usuario = u.id 
            WHERE m.id_mensaje_padre = ? 
            ORDER BY m.fecha ASC
            """;

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idMensajePadre);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje m = crearMensajeDesdeResultSet(rs);
                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error listando respuestas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
  
    public Mensaje obtenerPorId(int id) {
        String sql = """
            SELECT m.*, u.nombre as nombre_usuario 
            FROM mensaje m 
            JOIN usuario u ON m.id_usuario = u.id 
            WHERE m.id = ?
            """;

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return crearMensajeDesdeResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error obteniendo mensaje: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
  
    private Mensaje crearMensajeDesdeResultSet(ResultSet rs) throws SQLException {
        Mensaje m = new Mensaje();
        m.setId(rs.getInt("id"));
        m.setContenido(rs.getString("contenido"));
        m.setFecha(rs.getTimestamp("fecha"));
        m.setIdUsuario(rs.getInt("id_usuario"));
        m.setIdMensajePadre(rs.getObject("id_mensaje_padre", Integer.class));
        m.setNombreUsuario(rs.getString("nombre_usuario"));
        return m;
    }
}

