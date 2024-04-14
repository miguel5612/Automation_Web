package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.models.producto;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static bo.com.test.prueba.tecnica.userinterfaces.paginaCompras.*;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class agregarProductosAleatoriosAlCarrito implements Task {
    private final int numeroDeProductos;
    private final int minCantidad;
    private final int maxCantidad;
    private List<producto> productosAgregados = new ArrayList<>();
    private final Random random = new Random();

    public agregarProductosAleatoriosAlCarrito(int numeroDeProductos, int minCantidad, int maxCantidad) {
        this.numeroDeProductos = numeroDeProductos;
        this.minCantidad = minCantidad;
        this.maxCantidad = maxCantidad;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> productos = PRODUCTOS.resolveAllFor(actor);
        int cantidadTotalProductos = 0;
        for (int i = 0; i < numeroDeProductos; i++) {

            int indiceProductoSeleccionado = random.nextInt(productos.size());
            WebElementFacade productoSeleccionado = productos.get(indiceProductoSeleccionado);

            // Obtener el nombre y el precio directamente desde el producto seleccionado
            String nombre = productoSeleccionado.findBy(".//h3[@data-fs-product-card-title='true']/a").getText();
            String precioTexto = productoSeleccionado.findBy(".//div[contains(@class, 'ProductPrice_container__')]").getText();
            int precio = parsePrice(precioTexto);

            int cantidad = random.nextInt(maxCantidad - minCantidad + 1) + minCantidad;

            productosAgregados.add(new producto(nombre, cantidad, precio));

            // Interactuar con el botón agregar al carrito directamente bajo el producto seleccionado
            WebElementFacade botonAgregarAlCarrito = productoSeleccionado.findBy(".//button[contains(@class,'DefaultStyle_default__vCozi')]");
            actor.attemptsTo(
                    Scroll.to(productoSeleccionado).andAlignToTop()
            );

            if(cantidadTotalProductos > 0)
            {
                String cantidadElementosCarrito = CANTIDAD_CARRITO_COMPRAS.resolveFor(actor).getText();
                WaitUntil.the(cantidadElementosCarrito,
                        WebElementStateMatchers.containsText(String.valueOf(cantidadTotalProductos))
                ).forNoMoreThan(10).seconds();
            }

            botonAgregarAlCarrito.waitUntilVisible();
            actor.attemptsTo(
                    Click.on(botonAgregarAlCarrito)
            );
            cantidadTotalProductos = cantidadTotalProductos + 1;

            // Aumentar la cantidad N veces
            for (int j = 1; j < cantidad; j++) {
                // Esperar que el botón de cantidad sea visible y luego interactuar con él
                WebElementFacade botonMasCantidad = productoSeleccionado.findBy(".//button[contains(@class, 'QuantitySelectorDefault_plus__')]")
                        .withTimeoutOf(Duration.ofSeconds(35))
                        .waitUntilPresent();
                botonMasCantidad.waitUntilVisible();

                WebElementFacade cantidadActualElemento = productoSeleccionado.findBy(".//div[@class='QuantitySelectorDefault_custom-quantity-selector__selector__info__6M6Yp']/p");

                cantidadActualElemento.waitUntilVisible();

                String textCompareQTT = cantidadActualElemento.getText().replaceAll("\\D+", "");;

                WaitUntil.the(textCompareQTT,
                        WebElementStateMatchers.containsText(String.valueOf(j))
                ).forNoMoreThan(10).seconds();

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

                // Suponiendo que CANTIDAD_CARRITO_COMPRAS es de tipo Target y actor es tu Actor.
                WebElementFacade cantidadElementosCarritoFacade = CANTIDAD_CARRITO_COMPRAS.resolveFor(actor);

                wait.until(ExpectedConditions.textToBePresentInElement(cantidadElementosCarritoFacade,
                        String.valueOf(cantidadTotalProductos)));

                actor.attemptsTo(Click.on(botonMasCantidad));
                cantidadTotalProductos = cantidadTotalProductos + 1;

            }
        }

        actor.remember("productosAgregados", productosAgregados);
    }

    private int parsePrice(String priceText) {
        String cleanedPrice = priceText.trim().replaceAll("[^0-9]", "");
        try {
            return Integer.parseInt(cleanedPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio no pudo ser parseado: " + cleanedPrice);
        }
    }

    public static agregarProductosAleatoriosAlCarrito con(int numeroDeProductos, int minCantidad, int maxCantidad) {
        return Tasks.instrumented(agregarProductosAleatoriosAlCarrito.class, numeroDeProductos, minCantidad, maxCantidad);
    }
}
