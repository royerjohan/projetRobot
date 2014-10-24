/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author johan
 */
public class ProjetRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ChargementPartie c= new ChargementPartie("Test.txt");
        SauvegarderPartie s= new SauvegarderPartie("Partie.txt");
        try {
            PlateauJeu pj= c.chargerpartie();
            pj.partie();
            s.sauvegarderPartie(pj);
        } catch (IOException ex) {
            Logger.getLogger(ProjetRobot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjetRobot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProjetRobot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProjetRobot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }
    
    
}
