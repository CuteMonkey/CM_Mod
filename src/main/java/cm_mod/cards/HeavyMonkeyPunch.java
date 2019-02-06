package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.patches.AddCardTags;
import cm_mod.actions.ConsumeBananaEssence;

public class HeavyMonkeyPunch extends CMCard {
	public static final String ID = "CM_HeavyMonkeyPunch";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 2;
	private static final int ATTACK_DMG = 14;
	private static final int UPGRADE_PLUS_DMG = 6;
	private static final int DUAL_POWER_AMT = 2;
	private static final int BB_COST = 2;
	private static final int BB_ATTACK_DMG = 13;
	private static final int UPGRADE_BB_PLUS_DMG = 5;
	private static final int BB_DUAL_POWER_AMT = 1;
	
	public HeavyMonkeyPunch() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AddCardColor.BANANA_COLOR,
			CardRarity.UNCOMMON, CardTarget.ENEMY);
		
		this.tags.add(AddCardTags.MONKEY_PUGILISM);
		
		this.baseDamage = this.damage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = DUAL_POWER_AMT;
		this.BBCost = BB_COST;
		this.BBBaseDamage = BB_ATTACK_DMG;
		this.BBMagic = BB_DUAL_POWER_AMT;
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		calculateMonkeyCardDamage(mo);
		super.calculateCardDamage(mo);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
			this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber,
			false), this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m,
			this.magicNumber, false), this.magicNumber));
		
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
			
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.BBDamage),
				AbstractGameAction.AttackEffect.SMASH));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m, this.BBMagic,
				false), this.BBMagic));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m,
				this.BBMagic, false), this.BBMagic));
			
			this.didBurst = true;
		} else {
			this.didBurst = false;
		}
	}
	
	public AbstractCard makeCopy() {
		return new HeavyMonkeyPunch();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBBDamage(UPGRADE_BB_PLUS_DMG);
		}
	}
}
