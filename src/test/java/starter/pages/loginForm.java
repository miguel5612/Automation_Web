package starter.pages;

import org.openqa.selenium.By;

public class loginForm {
    // Este es el boton quea me lleva desde la pagina principal hacia el modulo de registro
    public static By GOLogin_Button = By.xpath("//*[@id=\"s-Page\"]/body/div/section[1]/div[2]/div/div/div[1]/div/div[2]/a[1]");
    public static By Email_TB = By.id("LoginPanel0_Username");
    public static By PW_TB = By.id("LoginPanel0_Password");
    public static By BTN_Login = By.id("LoginPanel0_LoginButton");
}

