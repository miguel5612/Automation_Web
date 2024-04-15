package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.models.producto;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bo.com.test.prueba.tecnica.userinterfaces.carritoCompras.NOMBRES_PRODUCTOS;
import static bo.com.test.prueba.tecnica.userinterfaces.paginaCompras.*;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.findAll;

public class verificarNombresCarrito implements Task {

    public verificarNombresCarrito() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_CARRITO)
        );
        verificarNombresDeProductosEnElCarrito(actor);
    }

    private void verificarNombresDeProductosEnElCarrito(Actor actor) {
        // Aquí recuperamos la lista de productos agregados previamente
        List<producto> productosAgregados = actor.recall("productosAgregados");
        List<String> nombresProductosAgregados = productosAgregados.stream()
                .map(producto::getNombre)
                .collect(Collectors.toList());

        List<WebElementFacade> elementosNombresProductos = NOMBRES_PRODUCTOS.resolveAllFor(actor);
        List<String> nombresProductosCarrito = elementosNombresProductos.stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());

        for (String nombreProductoAgregado : nombresProductosAgregados) {
            if (!nombresProductosCarrito.contains(nombreProductoAgregado)) {
                throw new AssertionError("El producto " + nombreProductoAgregado + " no está presente en el carrito.");
            }
        }
    }

    public static verificarNombresCarrito con() {
        return Tasks.instrumented(verificarNombresCarrito.class);
    }
}
