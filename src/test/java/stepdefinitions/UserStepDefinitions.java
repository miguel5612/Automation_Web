package stepdefinitions;

import io.cucumber.java.en.And;
import questions.SearchBU;
import tasks.CreateBU;
import tasks.Login;
import tasks.Openup;
import tasks.ValidationBU;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;


public class UserStepDefinitions {
@Before
public void setStage(){  OnStage.setTheStage (new OnlineCast());}

    @Given("that the user is on the client page")
    public void that_the_user_is_on_the_star_sharp_page() {
    theActorCalled("user").wasAbleTo(Openup.thepage());
    }



    @Given("the user Logs in")
    public void the_user_logs_in(io.cucumber.datatable.DataTable data) {
        List<Map<String,String>> datalogin= data.asMaps(String.class,String.class);
        theActorInTheSpotlight().attemptsTo(Login.onThePage(datalogin.get(0).get("user"),datalogin.get(0).get("password")));
    }

    @Given("he go to the Bussines Units")
    public void he_go_to_the_bussines_units() {
        theActorInTheSpotlight().attemptsTo(ValidationBU.toBussines("unit"));

            }

    @When("^he create a new unit (.*)$")
    public void he_create_a_new_unit(String data) {
         theActorInTheSpotlight().attemptsTo(CreateBU.create(data));
    }

    @Then("^the (.*) should display in the menu$")
    public void the_UnidadWill_should_display_in_the_menu(String data) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(SearchBU.theItemExist(data)));
    }


    @Given("que el usuario está en la página principal de (.+)")
    public void que_el_usuario_esta_en_la_pagina_principal_de_tiendas_exito(String pagina) {
        //driver.get("https://www.exito.com/");
        //wait = new WebDriverWait(driver, 10);
        int a = 1 + 1;
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