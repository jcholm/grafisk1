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

/**
 *
 * @author johancholmberg
 */
public class Bild extends JPanel {
    private ImageIcon bild;
    
    
    Bild(){
        setLayout(null);
	bild = new ImageIcon("world.png");
	setPreferredSize(new Dimension(bild.getIconWidth(), bild.getIconHeight()));
    }
    
    
    protected void paintComponent(Graphics g){
	        super.paintComponent(g);
	            g.drawImage(bild.getImage(), 0, 0, this);
	            //kallas på vid add bildcwde2
	           
	       
	    }

}
