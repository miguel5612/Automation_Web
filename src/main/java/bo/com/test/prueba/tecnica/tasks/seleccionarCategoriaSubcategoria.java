package bo.com.test.prueba.tecnica.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.*;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.PageElement;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.List;
import java.util.Random;

import static bo.com.test.prueba.tecnica.userinterfaces.paginaCompras.*;
import static bo.com.test.prueba.tecnica.userinterfaces.paginaPrincipal.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class seleccionarCategoriaSubcategoria implements Task {
    private Random random = new Random();
    private final int maxAttempts;

    public seleccionarCategoriaSubcategoria(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        int attempt = 0;
        boolean isSuccessful = false;

        while (!isSuccessful && attempt < maxAttempts) {
            actor.attemptsTo(
                    WaitUntil.the(BTNHamburguesa, isVisible()).forNoMoreThan(5).seconds(),
                    Click.on(BTNHamburguesa),
                    WaitUntil.the(ListaCategorias, isVisible()).forNoMoreThan(5).seconds()
            );

            WebElementFacade categoriaSeleccionada = seleccionarRandom(actor, ListaCategorias);
            System.out.println("Intento " + (attempt + 1) + ": Categoría seleccionada - " + categoriaSeleccionada.getText());

            actor.attemptsTo(
                    WaitUntil.the(SUBCategoria, isVisible()).forNoMoreThan(10).seconds()
            );

            isSuccessful = seleccionarSubcategoriaYVerificarProductos(actor);

            if (!isSuccessful) {
                System.out.println("Reintentando con otra categoría/subcategoría. Intento " + (attempt + 1));
            }
            attempt++;
        }

        if (!isSuccessful) {
            throw new IllegalStateException("No se pudo verificar la presencia de suficientes productos tras " + maxAttempts + " intentos.");
        }
    }

    private WebElementFacade seleccionarRandom(Actor actor, Target target) {
        List<WebElementFacade> opciones = target.resolveAllFor(actor);
        WebElementFacade seleccionada = opciones.get(random.nextInt(opciones.size()));
        actor.attemptsTo(Click.on(seleccionada));
        return seleccionada;
    }

    private boolean seleccionarSubcategoriaYVerificarProductos(Actor actor) {
        List<WebElementFacade> subCategorias = SUBCategoria.resolveAllFor(actor);

        if (subCategorias.isEmpty()) {
            System.out.println("No se encontraron subcategorías.");
            return false;
        }

        int indiceSeleccionado = random.nextInt(subCategorias.size());
        WebElementFacade subCategoriaSeleccionada = subCategorias.get(indiceSeleccionado);
        String nombreSubCat = subCategoriaSeleccionada.getText();
        System.out.println("Subcategoría con la que se esta intentando: " + nombreSubCat);

        actor.attemptsTo(
                Scroll.to(subCategoriaSeleccionada),
                Click.on(subCategoriaSeleccionada)
        );

        boolean productsVisible = tryToEnsureProductVisible(actor);

        if (productsVisible) {
            System.out.println("Subcategoría seleccionada con éxito: " + nombreSubCat);
        } else {
            System.out.println("La subcategoría " + nombreSubCat + " no tiene suficientes productos visibles.");
        }

        return productsVisible;
    }

    private boolean tryToEnsureProductVisible(Actor actor) {
        // Intenta verificar la visibilidad de los elementos necesarios
        boolean productosVisible = actorAsksFor(actor, WaitUntil.the(PRODUCTOS, isVisible()).forNoMoreThan(10).seconds());
        boolean botonVisible = actorAsksFor(actor, WaitUntil.the(BOTON_AGREGAR_AL_CARRITO, isVisible()).forNoMoreThan(10).seconds());
        boolean precioVisible = actorAsksFor(actor, WaitUntil.the(PRECIO_PRODUCTO, isVisible()).forNoMoreThan(10).seconds());
        boolean nombreVisible = actorAsksFor(actor, WaitUntil.the(NOMBRE_PRODUCTO, isVisible()).forNoMoreThan(10).seconds());

        if (productosVisible && botonVisible && precioVisible && nombreVisible) {
            List<WebElementFacade> productosVisibles = PRODUCTOS.resolveAllFor(actor);
            if (productosVisibles.size() >= 5) {
                System.out.println("Hay " + productosVisibles.size() + " productos visibles, lo cual cumple con los requisitos.");
                return true; // Hay al menos 5 productos visibles
            } else {
                System.out.println("Número de productos visibles (" + productosVisibles.size() + ") es insuficiente.");
            }
        } else {
            System.out.println("Algunos elementos necesarios no están visibles.");
        }
        return false; // No se cumplen los requisitos de visibilidad o cantidad

    }

    private boolean actorAsksFor(Actor actor, Interaction interaction) {
        try {
            interaction.performAs(actor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static seleccionarCategoriaSubcategoria go(int maxAttempts) {
        return Tasks.instrumented(seleccionarCategoriaSubcategoria.class, maxAttempts);
    }
}
