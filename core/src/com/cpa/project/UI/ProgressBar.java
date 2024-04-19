package com.cpa.project.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class ProgressBar {
    public static Texture makeBarTexture(int width, int height, float health, Color color) {
        Pixmap pixmap = new Pixmap(width+4, height+2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.setColor(Color.GRAY);
        pixmap.fillRectangle(2,1, width, height);
        pixmap.setColor(color);
        int w = MathUtils.round((float)width*health);
        if(w > 0)
            pixmap.fillRectangle(2,1, w, height);
        return new Texture(pixmap);
    }
}
