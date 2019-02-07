package cm_mod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import cm_mod.cards.CMCard;
import cm_mod.actions.GainBananaEssence;
import cm_mod.patches.AddCardColor;

import basemod.abstracts.CustomRelic;

public class BananaExtractor extends CustomRelic {
	public static final String ID = "CM_BananaExtractor";
	public static final String IMG = "img/relics/banana_extractor.png";
	
	private static final int BP_THRESHOLD = 6;
	private static final int BE_GAIN_AMT = 1;
	private int increase = 0;
	
	public BananaExtractor() {
		super(ID, ImageMaster.loadImage(IMG), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + BP_THRESHOLD + DESCRIPTIONS[1] + BE_GAIN_AMT + DESCRIPTIONS[2];
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if((card.color == AddCardColor.BANANA_COLOR) && ((CMCard) card).didBurst) {
			increase = 3;
		} else {
			increase = 2;
		}
		
		if(AbstractDungeon.player.hasPower("CM_BananaBlessing")) {
			increase *= 2;
		}
		
		this.counter += increase;
		if(this.counter >= BP_THRESHOLD) {
			AbstractDungeon.actionManager.addToBottom(new GainBananaEssence(AbstractDungeon.player,
				BE_GAIN_AMT, true));
			this.counter -= BP_THRESHOLD;
		}
	}
	
	@Override
	public void onVictory() {
		this.counter = 0;
	}
	
	public AbstractRelic makeCopy() {
		return new BananaExtractor();
	}
}
