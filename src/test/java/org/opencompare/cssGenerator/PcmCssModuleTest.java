/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opencompare.cssGenerator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PcmCssModuleTest {

    @Test
    public void testOneModule() throws IOException, Exception {
        
        String result = "@charset 'UTF-8';\n.pcm{background-color:#ffffff;color:#123123;font-size:10;font-style:bold}\n";
        
        Map<String, String> styles = new HashMap<String, String>() {{
           put("background-color", "#ffffff");
           put("color", "#123123");
           put("font-size", "10");
           put("font-style", "bold");
        }};
        
        PcmCssBuilder builder = new PcmCssBuilder();
        builder.addModule(".pcm", styles);
        
        String fileName = "testModuleCss.css";
        String path = "./src/test/css/";
        File file = new File(path+fileName);
        if(file.exists()) { file.delete(); }
        
        builder.generateCss(path+fileName);
        
        file = new File(path+fileName);
        
        // On vérifie que le fichier a bien été généré.
        Assert.assertTrue("Le fichier a été généré.", file.exists());
        
        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String fileContent = new String(encoded, StandardCharsets.UTF_8);
        
        Assert.assertTrue("Le fichier généné est correct.", fileContent.equals(result));
    }
    
    @Test
    public void testTwoModules() throws IOException, Exception {
        
        String result = "@charset 'UTF-8';\n"
                + ".pcm{font-size:10}\n"
                + ".features{background-color:#ffffff;color:#123123;font-style:bold}\n";
        
        Map<String, String> stylesPCM = new HashMap<String, String>() {{
           put("font-size", "10");
        }};
        
        Map<String, String> stylesFeatures = new HashMap<String, String>() {{
           put("background-color", "#ffffff");
           put("color", "#123123");
           put("font-style", "bold");
        }};
        
        PcmCssBuilder builder = new PcmCssBuilder();
        builder.addModule(".pcm", stylesPCM);
        builder.addModule(".features", stylesFeatures);
        
        String fileName = "testModuleCss.css";
        String path = "./src/test/css/";
        File file = new File(path + fileName);
        if(file.exists()) { file.delete(); }
        
        builder.generateCss(path + fileName);
        
        file = new File(path + fileName);
        
        // On vérifie que le fichier a bien été généré.
        Assert.assertTrue("Le fichier a été généré.", file.exists());
        
        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String fileContent = new String(encoded, StandardCharsets.UTF_8);
        
        Assert.assertTrue("Le fichier généné est correct.", fileContent.equals(result));
    }
}
