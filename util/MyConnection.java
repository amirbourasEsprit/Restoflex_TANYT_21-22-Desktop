/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.*;


/**
 *
 * @author boura
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/restoflex";
    public String login="root";
    public String pwd="";
    Connection cnx;
    private static MyConnection instance;
    
    private MyConnection(){
        try {
            cnx= DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion établie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
                
    }
    public Connection getCnx() {
        return cnx;
    }
    
    
    public static MyConnection getInstance() {
        if( instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    
}
