package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Compare PCM values to a range and apply a style
 * @todo tests
 */
public class Range implements IOperation<Number> {

    private long minValue;
    private long maxValue;
    private Style style;

    /**
     * Constructor
     * @param minValue long
     * @param maxValue long
     * @param style Style
     */
    public Range(long minValue, long maxValue, Style style) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.style = style;
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
    public boolean execute(Number value) {
        if(value.longValue() >= this.minValue && value.longValue() < this.maxValue) return true;
        return false;
    }
}
