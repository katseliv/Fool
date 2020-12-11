package game.serialization;

import com.google.gson.*;
import game.models.Step;
import java.lang.reflect.Type;

public class StepSerializer implements JsonSerializer<Step> {

    @Override
    public JsonElement serialize(Step step, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();

        result.add("Target", context.serialize(step.getTarget()));
        result.add("Step", context.serialize(step.getList()));

        return result;
    }

}
