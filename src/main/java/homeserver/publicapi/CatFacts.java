package homeserver.publicapi;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
@RestController
public class CatFacts {
    private static final String url = "https://catfact.ninja/fact";
    private HttpClient client = HttpClient.newHttpClient();
    @GetMapping
    public String getResult() throws IOException, InterruptedException {
        System.out.println("getResult called!");
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    JSONObject jsonpObject = new JSONObject(response.body());
        System.out.println(jsonpObject);
    return jsonpObject.toString();
    }
}
