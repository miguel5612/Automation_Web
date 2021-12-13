package starter.pages;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class HomePage {
    public static Performable automationpracticeHomePage() {
        return Task.where("{0} is on the automationpractice home page",
                Open.browserOn().the(AutomationPracticePage.class));
    }
}