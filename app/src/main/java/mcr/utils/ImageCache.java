package mcr.utils;

import mcr.AnalogClockType;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {
    private static final Map<AnalogClockType, Image> IMAGE_MAP = new HashMap<>();

    public static Image getImage(AnalogClockType type, Dimension dimension) {
        return IMAGE_MAP.computeIfAbsent(type, t -> loadImage(t, dimension));
    }

    private static Image loadImage(AnalogClockType type, Dimension dimension) {
        Image image = Toolkit.getDefaultToolkit().getImage(type.getBackgroundImage());
        return image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_DEFAULT);
    }
}

