package cm_mod.relics;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

import cm_mod.actions.GainBananaEssence;

public class GlodenBananaExtractor extends CustomRelic {
	public static final String ID = "CM_GlodenBananaExtractor";
	public static final String IMG = "img/relics/gloden_banana_extractor.png";
	
	private static final int THRESHOLD = 4;
	private static final int BE_GAIN_AMT = 2;
	
	public GlodenBananaExtractor() {
		super(ID, ImageMaster.loadImage(IMG), AbstractRelic.RelicTier.BOSS, AbstractRelic.LandingSound.MAGICAL);
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + THRESHOLD + DESCRIPTIONS[1] + BE_GAIN_AMT + DESCRIPTIONS[2];
	}
	
	@Override
	public void obtain() {
		if(AbstractDungeon.player.hasRelic("CM_BananaExtractor")) {
			instantObtain(AbstractDungeon.player, 0, false);
		} else {
			super.obtain();
		}
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		this.counter++;
		if(this.counter == THRESHOLD) {
			AbstractDungeon.actionManager.addToBottom(new GainBananaEssence(AbstractDungeon.player,
				BE_GAIN_AMT, true, false));
			this.counter = 0;
		}
	}
	
	@Override
	public void atBattleStart() {
		this.counter = 0;
	}
	
	public AbstractRelic makeCopy() {
		return new GlodenBananaExtractor();
	}
}
