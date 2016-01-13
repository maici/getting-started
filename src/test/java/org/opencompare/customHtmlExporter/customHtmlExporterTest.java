package org.opencompare.customHtmlExporter;

import org.junit.Test;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.jsonParser.JsonParamsLoader;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;

/**
 * Created by mahdi on 25/11/15.
 */
public class customHtmlExporterTest {


    @Test
    public void testExport() throws Exception {
        File pcmFile = new File("/home/mahdi/Bureau/demo/ide_java.pcm");
        File jsonFile = new File("/home/mahdi/Bureau/demo/ide_java_params.json");

        PCMLoader loader = new KMFJSONLoader();
        PCMContainer pcmContainer = loader.load(pcmFile).get(0);

        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(jsonFile);
        PcmParams pcmParams = jsonParamsLoader.load();

        CustomHtmlExporter exporter = new CustomHtmlExporter();
        exporter.export(pcmContainer, pcmParams, "/home/mahdi/Bureau/demo/html");
    }
}
