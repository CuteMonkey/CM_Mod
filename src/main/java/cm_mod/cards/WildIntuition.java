package cm_mod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;

import cm_mod.patches.AddCardColor;
import cm_mod.actions.ConsumeBananaEssence;
import cm_mod.cards.CMCard;

public class WildIntuition extends CMCard {
	public static final String ID = "CM_WildIntuition";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 0;
	private static final int BLOCK_AMT = 3;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int BB_COST = 1;
	private static final int BB_DRAW = 2;
	private static final int BB_UPGRADE_PLUS_DRAW = 1;
	
	public WildIntuition() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AddCardColor.BANANA_COLOR,
			AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = this.block = BLOCK_AMT;
		this.BBCost = BB_COST;
		this.BBMagic = BB_DRAW;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.BBMagic));
		}
	}
	
	public AbstractCard makeCopy() {
		return new WildIntuition();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeBBMagic(BB_UPGRADE_PLUS_DRAW);
		}
	}
}
