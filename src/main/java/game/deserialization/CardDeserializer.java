package game.deserialization;

import com.google.gson.*;
import game.enums.CardSuit;
import game.enums.RankOfCards;
import game.models.Card;
import java.lang.reflect.Type;

public class CardDeserializer implements JsonDeserializer<Card> {
    @Override
    public Card deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Card card = new Card();

        card.setRank(context.deserialize(jsonObject.get("Rank"), RankOfCards.class));
        card.setSuit(context.deserialize(jsonObject.get("Suit"), CardSuit.class));

        return card;
    }
}
