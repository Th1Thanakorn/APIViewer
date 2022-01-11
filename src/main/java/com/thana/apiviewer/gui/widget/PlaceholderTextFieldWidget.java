package com.thana.apiviewer.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thana.apiviewer.utils.sugarapi.ChatColor;
import com.thana.apiviewer.utils.sugarapi.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;

import java.util.UUID;

public class PlaceholderTextFieldWidget extends TextFieldWidget {

    private final Minecraft mc = Minecraft.getInstance();
    private final String shadowText;
    private static final int GRAY = ColorUtils.toColorCodes(153, 163, 164);

    public PlaceholderTextFieldWidget(FontRenderer fontRenderer, int x, int y, int width, int height, ITextComponent title, String shadowText, int textSize, boolean canLoseFocus, boolean saveHistory, UUID widgetUUID) {
        super(fontRenderer, x, y, width, height, title);
        this.shadowText = ChatColor.ITALIC + shadowText;
        this.setMaxLength(textSize);
        this.setCanLoseFocus(canLoseFocus);
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
        if (this.getValue().isEmpty() && !this.isFocused()) {
            int alpha = 180;
            int alphaRGB = alpha << 24 & -16777216;
            int color = GRAY | alphaRGB;
            int fontHeight = this.mc.font.lineHeight;
            AbstractGui.drawString(matrixStack, this.mc.font, this.shadowText, x + 5, (y + height / 2 - fontHeight / 2), color);
        }
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return super.charTyped(codePoint, modifiers);
    }
}
