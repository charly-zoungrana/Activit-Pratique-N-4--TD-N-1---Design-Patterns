package net.zoungrana.question1;

import javax.xml.namespace.QName;

public abstract class Figure {
    protected String name;

    public Figure(String name){
        this.name=name;
    }

    public abstract double calculerAire();
    public abstract void afficherFigure();
}
