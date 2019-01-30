package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.powers.WeakPower;

import cm_mod.patches.AddCardColor;
import cm_mod.actions.ConsumeBananaEssence;
import cm_mod.cards.CMCard;

public class Uppercut extends CMCard {
	public static final String ID = "CM_Uppercut";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/uppercut.png";
	
	private static final int COST = 2;
	private static final int ATTACT_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int WEAK_AMT = 1;
	private static final int UPGRADE_PLUS_WEAK = 1;
	//the amount of banana essence needed to trigger banana burst
	private static final int BB_COST = 1;
	private static final int BB_WEAK_AMT = 1;
	private static final int BB_UPGRADE_PLUS_WEAK = 1;
	
	public Uppercut() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AddCardColor.BANANA_COLOR,
			AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseMagicNumber = this.magicNumber = WEAK_AMT;
		this.baseDamage = this.damage = ATTACT_DMG;
		this.BBCost = BB_COST;
		this.BBMagic = BB_WEAK_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
			this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber,
			false), this.magicNumber, true));
		
		//action of banana burst
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, BB_COST));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, BB_WEAK_AMT,
				false), BB_WEAK_AMT));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Uppercut();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_PLUS_WEAK);
			upgradeBBMagic(BB_UPGRADE_PLUS_WEAK);
		}
	}
}
