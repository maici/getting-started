package org.opencompare.jsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Operations Factory, wrap IOperation operations
 */
public class Operation<T> implements IOperation<T>{

    private String id;
    private String type;
    private Map<String, String> values;
    private Style style;
    private IOperation operation;

    public Operation() throws Exception {
        this.values = new HashMap<>();
    }

    /**
     * Instantiate the correct operation
     */
    public void operationFactory() {
        switch (type) {
            case "comparison":
                operation = new Comparison(values.get("operation"), Integer.valueOf(values.get("operand")), style);
                break;
            case "range":
                operation = new Range(Integer.valueOf(values.get("minValue")), Integer.valueOf(values.get("maxValue")), style);
                break;
            case "string-comparison":
                operation = new StringComparison(values.get("match"), style);
                break;
            default:
                throw new IllegalArgumentException("Incorrect operation type");
        }
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
    public boolean execute(T value) {
        if(operation == null ) operationFactory();
        if(value instanceof String && operation instanceof Comparison) return false;
        if(value instanceof String && operation instanceof Range) return false;
        if(value instanceof Number && operation instanceof StringComparison) return false;
        return operation.execute(value);
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
