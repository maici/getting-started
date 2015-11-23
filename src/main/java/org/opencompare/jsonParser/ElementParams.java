package org.opencompare.jsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represent the style parameters of an element (feature/product)
 * @todo tests
 */
public class ElementParams {
    private long id;
    private long order;
    private Style style;
    private List<CellParams> cells;
    private List<IOperation> operations;

    public ElementParams() {
        this.cells = new ArrayList<>();
        this.operations = new ArrayList<>();
    }

    /**
     * Return the element id
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Set the element id
     * @return long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return the order of an element (feature/product)
     * @return long
     */
    public long getOrder() {
        return order;
    }

    /**
     * Set the order of an element (feature/product)
     * @param order long
     */
    public void setOrder(long order) {
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
        return this.style.hasStyle();
    }

    /**
     * Return all the cells
     * @return list
     */
    public List<CellParams> getCells() {
        return cells;
    }

    /**
     * Set a list of cells
     * @param cells List
     */
    public void setCells(List<CellParams> cells) {
        this.cells = cells;
    }

    /**
     * Return cell parameters
     * @param index int
     * @return CellParams
     */
    public CellParams getCell(int index) {
        return cells.get(index);
    }

    /**
     * Add style to a cell of the element (feature/product)
     * @param cellParams CellParams
     * @return boolean
     */
    public boolean addCellStyle(CellParams cellParams) {
        return cells.add(cellParams);
    }

    /**
     * Remove cell style of the element (feature/product)
     * @param cellParams CellParams
     * @return boolean
     */
    public boolean removeCellStyle(CellParams cellParams) {
        return cells.remove(cellParams);
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
    public List<IOperation> getOperations() {
        return operations;
    }

    /**
     * Set a list of operations
     * @param operations
     */
    public void setOperations(List<IOperation> operations) {
        this.operations = operations;
    }

    /**
     * Add operation for the element (feature/product)
     * @param operation
     * @return boolean
     */
    public boolean addOperation(IOperation operation) {
        return operations.add(operation);
    }

    /**
     * Remove operation for the element (feature/product)
     * @param operation
     * @return boolean
     */
    public boolean removeOperation(IOperation operation) {
        return operations.remove(operation);
    }

    /**
     * Test if the element has operations
     * @return boolean
     */
    public boolean hasOperations() {
        return !operations.isEmpty();
    }
}
