/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import javax.swing.JOptionPane;
/**
 *
 * @author boc
 */
public class Alerts {
    
    public static void infoBox(String infoMsg , String Title){
     JOptionPane.showMessageDialog(null,infoMsg, Title,JOptionPane.INFORMATION_MESSAGE);
    
    }
    
}


