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
    
    public void SetPoang (int p){
        poang+=p;
    }
    
    public int GetPoang (){
        return poang;
    } 
    
    public void SetFraga (int id){
        fragLista.addFirst(id);
        if(fragLista.size()>29){
            fragLista.removeLast();
        }
    }
    
    public LinkedList GetFraga (){
        return fragLista;
    }
    
}
