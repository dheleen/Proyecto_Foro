/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto_foro.servicio;

import proyecto_foro.dao.usuarioDAO;
import proyecto_foro.modelo.Usuario;

public class autentificacion {

    private usuarioDAO usuarioDAO = new usuarioDAO();

    public Usuario login(String email, String password) {
        if (!validarEmail(email)) {
            return null;
        }
        
        if (password == null || password.length() < 6) {
            return null;
        }

        return usuarioDAO.login(email, password);
    }

    public boolean registrar(Usuario usuario) {
   
        if (!validarEmail(usuario.getEmail())) {
            return false;
        }
        
        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            return false;
        }
        
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            return false;
        }
        

        if (usuarioDAO.emailExiste(usuario.getEmail())) {
            return false;
        }
        
        return usuarioDAO.registrarUsuario(usuario);
    }
    
    private boolean validarEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
