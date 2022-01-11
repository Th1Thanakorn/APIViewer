package com.thana.apiviewer.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thana.apiviewer.utils.sugarapi.ColorUtils;
import com.thana.object3d.utils.ChatColor;
import com.thana.object3d.utils.ColorUtils;
import com.thana.object3d.utils.TextFieldSaver;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;

import java.util.UUID;

public class PlaceholderTextFieldWidget extends TextFieldWidget {

    private final Minecraft mc = Minecraft.getInstance();
    private final String shadowText;
    private final boolean saveHistory;
    private final String uuid;
    private static final int GRAY = ColorUtils.toColorCodes(153, 163, 164);

    public PlaceholderTextFieldWidget(FontRenderer fontRenderer, int x, int y, int width, int height, ITextComponent title, String shadowText, int textSize, boolean canLoseFocus, boolean saveHistory, UUID widgetUUID) {
        super(fontRenderer, x, y, width, height, title);
        this.shadowText = ChatColor.ITALIC + shadowText;
        this.saveHistory = saveHistory;
        this.uuid = widgetUUID.toString();
        this.setMaxStringLength(textSize);
        this.setCanLoseFocus(canLoseFocus);
        this.setText(TextFieldSaver.loadText(uuid));
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
        if (this.getText().isEmpty() && !this.isFocused()) {
            int alpha = 180;
            int alphaRGB = alpha << 24 & -16777216;
            int color = GRAY | alphaRGB;
            int fontHeight = this.mc.fontRenderer.FONT_HEIGHT;
            AbstractGui.drawString(matrixStack, this.mc.fontRenderer, this.shadowText, x + 5, (y + height / 2 - fontHeight / 2), color);
        }
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        boolean flag = super.charTyped(codePoint, modifiers);
        this.textChanged();
        return flag;
    }

    @Override
    public void setText(String textIn) {
        super.setText(textIn);
        this.textChanged();
    }

    private void textChanged() {
        if (this.saveHistory) {
            TextFieldSaver.saveText(this.uuid, this.getText());
        }
    }
}
