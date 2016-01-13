package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Compare string values to PCM values and apply a style
 */
public class StringComparison implements IOperation<String> {

    private String id;
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

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Execute the operation
     * @param value T
     * @return boolean
     */
    @Override
    public boolean execute(String value) {
        if(value.matches(this.string)) return true;
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

    @Override
    public void setStyle(Style style) {
        this.style = style;
    }
}
