package org.opencompare.customHtmlExporter;

import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.opencompare.api.java.Cell;
import org.opencompare.api.java.Feature;
import org.opencompare.api.java.PCM;
import org.opencompare.api.java.Product;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.cssGenerator.PcmCssBuilder;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by mahdi on 23/11/15.
 */
public class CustomHtmlExporter extends HTMLExporter {

    private PcmParams pcmParams;
    private PcmCssBuilder pcmCssBuilder;
    private String htmlTemplate;
    private Element body;
    private Element tr;
    private Document.OutputSettings settings;
    private int productIndex = 0;
    private int featureIndex = 0;
    private int cellIndex = 0;
    private PrintWriter writer;

    public CustomHtmlExporter() {
        this.pcmCssBuilder = new PcmCssBuilder();
        this.settings = new Document.OutputSettings();
    }

    public boolean export(PCM pcm, PcmParams pcmParams) {
        String cssFile = "style.css";
        File templateFile = new File("./template/template.html");
        this.pcmParams = pcmParams;
        try {
            htmlTemplate = Files.toString(templateFile, StandardCharsets.UTF_8);
            htmlTemplate = toHTML(pcm);
            writer = new PrintWriter("pcm.html", "UTF-8");
            writer.write(htmlTemplate);
            pcmCssBuilder.generateCss(cssFile);
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
        Document document = Jsoup.parse(htmlTemplate);
        body = document.body();
        document.head().select("title").first().text(pcmParams.getTitle());
        pcm.accept(this);
        return document.outputSettings(settings).outerHtml();
    }

    @Override
    //@todo export htmlTemplate
    public void visit(PCM pcm) {
        Element table = body.appendElement("table");
        table.attr("id", "matrix_" + pcmParams.getTitle()).attr("border", "1");

        if(pcmParams.hasStyle()) {
            pcmCssBuilder.addModule(".pcm", pcmParams.getStyle());
            table.addClass(".pcm");
        }

        tr = table.appendElement("tr");
        if(pcmParams.getFeatures().hasStyle()) {
            pcmCssBuilder.addModule(".features", pcmParams.getFeatures().getStyle());
            tr.addClass("features");
        }
        for(Feature feature: pcm.getConcreteFeatures()) {
            feature.accept(this);
            featureIndex++;
        }

        if(pcmParams.getProducts().hasStyle()) {
            pcmCssBuilder.addModule(".products", pcmParams.getProducts().getStyle());
            tr.addClass("products");
        }
        for (Product product : pcm.getProducts()) {
            tr = table.appendElement("tr");
            product.accept(this);
            productIndex++;
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Feature feature) {
        Element td = tr.appendElement("th").text(feature.getName());

        if(pcmParams.getFeatures().getElement(featureIndex).hasStyle()) {
            pcmCssBuilder.addModule("." + feature.getName(), pcmParams.getFeatures().getElement(featureIndex).getStyle());
            td.addClass(feature.getName());
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Product product) {
        tr.appendElement("th").text(product.getName());
        cellIndex = 0;

        if(pcmParams.getProducts().getElement(productIndex).hasStyle()) {
            pcmCssBuilder.addModule("." + product.getName(), pcmParams.getProducts().getElement(productIndex).getStyle());
            tr.addClass(product.getName());
        }
        for(Cell cell: product.getCells()) {
            cell.accept(this);
            cellIndex++;
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Cell cell) {
        Element td = tr.appendElement("td").text(cell.getContent());
        String cssClass;
        if(pcmParams.getProducts().getElement(productIndex).getCell(cellIndex).hasStyle()) {
            cssClass = cell.getFeature().getName() + "_" + cell.getProduct().getName();
            pcmCssBuilder.addModule("." + cssClass, pcmParams.getProducts().getElement(productIndex).getCell(cellIndex).getStyle());
            td.addClass(cssClass);
        }
    }
}
