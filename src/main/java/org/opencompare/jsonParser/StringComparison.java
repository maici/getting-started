package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Compare string values to PCM values and apply a style
 */
public class StringComparison<T extends String> implements IOperation<T> {

    private String string;
    private Style style;

    /**
     * Constructor
     * @param string String
     * @param style Style
     */
    public StringComparison(String string, Style style) {
        this.string = string;
        this.style = style;
    }

    /**
     * Execute the operation
     * @param value T
     * @return boolean
     */
    @Override
    public boolean execute(T value) {
        if(string.equals(value)) return true;
        return false;
    }

    /**
     * Return the style to apply
     * @return Map
     */
    @Override
    public Map getStyle() {
        return this.style.getStyle();
    }
}
