package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.actions.ConsumeBananaEssence;

public class WoodenBunker extends CMCard {
	public final static String ID = "CM_WoodenBunker";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 1;
	private static final int BLOCK_AMT = 6;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int BB_COST = 1;
	private static final int BB_BLOCK_AMT = 5;
	private static final int UPGRADE_BB_PLUS_BLOCK = 2;
	
	public WoodenBunker() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.SELF);
		
		this.baseBlock = this.block = BLOCK_AMT;
		this.BBCost = BB_COST;
		this.BBBaseBlock = BB_BLOCK_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
			
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.BBBlock));
			resetBBAttributes();
		}
	}
	
	public AbstractCard makeCopy() {
		return new WoodenBunker();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeBBBlock(UPGRADE_BB_PLUS_BLOCK);
		}
	}
}
