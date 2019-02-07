package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.actions.ConsumeBananaEssence;

public class WorkReinforcement extends CMCard {
	public static final String ID = "CM_WorkReinforcement";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 1;
	private static final int BLUR_AMT = 1;
	private static final int UPGRADE_PLUS_BLUR = 1;
	private static final int BB_COST = 1;
	private static final int BB_BLOCK_AMT = 8;
	private static final int UPGRADE_BB_PLUS_BLOCK = 4;
	
	public WorkReinforcement() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.UNCOMMON, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = BLUR_AMT;
		this.BBCost = BB_COST;
		this.BBBaseBlock = BB_BLOCK_AMT;
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		calculateMonkeyCardDamage(mo);
		super.calculateCardDamage(mo);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BlurPower(p,
			this.magicNumber), this.magicNumber));
		
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
			
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.BBBlock));
			resetBBAttributes();
			
			this.didBurst = true;
		} else {
			this.didBurst = false;
		}
	}
	
	public AbstractCard makeCopy() {
		return new WorkReinforcement();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_BLUR);
			upgradeBBBlock(UPGRADE_BB_PLUS_BLOCK);
		}
	}
}
