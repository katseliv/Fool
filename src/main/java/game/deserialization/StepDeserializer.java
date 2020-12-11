package game.deserialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import game.models.Card;
import game.models.Player;
import game.models.Step;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class StepDeserializer implements JsonDeserializer<Step> {

    @Override
    public Step deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Step step = new Step();

        step.setTarget(context.deserialize(jsonObject.get("Target"), Player.class));

        Type typeToken = new TypeToken<Map<Player, HashMap<Card, Card>>>() {
        }.getType();
        step.setList(context.deserialize(jsonObject.get("Step"), typeToken));

        return step;
    }

}
