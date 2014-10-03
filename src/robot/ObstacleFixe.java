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
public class ObstacleFixe extends Obstacle {

    public ObstacleFixe(Point2D position) {
        super(position);
    }
    public ObstacleFixe(int abs,int ord){
        super(abs,ord);
    }
    
    public String toString(){
        return("Je suis un Obstacle Fixe, je suis en position ["+this.getPosition().getAbscisse()+","+this.getPosition().getOrdonne()+"]");
    }
}
