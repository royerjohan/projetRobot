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
public class Obstacle {
    private Point2D position;
    
    public Obstacle(Point2D position){
        this.position=position;
    }
    
    public Obstacle(int abs, int ord){
       this.position=new Point2D(abs,ord);
    }
    
    public Point2D getPosition(){
        return this.position;
    }
    
    public void setPosition(Point2D position){
        this.position=position;
    }
    
    public void setPosition(int abs,int ord){
          this.position= new Point2D(abs,ord);
    }
}
