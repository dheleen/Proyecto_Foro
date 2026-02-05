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
    private JTextField txtNombre; 
    private JPasswordField txtPasswordConfirm; 
    private JPanel cardPanel; 
    private CardLayout cardLayout;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        
    
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
   
        cardPanel.add(crearLoginPanel(), "LOGIN");
        cardPanel.add(crearRegistroPanel(), "REGISTRO");
        
        add(cardPanel, BorderLayout.CENTER);
        

        cardLayout.show(cardPanel, "LOGIN");
    }
    
    private JPanel crearLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
       
        JLabel lblTitulo = new JLabel("FORO - INICIAR SESIÓN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(41, 128, 185));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
      
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 14));
        lblEmail.setForeground(new Color(44, 62, 80));
        
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 14));
        lblPassword.setForeground(new Color(44, 62, 80));
        
    
        txtEmail = new JTextField(25);
        txtEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        txtEmail.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
        txtPassword = new JPasswordField(25);
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
        
 
        JButton btnLogin = new JButton("INICIAR SESIÓN");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBackground(new Color(46, 204, 113));
        btnLogin.setForeground(Color.GRAY);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JButton btnIrARegistro = new JButton("¿No tienes cuenta? Regístrate");
        btnIrARegistro.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIrARegistro.setForeground(new Color(20, 20, 20));
        btnIrARegistro.setBorderPainted(false);
        btnIrARegistro.setContentAreaFilled(false);
        btnIrARegistro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
  
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);
        
        gbc.gridy++; gbc.gridwidth = 1;
        panel.add(lblEmail, gbc);
        gbc.gridx = 1;
        panel.add(txtEmail, gbc);
        
        
        gbc.gridx = 0; gbc.gridy++;
        panel.add(lblPassword, gbc);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);
        
        gbc.gridy++;
        panel.add(btnIrARegistro, gbc);
        
  
        btnLogin.addActionListener(e -> login());
        btnIrARegistro.addActionListener(e -> cardLayout.show(cardPanel, "REGISTRO"));
        
        return panel;
    }
    
    private JPanel crearRegistroPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(208, 230, 242)); // Fondo diferente
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        

        JLabel lblTitulo = new JLabel("CREAR CUENTA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(34, 117, 171));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setForeground(new Color(44, 62, 80));
        
        JLabel lblEmailReg = new JLabel("Email:");
        lblEmailReg.setFont(new Font("Arial", Font.BOLD, 14));
        lblEmailReg.setForeground(new Color(44, 62, 80));
        
        JLabel lblPasswordReg = new JLabel("Contraseña:");
        lblPasswordReg.setFont(new Font("Arial", Font.BOLD, 14));
        lblPasswordReg.setForeground(new Color(44, 62, 80));
        
        JLabel lblPasswordConfirm = new JLabel("Confirmar contraseña:");
        lblPasswordConfirm.setFont(new Font("Arial", Font.BOLD, 14));
        lblPasswordConfirm.setForeground(new Color(44, 62, 80));
        
 
        txtNombre = new JTextField(25);
        txtNombre.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        
        JTextField txtEmailReg = new JTextField(25);
        txtEmailReg.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        
        txtPasswordConfirm = new JPasswordField(25);
        txtPasswordConfirm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        
        JPasswordField txtPasswordReg = new JPasswordField(25);
        txtPasswordReg.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        
        JButton btnRegistrar = new JButton("REGISTRARSE");
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.GRAY);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 18));
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JButton btnIrALogin = new JButton("← Volver a Login");
        btnIrALogin.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIrALogin.setForeground(new Color(20, 20, 20));
        btnIrALogin.setBorderPainted(false);
        btnIrALogin.setContentAreaFilled(false);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);
        
        gbc.gridy++; gbc.gridwidth = 1;
        panel.add(lblNombre, gbc);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        panel.add(lblEmailReg, gbc);
        gbc.gridx = 1;
        panel.add(txtEmailReg, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        panel.add(lblPasswordReg, gbc);
        gbc.gridx = 1;
        panel.add(txtPasswordReg, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        panel.add(lblPasswordConfirm, gbc);
        gbc.gridx = 1;
        panel.add(txtPasswordConfirm, gbc);
        
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        panel.add(btnRegistrar, gbc);
        
        gbc.gridy++;
        panel.add(btnIrALogin, gbc);
        
        btnRegistrar.addActionListener(e -> registrar(
            txtNombre.getText(),
            txtEmailReg.getText(),
            new String(txtPasswordReg.getPassword()),
            new String(txtPasswordConfirm.getPassword())
        ));
        
        btnIrALogin.addActionListener(e -> cardLayout.show(cardPanel, "LOGIN"));
        
        return panel;
    }
    
    private void registrar(String nombre, String email, String password, String confirmacion) {
        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }
        
        if (!password.equals(confirmacion)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return;
        }
        
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Email inválido");
            return;
        }
        
        autentificacion service = new autentificacion();
        Usuario nuevoUsuario = new Usuario(nombre, email, password);
        
        if (service.registrar(nuevoUsuario)) {
            JOptionPane.showMessageDialog(this, "¡Registro exitoso! Ahora puedes iniciar sesión");
            cardLayout.show(cardPanel, "LOGIN");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar. El email ya existe.");
        }
    }

    private void login() {
        autentificacion service = new autentificacion();
        Usuario usuario = service.login(
                txtEmail.getText(),
                new String(txtPassword.getPassword())
        );

        if (usuario != null) {
            mainFrame.mostrar(MainFrame.FORO);
            mainFrame.setUsuarioActual(usuario);
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }
}