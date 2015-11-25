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
    private String cssFile;
    private Element body;
    private Element tr;
    private Document.OutputSettings settings;
    private PrintWriter writer;

    public CustomHtmlExporter() {
        this.pcmCssBuilder = new PcmCssBuilder();
        this.settings = new Document.OutputSettings();
    }

    public boolean export(PCM pcm, PcmParams pcmParams) {
        String path = "./out";
        cssFile = "style.css";
        File templateFile = new File("./template/template.html");
        this.pcmParams = pcmParams;
        try {
            htmlTemplate = Files.toString(templateFile, StandardCharsets.UTF_8);
            htmlTemplate = toHTML(pcm);
            new File(path).mkdir();
            writer = new PrintWriter(path + "/pcm.html", "UTF-8");
            writer.write(htmlTemplate);
            pcmCssBuilder.generateCss(path + "/" + cssFile);
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
        if(pcmCssBuilder.hasModules()) {
            document.head().append("<link rel=\"stylesheet\" href=" + cssFile + ">");
        }
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
        tr.appendElement("th").text("Product");
        if(pcmParams.hasFeatures() && pcmParams.getFeatures().hasStyle()) {
            pcmCssBuilder.addModule(".features", pcmParams.getFeatures().getStyle());
            tr.addClass("features");
        }
        for(Feature feature: pcm.getConcreteFeatures()) {
            feature.accept(this);
        }

        if(pcmParams.hasProducts() && pcmParams.getProducts().hasStyle()) {
            pcmCssBuilder.addModule(".products", pcmParams.getProducts().getStyle());
            tr.addClass("products");
        }
        for (Product product : pcm.getProducts()) {
            tr = table.appendElement("tr");
            product.accept(this);
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Feature feature) {
        Element td = tr.appendElement("th").text(feature.getName());

        if(pcmParams.hasFeatures() &&
                pcmParams.getFeatures().containsElement(feature.getName()) &&
                pcmParams.getFeatures().getElement(feature.getName()).hasStyle()) {
            pcmCssBuilder.addModule("." + feature.getName(), pcmParams.getFeatures().getElement(feature.getName()).getStyle());
            td.addClass(feature.getName());
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Product product) {
        tr.appendElement("th").text(product.getName());

        if(pcmParams.hasProducts() &&
                pcmParams.getProducts().containsElement(product.getName()) &&
                pcmParams.getProducts().getElement(product.getName()).hasStyle()) {
            pcmCssBuilder.addModule("." + product.getName(), pcmParams.getProducts().getElement(product.getName()).getStyle());
            tr.addClass(product.getName());
        }
        for(Cell cell: product.getCells()) {
            cell.accept(this);
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Cell cell) {
        Element td = tr.appendElement("td").text(cell.getContent());
        String cellId = cell.getFeature().getName() + "_";
        if(pcmParams.hasProducts() &&
                pcmParams.getProducts().containsElement(cell.getProduct().getName()) &&
                pcmParams.getProducts().getElement(cell.getProduct().getName()).containsCell(cellId) &&
                pcmParams.getProducts().getElement(cell.getProduct().getName()).getCell(cellId).hasStyle()) {
            pcmCssBuilder.addModule("." + cellId, pcmParams.getProducts().getElement(cell.getProduct().getName()).getCell(cellId).getStyle());
            td.addClass(cellId);
        }
    }
}
