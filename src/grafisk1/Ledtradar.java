/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johancholmberg
 */
public class Ledtradar {
  ArrayList <String> ledLista = new ArrayList <String> ();
  
  String q_answer = "default";
  int ledCount=0;
  int index;
  int ledLap = 0;
  int points = 10;
  int id = 1;
  String[] ledPoint = {"10","8","6","4","2"};
  String[] svarsalt;
    Ledtradar(){
    try{
        String host = "jdbc:mysql://atlas.dsv.su.se:3306/db_14534252";
        String driver = "com.mysql.jbdc.Driver";
        String uname = "usr_14534252";
        String pwd = "534252";
        Connection con = DriverManager.getConnection(host, uname, pwd);
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM fragor WHERE id=" + id;
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int id_col = rs.getInt("id");
        String q_Led10 = rs.getString("led10");
        String q_Led8 = rs.getString("led8");
        String q_Led6 = rs.getString("led6");
        String q_Led4 = rs.getString("led4");
        String q_Led2 = rs.getString("led2");
        q_answer = rs.getString("svar");
        svarsalt = q_answer.split("\\s*,\\s*");
        ledLista.add(q_Led10);
        ledLista.add(q_Led8);
        ledLista.add(q_Led6);
        ledLista.add(q_Led4);
        ledLista.add(q_Led2);
        }
        catch (SQLException err) {
            System.out.println(err.getMessage());
        } 
    }
         
    
 public String nastaLed(){
     if(ledCount>4) {ledCount = 0; this.ledLap = 1;}
     this.index = ledCount;
     ledCount++;
     return ledLista.get(index);
    
 }
 public String nastapoang(){
     if(ledLap == 0){
         this.points = 10-(2*index);
         return ledPoint[index] + " poäng: ";
     } else {
         this.points = 2;
         return "2 poäng: ";
     }
 }
 public Integer svarP(){
     return this.points;
            
 }
 public boolean ansComp(String poss_ans){
     boolean res_Comp = false;
     for(String q_ans:svarsalt) {
         if(q_ans.toLowerCase().equals(poss_ans.toLowerCase())) {
             res_Comp = true;
             break;
         }
         
     }
     return res_Comp;
 }

}
