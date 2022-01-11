package com.thana.apiviewer.event.handler;

import com.thana.apiviewer.gui.screen.APISearchScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MainEventHandler {

    private final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public void onGuiPost(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof ChatScreen) {
            int width = event.getGui().width;
            int height = event.getGui().height;

            event.addWidget(new Button(width - 80, height - 42, 70, 20, new StringTextComponent("API Viewer"), (button) -> this.mc.setScreen(new APISearchScreen())));
        }
    }
}
