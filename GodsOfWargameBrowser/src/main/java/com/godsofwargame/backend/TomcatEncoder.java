/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author brenn
 */
//Proper use would be to change JSONhandler into the encoder
public class TomcatEncoder  implements Encoder.Text<String>{
   @Override
   public void init(EndpointConfig ec) { }
   @Override
   public void destroy() { }
    @Override
    public String encode(String msgA) throws EncodeException {
      // Access msgA's properties and convert to JSON text...
      return msgA;
   }
}
