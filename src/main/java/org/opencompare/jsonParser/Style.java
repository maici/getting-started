package org.opencompare.jsonParser;

import java.util.HashMap;

/**
 * Created by mahdi on 12/11/15.
 */
public class Style {

    private HashMap<String, String> style;

    public Style() {
    }

    public void setBackgroundColor(String backgroundColor) {
        if(backgroundColor.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
            this.style.put("background-color", "#" + backgroundColor);
        else throw new NumberFormatException("background color must be in hexadecimal format");
    }

    public void setColor(String color) {
        if(color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
            this.style.put("color", "#" + color);
        else throw new NumberFormatException("color must be in hexadecimal format");
    }

    public void setFont(String font) {
        this.style.put("font-family", font);
    }

    public void setFontStyle(String fontStyle) {
        if(fontStyle == "bold") this.style.put("font-weight", fontStyle);
        else if (fontStyle == "italic") this.style.put("font-style", fontStyle);
        else this.style.put("font-weight", "normal");
    }

    public void setFontSize(int fontSize) {
        if(fontSize > 0)
            this.style.put("font-size", Integer.toString(fontSize));
        else throw new IllegalArgumentException("font size must be positive.");
    }

    public HashMap<String, String> getStyle() {
        return style;
    }
}
