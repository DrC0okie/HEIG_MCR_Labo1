package mcr.utils;

import mcr.AnalogClockType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static final Map<AnalogClockType, Image> IMAGE_MAP = new HashMap<>();

    public static Image getImage(AnalogClockType type, Dimension dimension) {
        return IMAGE_MAP.computeIfAbsent(type, t -> loadImage(t, dimension));
    }

    private static Image loadImage(AnalogClockType type, Dimension dimension) {
        try {
            // Attempt to load the image
            Image image = Toolkit.getDefaultToolkit().getImage(type.getBackgroundImage());

            // Check if the image was successfully loaded
            if (image.getWidth(null) == -1) {
                throw new IllegalArgumentException("Image not found: " + type.getBackgroundImage());
            }

            return image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());

            // Optionally, return a placeholder or default image to avoid null references
            return createPlaceholderImage(dimension);
        }
    }
    private static Image createPlaceholderImage(Dimension dimension) {
        // Create a simple placeholder image (e.g., a colored rectangle) as a fallback
        BufferedImage placeholder = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = placeholder.createGraphics();
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, dimension.width, dimension.height);
        g2d.dispose();
        return placeholder;
    }
}

