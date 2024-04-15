package bo.com.test.prueba.tecnica.interactions;

import net.serenitybdd.screenplay.rest.interactions.*;

import java.util.Map;

public class APIInteractionBuilder {

    public static RestInteraction buildInteraction(String method, String endpoint, Map<String, String> headers, String body) {
        switch (method.toUpperCase()) {
            case "POST":
                return Post.to(endpoint).with(request -> request.headers(headers).body(body));
            case "GET":
                return Get.resource(endpoint).with(request -> request.headers(headers));
            case "PUT":
                return Put.to(endpoint).with(request -> request.headers(headers).body(body));
            case "DELETE":
                return Delete.from(endpoint).with(request -> request.headers(headers));
            case "PATCH":
                return Patch.to(endpoint).with(request -> request.headers(headers).body(body));
            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }
}
