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
public class ProjetRobot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         Point2D p1=new Point2D();
        Point2D p2=new Point2D(3,2);
        Point2D p3=new Point2D(5,7);
        Point2D p4=new Point2D(4,4);
        RobotNeuneu r=new RobotNeuneu("Jean",p1);
        RobotCombattant r2= new RobotCombattant("Francis",p4);
        ObstacleMobile o= new ObstacleMobile(p2);
        BorneEnergie b= new BorneEnergie(p3);
        PlateauJeu p=new PlateauJeu(30,30);
        p.addRobot(r);
        p.addRobot(r2);
        p.addObstacle(o);
        p.addBonus(b);
        p.partie();
    }
    
    
}
