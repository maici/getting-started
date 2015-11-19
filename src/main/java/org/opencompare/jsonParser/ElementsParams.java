package org.opencompare.jsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent the style parameters of the elements (features/products)
 * @todo tests
 */
public class ElementsParams {

    private Style style;
    private List<ElementParams> elements;
    private List<IOperation> operations;

    public ElementsParams() {
        this.elements = new ArrayList<>();
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
     * @param elementParams ElementParams
     * @return boolean
     */
    public boolean addElement(ElementParams elementParams) {
        return elements.add(elementParams);
    }

    /**
     * Remove style parameters of an element (feature/product)
     * @param elementParams ElementParams
     * @return boolean
     */
    public boolean removeElement(ElementParams elementParams) {
        return elements.remove(elementParams);
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
