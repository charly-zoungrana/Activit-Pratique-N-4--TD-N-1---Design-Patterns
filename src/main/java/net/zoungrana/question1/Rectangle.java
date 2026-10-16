package net.zoungrana.question1;

public class Rectangle extends Figure{
    private  double largeur;
    private  double longueur;

    public Rectangle(String name){
        super(name);
    }

    public Rectangle(String name,double largeur, double longueur) {
        super(name);
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
        System.out.println(name +" de largeur: " +largeur + " et de longueur : " + longueur + " Aire:"+ calculerAire());
        System.out.println("*************************************");
    }
}
