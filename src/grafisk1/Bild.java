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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author johancholmberg
 */
public class Bild extends JPanel {
    ImageIcon bild;
    BufferedImage bilder;
    
    Bild() throws IOException{
        System.out.println("Not connected yet");
        JSch jsch = new JSch();
        Session session;
        InputStream out;
        
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
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            System.out.println("Channel done");
            out = sftpChannel.get("public_html/Bilder/world.png");
            System.out.println("Directory found");
            bilder = ImageIO.read(out);
            //sftpChannel.exit();
            //session.disconnect();
        } catch (JSchException | SftpException e) {
        }
        setLayout(null);
	bild = new ImageIcon(bilder);
	setPreferredSize(new Dimension(300,300));
    }
    //bild.getIconWidth(), bild.getIconHeight()
    
    protected void paintComponent(Graphics g){
	        super.paintComponent(g);
	            g.drawImage(bild.getImage(), 0, 0, this);
	            //kallas på vid add bildcwde2
	           
	       
	    }

}
