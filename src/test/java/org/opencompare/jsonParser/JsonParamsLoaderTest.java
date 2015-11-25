package org.opencompare.jsonParser;

import org.junit.Test;

import java.io.File;

/**
 * Created by mahdi on 19/11/15.
 */
public class JsonParamsLoaderTest {

    @Test
    public void testLoad() throws Exception {
        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(new File("./src/test/java/org/opencompare/jsonParser/resources/example.json"));
        PcmParams pcmParams = jsonParamsLoader.load();
        System.out.println(pcmParams.getTitle());
        System.out.println(pcmParams.getDescription());
        System.out.println(pcmParams.isInvert());
        System.out.println(pcmParams.getStyle());
        System.out.println(pcmParams.getFeatures().getStyle());
        System.out.println(pcmParams.getFeatures().hasStyle());
        System.out.println(pcmParams.getFeatures().getElement(0).getStyle());
        System.out.println(pcmParams.getFeatures().getElement(0).getCell(0).getStyle());
        System.out.println(pcmParams.hasOperations());
        System.out.println(pcmParams.getOperation(0).execute(10));
        System.out.println(pcmParams.getOperation(1).execute(9.9));
        System.out.println(pcmParams.getOperation(2).execute("test"));
    }

}