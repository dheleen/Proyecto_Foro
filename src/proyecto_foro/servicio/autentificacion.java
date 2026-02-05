/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.servicio;

import proyecto_foro.dao.usuarioDAO;
import proyecto_foro.modelo.Usuario;

public class autentificacion {
    private usuarioDAO usuarioDao;

    public autentificacion() {
        this.usuarioDao = new usuarioDAO();
    }

    public Usuario login(String email, String password) {
        Usuario u = usuarioDao.buscarPorEmail(email);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}