package net.zoungrana.question1;

import java.util.ArrayList;
import java.util.List;

public class GroupeFigure extends Figure{

    private final List<Figure> figures=new ArrayList<>();

    public GroupeFigure(String name) {
        super(name);
    }


    @Override
    public double calculerAire() {
        return figures.stream()
                .mapToDouble(Figure::calculerAire)
                .sum();

    }

    @Override
    public void afficherFigure() {
        System.out.println("-------------------- Groupe---------------------------");
        System.out.println("Aire du groupe: " + calculerAire() );
        figures.forEach(Figure::afficherFigure);
        System.out.println("-------------------------------------------------------");
    }

    public Figure ajouterFigure(Figure f){
        figures.add(f);
        return f;
    }
}
