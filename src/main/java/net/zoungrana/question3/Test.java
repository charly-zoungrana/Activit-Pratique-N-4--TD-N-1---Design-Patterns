package net.zoungrana.question3;

public class Test {
    public static void main(String[] args) {
        TraitementInterface c=new Composant();
        c=new ResponsabiliteSupADecorator(c);
        c.traitement();

        System.out.println("-----------------------------------------");

        c=new ResponsabiliteSupBDecorator(c);
        c.traitement();
    }
}
