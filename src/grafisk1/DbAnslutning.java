/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author johancholmberg
 */
public class DbAnslutning {
    
    Connection con;
    
    DbAnslutning(){
        try{
        String host = "sql3.freemysqlhosting.net";
        String driver = "com.mysql.jbdc.Driver";
        String uname = "sql346864";
        String pwd = "mM2!gX5%";
        con = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net/sql346864", uname, pwd);
        
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
        } 
    }
}
