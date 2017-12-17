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
public class ProieControleur {
    
    public ProieControleur(){}
    
    public ProieControleur(Pane pane, Vecteur vecteur) {
        Circle circle = new Circle(vecteur.getX(), vecteur.getY(), 7);
        circle.setFill(Color.rgb(200, 50, 50, 1));
        pane.getChildren().add(circle);
    }
}
