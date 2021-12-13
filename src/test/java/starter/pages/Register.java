package starter.pages;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class Register {
    public static Performable pressBtn() {
        return Task.where("{0} is on the automationpractice register page",
                //Clear.field(SearchForm.SEARCH_FIELD),
                //Enter.theValue(term).into(SearchForm.SEARCH_FIELD),
                Click.on(RegisterForm.Register_Button)
        );
    }
    public static Performable writeEmail(String email) {
        return Task.where("{0} He wrotes his personal data",
                Clear.field(RegisterForm.RegisterEmail_TB),
                Enter.theValue(email).into(RegisterForm.RegisterEmail_TB),
                Click.on(RegisterForm.Register_Email_Btn)
        ).with("email").of(email);
    }
}