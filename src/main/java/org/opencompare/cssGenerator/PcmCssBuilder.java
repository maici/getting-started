/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opencompare.cssGenerator;
import com.projetloki.genesis.CssBuilder;
import com.projetloki.genesis.CssModule;
import com.projetloki.genesis.Genesis;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencompare.jsonParser.Style;

/**
 *
 * @author Florian
 */ 
public class PcmCssBuilder{
    
    private Collection<CssModule> modules;
    
    public PcmCssBuilder(){
        this.modules = new ArrayList<>();
    }
    
    public void addModule(String property, Map<String, String> style){
        modules.add(new PcmCssModule(property, style).getModule());
    }
    
    public void generateCss(String name) {
        Genesis.Builder genesis = Genesis.builder();
        for(CssModule mod: modules){
            genesis.install(mod);
        }
        try {
            genesis.build().writeCssFile(new File(name));
        } catch (IOException ex) {
            Logger.getLogger(PcmCssBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
