# Exercice 1 : Conception & Application des Patrons de Conception

**Module :** Conception Orientée Objet & Design Patterns  
**Enseignant :** Pr. Mohamed Youssfi  
**Étudiant :** ZOUNGRANA Charly  
**Branche Git :** `exercice-1`

---

## Sommaire de l'Exercice

L'Exercice 1 porte sur l'analyse de situations de conception récurrentes et l'identification des design patterns appropriés, accompagnés de diagrammes de classes et d'implémentations de référence :

1. [Question 1 : Modélisation de figures géométriques simples et composites](#question-1--modélisation-de-figures-géométriques-simples-et-composites) *(Traité)*
2. [Question 2 : Squelette d'algorithme et instanciation dynamique de plugins](#question-2--squelette-dalgorithme-et-instanciation-dynamique-de-plugins) *(Traité)*
3. [Question 3 : Extension dynamique de responsabilités d'un composant](#question-3--extension-dynamique-de-responsabilités-dun-composant) *(Traité)*
4. [Question 4 : Système de notification d'état à couplage faible](#question-4--système-de-notification-détat-à-couplage-faible) *(Traité)*

---

## Question 1 : Modélisation de figures géométriques simples et composites

### 1. Énoncé
> *« Une figure peut être soit un cercle, un rectangle ou un groupe de figures. »*

La problématique consiste à manipuler de façon indifférenciée des figures géométriques élémentaires (feuilles de l'arborescence) et des regroupements de figures (nœuds composites), tout en offrant les mêmes opérations (calcul de l'aire, affichage) sans coupler le code client aux types concrets.

---

### 2. Design Pattern Approprié : **Composite**

Le patron de conception structurel **Composite** répond rigoureusement à cette exigence :

- **Hiérarchie récursive (Partie / Tout) :** Il permet d'agréger des objets simples et des conteneurs d'objets au sein d'une structure arborescente unifiée.
- **Transparence opérationnelle :** Le client interagit uniquement avec l'abstraction parente (`Figure`). L'appel à `calculerAire()` ou `afficherFigure()` produit le résultat attendu, que la cible soit un élément isolé ou un ensemble composé.
- **Extensibilité (Principe Ouvert/Fermé) :** De nouvelles primitives (ex. `Triangle`, `Ellipse`) ou d'autres types de conteneurs peuvent être ajoutés sans altérer le code existant.

---

### 3. Diagramme de Classes

Le diagramme suivant présente la structure mise en œuvre pour la Question 1 :

![Diagramme de classes - Pattern Composite](./diagramme-classe-exo-1-question1-real.png)

---

### 4. Description de l'Implémentation

Le code est structuré dans le package `net.zoungrana.question1` :

#### A. Classe Abstraite `Figure` (Composant)
Point d'entrée du contrat d'abstraction :
- Définit l'attribut protégé `name`.
- Déclare les opérations communes abstraites :
  - `public abstract double calculerAire();`
  - `public abstract void afficherFigure();`

#### B. Classes Feuilles : `Cercle` et `Rectangle`
Implémentations concrètes des formes élémentaires :
- **`Cercle` :** gère l'attribut `rayon`, calcule son aire via $\pi \cdot \text{rayon}^2$ et affiche sa description spécifique.
- **`Rectangle` :** gère `largeur` et `longueur`, calcule son aire par $\text{largeur} \times \text{longueur}$ et affiche ses dimensions.

#### C. Classe Composite : `GroupeFigure`
Gestionnaire de composition :
- Encapsule une liste d'éléments `List<Figure> figures = new ArrayList<>()`.
- Fournit la méthode d'agrégation `public Figure ajouterFigure(Figure f)`.
- **Délégation récursive :**
  - `calculerAire()` : calcule la somme des aires de tous les sous-éléments via la Stream API (`figures.stream().mapToDouble(Figure::calculerAire).sum()`).
  - `afficherFigure()` : affiche le bloc d'en-tête du groupe et délègue l'affichage à chaque figure fille (`figures.forEach(Figure::afficherFigure)`).

---

### 5. Programme de Test

La classe `Test` valide la cohérence du patron :
- Instanciation d'un `Cercle` de rayon $5.0$.
- Instanciation d'un `Rectangle` de dimensions $5.0 \times 10.0$.
- Composition au sein d'une instance `GroupeFigure` (« Groupe1 »).
- Appel de `afficherFigure()` sur chaque entité et sur le groupe consolidé.

```java
package net.zoungrana.question1;

public class Test {
    public static void main(String[] args) {
        Cercle c = new Cercle("Cercle 1", 5);
        c.afficherFigure();

        Rectangle r = new Rectangle("Rectangle 1", 5, 10);
        r.afficherFigure();

        GroupeFigure g = new GroupeFigure("Groupe1");
        g.ajouterFigure(c);
        g.ajouterFigure(r);
        g.afficherFigure();
    }
}
```

---

### 6. Trace d'Exécution

#### Capture écran de la console
![Trace d'exécution console](./exo1-question-1-execution.png)


---

## Question 2 : Squelette d'algorithme et instanciation dynamique de plugins

### 1. Énoncé
> *« Un plugin contient une opération implémentant le squelette d’un algorithme dont deux parties (partie1 et partie2) sont variables. On voudrait laisser au développeur la possibilité d’implémenter les deux parties manquantes de cet algorithme et on voudrait aussi que l’application cliente puisse instancier une implémentation concrète du plugin sans connaître sa classe d’implémentation. »*

Cette problématique introduit deux contraintes majeures de génie logiciel :
1. **Contrainte algorithmique :** Fixer la structure d'un processus global tout en externalisant certaines étapes modulables auprès de sous-classes.
2. **Contrainte d'instanciation :** Isoler le code client de toute connaissance des classes concrètes d'implémentation lors de la création d'instances.

---

### 2. Choix et Justification des Design Patterns

Pour satisfaire ces deux exigences de manière découplée, deux patrons de conception complémentaires sont combinés :

#### A. Le patron comportemental : **Template Method** (*Patron de Méthode / Méthode Modèle*)
- **Définition :** En ingénierie logicielle et selon la classification du Gang of Four (GoF), le patron **Template Method** définit le squelette invariant d'un algorithme dans une méthode de la classe mère, en déléguant la définition de certaines étapes primitives (abstraites ou configurables) aux sous-classes, sans pour autant altérer l'ordonnancement global du processus.
- **Principe d'Inversion de Contrôle (*Hollywood Principle*) :** Contrairement à un flux impératif classique, c'est la classe abstraite parente qui pilote le flux d'exécution et appelle les méthodes des sous-classes (*« Ne nous appelez pas, nous vous appellerons »*).
- **Application au cas d'étude :**
  - La classe abstraite `Plugin` déclare la méthode modèle `appliquerAlgo()`.
  - Cette méthode enchaîne rigoureusement les étapes invariantes (`preparer()` et `terminer()`) et les étapes modulables abstraites (`algoPart1()` et `algoPart2()`).
  - Les sous-classes (`PluginA`, `PluginB`) fournissent leurs implémentations spécifiques des étapes sans pouvoir modifier la séquence d'exécution globale.

#### B. Le patron créationnel : **Factory** (*Fabrique Simple / Simple Factory*)
- **Définition :** Le patron **Factory** délègue la responsabilité de l'instanciation des objets à une entité dédiée (`PluginFactory`).
- **Application au cas d'étude :**
  - La méthode statique `PluginFactory.creerPlugin(String pluginType)` instancie l'implémentation concrète demandée (`PluginA` ou `PluginB`) selon un identifiant passé en paramètre.
  - Le code client manipule exclusivement le type abstrait `Plugin`, garantissant un **couplage faible** et respectant le principe d'inversion des dépendances (DIP - *Dependency Inversion Principle*).

---

### 3. Diagramme de Classes

Le diagramme de classes illustre l'organisation entre l'abstraction, les implémentations concrètes et la fabrique :

![Diagramme de classes - Pattern Template Method & Factory](./diagramme-classe-exo-1-question-2.png)

#### Analyse des relations :
- `Plugin` est une classe abstraite déclarant la méthode modèle `appliquerAlgo()`, les méthodes invariantes (`preparer()`, `terminer()`) et les méthodes abstraites (`algoPart1()`, `algoPart2()`).
- `PluginA` et `PluginB` héritent de `Plugin` et fournissent l'implémentation concrète des deux étapes primitives.
- `PluginFactory` possède une relation de dépendance de création (`« create »` / `peut créer`) vers `PluginA` et `PluginB`. Elle retourne un objet typé par l'abstraction `Plugin`.

---

### 4. Description de l'Implémentation

Le code est structuré dans le package `net.zoungrana.question2` :

#### A. Classe Abstraite `Plugin` (Template)
Elle structure l'algorithme :
```java
package net.zoungrana.question2;

public abstract class Plugin {

    public void appliquerAlgo(){
        preparer();
        algoPart1();
        algoPart2();
        terminer();
    }

    private void preparer(){
        System.out.println("Preparation");
    }

    public abstract void algoPart1();
    public abstract void algoPart2();

    private void terminer(){
        System.out.println("Terminer");
    }
}
```
- `appliquerAlgo()` : méthode patron définissant la cinématique du traitement.
- `preparer()` et `terminer()` : étapes invariantes privées garantissant le cadre d'exécution.
- `algoPart1()` et `algoPart2()` : méthodes abstraites publiques constituant les points d'extension obligatoires.

#### B. Classes Concrètes : `PluginA` et `PluginB`
Chaque classe concrétise le comportement spécifique des deux étapes variables :
- **`PluginA` :**
  ```java
  package net.zoungrana.question2;

  public class PluginA extends Plugin {
      @Override
      public void algoPart1() {
          System.out.println("**************** Plugin A *******************");
          System.out.println("Algo part 1 depuis Plugin A");
          System.out.println("********************************************");
      }

      @Override
      public void algoPart2() {
          System.out.println("**************** Plugin A *******************");
          System.out.println("Algo part 2 depuis Plugin A");
          System.out.println("********************************************");
      }
  }
  ```
- **`PluginB` :** redéfinit également `algoPart1()` et `algoPart2()` avec un affichage adapté à `PluginB`.

#### C. Classe Créationnelle : `PluginFactory`
Elle centralise et isole la création des instances :
```java
package net.zoungrana.question2;

public class PluginFactory {
    public static Plugin creerPlugin(String pluginType){
        return switch (pluginType) {
            case "A" -> new PluginA();
            case "B" -> new PluginB();
            default -> throw new IllegalArgumentException("Le type de plugin n'existe pas");
        };
    }
}
```
Grâce à cette fabrique, le client n'a jamais besoin de faire `new PluginA()` ou `new PluginB()`. L'ajout d'une nouvelle variante de plugin se fait sans modifier le code client existant.

---

### 5. Programme de Test et Explication

La classe `Test` illustre l'utilisation transparente et découplée des composants :

```java
package net.zoungrana.question2;

public class Test {
    public static void main(String[] args) {

        Plugin pluginA = PluginFactory.creerPlugin("A");
        pluginA.appliquerAlgo();

        Plugin pluginB = PluginFactory.creerPlugin("B");
        pluginB.appliquerAlgo();

    }
}
```

#### Déroulement de l'exécution :
1. **Instanciation via la fabrique :** Le client sollicite `PluginFactory.creerPlugin("A")` puis `PluginFactory.creerPlugin("B")`. Le type de référence utilisé est exclusivement le type abstrait `Plugin`.
2. **Exécution du Template Method :** Lors de l'appel `pluginA.appliquerAlgo()`, le flux s'exécute selon l'ordonnancement fixé par la classe `Plugin` :
   - `preparer()` est exécutée (affichage `"Preparation"`).
   - `algoPart1()` spécifique à `PluginA` est exécutée.
   - `algoPart2()` spécifique à `PluginA` est exécutée.
   - `terminer()` est exécutée (affichage `"Terminer"`).
3. Le même cycle se répète de façon homogène pour `PluginB`.

---

### 6. Trace d'Exécution

#### Capture écran de la console
![Trace d'exécution console](./execution-exo1-question-2.png)



---

## Question 3 : Extension dynamique de responsabilités d'un composant

### 1. Énoncé
> *« On dispose d’un composant implémentant une interface qui définit une opération « traitement() ». On voudrait rattacher à ce composant des responsabilités supplémentaires sans modifier son code source. C'est-à-dire envelopper l’exécution de la méthode traitement par d’autres traitements avant et après son exécution. »*

Cette situation pose une problématique classique d'extensibilité logicielle : comment enrichir le comportement d'une classe existante sans altérer son code source, sans briser l'encapsulation et sans recourir à une sous-classification statique excessive ?

---

### 2. Choix et Justification du Design Pattern : **Decorator** (*Décorateur / Wrapper*)

Le patron structurel **Decorator** (GoF) répond fidèlement à cette exigence :

- **Attachement dynamique de responsabilités :** Contrairement à l'héritage qui fige les comportements au moment de la compilation, le décorateur permet d'adjoindre des fonctionnalités supplémentaires à un objet dynamiquement à l'exécution (*runtime*).
- **Principe Ouvert / Fermé (Open/Closed Principle - OCP) :** La classe de base (`Composant`) reste intacte et fermée à la modification, tout en étant ouverte à des extensions fonctionnelles par l'ajout de nouveaux décorateurs.
- **Transparence et substituabilité :** Le décorateur abstrait (`DecorateurAbstrait`) et le composant concret (`Composant`) partagent la même interface racine (`TraitementInterface`). Le client manipule ainsi les objets décorés de manière transparente sans changer de type d'abstraction.
- **Enveloppement avant / après (*Wrapping*) :** Les décorateurs concrets (`ResponsabiliteSupADecorator`, `ResponsabiliteSupBDecorator`) interceptent l'appel à `traitement()`, injectent des traitements préliminaires (pré-traitements), délèguent l'exécution au composant enveloppé (`composant.traitement()`), puis déclenchent des traitements complémentaires (post-traitements).
- **Composition récursive et combinatoire :** Ce patron permet d'empiler plusieurs décorateurs de manière cumulative (`ResponsabiliteSupBDecorator` enveloppant `ResponsabiliteSupADecorator`, qui enveloppe `Composant`), évitant ainsi l'explosion combinatoire de sous-classes.

---

### 3. Diagramme de Classes

Le diagramme de classes illustre la structure du patron Decorator mise en œuvre :

![Diagramme de classes - Pattern Decorator](./diagramme-classe-exo-1-question-3.png)

#### Analyse des relations :
- **`TraitementInterface` :** Interface racine commune exposant l'opération abstraite `+ traitement() : void`.
- **`Composant` :** Implémentation concrète de base réalisant `TraitementInterface`.
- **`DecorateurAbstrait` :** Classe abstraite implémentant `TraitementInterface` et maintenant une relation de composition/agrégation `1..1` vers une instance de `TraitementInterface` (`# composant`).
- **`ResponsabiliteSupADecorator` & `ResponsabiliteSupBDecorator` :** Décorateurs concrets dérivant de `DecorateurAbstrait` et redéfinissant `traitement()` pour intercaler leurs traitements spécifiques avant et après la délégation.

---

### 4. Description de l'Implémentation

Le code est structuré sous le package `net.zoungrana.question3` :

#### A. Interface Commune : `TraitementInterface`
Définit le contrat partagé entre le composant originel et l'ensemble de ses décorateurs :
```java
package net.zoungrana.question3;

public interface TraitementInterface {
    void traitement();
}
```

#### B. Composant Concret : `Composant`
Réalise le traitement standard initial sans préoccupation transversale :
```java
package net.zoungrana.question3;

public class Composant implements TraitementInterface {
    @Override
    public void traitement() {
        System.out.println("***************************");
        System.out.println("Traitement de base");
        System.out.println("***************************");
    }
}
```

#### C. Décorateur Abstrait : `DecorateurAbstrait`
Maintient l'état du composant encapsulé :
```java
package net.zoungrana.question3;

public abstract class DecorateurAbstrait implements TraitementInterface {
    protected TraitementInterface composant;

    public DecorateurAbstrait(TraitementInterface composant){
        this.composant = composant;
    }
}
```

#### D. Décorateurs Concrets : `ResponsabiliteSupADecorator` & `ResponsabiliteSupBDecorator`
Chaque décorateur concrétise le mécanisme d'interception :
- **`ResponsabiliteSupADecorator` :**
  ```java
  package net.zoungrana.question3;

  public class ResponsabiliteSupADecorator extends DecorateurAbstrait {

      public ResponsabiliteSupADecorator(TraitementInterface composant){
          super(composant);
      }

      @Override
      public void traitement() {
          System.out.println("************ Traitement supplémentaire A  ***************");
          composant.traitement();
          System.out.println("************** Traitement supplémentaire A *************");
      }
  }
  ```
- **`ResponsabiliteSupBDecorator` :**
  ```java
  package net.zoungrana.question3;

  public class ResponsabiliteSupBDecorator extends DecorateurAbstrait {

      public ResponsabiliteSupBDecorator(TraitementInterface composant){
          super(composant);
      }

      @Override
      public void traitement() {
          System.out.println("************ Traitement Supplémentaire B ***************");
          composant.traitement();
          System.out.println("*************** Traitement Supplémentaire B ************");
      }
  }
  ```

---

### 5. Programme de Test et Explication

La classe `Test` démontre la composition dynamique et le chaînage d'enveloppes :

```java
package net.zoungrana.question3;

public class Test {
    public static void main(String[] args) {
        TraitementInterface c = new Composant();
        c = new ResponsabiliteSupADecorator(c);
        c.traitement();

        System.out.println("-----------------------------------------");

        c = new ResponsabiliteSupBDecorator(c);
        c.traitement();
    }
}
```

#### Déroulement de l'exécution :
1. **Étape 1 — Décoration simple :** L'instance de `Composant` est enveloppée dans `ResponsabiliteSupADecorator`. L'appel `c.traitement()` déclenche le pré-traitement A, puis le traitement de base, puis le post-traitement A.
2. **Étape 2 — Décoration cumulative :** La référence `c` (déjà décorée par A) est à nouveau enveloppée dans `ResponsabiliteSupBDecorator`. L'appel `c.traitement()` produit une exécution emboîtée de l'extérieur vers l'intérieur :
   - Déclenchement du pré-traitement B.
   - Délégation à `composant` (qui est le décorateur A) : exécution du pré-traitement A.
   - Délégation au `Composant` d'origine : exécution du traitement de base.
   - Exécution du post-traitement A.
   - Exécution finale du post-traitement B.

---

### 6. Trace d'Exécution

#### Capture écran de la console
![Trace d'exécution console](./execution-exo1-question-3.png)


---

## Question 4 : Système de notification d'état à couplage faible

### 1. Énoncé
> *« On désire créer une classe Joueur ayant un état représenté par une variable score de type int. On voudrait que les objets de l’environnement du jeu (Couloir, Caméra et Gardien) soient informés à chaque fois que le score du joueur change tout en gardant un couplage faible entre la classe Joueur et les autres classes. »*

Cette problématique requiert la mise en place d'un canal de communication un-à-plusieurs (*one-to-many*) réactif, assurant la synchronisation en temps réel de plusieurs entités hétérogènes de l'environnement du jeu en fonction des variations d'état d'un joueur, tout en éliminant toute adhérence forte entre l'émetteur et les récepteurs.

---

### 2. Choix et Justification du Design Pattern : **Observer** (*Observateur / Publish-Subscribe*)

Le patron comportemental **Observer** (GoF) constitue la solution de référence pour répondre à ce besoin :

- **Découplage et couplage faible (*Loose Coupling*) :** La classe émettrice (`Joueur`, rôle de Sujet / *Observable*) n'a aucune dépendance directe envers les classes concrètes de l'environnement (`Camera`, `Couloir`, `Gardien`). Elle communique uniquement à travers l'interface abstraite `Observer`.
- **Souscription dynamique :** Les observateurs peuvent s'abonner (`subscribe`) ou se désabonner (`unsubscribe`) dynamiquement au cours du cycle de vie du jeu, sans exiger la moindre modification de la classe `Joueur`.
- **Principe Ouvert / Fermé (Open/Closed Principle - OCP) :** De nouveaux éléments de gameplay (ex. `Piege`, `TableauScores`, `Boss`) peuvent être introduits dans le système en implémentant simplement le contrat `Observer`, sans impacter l'architecture existante.
- **Modèle de notification souple (Mode *Pull*) :** Lors de l'invocation de `notifyObservers()`, le joueur transmet sa propre référence (`this`) à chaque observateur via `observer.update(this)`. Chaque entité observatrice interroge alors sélectivement l'état (`joueur.getScore()`) et applique ses propres critères et logiques métier (ex. réactivité immédiate pour la caméra, seuil à 5 pour le couloir, seuil à 10 pour le gardien).

---

### 3. Diagramme de Classes

Le diagramme de classes ci-dessous modélise l'architecture du patron Observer mise en œuvre :

![Diagramme de classes - Pattern Observer](./diagramme-classe-exo-1-question-4.png)

#### Analyse des relations :
- **`Observable` :** Interface définissant le contrat de gestion des abonnements (`subscribe(Observer o)`, `unsubscribe(Observer o)`) et de notification (`notifyObservers()`).
- **`Joueur` :** Sujet concret (*Concrete Subject*) implémentant `Observable`, encapsulant l'état `score : int` et agrégeant une collection polymorphique `List<Observer> observers`.
- **`Observer` :** Interface abstraite pour les récepteurs, déclarant la méthode de mise à jour `update(Joueur joueur)`.
- **`Camera`, `Couloir`, `Gardien` :** Observateurs concrets (*Concrete Observers*) implémentant `Observer` et définissant leur propre comportement en réaction aux variations du score.

---

### 4. Description de l'Implémentation

Le code est structuré sous le package `net.zoungrana.question4` :

#### A. Interface du Sujet : `Observable`
Définit le contrat d'enregistrement et d'avertissement :
```java
package net.zoungrana.question4;

public interface Observable {
    void subscribe(Observer o);
    void unsubscribe(Observer o);
    void notifyObservers();
}
```

#### B. Interface des Observateurs : `Observer`
Définit le point d'entrée pour la réception des notifications :
```java
package net.zoungrana.question4;

public interface Observer {
    void update(Joueur joueur);
}
```

#### C. Sujet Concret : `Joueur`
Encapsule l'état et orchestre la diffusion des événements :
```java
package net.zoungrana.question4;

import java.util.ArrayList;
import java.util.List;

public class Joueur implements Observable {
    private int score;
    List<Observer> observers = new ArrayList<>();

    @Override
    public void subscribe(Observer o) {
        observers.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> observer.update(this));
    }

    public void setScore(int score) {
        this.score = score;
        notifyObservers();
    }

    public int getScore() {
        return score;
    }
}
```

#### D. Observateurs Concrets de l'Environnement
Chaque entité concrétise une réaction contextualisée au score du joueur :
- **`Camera` :** Réagit systématiquement à chaque changement de score.
  ```java
  package net.zoungrana.question4;

  public class Camera implements Observer {
      @Override
      public void update(Joueur joueur) {
          System.out.println("****************** Caméra *******************");
          System.out.println("Camera: " + joueur.getScore());
          System.out.println("******************** Caméra *****************");
      }
  }
  ```
- **`Couloir` :** Ne s'active que si le score atteint ou dépasse le seuil de 5.
  ```java
  package net.zoungrana.question4;

  public class Couloir implements Observer {
      @Override
      public void update(Joueur joueur) {
          if (joueur.getScore() >= 5) {
              System.out.println("+++++++++++++++++++++ Couloir ++++++++++++++++++++++");
              System.out.println("Couloir: " + joueur.getScore());
              System.out.println("+++++++++++++++++++++ Couloir ++++++++++++++++++++++");
          }
      }
  }
  ```
- **`Gardien` :** Ne réagit que si le score atteint ou dépasse le palier d'alerte de 10.
  ```java
  package net.zoungrana.question4;

  public class Gardien implements Observer {
      @Override
      public void update(Joueur joueur) {
          if (joueur.getScore() >= 10) {
              System.out.println("====================== Gardien ===================");
              System.out.println("Gardien: " + joueur.getScore());
              System.out.println("====================== Gardien ====================");
          }
      }
  }
  ```

---

### 5. Programme de Test et Explication

La classe `Test` orchestre la souscription des observateurs et simule deux variations d'état :

```java
package net.zoungrana.question4;

import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Joueur joueur = new Joueur();
        Camera c = new Camera();
        Gardien g = new Gardien();
        Couloir co = new Couloir();

        Stream.of(c, g, co).forEach(joueur::subscribe);
        joueur.setScore(5);

        System.out.println("-----------------------------------------");
        joueur.setScore(15);
    }
}
```

#### Déroulement de l'exécution :
1. **Enregistrement des abonnés :** Les instances `Camera`, `Gardien` et `Couloir` s'abonnent auprès de l'instance `Joueur`.
2. **Premier événement (`score = 5`) :**
   - La méthode `setScore(5)` déclenche `notifyObservers()`.
   - `Camera` notifie immédiatement la valeur 5.
   - `Gardien` vérifie sa condition ($5 \ge 10$ : faux) et reste silencieux.
   - `Couloir` valide sa condition ($5 \ge 5$ : vrai) et affiche son message d'alerte.
3. **Second événement (`score = 15`) :**
   - La méthode `setScore(15)` déclenche `notifyObservers()`.
   - `Camera` affiche la nouvelle valeur 15.
   - `Gardien` valide sa condition ($15 \ge 10$ : vrai) et déclenche son alerte.
   - `Couloir` valide sa condition ($15 \ge 5$ : vrai) et affiche son alerte.

---

### 6. Trace d'Exécution

#### Capture écran de la console
![Trace d'exécution console](./execution-exo1-question-4.png)

---

## Arborescence du Projet (Branche `exercice-1`)

```text
TD-1-Design-Pattern/
├── pom.xml
├── README.md
├── diagramme-classe-exo-1-question1-real.png
├── diagramme-classe-exo-1-question-2.png
├── diagramme-classe-exo-1-question-3.png
├── diagramme-classe-exo-1-question-4.png
├── exo1-question-1-execution.png
├── execution-exo1-question-2.png
├── execution-exo1-question-3.png
├── execution-exo1-question-4.png
└── src/
    └── main/
        └── java/
            └── net/
                └── zoungrana/
                    ├── question1/
                    │   ├── Figure.java
                    │   ├── Cercle.java
                    │   ├── Rectangle.java
                    │   ├── GroupeFigure.java
                    │   └── Test.java
                    ├── question2/
                    │   ├── Plugin.java
                    │   ├── PluginA.java
                    │   ├── PluginB.java
                    │   ├── PluginFactory.java
                    │   └── Test.java
                    ├── question3/
                    │   ├── TraitementInterface.java
                    │   ├── Composant.java
                    │   ├── DecorateurAbstrait.java
                    │   ├── ResponsabiliteSupADecorator.java
                    │   ├── ResponsabiliteSupBDecorator.java
                    │   └── Test.java
                    └── question4/
                        ├── Observable.java
                        ├── Observer.java
                        ├── Joueur.java
                        ├── Camera.java
                        ├── Couloir.java
                        ├── Gardien.java
                        └── Test.java
```
