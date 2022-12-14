package stepdefinitions;

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

    }