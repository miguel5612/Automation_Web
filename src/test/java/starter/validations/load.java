package starter.validations;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;

import java.util.List;

public class load {
        public static Question<List<String>> listContainers() {
        return actor -> TextContent.of(containerElements.Containers).viewedBy(actor).asList();
    }
}