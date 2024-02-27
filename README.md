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