package net.zoungrana.question4;

public class Camera implements Observer{

    @Override
    public void update(Joueur joueur) {
        System.out.println("****************** Caméra *******************");
        System.out.println("Camera: "+joueur.getScore());
        System.out.println("******************** Caméra *****************");
    }
}
