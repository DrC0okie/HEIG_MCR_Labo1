package mcr;

import mcr.utils.SimpleTime;

import java.awt.*;

public class AnalogClock extends ClockPanel {

    private final Image backgroundImage;
    private static final int Y_POS_TITLE = 120;
    private static final int X_POS_TITLE = 75;
    private static final int MAX_MINUTES_SECONDS = 60;
    private static final int MAX_HOURS = 12;
    private static final int HOURS_NEEDLE_THICKNESS = 4;
    private static final int MINUTES_NEEDLE_THICKNESS = 3;
    private static final int SECONDES_NEEDLE_THICKNESS = 2;


    public AnalogClock(Chrono chrono, Dimension dimension, String backgroundSource) {
        super(chrono, dimension);
        setLayout(new FlowLayout());
        backgroundImage = Toolkit.getDefaultToolkit().getImage(backgroundSource).getScaledInstance(
                dimension.width, dimension.height, Image.SCALE_DEFAULT);
    }

    private void drawNeedle(Graphics2D graphics2D, Color color, int length, int thickness, int time, int cycleDuration) {
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(thickness, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

        double degree = calculateNeedleAngle(time, cycleDuration);
        Point endPoint = calculateNeedleEndPoint(length, degree);

        // Draw the main needle
        graphics2D.drawLine(0, 0, endPoint.x, endPoint.y);
    }

    private double calculateNeedleAngle(int time, int cycleDuration) {
        return 2.0 * Math.PI * time / cycleDuration;
    }

    private Point calculateNeedleEndPoint(int length, double degree) {
        int x = (int) (Math.sin(degree) * length);
        int y = (int) (-Math.cos(degree) * length);
        return new Point(x, y);
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        // Improve rendering quality
        setRenderingHints(graphics2D);

        // Draw the background image
        drawBackground(graphics2D);

        // Draw the clock hands
        drawClockHands(graphics2D);
    }

    private void setRenderingHints(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }

    private void drawBackground(Graphics2D graphics2D) {
        graphics2D.drawImage(backgroundImage, 0, 0, this);
    }

    private void drawClockHands(Graphics2D graphics2D) {
        graphics2D.drawString("Chrono #" + chrono.getId(), X_POS_TITLE, Y_POS_TITLE);

        // Centralize the origin
        graphics2D.translate(getWidth() / 2, getHeight() / 2);

        SimpleTime time = chrono.getTime();

        // Draw second, minute, and hour hands
        drawNeedle(graphics2D, Color.RED, (int) (getWidth() * 0.4), SECONDES_NEEDLE_THICKNESS, time.getSeconds(), MAX_MINUTES_SECONDS);
        drawNeedle(graphics2D, Color.BLUE, (int) (getWidth() * 0.3), MINUTES_NEEDLE_THICKNESS, time.getMinutes(), MAX_MINUTES_SECONDS);
        drawNeedle(graphics2D, Color.BLACK, (int) (getWidth() * 0.2), HOURS_NEEDLE_THICKNESS, time.getHours(), MAX_HOURS);
    }
}
