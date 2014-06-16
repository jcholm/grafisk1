/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.*;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    Bild bilden=null;
    Ledtradar led;
    Grafisk1(){
        super("test");
        JPanel north=new JPanel();
	add(north,BorderLayout.NORTH);
        led=new Ledtradar();
        ledtrad=new JLabel(led.nastaLed());
        nyled=new JButton("Ny ledtråd");
        nyled.addActionListener(new nyledLyss());

        north.add(ledtrad);
        north.add(nyled);
        
       
        
        bilden = new Bild();//är en JPanel
        add(bilden, BorderLayout.CENTER);
        
        
        
        JPanel south=new JPanel();
        svar=new JTextField("Svar",10);
        klar=new JButton("Klar");
	add(south,BorderLayout.SOUTH);
        
        south.add(svar);
        south.add(klar);
        
        setVisible(true);
        setLocation(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(700, 400);
        pack();
        
        
        
    }
    
    
    class nyledLyss implements ActionListener{
	public void actionPerformed (ActionEvent ave){
		ledtrad.setText(led.nastaLed());
                ledtrad.setText(led.nastapoang());
		
                
                
                }
	}
    
    
    
    public static void main(String[] args) {
        new Grafisk1();
        
    }
    
}
