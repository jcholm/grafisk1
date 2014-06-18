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
        String host = "jdbc:derby://localhost:1527/grafisk";
        String uname = "inan";
        String pwd = "admin";
        Connection con = DriverManager.getConnection(host, uname, pwd);
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM QUESTION WHERE ID=" + id;
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int id_col = rs.getInt("ID");
        String q_Led10 = rs.getString("LED1");
        String q_Led8 = rs.getString("LED2");
        String q_Led6 = rs.getString("LED3");
        String q_Led4 = rs.getString("LED4");
        String q_Led2 = rs.getString("LED5");
        q_answer = rs.getString("SVAR");
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
