package cm_mod.cards;

import com.badlogic.gdx.math.MathUtils;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;

public class CriticalDodge extends CMCard {
	public static final String ID = "CM_CriticalDodge";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/no_image.png";
	
	private static final int COST = 0;
	private static final int BLOCK_AMT = 2;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	private static final int SELF_DMG = 2;
	
	public CriticalDodge() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AddCardColor.BANANA_COLOR,
			CardRarity.COMMON, CardTarget.ALL);
		
		this.baseBlock = this.block = BLOCK_AMT;
		this.BCurse = SELF_DMG;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
			if(!monster.isDead && !monster.isDying) {
				if(MathUtils.random(1, 1000) < 250) {
					AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.BCurse));
				} else {
					AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
				}
			}
		}
	}
	
	public AbstractCard makeCopy() {
		return new CriticalDodge();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}
