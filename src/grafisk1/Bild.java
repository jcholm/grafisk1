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
    private ImageIcon bild;
    BufferedImage bilder;
    
    Bild() throws IOException{
        System.out.println("Not connected yet");
        JSch jsch = new JSch();
        Session session = null;
        InputStream out = null;
        
        try {
            session = jsch.getSession("joho3075", "sftp.dsv.se.se", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("eqxkudfe2U");
            session.connect();
            System.out.println("Connection successfull");

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            out = sftpChannel.get("public_html/Bilder/world.png");
            sftpChannel.exit();
            session.disconnect();
            bilder = ImageIO.read(out);
        } catch (JSchException | SftpException e) {
        }
        setLayout(null);
	bild = new ImageIcon(bilder);
	setPreferredSize(new Dimension(300,300));
    }
    //bild.getIconWidth(), bild.getIconHeight()
    
    @Override
    protected void paintComponent(Graphics g){
	        super.paintComponent(g);
	            g.drawImage(bild.getImage(), 0, 0, this);
	            //kallas på vid add bildcwde2
	           
	       
	    }

}
