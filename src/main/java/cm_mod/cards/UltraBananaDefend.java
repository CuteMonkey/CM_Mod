package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;

public class UltraBananaDefend extends CMCard {
	public static final String ID = "CM_UltraBananaDefend";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 2;
	private static final int BASE_BLOCK = 18;
	private static final int UPGRADE_PLUS_BASE_BLOCK = 4;
	private static final int EXTRA_BLOCK = 3;
	private static final int UPGRADE_PLUS_EXTRA_BLOCK = 1;
	
	private int burstCount;
	
	public UltraBananaDefend() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
				CardRarity.RARE, CardTarget.SELF);
			
			this.baseBlock = this.block = BASE_BLOCK;
			this.baseMagicNumber = this.magicNumber = EXTRA_BLOCK;
			
			burstCount = 0;
	}

	@Override
	public void triggerOnCardPlayed(AbstractCard c) {
		if((c.color == AddCardColor.BANANA_COLOR) && ((CMCard) c).didBurst) {
			burstCount++;
		}
	}
	
	@Override
	public void onMoveToDiscard() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		super.calculateCardDamage(mo);
		this.BBBaseBlock = this.block + this.burstCount * this.magicNumber;
		calculateBBBlock(AbstractDungeon.player);
		
		this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.BBBlock));
	}
	
	public AbstractCard makeCopy() {
		return new UltraBananaDefend();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BASE_BLOCK);
			upgradeMagicNumber(UPGRADE_PLUS_EXTRA_BLOCK);
		}
	}
}
