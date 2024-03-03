package mcr.utils;

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

    // Forbid instantiation as this is a utility class with only static methods
    private ImageCache() {
    }

    /**
     * A map to store cached images.
     */
    private static final Map<String, Image> IMAGE_MAP = new HashMap<>();

    /**
     * Retrieves an image from the cache based on the specified clock path.
     * If the image is not in the cache, it is loaded from the disk and added to the cache.
     * @param path The path of the image
     * @param dimension The desired dimensions of the image.
     * @return An {@link Image} instance corresponding to the specified type and dimension. If the image
     * cannot be loaded, a 1x1 transparent image is returned as a fallback.
     */
    public static Image getImage(String path, Dimension dimension) {
        return IMAGE_MAP.computeIfAbsent(path, t -> loadImage(t, dimension));
    }

    /**
     * Loads an image from the disk based on the specified clock type and dimension. If the image cannot
     * be loaded, this method logs an error message and returns a 1x1 transparent image as a fallback.
     * @param path The type of the analog clock for which the image is to be loaded.
     * @param dimension The desired dimensions of the image.
     * @return An {@link Image} instance corresponding to the specified type and dimension, or a 1x1
     * transparent image if the image cannot be loaded.
     */
    private static Image loadImage(String path, Dimension dimension) {
        try {
            // Attempt to load the image
            Image image = Toolkit.getDefaultToolkit().getImage(path);
            return image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_DEFAULT);
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());

            // return empty image
            return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }
}
