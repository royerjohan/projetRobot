/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

/**
 *
 * @author johan
 */
public class RobotNeuneu extends Robot{
    public RobotNeuneu(){
        super();
    }
    
    public RobotNeuneu(String name, Point2D position, int energie, int ptvie){
         super(name,position,energie,ptvie);
     }
             
             
public RobotNeuneu(String name, Point2D position){
   super(name,position);
}

public String toString(){
    return ("Je m'appelle "+this.getName()+", je suis un robot de type Neuneu, je possede "+this.getEnergie()+
            " points d'énergie, "+this.getPtVie()+" points de vie et je me trouve en position "
            + "["+this.getPosition().getAbscisse()+","+this.getPosition().getOrdonne()+"].");
}
    
    
}
