package org.opencompare.jsonParser;

import java.util.HashMap;

/**
 * Handle styles parameters
 */
public class Style {

    private HashMap<String, String> style = new HashMap<>();

    public Style() {
    }

    /**
     * set background color
     * @param backgroundColor String
     * @throws IllegalArgumentException
     */
    public void setBackgroundColor(String backgroundColor) {
        if(backgroundColor.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
            this.style.put("background-color", "#" + backgroundColor);
        else throw new IllegalArgumentException("Background color must be in hexadecimal format");
    }

    /**
     * set text color
     * @param color String
     * @throws IllegalArgumentException
     */
    public void setColor(String color) {
        if(color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$"))
            this.style.put("color", "#" + color);
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
     * get styles parameters
     * @return HashMap
     */
    public HashMap<String, String> getStyle() {
        return this.style;
    }
}
