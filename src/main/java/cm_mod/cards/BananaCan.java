package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.actions.GainBananaEssence;

public class BananaCan extends CMCard {
	public static final String ID = "CM_BananaCan";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 1;
	private static final int BE_AMT = 2;
	private static final int UPGRADE_PLUS_BE = 2;
	
	public BananaCan() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = BE_AMT;
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBananaEssence(p, this.magicNumber, false));
	}
	
	public AbstractCard makeCopy() {
		return new BananaCan();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_BE);
		}
	}
}
