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
        String host = "jdbc:mysql://atlas.dsv.su.se:3306/db_14534252";
        String driver = "com.mysql.jbdc.Driver";
        String uname = "usr_14534252";
        String pwd = "534252";
        con = DriverManager.getConnection(host, uname, pwd);
        
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
        } 
    }
}
