package mcr.clock;

import mcr.chrono.Chrono;
import mcr.utils.ImageCache;
import mcr.chrono.SimpleTime;
import java.awt.*;

/**
 * The AnalogClock class that provide a visual representation of an analog clock.
 * @author Samuel Roland, Timothée Van Hove
 */
public abstract class AnalogClock extends ClockPanel {

    private final Image backgroundImage;
    private static final int SECOND_MINUTE_CYCLE = 60;
    private static final int HOUR_CYCLE = 12;
    private static final int SECOND_HAND_THICKNESS = 2;
    private static final double SECOND_HAND_LENGTH_FACTOR = 0.4;
    private static final int MINUTE_HAND_THICKNESS = 3;
    private static final double MINUTE_HAND_LENGTH_FACTOR = 0.3;
    private static final int HOUR_HAND_THICKNESS = 4;
    private static final double HOUR_HAND_LENGTH_FACTOR = 0.2;
    protected Color secondHandColor = Color.RED;
    protected Color minuteHandColor = Color.RED;
    protected Color hourHandColor = Color.RED;

    public AnalogClock(Chrono chrono, String imagePath) {

        super(chrono);
        setLayout(new FlowLayout());

        // Retrieve the background image from the cache
        backgroundImage = ImageCache.getImage(imagePath, CLOCK_DIMENSION);
    }

    /**
     * Updates the clock display.
     */
    @Override
    public void update() {
        repaint();
    }

    /**
     * Handles the drawing the clock's background image, hands, and identifier text.
     * @param graphics The Graphics context in which to paint.
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // Improve rendering quality
        setRenderingHints(graphics2D);

        // Draw the clock components
        graphics2D.drawImage(backgroundImage, 0, 0, this);
        drawClockHands(graphics2D);
        drawChronoId(graphics2D);
    }

    protected void setSecondHandColor(Color color){
        secondHandColor = color;
    }

    protected void setMinuteHandColor(Color color){
        minuteHandColor = color;
    }

    protected void setHourHandColor(Color color){
        hourHandColor = color;
    }

    /**
     * Sets rendering hints for the Graphics2D context to improve the quality of the drawing.
     * @param graphics2D The Graphics2D context where rendering hints are applied.
     */
    private void setRenderingHints(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    /**
     * Draws the clock hands for hours, minutes, and seconds.
     * This method works on a copy of the {@link Graphics2D} because it changes its coordinates
     * @param graphics2D The Graphics2D context used for drawing the hands.
     */
    private void drawClockHands(Graphics2D graphics2D) {
        // Create a copy of the Graphics2D context to work with
        Graphics2D gCopy = (Graphics2D) graphics2D.create();

        try {
            SimpleTime time = chrono.getTime();

            // Centralize the origin for drawing hands
            gCopy.translate(getWidth() / 2, getHeight() / 2);

            // Draw second, minute, and hour hands
            drawHand(gCopy, secondHandColor, (int) (getWidth() * SECOND_HAND_LENGTH_FACTOR),
                    SECOND_HAND_THICKNESS, time.getSeconds(), SECOND_MINUTE_CYCLE);
            drawHand(gCopy, minuteHandColor, (int) (getWidth() * MINUTE_HAND_LENGTH_FACTOR),
                    MINUTE_HAND_THICKNESS, time.getMinutes(), SECOND_MINUTE_CYCLE);
            drawHand(gCopy, hourHandColor, (int) (getWidth() * HOUR_HAND_LENGTH_FACTOR),
                    HOUR_HAND_THICKNESS, time.getHours(), HOUR_CYCLE);

        } finally {
            // Dispose of the copied graphics context, reverting to the original state
            gCopy.dispose();
        }
    }

    /**
     * Draws the identifier of the Chrono associated with this clock.
     * @param graphics2D The Graphics2D context used for drawing the text.
     */
    private void drawChronoId(Graphics2D graphics2D) {
        String chronoText = "Chrono #" + chrono.getId();

        // Get metrics from the graphics
        FontMetrics metrics = graphics2D.getFontMetrics(graphics2D.getFont());

        // Center horizontally
        int x = (getWidth() - metrics.stringWidth(chronoText)) / 2;

        // Position at 60% of the height vertically
        int y = (int) (getHeight() * 0.6);

        // Draw the string
        graphics2D.drawString(chronoText, x, y);
    }

    /**
     * Draws a single hand (hour, minute, or second) on the clock.
     *
     * @param graphics2D The Graphics2D context used for drawing the hand.
     * @param color The color of the hand to be drawn.
     * @param length The length of the hand.
     * @param thickness The thickness of the hand.
     * @param time The current time value (hour, minute, or second) that the hand represents.
     * @param timeCycle The time cycle (e.g., 60 for minutes and seconds, 12 for hours).
     */
    private void drawHand(Graphics2D graphics2D, Color color, int length, int thickness, int time, int timeCycle) {
        Point endPoint = calculateNeedleEndPoint(length, time, timeCycle);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
        graphics2D.drawLine(0, 0, endPoint.x, endPoint.y);
    }

    /**
     * Calculates the end point of a clock hand based on the time and the hand length.
     * Translates the time value into an angle and then calculates the end point's coordinates.
     * @param length The length of the hand from the center of the clock.
     * @param time The current time value (hour, minute, or second) that the hand represents.
     * @param timeCycle The time cycle (e.g., 60 for minutes and seconds, 12 for hours).
     * @return A Point representing the end point of the hand.
     */
    private Point calculateNeedleEndPoint(int length, int time, int timeCycle) {
        double angle = 2.0 * Math.PI * time / timeCycle;
        int x = (int) (Math.sin(angle) * length);
        int y = (int) (-Math.cos(angle) * length);
        return new Point(x, y);
    }
}
