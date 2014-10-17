/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import static java.lang.Math.min;

/**
 *
 * @author johan
 */
public class BorneSante extends Bonus {
    private int reserveSante;
    final int BORNE_DEFAUT_SANTE=50;
    
   public BorneSante(){
       super();
       this.reserveSante=BORNE_DEFAUT_SANTE;
   }
    public BorneSante(Point2D position) {
         super(position);
        reserveSante=BORNE_DEFAUT_SANTE;
    }
    
    public BorneSante(int abs,int ord){
        super(abs,ord);
        reserveSante=BORNE_DEFAUT_SANTE;
    }
    
    public BorneSante(Point2D position, int sante) {
        super(position);
        reserveSante=sante;
    }
    
    public BorneSante(int abs,int ord, int sante){
        super(abs,ord);
        reserveSante=sante;
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
