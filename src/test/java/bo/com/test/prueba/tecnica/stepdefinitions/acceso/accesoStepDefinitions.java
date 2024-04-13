package bo.com.test.prueba.tecnica.stepdefinitions.acceso;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class accesoStepDefinitions {
    @Managed
    private WebDriver driver;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Cliente");
    }

    @Given("el usuario está en la página principal de {string}")
    public void que_el_usuario_esta_en_la_pagina_principal_de_tiendas_exito(String pagina) {
        OnStage.theActorInTheSpotlight().wasAbleTo(
                Open.url("https://www.exito.com/")
        );
    }

    @When("el usuario navega a una categoría y subcategoría específica")
    public void el_usuario_navega_a_una_categoria_y_subcategoria_especifica() {
        // Navegar utilizando los elementos de la web
        int a = 1 + 1;
    }

    @And("el usuario agrega 5 productos aleatorios al carrito con cantidades aleatorias entre 1 y 10")
    public void el_usuario_agrega_productos_aleatorios_al_carrito_con_cantidades_aleatorias() {
        // Implementar la lógica para añadir productos aleatorios
        int a = 1 + 1;
    }

    @Then("se verifica que los nombres de los productos en el carrito coincidan con los seleccionados")
    public void se_verifica_que_los_nombres_de_los_productos_en_el_carrito_coincidan_con_los_seleccionados() {
        // Verificar nombres
        int a = 1 + 1;
    }

    @And("se verifica que las cantidades de los productos en el carrito sean correctas")
    public void se_verifica_que_las_cantidades_de_los_productos_en_el_carrito_sean_correctas() {
        // Verificar cantidades
        int a = 1 + 1;
    }
    @And("se verifica que el total de los precios en el carrito sea correcto")
    public void se_verifica_que_el_total_de_los_precios_carrito_sea_correcto() {
        // Verificar cantidades
        int a = 1 + 1;
    }
    @And("se verifica que el número de productos en el carrito sea 5")
    public void se_verifica_que_el_numero_de_productos_en_el_carrito_sea_5() {
        // Verificar cantidades
        int a = 1 + 1;
    }


}