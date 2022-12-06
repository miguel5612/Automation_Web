package starter.validations;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;
import org.openqa.selenium.WebElement;

import java.util.List;

public class load {
        public static Question<List<String>> listContainers() {
        return actor -> TextContent.of(containerElements.Containers).viewedBy(actor).asList();
    }
    /*
        public static Question<List<WebElement>> listLinks() {
            return actor -> getDriver().findElements(links.Link).viewedBy(actor);
        }
     */
}