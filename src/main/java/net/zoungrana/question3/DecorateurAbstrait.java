package net.zoungrana.question3;

public abstract class DecorateurAbstrait implements TraitementInterface {
    protected TraitementInterface composant;

    public DecorateurAbstrait(TraitementInterface composant){
        this.composant=composant;
    }

}
