package org.opencompare.customHtmlExporter;

import org.junit.Test;
import org.opencompare.api.java.PCM;
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
        File pcmFile = new File("pcms/example.pcm");
        File jsonFile = new File("params/example.json");

        PCMLoader loader = new KMFJSONLoader();
        PCM pcm = loader.load(pcmFile).get(0).getPcm();

        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(jsonFile);
        PcmParams pcmParams = jsonParamsLoader.load();

        CustomHtmlExporter exporter = new CustomHtmlExporter();
        exporter.export(pcm, pcmParams);
    }
}
