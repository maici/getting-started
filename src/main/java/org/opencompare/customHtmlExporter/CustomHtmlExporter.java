package org.opencompare.customHtmlExporter;

import org.opencompare.api.java.*;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.cssGenerator.PcmCssBuilder;
import org.opencompare.jsonParser.JsonParamsLoader;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;

/**
 * Created by mahdi on 23/11/15.
 */
public class CustomHtmlExporter extends HTMLExporter {

    private PcmParams pcmParams;
    private PcmCssBuilder pcmCssBuilder = new PcmCssBuilder();
    private String CssFile;

    public boolean export(PCMContainer container, File json) {
        CssFile = "pcm.css";
        JsonParamsLoader jsonParamsLoader = null;
        try {
            jsonParamsLoader = new JsonParamsLoader(json);
            pcmParams = jsonParamsLoader.load();
            toHTML(container.getPcm());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    //@todo export html
    public String toHTML(PCM pcm) {

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
