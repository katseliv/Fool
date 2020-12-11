package game.serialization;

import com.google.gson.*;
import game.models.Card;

import java.lang.reflect.Type;

public class CardSerializer implements JsonSerializer<Card> {

    @Override
    public JsonElement serialize(Card card, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("Rank", context.serialize(card.getRank()));
        jsonObject.add("Suit", context.serialize(card.getSuit()));

        return jsonObject;
    }

}
