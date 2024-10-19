import java.sql.*;
import java.util.Scanner;

public class MenuBD {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/BDNegocio";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Ingresar nuevo producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Modificar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    ingresarProducto();
                    break;
                case 2:
                    mostrarProductos();
                    break;
                case 3:
                    modificarProducto();
                    break;
                case 4:
                    eliminarProducto();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 5);

        scanner.close();
    }

    // Método para ingresar un nuevo producto
    private static void ingresarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del producto: ");
        String nombreProducto = scanner.nextLine();
        System.out.print("Ingrese el precio unitario: ");
        double precioUnitario = scanner.nextDouble();
        System.out.print("Ingrese la cantidad del producto: ");
        int cantidadProducto = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la fecha de vencimiento (YYYY-MM-DD) o presione Enter si no aplica: ");
        String fechaVencimiento = scanner.nextLine();

        // Realizar la inserción en la base de datos
        String query = "INSERT INTO producto (nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?)";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, nombreProducto);
            pst.setDouble(2, precioUnitario);
            pst.setInt(3, cantidadProducto);
            if (fechaVencimiento.isEmpty()) {
                pst.setNull(4, Types.DATE); // Si no hay fecha, se inserta null
            } else {
                pst.setDate(4, Date.valueOf(fechaVencimiento));
            }
            pst.executeUpdate();
            System.out.println("Producto ingresado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al ingresar el producto: " + e.getMessage());
        }
    }

    // Método para mostrar todos los productos
    private static void mostrarProductos() {
        String query = "SELECT * FROM producto";
        try (Connection con = conectar(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            // Imprimir encabezados de la tabla
            System.out.printf("%-15s %-25s %-15s %-15s %-20s%n", "Código", "Nombre", "Precio", "Cantidad", "Fecha de Vencimiento");
            System.out.println("--------------------------------------------------------------------------------------------------");
            
            boolean hayResultados = false;
            while (rs.next()) {
                hayResultados = true;
                // Imprimir cada producto en una fila
                System.out.printf("%-15d %-25s %-15.2f %-15d %-20s%n",
                        rs.getInt("codigoProducto"),
                        rs.getString("nombreProducto"),
                        rs.getDouble("precioUnitario"),
                        rs.getInt("cantidadProducto"),
                        rs.getDate("fechaVencimiento") != null ? rs.getDate("fechaVencimiento").toString() : "N/A");
            }
            if (!hayResultados) {
                System.out.println("No hay productos disponibles.");
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los productos: " + e.getMessage());
        }
    }

    // Método para modificar un producto
    private static void modificarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código del producto a modificar: ");
        int codigoProducto = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nuevoNombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo precio unitario: ");
        double nuevoPrecio = scanner.nextDouble();

        String query = "UPDATE producto SET nombreProducto = ?, precioUnitario = ? WHERE codigoProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, nuevoNombre);
            pst.setDouble(2, nuevoPrecio);
            pst.setInt(3, codigoProducto);
            pst.executeUpdate();
            System.out.println("Producto modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto
    private static void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el código del producto a eliminar: ");
        int codigoProducto = scanner.nextInt();

        String query = "DELETE FROM producto WHERE codigoProducto = ?";
        try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, codigoProducto);
            pst.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        mostrarMenu();
    }
}
