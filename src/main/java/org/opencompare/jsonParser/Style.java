package org.opencompare.jsonParser;

import java.util.HashMap;
import java.util.Map;

/**
 * Handle styles parameters
 */
public class Style {

    private Map<String, String> style;
    private String backgroundColor;
    private String color;
    private String font;
    private String fontWeight;
    private String fontStyle;
    private int fontSize;

    public Style() {
        this.style = new HashMap<>();
    }

    public String put(String key, String value) {
        return style.put(key, value);
    }

    public String get(Object key) {
        return style.get(key);
    }

    /**
     * set background color
     * @param backgroundColor String
     * @throws IllegalArgumentException
     */
    public void setBackgroundColor(String backgroundColor) {
        if(backgroundColor.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
            this.style.put("background-color", backgroundColor);
        else throw new IllegalArgumentException("Background color must be in hexadecimal format");
    }

    /**
     * set text color
     * @param color String
     * @throws IllegalArgumentException
     */
    public void setColor(String color) {
        if(color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
            this.style.put("color", color);
        else throw new IllegalArgumentException("Color must be in hexadecimal format");
    }

    /**
     * set the text
     * @param font String
     */
    public void setFont(String font) {
        this.style.put("font-family", font);
    }

    /**
     * set font weight (bold/normal)
     * @param fontWeight String
     * @throws IllegalArgumentException
     */
    public void setFontWeight(String fontWeight) {
        if(fontWeight.equals("normal") || fontWeight.equals("bold")) {
            this.style.put("font-weight", fontWeight);
        }
        else throw new IllegalArgumentException("Incorrect font weight value");
    }

    /**
     * set font style (italic/none)
     * @param fontStyle String
     * @throws IllegalArgumentException
     */
    public void setFontStyle(String fontStyle) {

        if(fontStyle.equals("normal") || fontStyle.equals("italic")) {
            this.style.put("font-style", fontStyle);
        }
        else throw new IllegalArgumentException("Incorrect font style value");
    }

    /**
     * set font size in px
     * @param fontSize int
     * @throws IllegalArgumentException
     */
    public void setFontSize(int fontSize) {
        if(fontSize > 0)
            this.style.put("font-size", Integer.toString(fontSize));
        else throw new IllegalArgumentException("font size must be positive.");
    }

    /**
     * Test if the style parameters is empty
     * @return boolean
     */
    public boolean hasStyle() {
        return style.isEmpty();
    }

    /**
     * get styles parameters
     * @return HashMap
     */
    public Map<String, String> getStyle() {
        if(this.backgroundColor != null) this.setBackgroundColor(this.backgroundColor);
        if(this.color != null) this.setColor(this.color);
        if(this.font != null) this.setFont(this.font);
        if(this.fontWeight != null) this.setFontWeight(this.fontWeight);
        if(this.fontStyle != null) this.setFontStyle(this.fontStyle);
        if(this.fontSize != 0) this.setFontSize(this.fontSize);
        return this.style;
    }
}
