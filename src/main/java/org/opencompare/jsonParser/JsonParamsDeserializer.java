package org.opencompare.jsonParser;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Deserialize json parameters objects
 * @todo tests
 */
public class JsonParamsDeserializer<T> implements JsonDeserializer<T> {

    private String name;

    /**
     * Constructor
     * @param name String json object name
     */
    public JsonParamsDeserializer(String name) {
        this.name = name;
    }

    /**
     * deserialize json to object type
     * @param jsonElement JsonElement
     * @param type Type
     * @param jsonDeserializationContext JsonDeserializationContext
     * @return Gson object
     * @throws JsonParseException
     */
    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Gson().fromJson(jsonElement.getAsJsonObject().get(name), type);
    }
}
