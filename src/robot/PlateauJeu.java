/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package robot;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


/**
 *
 * @author johan
 */
public class PlateauJeu {
    private int largeur;
    private int hauteur;
    private ArrayList<Robot> listeRobot;
    private ArrayList<Obstacle> listeObstacle;
    private ArrayList<Bonus> listeBonus;
    private GUIBoard monInterface;        
    /**
     * Lorsque le plateau est créé, il n'y a pas de pièces dessus
     * il faut les rajouter après avec addxxx()
     * @param l
     * @param h 
     */
   public PlateauJeu(int l, int h){
       this.largeur=l;
       this.hauteur=h;
       this.listeRobot= new ArrayList<Robot>();
       this.listeObstacle=new ArrayList<Obstacle>();
       this.listeBonus=new ArrayList<Bonus>();
       creerGUI(l, h);
   }
   /**
    * Renvoit la largeur du plateau.
    * @return 
    */
   public int getLargeur(){
       return this.largeur;
   }
   /**
    * Renvoit la hauteur du plateau
    * @return 
    */
   public int getHauteur(){
       return this.hauteur;
   }
   
   public ArrayList<Robot> getListeRobot(){
       return this.listeRobot;
   }
   
   public ArrayList<Obstacle> getListeObstacle(){
       return this.listeObstacle;
   }
   
   public ArrayList<Bonus> getListeBonus(){
       return this.listeBonus;
   }
   /**
    * modifie la largeur du plateau
    * @param l 
    */
   public void setLargeur(int l){
       this.largeur=l;
   }
   /**
    * modifie la hauteur du plateau
    * @param h 
    */
   public void setHauteur(int h){
       this.hauteur=h;
   }
   /**
    *  *  ajoute le robot en paramètre dans la liste des robots sur le
    * plateau en vérifiant que sa position correspond à une case 
    * libre.
    * @param r 
    */
   public void addRobot(Robot r){
       Point2D p=r.getPosition();
       if(this.caseLibre(p)&&!this.horsPlateau(p)){
           this.listeRobot.add(r);
       } 
   }
   /**
    *  *  ajoute le bonus en paramètre dans la liste des bonus sur le
    * plateau en vérifiant que sa position correspond à une case 
    * libre.
    * @param b 
    */
   public void addBonus(Bonus b){
       Point2D p=b.getPosition();
       if(this.caseLibre(p)&&!this.horsPlateau(p)){
           this.listeBonus.add(b);
       } 
   }
   /**
    *  ajoute l'obstacle en paramètre dans la liste des obstacles sur le
    * plateau en vérifiant que sa position correspond à une case 
    * libre.
    * @param o 
    */
   public void addObstacle(Obstacle o){
       Point2D p=o.getPosition();
       if(this.caseLibre(p)&&!this.horsPlateau(p)){
           this.listeObstacle.add(o);
       } 
   }
   /**
    *  supprime le robot en paramètre de la liste des robots sur le
    * plateau
    * @param r 
    */
   public void deleteRobot(Robot r){
       this.listeRobot.remove(r);
   }
   
   /**
    *  supprime le bonus en paramètre de la liste des bonus sur le
    * plateau
    * @param b 
    */
   public void deleteBonus (Bonus b){
       this.listeBonus.remove(b);
   }
   
   /**
    * supprime l'obstacle en paramètre de la liste des obstacles sur le
    * plateau
    * @param o 
    */
   public void deleteObstacle(Obstacle o){
       this.listeObstacle.remove(o);
   }
   
