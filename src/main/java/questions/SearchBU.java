package questions;

import static userinterface.DasboardMenuPage.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class SearchBU implements Question<Boolean> {

    private String item;

    public SearchBU (String item){this.item = item;}
    public static SearchBU theItemExist(String item){return new SearchBU(item);}

    @Override
    public Boolean answeredBy(Actor actor) {
        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(item).into(INPUT_SEARCH_NAME).thenHit(Keys.ENTER)
        );
        return LABEL_ELEMENT_NAME.of(item).isVisibleFor(actor);
    }
}
