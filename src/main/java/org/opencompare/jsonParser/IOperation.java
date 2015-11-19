package org.opencompare.jsonParser;

/**
 * Interface for the pcm operations that can modify the style
 */
public interface IOperation {
    boolean execute();
    Style getStyle();
}
