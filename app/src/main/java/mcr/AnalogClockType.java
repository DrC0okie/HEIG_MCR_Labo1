package mcr;

import java.awt.*;

public enum AnalogClockType {
    ROMAN("img/cadran_chiffres_romains.jpg", Color.BLACK, Color.GRAY, Color.ORANGE),
    ARABIC("img/cadran_chiffres_arabes.jpg", Color.BLACK, Color.BLUE, Color.RED);

    private final String backgroundImage;
    private final Color hourHandColor;
    private final Color minuteHandColor;
    private final Color secondHandColor;

    AnalogClockType(String backgroundImage, Color hourHandColor, Color minuteHandColor, Color secondHandColor) {
        this.backgroundImage = backgroundImage;
        this.hourHandColor = hourHandColor;
        this.minuteHandColor = minuteHandColor;
        this.secondHandColor = secondHandColor;
    }

    // Getters
    public String getBackgroundImage() {
        return backgroundImage;
    }

    public Color getHourHandColor() {
        return hourHandColor;
    }

    public Color getMinuteHandColor() {
        return minuteHandColor;
    }

    public Color getSecondHandColor() {
        return secondHandColor;
    }
}
