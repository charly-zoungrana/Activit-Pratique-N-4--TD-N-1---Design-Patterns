package net.zoungrana.exercice2;

import net.zoungrana.exercice2.compression.Compresseur;
import net.zoungrana.exercice2.filtrage.Filtre;

import java.util.Objects;

public class TraitementImage {
    private Filtre filtre;
    private final Compresseur compresseur;

    public TraitementImage(Filtre filtre, Compresseur compresseur) {
        setFiltre(filtre);
        this.compresseur = Objects.requireNonNull(compresseur, "compresseur");
    }

    public final void setFiltre(Filtre filtre) {
        this.filtre = Objects.requireNonNull(filtre, "filtre");
    }

    public int[] filter(int[] data) {
        return filtre.filter(Objects.requireNonNull(data, "data"));
    }

    public int[] compresser(int[] data) {
        return compresseur.compresser(data);
    }
}
