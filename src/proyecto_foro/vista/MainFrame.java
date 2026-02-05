/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// MainFrame.java - MODIFICAR
package proyecto_foro.vista;

import proyecto_foro.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final String LOGIN = "LOGIN";
    public static final String FORO = "FORO";

    private CardLayout cardLayout;
    private JPanel contenedor;
    private Usuario usuarioActual; // Guardar usuario logueado
    private ForoPanel foroPanel; // Referencia al panel de foro

    public MainFrame() {
        setTitle("Foro - Aplicación de Escritorio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Configurar Look and Feel para mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        
        // Crear instancia de ForoPanel que recibe MainFrame
        foroPanel = new ForoPanel(this);
        
        contenedor.add(new LoginPanel(this), LOGIN);
        contenedor.add(foroPanel, FORO);

        add(contenedor);
        
        // Centrar ventana
        setLocationRelativeTo(null);
    }

    public void mostrar(String vista) {
        cardLayout.show(contenedor, vista);
        if (vista.equals(FORO)) {
            foroPanel.cargarMensajes(); // Refrescar mensajes al entrar
        }
    }
    
    // Nuevos métodos
    public void setUsuarioActual(Usuario usuario) {
        this.usuarioActual = usuario;
        setTitle("Foro - Bienvenido: " + usuario.getNombre());
    }
    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public void logout() {
        usuarioActual = null;
        mostrar(LOGIN);
        setTitle("Foro - Aplicación de Escritorio");
    }
}