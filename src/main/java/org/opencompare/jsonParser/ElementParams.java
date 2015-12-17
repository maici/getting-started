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
    private Map<String,Style> cells;
    private List<Operation> operations;

    /**
     * Constructor
     */
    public ElementParams() {
        this.cells = new HashMap<>();
        this.operations = new ArrayList<>();
    }

    /**
     * Return the order of an element (feature/product)
     * @return int
     */
    public long getOrder() {
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
     * @return Style Map
     */
    public Map<String, String> getStyle() {
        return style.getStyle();
    }

    /**
     * Set the style of an element
     * @param style Style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Test if the element has style parameters
     * @return boolean
     */
    public boolean hasStyle() {
        if(style != null) return this.style.hasStyle();
        return false;
    }

    /**
     * Return all the cells
     * @return Map
     */
    public Map<String, Style> getCells() {
        return cells;
    }

    /**
     * Set a list of cells
     * @param cells Map
     */
    public void setCells(Map<String, Style> cells) {
        this.cells = cells;
    }

    /**
     * Return cell parameters
     * @param key String
     * @return CellParams
     */
    public Style getCell(String key) {
        return cells.get(key);
    }

    /**
     * Check if the cell exist
     * @param key String
     * @return boolean
     */
    public boolean containsCell(String key) {
        return cells.containsKey(key);
    }

    /**
     * Add style to a cell of the element (feature/product)
     * @param key String
     * @param cellParams Style
     * @return CellParams
     */
    public Style addCellStyle(String key, Style cellParams) {
        return cells.put(key, cellParams);
    }

    /**
     * Remove cell style of the element (feature/product)
     * @param key String
     */
    public void removeCellStyle(String key) {
        cells.remove(key);
    }

    /**
     * Test if has cells parameters
     * @return boolean
     */
    public boolean hasCells() {
        return !cells.isEmpty();
    }

    /**
     * Return all the operations
     * @return list
     */
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * Set a list of operations
     * @param operations
     */
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    /**
     * Add operation for the element (feature/product)
     * @param operation
     * @return boolean
     */
    public boolean addOperation(Operation operation) {
        return operations.add(operation);
    }

    /**
     * Remove operation for the element (feature/product)
     * @param operation
     * @return boolean
     */
    public boolean removeOperation(Operation operation) {
        return operations.remove(operation);
    }

    /**
     * Test if the element has operations
     * @return boolean
     */
    public boolean hasOperations() {
        if(operations != null )return !operations.isEmpty();
        return false;
    }
}
