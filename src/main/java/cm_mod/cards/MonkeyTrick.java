package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;

public class MonkeyTrick extends CMCard {
	public static final String ID = "CM_MonkeyTrick";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/monkey_trick.png";
	
	private static final int COST = 1;
	private static final int DRAW_AMT = 3;
	private static final int UPGRADE_PLUS_DRAW = 2;
	private static final int DISCARD_AMT = 1;
	
	public MonkeyTrick() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = DRAW_AMT;
		this.BCurse = DISCARD_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.BCurse, false));
	}
	
	public AbstractCard makeCopy() {
		return new MonkeyTrick();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_DRAW);
		}
	}
}
