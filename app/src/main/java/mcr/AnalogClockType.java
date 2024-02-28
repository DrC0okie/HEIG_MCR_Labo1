package mcr;

import java.awt.*;

/**
 * Enum representing the different types of analog clock styles supported by the application.
 * Each enum constant is associated with a specific set of visual properties.
 * @author Samuel Roland, Timothée Van Hove
 */
public enum AnalogClockType {

    ROMAN("img/cadran_chiffres_romains.jpg", Color.BLACK, Color.GRAY, Color.ORANGE),

    ARABIC("img/cadran_chiffres_arabes.jpg", Color.BLACK, Color.BLUE, Color.RED);

    private final String backgroundImage;
    private final Color hourHandColor;
    private final Color minuteHandColor;
    private final Color secondHandColor;

    /**
     * Constructs an instance of {@code AnalogClockType} with its associated properties.
     * @param backgroundImage Path to the image file used as the clock's background.
     * @param hourHandColor Color of the clock's hour hand.
     * @param minuteHandColor Color of the clock's minute hand.
     * @param secondHandColor Color of the clock's second hand.
     */
    AnalogClockType(String backgroundImage, Color hourHandColor, Color minuteHandColor, Color secondHandColor) {
        this.backgroundImage = backgroundImage;
        this.hourHandColor = hourHandColor;
        this.minuteHandColor = minuteHandColor;
        this.secondHandColor = secondHandColor;
    }

    /**
     * Returns the path to the background image associated with this clock style.
     * @return A {@code String} representing the path to the background image.
     */
    public String getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Returns the color of the hour hand associated with this clock style.
     * @return A {@code Color} representing the color of the hour hand.
     */
    public Color getHourHandColor() {
        return hourHandColor;
    }

    /**
     * Returns the color of the minute hand associated with this clock style.
     * @return A {@code Color} representing the color of the minute hand.
     */
    public Color getMinuteHandColor() {
        return minuteHandColor;
    }

    /**
     * Returns the color of the second hand associated with this clock style.
     * @return A {@code Color} representing the color of the second hand.
     */
    public Color getSecondHandColor() {
        return secondHandColor;
    }
}
