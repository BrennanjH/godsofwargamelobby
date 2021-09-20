/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import org.springframework.stereotype.Component;

/**
 *
 * @author brenn
 */
@Component
public class LoadState {
    private boolean preLoad = false;
    private boolean fullyLoaded = false;

    public boolean isPreLoad() {
        return preLoad;
    }

    public void setPreLoad(boolean preLoad) {
        this.preLoad = preLoad;
    }

    public boolean isFullyLoaded() {
        return fullyLoaded;
    }

    public void setFullyLoaded(boolean readyLoad) {
        this.fullyLoaded = readyLoad;
    }
    
}
