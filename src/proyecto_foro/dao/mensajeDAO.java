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
        String sql = "INSERT INTO mensaje(contenido,id_usuario) VALUES (?,?)";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mensaje.getContenido());
            ps.setInt(2, mensaje.getIdUsuario());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public List<Mensaje> listar() {
        List<Mensaje> lista = new ArrayList<>();
        String sql = "SELECT * FROM mensaje ORDER BY fecha DESC";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mensaje m = new Mensaje();
                m.setId(rs.getInt("id"));
                m.setContenido(rs.getString("contenido"));
                m.setFecha(rs.getTimestamp("fecha"));
                m.setIdUsuario(rs.getInt("id_usuario"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Error listar mensajes");
        }
        return lista;
    }
}

