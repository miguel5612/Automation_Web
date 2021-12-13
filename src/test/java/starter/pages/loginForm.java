package starter.pages;

import org.openqa.selenium.By;

public class loginForm {
    // Este es el boton quea me lleva desde la pagina principal hacia el modulo de registro
    public static By GOLogin_Button = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
    public static By Email_TB = By.id("email");
    public static By PW_TB = By.id("passwd");
    public static By BTN_Login = By.id("SubmitLogin");
}

