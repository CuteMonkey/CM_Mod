package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;

public class LitterStone extends CMCard {
	public static final String ID = "CM_LitterStone";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/litter_stone.png";
	
	private static final int COST = 0;
	private static final int ATTACK_DMG = 2;
	private static final int ATTACK_NUMBER = 2;
	private static final int UPGRADE_PLUS_NUMBER = 2;
	
	public LitterStone() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.ALL_ENEMY);
		
		this.baseDamage = this.damage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = ATTACK_NUMBER;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i = 0; i < this.magicNumber; i++) {
			AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(
				p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
		}
	}
	
	public AbstractCard makeCopy() {
		return new LitterStone();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_NUMBER);
		}
	}
}
