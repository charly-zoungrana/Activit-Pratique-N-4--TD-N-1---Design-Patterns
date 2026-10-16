package net.zoungrana.question4;

import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Joueur joueur=new Joueur();
        Camera c=new Camera();
        Gardien g=new Gardien();
        Couloir co=new Couloir();

        Stream.of(c,g,co).forEach(joueur::subscribe);
        joueur.setScore(5);

        System.out.println("-----------------------------------------");
        joueur.setScore(15);

    }
}
