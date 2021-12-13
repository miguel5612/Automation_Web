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
import starter.pages.Register;
import starter.validations.alertList;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegisterFormStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^(.*) is on the automationpractice register page")
    public void on_the_automationpractice_homepage(String actor) {
        theActorCalled(actor).attemptsTo(HomePage.automationpracticeHomePage());
        withCurrentActor(
                Register.pressBtn()
        );
    }

    @When("He wrotes his personal data")
    public void writingEmailOnForm() {
        String email =  Math.random() + "@gmail.com";
        withCurrentActor(
                Register.writeEmail(email)
        );
    }

    @Then("He press OK")
    public void validatingMailWrited() {
        theActorInTheSpotlight().should(
                seeThat("testing register form viewed",
                        alertList.getList(), is(not(empty()))));
    }
}
