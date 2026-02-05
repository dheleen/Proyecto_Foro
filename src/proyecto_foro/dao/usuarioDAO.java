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
        String sql = "SELECT * FROM usuario WHERE email = ? AND password = ?";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password); // Contraseña en texto plano

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                return u;
            }

        } catch (SQLException e) {
            System.err.println("Error en login: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, email, password) VALUES(?, ?, ?)";

        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword()); // Guardar en texto plano
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en registro: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para verificar si email ya existe
    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
        
        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error verificando email: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    
    // Método opcional: obtener usuario por ID
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                // No retornamos la contraseña por seguridad
                return u;
            }
            
        } catch (SQLException e) {
            System.err.println("Error obteniendo usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    // Método opcional: actualizar datos de usuario
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuario SET nombre = ?, email = ? WHERE id = ?";
        
        try (Connection conn = conexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setInt(3, usuario.getId());
            
            int filas = ps.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error actualizando usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}