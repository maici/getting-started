/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opencompare.cssGenerator;

import org.junit.Test;
import org.opencompare.jsonParser.JsonParamsLoader;
import org.opencompare.jsonParser.PcmParams;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Florian
 */
public class PcmCssModuleTest {

    @Test
    public void testGetModule() throws IOException, Exception {
        JsonParamsLoader jsonParamsLoader = new JsonParamsLoader(new File("./src/test/params/example.json"));
        PcmParams pcmParams = jsonParamsLoader.load();
        PcmCssBuilder builder = new PcmCssBuilder();
        builder.addModule(".pcm", pcmParams.getStyle());
        builder.generateCss("./src/test/css/myCss.css");
    }
    
}
