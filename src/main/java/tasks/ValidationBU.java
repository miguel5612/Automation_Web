package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.CurrentVisibility;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static userinterface.DasboardMenuPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;

public class ValidationBU implements Task {

    private String objetive;


    public ValidationBU(String objetive){this.objetive=objetive;}

    public static ValidationBU toBussines (String objetive){return Tasks.instrumented(ValidationBU.class,objetive);}
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Check.whether(actor.asksFor(CurrentVisibility.of(BUTTON_LINES))
                                && !actor.asksFor(CurrentVisibility.of(ORGANIZATION_MENU)))
                        .andIfSo(Click.on(BUTTON_LINES))
        );
        Check.whether(actor.asksFor (CurrentVisibility.of(ORGANIZATION_MENU))
                )
                .andIfSo(
                        Click.on(ORGANIZATION_MENU),
                        WaitUntil.the(ORGANIZATION_MENU, isCurrentlyVisible())
                                .forNoMoreThan(30).seconds(),
                        Click.on(UNITS_LINK)
                )
                .performAs(actor);


    }
    }

