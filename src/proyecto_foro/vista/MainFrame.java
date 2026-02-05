/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_foro.vista;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public static final String LOGIN = "LOGIN";
    public static final String FORO = "FORO";

    private CardLayout cardLayout;
    private JPanel contenedor;

    public MainFrame() {
        setTitle("Foro - Aplicación de Escritorio");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.add(new LoginPanel(this), LOGIN);
        contenedor.add(new ForoPanel(this), FORO);

        add(contenedor);
    }

    public void mostrar(String vista) {
        cardLayout.show(contenedor, vista);
    }
}
