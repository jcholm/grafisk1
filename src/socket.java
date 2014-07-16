import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class socket {
   public static void main(String args[]) {
      String data = "Toobie ornaught toobie";
      try {
         ServerSocket srvr = new ServerSocket(1234);
         Socket skt = srvr.accept();
         System.out.print("Server has connected!\n");
         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
         System.out.print("Sending string: '" + data + "'\n");
         out.print(data);
         out.close();
         skt.close();
         srvr.close();
      }
      catch(IOException e) {
         System.out.print("Whoops! It didn't work!\n");
      }
   }
   public boolean checkStat(){
       try{
           String host = "";
           String user = "";
           String pwd = "";
           Connection connect = DriverManager.getConnection(host,user,pwd);
           Statement stat = connect.createStatement();
           String opponents = "SELECT COUNT(*) FROM PLAYERS";
           
           String sql = "SELECT USERIP FROM PLAYERS WHERE PAIRED=false";
           ResultSet rsSet = stat.executeQuery(opponents);
           rsSet.next();
           if(rsSet.getInt(1)<1){
               
           }
       } catch (SQLException err) {
           System.out.println(err.getMessage());
       }
       return true;
   }
}
