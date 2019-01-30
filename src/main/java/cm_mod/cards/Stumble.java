package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.patches.AddCardColor;
import cm_mod.actions.ConsumeBananaEssence;
import cm_mod.cards.CMCard;

public class Stumble extends CMCard {
	public static final String ID = "CM_Stumble";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 1;
	private static final int DAMAGE_AMT = 8;
	private static final int UPGRADE_PLUS_DMG = 4;
	private static final int BB_COST = 1;
	private static final int BB_VULNERABLE_AMT = 1;
	private static final int BB_UPGRADE_PLUS_VULNERABLE = 1;
	
	public Stumble() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.ATTACK, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.ALL_ENEMY);
		this.baseDamage = this.damage = DAMAGE_AMT;
		this.isMultiDamage = true;
		this.BBCost = BB_COST;
		this.BBMagic = BB_VULNERABLE_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage,
			this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
		
		if(isBEEnough(p)) {
			AbstractDungeon.actionManager.addToBottom(new ConsumeBananaEssence(p, this.BBCost));
			for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
				if((!monster.isDead) && (!monster.isDying)) {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p,
						new VulnerablePower(monster, this.BBMagic, false), this.BBMagic));
				}
			}
		}
	}
	
	public AbstractCard makeCopy() {
		return new Stumble();
	}
	
	public void upgrade() {
		if(!upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBBMagic(BB_UPGRADE_PLUS_VULNERABLE);
		}
	}
}
