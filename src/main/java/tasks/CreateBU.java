package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static userinterface.DasboardMenuPage.*;

public class CreateBU implements Task {
    private String data;

    public CreateBU(String data){this.data=data;}

    public static CreateBU create (String data){return Tasks.instrumented(CreateBU.class,data);}


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(NEW_UNIT_BUTTON),
                Enter.theValue(data).into(NAME_INPUT),
                Click.on(CREATE_BUTTON),
                Click.on(Refresh_UNIT_BUTTON)
        );
    }
}
