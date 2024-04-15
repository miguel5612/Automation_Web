package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.models.producto;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;
import java.util.stream.Collectors;

import static bo.com.test.prueba.tecnica.userinterfaces.carritoCompras.*;
import static bo.com.test.prueba.tecnica.userinterfaces.paginaCompras.BTN_CARRITO;

public class verificarSubtotalCarrito implements Task {

    public verificarSubtotalCarrito() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Supongamos que recuperas la lista de productos agregados así:
        List<producto> productosAgregados = actor.recall("productosAgregados");
        int subtotalEsperado = calcularSubtotalEsperado(productosAgregados);

        // Extraer el subtotal mostrado
        String subtotalTexto = SUBTOTAL_CARRITO_COMPRAS.resolveFor(actor).getText();
        subtotalTexto = subtotalTexto.replaceAll("[^\\d]", "");  // Remover cualquier carácter no numérico
        int subtotalMostrado = Integer.parseInt(subtotalTexto);

        // Comparar el subtotal esperado con el mostrado
        if (subtotalEsperado != subtotalMostrado) {
            throw new AssertionError("El subtotal del carrito (" + subtotalMostrado +
                    ") no coincide con el subtotal esperado (" + subtotalEsperado + ").");
        }
    }

    private int calcularSubtotalEsperado(List<producto> productos) {
        return productos.stream()
                .mapToInt(producto -> (int) (producto.getPrecio() * producto.getCantidad()))
                .sum();
    }

    public static verificarSubtotalCarrito con() {
        return Tasks.instrumented(verificarSubtotalCarrito.class);
    }
}
