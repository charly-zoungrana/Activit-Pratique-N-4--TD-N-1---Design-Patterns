package net.zoungrana.exercice2.application;

import net.zoungrana.exercice2.TraitementImage;
import net.zoungrana.exercice2.adaptation.AdaptateurFiltre;
import net.zoungrana.exercice2.compression.Compresseur;
import net.zoungrana.exercice2.compression.CompresseurRle;
import net.zoungrana.exercice2.filtrage.Filtre;
import net.zoungrana.exercice2.filtrage.FiltreSeuil;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public final class ApplicationTraitementImage {
    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            lancer(console);
        }
    }

    private static void lancer(Scanner console) {
        System.out.println("Exemples de filtres : " + FiltreSeuil.class.getName()
                + " ou " + AdaptateurFiltre.class.getName());
        Optional<Filtre> filtre = lireImplementation(console, "filtre", Filtre.class);
        if (filtre.isEmpty()) {
            return;
        }

        System.out.println("Exemple de compresseur : " + CompresseurRle.class.getName());
        Optional<Compresseur> compresseur = lireImplementation(console, "compresseur", Compresseur.class);
        if (compresseur.isEmpty()) {
            return;
        }

        TraitementImage traitement = new TraitementImage(filtre.get(), compresseur.get());
        int[] image = {20, 20, 140, 140, 140, 230, 30, 30};
        int[] imageFiltree = traitement.filter(image);
        int[] imageCompressee = traitement.compresser(imageFiltree);

        System.out.println("Image de départ : " + Arrays.toString(image));
        System.out.println("Image filtrée : " + Arrays.toString(imageFiltree));
        System.out.println("Image filtrée puis compressée : " + Arrays.toString(imageCompressee));
    }

    private static <T> Optional<T> lireImplementation(Scanner console, String role, Class<T> contrat) {
        while (true) {
            System.out.print("Nom complet de la classe pour le " + role + " : ");
            if (!console.hasNextLine()) {
                return Optional.empty();
            }

            String nom = console.nextLine().strip();
            try {
                Class<? extends T> implementation =
                        Class.forName(nom, false, contrat.getClassLoader()).asSubclass(contrat);
                return Optional.of(implementation.getConstructor().newInstance());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe introuvable : " + nom);
            } catch (ClassCastException e) {
                System.out.println(nom + " doit être compatible avec " + contrat.getSimpleName() + ".");
            } catch (InvocationTargetException e) {
                System.out.println("Le constructeur de " + nom + " a échoué : " + e.getCause());
            } catch (ReflectiveOperationException e) {
                System.out.println(nom + " doit être une classe concrète publique avec un constructeur public sans argument.");
            } catch (LinkageError e) {
                System.out.println("Impossible de charger " + nom + " : " + e.getMessage());
            }
        }
    }
}
