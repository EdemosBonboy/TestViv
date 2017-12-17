/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author bonbo
 */
public class Animal {
    protected Vecteur position;
    protected Vecteur direction;
    
    /* Définition d'un générateur de nombre aléatoire */
    private Random random = new Random();
    
    public Animal() {
        this.position = new Vecteur();
        this.direction = new Vecteur();
    }
    
    public Animal(double x, double y) {
        this.position = new Vecteur(x, y);
        //Génération aléatoire de la direction que prendra l'animal
        double directionX = nombreAleatoireNegatif(random.nextDouble() * 3);
        double directionY = nombreAleatoireNegatif(random.nextDouble() * 3);
        this.direction = new Vecteur(directionX, directionY);
        //this.direction = new Vecteur(1, 1);
    }
    
    public void setPosition(Vecteur p) {
        this.position = p;
    }
    
    public Vecteur getPosition() {
        return position;
    }
    
    public void setDirection(Vecteur v) {
        this.direction = v;
    }
    
    public Vecteur getDirection() {
        return direction;
    }
    
    /**
     * Afin de ne pas toujours générer des nombres positifs, cett méthode
     * permettra de générer aléatoire soit un nombre positif, soit un nombre
     * négatif à partir du nombre fourni en paramètre.
     * @param nombre représente le nombre dont doit être renvoyée la valeur
     * positive ou négative
     * @return +nombre ou - nombre
     */
    private double nombreAleatoireNegatif(double nombre) {
        // On génère un entier entre 0 et 1
        // Si l'entier est égal à 1, alors on transforme le nombre en négatif
        // Sion le nombre est retourné tel quel
        switch(random.nextInt(2)) {
            case 1:
                nombre = -nombre;
                break;
        }
        
        return nombre;
    }
    
    private void seDeplacer(Set<Animal> listeAnimaux) {
        genererMouvementAleatoire();
        //genererEspacePersonnel(listeAnimaux);
        maintenirDansLeVivarium();
    }
    
    /**
     * Cette méthode permet de générer des mouvements aléatoires des animaux
     * dans le vivarium.
     */
    private void genererMouvementAleatoire() {
        double x = nombreAleatoireNegatif(random.nextDouble() * Constantes.VITESSE_DEPLACEMENT_ALEATOIRE);
        double y = nombreAleatoireNegatif(random.nextDouble() * Constantes.VITESSE_DEPLACEMENT_ALEATOIRE);
        this.setDirection(new Vecteur(x, y));
    }

    /**
     * Cette méthode permet de générer un espace autour des animaux
     * pour éviter les colisions
     * @param listeAnimaux 
     */
    private void genererEspacePersonnel(Set<Animal> listeAnimaux) {
        // Définition de la liste qui contiendar les animaux voisins
        ArrayList<Animal> voisins = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();
        for (Animal animal : listeAnimaux) {
            // Calcul de la distance
            // La méthode Math.hypot fait le calcul sqrt(x^2 + y^2)
            // On vérifie ensuite si la distance entre les animaux est inférieure
            // à la distance de sécurité.
            // Si oui, on ajoute l'animal en question à la liste des voisins
            double distance = Math.hypot(this.getPosition().getX()-animal.getPosition().getX(),
                                         this.getPosition().getY()-animal.getPosition().getY());
            distances.add(distance);
            if (animal != this && distance < Constantes.DISTANCE_DE_SECURITE) {
                voisins.add(animal);
            }
        }
        
        // Trouver le voisin le plus proche et le distancer
        if (!voisins.isEmpty()) {
            Animal lePlusProche = new Animal();
            
        }
    }
    
    /**
     * Cette méthode permet de déterminer la plus petite distance
     * entre les animaux
     */
    private double plusPetiteDistance(ArrayList<Double> liste) {
        double min = 0.;
        for(int i = 0; i < liste.size(); i++) {
            for (int j = 1; j < liste.size(); j++) {
                if (liste.get(i) <= liste.get(j)) {
                    min = liste.get(i);
                } else {
                    min = liste.get(j);
                }
            }
        }
        return min;
    }

    /**
     * Cette méthode permet de maintenir les animaux dans le vivarium.
     * Lorsqu'ils sortent du cadre de la fenêtre, ils réapparaissent automatiquement
     * de l'autre côté.
     * 
     * Donc ici, on vérifie les coordonnées de l'animal et si elles dépassent
     * les bornes, on le repositionne dans le vivarium.
     */
    private void maintenirDansLeVivarium() {
        double x = 0., y = 0.;
        if (this.position.getX() < 0) {
            x = Constantes.PANE_WIDTH;
        } else if (this.position.getX() > Constantes.PANE_WIDTH) {
            x = 0.;
        }
        
        if (this.position.getY() < 0) {
            y = Constantes.PANE_HEIGHT;
        } else if (this.position.getY() > Constantes.PANE_HEIGHT) {
            y = 0.;
        }
        
        this.setPosition(new Vecteur(x, y));
    }
}
