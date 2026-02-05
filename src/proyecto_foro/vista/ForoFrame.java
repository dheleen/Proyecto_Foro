package proyecto_foro.vista;

import proyecto_foro.dao.mensajeDAO;
import proyecto_foro.modelo.Mensaje;
import proyecto_foro.modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ForoFrame extends JFrame {

    private Usuario usuario;
    private JTextArea txtMensajes;
    private JTextField txtNuevoMensaje;

    public ForoFrame(Usuario usuario) {
        this.usuario = usuario;
        setTitle("Foro - Usuario: " + usuario.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(null);

        txtMensajes = new JTextArea();
        txtMensajes.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtMensajes);
        scroll.setBounds(20, 20, 350, 150);
        panel.add(scroll);

        txtNuevoMensaje = new JTextField();
        txtNuevoMensaje.setBounds(20, 190, 260, 25);
        panel.add(txtNuevoMensaje);

        JButton btnEnviar = new JButton("Enviar");
        btnEnviar.setBounds(290, 190, 80, 25);
        panel.add(btnEnviar);

        btnEnviar.addActionListener((ActionEvent e) -> enviarMensaje());

        add(panel);
    }

    private void enviarMensaje() {
        String contenido = txtNuevoMensaje.getText();
        Mensaje mensaje = new Mensaje(contenido, usuario.getId());

        mensajeDAO dao = new mensajeDAO();
        dao.publicarMensaje(mensaje);

        txtMensajes.append(usuario.getNombre() + ": " + contenido + "\n");
        txtNuevoMensaje.setText("");
    }
}
