package org.opencompare.customHtmlExporter;

import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.opencompare.api.java.*;
import org.opencompare.api.java.impl.PCMFactoryImpl;
import org.opencompare.api.java.io.HTMLExporter;
import org.opencompare.api.java.value.IntegerValue;
import org.opencompare.api.java.value.RealValue;
import org.opencompare.cssGenerator.PcmCssBuilder;
import org.opencompare.jsonParser.IOperation;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;
import java.io.IOException;
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
    private Element body;
    private Element thead;
    private Element tr;
    private Document.OutputSettings settings;
    private PrintWriter writer;
    private PCMMetadata pcmMetadata;
    private int featureDepth;
    private Product currentProduct;
    private String specialCharacterRegex = "[\\[-\\]{}()*+?@<>\\/.,\\\\^$|#\\s]";
    private String cssFile = "style.css";
    private String cssPrefix = "oc_";

    public CustomHtmlExporter() {
        this.pcmCssBuilder = new PcmCssBuilder();
        this.settings = new Document.OutputSettings();
    }

    public boolean export(PCMContainer pcmContainer, PcmParams pcmParams, String path) {
        pcm = pcmContainer.getPcm();
        if (pcmContainer.getMetadata() == null) {
            pcmMetadata = new PCMMetadata(pcm);
        } else {
            pcmMetadata = pcmContainer.getMetadata();
        }
        String templatePath = "./template";
        File templateFile = new File("./template/template.html");
        this.pcmParams = pcmParams;
        try {
            htmlTemplate = Files.toString(templateFile, StandardCharsets.UTF_8);
            htmlTemplate = toHTML(pcm);
            createExportDirectories(path, templatePath);
            writer = new PrintWriter(path + "/index.html", "UTF-8");
            writer.write(htmlTemplate);
            writer.close();
            pcmCssBuilder.generateCss(path + "/css/" + cssFile);
            File template = new File(path + "/template.html");
            template.delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    //@todo export htmlTemplate
    public String toHTML(PCM pcm) {
        if (pcmParams.isInvert()) {
            pcm.invert(new PCMFactoryImpl());
            pcmParams.invertParams();
        }
        settings.prettyPrint();
        Document document = Jsoup.parse(htmlTemplate);
        body = document.body();
        document.head().select("title").first().text(pcmParams.getTitle());
        pcm.accept(this);
        if (pcmCssBuilder.hasModules()) {
            document.head().append("<link rel=\"stylesheet\" href= css/" + cssFile + ">");
        }
        return document.outputSettings(settings).outerHtml();
    }

    @Override
    //@todo export htmlTemplate
    public void visit(PCM pcm) {
        LinkedList<AbstractFeature> featuresToVisit = new LinkedList<>();
        LinkedList<AbstractFeature> nextFeaturesToVisit;
        String pcmCssClass = cssPrefix + "pcm";
        String featuresCssClass = cssPrefix + "features";
        String productsCssClass = cssPrefix + "products";

        body.addClass("container");
        Element description = body.appendElement("div").addClass("page-header");
        description.appendElement("h1").text(pcmParams.getTitle()).appendElement("br").appendElement("small").text(" " + pcmParams.getDescription());

        featuresToVisit.addAll(pcm.getFeatures());
        Element divTable = body.appendElement("div");
        divTable.addClass("table-responsive");
        Element table = divTable.appendElement("table").addClass("table-bordered").addClass("text-center");
        table.addClass("table");
        table.attr("id", "pcm");
        if (pcmParams.hasStyle()) {
            pcmCssBuilder.addModule(pcmCssClass, pcmParams.getStyle());
            table.addClass(pcmCssClass);
        }

        if (pcmParams.hasOperations()) {
            for (IOperation op : pcmParams.getOperations()) {
                pcmCssBuilder.addModule(cssPrefix + op.getId(), op.getStyle());
            }
        }

        // Compute depth
        featureDepth = pcm.getFeaturesDepth();

        // Generate HTML code for features
        featuresToVisit = new LinkedList<>();
        nextFeaturesToVisit = new LinkedList<>();
        featuresToVisit.addAll(pcm.getFeatures());

        thead = table.appendElement("thead").appendElement("tr");
        Element tbody = table.appendElement("tbody");

        if(pcmParams.isInvert()) {
            thead.appendElement("th").text("Features");
        }else {
            thead.appendElement("th").text("Products");
        }
        while (!featuresToVisit.isEmpty()) {
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
                tr = tbody.appendElement("tr");
            }
        }

        if (pcmParams.hasFeatures() && pcmParams.getFeatures().hasStyle()) {
            pcmCssBuilder.addModule(featuresCssClass, pcmParams.getFeatures().getStyle());
            tr.addClass(featuresCssClass);

            if (pcmParams.getFeatures().hasOperations()) {
                for (IOperation op : pcmParams.getFeatures().getOperations()) {
                    pcmCssBuilder.addModule(featuresCssClass + "_" + op.getId(), op.getStyle());
                }
            }
        }

        for (AbstractFeature feature : featuresToVisit) {
            feature.accept(this);
        }

        if (pcmParams.hasProducts() && pcmParams.getProducts().hasStyle()) {
            pcmCssBuilder.addModule(productsCssClass, pcmParams.getProducts().getStyle());
            if (pcmParams.getProducts().hasOperations()) {
                for (IOperation op : pcmParams.getProducts().getOperations()) {
                    pcmCssBuilder.addModule(productsCssClass + "_" + op.getId(), op.getStyle());
                }
            }
        }

        for (Product product : pcmMetadata.getSortedProducts()) {
            tr = tbody.appendElement("tr");
            product.accept(this);
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Feature feature) {
        Element td = thead.appendElement("th").text(feature.getName());

        if (pcmParams.hasFeatures() &&
                pcmParams.getFeatures().containsElement(feature.getName())) {

            String featureCssClass = cssPrefix + feature.getName().replaceAll(this.specialCharacterRegex, "");

            if (pcmParams.getFeatures().getElement(feature.getName()).hasStyle()) {
                pcmCssBuilder.addModule(featureCssClass, pcmParams.getFeatures().getElement(feature.getName()).getStyle());
                td.addClass(featureCssClass);
            }

            if (pcmParams.getFeatures().getElement(feature.getName()).hasOperations() &&
                    !pcmCssBuilder.containsCSSClass(feature.getName())) {
                for (IOperation op : pcmParams.getFeatures().getElement(feature.getName()).getOperations()) {
                    pcmCssBuilder.addModule(featureCssClass + "_" + op.getId(), op.getStyle());
                }
            }
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Product product) {
        // Fix pour le probleme cell.getProduct() null pointer
        this.currentProduct = product;

        Element th = tr.appendElement("th");
        th.text(product.getName());

        if (pcmParams.hasProducts() && pcmParams.getProducts().hasStyle()) {
            th.addClass(cssPrefix + "products");
        }

        if (pcmParams.hasProducts() &&
                pcmParams.getProducts().containsElement(product.getName())) {

            String productCssClass = cssPrefix + product.getName().replaceAll(this.specialCharacterRegex, "");

            if (pcmParams.getProducts().getElement(product.getName()).hasStyle()) {
                pcmCssBuilder.addModule(productCssClass, pcmParams.getProducts().getElement(product.getName()).getStyle());
                tr.addClass(productCssClass);
            }

            if (pcmParams.getProducts().getElement(product.getName()).hasOperations()) {
                for (IOperation op : pcmParams.getProducts().getElement(product.getName()).getOperations()) {
                    pcmCssBuilder.addModule(productCssClass + "_" + op.getId(), op.getStyle());
                }
            }
        }
        List<Cell> cells = product.getCells();

        Collections.sort(cells, new Comparator<Cell>() {
            @Override
            public int compare(Cell cell1, Cell cell2) {
                return pcmMetadata.getSortedFeatures().indexOf(cell1.getFeature()) - pcmMetadata.getSortedFeatures().indexOf(cell2.getFeature());
            }
        });
        for (Cell cell : cells) {
            cell.accept(this);
        }
    }

    @Override
    //@todo export htmlTemplate
    public void visit(Cell cell) {
        Element td = tr.appendElement("td").text(cell.getContent());
        String featureName = cell.getFeature().getName();
        String productName = this.currentProduct.getName();

        if (pcmParams.hasFeatures() &&
                pcmParams.getFeatures().containsElement(cell.getFeature().getName()) &&
                pcmParams.getFeatures().getElement(cell.getFeature().getName()).hasStyle()) {
            td.addClass(cssPrefix + cell.getFeature().getName().replaceAll(this.specialCharacterRegex, ""));
        }

        if ((pcmParams.hasFeatures() &&
                pcmParams.getFeatures().containsElement(featureName) &&
                pcmParams.getFeatures().getElement(featureName).containsCell(productName) &&
                pcmParams.getFeatures().getElement(featureName).getCell(productName).hasStyle())) {

            String cellCssClass = cssPrefix +
                    cssPrefix + featureName.replaceAll(this.specialCharacterRegex, "") +
                    "_" +
                    this.currentProduct.getName().replaceAll(this.specialCharacterRegex, "");

            pcmCssBuilder.addModule(cellCssClass, pcmParams.getFeatures().getElement(featureName).getCell(productName).getStyle());
            td.addClass(cellCssClass);
        } else if ((pcmParams.hasProducts() &&
                pcmParams.getProducts().containsElement(productName) &&
                pcmParams.getProducts().getElement(productName).containsCell(featureName) &&
                pcmParams.getProducts().getElement(productName).getCell(featureName).hasStyle())) {

            String cellCssClass = cssPrefix +
                    this.currentProduct.getName().replaceAll(this.specialCharacterRegex, "") +
                    "_" +
                    cssPrefix + featureName.replaceAll(this.specialCharacterRegex, "");

            pcmCssBuilder.addModule(cellCssClass, pcmParams.getProducts().getElement(productName).getCell(featureName).getStyle());
            td.addClass(cellCssClass);
        }

        Object value;

        if (cell.getInterpretation() instanceof IntegerValue) value = Integer.valueOf(cell.getContent());
        else if (cell.getInterpretation() instanceof RealValue) value = Float.valueOf(cell.getContent());
        else value = cell.getContent();

        if (pcmParams.hasOperations()) {
            for (IOperation op : pcmParams.getOperations()) {
                if (op.execute(value)) {
                    td.addClass(cssPrefix + op.getId());
                }
            }
        }

        if (pcmParams.hasFeatures() && pcmParams.getFeatures().hasOperations()) {
            for (IOperation op : pcmParams.getFeatures().getOperations()) {
                if (op.execute(value)) {
                    td.addClass(cssPrefix + "features_" + op.getId());
                }
            }
        }

        if (pcmParams.hasFeatures() &&
                pcmParams.getFeatures().containsElement(cell.getFeature().getName()) &&
                pcmParams.getFeatures().getElement(cell.getFeature().getName()).hasOperations()) {
            for (IOperation op : pcmParams.getFeatures().getElement(cell.getFeature().getName()).getOperations()) {
                if (op.execute(value)) {
                    td.addClass(cssPrefix + cell.getFeature().getName().replaceAll(this.specialCharacterRegex, "") + "_" + op.getId());
                }
            }
        }

        if (pcmParams.hasProducts() && pcmParams.getProducts().hasOperations()) {
            for (IOperation op : pcmParams.getProducts().getOperations()) {
                if (op.execute(value)) {
                    td.addClass(cssPrefix + "products_" + op.getId());
                }
            }
        }

        if (pcmParams.hasProducts() &&
                pcmParams.getProducts().containsElement(this.currentProduct.getName()) &&
                pcmParams.getProducts().getElement(this.currentProduct.getName()).hasOperations()) {
            for (IOperation op : pcmParams.getProducts().getElement(this.currentProduct.getName()).getOperations()) {
                if (op.execute(value)) {
                    td.addClass(cssPrefix + this.currentProduct.getName().replaceAll(this.specialCharacterRegex, "") + "_" + op.getId());
                }
            }
        }
    }

    public void createExportDirectories(String path, String templatePath) throws IOException {
        File root = new File(path);
        root.mkdir();
        copyDirectories(path, new File(templatePath).listFiles());
    }

    public void copyDirectories(String path, File[] dir) throws IOException {
        for (File file : dir) {
            if (file.isDirectory()) {
                new File(path + "/" + file.getName()).mkdir();
                copyDirectories(path + "/" + file.getName(), file.listFiles());
            } else {
                Files.copy(file, new File(path + "/" + file.getName()));
            }
        }
    }
}
