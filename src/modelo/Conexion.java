/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author rramos
 */
public class Conexion {
    Connection con;
   public Connection getConnection(){
       String url="jdbc:mysql://localhost:3306/bd_demo_java";
       String user="root";
       String pass="";
       try{
       Class.forName("com.mysql.jdbc.Driver");
       con=DriverManager.getConnection(url, user, pass);
       }catch(Exception e){
       }
   return con;
   
   }
   
}
