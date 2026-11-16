package net.zoungrana.exercice2.compression;

import java.util.Objects;

public abstract class Compresseur {
    public final int[] compresser(int[] data) {
        int[] donneesPreparees = preparer(data);
        int[] donneesEncodees = encoder(donneesPreparees);
        return finaliser(donneesEncodees);
    }

    private int[] preparer(int[] data) {
        return Objects.requireNonNull(data, "data").clone();
    }

    protected abstract int[] encoder(int[] data);

    private int[] finaliser(int[] data) {
        return Objects.requireNonNull(data, "L'encodeur doit renvoyer un tableau");
    }
}
