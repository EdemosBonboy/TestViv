/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vivariumtest;

import Modeles.Constantes;
import Modeles.Vivarium;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author bonbo
 */
public class VivariumTest extends Application {
    
    private Scene scene;
    
    @Override
    public void start(Stage primaryStage) {
        Text titre = new Text(Constantes.TITRE_TEXT);
        titre.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        titre.setUnderline(true);
        
        FadeTransition ft = new FadeTransition(Duration.millis(2000), titre);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.setCycleCount(1);
        //ft.setAutoReverse(true);
        ft.play();
        
        Text nouvellePartieText = new Text(Constantes.NOUVELLE_PARTIE_TEXT);
        setFontToMenuChoices(nouvellePartieText);
        setTextUnderlinedOnMouseHover(nouvellePartieText);
        
        Text chargerVivariumText = new Text(Constantes.CHARGER_VIVARIUM_TEXT);
        setFontToMenuChoices(chargerVivariumText);
        setTextUnderlinedOnMouseHover(chargerVivariumText);
        
        Text scoreText = new Text(Constantes.SCORE_TEXT);
        setFontToMenuChoices(scoreText);
        setTextUnderlinedOnMouseHover(scoreText);
        
        Text quitterText = new Text(Constantes.QUITTER_TEXT);
        setFontToMenuChoices(quitterText);
        setTextUnderlinedOnMouseHover(quitterText);
        quitterText.setOnMouseClicked((MouseEvent event) -> {
            primaryStage.close();
        });
        
        VBox vBox = new VBox(20, titre, nouvellePartieText, chargerVivariumText, scoreText, quitterText);
        vBox.setAlignment(Pos.CENTER);
        
        scene = new Scene(vBox, 900, 700);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle(Constantes.TITRE_TEXT);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
    
    /**
     * Pour souligner le texte lorsque la souris passe au dessus
     * et faire disparaître le trait de soulignement le cursor quitte le texte
     * @param text représente l'objet Text à survoler
     */
    private void setTextUnderlinedOnMouseHover(Text text) {
        text.setOnMouseEntered((MouseEvent event) -> {
            text.setUnderline(true);
            scene.setCursor(Cursor.HAND);
        });
        text.setOnMouseExited((MouseEvent event) -> {
            text.setUnderline(false);
            scene.setCursor(Cursor.DEFAULT);
        });
        text.setOnMouseClicked(e -> {
            lancerNouvellePartie();
        });
    }
    
    private void showDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Good job", ButtonType.OK);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
    
    private void setFontToMenuChoices(Text text) {
        text.setFont(Font.font("Verdana", FontWeight.LIGHT, 25));
    }
    
    private void lancerNouvellePartie() {
        Vivarium vivarium = new Vivarium("Mon Viva", Constantes.PANE_WIDTH, Constantes.PANE_HEIGHT);
        vivarium.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
