package bo.com.test.prueba.tecnica.stepdefinitions.api;

import bo.com.test.prueba.tecnica.tasks.ExecuteAPIRequest;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Before;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class APIStepDefinitions {
    @Before
    public void setup() {
    }

    @Given("el usuario {string} realiza la peticion {string}")
    public void el_usuario_realiza_la_peticion(String usuario, String nombrePeticion) {
        String baseUrl = "http://localhost:5002";
        Actor miguel = OnStage.theActorCalled(usuario);
        miguel.can(CallAnApi.at(baseUrl));
    }

    @When("llama al endpoint correspondiente el actor {string}")
    public void llama_al_endpoint_correspondiente(String actorNombre, DataTable dataTable) {
        List<Map<String, String>> detailsList = dataTable.asMaps();
        for (Map<String, String> details : detailsList) {
            String method = details.get("Metodo");
            String url = details.get("EndPoint");
            String headersString = details.get("Headers").equals("[empty]") ? "" : details.get("Headers");
            String body = Optional.ofNullable(details.get("Body")).orElse("");
            String fileURL = Optional.ofNullable(details.get("FileURL")).orElse(null);


            Map<String, String> headers = new HashMap<>();
            if (headersString != null && !headersString.isEmpty()) {
                for (String header : headersString.split(";")) {
                    String[] headerParts = header.split(":");
                    if (headerParts.length == 2) {
                        headers.put(headerParts[0].trim(), headerParts[1].trim());
                    }
                }
            }
            Actor actor = OnStage.theActorCalled(actorNombre);

            actor.attemptsTo(
                    ExecuteAPIRequest.withDetails(method, url, headers, body, fileURL)
            );
        }
    }

    @Then("deberia {string} recibir una respuesta acorde a los parametros preestablecidos")
    public void deberia_recibir_una_respuesta_acorde_a_los_parametros_preestablecidos(String actorNombre, DataTable dataTable) {
        List<Map<String, String>> detailsList = dataTable.asMaps();
        for (Map<String, String> details : detailsList) {
            String pathParams = details.get("PathParams");
            String expectedResponses = details.get("RespuestasEsperadas");
            Actor actor = OnStage.theActorCalled(actorNombre);
            JsonNode responseJson = actor.recall("lastApiResponse");

            if (pathParams == null && expectedResponses  == null) {
                // Asumimos que es una descarga de archivo
                String downloadedFilePath = actor.recall("downloadedFilePath");
                File file = new File(downloadedFilePath);
                assertThat(file.exists()).isTrue();  // Verificar que el archivo existe
            } else {
                String[] pathParamsArray = pathParams.split("#");
                String[] expectedResponsesArray = expectedResponses.split("#");
                for (int i = 0; i < pathParamsArray.length; i++) {
                    String currentPath = pathParamsArray[i];
                    String expectedValue = expectedResponsesArray[i];
                    JsonNode actualValue = responseJson.at(convertToJSONPointer(currentPath));

                    // Use a method like `asText()` or `toString()` based on the type of the actualValue
                    assertThat(actualValue.asText()).isEqualTo(expectedValue);
                }
            }
        }
    }

    private String convertToJSONPointer(String path) {
        return "/" + path.replace("#", "/");
    }
}
