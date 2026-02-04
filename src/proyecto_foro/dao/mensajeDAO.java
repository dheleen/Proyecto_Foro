/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.dao;

import proyecto_foro.conexion.conexionBD;
import proyecto_foro.modelo.mensaje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mensajeDAO {

    public boolean publicarMensaje(mensaje mensaje) {
        String sql = "INSERT INTO mensaje (contenido, id_usuario) VALUES (?, ?)";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mensaje.getContenido());
            ps.setInt(2, mensaje.getIdUsuario());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error al publicar mensaje: " + e.getMessage());
            return false;
        }
    }
}
