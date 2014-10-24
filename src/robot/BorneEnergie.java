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
public class BorneEnergie extends Bonus{
    private int reserveEnergie;
    private String ressourcePath;
    private String iconName;
    private ImageIcon icon;
    final int BORNE_DEFAUT_ENERGIE=50;
    
   public BorneEnergie(){
       super();
       this.reserveEnergie=BORNE_DEFAUT_ENERGIE;
   }
    public BorneEnergie(Point2D position) {
         super(position);
         this.ressourcePath="/ressources/";
         this.iconName="energy.png";
         this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);

        reserveEnergie=BORNE_DEFAUT_ENERGIE;
    }
    
    public BorneEnergie(int abs,int ord){
        super(abs,ord);
        this.ressourcePath="/ressources/";
        this.iconName="energy.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveEnergie=BORNE_DEFAUT_ENERGIE;
    }
    
    public BorneEnergie(Point2D position, int energie) {
        super(position);
        this.ressourcePath="/ressources/";
        this.iconName="energy.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveEnergie=energie;
    }
    
    public BorneEnergie(int abs,int ord, int energie){
        super(abs,ord);
        this.ressourcePath="/ressources/";
        this.iconName="energy.png";
        this.icon=this.createImageIcon(ressourcePath+iconName, ressourcePath);
        reserveEnergie=energie;
    }
    
    public int getEnergie(){
        return this.reserveEnergie;
    }
    
    public void setEnergie(int e){
        this.reserveEnergie=e;
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
    /**
     * transfere l'energie donnee au robot, si le robot a besoin de moins 
     * d'energie il ne recoit que l'energie necessaire pour atteindre sa valeur max.
     * Si la borne n'a pas autant d'energie alors le robot ne recoit que l'energie restante
     * dans la borne
     * @param energie
     * @param r 
     */
    public void transfertEnergie(int energie, Robot r){
        // on regarde si le besoin n'a pas besoin d'autant d'energie pour atteindre son max
        int energietransfere=min(r.ROBOT_MAX_ENERGIE-r.getEnergie(),energie);
       // on verifie si la borne a suffisement d'energie, si non elle lui donne tout ce qu'il lui
       //reste et tombe a 0.
        if (this.reserveEnergie>=energietransfere){ 
                this.reserveEnergie-=energietransfere;
                r.recharger(energietransfere);
        }else {
            r.recharger(this.reserveEnergie);
            this.reserveEnergie=0;
        }
    }
    
    public String toString(){
        return ("Je suis une Borne d'énergie, il me reste "+this.getEnergie()+" points d'énergie, je suis en position ["+this.getPosition().getAbscisse()+","+this.getPosition().getOrdonne()+"]");
    }

    
    public void setJauge(int jauge) {
        this.reserveEnergie=jauge;
    }
    
    public int getJauge(){
        return this.reserveEnergie;
    }
}
