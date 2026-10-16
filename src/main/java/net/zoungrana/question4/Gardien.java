package net.zoungrana.question4;

public class Gardien implements Observer{

    @Override
    public void update(Joueur joueur) {
        if(joueur.getScore()>=10){
            System.out.println("====================== Gardien ===================");
            System.out.println("Gardien: "+joueur.getScore());
            System.out.println("====================== Gardien ====================");
        }
    }
}
