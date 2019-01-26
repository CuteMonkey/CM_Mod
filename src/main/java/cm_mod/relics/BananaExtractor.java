package cm_mod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import cm_mod.powers.BananaEssence;

import basemod.abstracts.CustomRelic;

public class BananaExtractor extends CustomRelic {
	public static final String ID = "CM_BananaExtractor";
	public static final String IMG = "img/relics/banana_extractor.png";
	
	private static final int THRESHOLD = 3;
	
	public BananaExtractor() {
		super(ID, ImageMaster.loadImage(IMG), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
	}
	
	@Override
	public String getUpdatedDescription() {
		return DESCRIPTIONS[0] + THRESHOLD + DESCRIPTIONS[1];
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		this.counter++;
		if(this.counter == THRESHOLD) {
			AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player,
				AbstractDungeon.player, new BananaEssence(AbstractDungeon.player, 1), 1, true));
			this.counter = 0;
		}
	}
	
	@Override
	public void atBattleStart() {
		this.counter = 0;
	}
	
	public AbstractRelic makeCopy() {
		return new BananaExtractor();
	}
}
