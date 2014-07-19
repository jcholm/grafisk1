/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;

import com.jcraft.jsch.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author johancholmberg
 */
public class Bild extends JPanel implements Runnable {
    ImageIcon bild;
    BufferedImage bildbuffer;
    volatile ChannelSftp sftpChannel;
    InputStream out;
    Session session;
    JSch jsch = new JSch();
    Bild() throws IOException{
             


    }
    public synchronized void run(){
        try {
            session = jsch.getSession("joho3075", "sftp.dsv.su.se", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("eqxkudfe2U");
            session.connect();
            System.out.println("Connection successfull");

            Channel channel = session.openChannel("sftp");
            System.out.println("session.openChannel done");
            channel.connect();
            System.out.println("Channel connected");
            this.sftpChannel = (ChannelSftp) channel;
            System.out.println("Channel done");
        } catch (JSchException e) {
        }
    }
    //bild.getIconWidth(), bild.getIconHeight()
    
    protected void paintComponent(Graphics g){
	        super.paintComponent(g);
	            g.drawImage(bild.getImage(), 0, 0, this);
	            //kallas på vid add bildcwde2
	           
	       
	    }
    
    public synchronized void bytbild(String namn) throws SftpException{
        System.out.println("Trying to find image " + namn);
        out = sftpChannel.get("public_html/Bilder/" + namn);
        System.out.println("Directory found");
        try {
            bildbuffer = ImageIO.read(out);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Bild.class.getName()).log(Level.SEVERE, null, ex);
        }
        setLayout(null);
	this.bild = new ImageIcon(bildbuffer);
	setPreferredSize(new Dimension(300,300));
    }
    public synchronized ImageIcon getBild(String namn){
        System.out.println("Trying to find image " + namn);
        try {
            out = sftpChannel.get("public_html/Bilder/" + namn);
            System.out.println("Directory found");
        
            bildbuffer = ImageIO.read(out);
        } catch (SftpException | IOException ex) {
            java.util.logging.Logger.getLogger(Bild.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ImageIcon(bildbuffer);
    }
    public synchronized void setBild(ImageIcon img){
        setLayout(null);
        this.bild = img;
        setPreferredSize(new Dimension(300,300));
    }
    public void closeCon(){
            this.sftpChannel.exit();
            this.session.disconnect();
    }

}
