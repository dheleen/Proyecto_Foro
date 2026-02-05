/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// ForoPanel.java - MODIFICAR
package proyecto_foro.vista;

import proyecto_foro.dao.mensajeDAO;
import proyecto_foro.modelo.Mensaje;
import proyecto_foro.modelo.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class ForoPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextArea txtMensajes;
    private JTextField txtNuevoMensaje;
    private mensajeDAO mensajeDAO = new mensajeDAO();
    private JPanel mensajesPanel;
    private JButton btnLogout;

    public ForoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel superior con información de usuario y logout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 245, 245));
        topPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JLabel lblUsuario = new JLabel();
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.BLACK);
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.GRAY);
        btnLogout.setFocusPainted(false);
        
        topPanel.add(lblUsuario, BorderLayout.WEST);
        topPanel.add(btnLogout, BorderLayout.EAST);
        
        // Panel de mensajes con scroll
        mensajesPanel = new JPanel();
        mensajesPanel.setLayout(new BoxLayout(mensajesPanel, BoxLayout.Y_AXIS));
        mensajesPanel.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(mensajesPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            "Mensajes del Foro",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 14),
            new Color(52, 73, 94)
        ));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Panel inferior para enviar mensajes
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        txtNuevoMensaje = new JTextField();
        txtNuevoMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        txtNuevoMensaje.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(52, 152, 219)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
        JButton btnEnviar = new JButton("ENVIAR");
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnviar.setBackground(new Color(46, 204, 113));
        btnEnviar.setForeground(Color.GRAY);
        btnEnviar.setFocusPainted(false);
        btnEnviar.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        bottomPanel.add(txtNuevoMensaje, BorderLayout.CENTER);
        bottomPanel.add(btnEnviar, BorderLayout.EAST);

        // Agregar componentes al panel principal
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Listeners
        btnEnviar.addActionListener(this::enviarMensaje);
        btnLogout.addActionListener(e -> mainFrame.logout());
        
        // Actualizar información de usuario
        actualizarInfoUsuario();
    }
    
    private void actualizarInfoUsuario() {
        Usuario usuario = mainFrame.getUsuarioActual();
        if (usuario != null) {
            ((JLabel)((JPanel)getComponent(0)).getComponent(0)).setText(
                "Usuario: " + usuario.getNombre() + " (" + usuario.getEmail() + ")"
            );
        }
    }

    public void cargarMensajes() {
        mensajesPanel.removeAll();
        
        List<Mensaje> mensajes = mensajeDAO.listar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        for (Mensaje m : mensajes) {
            // Crear tarjeta para cada mensaje
            JPanel mensajeCard = new JPanel(new BorderLayout(10, 5));
            mensajeCard.setBackground(Color.WHITE);
            mensajeCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            mensajeCard.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
            
            // Contenido del mensaje
            JTextArea contenido = new JTextArea(m.getContenido());
            contenido.setWrapStyleWord(true);
            contenido.setLineWrap(true);
            contenido.setEditable(false);
            contenido.setBackground(Color.WHITE);
            contenido.setFont(new Font("Arial", Font.PLAIN, 13));
            
            // Información del mensaje
            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setBackground(Color.WHITE);
            
            JLabel lblInfo = new JLabel(String.format(
                "Usuario ID: %d • Publicado: %s",
                m.getIdUsuario(),
                sdf.format(m.getFecha())
            ));
            lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
            lblInfo.setForeground(new Color(127, 140, 141));
            
            infoPanel.add(lblInfo);
            
            mensajeCard.add(contenido, BorderLayout.CENTER);
            mensajeCard.add(infoPanel, BorderLayout.SOUTH);
            
            mensajesPanel.add(mensajeCard);
            mensajesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        mensajesPanel.revalidate();
        mensajesPanel.repaint();
    }

    private void enviarMensaje(ActionEvent e) {
        Usuario usuario = mainFrame.getUsuarioActual();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión para publicar");
            return;
        }
        
        String texto = txtNuevoMensaje.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El mensaje no puede estar vacío");
            return;
        }
        
        if (texto.length() > 500) {
            JOptionPane.showMessageDialog(this, "El mensaje no puede exceder 500 caracteres");
            return;
        }
        
        Mensaje nuevoMensaje = new Mensaje(texto, usuario.getId());
        if (mensajeDAO.publicar(nuevoMensaje)) {
            txtNuevoMensaje.setText("");
            cargarMensajes();
            JOptionPane.showMessageDialog(this, "Mensaje publicado exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al publicar el mensaje");
        }
    }
}