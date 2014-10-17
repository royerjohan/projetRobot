/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author johan
 */
public class Robot implements Deplacement {
    
   private String name;
   private Point2D position;
   private int energie;
   private int ptvie;
   /**
    * nbrobot est un entier qui s'incrémente à chaque création de robot,
    * il permet de savoir le nombre total de robots créés.
    */
   static int nbrobot=0;
   final int ROBOT_MAX_ENERGIE=150;
   final int ROBOT_MAX_SANTE=200;
   final int ROBOT_DEFAULT_ENERGIE=100;
   final int ROBOT_DEFAULT_SANTE=100;
   
   /**
    * Creation d'un robot par défaut, pas de nom, positionné à l'origine
    * 50 pts de vie et d'énergie
    */
   public Robot(){
       name="";
       position= new Point2D();
       energie= ROBOT_DEFAULT_ENERGIE;
       ptvie=ROBOT_DEFAULT_SANTE;
       nbrobot+=1;
   }
   /**
    * Créé le robot avec les param suivants, la position doit être un objet Point2D
    * si la valeur de l'energie ou de la sante sont superieures aux valeurs max
    * elles sont ramenées à ses valeurs
    * @param name
    * @param position
    * @param energie
    * @param ptvie 
    */
   public Robot(String name, Point2D position, int energie, int ptvie){
       this.name=name;
       this.position=new Point2D(position);
       if (energie>ROBOT_MAX_ENERGIE){
           this.energie=ROBOT_MAX_ENERGIE;
       }else this.energie=energie;
       if (ptvie>ROBOT_MAX_SANTE){
           this.ptvie=ROBOT_MAX_SANTE;
       } else this.ptvie=ptvie;
       nbrobot+=1;
   }
   /**
    * Créé un robot du nom voulu et à la position fixée (la position doit etre un Point2D)
    * Avec les valeurs de vie/energie par defaut
    * @param name
    * @param position 
    */
   public Robot(String name, Point2D position){
       this.name=name;
       this.position=new Point2D(position);
       this.energie=ROBOT_DEFAULT_ENERGIE;
       this.ptvie=ROBOT_DEFAULT_SANTE;
       nbrobot+=1;
   }
   /**
    * Copie le robot sélectionné pour créer un robot avec tous les paramatres identiques
    * peut etre a supprimer plus tard
    * @param r 
    */
   public Robot(Robot r){
       this.name=r.name;
       this.position=new Point2D(r.position);
       this.energie=r.energie;
       this.ptvie=r.ptvie;
       nbrobot+=1;
   }
   /**
    * renvoie le nom du robot sélectionné
    * @return 
    */
   public String getName(){
       String n=this.name;
       return n;
   }
   /**
    * renvoie la position du robot sélectionné sous forme d'objet Point2D
    * @return 
    */
   public Point2D getPosition(){
       Point2D p=new Point2D(this.position);
       return p;
   }
   /**
    * renvoie l'énergie du robot sélectionné (entier)
    * @return 
    */
   public int getEnergie(){
       int e=this.energie;
       return e;
   }
   /**
    * renvoie les points de vie du robot sélectionné (entier)
    * @return 
    */
   public int getPtVie(){
       int pv=this.ptvie;
       return pv;
   }
   
   /**
    * modifie le nom du robot sélectionné
    * @param n 
    */
   public void setName(String n){
       this.name=n;
   }
   /**
    * modifie la position du robot sélectionné, en paramatre doit se trouver un Point2D
    * @param p 
    */
   public void setPosition(Point2D p){
       this.position= new Point2D(p);
   }
   /**
    * modifie l'énergie du robot sélectionné
    * renvoit un message d'erreur si la valeur donnée est sup au max
    * @param e 
    */
   public void setEnergie(int e){
       if(e<=ROBOT_MAX_ENERGIE){
           this.energie=e;
       }else System.out.println("cette valeur d'énergie est supérieure au max");
   }
   /***
    * modifie les points de vie du robot sélectionné
    *  renvoit un message d'erreur si la valeur donnée est sup au max
    * @param pv 
    */
   public void setPtVie(int pv){
       if(pv<=ROBOT_MAX_SANTE){
           this.ptvie=pv;
       }else System.out.println("cette valeur de sante est supérieure au max");
   }
   
   /****
    * On entre en paramètre le nombre de cases dont le robot
    * se déplacera en abscisse et en ordonné, s'il n'a pas assez d'energie
    * un message d'erreur s'affiche
    * @param abs
    * @param ord 
    */
   public void deplace(int abs, int ord){
       if(this.energie>=Math.abs(abs)+Math.abs(ord)){
       Point2D p= new Point2D(this.getPosition());
       int absfinal=p.getAbscisse()+abs;
       int ordfinal=p.getOrdonne()+ord;
       Point2D pfinal= new Point2D(absfinal,ordfinal);
       this.setPosition(pfinal);
       this.depenserEnergie(Math.abs(abs)+Math.abs(ord));
       }else System.out.println("not enough energie");
   }
   
   /**
    * Permet d'afficher toutes les statistiques du robot sélectionné:
    * sont nom, sa position (abs,ord), son énergie puis ses points de vie
    */
   public void afficherStat(){
       System.out.println("voici les caractéristiques de ce robot: nom:"+this.name+", Position:");
       this.position.afficherPoint();
       System.out.println(", energie:"+this.energie+", Points de vie: "+this.ptvie);
               
   }
   
   /**
    * Augmente la valeur d'energie du robot avec la valeur d'energie passee en
    * paramètre, la valeur finale est capée par la valeur max d'energie
    * @param e 
    */
   public void recharger(int e){
       this.energie=min(this.energie+e,ROBOT_MAX_ENERGIE);
   }
   
   /**
    * Augmente la sante du robot avec la valeur passee en
    * paramètre, la valeur finale est capée par la valeur max de sante
    * @param s 
    */
   public void reparer(int s){
       this.ptvie=min(this.ptvie+s,ROBOT_MAX_SANTE);
   }
  
   /**
    * diminue la valeur du d'énergie du robot avec la valeur passée en paramètre,
    * si le robot perd plus d'énergie qu'il n'en possède, son énergie tombe à 0.
    * @param e 
    */
   public void depenserEnergie(int e){
       if(e>this.energie){
           this.energie=0;
       }else this.energie=this.energie-e;
   }
   
   public void nombreRobot(){
       System.out.println("il y a "+nbrobot+" robots");
   }
   
   public void bouge(PlateauJeu p){
       ArrayList<Point2D> cases= p.casesLibresAutourDe(this.getPosition());
       Random direction = new Random();
       Point2D caseFinale= new Point2D(cases.get(direction.nextInt(cases.size())));
       System.out.println((caseFinale.getAbscisse()-this.position.getAbscisse())+" ;"+(caseFinale.getOrdonne()-this.position.getOrdonne()));
       this.deplace((caseFinale.getAbscisse()-this.position.getAbscisse()), (caseFinale.getOrdonne()-this.position.getOrdonne()));
   }
    
}
