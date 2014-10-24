package robot;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Johan
 */
public class GUIBoard extends JFrame implements ActionListener{
    
    // Largeur et hauteur du plateau de Jeu
    private int largeur;
    private int hauteur;
    // Menu
    private JMenuBar        jMenuBar;
    private JMenu           jMenuFile;
    private JMenuItem       jFileNew;
    private JMenuItem       jFileOpen;
    private JMenuItem       jFileSave;
    private JMenuItem       jFileReset;
    private JMenuItem       jFileExit;
    private JMenu           jMenuAbout;
    private JMenuItem       jAboutInfo;
    
    // Panel pour contenir les boutons
    private JPanel          jPanelBoutons;
    private JToggleButton   jPlateau[][];
   
    // Panel pour contenir le texte + bouton reset
    private JPanel          jPanelTexte;
    private JScrollPane     jScrollPane;
    private JTextArea       jZoneTexte;
    private JButton         jResetTextButton;
    
    // Play/Pause bouton
    private JButton         jPlayButton;
    private JButton         jStopButton;
    private JPanel          jPanelPlayStop;
    
   
    
    public GUIBoard(int l, int h) {
        this.largeur = l;
        this.hauteur = h;
        
        // Creation de la GUI
        initGUIComponents();
    }
    public JButton getPlayButton(){
        return this.jPlayButton;
    }
    public JToggleButton getJPlateauButton(int i, int j){
        return this.jPlateau[i][j];
    }
    public JTextArea getjZoneText(){
        return this.jZoneTexte;
    }
    
    public void initGUIComponents(){
      // Creating the frame and the layout
      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
      setTitle("RobotSimulator");
      setMaximumSize(new java.awt.Dimension(1600, 1200));
      setMinimumSize(new java.awt.Dimension(320, 240));
      setPreferredSize(new java.awt.Dimension(640, 480));
      
        // Adding the menu
        jMenuBar = new javax.swing.JMenuBar();
        jMenuBar.setMinimumSize(new java.awt.Dimension(320, 22));
        jMenuBar.setSize(new java.awt.Dimension(320, 20));
              
        // Menu
        jMenuFile = new JMenu("File");
        // Menu new
        jFileNew = new JMenuItem("New");
        // Menu open 
        jFileOpen = new JMenuItem("Open");
        // Menu save 
        jFileSave = new JMenuItem("Save");
        // Menu reset 
        jFileReset = new JMenuItem("Reset");
        // Menu exit
        jFileExit = new JMenuItem("Exit");        
              
        // Menu a propos
        jMenuAbout = new JMenu("About");
        jAboutInfo = new JMenuItem("Info");
        
        // Adding menu items to the menu
        jMenuFile.add(jFileNew);
        jMenuFile.add(jFileOpen);
        jMenuFile.add(jFileSave);
        jMenuFile.add(jFileReset);
        jMenuFile.add(jFileExit);
              
        jMenuAbout.add(jAboutInfo);
        
        jMenuBar.add(jMenuFile);
        jMenuBar.add(jMenuAbout);
      
        // Ajout du menu a la JFrame
        setJMenuBar(jMenuBar);
        
        // apres avoir choisi les tailles de la fenetre et son titre
        getContentPane().setLayout(new BorderLayout());
            // On cree un panel qui va stocker les boutons de notre plateau de jeu
        jPanelBoutons = new JPanel(new GridLayout(this.largeur,this.hauteur));
            // Creating the board
        jPlateau = new JToggleButton[this.largeur][this.hauteur];
            // On cree l'ensemble des boutons (JToggleButton)
        for(int i =0;i<largeur;i++) {
            for(int j=0;j<hauteur;j++) {
             // Creation du bouton en [i,j]
             jPlateau[i][j] = new JToggleButton();
             jPlateau[i][j].setEnabled(false);
             jPlateau[i][j].setMaximumSize(new java.awt.Dimension(100, 100));
              jPlateau[i][j].setMinimumSize(new java.awt.Dimension(10, 10));
           jPlateau[i][j].setPreferredSize(new java.awt.Dimension(40, 40));
                
              // Ajout du bouton nouvellement cree
           jPanelBoutons.add(jPlateau[i][j],i,j);
            }
         }
        // ajout du panel a la JFrame (une GUIBoard est une JFrame)
        getContentPane().add(jPanelBoutons, BorderLayout.CENTER);
        
         // Play/Pause button
        jPlayButton = new JButton("Play");
        jPlayButton.addActionListener(this); 
        jStopButton = new JButton("Stop");
        jStopButton.addActionListener(this);
        // Adding the "Play"/Stop" buttons
         jPanelPlayStop = new JPanel(new FlowLayout());
        jPanelPlayStop.add(jPlayButton);
        
        jPanelPlayStop.add(jStopButton);
        getContentPane().add(jPanelPlayStop, BorderLayout.SOUTH);
        
        // Un panneau permettant de gerer le defilement
        // et gestion des tailles minimales et preferees
         jScrollPane = new JScrollPane();
         jScrollPane.setMinimumSize(new java.awt.Dimension(300, 140));
         jScrollPane.setPreferredSize(new java.awt.Dimension(200, 100));

         // Text Area
         jZoneTexte = new JTextArea();
         ///////////////////////////
         // apres la creation de jZoneTexte
        jZoneTexte.setText("Test\n de texte dans \n la zone de texte !");
  //////////////////////////////////////////////////////////
         // Ajout de la zone de texte dans le scroll pane
         jScrollPane.setViewportView(jZoneTexte);
         
         // Reset bouton        
         jResetTextButton = new JButton("Clear Text");
        jResetTextButton.setFocusable(false);
         // apres la creation de jResetTextButton
         jResetTextButton.addActionListener(this);        
         // Ajout scroll pane/textarea
        jPanelTexte = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        // Ajout du scroll pane
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
          c.weightx = 1.0;
        c.weighty = 1.0;
          jPanelTexte.add(jScrollPane, c);
        
         // Ajout du bouton
         c.fill = GridBagConstraints.BOTH;
         c.gridx = 0;
         c.gridy = 1;
         c.weightx = 1.0;
         c.weighty = 0.2;
          jPanelTexte.add(jResetTextButton,c);
        
         // Ajout the panel for the texte
         getContentPane().add(jPanelTexte, BorderLayout.EAST);
             // juste avant la fin de la methode
        pack(); 
    }

    
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Clear Text")){
        this.jZoneTexte.setText(null);
        }
        //"Play " correspond au moment d'attente dujeu qui veut que l'on appuie sur le bouton pour relacer un tour, faire repasser le boutton à Play relance le tour
        if(e.getActionCommand().equals("Play ")){
            this.jPlayButton.setText("Play");
        }
        if(e.getActionCommand().equals("Stop")){
            this.dispose();
        }
    }
}
