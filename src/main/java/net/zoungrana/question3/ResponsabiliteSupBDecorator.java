package net.zoungrana.question3;

public class ResponsabiliteSupBDecorator extends DecorateurAbstrait{

    public ResponsabiliteSupBDecorator(TraitementInterface traitement){
        super(traitement);
    }

    @Override
    public void traitement() {
        System.out.println("************ Traitement Supplémentaire B ***************");
        composant.traitement();
        System.out.println("*************** Traitement Supplémentaire B ************");
    }
}
