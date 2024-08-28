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
        JSONObject jsonObject = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonObject = new JSONObject(response.body());
            System.out.println(jsonObject);

        } catch (Exception e) {
            System.out.println("Something went wring with your request" + e);
        }
        if (jsonObject != null){
        return jsonObject.toString();
        } else {
            return "The cat fact was empty! Please check to make sure the connection is working";
        }
    }
}
