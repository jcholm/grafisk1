package grafisk1;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class socket {
    public static void socket(){
        boolean status = true;
        if(status){ 
            serverSocket();
        } else{
            clientSocket();
        }
    }
    
    public static void serverSocket() {
        Socket smtpSocket = null;  
        DataOutputStream os = null;
        DataInputStream is = null;
// Initialization section:
// Try to open a socket on port 25
// Try to open input and output streams
        try {
            smtpSocket = new Socket("Name",2525);
            os = new DataOutputStream(smtpSocket.getOutputStream());
            is = new DataInputStream(smtpSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: hostname");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: hostname");
        }
// If everything has been initialized then we want to write some data
// to the socket we have opened a connection to on port 25
    if (smtpSocket != null && os != null && is != null) {
            try {
// The capital string before each colon has a special meaning to SMTP
// you may want to read the SMTP specification, RFC1822/3
                os.close();
                is.close();
                smtpSocket.close();   
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }           


    public static void clientSocket() {
        ServerSocket echoServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;
// Try to open a server socket on port 9999
// Note that we can't choose a port less than 1023 if we are not
// privileged users (root)
        try {
           echoServer = new ServerSocket(2525);
        }
        catch (IOException e) {
           System.out.println(e);
        }   
// Create a socket object from the ServerSocket to listen and accept 
// connections.
// Open input and output streams
    try {
           clientSocket = echoServer.accept();
           is = new DataInputStream(clientSocket.getInputStream());
           os = new PrintStream(clientSocket.getOutputStream());
// As long as we receive data, echo that data back to the client.
           while (true) {
             line = is.readLine();
             os.println(line); 
           }
        }   
    catch (IOException e) {
           System.out.println(e);
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
