/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/** A java file that defines spring beans for use by the server
 *
 * @author brenn
 */

@Configuration
public class GOWConfig {
    
    @Bean
    @Scope(value="singleton")
    public static GodsofWargame godsOfWargame(){
        System.out.println("GOWConfig: Creating GodsofWargameObject");
        return new GodsofWargame();
    }
    
    
}

