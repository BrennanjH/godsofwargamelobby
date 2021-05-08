/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author brenn
 */
@WebServlet(name = "informLoggs")
public class informLoggs extends HttpServlet {
    String matchmakerURL = "http://matchmaker-service:8081/lobbyUpdate";//GodsOfWargameMatchmaking //GodsOfWargameMatchmaking-Vers.1
    
    @Override
    public void init() throws ServletException
    {
        //catch ()
        System.out.println("-----------");
        
        
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(matchmakerURL);//GodsOfWargameMatchmaking //GodsOfWargameMatchmaking-Vers.1
            //System.out.println("After404?");
            List<NameValuePair> params = new ArrayList<>();
            InetAddress ip;
            String hostname;
            try {
                ip = InetAddress.getLocalHost();
                hostname = ip.getHostName();
                System.out.println("Your current IP address : " + ip);
                System.out.println("Your current Hostname : " + hostname);
                params.add(new BasicNameValuePair("serverURL", ip.getHostAddress()));
            } catch (UnknownHostException e) {
                
                e.printStackTrace();
            }
            
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            client.close();
            
        } catch (IOException ex) { 
            Logger.getLogger(informLoggs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        System.out.println("---------- GameServerServlet:informLoggs Initialized ----------");
        
    }
    
    @Override
    public void destroy() {
        System.out.println("-----------");
        
        try {
            //System.out.println("trying to remove server");
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(matchmakerURL);//GodsOfWargameMatchmaking //GodsOfWargameMatchmaking-Vers.1
            //System.out.println("After404?");
            List<NameValuePair> params = new ArrayList<>();
            InetAddress ip;
            //String hostname;
            try {
                ip = InetAddress.getLocalHost();
                
                params.add(new BasicNameValuePair("removeServerURL", ip.getHostAddress()));
            } catch (UnknownHostException e) {
                
                e.printStackTrace();
            }
            
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            
            CloseableHttpResponse response = client.execute(httpPost);
            System.out.println(response.getStatusLine().getStatusCode());
            //assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
            client.close();
            
        } catch (IOException ex) { 
            Logger.getLogger(informLoggs.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("---------- Servlet:informLoggs shutdown completed successfully ----------");
        System.out.println("----------");
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet who's purpose is to send the ip of the machine hosting container";
    }// </editor-fold>

}
