package org.opencompare.jsonParser;

import com.google.gson.GsonBuilder;

import java.io.File;

/**
 * Json parameters loader
 * @todo tests
 */
public class JsonParamsLoader {

    private File json;
    private GsonBuilder gsonBuilder;

    public JsonParamsLoader(File json) {
        this.json = json;
        this.gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(PcmParams.class, new JSONParamsDeserializer<>("pcm"));
        gsonBuilder.registerTypeAdapter(ElementsParams.class, new JSONParamsDeserializer<>("features"));
        gsonBuilder.registerTypeAdapter(ElementsParams.class, new JSONParamsDeserializer<>("products"));
        gsonBuilder.registerTypeAdapter(ElementParams.class, new JSONParamsDeserializer<>("feature"));
        gsonBuilder.registerTypeAdapter(ElementParams.class, new JSONParamsDeserializer<>("product"));
        gsonBuilder.registerTypeAdapter(Comparison.class, new JSONParamsDeserializer<>("comparison"));
        gsonBuilder.registerTypeAdapter(Range.class, new JSONParamsDeserializer<>("range"));
        gsonBuilder.registerTypeAdapter(Style.class, new JSONParamsDeserializer<>("style"));
    }


    public PcmParams load(){
        //@todo lecture du fichier json
        return new PcmParams();
    }
}
