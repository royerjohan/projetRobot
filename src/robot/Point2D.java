/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

/**
 *Classe Point2D qui permet de créer des points dans un dans un repère 2D
 * en fonction de leurs abscisse et de leur ordonnée
 * @author johan
 */
public class Point2D {
    
    /*
    abscisse et ordonnées sont les 2 coordonnées du point
    */
    private int abscisse;
    private int ordonnee;
    
   /******
    * Constructeur par défault, le point se trouve à l'origine.
    */
    public Point2D(){
        abscisse=0;
        ordonnee=0;
    }
    /****
     * Constructeur permettant de créer le point avec l'absisse x et l'ordonnée y
     * @param x
     * @param y 
     */
    public Point2D(int x, int y){
        this.abscisse=x;
        this.ordonnee=y;
    }
/****
 * Copie un second point aux mêmes coordonnées que le premier prit en paramètre
 * @param p 
 */
    public Point2D(Point2D p){
        this.abscisse=p.abscisse;
        this.ordonnee=p.ordonnee;
    }
    /****
     * renvoie l'abscisse du point sélectionné
     * @return 
     */
    public int getAbscisse(){
        int abs= abscisse;
        return abs;
    }
    
    /*****
     * renvoie l'ordonnée du point sélectionné
     * @return 
     */
    public int getOrdonne(){
        int ord= ordonnee;
        return ord;
    }
    
    /*****
     * change l'abscisse du point sélectionné à la valeur abs
     * @param abs 
     */
    public void setAbscisse(int abs){
        this.abscisse=abs;
    }
    /***
     * change l'ordonne du point sélectionné à la valeur ord
     * @param ord 
     */
    public void setOrdonne(int ord){
        this.ordonnee=ord;
    }
    /***********
     * modifie la position du point sélectionné aux coordonnées (abs,ord)
     * @param abs
     * @param ord 
     */
    public void modifPosition(int abs, int ord){
       this.setAbscisse(abs);
       this.setOrdonne(ord);
    }
   /************
    * affiche l'abs et l'ord du point sélectionné
    */
    public void afficherPoint(){
        int abs= this.getAbscisse();
        int ord=this.getOrdonne();
        System.out.println("les coordonnees du point sont "+abs+","+ord);
    }
    /******
     * compare le point sélectionné et le point prit en paramètre, s'ils ont la meme abscisse
     * et la meme ordonné renvoie vraie sinon renvoie faux.
     * @param p
     * @return 
     */
    public boolean compare(Point2D p){
       int abs=p.getAbscisse();
       int ord=p.getOrdonne();
        boolean b= abs==this.abscisse && ord== this.ordonnee;
        return b;
    }
    
}
