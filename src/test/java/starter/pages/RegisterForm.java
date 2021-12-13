package starter.pages;

import org.openqa.selenium.By;

public class RegisterForm {
    // Este es el boton que me lleva desde la pagina principal hacia el modulo de registro
    public static By Register_Button = By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a");
    public static By RegisterEmail_TB = By.xpath("/html/body/div[2]/div[2]/div/div[3]/div/div/div[1]/form/div/div[2]/input");
    public static By Register_Email_Btn = By.xpath("//*[@id=\"SubmitCreate\"]");
}