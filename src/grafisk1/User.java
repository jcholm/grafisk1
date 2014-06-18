/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;


import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author johancholmberg
 */
public class User {
    int poang;
    String namn;
    LinkedList fragLista = new LinkedList ();
    
    User(String namn){
        this.namn=namn;
    }
    
    public String getNamn ( ){
        return namn;
    }
    
    public void setPoang (int p){
        poang+=p;
    }
    
    public int getPoang (){
        return poang;
    } 
    
    public void setFraga (int id){
        fragLista.addFirst(id);
        if(fragLista.size()>29){
            fragLista.removeLast();
        }
    }
    
    public LinkedList getFraga (){
        return fragLista;
    }
    
}
