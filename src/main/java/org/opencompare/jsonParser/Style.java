package org.opencompare.jsonParser;

import java.util.HashMap;

/**
 * Created by mahdi on 12/11/15.
 */
public class Style {

    private String backgroundColor;
    private String color;
    private String font;
    private String fontStyle;
    private int fontSize;
    private HashMap<String, String> style;

    public Style() {
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.style.put("background-color", backgroundColor);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        this.style.put("color", color);
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
        this.style.put("font-family", font);
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
        if(fontStyle == "bold") this.style.put("font-weight", fontStyle);
        else if (fontStyle == "italic") this.style.put("font-style", fontStyle);
        else this.style.put("font-weight", "normal");
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
