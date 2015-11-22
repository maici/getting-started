package org.opencompare.jsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Operations Factory, wrap IOperation operations
 */
public class Operation<T> implements IOperation<T>{

    private String type;
    private Map<String, String> values;
    private Style style;
    private IOperation operation;
    private int value;

    public Operation() {
        this.values = new HashMap<>();
    }

    /**
     * Create the correct operation
     * @return IOperation
     */
    public IOperation operationFactory() {
        switch (type) {
            case "comparison":
                return new Comparison(values.get("operation"), Integer.valueOf(values.get("operand")), style);
            case "range":
                return new Range(Integer.valueOf(values.get("minValue")), Integer.valueOf(values.get("maxValue")), style);
            case "string-comparison":
                return new StringComparison(values.get("string"), style);
            default:
                throw new IllegalArgumentException("Incorrect operation type");
        }
    }

    /**
     * Execute the operation
     * @param value T
     * @return boolean
     */
    @Override
    public boolean execute(T value) {
        if(operation == null ) this.operation = operationFactory();
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
}
