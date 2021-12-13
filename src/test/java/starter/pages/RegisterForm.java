package starter.pages;

import org.openqa.selenium.By;

public class RegisterForm {
    // Este es el boton quea me lleva desde la pagina principal hacia el modulo de registro
    public static By Register_Button = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
    public static By RegisterEmail_TB = By.id("email_create");
    public static By Register_Email_Btn = By.id("SubmitCreate");
}