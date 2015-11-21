package org.opencompare.jsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        return this.style.hasStyle();
    }

    /**
     * Return all the elements
     * @return List
     */
    public List<ElementParams> getElements() {
        return elements;
    }

    /**
     * Set a list of ElementParam
     * @param elements List
     */
    public void setElements(List<ElementParams> elements) {
        this.elements = elements;
    }

    /**
     * Return a element
     * @param index int
     * @return ElementParam
     */
    public ElementParams getElement(int index) {
        return elements.get(index);
    }

    /**
     * Add style parameters of an element (feature/product)
     * @param elementParams ElementParams
     * @return boolean
     */
    public boolean addElementParams(ElementParams elementParams) {
        return elements.add(elementParams);
    }

    /**
     * Remove style parameters of an element (feature/product)
     * @param elementParams ElementParams
     */
    public void removeElementParams(ElementParams elementParams) {
        elements.remove(elementParams);
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
        return operations.isEmpty();
    }
}
