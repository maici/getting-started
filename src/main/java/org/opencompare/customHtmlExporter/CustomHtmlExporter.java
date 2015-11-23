package org.opencompare.customHtmlExporter;

import com.google.common.io.Files;
import org.opencompare.api.java.*;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.cssGenerator.PcmCssBuilder;
import org.opencompare.jsonParser.JsonParamsLoader;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Created by mahdi on 23/11/15.
 */
public class CustomHtmlExporter extends HTMLExporter {

    private PcmParams pcmParams;
    private PcmCssBuilder pcmCssBuilder = new PcmCssBuilder();
    private String CssFile;
    private File template;
    private String html;

    public boolean export(PCMContainer container, File json) {
        CssFile = "pcm.css";
        JsonParamsLoader jsonParamsLoader = null;
        template = new File("./src/main/java/org/opencompare/customHtmlExporter/ressources/template.html");
        try {
            jsonParamsLoader = new JsonParamsLoader(json);
            pcmParams = jsonParamsLoader.load();
            html = Files.toString(template, StandardCharsets.UTF_8);
            html = toHTML(container.getPcm());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    //@todo export html
    public String toHTML(PCM pcm) {
        return null;
    }

    @Override
    //@todo export html
    public void visit(PCM pcm) {

    }

    @Override
    //@todo export html
    public void visit(Feature feature) {

    }

    @Override
    //@todo export html
    public void visit(Product product) {

    }

    @Override
    //@todo export html
    public void visit(Cell cell) {

    }
}
