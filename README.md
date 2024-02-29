# HEIG_MCR_Labo1

## Brainstorming
```mermaid
classDiagram
    direction BT
    class App {
        + main(String[]) void
    }
    class Jpanel{
        
    }
    class AnalogClock {
        + update() void
        - calculateNeedleEndPoint(int, double) Point
        - drawBackground(Graphics2D) void
        - drawClockHands(Graphics2D) void
        - drawNeedle(Graphics2D, Color, int, int, int, int) void
        + paintComponent(Graphics) void
        - calculateNeedleAngle(int, int) double
        Graphics2D renderingHints
    }

    class Chrono {
        - int id
        + toggle() void
        + stop() void
        + start() void
        + reset() void
        int id
        SimpleTime time
    }
    class ClockFrame
    class ClockPanel {
        + detachFromChrono() void
    }
    class ControlPanel {
        - addButton(String, JPanel, ActionListener) void
        - showClocks(LinkedList~Chrono~, Function~Chrono, ClockPanel~, int) void
        - addSingleClockButtons(JPanel, Chrono) void
        - showClock(Chrono, Function~Chrono, ClockPanel~) void
        - addMultiClockButtons(JFrame, LinkedList~Chrono~, int) void
    }
    class NumericClock {
        + update() void
    }
    class Observer {
        <<Interface>>
        + update() void
    }
    class SimpleTime {
        + toString() String
        + increment(int) void
        int hours
        int minutes
        int seconds
    }
    class Subject {
        + notifyObservers() void
        + attach(Observer) void
        + detach(Observer) void
    }

    AnalogClock  -->  ClockPanel
    Chrono  -->  Subject
    ClockPanel  ..>  Observer
    NumericClock  -->  ClockPanel
    ClockPanel --|>Jpanel
    ClockFrame o-- ClockPanel
```


## Question à poser

1. Faut-il implémenter les RomanClock et ArabicClock comme sous-classe de AnalogClock, ou passer les styles en paramètres avec un enum?
   1. mieux d'avoir des sous classes pour pouvoir modifier des comportements par la suite
2. ClockFrame: Faut-il hériter de JFrame ou juste utiliser une composition? ok
3. Commentaires: Faut-il faire de la javadoc pour les champs privés des classes? pas besoin et pas besoin pour les getters et les champs et méthodes triviales. surtout les méthodes avec des algos pas évidents.
4. Faut-il rendre un rapport ou juste UML suffit? pas de rapport
5. cache Overkill ou à garder? ok
6. Peut-on utiliser les champs privés / données locales dans une fonction privée ou doit-on les passer en paramètres? oui
7. Dans l'UML si on implémente une interface, doit-on redéclarer les méthodes de l'interface dans la classe (ou sous-classe) qui l'implémente? oui utile pour savoir où dans les sous classes
8. Faut-il stocker ControlPanel nbClock ? oui c'est bien

## TODO
* Error management / exception handling -> pas besoin de gérer si pas public

- [ ] Chercher à mettre des paramètres de méthodes en attributs privés (Graphics2D par ex.)
- [ ] Checker javadoc (paramètre retirés et modifiés)
- [ ] Relecture complète
- [ ] UML à update après changements
- [ ] UML à exporter
- [ ] Mini tests mouvements aiguilles
- [ ] Check ok avec consigne
- [ ] Check si outil disponible pour d'autres labos pour générer des diagrammes UML
- [ ] Reformattage complet (avec longues lignes)

