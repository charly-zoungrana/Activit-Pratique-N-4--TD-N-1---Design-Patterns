package net.zoungrana.question1;

public class Test {
    public static void main(String[] args) {
        Cercle c=new Cercle("Cercle 1",5);
        c.afficherFigure();

        Rectangle r=new Rectangle("Rectangle 1",5,10);
        r.afficherFigure();

        GroupeFigure g=new GroupeFigure("Groupe1");
        g.ajouterFigure(c);
        g.ajouterFigure(r);
        g.afficherFigure();
    }
}
