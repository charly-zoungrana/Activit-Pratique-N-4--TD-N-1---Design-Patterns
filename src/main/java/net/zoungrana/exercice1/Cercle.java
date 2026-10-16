package net.zoungrana.exercice1;

public class Cercle extends Figure{
    private final double rayon;

    public Cercle(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public double calculerAire() {
        return Math.PI*rayon*rayon;
    }

    @Override
    public void afficherFigure() {
        System.out.println("*************************************");
        System.out.println("Cercle de rayon : " + rayon + " Aire:"+ calculerAire());
        System.out.println("*************************************");
    }
}
