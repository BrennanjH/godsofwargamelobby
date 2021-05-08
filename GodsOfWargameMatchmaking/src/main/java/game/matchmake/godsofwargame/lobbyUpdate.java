/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.matchmake.godsofwargame;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author brenn
 */
@WebServlet(name = "lobbyUpdate", urlPatterns = {"/lobbyUpdate"})
public class lobbyUpdate extends HttpServlet {
    static LobbyPointer lobbyList;
    @Override
    public void init() {
        System.out.println("-------------------------=");
        System.out.println("LOBBY UPDATE HAS STARTED");
        InetAddress ip;
        String hostname;
        
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
        } catch (UnknownHostException ex) {
            Logger.getLogger(lobbyUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
        System.out.println("-------------------------=");
        lobbyList = new LobbyPointer();
    }
    @Override
    public void destroy(){
        System.out.println("-------------------------=");
        System.out.println("Servlet:lobbyUpdate shutdown completed successfully");
        System.out.println("-------------------------=");
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
    @Override//TODO create more intelligent system with lobby filtering and performance saving techniques
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet called");
        Gson serializer = new Gson();
        lobbyList.asArray();
        PrintWriter responseData = response.getWriter();
        responseData.write(serializer.toJson(lobbyList.keys));
        //System.out.println(lobbyList.gameList.isEmpty());
        System.out.println(serializer.toJson(lobbyList.keys)+ " :Response data");
            
        
        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        BufferedReader body = request.getReader();
        String lineReader;
        while(body.ready()){
            lineReader = body.readLine();
            System.out.println(lineReader + " :The POST body");
            Lobby gameServer = new Lobby();
            URL url = new URL(request.getRequestURL().toString());
            //String serverAddress = lineReader.substring(lineReader.indexOf("=") + 1, lineReader.length());
            //System.out.println(lineReader + " :lineReader");
            System.out.println(url + " :gameAddress");
            gameServer.ip = request.getRequestURL().toString();
            
            if(lineReader.contains("serverURL")){
                
                try {
                    
                    //System.out.println(url);
                    lobbyList.addLobby(gameServer);
                } catch (LobbyDiscrepencyException ex) {
                    Logger.getLogger(lobbyUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
                lobbyList.needsUpdate = true;
            } else if(lineReader.contains("removeServerURL")){
                
                System.out.println("removing Server");
                try {
                    
                    lobbyList.removeLobby(gameServer);
                } catch (LobbyDiscrepencyException E){
                    E.printStackTrace();
                }
                lobbyList.needsUpdate = true;
            }
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "recieves http from game server about and stores it.";
    }// </editor-fold>

}