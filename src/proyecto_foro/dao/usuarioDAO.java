/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto_foro.dao;

import proyecto_foro.conexion.conexionBD;
import proyecto_foro.modelo.Usuario;

import java.sql.*;

public class usuarioDAO {

    public Usuario login(String email, String password) {
        String sql = "SELECT * FROM usuario WHERE email=? AND password=?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                return u;
            }

        } catch (SQLException e) {
            System.err.println("Error login: " + e.getMessage());
        }
        return null;
    }

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre,email,password) VALUES(?,?,?)";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }
}
