package net.zoungrana.exercice1;

public class Rectangle extends Figure{
    private final double largeur;
    private final double longueur;

    public Rectangle(double largeur, double longueur) {
        this.largeur = largeur;
        this.longueur = longueur;
    }

    @Override
    public double calculerAire() {
        return largeur*longueur;
    }

    @Override
    public void afficherFigure() {
        System.out.println("*************************************");
        System.out.println("Rectangle de largeur : " + largeur + " et de longueur : " + longueur + " Aire:"+ calculerAire());
        System.out.println("*************************************");
    }
}
