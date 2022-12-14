package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.CurrentVisibility;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static userinterface.DasboardMenuPage.*;
import static userinterface.MeetPage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;

public class ValidationMeet implements Task {

    public static ValidationMeet toMeeting (){return Tasks.instrumented(ValidationMeet.class);}

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Check.whether(actor.asksFor(CurrentVisibility.of(BUTTON_LINES))
                                && !actor.asksFor(CurrentVisibility.of(MEETINGS_LINK)))
                        .andIfSo(Click.on(BUTTON_LINES))
        );

        actor.attemptsTo(
                WaitUntil.the(MEETINGS_LINK, isCurrentlyVisible())
                        .forNoMoreThan(60).seconds(),
                Click.on(MEETINGS_LINK),
                Click.on(MEETINGS_PAGE)
        );

    }

}
