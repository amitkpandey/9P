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
import se.tube42.lib.service.*;


import se.tube42.p9.data.*;
import se.tube42.p9.logic.*;

import static se.tube42.p9.data.Constants.*;


public class BrickItem extends SpriteItem
{
    private TextBox text;
    private BitmapFont font1, font2;
    private char c;
    private int pos;
    private boolean sel;
    private GlyphLayout layout = new GlyphLayout();

    public BrickItem(int pos)
    {
        super(Assets.tex_rect, 0);

        this.font1 = Assets.fonts1[0];
        this.font2 = Assets.fonts1[1];
        this.flags |= BaseItem.FLAG_TOUCHABLE;
        this.text = new TextBox(this.font2);
        this.text.setAlignment(-0.5f, + 0.5f);

        setColor(Constants.COLOR_1);
        setChar('?', '?');
		setPosition(pos);

		setRotation(0);
		text.setFont(font2);
    }

	// ----------------------------------------

	@Override
	public void setSize(float w, float h) {
		set(ITEM_V, w).configure(Math.min(SPEED_ADD, SPEED_REMOVE), null);
		super.setSize(w, h);
	}

    public boolean free() { return !sel; }

    public void select(boolean sel, int big_size, int small_size)
    {
		if(this.sel == sel)
			return;

		// save this and get back to it in a moment
		this.sel = sel;
		final float t = RandomService.get(0.2f, 0.4f);

		if(sel) {
			final float r = RandomService.get(-3, +3);
			setRotation(t, r);
			text.setFont(font1);
			setSize(small_size, small_size);

		} else {
			setRotation(t, 0);
			text.setFont(font2);
			setSize(big_size, big_size);
		}
    }

    public int getPosition()
    {
        return pos;
    }

    public void setPosition(int pos)
    {
        this.pos = pos;
    }

    public char getChar()
    {
        return c;
    }


    public void setChar(char ascii, char unicode)
    {
        this.c = ascii;
        text.setText( Character.toString(unicode) );
    }

	public void draw(SpriteBatch sb)
	{
    	final int size = (int)get(ITEM_V);
		this.w = size;
		this.h = size;

        super.draw(sb);
        ColorHelper.set(text.getFont(), COLOR_FG, getAlpha());
        text.draw(sb, getX() + w / 2, getY() + h / 2);
    }
}
