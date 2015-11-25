package org.opencompare.customHtmlExporter;

import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
    private File templateFile;
    private String CssFile;
    private String htmlTemplate;
    private Document document;
    private Element body;
    private Element tr;
    private Document.OutputSettings settings;


    public boolean export(PCMContainer container, File json) {
        CssFile = "pcm.css";
        JsonParamsLoader jsonParamsLoader = null;
        templateFile = new File("./src/main/java/org/opencompare/customHtmlExporter/ressources/templateFile.htmlTemplate");
        try {
            jsonParamsLoader = new JsonParamsLoader(json);
            pcmParams = jsonParamsLoader.load();
            htmlTemplate = Files.toString(templateFile, StandardCharsets.UTF_8);
            htmlTemplate = toHTML(container.getPcm());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    //@todo export htmlTemplate
    public String toHTML(PCM pcm) {
        settings.prettyPrint();
        document = Jsoup.parse(htmlTemplate);
        body = document.body();
        document.head().select("title").first().text(pcmParams.getTitle());
        pcm.accept(this);
        return document.outputSettings(settings).outerHtml();
    }

    @Override
    //@todo export htmlTemplate
    public void visit(PCM pcm) {

    }

    @Override
    //@todo export htmlTemplate
    public void visit(Feature feature) {

    }

    @Override
    //@todo export htmlTemplate
    public void visit(Product product) {

    }

    @Override
    //@todo export htmlTemplate
    public void visit(Cell cell) {

    }
}
