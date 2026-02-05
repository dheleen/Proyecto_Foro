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

        if (email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {
            return null;
        }

        return usuarioDAO.login(email, password);
    }

    public boolean registrar(Usuario usuario) {
        return usuarioDAO.registrarUsuario(usuario);
    }
}
