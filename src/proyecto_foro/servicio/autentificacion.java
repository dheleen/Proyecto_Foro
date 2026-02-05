/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.servicio;

import proyecto_foro.dao.usuarioDAO;
import proyecto_foro.modelo.usuario;

public class autentificacion {
    private usuarioDAO usuarioDao;

    public autentificacion() {
        this.usuarioDao = new usuarioDAO();
    }

    public usuario login(String email, String password) {
        usuario u = usuarioDao.buscarPorEmail(email);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}