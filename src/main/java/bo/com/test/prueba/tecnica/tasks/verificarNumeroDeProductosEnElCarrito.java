package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.models.producto;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;

import static bo.com.test.prueba.tecnica.userinterfaces.carritoCompras.CANTIDADES_PRODUCTOS;

public class verificarNumeroDeProductosEnElCarrito implements Task {

    public verificarNumeroDeProductosEnElCarrito() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Recuperar la lista de productos agregados previamente
        List<producto> productosAgregados = actor.recall("productosAgregados");

        // Contar los productos en el carrito usando un selector apropiado
        int numeroDeProductosEnElCarrito = CANTIDADES_PRODUCTOS.resolveAllFor(actor).size();

        // Verificar que el número de productos en el carrito sea igual al número de productos agregados
        if (productosAgregados.size() != numeroDeProductosEnElCarrito) {
            throw new AssertionError("El número de productos en el carrito (" + numeroDeProductosEnElCarrito +
                    ") no coincide con el número esperado (" + productosAgregados.size() + ").");
        }
    }

    public static verificarNumeroDeProductosEnElCarrito con() {
        return Tasks.instrumented(verificarNumeroDeProductosEnElCarrito.class);
    }
}
