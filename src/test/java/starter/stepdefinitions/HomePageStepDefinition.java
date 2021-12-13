package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.Question;

import starter.pages.HomePage;
import starter.validations.load;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class HomePageStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^(.*) is on the automationpractice home page")
    public void on_the_automationpractice_homepage(String actor) {
        theActorCalled(actor).attemptsTo(HomePage.automationpracticeHomePage());
    }

    @When("He wait to Load")
    public void search_for() {
        /*
        withCurrentActor(
                SearchFor.term(term)
        );
        */
    }

    @Then("He shows the HTML\\/CSS\\/JS Page")
    public void he_shows_the_html_css_js_page() {
            theActorInTheSpotlight().should(
                    seeThat("testing website viewed",
                            load.listContainers(), notNullValue()));
      }
}
