package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Compare PCM values and apply a style
 * @todo tests
 */
public class Comparison implements IOperation<Number> {

    private String operation;
    private long operand;
    private Style style;

    /**
     * Constructor
     * @param operation String
     * @param operand long
     * @param style Style
     */
    public Comparison(String operation, long operand, Style style) {
        setOperation(operation);
        this.operand = operand;
        this.style = style;
    }

    /**
     * Set the comparison operation
     * @param operation String
     * @throws IllegalArgumentException
     */
    public void setOperation(String operation) {
        if(operation.equals("<") ||
                operation.equals("<=") ||
                operation.equals(">") ||
                operation.equals(">=") ||
                operation.equals("="))
            this.operation = operation;
        else throw new IllegalArgumentException("Invalid comparison operation");
    }

    /**
     * Return the style to apply
     * @return Map
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
     * Execute the comparison
     * @return boolean
     */
    @Override
    public boolean execute(Number value) {
        boolean res = false;

        switch (this.operation) {
            case "<":
                if(value.floatValue() < this.operand) res = true;
                break;
            case "<=":
                if(value.floatValue() <= this.operand) res = true;
                break;
            case ">":
                if(value.floatValue() > this.operand) res = true;
                break;
            case ">=":
                if(value.floatValue() >= this.operand) res = true;
                break;
            case "=":
                if(value.floatValue() == this.operand) res = true;
                break;
            default: res = false;
        }
        return res;
    }
}
