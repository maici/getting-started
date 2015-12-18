package org.opencompare.jsonParser;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by mahdi on 19/11/15.
 */
public class JsonParsingTest {

    @Test
    public void testLoad() {
        JsonParamsLoader jsonParamsLoader = null;
        try {
            jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/example.json"));
        } catch (IOException e) {
            fail("Unable to find the file");
        }
        PcmParams pcmParams = null;
        try {
            pcmParams = jsonParamsLoader.load();
        } catch (Exception e) {
            fail("Unable to load pcm parameters file");
        }
    }

    @Test
    public void testEmptyParameters() throws Exception {
        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/test_empty_params.json"));
        PcmParams pcmParams = jsonParamsLoader.load();
        assertTrue("Unable to get pcm title parameter", pcmParams.getTitle().equals("pcm title"));
        assertTrue("Unable to get pcm description parameter",pcmParams.getDescription().equals("pcm description"));
        assertFalse("Should return false, no features parameters", pcmParams.hasFeatures());
        assertFalse("Should return false, no products parameters", pcmParams.hasProducts());
        assertFalse("Should return false, no style", pcmParams.hasStyle());
        assertFalse("Should return false, no operations", pcmParams.hasOperations());

    }

    @Test
    public void testPcmParameters() throws Exception {
        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/test_pcm_params.json"));
        PcmParams pcmParams = jsonParamsLoader.load();

        // Style
        assertTrue("Unable to get pcm style parameters", pcmParams.hasStyle());
        assertTrue("Wrong color parameter", pcmParams.getStyle().get("color").equals("#000000"));
        assertTrue("Wrong backgroundColor parameter", pcmParams.getStyle().get("background-color").equals("#000000"));
        assertTrue("Wrong fontWeight parameter", pcmParams.getStyle().get("font-weight").equals("bold"));
        assertTrue("Wrong fontStyle parameter", pcmParams.getStyle().get("font-style").equals("italic"));
        assertTrue("Wrong font parameter", pcmParams.getStyle().get("font-family").equals("Arial"));
        assertTrue("Wrong fontSize parameter", pcmParams.getStyle().get("font-size").equals("10"));

        // Operations
        assertTrue("Unable to get pcm operations parameters", pcmParams.hasOperations());
        //Comparison
        //Sup
        assertTrue("Wrong operation id parameter", pcmParams.getOperation(0).getId().equals("op_sup_0"));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(0).execute(1));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(0).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getOperation(0).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getOperation(0).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getOperation(0).getStyle().get("color").equals("#000000"));
        //Inf
        assertTrue("Wrong operation id parameter", pcmParams.getOperation(1).getId().equals("op_inf_0"));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(1).execute(-1));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(1).execute(-0.1));
        assertFalse("Wrong operation parameters", pcmParams.getOperation(1).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getOperation(1).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getOperation(1).getStyle().get("color").equals("#000000"));
        //Eq
        assertTrue("Wrong operation id parameter", pcmParams.getOperation(2).getId().equals("op_eq_0"));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(2).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(2).execute(0.0));
        assertFalse("Wrong operation parameters", pcmParams.getOperation(2).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getOperation(2).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getOperation(2).getStyle().get("color").equals("#000000"));
        //Range
        assertTrue("Wrong operation id parameter", pcmParams.getOperation(3).getId().equals("op_range_0_1"));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(3).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(3).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getOperation(3).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getOperation(3).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getOperation(3).getStyle().get("color").equals("#000000"));
        //String-comparison
        assertTrue("Wrong operation id parameter", pcmParams.getOperation(4).getId().equals("op_match_test"));
        assertTrue("Wrong operation parameters", pcmParams.getOperation(4).execute("test"));
        assertFalse("Wrong operation parameters", pcmParams.getOperation(4).execute("testt"));
        assertFalse("Wrong fontSize parameter", pcmParams.getOperation(4).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getOperation(4).execute(0.1));
        assertTrue("Wrong operation style parameter", pcmParams.getOperation(4).getStyle().get("color").equals("#000000"));
    }

    @Test
    public void testFeaturesParams() {
        JsonParamsLoader jsonParamsLoader = null;
        try {
            jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/test_features_params.json"));
        } catch (IOException e) {
            fail();
        }
        PcmParams pcmParams = null;
        try {
            pcmParams = jsonParamsLoader.load();
        } catch (Exception e) {
            fail();
        }

        // Style
        assertTrue("Unable to get features parameters", pcmParams.hasFeatures());
        assertTrue("Unable to get features style parameters", pcmParams.getFeatures().hasStyle());
        assertTrue("Wrong features color parameter", pcmParams.getFeatures().getStyle().get("color").equals("#000000"));
        assertTrue("Wrong features backgroundColor parameter", pcmParams.getFeatures().getStyle().get("background-color").equals("#000000"));
        assertTrue("Wrong features fontWeight parameter", pcmParams.getFeatures().getStyle().get("font-weight").equals("bold"));
        assertTrue("Wrong features fontStyle parameter", pcmParams.getFeatures().getStyle().get("font-style").equals("italic"));
        assertTrue("Wrong features font parameter", pcmParams.getFeatures().getStyle().get("font-family").equals("Arial"));
        assertTrue("Wrong features fontSize parameter", pcmParams.getFeatures().getStyle().get("font-size").equals("10"));

        // Operations
        assertTrue("Unable to get features operations parameters", pcmParams.getFeatures().hasOperations());
        //Comparison
        //Sup
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getOperations().get(0).getId().equals("op_sup_0"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(0).execute(1));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(0).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(0).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getOperations().get(0).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getOperations().get(0).getStyle().get("color").equals("#000000"));
        //Inf
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getOperations().get(1).getId().equals("op_inf_0"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(1).execute(-1));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(1).execute(-0.1));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(1).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getOperations().get(1).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getOperations().get(1).getStyle().get("color").equals("#000000"));
        //Eq
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getOperations().get(2).getId().equals("op_eq_0"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(2).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(2).execute(0.0));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(2).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getOperations().get(2).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getOperations().get(2).getStyle().get("color").equals("#000000"));
        //Range
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getOperations().get(3).getId().equals("op_range_0_1"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(3).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(3).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(3).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getOperations().get(3).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getOperations().get(3).getStyle().get("color").equals("#000000"));
        //String-comparison
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getOperations().get(4).getId().equals("op_match_test"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(4).execute("test"));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getOperations().get(4).execute("testt"));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getOperations().get(4).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getOperations().get(4).execute(0.1));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getOperations().get(4).getStyle().get("color").equals("#000000"));
    }

    @Test
    public void testProductsParams() {
        JsonParamsLoader jsonParamsLoader = null;
        try {
            jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/test_products_params.json"));
        } catch (IOException e) {
            fail();
        }
        PcmParams pcmParams = null;
        try {
            pcmParams = jsonParamsLoader.load();
        } catch (Exception e) {
            fail();
        }

        // Style
        assertTrue("Unable to get features parameters", pcmParams.hasProducts());
        assertTrue("Unable to get features style parameters", pcmParams.getProducts().hasStyle());
        assertTrue("Wrong features color parameter", pcmParams.getProducts().getStyle().get("color").equals("#000000"));
        assertTrue("Wrong features backgroundColor parameter", pcmParams.getProducts().getStyle().get("background-color").equals("#000000"));
        assertTrue("Wrong features fontWeight parameter", pcmParams.getProducts().getStyle().get("font-weight").equals("bold"));
        assertTrue("Wrong features fontStyle parameter", pcmParams.getProducts().getStyle().get("font-style").equals("italic"));
        assertTrue("Wrong features font parameter", pcmParams.getProducts().getStyle().get("font-family").equals("Arial"));
        assertTrue("Wrong features fontSize parameter", pcmParams.getProducts().getStyle().get("font-size").equals("10"));

        // Operations
        assertTrue("Unable to get features operations parameters", pcmParams.getProducts().hasOperations());
        //Comparison
        //Sup
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getOperations().get(0).getId().equals("op_sup_0"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(0).execute(1));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(0).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getOperations().get(0).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getOperations().get(0).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getOperations().get(0).getStyle().get("color").equals("#000000"));
        //Inf
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getOperations().get(1).getId().equals("op_inf_0"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(1).execute(-1));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(1).execute(-0.1));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getOperations().get(1).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getOperations().get(1).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getOperations().get(1).getStyle().get("color").equals("#000000"));
        //Eq
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getOperations().get(2).getId().equals("op_eq_0"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(2).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(2).execute(0.0));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getOperations().get(2).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getOperations().get(2).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getOperations().get(2).getStyle().get("color").equals("#000000"));
        //Range
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getOperations().get(3).getId().equals("op_range_0_1"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(3).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(3).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getOperations().get(3).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getOperations().get(3).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getOperations().get(3).getStyle().get("color").equals("#000000"));
        //String-comparison
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getOperations().get(4).getId().equals("op_match_test"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getOperations().get(4).execute("test"));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getOperations().get(4).execute("testt"));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getOperations().get(4).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getOperations().get(4).execute(0.1));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getOperations().get(4).getStyle().get("color").equals("#000000"));
    }

    @Test
    public void testFeatureParams() {
        JsonParamsLoader jsonParamsLoader = null;
        try {
            jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/test_feature_params.json"));
        } catch (IOException e) {
            fail();
        }
        PcmParams pcmParams = null;
        try {
            pcmParams = jsonParamsLoader.load();
        } catch (Exception e) {
            fail();
        }

        // Style
        assertTrue("Unable to get features parameters", pcmParams.getFeatures().containsElement("element1"));
        assertTrue("Unable to get features style parameters", pcmParams.getFeatures().getElement("element1").hasStyle());
        assertTrue("Wrong features color parameter", pcmParams.getFeatures().getElement("element1").getStyle().get("color").equals("#000000"));
        assertTrue("Wrong features backgroundColor parameter", pcmParams.getFeatures().getElement("element1").getStyle().get("background-color").equals("#000000"));
        assertTrue("Wrong features fontWeight parameter", pcmParams.getFeatures().getElement("element1").getStyle().get("font-weight").equals("bold"));
        assertTrue("Wrong features fontStyle parameter", pcmParams.getFeatures().getElement("element1").getStyle().get("font-style").equals("italic"));
        assertTrue("Wrong features font parameter", pcmParams.getFeatures().getElement("element1").getStyle().get("font-family").equals("Arial"));
        assertTrue("Wrong features fontSize parameter", pcmParams.getFeatures().getElement("element1").getStyle().get("font-size").equals("10"));

        // Operations
        assertTrue("Unable to get features operations parameters", pcmParams.getFeatures().getElement("element1").hasOperations());
        //Comparison
        //Sup
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(0).getId().equals("op_sup_0"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(0).execute(1));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(0).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(0).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(0).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(0).getStyle().get("color").equals("#000000"));
        //Inf
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(1).getId().equals("op_inf_0"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(1).execute(-1));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(1).execute(-0.1));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(1).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(1).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(1).getStyle().get("color").equals("#000000"));
        //Eq
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(2).getId().equals("op_eq_0"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(2).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(2).execute(0.0));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(2).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(2).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(2).getStyle().get("color").equals("#000000"));
        //Range
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(3).getId().equals("op_range_0_1"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(3).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(3).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(3).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(3).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(3).getStyle().get("color").equals("#000000"));
        //String-comparison
        assertTrue("Wrong operation id parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(4).getId().equals("op_match_test"));
        assertTrue("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(4).execute("test"));
        assertFalse("Wrong operation parameters", pcmParams.getFeatures().getElement("element1").getOperations().get(4).execute("testt"));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(4).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(4).execute(0.1));
        assertTrue("Wrong operation style parameter", pcmParams.getFeatures().getElement("element1").getOperations().get(4).getStyle().get("color").equals("#000000"));
    }

    @Test
    public void testProductParams() {
        JsonParamsLoader jsonParamsLoader = null;
        try {
            jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/test_product_params.json"));
        } catch (IOException e) {
            fail();
        }
        PcmParams pcmParams = null;
        try {
            pcmParams = jsonParamsLoader.load();
        } catch (Exception e) {
            fail();
        }

        // Style
        assertTrue("Unable to get features parameters", pcmParams.getProducts().containsElement("element1"));
        assertTrue("Unable to get features style parameters", pcmParams.getProducts().getElement("element1").hasStyle());
        assertTrue("Wrong features color parameter", pcmParams.getProducts().getElement("element1").getStyle().get("color").equals("#000000"));
        assertTrue("Wrong features backgroundColor parameter", pcmParams.getProducts().getElement("element1").getStyle().get("background-color").equals("#000000"));
        assertTrue("Wrong features fontWeight parameter", pcmParams.getProducts().getElement("element1").getStyle().get("font-weight").equals("bold"));
        assertTrue("Wrong features fontStyle parameter", pcmParams.getProducts().getElement("element1").getStyle().get("font-style").equals("italic"));
        assertTrue("Wrong features font parameter", pcmParams.getProducts().getElement("element1").getStyle().get("font-family").equals("Arial"));
        assertTrue("Wrong features fontSize parameter", pcmParams.getProducts().getElement("element1").getStyle().get("font-size").equals("10"));

        // Operations
        assertTrue("Unable to get features operations parameters", pcmParams.getProducts().getElement("element1").hasOperations());
        //Comparison
        //Sup
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getElement("element1").getOperations().get(0).getId().equals("op_sup_0"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(0).execute(1));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(0).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(0).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getElement("element1").getOperations().get(0).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getElement("element1").getOperations().get(0).getStyle().get("color").equals("#000000"));
        //Inf
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getElement("element1").getOperations().get(1).getId().equals("op_inf_0"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(1).execute(-1));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(1).execute(-0.1));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(1).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getElement("element1").getOperations().get(1).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getElement("element1").getOperations().get(1).getStyle().get("color").equals("#000000"));
        //Eq
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getElement("element1").getOperations().get(2).getId().equals("op_eq_0"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(2).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(2).execute(0.0));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(2).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getElement("element1").getOperations().get(2).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getElement("element1").getOperations().get(2).getStyle().get("color").equals("#000000"));
        //Range
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getElement("element1").getOperations().get(3).getId().equals("op_range_0_1"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(3).execute(0));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(3).execute(0.1));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(3).execute(1));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getElement("element1").getOperations().get(3).execute("a"));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getElement("element1").getOperations().get(3).getStyle().get("color").equals("#000000"));
        //String-comparison
        assertTrue("Wrong operation id parameter", pcmParams.getProducts().getElement("element1").getOperations().get(4).getId().equals("op_match_test"));
        assertTrue("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(4).execute("test"));
        assertFalse("Wrong operation parameters", pcmParams.getProducts().getElement("element1").getOperations().get(4).execute("testt"));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getElement("element1").getOperations().get(4).execute(0));
        assertFalse("Wrong fontSize parameter", pcmParams.getProducts().getElement("element1").getOperations().get(4).execute(0.1));
        assertTrue("Wrong operation style parameter", pcmParams.getProducts().getElement("element1").getOperations().get(4).getStyle().get("color").equals("#000000"));
    }
}