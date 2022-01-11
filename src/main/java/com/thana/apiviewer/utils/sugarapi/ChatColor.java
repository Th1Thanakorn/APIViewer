package com.thana.apiviewer.utils.sugarapi;

import com.google.common.collect.Lists;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum ChatColor {
    BLACK("BLACK", '0', 0, 0),
    DARK_BLUE("DARK_BLUE", '1', 1, 170),
    DARK_GREEN("DARK_GREEN", '2', 2, 43520),
    DARK_AQUA("DARK_AQUA", '3', 3, 43690),
    DARK_RED("DARK_RED", '4', 4, 11141120),
    DARK_PURPLE("DARK_PURPLE", '5', 5, 11141290),
    GOLD("GOLD", '6', 6, 16755200),
    GRAY("GRAY", '7', 7, 11184810),
    DARK_GRAY("DARK_GRAY", '8', 8, 5592405),
    BLUE("BLUE", '9', 9, 5592575),
    GREEN("GREEN", 'a', 10, 5635925),
    AQUA("AQUA", 'b', 11, 5636095),
    RED("RED", 'c', 12, 16733525),
    LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13, 16733695),
    YELLOW("YELLOW", 'e', 14, 16777045),
    WHITE("WHITE", 'f', 15, 16777215),
    OBFUSCATED("OBFUSCATED", 'k', true),
    BOLD("BOLD", 'l', true),
    STRIKETHROUGH("STRIKETHROUGH", 'm', true),
    UNDERLINE("UNDERLINE", 'n', true),
    ITALIC("ITALIC", 'o', true),
    RESET("RESET", 'r', -1, (Integer)null),
    DELAY("CUSTOMIZE", 's', 16, 5465468);

    private static final Map<Object, Object> NAME_MAPPING = Arrays.stream(values()).collect(Collectors.toMap((p_199746_0_) -> lowercaseAlpha(p_199746_0_.name), (p_199747_0_) -> p_199747_0_));
    private static final Pattern FORMATTING_CODE_PATTERN = Pattern.compile("(?i)\u00a7[0-9A-FK-OR]");
    private final String name;
    private final char formattingCode;
    private final boolean fancyStyling;
    private final String controlString;
    private final int colorIndex;
    @Nullable
    private final Integer color;

    private static String lowercaseAlpha(String string) {
        return string.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
    }

    ChatColor(String formattingName, char formattingCodeIn, int index, @Nullable Integer colorCode) {
        this(formattingName, formattingCodeIn, false, index, colorCode);
    }

    ChatColor(String formattingName, char formattingCodeIn, boolean fancyStylingIn) {
        this(formattingName, formattingCodeIn, fancyStylingIn, -1, (Integer)null);
    }

    ChatColor(String formattingName, char formattingCodeIn, boolean fancyStylingIn, int index, @Nullable Integer colorCode) {
        this.name = formattingName;
        this.formattingCode = formattingCodeIn;
        this.fancyStyling = fancyStylingIn;
        this.colorIndex = index;
        this.color = colorCode;
        this.controlString = "\u00a7" + formattingCodeIn;
    }

    public int getColorIndex() {
        return this.colorIndex;
    }

    public boolean isFancyStyling() {
        return this.fancyStyling;
    }

    public boolean isColor() {
        return !this.fancyStyling && this != RESET;
    }

    public Integer getColor() {
        return this.color;
    }

    public String getFriendlyName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public String toString() {
        return this.controlString;
    }

    public static String getTextWithoutFormattingCodes(String text) {
        return FORMATTING_CODE_PATTERN.matcher(text).replaceAll("");
    }

    public static ChatColor getValueByName(String friendlyName) {
        return (ChatColor) NAME_MAPPING.get(lowercaseAlpha(friendlyName));
    }

    public static ChatColor fromColorIndex(int index) {
        if (index < 0) {
            return RESET;
        } else {
            for(ChatColor chatColor : values()) {
                if (chatColor.getColorIndex() == index) {
                    return chatColor;
                }
            }
            return null;
        }
    }

    public static ChatColor fromCPRStyle(Color color) {
        if (!color.serialize().startsWith("#")) {
            switch (color.serialize()) {
                case "red": return ChatColor.RED;
                case "black": return ChatColor.BLACK;
                case "dark_blue": return ChatColor.DARK_BLUE;
                case "dark_green": return ChatColor.DARK_GREEN;
                case "dark_aqua": return ChatColor.DARK_AQUA;
                case "dark_red": return ChatColor.DARK_RED;
                case "dark_purple": return ChatColor.DARK_PURPLE;
                case "gold": return ChatColor.GOLD;
                case "gray": return ChatColor.GRAY;
                case "dark_gray": return ChatColor.DARK_GRAY;
                case "blue": return ChatColor.BLUE;
                case "green": return ChatColor.GREEN;
                case "aqua": return ChatColor.AQUA;
                case "light_purple": return ChatColor.LIGHT_PURPLE;
                case "yellow": return ChatColor.YELLOW;
                case "white": return ChatColor.WHITE;
            }
        }
        return ChatColor.WHITE;
    }

    public static ChatColor fromRawCPRStyle(Color color) {
         for (ChatColor colors : values()) {
             if (colors.getFriendlyName().equals(color.serialize())) {
                 return colors;
             }
         }
         return ChatColor.WHITE;
    }

    public static ITextComponent textToComponent(String text, ChatColor color, boolean bold) {
        return bold ? new StringTextComponent(color + "" + ChatColor.BOLD + text) : new StringTextComponent(color + text);
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public static ChatColor fromFormattingCode(char formattingCodeIn) {
        char c0 = Character.toString(formattingCodeIn).toLowerCase(Locale.ROOT).charAt(0);
        for (ChatColor chatColor : values()) {
            if (chatColor.formattingCode == c0) {
                return chatColor;
            }
        }
        return null;
    }

    public static Collection<String> getValidValues(boolean getColor, boolean getFancyStyling) {
        List<String> list = Lists.newArrayList();
        for(ChatColor chatColor : values()) {
            if ((!chatColor.isColor() || getColor) && (!chatColor.isFancyStyling() || getFancyStyling)) {
                list.add(chatColor.getFriendlyName());
            }
        }
        return list;
    }

    public static String appendTextWithColor(ChatColor format, ChatColor color, String text) {
        return format + "" + color + text;
    }

    public static String mergeTextWithColor(ChatColor color, Object text) {
        return color + String.valueOf(text);
    }
}
