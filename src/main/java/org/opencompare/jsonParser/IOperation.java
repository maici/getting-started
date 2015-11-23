package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Interface for the pcm operations that can modify the style
 */
public interface IOperation<T> {
    boolean execute(T value);
    Map getStyle();
}
