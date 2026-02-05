/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto_foro.vista;

import proyecto_foro.modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final String LOGIN = "LOGIN";
    public static final String FORO = "FORO";

    private CardLayout cardLayout;
    private JPanel contenedor;
    private Usuario usuarioActual; 
    private ForoPanel foroPanel; 

    public MainFrame() {
        setTitle("Foro - Aplicación de Escritorio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);
        
        foroPanel = new ForoPanel(this);
        
        contenedor.add(new LoginPanel(this), LOGIN);
        contenedor.add(foroPanel, FORO);

        add(contenedor);
        
        setLocationRelativeTo(null);
    }

    public void mostrar(String vista) {
        cardLayout.show(contenedor, vista);
        if (vista.equals(FORO)) {
            foroPanel.cargarMensajes(); 
        }
    }
    
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