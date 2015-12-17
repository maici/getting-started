package org.opencompare.jsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent the style parameters of the elements (features/products)
 * @todo tests
 */
public class ElementsParams {

    private Style style;
    private Map<String, ElementParams> elements;
    private List<IOperation> operations;

    public ElementsParams() {
        this.elements = new HashMap<>();
        this.operations = new ArrayList<>();
    }

    /**
     * Return the style of the element (features/products)
     * @return Map
     */
    public Map<String, String> getStyle() {
        return style.getStyle();
    }

    /**
     * Set the style of all the features/products
     * @param style Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Test if the elements of the PCM have style parameters
     * @return boolean
     */
    public boolean hasStyle() {
        if(style != null) return this.style.hasStyle();
        return false;
    }

    /**
     * Check if an element exist
     * @param key String
     * @return boolean
     */
    public boolean containsElement(String key) {
        return elements.containsKey(key);
    }

    /**
     * Return all the elements
     * @return Map
     */
    public Map<String, ElementParams> getElements() {
        return elements;
    }

    /**
     * Set a list of ElementParam
     * @param elements Map
     */
    public void setElements(HashMap<String, ElementParams> elements) {
        this.elements = elements;
    }

    /**
     * Return a element
     * @param key String
     * @return ElementParam
     */
    public ElementParams getElement(String key) {
        return elements.get(key);
    }

    /**
     * Add style parameters of an element (feature/product)
     * @param key String
     * @param elementParams ElementParams
     * @return ElementParams
     */
    public ElementParams addElementParams(String key, ElementParams elementParams) {
        return elements.put(key, elementParams);
    }

    /**
     * Remove style parameters of an element (feature/product)
     * @param id String
     */
    public void removeElementParams(String id) {
        elements.remove(id);
    }

    /**
     * Return all operations
     * @return List
     */
    public List<IOperation> getOperations() {
        return operations;
    }

    /**
     * Set a list of operations
     * @param operations List
     */
    public void setOperations(List<IOperation> operations) {
        this.operations = operations;
    }

    /**
     * Add an operation for the elements (features/products)
     * @param iOperation IOperation
     * @return boolean
     */
    public boolean addOperation(IOperation iOperation) {
        return operations.add(iOperation);
    }

    /**
     * Remove an operation for the elements (features/products)
     * @param iOperation IOperation
     * @return boolean
     */
    public boolean removeOperation(IOperation iOperation) {
        return operations.remove(iOperation);
    }

    /**
     * Test if has operations
     * @return boolean
     */
    public boolean hasOperations() {
        if(operations != null )return !operations.isEmpty();
        return false;
    }
}
