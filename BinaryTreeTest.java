import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeTest {
    private BinaryTree<String, Producto> tree;
    
    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
    }

    @Test
    void testInsertAndSearch() {
        Map<String, Integer> tallas = new HashMap<>();
        tallas.put("M", 10);
        Producto producto = new Producto("SKU123", "Camiseta", "Camiseta deportiva", tallas);
        
        tree.insert("SKU123", producto);
        Producto resultado = tree.search("SKU123");
        
        assertNotNull(resultado, "El producto no debería ser nulo");
        assertEquals("Camiseta", resultado.nombre, "El nombre del producto no coincide");
        assertEquals("Camiseta deportiva", resultado.descripcion, "La descripción del producto no coincide");
        assertTrue(resultado.tallas.containsKey("M"), "Las tallas deberían incluir 'M'");
        assertEquals(10, resultado.tallas.get("M"), "La cantidad de talla 'M' debería ser 10");
    }

    @Test
    void testSearchNonExistent() {
        assertNull(tree.search("SKU999"), "La búsqueda de un SKU inexistente debería devolver null");
    }
}

