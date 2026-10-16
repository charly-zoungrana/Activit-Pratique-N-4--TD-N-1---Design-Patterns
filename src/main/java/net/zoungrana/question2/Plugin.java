package net.zoungrana.question2;

public abstract class Plugin {

    public void appliquerAlgo(){
        preparer();
        algoPart1();
        algoPart2();
        terminer();
    }

    private void preparer(){
        System.out.println("Preparation");
    }

    public abstract void algoPart1();
    public abstract void algoPart2();

    private void terminer(){
        System.out.println("Terminer");
    }
}