   /**
    * Affiche les dimensions du plateau ainsi que les caracs de toutes les 
    * pièces en jeu.
    */
   public void affichePlateau(){
       System.out.println("Sur ce plateau de jeu de taille ["+this.getLargeur()+","+this.getHauteur()+"] se trouvent les pièces suivantes");
                  System.out.println(this.listeRobot.size());
       for(int i=0; i<this.listeRobot.size();i++){
           System.out.println(this.listeRobot.get(i).toString());
       }
       for(int i=0; i<this.listeObstacle.size();i++){
           System.out.println(this.listeObstacle.get(i).toString());
       }
       for(int i=0; i<this.listeBonus.size();i++){
           System.out.println(this.listeBonus.get(i).toString());
       }
   }
   /**
    * Simule un tour de jeu, affiche la dimension du plateau ainsi que
    * les caracs des pièces.
    * Demande au joueur s'il veut continuer ou non.
    * Tour de jeu renvoit la réponse du joueur (o ou n)
    * Cela affecte le comportement de partie();
    * @return 
    */
   public void tourDeJeu(){
       for(int i=0; i<listeRobot.size();i++){
           listeRobot.get(i).bouge(this);
           this.displayGameElement(listeRobot.get(i).getPosition(),null,listeRobot.get(i).getPtVie()+"pv/"+listeRobot.get(i).getEnergie()+"e", "changer tostring dans robot");
       }
       for(int j=0; j<listeObstacle.size();j++){
           if(listeObstacle.get(j).getClass().getName().equals("robot.ObstacleMobile")){
               ObstacleMobile om= (ObstacleMobile) listeObstacle.get(j);
               om.bouge(this);
           }
       }
       this.affichePlateau();
       /*Scanner console=new Scanner(System.in);
       System.out.println("Voulez vous continuer la partie? (o/n)");
       String reponse = console.nextLine();
       while ((!reponse.equals("o")) && (!reponse.equals("n"))){
           System.out.println("Réponse inconnue, veuillez entrer o ou n");
          System.out.println("Voulez vous continuer la partie? (o/n)");
          reponse = console.nextLine(); 
       }
       return reponse;*/
   }
   
   
   /**
    * Simule une partie de jeu, tant que le joueur veut continuer (répond o 
    * à la fin de son tour de jeu) un nouveau tour commence.
    */
   public void partie(){
       String reponse="o";
       int i=0;
       while(this.monInterface.isActive()){
           //i=1 si un tour à été joué, il repasse à 0 quand on appuie sur Play puis à 1 quand le tour est fait
           if(i==0){
               this.tourDeJeu();
               this.monInterface.getPlayButton().setText("Play ");
               i=1;
           }if(this.monInterface.getPlayButton().getText().equals("Play")){
               i=0;
           }
               }
   }
   /**
    * Renvoit true si le point sélectionné se trouve sur le plateau
    * false sinon
    * @param p
    * @return 
    */
   public boolean horsPlateau(Point2D p){
       int l=p.getAbscisse();
       int h=p.getOrdonne();
       // chèque les conditions de coords négatives ou trop grandes
        return !(0<=l && l<=this.getLargeur() && 0<=h && h<=this.getHauteur());
   }
   
   public boolean caseLibre(Point2D p){
      for(int i=0; i<this.listeRobot.size();i++){
          if(this.listeRobot.get(i).getPosition().getAbscisse()==p.getAbscisse()
                  &&this.listeRobot.get(i).getPosition().getOrdonne()==p.getOrdonne()){
              return false;
          }
       }
      for(int i=0; i<this.listeObstacle.size();i++){
          if(this.listeObstacle.get(i).getPosition().getAbscisse()==p.getAbscisse()
                  &&this.listeObstacle.get(i).getPosition().getOrdonne()==p.getOrdonne()){
              return false;
          }
       }
      for(int i=0; i<this.listeBonus.size();i++){
          if(this.listeBonus.get(i).getPosition().getAbscisse()==p.getAbscisse()
                  &&this.listeBonus.get(i).getPosition().getOrdonne()==p.getOrdonne()){
              return false;
          }
       }
      return true;
   }
   
   public ArrayList<Point2D> casesLibresAutourDe(Point2D p){
       ArrayList<Point2D> casesLibres = new ArrayList<Point2D>();
       int a=p.getAbscisse();
       int o=p.getOrdonne();
       for (int i=a-1;i<=a+1;i++){
       for(int j=o-1;j<=o+1;j++){
          Point2D point=new Point2D(i,j);
          if(this.caseLibre(point)&&!this.horsPlateau(point)){
              casesLibres.add(point);
          }
           }
       }
       return casesLibres;
   }
   
   public void jouer(Robot r){
       r.bouge(this);
   }
   /**
    * Cree l'interface avec les dimensions du plateau
    * @param l : largeur de la GUI (boutons)
    * @param h : hauteur de la GUI (boutons)
    */
   public void creerGUI(int l, int h) {
    this.monInterface = new GUIBoard(l, h);
    this.monInterface.setVisible(true);
  }
   public void displayGameElement(Point2D pos, ImageIcon icon, String textIcon, String textZoneTexte){
       this.monInterface.getJPlateauButton(pos.getAbscisse(), pos.getOrdonne()).setIcon(icon);
       this.monInterface.getJPlateauButton(pos.getAbscisse(), pos.getOrdonne()).setDisabledIcon(icon);
       this.monInterface.getJPlateauButton(pos.getAbscisse(), pos.getOrdonne()).setText(textIcon);
       this.monInterface.getJPlateauButton(pos.getAbscisse(), pos.getOrdonne()).setVerticalTextPosition(SwingConstants.CENTER);
       this.monInterface.getJPlateauButton(pos.getAbscisse(), pos.getOrdonne()).setHorizontalTextPosition(SwingConstants.CENTER);
       this.monInterface.getjZoneText().append(textZoneTexte+"\n");


   }
   
}
