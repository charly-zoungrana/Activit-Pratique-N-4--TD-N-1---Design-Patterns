# Travail à Rendre — Conception Orientée Objet & Design Patterns

**Module :** Conception Orientée Objet & Design Patterns  
**Enseignant :** Pr. Mohamed Youssfi  
**Étudiant :** ZOUNGRANA Charly  
**Branche Git courante :** `main` (Portail & Synthèse globale)

---

## Présentation Générale
Ce dépôt regroupe l'ensemble des travaux réalisés dans le cadre du TD 1 du module Design Pattern. L'objectif est d'appliquer les principes fondamentaux de l'ingénierie logicielle  à travers la résolution de cas d'études concrets et le développement d'un framework extensible.

Le travail est articulé en deux exercices indépendants, gérés sur des branches Git dédiées afin d'assurer une traçabilité rigoureuse :

1. **Exercice 1 :** Étude et mise en œuvre des patrons de conception fondamentaux.
2. **Exercice 2 :** Conception et développement d'un Framework de traitement d'images.

---

## Organisation des Branches Git

Le projet suit une stratégie de branches thématiques :

| Branche Git | Contenu & Objectif | Statut | Accès direct |
| :--- | :--- | :---: | :--- |
| **`main`** | Portail général, synthèse globale et navigation inter-branches | **Actif** | *Branche courante* |
| **`exercice-1`** | Solutions complètes des 4 situations (Composite, Template Method, Factory, Decorator, Observer), diagrammes et traces d'exécution | **Terminé** | [Consulter la branche `exercice-1`](https://github.com/charly-zoungrana/Activit-Pratique-N-4--TD-N-1---Design-Patterns/tree/exercice-1) |
| **`exercice-2`** | Framework de traitement d'images (Strategy, Adapter, Template Method, instanciation dynamique) | *En cours* | [Consulter la branche `exercice-2`](https://github.com/charly-zoungrana/Activit-Pratique-N-4--TD-N-1---Design-Patterns/tree/exercice-2) *(bientôt disponible)* |

---

## Synthèse de l'Exercice 1 : Patrons Fondamentaux

L'Exercice 1 traite quatre problématiques de conception représentatives des défis du génie logiciel. Chaque problème a fait l'objet d'une analyse conceptuelle, d'un diagramme de classes de référence,et d'une implémentation Java.

### Récapitulatif des Situations Traitées

| Question | Problématique de Conception | Design Pattern(s) Retenu(s) | Catégorie GoF | Rôle Architectural |
| :--- | :--- | :--- | :--- | :--- |
| **Question 1** | Traitement unifié des figures simples (Cercle, Rectangle) et des groupes | **Composite** | Structurel | Permet de manipuler des objets individuels et des arborescences composites de manière transparente. |
| **Question 2** | Squelette d'algorithme avec étapes variables & instanciation découplée | **Template Method** & **Simple Factory** | Comportemental & Créationnel | Fixe la cinématique algorithmique via l'inversion de contrôle et isole la création des plugins. |
| **Question 3** | Extension dynamique de responsabilités sans modification du code source | **Decorator** (*Wrapper*) | Structurel | Enveloppe dynamiquement un composant par des pré/post-traitements sans sous-classification statique. |
| **Question 4** | Notification d'état en temps réel avec couplage faible (Joueur / Environnement) | **Observer** (*Pub/Sub*) | Comportemental | Assure la synchronisation un-à-plusieurs réactive entre le joueur et les éléments du jeu. |

### Accès au Rapport et au Code Source de l'Exercice 1

Le rapport détaillé, les diagrammes de classes UML et les captures d'exécution pour chaque question sont consultables directement sur la branche dédiée :

- **Rapport complet :** [README de la branche `exercice-1`](https://github.com/charly-zoungrana/Activit-Pratique-N-4--TD-N-1---Design-Patterns/blob/exercice-1/README.md)
- **Code source Java :** [`src/main/java/net/zoungrana/`](https://github.com/charly-zoungrana/Activit-Pratique-N-4--TD-N-1---Design-Patterns/tree/exercice-1/src/main/java/net/zoungrana)
  - `question1/` : Modélisation des figures géométriques (Composite).
  - `question2/` : Algorithme à squelette invariant et fabrique de plugins (Template Method & Factory).
  - `question3/` : Composant et décorateurs de traitement (Decorator).
  - `question4/` : Modèle joueur/environnement (Observer).

Pour basculer localement sur la branche de l'Exercice 1 :
```bash
git checkout exercice-1
```

---

## Présentation de l'Exercice 2 : Framework de Traitement d'Images

L'Exercice 2 a pour objectif la conception et l'implémentation d'un **Framework orienté objet extensible** dédié au traitement d'images numériques représentées par des matrices unidimensionnelles d'entiers (`int[] data`).

### Spécifications & Contraintes du Framework

1. **Opérations Clés :**
   - **Filtrage :** `public int[] filter(int[] data)`
   - **Compression :** `public int[] compresser(int[] data)`

2. **Principes Architecturaux :**
   - **Principe Ouvert/Fermé (OCP) :** Le framework doit être fermé à la modification mais totalement ouvert aux extensions sans recompilation de son noyau.
   - **Interchangeabilité dynamique des filtres :** Possibilité d'ajouter de nouvelles stratégies de filtrage et de commuter l'algorithme actif à l'exécution (*Runtime*) $\rightarrow$ **Patron Strategy**.
   - **Interopérabilité avec l'existant :** Intégration transparente d'une ancienne classe non conforme `ImplNonStandard` (`public int[] appliquerFiltre(String filterName, int[] data)`) sans altérer le contrat du framework $\rightarrow$ **Patron Adapter**.
   - **Squelette de compression standardisé :** Définition de la trame algorithmique de compression tout en déléguant les variantes aux sous-classes $\rightarrow$ **Patron Template Method**.
   - **Configuration dynamique :** Application cliente permettant à l'utilisateur de charger dynamiquement les classes d'implémentation par saisie de leur nom qualifié (instanciation dynamique via réflexion ou fabrique).

> Le lien vers la branche `exercice-2` ainsi que son rapport détaillé seront intégrés ici dès la finalisation des développements correspondants.

---

## Instructions Générales d'Exécution

### Prérequis
- **Java Development Kit (JDK) :** version 17 ou supérieure (version 21 recommandée).
- **Apache Maven :** version 3.8+.
- **Git**

### Compilation et Tests
```bash
# Cloner le dépôt
git clone git@github-perso:charly-zoungrana/Activit-Pratique-N-4--TD-N-1---Design-Patterns.git
cd Activit-Pratique-N-4--TD-N-1---Design-Patterns

# Basculer sur la branche de l'exercice souhaité
git checkout exercice-1

# Compiler le projet
mvn clean compile
```
