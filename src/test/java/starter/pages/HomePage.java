package starter.pages;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class HomePage {
    public static Performable serenityHomePage() {
        return Task.where("{0} is on the Serenity home page",
                Open.browserOn().the(SerenityWebPage.class));
    }
    public static Performable serenityLoginPage() {
        return Task.where("{0} is on the Serenity home page",
                Open.browserOn().the(SerenityLoginPage.class));
    }
}

