/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/** A class that is created by grabbing values of fields from a plethora of objects primarily gameState
 *
 * @author brenn
 */
@Component
public class MatchProperties {
    @Value("${map.column}")
    private String cols;
    @Value("${map.row}")
    private String rows;
    
    public String getCols() {
        return cols;
    }

    public String getRows() {
        return rows;
    }

    
}
