package org.opencompare.jsonParser;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Json parameters loader
 * @todo tests
 */
public class JsonParamsLoader {

    private String json;
    private GsonBuilder gsonBuilder;

    /**
     * Constructor
     * @param jsonFile File
     * @throws IOException
     */
    public JsonParamsLoader(File jsonFile) throws IOException {
        this.json = new String(Files.readAllBytes(jsonFile.toPath()), StandardCharsets.UTF_8);
        this.gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(PcmParams.class, new JsonParamsDeserializer<>("pcm"));
    }

    /**
     * Read the json file and create the PcmParams Object
     * @return PcmParams
     * @throws Exception
     */
    public PcmParams load() throws Exception {
        try {
            return gsonBuilder.create().fromJson(this.json, PcmParams.class);
        }
        catch (Exception e) {
            throw new Exception("Incorrect json PCM parameters file");
        }
    }
}
