/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import Controleurs.PredateurControleur;
import Controleurs.ProieControleur;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author bonbo
 */
public class Vivarium extends Stage {
    private Pane espaceDeJeu = new Pane();
    private ArrayList<Proie> listeProies = new ArrayList<>();
    private ArrayList<Predateur> listePredateurs = new ArrayList<>();
    
    public Vivarium() {}
    
    public Vivarium(String nom, int largeur, int hauteur) {
        this.setTitle(nom);
        this.setWidth(largeur);
        this.setHeight(hauteur);
        this.initModality(Modality.APPLICATION_MODAL);
        //this.setResizable(false);
        
        BorderPane root = new BorderPane();
        root.setCenter(espaceDeJeu);
        
        configurerEspaceDeJeu();
        
        Scene scene = new Scene(root, Constantes.PANE_WIDTH, Constantes.PANE_HEIGHT);
        this.setScene(scene);
    }
    
    public void configurerEspaceDeJeu() {
        espaceDeJeu.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                switch(event.getButton()) {
                    case PRIMARY:
                        // Bouton gauche
                        Proie proie = new Proie(event.getX(), event.getY());
                        System.out.println("Proie : x = "
                                            + proie.getPosition().getX() + ", y = "
                                            + proie.getPosition().getY());
                        ProieControleur proieControleur = new ProieControleur(espaceDeJeu,
                                                            new Vecteur(proie.getPosition().getX(),
                                                                        proie.getPosition().getY()));
                        listeProies.add(proie);
                        break;
                        
                    case SECONDARY:
                        // Bouton droit
                        Predateur predateur = new Predateur(event.getX(), event.getY());
                        System.out.println("Predateur : x = "
                                            + predateur.getPosition().getX() + ", y = "
                                            + predateur.getPosition().getY());
                        PredateurControleur predateurControleur = new PredateurControleur(espaceDeJeu,
                                                            new Vecteur(predateur.getPosition().getX(),
                                                                        predateur.getPosition().getY()));
                        listePredateurs.add(predateur);
                        break;
                }
            }
            
        });
        
        espaceDeJeu.minWidth(Constantes.PANE_WIDTH);
        espaceDeJeu.minHeight(Constantes.PANE_HEIGHT);
    }
}
