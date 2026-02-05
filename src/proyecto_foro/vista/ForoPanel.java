/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto_foro.vista;

import proyecto_foro.dao.mensajeDAO;
import proyecto_foro.modelo.Mensaje;
import proyecto_foro.modelo.Usuario;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForoPanel extends JPanel {

    private MainFrame mainFrame;
    private mensajeDAO mensajeDAO = new mensajeDAO();
    private JPanel mensajesPanel;
    private JButton btnLogout;
    private JTextArea txtAreaNuevoMensaje;
    private Map<Integer, JPanel> panelesMensajes; 
    
    
    private final Color COLOR_PRINCIPAL = new Color(52, 152, 219);
    private final Color COLOR_RESPUESTA = new Color(236, 240, 241);
    private final Color COLOR_BORDE = new Color(189, 195, 199);
    
    public ForoPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.panelesMensajes = new HashMap<>();
        
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        crearPanelSuperior();
        crearPanelMensajes();
        crearPanelPublicacion();
        
        cargarMensajes();
    }
    
    private void crearPanelSuperior() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(COLOR_PRINCIPAL);
        topPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JLabel lblUsuario = new JLabel();
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setForeground(Color.BLACK);
        
        Usuario usuario = mainFrame.getUsuarioActual();
        if (usuario != null) {
            lblUsuario.setText("Bienvenido: " + usuario.getNombre());
        }
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.GRAY);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(new EmptyBorder(8, 15, 8, 15));
        
        topPanel.add(lblUsuario, BorderLayout.WEST);
        topPanel.add(btnLogout, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        
        btnLogout.addActionListener(e -> mainFrame.logout());
    }
    
    private void crearPanelMensajes() {
        mensajesPanel = new JPanel();
        mensajesPanel.setLayout(new BoxLayout(mensajesPanel, BoxLayout.Y_AXIS));
        mensajesPanel.setBackground(new Color(250, 250, 250));
        
        JScrollPane scrollPane = new JScrollPane(mensajesPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(COLOR_BORDE, 1),
            "FORO DE DISCUSIÓN",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 16),
            COLOR_PRINCIPAL
        ));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void crearPanelPublicacion() {
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 0));
        bottomPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        
        txtAreaNuevoMensaje = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                if (getText().isEmpty() && !hasFocus()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(new Color(150, 150, 150));
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    
                   
                    String placeholder = "Escribir tu mensaje";
                    
                   
                    FontMetrics fm = g2.getFontMetrics();
                    int textHeight = fm.getHeight();
                    int y = (getHeight() - textHeight) / 2 + fm.getAscent();
                    
                    
                    String[] lines = placeholder.split("\n");
                    for (int i = 0; i < lines.length; i++) {
                        int x = 5;
                        g2.drawString(lines[i], x, y + (i * textHeight));
                    }
                    
                    g2.dispose();
                }
            }
        };
        
        txtAreaNuevoMensaje.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaNuevoMensaje.setLineWrap(true);
        txtAreaNuevoMensaje.setWrapStyleWord(true);
        txtAreaNuevoMensaje.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_PRINCIPAL, 2, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
        txtAreaNuevoMensaje.setPreferredSize(new Dimension(0, 80));
        
      
        txtAreaNuevoMensaje.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txtAreaNuevoMensaje.getText().length() >= 500) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        
       
        JPanel textAreaPanel = new JPanel(new BorderLayout());
        JLabel lblContador = new JLabel("0/500");
        lblContador.setFont(new Font("Arial", Font.PLAIN, 11));
        lblContador.setForeground(new Color(127, 140, 141));
        lblContador.setHorizontalAlignment(SwingConstants.RIGHT);
        lblContador.setBorder(new EmptyBorder(0, 0, 5, 5));
        
        txtAreaNuevoMensaje.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            
            private void update() {
                int length = txtAreaNuevoMensaje.getText().length();
                lblContador.setText(length + "/500");
                lblContador.setForeground(length >= 450 ? Color.RED : new Color(127, 140, 141));
            }
        });
        
        textAreaPanel.add(txtAreaNuevoMensaje, BorderLayout.CENTER);
        textAreaPanel.add(lblContador, BorderLayout.SOUTH);
        
        JButton btnEnviar = new JButton("publicar");
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEnviar.setBackground(new Color(46, 204, 113));
        btnEnviar.setForeground(Color.GRAY);
        btnEnviar.setFocusPainted(false);
        btnEnviar.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        bottomPanel.add(textAreaPanel, BorderLayout.CENTER);
        bottomPanel.add(btnEnviar, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
        
        btnEnviar.addActionListener(e -> publicarMensajePrincipal());
    }
    
    public void cargarMensajes() {
        mensajesPanel.removeAll();
        panelesMensajes.clear();
        
        List<Mensaje> mensajesPrincipales = mensajeDAO.listarMensajesPrincipales();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        if (mensajesPrincipales.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay mensajes en el foro. ¡Sé el primero en publicar!");
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 14));
            lblVacio.setForeground(new Color(127, 140, 141));
            lblVacio.setHorizontalAlignment(SwingConstants.CENTER);
            lblVacio.setBorder(new EmptyBorder(50, 0, 50, 0));
            mensajesPanel.add(lblVacio);
        } else {
            for (Mensaje mensaje : mensajesPrincipales) {
                JPanel mensajeCard = crearTarjetaMensaje(mensaje, sdf, false);
                panelesMensajes.put(mensaje.getId(), mensajeCard);
                mensajesPanel.add(mensajeCard);
                
              
                cargarRespuestas(mensaje.getId(), sdf);
                
                mensajesPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }
        
        mensajesPanel.revalidate();
        mensajesPanel.repaint();
    }
    
    private void cargarRespuestas(int idMensajePadre, SimpleDateFormat sdf) {
        List<Mensaje> respuestas = mensajeDAO.listarRespuestas(idMensajePadre);
        
        if (!respuestas.isEmpty()) {
            JPanel respuestasPanel = new JPanel();
            respuestasPanel.setLayout(new BoxLayout(respuestasPanel, BoxLayout.Y_AXIS));
            respuestasPanel.setBackground(COLOR_RESPUESTA);
            respuestasPanel.setBorder(new EmptyBorder(10, 30, 10, 10)); 
            
            for (Mensaje respuesta : respuestas) {
                JPanel respuestaCard = crearTarjetaMensaje(respuesta, sdf, true);
                respuestasPanel.add(respuestaCard);
                respuestasPanel.add(Box.createRigidArea(new Dimension(0, 8)));
            }
            
         
            JPanel padrePanel = panelesMensajes.get(idMensajePadre);
            if (padrePanel != null) {
                padrePanel.add(respuestasPanel, BorderLayout.SOUTH);
            }
        }
    }
    
    private JPanel crearTarjetaMensaje(Mensaje mensaje, SimpleDateFormat sdf, boolean esRespuesta) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(esRespuesta ? COLOR_RESPUESTA : Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(esRespuesta ? new Color(200, 200, 200) : COLOR_PRINCIPAL, 1, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
       
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setOpaque(false);
        
        JLabel lblUsuario = new JLabel(mensaje.getNombreUsuario() != null ? 
            mensaje.getNombreUsuario() : "Usuario #" + mensaje.getIdUsuario());
        lblUsuario.setFont(new Font("Arial", Font.BOLD, esRespuesta ? 13 : 14));
        lblUsuario.setForeground(esRespuesta ? new Color(52, 73, 94) : COLOR_PRINCIPAL);
        
        JLabel lblFecha = new JLabel(sdf.format(mensaje.getFecha()));
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 11));
        lblFecha.setForeground(new Color(127, 140, 141));
        
        infoPanel.add(lblUsuario, BorderLayout.WEST);
        infoPanel.add(lblFecha, BorderLayout.EAST);
        
        
        JTextArea contenido = new JTextArea(mensaje.getContenido());
        contenido.setWrapStyleWord(true);
        contenido.setLineWrap(true);
        contenido.setEditable(false);
        contenido.setOpaque(false);
        contenido.setFont(new Font("Arial", Font.PLAIN, esRespuesta ? 13 : 14));
        
        JScrollPane scrollContenido = new JScrollPane(contenido);
        scrollContenido.setBorder(null);
        scrollContenido.setOpaque(false);
        scrollContenido.getViewport().setOpaque(false);
        
      
        JPanel accionesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        accionesPanel.setOpaque(false);
        
        if (!esRespuesta) {
            JButton btnResponder = new JButton("Responder");
            btnResponder.setFont(new Font("Arial", Font.PLAIN, 12));
            btnResponder.setBackground(new Color(155, 89, 182));
            btnResponder.setForeground(Color.GRAY);
            btnResponder.setFocusPainted(false);
            btnResponder.setBorder(new EmptyBorder(5, 10, 5, 10));
            
            btnResponder.addActionListener(e -> mostrarDialogoRespuesta(mensaje.getId()));
            accionesPanel.add(btnResponder);
        }
        
      
        if (esRespuesta) {
            JLabel lblRespuestaIcon = new JLabel("Respuestas");
            lblRespuestaIcon.setFont(new Font("Arial", Font.BOLD, 14));
            infoPanel.add(lblRespuestaIcon, BorderLayout.WEST);
        }
        
        card.add(infoPanel, BorderLayout.NORTH);
        card.add(scrollContenido, BorderLayout.CENTER);
        card.add(accionesPanel, BorderLayout.SOUTH);
        
        return card;
    }
    
    private void mostrarDialogoRespuesta(int idMensajePadre) {
        Usuario usuario = mainFrame.getUsuarioActual();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión para responder");
            return;
        }
        
       
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Responder Mensaje", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 300);
        dialog.setLocationRelativeTo(this);
        
        
        Mensaje mensajeOriginal = mensajeDAO.obtenerPorId(idMensajePadre);
        
        JPanel contenidoPanel = new JPanel(new BorderLayout(10, 10));
        contenidoPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
       
        JTextArea txtOriginal = new JTextArea("Respondiendo a: " + 
            (mensajeOriginal != null ? mensajeOriginal.getContenido() : ""));
        txtOriginal.setEditable(false);
        txtOriginal.setLineWrap(true);
        txtOriginal.setWrapStyleWord(true);
        txtOriginal.setBackground(new Color(240, 240, 240));
        txtOriginal.setBorder(new EmptyBorder(8, 8, 8, 8));
        
       
        JTextArea txtRespuesta = new JTextArea(4, 40);
        txtRespuesta.setLineWrap(true);
        txtRespuesta.setWrapStyleWord(true);
        txtRespuesta.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(COLOR_PRINCIPAL, 1),
            new EmptyBorder(8, 8, 8, 8)
        ));
        
       
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEnviar = new JButton("Enviar Respuesta");
        btnEnviar.setBackground(new Color(46, 204, 113));
        btnEnviar.setForeground(Color.GRAY);
        
        botonesPanel.add(btnCancelar);
        botonesPanel.add(btnEnviar);
        
        contenidoPanel.add(new JLabel("Mensaje original:"), BorderLayout.NORTH);
        contenidoPanel.add(new JScrollPane(txtOriginal), BorderLayout.CENTER);
        contenidoPanel.add(new JLabel("Tu respuesta:"), BorderLayout.WEST);
        contenidoPanel.add(new JScrollPane(txtRespuesta), BorderLayout.SOUTH);
        
        dialog.add(contenidoPanel, BorderLayout.CENTER);
        dialog.add(botonesPanel, BorderLayout.SOUTH);
        
       
        btnCancelar.addActionListener(e -> dialog.dispose());
        btnEnviar.addActionListener(e -> {
            String respuesta = txtRespuesta.getText().trim();
            if (respuesta.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "La respuesta no puede estar vacía");
                return;
            }
            
            Mensaje nuevaRespuesta = new Mensaje(respuesta, usuario.getId(), idMensajePadre);
            if (mensajeDAO.publicar(nuevaRespuesta)) {
                JOptionPane.showMessageDialog(dialog, "Respuesta publicada exitosamente");
                dialog.dispose();
                cargarMensajes(); 
            } else {
                JOptionPane.showMessageDialog(dialog, "Error al publicar la respuesta");
            }
        });
        
        dialog.setVisible(true);
    }
    
    private void publicarMensajePrincipal() {
        Usuario usuario = mainFrame.getUsuarioActual();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión para publicar");
            return;
        }
        
        String texto = txtAreaNuevoMensaje.getText().trim();
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
            txtAreaNuevoMensaje.setText("");
            cargarMensajes();
            JOptionPane.showMessageDialog(this, "Mensaje publicado exitosamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al publicar el mensaje");
        }
    }
}