package com.typorrhea.glasshole;

import com.google.android.glass.app.Card;

import android.app.Activity;
import android.os.Bundle;

public class GlassholeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Card card = new Card(this);
		card.setImageLayout(Card.ImageLayout.FULL);
		card.addImage(R.drawable.smiling_jackass);
		card.setText("Thanks, I'll try!");
		card.setFootnote("(glasshole)");
		setContentView(card.getView());
	}
}
