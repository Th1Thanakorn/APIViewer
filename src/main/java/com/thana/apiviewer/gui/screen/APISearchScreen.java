package com.thana.apiviewer.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.thana.apiviewer.utils.sugarapi.FontColor;
import com.thana.apiviewer.utils.sugarapi.Rewrite;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class APISearchScreen extends Screen {

    public APISearchScreen() {
        super(new StringTextComponent("Players API Search"));
    }

    @Override
    public void init() {
        TextFieldWidget textFieldWidget;
        this.addButton(textFieldWidget = new TextFieldWidget(this.font, this.width / 2 - 100, this.height / 2 - 9, 200, 18, new StringTextComponent("input")));

        textFieldWidget.setCanLoseFocus(true);
        textFieldWidget.setMaxLength(32767);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        AbstractGui.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 24, FontColor.WHITE.getColorCodes());
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    @Rewrite
    public ITextComponent getTitle() {
        return this.title;
    }
}
