/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;

import java.util.ArrayList;

/**
 *
 * @author johancholmberg
 */
public class Ledtradar {
  ArrayList <String> ledLista = new ArrayList <String> ();
  
  String led10="Detta land självständighet deklarerades 1776";
  String led8="Detta land är det fjärde största till ytan";
  String led6="Detta land är det tredje största till total befolkning";
  String led4="En känd pereson från detta land är Michael Phelps";
  String led2= "Huvudstaden i detta land är Washington DC";
  String answer = "USA";
  int ledCount=0;
  int index;
  int ledLap = 0;
  int points = 10;
  String[] ledPoint = {"10","8","6","4","2"};
    Ledtradar(){
    ledLista.add(led10);
    ledLista.add(led8);
    ledLista.add(led6);
    ledLista.add(led4);
    ledLista.add(led2);
    
    
}
 
 public String nastaLed(){
     if(ledCount>4) {ledCount = 0; this.ledLap = 1;}
     this.index = ledCount;
     ledCount++;
     return ledLista.get(index);
    
 }
 public String nastapoang(){
     if(ledLap == 0){
         this.points = 10-(2*index);
         return ledPoint[index] + " poäng: ";
     } else {
         this.points = 2;
         return "2 poäng: ";
     }
 }
 public Integer svarP(){
     return this.points;
            
 }

}
