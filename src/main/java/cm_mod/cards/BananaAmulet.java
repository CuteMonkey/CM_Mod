package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.powers.BananaBlessing;

public class BananaAmulet extends CMCard {
	public static final String ID = "CM_BananaAmulet";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/banana_amulet.png";
	
	private static final int COST = 1;
	private static final int BBlessing_AMT = 2;
	private static final int UPGRADE_PLUS_BBlessing = 1;
	
	public BananaAmulet() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.UNCOMMON, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = BBlessing_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BananaBlessing(
			p, this.magicNumber), this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new BananaAmulet();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_BBlessing);
		}
	}
}
