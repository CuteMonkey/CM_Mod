package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.patches.AddCardTags;
import cm_mod.actions.ConsumeBananaEssence;

public class SerialMonkeyJab extends CMCard {
	public static final String ID = "CM_SerialMonkeyJab";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 2;
	private static final int ATTACK_DMG = 4;
	private static final int ATTACK_NUMBER = 2;
	private static final int UPGRADE_PLUS_DMG = 1;
	private static final int BB_COST = 1;
	private static final int UPGRADE_BB_COST = 2;
	private static final int BB_ATTACK_DMG = 4;
	private static final int BB_ATTACK_NUMBER = 2;
	private static final int UPGRADE_BB_PLUS_DMG = 2;
	
	public SerialMonkeyJab() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.ENEMY);
		
		this.tags.add(AddCardTags.MONKEY_PUGILISM);
		
		this.baseDamage = this.damage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = ATTACK_NUMBER;
		this.BBCost = BB_COST;
		this.BBBaseDamage = BB_ATTACK_DMG;
		this.BBMagic = BB_ATTACK_NUMBER;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0; i < this.magicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p,
				this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
		}
		
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
			
			for(int i = 0; i < this.BBMagic; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p,
					this.BBDamage), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
			}
			resetBBAttributes();
		}
	}
	
	public AbstractCard makeCopy() {
		return new SerialMonkeyJab();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBBCost(UPGRADE_BB_COST);
			upgradeBBDamage(UPGRADE_BB_PLUS_DMG);
		}
	}
}
