package bo.com.test.prueba.tecnica.tasks;

import bo.com.test.prueba.tecnica.interactions.APIInteractionBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import org.apache.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.http.ContentType.MULTIPART;


public class ExecuteAPIRequest implements Task {
    private final String method;
    private final String endpoint;
    private final Map<String, String> headers;
    private final String body;
    private final String fileURL;

    public ExecuteAPIRequest(String method, String endpoint, Map<String, String> headers, String body, String fileURL) {
        this.method = method;
        this.endpoint = endpoint;
        this.headers = headers;
        this.body = body;
        this.fileURL = fileURL;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        RestInteraction interaction = APIInteractionBuilder.buildInteraction(method, endpoint, headers, body);

        if (fileURL != null && !fileURL.isEmpty()) {
            MultiPartSpecification fileSpec = new MultiPartSpecBuilder(new File(fileURL))
                    .controlName("file") // This is the name of the form-field to which the file will be attached in the request.
                    .fileName(new File(fileURL).getName())
                    .mimeType("image/jpg")
                    .build();
            interaction = interaction.with(request -> request
                    .contentType(MULTIPART)
                    .multiPart(fileSpec));

            if (!body.isEmpty()) {
                interaction = interaction.with(request -> request
                        .formParam("jsonBody", body)); // Envía otros datos como parámetros de formulario si es necesario
            }
        }

        actor.attemptsTo(interaction);

        if (SerenityRest.lastResponse().statusCode() < HttpStatus.SC_OK &&
                SerenityRest.lastResponse().statusCode() > 299) {
            throw new RuntimeException("HTTP Error: Unexpected response status code " + SerenityRest.lastResponse().statusCode());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode responseJson = mapper.readTree(SerenityRest.lastResponse().asString());
            actor.remember("lastApiResponse", responseJson);
        } catch (Exception e) {
            //throw new RuntimeException("Error processing JSON response", e);
            byte[] fileData = SerenityRest.lastResponse().asByteArray();
            Path path = Paths.get("src/test/resources/jpg/Yey.jpg");
            try {
                Files.write(path, fileData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            actor.remember("downloadedFilePath", path.toString());
        }
    }

    public static ExecuteAPIRequest withDetails(String method, String endpoint, Map<String, String> headers, String body, String fileURL) {
        return Tasks.instrumented(ExecuteAPIRequest.class, method, endpoint, headers, body, fileURL);
    }
}
