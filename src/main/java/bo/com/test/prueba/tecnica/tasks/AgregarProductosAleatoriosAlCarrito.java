package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.models.Producto;
import net.serenitybdd.core.pages.WaitForAngular;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bo.com.test.prueba.tecnica.userinterfaces.paginaCompras.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductosAleatoriosAlCarrito implements Task {
    private final int numeroDeProductos;
    private final int minCantidad;
    private final int maxCantidad;
    private List<Producto> productosAgregados = new ArrayList<>();
    private final Random random = new Random();

    public AgregarProductosAleatoriosAlCarrito(int numeroDeProductos, int minCantidad, int maxCantidad) {
        this.numeroDeProductos = numeroDeProductos;
        this.minCantidad = minCantidad;
        this.maxCantidad = maxCantidad;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> productos = PRODUCTOS.resolveAllFor(actor);
        int indiceProductoSeleccionado = random.nextInt(productos.size());
        int numProductosDisponibles = productos.size();
        for (int i = 0; i < numeroDeProductos; i++) {
            WebElementFacade productoSeleccionado = productos.get(indiceProductoSeleccionado);

            // Asegúrate de obtener el nombre y el precio del mismo índice del producto seleccionado
            String nombre = NOMBRE_PRODUCTO.resolveAllFor(actor).get(indiceProductoSeleccionado).getText();
            String precioTexto = PRECIO_PRODUCTO.resolveAllFor(actor).get(indiceProductoSeleccionado).getText();
            double precio = parsePrice(precioTexto); // Usa la función de ayuda para parsear el precio

            int cantidad = random.nextInt(maxCantidad - minCantidad + 1) + minCantidad;
            productosAgregados.add(new Producto(nombre, cantidad, precio));

            actor.attemptsTo(
                    Scroll.to(productoSeleccionado),
                    WaitUntil.the(BOTON_AGREGAR_AL_CARRITO, isVisible()).forNoMoreThan(10).seconds(),
                    Click.on(BOTON_AGREGAR_AL_CARRITO.resolveAllFor(actor).get(indiceProductoSeleccionado))
            );

            // Assuming the "Add to cart" action reveals the quantity selection interface
            WaitUntil.the(BOTON_MAS_CANTIDAD, isVisible()).forNoMoreThan(60).seconds();
            if (cantidad > 1) {
                for (int j = 1; j < cantidad; j++) {
                    actor.attemptsTo(

                            Scroll.to(BOTON_MAS_CANTIDAD),
                            Click.on(BOTON_MAS_CANTIDAD.resolveAllFor(actor).get(i))
                    );
                }
            }
            int incremento = 1 + random.nextInt(numProductosDisponibles - indiceProductoSeleccionado - 1);
            indiceProductoSeleccionado += incremento;

        }

        actor.remember("productosAgregados", productosAgregados);
    }

    private double parsePrice(String priceText) {
        String cleanedPrice = priceText.replace("$", "").replace(" ", "").replaceAll("\\.", "");
        try {
            return Double.parseDouble(cleanedPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio no pudo ser parseado: " + cleanedPrice);
        }
    }

    public static AgregarProductosAleatoriosAlCarrito con(int numeroDeProductos, int minCantidad, int maxCantidad) {
        return Tasks.instrumented(AgregarProductosAleatoriosAlCarrito.class, numeroDeProductos, minCantidad, maxCantidad);
    }
}
