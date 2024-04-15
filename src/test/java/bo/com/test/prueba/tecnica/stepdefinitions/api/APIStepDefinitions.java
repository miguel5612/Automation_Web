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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class APIStepDefinitions {

    @Given("el usuario {string} realiza la peticion {string}")
    public void el_usuario_realiza_la_peticion(String usuario, String nombrePeticion) {
        OnStage.setTheStage(new OnlineCast());
        theActorCalled(usuario);
    }

    @When("llama al endpoint correspondiente")
    public void llama_al_endpoint_correspondiente(DataTable dataTable) {
        List<Map<String, String>> detailsList = dataTable.asMaps();
        for (Map<String, String> details : detailsList) {
            String method = details.get("metodo");
            String url = details.get("URL");
            String headersString = details.get("Headers").equals("[empty]") ? "" : details.get("Headers");
            String body = Optional.ofNullable(details.get("Body")).orElse("");
            String fileURL = Optional.ofNullable(details.get("fileURL")).orElse(null);


            Map<String, String> headers = new HashMap<>();
            if (headersString != null && !headersString.isEmpty()) {
                for (String header : headersString.split(";")) {
                    String[] headerParts = header.split(":");
                    if (headerParts.length == 2) {
                        headers.put(headerParts[0].trim(), headerParts[1].trim());
                    }
                }
            }
            OnStage.theActorInTheSpotlight().attemptsTo(
                    ExecuteAPIRequest.withDetails(method, url, headers, body)
            );
        }
    }

    @Then("deberia recibir una respuesta acorde a los parametros preestablecidos")
    public void deberia_recibir_una_respuesta_acorde_a_los_parametros_preestablecidos(Map<String, String> verificationData) {
        Actor actor = OnStage.theActorInTheSpotlight();
        JsonNode responseJson = actor.recall("lastApiResponse");

        verificationData.forEach((key, value) -> {
            String[] paths = key.split("#");
            String[] expectedValues = value.split("#");

            for (int i = 0; i < paths.length; i++) {
                JsonNode actualValue = responseJson.at(convertToJSONPointer(paths[i]));
                assertThat(actualValue.asText()).isEqualTo(expectedValues[i]);
            }
        });
    }

    private String convertToJSONPointer(String path) {
        return "/" + path.replace("#", "/");
    }
}
