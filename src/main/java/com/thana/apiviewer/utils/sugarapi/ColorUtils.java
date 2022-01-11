package com.thana.apiviewer.utils.sugarapi;

public class ColorUtils {

    public static int toColorCodes(int red, int green, int blue) {
        return 65536 * red + 256 * green + blue;
    }
}
