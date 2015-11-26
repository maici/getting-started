package org.opencompare.customHtmlExporter;

import org.junit.Test;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.impl.io.KMFJSONLoader;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.api.java.io.PCMLoader;
import org.opencompare.jsonParser.JsonParamsLoader;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;
import java.io.PrintWriter;

/**
 * Created by mahdi on 25/11/15.
 */
public class customHtmlExporterTest {

    @Test
    public void testExport() throws Exception {
        File pcmFile = new File("pcms/example.pcm");
        File jsonFile = new File("params/example.json");

        PCMLoader loader = new KMFJSONLoader();
        PCMContainer pcmContainer = loader.load(pcmFile).get(0);

        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(jsonFile);
        PcmParams pcmParams = jsonParamsLoader.load();
        System.out.println(pcmParams.getTitle());

        CustomHtmlExporter exporter = new CustomHtmlExporter();
        exporter.export(pcmContainer, pcmParams);

        HTMLExporter exporterOpencompare = new HTMLExporter();
        PrintWriter printWriter = new PrintWriter("./out/pcm_oc.html");
        printWriter.write(exporterOpencompare.export(loader.load(pcmFile).get(0)));
    }
}
