package hu.nlp;

import com.google.gson.Gson;
import hu.nlp.api.HuNlp;

import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();
        HuNlp nlp = new HuNlp();

        threadPool(12);
        port(9090);

        post("/v1/parse", "application/json", (request, response) -> {
            Map<String, String> inputText = gson.fromJson(request.body(), Map.class);
            return nlp.parse(inputText.get("text"));
        }, gson::toJson);
    }


}