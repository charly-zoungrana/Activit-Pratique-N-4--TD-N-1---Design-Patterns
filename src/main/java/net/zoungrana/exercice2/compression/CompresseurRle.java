package net.zoungrana.exercice2.compression;


public class CompresseurRle extends Compresseur {
    @Override
    protected int[] encoder(int[] data) {
        if (data.length == 0) {
            return new int[0];
        }

        int nombreSuites = 1;
        for (int i = 1; i < data.length; i++) {
            if (data[i] != data[i - 1]) {
                nombreSuites++;
            }
        }

        int[] resultat = new int[Math.multiplyExact(nombreSuites, 2)];
        int position = 0;
        int debut = 0;
        while (debut < data.length) {
            int valeur = data[debut];
            int fin = debut + 1;
            while (fin < data.length && data[fin] == valeur) {
                fin++;
            }

            resultat[position++] = valeur;
            resultat[position++] = fin - debut;
            debut = fin;
        }
        return resultat;
    }
}
