/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 */
public interface internalCommands {
    public void execute(UnitTypes removed);
}
