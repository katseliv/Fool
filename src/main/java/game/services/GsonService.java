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

public class GsonService {

    public String serialize(GameFool gameFool){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(GameFool.class, new GameSerializer())
                .registerTypeAdapter(Player.class, new PlayerSerializer())
                .registerTypeAdapter(Card.class, new CardSerializer())
                .registerTypeAdapter(Step.class, new StepSerializer())
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .create();
        return gson.toJson(gameFool);
    }

    public GameFool deserialize(String json){
        Gson gson1 = new GsonBuilder()
                .registerTypeAdapter(GameFool.class, new GameDeserializer())
                .registerTypeAdapter(Player.class, new PlayerDeserializer())
                .registerTypeAdapter(Card.class, new CardDeserializer())
                .registerTypeAdapter(Step.class, new StepDeserializer())
                .create();
        return gson1.fromJson(json, GameFool.class);
    }
}
