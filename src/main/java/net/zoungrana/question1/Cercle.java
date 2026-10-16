package net.zoungrana.question1;

public class Cercle extends Figure{
    private  double rayon;

    public Cercle(String name){
        super(name);
    }
    public Cercle(String name,double rayon) {
        super(name);
        this.rayon = rayon;
    }

    @Override
    public double calculerAire() {
        return Math.PI*rayon*rayon;
    }

    @Override
    public void afficherFigure() {
        System.out.println("*************************************");
        System.out.println(name+" Rayon:" + rayon + " Aire:"+ calculerAire());
        System.out.println("*************************************");
    }
}
