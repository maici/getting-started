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
    private Map<Integer,ElementParams> elements;
    private List<IOperation> operations;

    public ElementsParams() {
        this.elements = new HashMap<>();
        this.operations = new ArrayList<>();
    }

    /**
     * Return the style of the element (features/products)
     * @return Style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Set the style of all the features/products
     * @param style Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Add style parameters of an element (feature/product)
     * @param key Integer
     * @param elementParams ElementParams
     * @return boolean
     */
    public ElementParams put(Integer key, ElementParams elementParams) {
        return elements.put(key, elementParams);
    }

    /**
     * Remove style parameters of an element (feature/product)
     * @param key Integer
     */
    public void remove(Integer key) {
        elements.remove(key);
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
}
