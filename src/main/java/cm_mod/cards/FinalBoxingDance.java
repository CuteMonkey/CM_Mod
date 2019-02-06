package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.patches.AddCardTags;

public class FinalBoxingDance extends CMCard {
	public static final String ID = "CM_FinalBoxingDance";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/final_boxing_dance.png";
	
	private static final int COST = 3;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int DMG_INCREASE = 4;
	private static final int UPGRADE_PLUS_INCREASE = 1;
	
	public FinalBoxingDance() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AddCardColor.BANANA_COLOR,
			CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
		
		this.tags.add(AddCardTags.MONKEY_PUGILISM);
		
		this.baseDamage = this.damage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = DMG_INCREASE;
	}
	
	@Override
	public void triggerOnCardPlayed(AbstractCard c) {
		if(c.hasTag(AddCardTags.MONKEY_PUGILISM)) {
			AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this.uuid,
				this.magicNumber));
		}
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage,
				this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}
	
	public AbstractCard makeCopy() {
		return new FinalBoxingDance();
	}
	
	public void upgrade() {
		if(!this.upgraded ) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_PLUS_INCREASE);
		}
	}
}
