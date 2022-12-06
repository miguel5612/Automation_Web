package starter.pages;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

public class login {
    public static Performable pressBtnGoToLogin() {
        return Task.where("{0} is on the serenity Login page",
                Click.on(loginForm.GOLogin_Button)
        );
    }
    public static Performable writeCredentials(String email, String password) {
        return Task.where("{0} He wrotes his personal data",
                Clear.field(loginForm.Email_TB),
                Clear.field(loginForm.PW_TB),
                Enter.theValue(email).into(loginForm.Email_TB),
                Enter.theValue(password).into(loginForm.PW_TB),
                Click.on(loginForm.BTN_Login)
        ).with("email").of(email);
    }
}