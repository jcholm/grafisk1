/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.*;
import java.awt.LayoutManager;

import javax.swing.*;
/**
 *
 * @author johancholmberg
 */
public class Grafisk1 extends JFrame {
    JButton klar;
    JButton nyled;
    JLabel ledtrad;
    JTextField svar;
    JPanel bild;
    JPanel center;
    
    Grafisk1(){
        super("test");
        JPanel north=new JPanel();
	add(north,BorderLayout.NORTH);
        
        ledtrad=new JLabel("ledtråd");
        nyled=new JButton("Ny ledtråd");
        north.add(ledtrad);
        north.add(nyled);
        
        center= new JPanel();
        ImageIcon image = new ImageIcon("image.jpeg");
        center.add(image, BorderLayout.CENTER);
        
       
        
        
        
        JPanel south=new JPanel();
        svar=new JTextField("Svar",10);
        klar=new JButton("Klar");
	add(south,BorderLayout.SOUTH);
        
        south.add(svar);
        south.add(klar);
        
        setVisible(true);
        setLocation(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        
    }
    
    public static void main(String[] args) {
        new Grafisk1();
        
    }
    
}
