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

public class BananaMeal extends CMCard {
	public static final String ID = "CM_BananaMeal";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 0;
	private static final int HP_RECOVER_1 = 12;
	private static final int HP_RECOVER_2 = 7;
	private static final int UPGRADE_PLUS_RECOVER_1 = 4;
	private static final int UPGRADE_PLUS_RECOVER_2 = 3;
	
	public BananaMeal() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR, CardRarity.UNCOMMON,
			CardTarget.SELF);
		this.tags.add(CardTags.HEALING);
		this.baseMagicNumber = HP_RECOVER_1;
		this.BBMagic = HP_RECOVER_2;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.currentHealth < p.maxHealth / 2) {
			AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
		} else {
			AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.BBMagic));
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
