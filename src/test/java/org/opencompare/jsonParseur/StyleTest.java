package org.opencompare.jsonParseur;

import org.junit.Test;
import org.opencompare.jsonParser.Style;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by mahdi on 12/11/15.
 */
public class StyleTest {

    @Test(expected = NumberFormatException.class)
    public void testBackgroundColor() {
        Style style = new Style();
        style.setBackgroundColor("000000");
        assertTrue("Check color format", style.getStyle().get("background-color") == "#000000");
        style.setBackgroundColor("#000000");
        fail("Color Format incorrect");
    }
}
