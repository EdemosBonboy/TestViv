/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleurs;

import Modeles.Vecteur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author bonbo
 */
public class PredateurControleur {
    public PredateurControleur(){}
    
    public PredateurControleur(Pane pane, Vecteur vecteur) {
        Circle circle = new Circle(vecteur.getX(), vecteur.getY(), 10);
        circle.setFill(Color.GREEN);
        pane.getChildren().add(circle);
    }
}
