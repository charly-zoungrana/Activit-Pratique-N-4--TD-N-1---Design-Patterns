package net.zoungrana.exercice2.filtrage;


public class FiltreSeuil implements Filtre {
    private final int seuil;

    public FiltreSeuil(int seuil) {
        this.seuil = seuil;
    }

    @Override
    public int[] filter(int[] data) {
        int[] resultat = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            resultat[i] = data[i] >= seuil ? 1 : 0;
        }
        return resultat;
    }
}
