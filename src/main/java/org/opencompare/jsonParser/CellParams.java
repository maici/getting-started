package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Represent the style parameters of a cell
 * @todo tests
 */
public class CellParams {

    private double id;
    private Style style;

    /**
     * Return the cell id
     * @return double
     */
    public double getId() {
        return id;
    }

    /**
     * Set the cell id
     * @param id double
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the style of the cell
     * @param style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Return the style of the cell
     * @return Map
     */
    public Map<String, String> getStyle() {
        return style.getStyle();
    }

    /**
     * Test if the cell have style parameters
     * @return boolean
     */
    public boolean hasStyle() {
        return style.hasStyle();
    }
}
