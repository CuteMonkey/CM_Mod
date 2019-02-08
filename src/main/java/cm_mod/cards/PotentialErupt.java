package cm_mod.cards;

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
import cm_mod.actions.ConsumeBananaEssence;
import cm_mod.powers.BananaGainSeal;

public class PotentialErupt extends CMCard {
	public static final String ID = "CM_PotentialErupt";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/potential_erupt.png";
	
	private static final int COST = 2;
	private static final int DUAL_POWER_AMT = 1;
	private static final int BB_COST = 2;
	private static final int UPGRADE_BB_COST = 1;
	private static final int BGS_AMT = 2;
	private static final int UPGRADE_BGS = 3;
	
	public PotentialErupt() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AddCardColor.BANANA_COLOR,
			CardRarity.RARE, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = DUAL_POWER_AMT;
		this.BBCost = BB_COST;
		this.BCurse = BGS_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(
			p, this.magicNumber), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(
			p, this.magicNumber), this.magicNumber));
		
		if(isBEEnough(p)) {
			int BBDualLayer = p.getPower("CM_BananaEssence").amount / this.BBCost;
			
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, BBDualLayer * this.BBCost));
			
			for(int i = 0; i < BBDualLayer; i++) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(
					p, 1), 1, true));
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(
					p, 1), 1, true));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BananaGainSeal(
				p, this.BCurse), this.BCurse));
			
			this.didBurst = true;
		} else {
			this.didBurst = false;
		}
	}
	
	public AbstractCard makeCopy() {
		return new PotentialErupt();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeBBCost(UPGRADE_BB_COST);
			modifyBCurse(UPGRADE_BGS);
		}
	}
}
