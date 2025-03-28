import java.io.*;
import java.util.*;

public class Inventario {
    private BinaryTree<String, Producto> bstSKU = new BinaryTree<>();
    private BinaryTree<String, Producto> bstNombre = new BinaryTree<>();
    private Scanner scanner = new Scanner(System.in);

    public Inventario() {
        cargarDesdeCSV("inventario_ropa_deportiva_30.csv");
    }

    public void cargarDesdeCSV(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (partes.length < 4) continue;
                
                String SKU = partes[0].trim();
                String nombre = partes[1].trim();
                String descripcion = partes[2].trim();
                Map<String, Integer> tallas = new HashMap<>();
                
                for (String tallaInfo : partes[3].split("\\|")) {
                    String[] datos = tallaInfo.split(":");
                    if (datos.length == 2) {
                        tallas.put(datos[0], Integer.parseInt(datos[1]));
                    }
                }
                
                Producto producto = new Producto(SKU, nombre, descripcion, tallas);
                bstSKU.insert(SKU, producto);
                bstNombre.insert(nombre, producto);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + rutaArchivo);
        }
    }

    public void menu() {
        int opcion = -1;
        do {
            System.out.println("\nMenú de Inventario");
            System.out.println("1. Buscar producto por SKU");
            System.out.println("2. Buscar producto por Nombre");
            System.out.println("3. Listar productos por SKU");
            System.out.println("4. Listar productos por Nombre");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida, intente de nuevo.");
                continue;
            }
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el SKU a buscar: ");
                    String sku = scanner.nextLine();
                    Producto pSKU = buscarPorSKU(sku);
                    System.out.println(pSKU != null ? pSKU : "Producto no encontrado.");
                    break;
                case 2:
                    System.out.print("Ingrese el Nombre a buscar: ");
                    String nombre = scanner.nextLine();
                    Producto pNombre = buscarPorNombre(nombre);
                    System.out.println(pNombre != null ? pNombre : "Producto no encontrado.");
                    break;
                case 3:
                    listarProductosPorSKU();
                    break;
                case 4:
                    listarProductosPorNombre();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    public Producto buscarPorSKU(String SKU) {
        return bstSKU.search(SKU);
    }

    public Producto buscarPorNombre(String nombre) {
        return bstNombre.search(nombre);
    }

    public void listarProductosPorSKU() {
        bstSKU.inOrderTraversal();
    }

    public void listarProductosPorNombre() {
        bstNombre.inOrderTraversal();
    }

    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        inventario.menu();
    }
}

