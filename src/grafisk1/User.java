/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johancholmberg
 */
public class User {
    int poang,size;
    String namn,oppName;
    LinkedList fragLista = new LinkedList ();
    String oppIp,iplocal;
    DbAnslutning dbansl;
    Statement stmt;
    ResultSet res;
    User(String namn){
        this.namn=namn;
    }
    
    public String getNamn ( ){
        return namn;
    }
    
    public void setPoang (int p){
        poang+=p;
    }
    
    public int getPoang (){
        return poang;
    } 
    
    public void setFraga (int id){
        this.fragLista.addFirst(id);
        if(fragLista.size()==4){
            this.fragLista.removeLast();
        }
    }
    
    public LinkedList getFragLista (){
        return fragLista;
    }
    public String checkOpp(){
        dbansl = new DbAnslutning();
        try {
            stmt = dbansl.con.createStatement();
            res = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE motspelare=0");
            res.next();
            size = res.getInt(1);
            if(size>0){
                res = stmt.executeQuery("SELECT id FROM User WHERE motspelare=0");
                res.next();
                oppIp = res.getString("id");
                oppName = res.getString("namn");
            }else{
                oppName = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         
        return oppName;
    }
    
    public boolean uploadId(){
        
        String insert = "INSERT INTO User (namn,motspelare,poäng)" +
        "VALUES ("+namn+",1,"+poang+")";
        try{
           stmt.executeQuery(insert);
           return true;
        }
        catch(SQLException e){
            return false;
        }
    }
    public void updateDb(){
        String updt = "UPDATE User SET poäng=" + poang + "WHERE id=" +oppIp;
        try{
           stmt.executeQuery(updt);
        }
        catch(SQLException e){
        }
    }
    
}
/*URL whatismyip = new URL("http://checkip.dyndns.org/");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String
        String semiColon=":";
        String slashB="</b";
        int small=ip.indexOf(semiColon);
        int big =ip.indexOf(slashB);
        
        String ip3;
        ip3=ip.substring(small+2, big-1);
        stmt = dbansl.con.createStatement();
        iplocal = InetAddress.getLocalHost().getHostAddress();
        */