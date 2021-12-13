package starter.validations;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;

import java.util.List;

public class alertList {
    public static Question<List<String>> getList() {
        return actor -> TextContent.of(containerElements.Alerts).viewedBy(actor).asList();
    }
}