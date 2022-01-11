package com.thana.apiviewer.utils.sugarapi;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.FontRenderer;

import java.util.Random;

public enum FontColor {
    WHITE(ColorUtils.toColorCodes(255, 255, 255)),
    RED(ColorUtils.toColorCodes(255, 0, 0)),
    GREEN(ColorUtils.toColorCodes(0, 255, 0)),
    BLUE(ColorUtils.toColorCodes(0, 0, 255)),
    YELLOW(ColorUtils.toColorCodes(255, 255, 0)),
    MAGENTA(ColorUtils.toColorCodes(255, 0, 255)),
    AQUA(ColorUtils.toColorCodes(0, 200, 255)),
    PURE_LIGHT_BLUE(ColorUtils.toColorCodes(125, 200, 255)),
    PURPLE(ColorUtils.toColorCodes(150, 0, 255)),
    DARK_RED(ColorUtils.toColorCodes(180, 0, 0)),
    BROWN(ColorUtils.toColorCodes(150, 50, 0)),
    DARK_GREEN(ColorUtils.toColorCodes(0, 150, 0)),
    CYAN(ColorUtils.toColorCodes(0, 150, 125)),
    ORANGE(ColorUtils.toColorCodes(255, 135, 75)),
    ROSE(ColorUtils.toColorCodes(255, 65, 100)),
    SELECTABLE(ColorUtils.toColorCodes(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));


    public int colorCodes;

    FontColor(int colorCodes) {
        this.colorCodes = colorCodes;
    }

    public int getColorCodes() {
        return this.colorCodes;
    }

    static class StringRenderer {

        public FontRenderer font;
        public MatrixStack matrixStack;
        public String text;
        public float xPos;
        public float yPos;
        public int fontColor;

        public StringRenderer(FontRenderer font, MatrixStack matrixStack, String text, float xPos, float yPos, int fontColor) {
            this.font = font;
            this.matrixStack = matrixStack;
            this.text = text;
            this.xPos = xPos;
            this.yPos = yPos;
            this.fontColor = fontColor;
        }
    }
}
