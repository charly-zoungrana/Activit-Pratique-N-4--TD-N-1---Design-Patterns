package net.zoungrana.exercice2.adaptation;


public class ImplNonStandard {
    public int[] appliquerFiltre(String filterName, int[] data) {
        if (!"inversion".equals(filterName)) {
            throw new IllegalArgumentException("Filtre inconnu : " + filterName);
        }

        int[] resultat = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            resultat[i] = ~data[i];
        }
        return resultat;
    }
}
