package org.opencompare.jsonParser;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mahdi on 12/11/15.
 */
public class StyleTest {

    @Test
    public void testBackgroundColor() {
        Style style = new Style();
        style.setBackgroundColor("#000000");
        assertTrue("Incorrect color format", style.getStyle().get("background-color").equals("#000000"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectBackgroundColor() {
        Style style = new Style();
        style.setBackgroundColor("red");
    }

    @Test
    public void testColor() {
        Style style = new Style();
        style.setColor("#000000");
        assertTrue("Incorrect color format", style.getStyle().get("color").equals("#000000"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectColor() {
        Style style = new Style();
        style.setColor("red");
    }

    @Test
    public void testFont() {
        Style style = new Style();
        style.setFont("Arial");
        assertTrue("Incorrect font value", style.getStyle().get("font-family").equals("Arial"));
        style.setFont("Gothic Sans");
        assertTrue("Incorrect font value", style.getStyle().get("font-family").equals("Gothic Sans"));
    }

    @Test
    public void testFontWeight() {
        Style style = new Style();
        style.setFontWeight("bold");
        assertTrue("Incorrect font weight value", style.getStyle().get("font-weight").equals("bold"));
        style.setFontWeight("normal");
        assertTrue("Incorrect font weight value", style.getStyle().get("font-weight").equals("normal"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectFontWeight() {
        Style style = new Style();
        style.setFontWeight("italic");
    }

    @Test
    public void testFontStyle() {
        Style style = new Style();
        style.setFontStyle("italic");
        assertTrue("Incorrect font style value", style.getStyle().get("font-style").equals("italic"));
        style.setFontStyle("normal");
        assertTrue("Incorrect font style value", style.getStyle().get("font-style").equals("normal"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectFontStyle() {
        Style style = new Style();
        style.setFontStyle("bold");
        fail("Incorrect font weight value");
    }

    @Test
    public void testFontSize() {
        Style style = new Style();
        style.setFontSize(10);
        assertTrue("Incorrect font size value", style.getStyle().get("font-size").equals("10"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectFontSize() {
        Style style = new Style();
        style.setFontSize(-10);
    }
}
