import java.util.*;

public class Producto {
    String SKU;
    String nombre;
    String descripcion;
    Map<String, Integer> tallas;

    public Producto(String SKU, String nombre, String descripcion, Map<String, Integer> tallas) {
        this.SKU = SKU;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tallas = tallas;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTallas(Map<String, Integer> tallas) {
        this.tallas = tallas;
    }

    @Override
    public String toString() {
        return "SKU: " + SKU + ", Nombre: " + nombre + ", Descripción: " + descripcion + ", Tallas: " + tallas;
    }
}

