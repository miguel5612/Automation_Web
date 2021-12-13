package starter.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.ensure.Ensure;

import starter.pages.HomePage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.core.Every.everyItem;

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

    @Then("He shows the HTML/CSS/JS Page")
    public void viewHTMLPage() {
        //HomePage.getTittleOfWebsite();
        /*
         theActorInTheSpotlight().should(
                seeThat("search result titles",
                        SearchResult.titles(), everyItem(containsIgnoringCase(term)))
        );
        */

    }
}
