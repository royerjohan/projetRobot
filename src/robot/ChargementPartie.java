/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 *
 * @author Johan
 */
public class ChargementPartie {
    private String nom;
    private BufferedReader buffer;
    
    public ChargementPartie(){
        this.nom="";
    }
    
    public ChargementPartie(String nom){
        this.nom=nom;
    }
    
    public PlateauJeu chargerpartie() throws FileNotFoundException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String ligne;
        String delimiteurs= " ,.;";
        int largeur=0;
        int hauteur=0;
        this.buffer= new BufferedReader(new FileReader(nom));
        // On lit la largueur et la hauteur d'abords
        ligne = buffer.readLine();
        StringTokenizer tokenizer = new StringTokenizer(ligne,delimiteurs);
        String mot= tokenizer.nextToken();
        if (mot.equals("Largeur")){
            mot=tokenizer.nextToken();
            largeur= Integer.parseInt(mot);
        } else {
            System.out.println("mauvais format dans le fichier de sauvegarde");
            return null;
        }
        //on fait la hauteur
        ligne=buffer.readLine();
        tokenizer= new StringTokenizer(ligne,delimiteurs);
        mot=tokenizer.nextToken();
        if (mot.equals("Hauteur")){
            mot=tokenizer.nextToken();
            hauteur= Integer.parseInt(mot);
        } else {
            System.out.println("mauvais format dans le fichier de sauvegarde");
            return null;
        }
        // on peut maintenant créer le plateau vide
        PlateauJeu pj= new PlateauJeu(largeur,hauteur);
        
        //on ajoute maintenant les pièces dans le plateau.
        while((ligne = buffer.readLine())!=null){
            ajouterPiece(pj,ligne,delimiteurs);
        }
        buffer.close();
        return pj;
    }
    
    public void ajouterPiece(PlateauJeu pj, String ligne, String delimiteurs) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        StringTokenizer tokenizer= new StringTokenizer(ligne,delimiteurs);
        //on créé la pièce de la classe correspondant au premier mot
        String mot= tokenizer.nextToken();
        Class classe= Class.forName("robot."+mot);
        Object piece= classe.newInstance();
        mot = tokenizer.nextToken();
        String nom="";
        // dans le cas où c'est un robot le 3eme mot est un String
        if(classe.getName().contains("Robot")){
            nom= mot;
            mot=tokenizer.nextToken();
        }
        //on créé le point2D de position
        int largeur= Integer.parseInt(mot);
        mot = tokenizer.nextToken();
        int hauteur= Integer.parseInt(mot);
        Point2D position= new Point2D(largeur, hauteur);
        
        //on regarde le nombre de mot restant pour savoir si c'est un robot (reste 2),
        //un obstacle (reste 0) ou une Borne (reste 1);
        
        int count= tokenizer.countTokens();
        // cas obstacle
        if(count==0){
            Obstacle o= (Obstacle) piece;
            o.setPosition(position);
            pj.addObstacle(o);
        }
        //cas Bonus
        if (count==1) {
            mot = tokenizer.nextToken();
            int jauge= Integer.parseInt(mot);
            Bonus b=(Bonus) piece;
            b.setPosition(position);
            b.setJauge(jauge);
            pj.addBonus(b);
        }
        //cas Robot
        if (count==2){
            mot = tokenizer.nextToken();
            int sante= Integer.parseInt(mot);
            mot= tokenizer.nextToken();
            int energie= Integer.parseInt(mot);
            Robot r= (Robot) piece;
            r.setPosition(position);
            r.setPtVie(sante);
            r.setEnergie(energie);
            r.setName(nom);
            pj.addRobot(r);
        }
               
    }
    
}
