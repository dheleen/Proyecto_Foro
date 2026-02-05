/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.vista;

import proyecto_foro.dao.mensajeDAO;
import proyecto_foro.modelo.Mensaje;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ForoPanel extends JPanel {

    private JTextArea txtMensajes;
    private JTextField txtNuevoMensaje;
    private mensajeDAO mensajeDAO = new mensajeDAO();

    public ForoPanel(MainFrame mainFrame) {
        setLayout(new BorderLayout(10, 10));

        txtMensajes = new JTextArea();
        txtMensajes.setEditable(false);
        txtMensajes.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(txtMensajes);

        txtNuevoMensaje = new JTextField();
        JButton btnEnviar = new JButton("Enviar");

        JPanel abajo = new JPanel(new BorderLayout());
        abajo.add(txtNuevoMensaje, BorderLayout.CENTER);
        abajo.add(btnEnviar, BorderLayout.EAST);

        add(scroll, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);

        btnEnviar.addActionListener(e -> enviar());
        cargarMensajes();
    }

    private void cargarMensajes() {
        List<Mensaje> mensajes = mensajeDAO.listar();
        txtMensajes.setText("");

        for (Mensaje m : mensajes) {
            txtMensajes.append(m.getContenido() + "\n");
        }
    }

    private void enviar() {
        String texto = txtNuevoMensaje.getText();
        if (!texto.isEmpty()) {
            mensajeDAO.publicar(new Mensaje(texto, 1)); 
            txtNuevoMensaje.setText("");
            cargarMensajes();
        }
    }
}
