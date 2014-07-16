/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package grafisk1;

import com.jcraft.jsch.SftpException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robin
 */
public class backGr {
    public void run() throws IOException, SftpException{
        try {
            Ledtradar ledT = new Ledtradar();
            ledT.getFraga(new LinkedList());
            Bild bilden = new Bild();
            bilden.bytbild(ledT.bildNamn());
            
        } catch (SQLException ex) {
            Logger.getLogger(backGr.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
