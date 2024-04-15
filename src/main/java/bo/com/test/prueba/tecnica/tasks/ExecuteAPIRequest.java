package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.interactions.APIInteractionBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import org.apache.http.HttpStatus;

import java.util.Map;


public class ExecuteAPIRequest implements Task {
    private final String method;
    private final String endpoint;
    private final Map<String, String> headers;
    private final String body;

    public ExecuteAPIRequest(String method, String endpoint, Map<String, String> headers, String body) {
        this.method = method;
        this.endpoint = endpoint;
        this.headers = headers;
        this.body = body;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        RestInteraction interaction = APIInteractionBuilder.buildInteraction(method, endpoint, headers, body);
        actor.attemptsTo(interaction);

        if (SerenityRest.lastResponse().statusCode() != HttpStatus.SC_OK) {
            throw new RuntimeException("HTTP Error: Unexpected response status code " + SerenityRest.lastResponse().statusCode());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJson = mapper.readTree(SerenityRest.lastResponse().asString());
            actor.remember("lastApiResponse", responseJson);
        } catch (Exception e) {
            throw new RuntimeException("Error processing JSON response", e);
        }
    }

    public static ExecuteAPIRequest withDetails(String method, String endpoint, Map<String, String> headers, String body) {
        return Tasks.instrumented(ExecuteAPIRequest.class, method, endpoint, headers, body);
    }
}
