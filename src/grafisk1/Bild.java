/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import com.jcraft.jsch.*;
/**
 *
 * @author johancholmberg
 */
public class Bild extends JPanel {
    private ImageIcon bild;
    
    
    Bild(){
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("joho3075", "127.0.0.1", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("eqxkudfe2U");
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            bild= new ImageIcon(sftpChannel.get("public_html/Bilder/world.png"));
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
        setLayout(null);
	//bild = new ImageIcon("world.png");
	setPreferredSize(new Dimension(300,300));
    }
    //bild.getIconWidth(), bild.getIconHeight()
    
    protected void paintComponent(Graphics g){
	        super.paintComponent(g);
	            g.drawImage(bild.getImage(), 0, 0, this);
	            //kallas på vid add bildcwde2
	           
	       
	    }

}
