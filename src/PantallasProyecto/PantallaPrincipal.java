/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PantallasProyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal extends JFrame {
    private JComboBox<String> comboDepartamento, comboAntiguedad;
    private JTextField campoNombre, campoApellidos, campoResultado;
    private JButton botonCalcular, botonLimpiar, botonRegresar;
    private String nombreUsuario;

    public PantallaPrincipal(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        setTitle("Ferretería LA UNION");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(new BorderLayout());

        // Panel superior con logo
        JPanel panelSuperior = new JPanel();
        ImageIcon logo = new ImageIcon("img/logo2.png"); // Cargar imagen del logo
        JLabel etiquetaLogo = new JLabel(logo);
        panelSuperior.add(etiquetaLogo);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central con los campos del formulario
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta para el nombre del usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel etiquetaUsuario = new JLabel("Usuario: " + nombreUsuario);
        panelCentral.add(etiquetaUsuario, gbc);

        // Campo para el nombre
        gbc.gridy = 1;
        JLabel etiquetaNombre = new JLabel("Nombre:");
        panelCentral.add(etiquetaNombre, gbc);

        gbc.gridx = 1;
        campoNombre = new JTextField(20);
        panelCentral.add(campoNombre, gbc);

        // Campo para los apellidos
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel etiquetaApellidos = new JLabel("Apellidos:");
        panelCentral.add(etiquetaApellidos, gbc);

        gbc.gridx = 1;
        campoApellidos = new JTextField(20);
        panelCentral.add(campoApellidos, gbc);

        // Combo box para seleccionar el departamento
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel etiquetaDepartamento = new JLabel("Departamento:");
        panelCentral.add(etiquetaDepartamento, gbc);

        gbc.gridx = 1;
        String[] departamentos = {"Atención al Cliente", "Logística", "Gerente"};
        comboDepartamento = new JComboBox<>(departamentos);
        panelCentral.add(comboDepartamento, gbc);

        // Combo box para seleccionar la antigüedad
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel etiquetaAntiguedad = new JLabel("Antigüedad (años):");
        panelCentral.add(etiquetaAntiguedad, gbc);

        gbc.gridx = 1;
        String[] antiguedad = {"1", "2", "3", "4", "5", "6", "7+"};
        comboAntiguedad = new JComboBox<>(antiguedad);
        panelCentral.add(comboAntiguedad, gbc);

        // Campo de texto para mostrar el resultado
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel etiquetaResultado = new JLabel("Días de Vacaciones:");
        panelCentral.add(etiquetaResultado, gbc);

        gbc.gridx = 1;
        campoResultado = new JTextField(20);
        campoResultado.setEditable(false); // Campo no editable
        panelCentral.add(campoResultado, gbc);

        // Botones de calcular, limpiar y regresar
        gbc.gridx = 0;
        gbc.gridy = 6;
        botonCalcular = new JButton("Calcular");
        panelCentral.add(botonCalcular, gbc);

        gbc.gridx = 1;
        botonLimpiar = new JButton("Limpiar");
        panelCentral.add(botonLimpiar, gbc);

        gbc.gridx = 2;
        botonRegresar = new JButton("Regresar a Bienvenida");
        panelCentral.add(botonRegresar, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior con la información del curso
        JPanel panelInferior = new JPanel();
        JLabel info = new JLabel("Ferretería LA UNION | Henry Daniel Cabrera Estrada | Curso: Programación II | Sección D");
        panelInferior.add(info);
        add(panelInferior, BorderLayout.PAGE_END);

        // Acción del botón Calcular
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularVacaciones();
            }
        });

        // Acción del botón Limpiar
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // Acción del botón Regresar
        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Bienvenida().setVisible(true);
                dispose();
            }
        });

        // Cargar icono de la aplicación (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("img/favicon.png");
        setIconImage(icon);
    }

    // Método para calcular los días de vacaciones
    private void calcularVacaciones() {
        String departamento = (String) comboDepartamento.getSelectedItem();
        int antiguedad = comboAntiguedad.getSelectedIndex() + 1;
        int diasVacaciones = 0;

        // Lógica de cálculo según el departamento y la antigüedad
        switch (departamento) {
            case "Atención al Cliente":
                if (antiguedad == 1) diasVacaciones = 6;
                else if (antiguedad <= 6) diasVacaciones = 14;
                else diasVacaciones = 20;
                break;
            case "Logística":
                if (antiguedad == 1) diasVacaciones = 7;
                else if (antiguedad <= 6) diasVacaciones = 15;
                else diasVacaciones = 22;
                break;
            case "Gerente":
                if (antiguedad == 1) diasVacaciones = 10;
                else if (antiguedad <= 6) diasVacaciones = 20;
                else diasVacaciones = 30;
                break;
        }

        campoResultado.setText(String.valueOf(diasVacaciones));
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        campoNombre.setText("");
        campoApellidos.setText("");
        comboDepartamento.setSelectedIndex(0);
        comboAntiguedad.setSelectedIndex(0);
        campoResultado.setText("");
    }
}
