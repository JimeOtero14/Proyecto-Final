/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.basededatos;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConectar {
    Connection SQLconexion;
    
    public SQLConectar(){
        String host = "localhost";
        String puerto = "3306";
        String nameBD = "trabajadores_tecnm";
        
        String usuario = "root";
        String pass = "";
        
        String driver = "com.mysql.cj.jdbc.driver";
        
        String databaseURL = "jdbc:mysql://"+host+":"+puerto+"/"+nameBD+"?useSSL=false";
        
        try{
            Class.forName(driver);
            SQLconexion = DriverManager.getConnection(databaseURL,usuario,pass);
        }catch(Exception ex){
            
        }
    }
    
    public Connection getConectarBD(){
        return SQLconexion;
    }
}
