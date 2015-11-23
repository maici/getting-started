/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opencompare.cssGenerator;
import com.projetloki.genesis.CssModule;
import com.projetloki.genesis.Genesis;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Pcm css builder
 */ 
public class PcmCssBuilder{
    
    private Collection<CssModule> modules;
    
    /**
     * Constructor
     */
    public PcmCssBuilder(){
        this.modules = new ArrayList<>();
    }
    
    /**
     * Create a module and add it into the list
     * @param classe Name of the css classe of the module
     * @param properties List of the modified properties and their value
     */
    public void addModule(String classe, Map<String, String> properties){
        modules.add(new PcmCssModule(classe, properties).getModule());
    }
    
    /**
     * Create css file with all modules defined
     * @param name Name of the css generated file
     */
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
