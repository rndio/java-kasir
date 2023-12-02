

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author MrSimamora
 */
public class main {
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( UnsupportedLookAndFeelException e ) {
            System.err.println(e );
        }
        
        try{
            login form = new login();
            form.setVisible(true);
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    
    
}
