package net.zoungrana.question4;

public class Couloir implements Observer{

    @Override
    public void update(Joueur joueur) {
        if(joueur.getScore()>=5){
            System.out.println("+++++++++++++++++++++ Couloir ++++++++++++++++++++++");
            System.out.println("Couloir: "+joueur.getScore());
            System.out.println("+++++++++++++++++++++ Couloir ++++++++++++++++++++++");
        }
    }
}
