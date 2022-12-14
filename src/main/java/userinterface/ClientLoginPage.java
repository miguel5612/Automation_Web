package userinterface;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;


@DefaultUrl("https://demo.serenity.is/Account/Login/?ReturnUrl=%2F")
    public class ClientLoginPage extends PageObject {

    public static final Target USER_NAME = Target.the("User name").located(By.id("LoginPanel0_Username"));
    public static final Target PASSWORD = Target.the("Password").located(By.id("LoginPanel0_Password"));
    public static final Target BTN_SINGIN = Target.the("Boton sing in").located(By.id("LoginPanel0_LoginButton"));


    }

