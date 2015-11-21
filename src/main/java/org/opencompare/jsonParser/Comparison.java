package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Compare PCM values and apply a style
 * @todo tests
 */
public class Comparison implements IOperation {

    private String operation;
    private int operand;
    private int value;
    private Style style;

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
     * Set the comparison operand value
     * @param operand int
     */
    public void setOperand(int operand) {
        this.operand = operand;
    }

    /**
     * Set the value to compare
     * @param value int
     */
    public void setValue(int value) {
        this.value = value;
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
    public boolean execute() {
        boolean res = false;

        switch (this.operation) {
            case "<":
                if(this.operand < this.value) res = true;
                break;
            case "<=":
                if(this.operand <= this.value) res = true;
                break;
            case ">":
                if(this.operand > this.value) res = true;
                break;
            case ">=":
                if(this.operand >= this.value) res = true;
                break;
            case "=":
                if(this.operand == this.value) res = true;
                break;
            default: res = false;
        }
        return res;
    }
}
