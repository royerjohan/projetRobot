/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author johan
 */
public class ObstacleMobile extends Obstacle implements Deplacement{

    public ObstacleMobile(Point2D position) {
        super(position);
    }
    public ObstacleMobile(int abs,int ord){
        super(abs,ord);
    }
    
    public void deplaceObstacle(Point2D p){
        this.setPosition(p);
    }
    public void deplaceObstacle(int abs,int ord){
        this.setPosition(abs, ord);
    }
    
    public String toString(){
        return("Je suis un Obstacle Mobile, je suis en position ["+this.getPosition().getAbscisse()+","+this.getPosition().getOrdonne()+"]");
    }
    
    public void bouge(PlateauJeu p){
        ArrayList<Point2D> cases= p.casesLibresAutourDe(this.getPosition());
        //retire les points en diagonale pour n'autoriser que les déplacements horizontaux et verticaux
        int i=0;
            while(i<cases.size()){
                if(!(cases.get(i).getAbscisse()==this.getPosition().getAbscisse()) && !(cases.get(i).getOrdonne()==this.getPosition().getOrdonne())){
                cases.remove(i);
                }else i=i+1;
            }
       Random direction = new Random();
       Point2D caseFinale= new Point2D(cases.get(direction.nextInt(cases.size())));
       this.deplaceObstacle(caseFinale.getAbscisse(), caseFinale.getOrdonne());
    }
    
}
