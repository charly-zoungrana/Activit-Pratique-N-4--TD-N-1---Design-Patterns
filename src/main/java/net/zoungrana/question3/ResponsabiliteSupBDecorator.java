package net.zoungrana.question3;

public class ResponsabiliteSupBDecorator extends DecorateurAbstrait{

    public ResponsabiliteSupBDecorator(TraitementInterface composant){
        super(composant);
    }

    @Override
    public void traitement() {
        System.out.println("************ Traitement Supplémentaire B ***************");
        composant.traitement();
        System.out.println("*************** Traitement Supplémentaire B ************");
    }
}
