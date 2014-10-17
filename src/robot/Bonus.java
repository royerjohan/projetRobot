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
public abstract class Bonus {
    private Point2D position;
    
    public Bonus(){
        this.position = new Point2D(0,0);
    }
    public Bonus(Point2D position){
        this.position=position;
    }
    
    public Bonus(int abs, int ord){
       this.position=new Point2D(abs,ord);
    }
    
    public Point2D getPosition(){
        return this.position;
    }
    
    public void setPosition(Point2D p){
        this.position=p;
    }
    public void setPostion(int abs, int ord){
        this.position=new Point2D(abs,ord);
    }
    public abstract void setJauge(int jauge);
    public abstract int getJauge();
}
