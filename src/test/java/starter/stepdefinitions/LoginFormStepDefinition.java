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
import starter.pages.login;
import starter.validations.alertList;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LoginFormStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("^(.*) is on the automationpractice Login page")
    public void on_the_automationpractice_loginpage(String actor) {
        theActorCalled(actor).attemptsTo(HomePage.automationpracticeHomePage());
        withCurrentActor(
                login.pressBtnGoToLogin()
        );
    }

    @When("He wrotes his personal data \\(Email and Password)")
    public void loginLoadData() {
        withCurrentActor(
                login.writeCredentials("miguelangel5612@gmail.com", "abcd1234")
        );
    }

    @Then("He press OK and verify is on the dashboard")
    public void loginValidation() {
        theActorInTheSpotlight().should(
                seeThat("testing dashboard",
                        alertList.getList(), is(empty())));
    }
}
