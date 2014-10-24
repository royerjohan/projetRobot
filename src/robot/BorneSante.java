/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import java.awt.Image;
import static java.lang.Math.min;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author johan
 */
public class BorneSante extends Bonus {
    private int reserveSante;
    private String ressourcePath;
    private String iconName;
    private ImageIcon icon;
    final int BORNE_DEFAUT_SANTE=50;
    
   public BorneSante(){
       super();
       this.ressourcePath="/ressources/";
       this.iconName="powerup.png";
       this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
       this.reserveSante=BORNE_DEFAUT_SANTE;
   }
    public BorneSante(Point2D position) {
         super(position);
         this.ressourcePath="/ressources/";
         this.iconName="powerup.png";
         this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveSante=BORNE_DEFAUT_SANTE;
    }
    
    public BorneSante(int abs,int ord){
        super(abs,ord);
        this.ressourcePath="/ressources/";
        this.iconName="powerup.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveSante=BORNE_DEFAUT_SANTE;
    }
    
    public BorneSante(Point2D position, int sante) {
        super(position);
        this.ressourcePath="/ressources/";
        this.iconName="powerup.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveSante=sante;
    }
    
    public BorneSante(int abs,int ord, int sante){
        super(abs,ord);
        this.ressourcePath="/ressources/";
        this.iconName="powerup.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveSante=sante;
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
    
    public int getSante(){
        return this.reserveSante;
    }
    
    public void setSante(int ptvie){
        this.reserveSante=ptvie;
    }
    
   
   /**
    *      * transfere la sante donnee au robot, si le robot a besoin de moins 
     * de sante il ne recoit que la sante necessaire pour atteindre sa valeur max.
     * Si la borne n'a pas autant de sante alors le robot ne recoit que la sante restante
     * dans la borne
    * @param sante
    * @param r 
    */ 
    public void transfertSante(int sante, Robot r){
        // on regarde si le besoin n'a pas besoin d'autant d'energie pour atteindre son max
        int santetransfere=min(r.ROBOT_MAX_SANTE-r.getPtVie(),sante);
        // on verifie si la borne a suffisement d'energie, si non elle lui donne tout ce qu'il lui
       //reste et tombe a 0.
        if (this.reserveSante>=santetransfere){ 
                this.reserveSante-=santetransfere;
                r.reparer(santetransfere);
        }else {
            r.reparer(this.reserveSante);
            this.reserveSante=0;
        }
    }
    
    public String toString(){
        return ("Je suis une Borne de sante, il me reste "+this.getSante()+" points d'énergie, je suis en position ["+this.getPosition().getAbscisse()+","+this.getPosition().getOrdonne()+"]");
    }

    
    public void setJauge(int jauge) {
        this.reserveSante=jauge;
    }
    
    public int getJauge(){
        return this.reserveSante;
    }
}
