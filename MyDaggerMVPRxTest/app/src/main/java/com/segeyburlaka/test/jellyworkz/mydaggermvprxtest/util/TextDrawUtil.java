package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.util;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

public class TextDrawUtil {

    private TextDrawUtil() {
        //no instance
    }

    public static TextDrawable getTextDrawable(String name) {
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color1 = generator.getColor(name);
        return TextDrawable.builder()
                .beginConfig()
                .withBorder(2)
                .endConfig()
                .buildRoundRect(name.substring(0, 1), color1, 10);
    }
}
