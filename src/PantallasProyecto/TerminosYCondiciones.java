package PantallasProyecto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TerminosYCondiciones extends JFrame {
    private JCheckBox aceptarTerminos;
    private JButton botonContinuar, botonNoAceptar;
    private String nombreUsuario;

    public TerminosYCondiciones(String nombreUsuario) {
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

        // Panel central con los términos y condiciones
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel etiquetaNombre = new JLabel("Usuario: " + nombreUsuario);
        panelCentral.add(etiquetaNombre, gbc);

        gbc.gridy = 1;
        JTextArea terminosTexto = new JTextArea(15, 50); // Aumentar líneas visibles
        terminosTexto.setText("Términos y Condiciones de Uso del Programa de la Ferretería [Nombre de la Ferretería]\n" +
"\n" +
"1. Aceptación de los Términos Al utilizar el software o aplicación proporcionado por [Nombre de la Ferretería], el usuario acepta estar"+"\n" 
                + " sujeto a los siguientes Términos y Condiciones de uso. Si no está de acuerdo con estos términos, no utilice el programa.\n" +
"\n" +
"2. Descripción del Servicio El programa es una herramienta diseñada para facilitar la compra de productos y servicios relacionados con la ferretería."+"\n"
                + " A través de la aplicación, los usuarios podrán consultar catálogos, realizar pedidos, gestionar su cuenta de cliente y acceder a"+"\n"
                + " promociones exclusivas.\n" +
"\n" +
"3. Registro y Cuenta de Usuario Para acceder a todas las funcionalidades del programa, el usuario debe crear una cuenta proporcionando información veraz"+"\n"
                + " y actualizada. El usuario es responsable de mantener la confidencialidad de su cuenta y contraseña, y de todas las actividades que"+"\n"
                + " ocurran bajo su cuenta.\n" +
"\n" +
"4. Uso Aceptable El usuario se compromete a utilizar el programa de manera responsable y de conformidad con las leyes aplicables. Está prohibido el"+"\n"
                + " uso del software para realizar actividades ilegales, fraudulentas o para causar daño a otros usuarios o a la ferretería.");
        terminosTexto.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(terminosTexto); // Agregamos un JScrollPane por si el texto es largo
        panelCentral.add(scrollPane, gbc);

        gbc.gridy = 2;
        aceptarTerminos = new JCheckBox("Aceptar términos y condiciones");
        panelCentral.add(aceptarTerminos, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel para botones y pie de página usando BoxLayout
        JPanel panelInferiorCompleto = new JPanel();
        panelInferiorCompleto.setLayout(new BoxLayout(panelInferiorCompleto, BoxLayout.Y_AXIS));

        // Panel para los botones de continuar y no aceptar
        JPanel panelBotones = new JPanel();
        botonContinuar = new JButton("Continuar");
        botonContinuar.setEnabled(false); // Deshabilitado por defecto
        botonNoAceptar = new JButton("No aceptar");
        panelBotones.add(botonContinuar);
        panelBotones.add(botonNoAceptar);

        // Agregar el panel de botones al panel inferior completo
        panelInferiorCompleto.add(panelBotones);

        // Habilitar/deshabilitar botones según los términos aceptados
        aceptarTerminos.addActionListener(e -> {
            botonContinuar.setEnabled(aceptarTerminos.isSelected());
            botonNoAceptar.setEnabled(!aceptarTerminos.isSelected());
        });

        // Acción del botón Continuar
        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (aceptarTerminos.isSelected()) {
                    new PantallaPrincipal(nombreUsuario).setVisible(true);
                    dispose();
                }
            }
        });

        // Acción del botón No aceptar
        botonNoAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Bienvenida().setVisible(true);
                dispose();
            }
        });

        // Panel inferior con la información del curso
        JPanel panelInferior = new JPanel();
        JLabel info = new JLabel("Ferretería LA UNION | Henry Daniel Cabrera Estrada | Curso: Programación II | Sección D");
        panelInferior.add(info);
        panelInferiorCompleto.add(panelInferior);

        // Agregar el panel completo (botones + información) al sur de la ventana
        add(panelInferiorCompleto, BorderLayout.SOUTH);

        // Cargar icono de la aplicación (favicon)
        Image icon = Toolkit.getDefaultToolkit().getImage("img/favicon.png");
        setIconImage(icon);
    }
}
