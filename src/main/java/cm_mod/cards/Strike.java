package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import basemod.helpers.BaseModCardTags;

import cm_mod.patches.AddCardColor;
import basemod.abstracts.CustomCard;

public class Strike extends CustomCard {
	public static final String ID = "CM_Strike";
	
	private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = null;
	
	private static final int COST = 1;
	private static final int ATTACT_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;
	
	public Strike() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AddCardColor.BANANA_COLOR,
			AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACT_DMG;
		tags.add(AbstractCard.CardTags.STRIKE);
		tags.add(BaseModCardTags.BASIC_STRIKE);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage,
			this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Strike();
	}
	
	@Override
	public void upgrade() {
		upgradeName();
		upgradeDamage(UPGRADE_PLUS_DMG);
	}
}
