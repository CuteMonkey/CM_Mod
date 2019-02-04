package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.patches.AddCardColor;
import cm_mod.cards.CMCard;
import cm_mod.actions.ConsumeBananaEssence;

public class BananaMeal extends CMCard {
	public static final String ID = "CM_BananaMeal";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/banana_meal.png";
	
	private static final int COST = 0;
	private static final int RECOVER_BE_COST_1 = 2;
	private static final int RECOVER_BE_COST_2 = 1;
	private static final int HP_RECOVER_1 = 16;
	private static final int HP_RECOVER_2 = 7;
	private static final int UPGRADE_PLUS_RECOVER_1 = 6;
	private static final int UPGRADE_PLUS_RECOVER_2 = 3;
	
	public BananaMeal() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR, CardRarity.UNCOMMON,
			CardTarget.SELF);
		
		this.tags.add(CardTags.HEALING);
		
		this.BBBaseDamage = this.BBDamage = RECOVER_BE_COST_1;
		this.BBBaseBlock = this.BBBlock = RECOVER_BE_COST_2;
		this.baseMagicNumber = this.magicNumber = HP_RECOVER_1;
		this.BBMagic = HP_RECOVER_2;
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.currentHealth < (p.maxHealth / 2)) {
			this.BBCost = RECOVER_BE_COST_1;
			if(isBEEnough(p)) {
				AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
				
				AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
			}
		} else {
			this.BBCost = RECOVER_BE_COST_2;
			if(isBEEnough(p)) {
				AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
				
				AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.BBMagic));
			}
		}
	}
	
	public AbstractCard makeCopy() {
		return new BananaMeal();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_RECOVER_1);
			upgradeBBMagic(UPGRADE_PLUS_RECOVER_2);
		}
	}
}
