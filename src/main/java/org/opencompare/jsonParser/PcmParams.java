package org.opencompare.jsonParser;

/**
 * Represent the style parameters of the PCM
 * @todo tests
 */
public class PcmParams {

    private String title;
    private String description;
    private boolean invert;
    private ElementsParams features;
    private ElementsParams products;

    /**
     * Return the PCM title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the PCM title
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the PCM description
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the PCM description
     * @param description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the PCM invert lines/columns value
     * @return boolean
     */
    public boolean isInvert() {
        return invert;
    }

    /**
     * Invert the PCM lines/columns
     * @param invert boolean
     */
    public void setInvert(boolean invert) {
        this.invert = invert;
    }

    /**
     * Return the style parameters for the features
     * @return ElementsParams
     */
    public ElementsParams getFeatures() {
        return features;
    }

    /**
     * Set the style parameters for the features
     * @param features ElementsParams
     */
    public void setFeatures(ElementsParams features) {
        this.features = features;
    }

    /**
     * Return the style parameters for the products
     * @return ElementsParams
     */
    public ElementsParams getProducts() {
        return products;
    }

    /**
     * Set the style parameters for the products
     * @param products ElementsParams
     */
    public void setProducts(ElementsParams products) {
        this.products = products;
    }
}
