package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import basemod.helpers.BaseModCardTags;

import cm_mod.patches.AddCardColor;
import basemod.abstracts.CustomCard;

public class Defend extends CustomCard {
	public static final String ID = "CM_Defend";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/defend.png";
	
	private static final int COST = 1;
	private static final int BLOCK_AMT = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	
	public Defend() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.SKILL, AddCardColor.BANANA_COLOR,
			AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.SELF);
		this.tags.add(BaseModCardTags.BASIC_DEFEND);
		this.baseBlock = BLOCK_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
	}
	
	public AbstractCard makeCopy() {
		return new Defend();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}
