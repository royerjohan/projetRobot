/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Johan
 */
public class SauvegarderPartie {
    private String nom;
    private BufferedWriter buffer;
    
    public SauvegarderPartie(String nom){
        this.nom=nom;
    }
    
    public void sauvegarderPartie(PlateauJeu pj) throws IOException{
        this.buffer= new BufferedWriter(new FileWriter(this.nom));
        // on écrit la largeur et la hauteur
        buffer.write("Largeur "+pj.getLargeur());
        buffer.newLine();
        buffer.write("Hauteur "+pj.getHauteur());
        // on parcourt ensuite les 3 listes du plateau de jeu
        for (Robot r: pj.getListeRobot()){
            buffer.newLine();
            buffer.write(r.getClass().getSimpleName()+" "+r.getName()+" "+r.getPosition().getAbscisse()+
                    " "+r.getPosition().getOrdonne()+" "+r.getPtVie()+" "+r.getEnergie());
        }
        for (Bonus b: pj.getListeBonus()){
            buffer.newLine();
            buffer.write(b.getClass().getSimpleName()+" "+b.getPosition().getAbscisse()+
                    " "+b.getPosition().getOrdonne()+" "+b.getJauge());
        }
        for (Obstacle o: pj.getListeObstacle()){
            buffer.newLine();
            buffer.write(o.getClass().getSimpleName()+" "+o.getPosition().getAbscisse()+
                    " "+o.getPosition().getOrdonne());
        }
        buffer.flush();
        buffer.close();
    }
}
