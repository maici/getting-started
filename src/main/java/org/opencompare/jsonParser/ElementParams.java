package org.opencompare.jsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent the style parameters of an element (feature/product)
 * @todo tests
 */
public class ElementParams {
    private int order;
    private Style style;
    private Map<Integer, Style> cells;
    private List<IOperation> operations;

    public ElementParams() {
        this.cells = new HashMap<>();
        this.operations = new ArrayList<>();
    }

    /**
     * Return the order of an element (feature/product)
     * @return int
     */
    public int getOrder() {
        return order;
    }

    /**
     * Set the order of an element (feature/product)
     * @param order int
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Return the style of an element
     * @return Style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Set the style of an element
     * @param style Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Add style to a cell of the element (feature/product)
     * @param key Integer
     * @param value Style
     * @return Style
     */
    public Style putCellStyle(Integer key, Style value) {
        return cells.put(key, value);
    }

    /**
     * Remove cell style of the element (feature/product)
     * @param key Integer
     * @return boolean
     */
    public Style removeCellStyle(Integer key) {
        return cells.remove(key);
    }
}
