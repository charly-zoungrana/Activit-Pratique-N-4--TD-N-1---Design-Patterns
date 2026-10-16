package net.zoungrana.question3;

public class ResponsabiliteSupADecorator extends DecorateurAbstrait{

    public ResponsabiliteSupADecorator(TraitementInterface composant){
        super(composant);
    }

    @Override
    public void traitement() {
        System.out.println("************ Traitement supplémentaire A  ***************");
        composant.traitement();
        System.out.println("************** Traitement supplémentaire A *************");
    }
}
