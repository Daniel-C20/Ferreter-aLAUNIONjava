package PantallasProyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bienvenida extends JFrame {
    private JTextField campoNombre;
    private JButton botonContinuar;
    private JLabel etiquetaError;

    public Bienvenida() {
        setTitle("Ferretería LA UNION");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con logo
        JPanel panelSuperior = new JPanel();
        ImageIcon logo = new ImageIcon("img/logo1.png");
        JLabel etiquetaLogo = new JLabel(logo);
        panelSuperior.add(etiquetaLogo);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central para centrar el campo de texto
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Configuración de colores oscuros
        getContentPane().setBackground(Color.DARK_GRAY);
        panelSuperior.setBackground(Color.DARK_GRAY);
        panelCentral.setBackground(Color.DARK_GRAY);

        // Etiqueta de nombre
        JLabel etiquetaNombre = new JLabel("Introduce tu nombre:");
        etiquetaNombre.setForeground(Color.WHITE);
        panelCentral.add(etiquetaNombre, gbc);

        // Campo de texto para el nombre
        gbc.gridy = 1;
        campoNombre = new JTextField();
        campoNombre.setPreferredSize(new Dimension(200, 30));
        campoNombre.setBackground(Color.GRAY);
        campoNombre.setForeground(Color.WHITE);
        panelCentral.add(campoNombre, gbc);

        // Etiqueta de error
        gbc.gridy = 2;
        etiquetaError = new JLabel("", SwingConstants.CENTER);
        etiquetaError.setForeground(Color.RED);
        panelCentral.add(etiquetaError, gbc);

        // Botón de continuar
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 10, 10, 10);
        botonContinuar = new JButton("Continuar");
        panelCentral.add(botonContinuar, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con la información del curso
        JPanel panelInferior = new JPanel();
        JLabel info = new JLabel("Ferreteria LA UNION | Henry Daniel Cabrera Estrada | Curso: Programación II | Sección D");
        info.setForeground(Color.WHITE);
        panelInferior.add(info);
        panelInferior.setBackground(Color.DARK_GRAY);
        add(panelInferior, BorderLayout.SOUTH);

        // Acción del botón continuar
        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (campoNombre.getText().isEmpty()) {
                    etiquetaError.setText("Por favor, introduce tu nombre.");
                } else {
                    new TerminosYCondiciones(campoNombre.getText()).setVisible(true);
                    dispose();
                }
            }
        });

        // Cargar icono de la aplicación (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("img/favicon.png");
        setIconImage(icon);
    }

    public static void main(String[] args) {
        Bienvenida ventana = new Bienvenida();
        ventana.setVisible(true);
    }
}
