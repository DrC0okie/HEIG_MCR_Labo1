package mcr.utils;

import mcr.AnalogClockType;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class that provides a caching mechanism for images used in the application.
 * It prevents the need to load the same image from disk multiple times, improving performance.
 * @author Samuel Roland, Timothée Van Hove
 */
public class ImageCache {
    /**
     * A map to store cached images.
     */
    private static final Map<AnalogClockType, Image> IMAGE_MAP = new HashMap<>();

    /**
     * Retrieves an image from the cache based on the specified clock type and dimension. If the image
     * is not already in the cache, it is loaded from the disk and added to the cache.
     *
     * @param type The type of the analog clock for which the image is needed.
     * @param dimension The desired dimensions of the image.
     * @return An {@link Image} instance corresponding to the specified type and dimension. If the image
     * cannot be loaded, a 1x1 transparent image is returned as a fallback.
     */
    public static Image getImage(AnalogClockType type, Dimension dimension) {
        return IMAGE_MAP.computeIfAbsent(type, t -> loadImage(t, dimension));
    }

    /**
     * Loads an image from the disk based on the specified clock type and dimension. If the image cannot
     * be loaded, this method logs an error message and returns a 1x1 transparent image as a fallback.
     *
     * @param type The type of the analog clock for which the image is to be loaded.
     * @param dimension The desired dimensions of the image.
     * @return An {@link Image} instance corresponding to the specified type and dimension, or a 1x1
     * transparent image if the image cannot be loaded.
     */
    private static Image loadImage(AnalogClockType type, Dimension dimension) {
        try {
            // Attempt to load the image
            Image image = Toolkit.getDefaultToolkit().getImage(type.getBackgroundImage());
            return image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());

            // return empty image
            return new BufferedImage(1,1, BufferedImage.TYPE_INT_ARGB);
        }
    }
}

