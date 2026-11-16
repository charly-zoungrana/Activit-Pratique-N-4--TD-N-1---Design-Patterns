package net.zoungrana.exercice2.adaptation;

import net.zoungrana.exercice2.filtrage.Filtre;

import java.util.Objects;

public class AdaptateurFiltre implements Filtre {
    private final ImplNonStandard implementation;
    private final String filterName;

    public AdaptateurFiltre() {
        this(new ImplNonStandard(), "inversion");
    }

    public AdaptateurFiltre(ImplNonStandard implementation, String filterName) {
        this.implementation = Objects.requireNonNull(implementation, "implementation");
        this.filterName = Objects.requireNonNull(filterName, "filterName");
    }

    @Override
    public int[] filter(int[] data) {
        return implementation.appliquerFiltre(filterName, data);
    }
}
