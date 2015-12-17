package org.opencompare.jsonParser;

import java.util.Map;

/**
 * Interface for the pcm operations that can modify the style
 */
public interface IOperation<T> {
    String getId();
    void setId(String id);
    boolean execute(T value);
    Map getStyle();
    void setStyle(Style style);
}
