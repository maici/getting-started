package org.opencompare.org.opencompare.jsonParser;

import java.util.HashMap;

/**
 * Created by mahdi on 12/11/15.
 */
public class Style {

    private String backgroundColor;
    private String color;
    private String font;
    private String fontStyle;
    private String fontSize;
    private HashMap<String, String> style;



    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }


}
