package game.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import game.deserialization.CardDeserializer;
import game.deserialization.GameDeserializer;
import game.deserialization.PlayerDeserializer;
import game.deserialization.StepDeserializer;
import game.models.Card;
import game.models.GameFool;
import game.models.Player;
import game.models.Step;
import game.serialization.CardSerializer;
import game.serialization.GameSerializer;
import game.serialization.PlayerSerializer;
import game.serialization.StepSerializer;

import java.io.*;

public class FileService {

    public GameFool readFile(File file) {
        String line;
        StringBuilder result = new StringBuilder();

        try (FileReader reader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            bufferedReader.close();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

        Gson gson1 = new GsonBuilder()
                .registerTypeAdapter(GameFool.class, new GameDeserializer())
                .registerTypeAdapter(Player.class, new PlayerDeserializer())
                .registerTypeAdapter(Card.class, new CardDeserializer())
                .registerTypeAdapter(Step.class, new StepDeserializer())
                .create();

        return gson1.fromJson(result.toString(), GameFool.class);
    }

    public void writeFile(GameFool gameFool, File file) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(GameFool.class, new GameSerializer())
                .registerTypeAdapter(Player.class, new PlayerSerializer())
                .registerTypeAdapter(Card.class, new CardSerializer())
                .registerTypeAdapter(Step.class, new StepSerializer())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();
        String text = gson.toJson(gameFool);

        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
