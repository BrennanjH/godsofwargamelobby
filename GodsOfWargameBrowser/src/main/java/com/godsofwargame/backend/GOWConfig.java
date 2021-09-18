/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

/** A java file that defines spring beans for use by the server
 *
 * @author brenn
 */
//File doesn't work off this system so I'll need to create a new solution
@Configuration
@ComponentScan("com.godsofwargame.backend")
@PropertySource("classpath:match.properties")
public class GOWConfig {
    
    @Bean
    @Scope(value="singleton")
    public static GodsofWargame godsOfWargame(){
        System.out.println("GOWConfig: Creating GodsofWargameObject");
        return new GodsofWargame();
    }
    @Bean
    @Scope(value="singleton")
    public static MatchProperties properties(){
        System.out.println("GOWConfig: Creating properties object");
        return new MatchProperties();
    }
    /*
    @Bean
    @Scope("prototype")
    public jsonsendHolder jsonSendHolder(){
        System.out.println("GOWConfig: Creating new jsonsendHolder");
        return new jsonsendHolder();
    }
    */
    /*
    @Bean
    @Scope(value="singleton")
    public static Map map(){
        System.out.println("GOWConfig: Creating map Object");
        
        Map temp = new Map();        
        return temp;
    }
    */
}

