package org.opencompare.customHtmlExporter;

import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.opencompare.api.java.*;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.cssGenerator.PcmCssBuilder;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mahdi on 23/11/15.
 */
public class CustomHtmlExporter extends HTMLExporter {

    private PcmParams pcmParams;
    private PcmCssBuilder pcmCssBuilder;
    private PCM pcm;
    private String htmlTemplate;
    private String cssFile;
    private Element body;
    private Element tr;
    private Document.OutputSettings settings;
    private PrintWriter writer;
    private PCMMetadata pcmMetadata;
    private int featureDepth;

    public CustomHtmlExporter() {
        this.pcmCssBuilder = new PcmCssBuilder();
        this.settings = new Document.OutputSettings();
    }

    public boolean export(PCMContainer pcmContainer, PcmParams pcmParams) {
        pcm = pcmContainer.getPcm();
        if(pcmContainer.getMetadata() == null) {
            pcmMetadata = new PCMMetadata(pcm);
        }
        else {
            pcmMetadata = pcmContainer.getMetadata();
        }
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
            writer.close();
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
        LinkedList<AbstractFeature> featuresToVisit = new LinkedList<>();
        LinkedList<AbstractFeature> nextFeaturesToVisit;
        featuresToVisit.addAll(pcm.getFeatures());
        Element table = body.appendElement("table");
        table.attr("id", "matrix_" + pcmParams.getTitle()).attr("border", "1");
        System.out.println(this.pcmParams.getStyle());

        if(pcmParams.hasStyle()) {
            System.out.println(this.pcmParams.hasStyle());
            pcmCssBuilder.addModule(".pcm", pcmParams.getStyle());
            table.addClass("pcm");
        }

        // Compute depth
        featureDepth = pcm.getFeaturesDepth();

        // Generate HTML code for features
        featuresToVisit = new LinkedList<>();
        nextFeaturesToVisit = new LinkedList<>();
        featuresToVisit.addAll(pcm.getFeatures());

        tr = table.appendElement("tr");
        tr.appendElement("th").attr("rowspan", Integer.toString(featureDepth)).text("Product");
        while(!featuresToVisit.isEmpty()) {
            Collections.sort(featuresToVisit, new Comparator<AbstractFeature>() {
                @Override
                public int compare(AbstractFeature feat1, AbstractFeature feat2) {
                    return pcmMetadata.getFeaturePosition(feat1) - pcmMetadata.getFeaturePosition(feat2);
                }
            });
            for (AbstractFeature feature : featuresToVisit) {
                feature.accept(this);
            }
            featuresToVisit = nextFeaturesToVisit;
            nextFeaturesToVisit = new LinkedList<>();
            featureDepth--;
            if (featureDepth >= 1) {
                tr = table.appendElement("tr");
            }
        }

        tr = table.appendElement("tr");
        if(pcmParams.hasFeatures() && pcmParams.getFeatures().hasStyle()) {
            pcmCssBuilder.addModule(".features", pcmParams.getFeatures().getStyle());
            tr.addClass("features");
        }
        for(AbstractFeature feature: featuresToVisit) {
            feature.accept(this);
        }

        if(pcmParams.hasProducts() && pcmParams.getProducts().hasStyle()) {
            pcmCssBuilder.addModule(".products", pcmParams.getProducts().getStyle());
            tr.addClass("products");
        }
        for (Product product : pcmMetadata.getSortedProducts()) {
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
        List<Cell> cells = product.getCells();

        Collections.sort(cells, new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                return pcmMetadata.getSortedFeatures().indexOf(cell1.getFeature()) - pcmMetadata.getSortedFeatures().indexOf(cell2.getFeature());
            }
        });
        for(Cell cell: cells) {
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
