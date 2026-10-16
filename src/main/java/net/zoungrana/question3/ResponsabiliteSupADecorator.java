package net.zoungrana.question3;

public class ResponsabiliteSupADecorator extends DecorateurAbstrait{

    public ResponsabiliteSupADecorator(TraitementInterface traitement){
        super(traitement);
    }

    @Override
    public void traitement() {
        System.out.println("************ Traitement supplémentaire A  ***************");
        composant.traitement();
        System.out.println("************** Traitement supplémentaire A *************");
    }
}
