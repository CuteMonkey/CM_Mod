package cm_mod.cards;

import com.badlogic.gdx.math.MathUtils;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;

public class MagicalBanana extends CMCard {
	public static final String ID = "CM_MagicalBanana";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 1;
	
	public MagicalBanana() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.SELF);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int result = MathUtils.random(1, 1000);
		
		if(this.upgraded) {
			if(result <= 100) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(
					p, 1), 1));
			} else if((100 < result) && (result <= 500)) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(
					p, 2), 2));
			} else if((500 < result) && (result <= 900)) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(
					p, 2), 2));
			} else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(
					p, 1), 1));
			}
		} else {
			if(result <= 500) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(
					p, 1), 1));
			} else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(
					p, 1), 1));
			}
		}
	}
	
	public AbstractCard makeCopy() {
		return new MagicalBanana();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
