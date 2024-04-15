package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.models.producto;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import java.util.List;
import java.util.stream.Collectors;

import static bo.com.test.prueba.tecnica.userinterfaces.carritoCompras.CANTIDADES_PRODUCTOS;
import static bo.com.test.prueba.tecnica.userinterfaces.carritoCompras.NOMBRES_PRODUCTOS;
import static bo.com.test.prueba.tecnica.userinterfaces.paginaCompras.BTN_CARRITO;

public class verificarCantidadesDeProductosEnElCarrito implements Task {

    public verificarCantidadesDeProductosEnElCarrito() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        verificarQTYDeProductosEnElCarrito(actor);
    }

    private void verificarQTYDeProductosEnElCarrito(Actor actor) {
        List<WebElementFacade> elementosCantidadesProductos = CANTIDADES_PRODUCTOS.resolveAllFor(actor);
        List<Integer> cantidadesProductosCarrito = elementosCantidadesProductos.stream()
                .map(elemento -> Integer.parseInt(elemento.getText().trim()))
                .collect(Collectors.toList());

        // Suponiendo que también has guardado las cantidades esperadas en algún lugar
        List<producto> productosAgregados = actor.recall("productosAgregados");
        List<Integer> cantidadesProductosAgregados = productosAgregados.stream()
                .map(producto::getCantidad)
                .collect(Collectors.toList());

        if (!cantidadesProductosCarrito.equals(cantidadesProductosAgregados)) {
            throw new AssertionError("Las cantidades de los productos en el carrito no coinciden con las esperadas.");
        }
    }

    public static verificarCantidadesDeProductosEnElCarrito con() {
        return Tasks.instrumented(verificarCantidadesDeProductosEnElCarrito.class);
    }
}
