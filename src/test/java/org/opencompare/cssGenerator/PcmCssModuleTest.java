/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opencompare.cssGenerator;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.opencompare.jsonParser.JsonParamsLoader;
import org.opencompare.jsonParser.PcmParams;

/**
 *
 * @author Florian
 */
public class PcmCssModuleTest {

    @Test
    public void testGetModule() throws IOException, Exception {
        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(new File("./src/test/java/org/opencompare/jsonParser/resources/params1.json"));
        PcmParams pcmParams = jsonParamsLoader.load();
        PcmCssBuilder builder = new PcmCssBuilder();
        builder.addModule(".pcm", pcmParams.getStyle());
        builder.generateCss("myCss.css");
    }
    
}
