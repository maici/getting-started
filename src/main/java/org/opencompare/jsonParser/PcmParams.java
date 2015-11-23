package org.opencompare.jsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represent the style parameters of the PCM
 * @todo tests
 */
public class PcmParams {

    private String title;
    private String description;
    private boolean invert;
    private Style style;
    private ElementsParams features;
    private ElementsParams products;
    private List<Operation> operations;

    public PcmParams() {
        this.operations = new ArrayList<>();
    }

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
     * Return the style of all the PCM
     * @return Map
     */
    public Map<String, String> getStyle() {
        return style.getStyle();
    }

    /**
     * Set the style of all the PCM
     * @param style Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Test if the PCM has style parameters
     * @return boolean
     */
    public boolean hasStyle() {
        return this.style.hasStyle();
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

    /**
     * Return the list of operations
     * @return List
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * Set a list of operations
     * @param operations List
     */
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    /**
     * Return an operation
     * @param index
     * @return IOperation
     */
    public IOperation getOperation(int index) {
        return operations.get(index);
    }

    /**
     * Add operation to the PCM
     * @param operation IOperation
     * @return boolean
     */
    public boolean addOperation(Operation operation) {
        return operations.add(operation);
    }

    /**
     * Remove operation to the PCM
     * @param operation IOperation
     * @return boolean
     */
    public boolean removeOperation(Operation operation) {
        return operations.remove(operation);
    }

    /**
     * Test if the PCM has operations
     * @return boolean
     */
    public boolean hasOperations() {
        return !operations.isEmpty();
    }
}
