/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opencompare.cssGenerator;

import com.projetloki.genesis.CssBuilder;
import com.projetloki.genesis.CssModule;
import com.projetloki.genesis.Properties;
import java.io.IOException;
import java.util.Map;

/**
 * Css module which contains is composed by a classe and the properties which are modified
 */
public class PcmCssModule {
    
    private String classe;
    private Map<String, String> properties;
    
    /**
     * 
     * @param classe Name of the modified classe
     * @param properties List of the modified properties and their value
     */
    public PcmCssModule(String classe, Map<String, String> properties){
        this.classe = classe;
        this.properties = properties;
    }

    /**
     * Configure a CssModule Object
     * @return CssModule Object
     */
    public CssModule getModule(){
        return new CssModule() {

            @Override
            public void configure(CssBuilder out) throws IOException {
                for(String property: properties.keySet()){
                    out.addRule(classe, Properties.builder().set(property, properties.get(property)));
                }
            }
        };
    }
}
