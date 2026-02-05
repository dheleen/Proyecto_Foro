package proyecto_foro.vista;

import proyecto_foro.dao.usuarioDAO;
import proyecto_foro.modelo.usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginFrame() {
        setTitle("Login - Foro");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 20, 80, 25);
        panel.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 20, 150, 25);
        panel.add(txtEmail);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(20, 60, 80, 25);
        panel.add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 60, 150, 25);
        panel.add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 100, 80, 25);
        panel.add(btnLogin);

        btnLogin.addActionListener((ActionEvent e) -> login());

        add(panel);
    }

    private void login() {
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        usuarioDAO dao = new usuarioDAO();
        usuario usuario = dao.login(email, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());
            dispose(); // cerrar login
            new ForoFrame(usuario).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }
}
