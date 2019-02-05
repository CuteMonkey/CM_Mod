package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

import cm_mod.CMMod;
import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;

public class UltraBananaAttack extends CMCard {
	public static final Logger logger = LogManager.getLogger(CMMod.class.getName());
	
	public static final String ID = "CM_UltraBananaAttack";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 2;
	private static final int BASE_DMG = 20;
	private static final int UPGRADE_PLUS_BASE_DMG = 5;
	private static final int EXTRA_DMG = 3;
	private static final int UPGRADE_PLUS_EXTRA_DMG = 1;
	
	private int burstCount;
	
	public UltraBananaAttack() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AddCardColor.BANANA_COLOR,
			CardRarity.RARE, CardTarget.ENEMY);
		
		this.baseDamage = this.damage = BASE_DMG;
		this.baseMagicNumber = this.magicNumber = EXTRA_DMG;
		
		burstCount = 0;
	}
	
	@Override
	public void triggerOnCardPlayed(AbstractCard c) {
		if((c.color == AddCardColor.BANANA_COLOR) && ((CMCard) c).didBurst) {
			burstCount++;
		}
	}
	
	@Override
	public void onMoveToDiscard() {
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		this.BBBaseDamage = this.damage + this.burstCount * this.magicNumber;
		
		super.calculateCardDamage(mo);
		
		this.rawDescription = DESCRIPTION + EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.BBDamage,
			this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	}
	
	public AbstractCard makeCopy() {
		return new UltraBananaAttack();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_BASE_DMG);
			upgradeMagicNumber(UPGRADE_PLUS_EXTRA_DMG);
		}
	}
}
