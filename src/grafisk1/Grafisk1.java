/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafisk1;

import com.jcraft.jsch.SftpException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author johancholmberg
 */
public class Grafisk1 extends JFrame {
    String ledtr;
    JButton klar, nyled;
    static JLabel ledtrad, poang, aNamnLabel, opponentName;
    JTextField svar;
    JRadioButton single,multi;
    JPanel bild, center;
    Bild bilden = null;
    Ledtradar led;
    Grafisk1 loader;
    LinkedList qList = new LinkedList();
    User U1;
    SwingWorker work;
    ImageIcon img;
    boolean singleGame;
    String opponentId,opponentNamn="";
    int opponentPoints;
    
    public synchronized void startGameGui() throws IOException, SQLException, SftpException, InterruptedException{
        loader = new Grafisk1();
        bilden = new Bild();
        led = new Ledtradar();
        leads();
        //Prompt player for name
        NamnForm form;
        do {
            form = new NamnForm();
            int svaret = JOptionPane.showConfirmDialog(Grafisk1.this, form,
                    "Skriv in ditt namn", JOptionPane.DEFAULT_OPTION);
            this.U1 = new User(form.getNamn());
        } while (form.getNamn().isEmpty());
        if(single.isSelected()){
            singleGame=true;
        }else{
            singleGame=false;
            multiplayer();
        }
        bilden.bytbild(led.bildNamn());
        //Create Labels for leads and points and help button
        JPanel north = new JPanel();
        add(north, BorderLayout.NORTH);
        System.out.println("Connected to database");

        ledtrad = new JLabel(ledtr);
        nyled = new JButton("Ny ledtråd");
        nyled.addActionListener(new nyledLyss());
        poang = new JLabel(led.nastapoang());

        north.add(poang, BorderLayout.LINE_START);
        north.add(ledtrad);
        north.add(nyled);
        
        //Add center image
        add(bilden, BorderLayout.CENTER);
        
        // Add Labels for points and name, 
        //textfield for answer and answer button
        JPanel south = new JPanel(new GridLayout(1,3));
        
        svar = new JTextField("Svar", 15);
        klar = new JButton("Klar");
        
        String tP = Integer.toString(U1.getPoang());
        aNamnLabel = new JLabel(U1.getNamn() + " " + tP + " poäng ");        
        opponentName = new JLabel();
        
        add(south, BorderLayout.SOUTH);
        
        klar.addActionListener(new klarLyss());
        JPanel southe = new JPanel(new BorderLayout());
        south.add(aNamnLabel);
        south.add(southe);            
        south.add(opponentName);
        opponentName.setText(opponentNamn);
        svar.selectAll();
        
           
        southe.add(svar, BorderLayout.CENTER);
        southe.add(klar, BorderLayout.EAST);
        southe.getRootPane().setDefaultButton(klar);

        setVisible(true);
        //setLocation(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setSize(600, 300);
        pack();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e){
                svar.requestFocus();
                svar.selectAll();
            }
        });
        update();
        work = new imgch();
        work.execute();
    }
        public synchronized void leads(){
            startThread();
        try {
            led.loadFraga(qList);
            led.getFraga();
            ledtr = led.nastaLed();
        } catch (SQLException ex) {
            Logger.getLogger(Grafisk1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        // Update leads and image
        public void update(){
            try{
                U1.setFraga(led.id);
                System.out.println("getfraga");
                led.loadFraga(U1.getFragLista());                
                //bilden.setBild(bilden.getBild(led.bildNamn()));
                
            }catch (SQLException ex) {
                    Logger.getLogger(Grafisk1.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        
        public void multiplayer() throws InterruptedException, SQLException{
            U1.removeDb();
            U1.uploadId();
            opponentNamn=U1.checkOpp(U1.namn);
            
            while(true){
                if(opponentNamn==null){
                    Thread.sleep(500);
                    opponentNamn=U1.checkOpp(U1.namn);
                }else{
                    break;
                }
                
            }
            
            
            
        }
//Display leads and image
        public void nextQ(){
            led.getFraga();
            bilden.setBild(img);
            System.out.println("setText");
            ledtrad.setText(led.nastaLed());
            System.out.println("setPoang");
            poang.setText(led.nastapoang());
            
            svar.setText("");
            aNamnLabel.setText(U1.getNamn() + " " + Integer.toString(+U1.getPoang()) + " poäng ");
        }
        //Download image in the background
        class imgch extends SwingWorker<ImageIcon, Void>{    
            @Override
            public ImageIcon doInBackground() {
                img=null;
                img = bilden.getBild(led.bildNamn());
                return img;
            }
            @Override
            public void done(){
                try {
                    img = get();
                    
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(Grafisk1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        //Start Thread to open channel to download image
    private synchronized void startThread(){    
        Thread t = new Thread(bilden);
        t.start();}

    class nyledLyss implements ActionListener {

        public void actionPerformed(ActionEvent ave) {
            ledtrad.setText(led.nastaLed());
            poang.setText(led.nastapoang());
		//System.out.println(led.svarP());

        }
    }

    class klarLyss implements ActionListener {

        public void actionPerformed(ActionEvent ave) {
		//ledtrad.setText(led.nastaLed());
            //poang.setText(led.nastapoang());
            String answ = svar.getText().toLowerCase();
            if (led.ansComp(answ)) {
                U1.setPoang(led.svarP());
                JOptionPane.showMessageDialog(Grafisk1.this,
                        "Rätt svar!");
                while(true){
                    if(work.isDone()){
                        
                        System.out.println("Worker done");
                        nextQ();
                        validate();
                        repaint();
                        update();
                        work = new imgch();
                        work.execute();
                        
                        break;
                    } else{
                        System.out.println("Worker not done");
                    }
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

    class NamnForm extends JPanel {

        private final JTextField NamnFält = new JTextField(13);
        

        public NamnForm() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel spelTyp = new JLabel("Ange speltyp");
            ButtonGroup radio = new ButtonGroup();
            single = new JRadioButton("Single player");
            multi = new JRadioButton("Multi Player");
            multi.setSelected(true);
            radio.add(single);
            radio.add(multi);
            JPanel rad0 = new JPanel();
            JPanel rad1 = new JPanel();
            JPanel rad2 = new JPanel();
            JLabel NamnF = new JLabel("Namn");
            rad2.add(spelTyp);
            rad0.add(multi);
            rad0.add(single);
            rad1.add(NamnF);
            rad1.add(NamnFält);
            add(rad2);
            add(rad0);
            add(rad1);

        }

        public String getNamn() {
            return NamnFält.getText();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        
        
        SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            Grafisk1 game = new Grafisk1();
            try {
                game.startGameGui();
            } catch (IOException | SQLException | SftpException | InterruptedException ex) {
                Logger.getLogger(Grafisk1.class.getName()).log(Level.SEVERE, null, ex);
            }
            game.setVisible(true);
      }
    });

    }

}
