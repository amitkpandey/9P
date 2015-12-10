package se.tube42.p9.view;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.Input.*;

import se.tube42.lib.tweeny.*;
import se.tube42.lib.ks.*;
import se.tube42.lib.scene.*;
import se.tube42.lib.item.*;
import se.tube42.lib.util.*;

import se.tube42.p9.data.*;
import se.tube42.p9.logic.*;

import static se.tube42.p9.data.Constants.*;

public class LevelItem extends ListBaseItem
{
    private Level level;
    private int stars;
    
    public LevelItem()
    {
        this.level = null;
    }
    
    public Level getLevel()
    {
        return level;
    }
    
    public void setLevel(Level l)
    {
        this.level = l;
        if(l == null) {
            this.flags &= ~BaseItem.FLAG_VISIBLE;
            this.text = "";
            this.stars = 0;
        } else {
            this.flags |= BaseItem.FLAG_VISIBLE;
            this.text = "" + (1 + l.id);
            this.stars = GameService.calcLevelStars(l);
        }
    }
    
    
    public void draw(SpriteBatch sb)
    {
        super.draw(sb);
        
        final float x = getX();
        final float y = getY();
        // final float a = Math.max(0, 1 - (1 - getAlpha() ) * 5f); // fade quicker
        final float a = getAlpha();
        
        // stars
        final int w4 = (int)w / 4;
        final float x0 = 0.5f + x + ((int)w - 3 * w4) / 2;
        final float y0 = 0.5f + y + w4 / 4;
        
        ColorHelper.set(sb, COLOR_FG, a);
        for(int i = 0; i < 3; i++) {
            final TextureRegion tr = Assets.tex_icons[ stars > i ? ICONS_STAR1 : ICONS_STAR0];
            sb.draw(tr, x0 + w4 * i, y0, w4, w4);
        }
        
        
        // level number
        final BitmapFont.TextBounds tb = font.getBounds(text);
        final float w0 = tb.width;
        final float h0 = tb.height;
        ColorHelper.set(font, COLOR_FG, a);
        font.draw(sb, text,
                  getX() + (w - w0) / 2,
                  getY() + (h + h0) / 2
                  );
    }
}
