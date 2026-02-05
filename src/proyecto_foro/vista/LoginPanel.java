/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.vista;

import proyecto_foro.modelo.Usuario;
import proyecto_foro.servicio.autentificacion;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("FORO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));

        txtEmail = new JTextField(20);
        txtPassword = new JPasswordField(20);

        JButton btnLogin = new JButton("Login");

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblTitulo, gbc);

        gbc.gridy++;
        add(new JLabel("Email:"), gbc);
        gbc.gridy++;
        add(txtEmail, gbc);

        gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        add(txtPassword, gbc);

        gbc.gridy++;
        add(btnLogin, gbc);

        btnLogin.addActionListener(e -> login());
    }

    private void login() {
        autentificacion service = new autentificacion();
        Usuario usuario = service.login(
                txtEmail.getText(),
                new String(txtPassword.getPassword())
        );

        if (usuario != null) {
            mainFrame.mostrar(MainFrame.FORO);
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }
}
