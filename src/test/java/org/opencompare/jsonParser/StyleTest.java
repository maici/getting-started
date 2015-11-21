package org.opencompare.jsonParser;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mahdi on 12/11/15.
 */
public class StyleTest {

    @Test(expected = IllegalArgumentException.class)
    public void testBackgroundColor() {
        Style style = new Style();
        style.setBackgroundColor("#000000");
        assertTrue("Incorrect color format", style.getStyle().get("background-color").equals("#000000"));
        style.setBackgroundColor("#000000");
        fail("Incorrect color format");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testColor() {
        Style style = new Style();
        style.setColor("#000000");
        assertTrue("Incorrect color format", style.getStyle().get("color").equals("#000000"));
        style.setColor("abc");
        fail("Color format incorrect");
    }

    @Test
    public void testFont() {
        Style style = new Style();
        style.setFont("Arial");
        assertTrue("Incorrect font value", style.getStyle().get("font-family").equals("Arial"));
        style.setFont("Gothic Sans");
        assertTrue("Incorrect font value", style.getStyle().get("font-family").equals("Gothic Sans"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFontWeight() {
        Style style = new Style();
        style.setFontWeight("bold");
        assertTrue("Incorrect font weight value", style.getStyle().get("font-weight").equals("bold"));
        style.setFontWeight("normal");
        assertTrue("Incorrect font weight value", style.getStyle().get("font-weight").equals("normal"));
        style.setFontWeight("italic");
        fail("Incorrect font weight value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFontStyle() {
        Style style = new Style();
        style.setFontStyle("italic");
        assertTrue("Incorrect font style value", style.getStyle().get("font-style").equals("italic"));
        style.setFontStyle("normal");
        assertTrue("Incorrect font style value", style.getStyle().get("font-style").equals("normal"));
        style.setFontStyle("bold");
        fail("Incorrect font weight value");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFontSize() {
        Style style = new Style();
        style.setFontSize(10);
        assertTrue("Incorrect font size value", style.getStyle().get("font-size").equals("10"));
        style.setFontSize(-10);
        fail("Incorrect font size value");
    }


}
