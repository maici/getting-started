package org.opencompare.jsonParser;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Created by mahdi on 05/11/15.
 */
public class JSONDeserializer<T> implements JsonDeserializer<T> {

    private String name;

    public JSONDeserializer(String name) {
        this.name = name;
    }

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Gson().fromJson(jsonElement.getAsJsonObject().get(name), type);
    }
}
