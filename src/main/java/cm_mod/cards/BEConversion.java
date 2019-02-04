package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.actions.ConsumeBananaEssence;

public class BEConversion extends CMCard {
	public static final String ID = "CM_BEConversion";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 0;
	private static final int BB_COST = 1;
	private static final int MAX_COUNT = 3;
	private static final int UPGRADE_MAX_COUNT = 2;
	
	public BEConversion() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.SELF);
		
		this.BBCost = BB_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(isBEEnough(p)) {
			int count;
			int currBEAmt = p.getPower("CM_BananaEssence").amount;
			if(this.upgraded) {
				if(currBEAmt >= UPGRADE_MAX_COUNT) {
					count = UPGRADE_MAX_COUNT;
				} else {
					count = currBEAmt;
				}
				
				for(int i = 0; i < count; i++) {
					AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, 1));
					
					AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
					AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
				}
			} else {
				if(currBEAmt >= MAX_COUNT) {
					count = MAX_COUNT;
				} else {
					count = currBEAmt;
				}
				
				for(int i = 0; i < count; i++) {
					AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, 1));
					
					AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
				}
			}
		}
	}
	
	public AbstractCard makeCopy() {
		return new BEConversion();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
