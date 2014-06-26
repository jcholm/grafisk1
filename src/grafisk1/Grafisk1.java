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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author johancholmberg
 */
public class Grafisk1 extends JFrame {
    JButton klar, nyled;
    JLabel ledtrad, poang, aNamnLabel, totalPoang;
    JTextField svar;
    JPanel bild, center;
    Bild bilden=null;
    Ledtradar led;
    
    User U1;
    Grafisk1() throws SQLException{
        super("test");
        NamnForm form;
        do {
        form= new NamnForm();
        int svaret = JOptionPane.showConfirmDialog(Grafisk1.this, form,
	"Skriv in ditt namn", JOptionPane.DEFAULT_OPTION);
        U1 = new User(form.getNamn());} while(form.getNamn().isEmpty());
        
        JPanel north=new JPanel();
	add(north,BorderLayout.NORTH);
        led=new Ledtradar();
        led.getFraga(U1.getFragLista());
        ledtrad=new JLabel(led.nastaLed());
        nyled=new JButton("Ny ledtråd");
        nyled.addActionListener(new nyledLyss());
        poang = new JLabel(led.nastapoang());

        north.add(poang, BorderLayout.LINE_START);
        north.add(ledtrad);
        north.add(nyled);
        
       
        
        bilden = new Bild();//är en JPanel
        add(bilden, BorderLayout.CENTER);
        
        
        
        JPanel south=new JPanel(new BorderLayout());
        svar=new JTextField("Svar",10);
        klar=new JButton("Klar");
        aNamnLabel = new JLabel(U1.getNamn());
        String tP = Integer.toString(U1.getPoang());
        totalPoang = new JLabel("  Du har " +tP +" poäng ");
        add(south,BorderLayout.SOUTH);
        klar.addActionListener(new klarLyss());
        south.add(aNamnLabel,BorderLayout.WEST);
        JPanel southe = new JPanel(new BorderLayout());
        south.add(southe,BorderLayout.EAST);
        south.add(totalPoang,BorderLayout.CENTER);
        southe.add(svar,BorderLayout.WEST);
        southe.add(klar,BorderLayout.EAST);
        //south.setLayout(new FlowLayout(FlowLayout.LEFT));
       

        
        setVisible(true);
        //setLocation(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(600, 300);
        pack();
        
        
        
    }
    
    
    class nyledLyss implements ActionListener{
	public void actionPerformed (ActionEvent ave){
		ledtrad.setText(led.nastaLed());
                poang.setText(led.nastapoang());
		//System.out.println(led.svarP());
                
                
                }
	}
    class klarLyss implements ActionListener{
	public void actionPerformed (ActionEvent ave){
		//ledtrad.setText(led.nastaLed());
                //poang.setText(led.nastapoang());
		String answ = svar.getText().toLowerCase();
                if(led.ansComp(answ)){
                    U1.setPoang(led.svarP());
                    JOptionPane.showMessageDialog(Grafisk1.this,
                    "Rätt svar!");
                    try {
                        U1.setFraga(led.id);
                        led.getFraga(U1.getFragLista());
                        ledtrad.setText(led.nastaLed());
                        poang.setText(led.nastapoang());
                        svar.setText("");
                        totalPoang.setText(" Du har "+Integer.toString( +U1.getPoang()) +" poäng!");
                    } catch (SQLException ex) {
                        Logger.getLogger(Grafisk1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Rätt svar " + led.svarP() + " Poäng");
                } else {
                    System.out.println("Fel svar"); 
                    JOptionPane.showMessageDialog(Grafisk1.this,
                    "Fel svar.");
                }
                /*
                if (answ.equals(led.q_ans.toLowerCase())){
                System.out.println(led.svarP() + " poäng!");
                } else {
                    System.out.println("Wrong answer, correct answer is: " + led.q_ans);
                }
                */
                
                }
	}
    class NamnForm extends JPanel{
    private JTextField NamnFält= new JTextField(13);
    public NamnForm(){
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			

			JPanel rad1= new JPanel();
			JLabel NamnF= new JLabel("Namn");
			rad1.add(NamnF);
			rad1.add(NamnFält);
			add(rad1);

	

		}

		public String getNamn() {
			return NamnFält.getText();
		}
		
		
}
    
    public static void main(String[] args) throws SQLException {
        new Grafisk1();
        
    }
    
}
