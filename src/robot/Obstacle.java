/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author johan
 */
public class Obstacle {
    private Point2D position;
    private String ressourcePath;
    private String iconName;
    private ImageIcon icon;
    
    public Obstacle(){
        this.position= new Point2D(0,0);
        this.ressourcePath="/ressources/";
        this.iconName="obstacle.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
    }
    
    public Obstacle(Point2D position){
        this.position=position;
        this.ressourcePath="/ressources/";
        this.iconName="obstacle.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
    }
    
    public Obstacle(int abs, int ord){
       this.position=new Point2D(abs,ord);
       this.ressourcePath="/ressources/";
       this.iconName="obstacle.png";
       this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
    }
    
    private ImageIcon createImageIcon(String path, String description){
       URL imgURL;
       ImageIcon res;
       imgURL = this.getClass().getResource(path);
       if (imgURL != null){
           res = new ImageIcon(imgURL, description);
           //on redimensionne l'image pour qu'elle fasse 40x40 pixels
           //ATTENTION A CES CONSTANTES !!!!!
           Image newimg=res.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_FAST);
           res = new ImageIcon( newimg);
       } else {
           System.err.println("Couldn't find file: "+ path);
           return null;
       }
       return res;
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
