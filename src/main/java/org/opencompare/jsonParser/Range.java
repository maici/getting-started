package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Compare PCM values to a range and apply a style
 * @todo tests
 */
public class Range implements IOperation {

    private int minValue;
    private int maxValue;
    private int value;
    private Style style;

    /**
     * Set the minimal boundary value
     * @param minValue int
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * Set the maximal boundary value
     * @param maxValue
     */
    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Set the value to compare
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Return the style to apply
     * @return Style
     */
    public Map<String, String> getStyle() {
        return style.getStyle();
    }

    /**
     * Set the style to apply
     * @param style Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Compare the value to the given range
     * @return boolean
     */
    @Override
    public boolean execute() {
        if(this.value >= this.minValue && this.value < this.maxValue) return true;
        return false;
    }
}
